# GirlMod — Development Documentation

**Minecraft:** 1.16.5  
**Forge:** 36.2.39  
**GeckoLib:** 3.0.106  
**Java:** 8  

---

## Table of Contents

1. [Project Structure](#1-project-structure)
2. [How It Works End-to-End](#2-how-it-works-end-to-end)
3. [File Reference](#3-file-reference)
4. [Animation System](#4-animation-system)
5. [Sound System](#5-sound-system)
6. [Networking](#6-networking)
7. [1.12.2 → 1.16.5 API Changes](#7-1122--1165-api-changes)
8. [GeckoLib: What Changed Between Versions](#8-geckolib-what-changed-between-versions)
9. [Swapping to Your Own Assets](#9-swapping-to-your-own-assets)
10. [Adding New Animations](#10-adding-new-animations)
11. [Adding New Sounds](#11-adding-new-sounds)
12. [Building and Running](#12-building-and-running)
13. [Known Limitations](#13-known-limitations)

---

## 1. Project Structure

```
girlmod/
├── build.gradle
├── src/main/
│   ├── java/com/girlmod/
│   │   ├── GirlMod.java                  Main mod class, lifecycle, /spawngirl
│   │   ├── entity/
│   │   │   ├── GirlEntity.java           Entity class (AI, DataManager, GeckoLib)
│   │   │   └── AnimState.java            Enum of all animation states
│   │   ├── client/
│   │   │   ├── model/GirlModel.java      AnimatedGeoModel (asset paths)
│   │   │   ├── renderer/GirlRenderer.java GeoEntityRenderer wrapper
│   │   │   └── gui/GuiGirlInteract.java  Right-click Screen GUI
│   │   ├── network/
│   │   │   ├── PacketHandler.java        SimpleChannel registration
│   │   │   └── PacketSetState.java       Client→Server animation packet
│   │   ├── sound/
│   │   │   └── SoundMapper.java          Effect name → SoundEvent mapping
│   │   └── init/
│   │       └── ModEntities.java          DeferredRegister entity types
│   └── resources/
│       ├── pack.mcmeta
│       ├── META-INF/mods.toml            Mod descriptor (replaces mcmod.info)
│       └── assets/girlmod/
│           ├── lang/en_us.json
│           ├── geo/                      ← put your .geo.json here later
│           ├── animations/               ← put your .animation.json here later
│           └── textures/entity/          ← put your .png here later
```

---

## 2. How It Works End-to-End

```
Player right-clicks GirlEntity
        │
        ▼ (client side)
GuiGirlInteract opens
        │
        ▼ Player clicks button
PacketSetState sent to server
        │
        ▼ (server side, enqueueWork)
GirlEntity.setState(COWGIRL_SLOW)
  - writes "COWGIRL_SLOW" to DataManager (SynchedEntityData)
  - DataManager automatically syncs to all nearby clients
  - starts PLAY_ONCE tick counter if applicable
        │
        ▼ (client side, every render frame)
GeckoLib calls animationPredicate()
  - reads DataManager state → AnimState.COWGIRL_SLOW
  - state changed? → controller.setAnimation(builder.loop("animation.ellie.cowgirlslow2"))
  - returns PlayState.CONTINUE
        │
        ▼ (client side, at animation keyframe timestamps)
GeckoLib calls onSoundKeyframe(event)
  - event.sound = "lipsound" (from animation JSON)
  - SoundMapper.resolve("lipsound") → random variant from girls.ellie.lipsound.*
  - world.playLocalSound(...) plays it at entity position
```

---

## 3. File Reference

### GirlMod.java
The `@Mod` entry point. In 1.16.5 this replaces the old `@Mod.EventHandler` pattern
with an event bus subscription model.

Key responsibilities:
- Attaches `ModEntities.ENTITY_TYPES` DeferredRegister to the mod bus
- Calls `PacketHandler.register()` during common setup
- Registers `GirlRenderer` during client setup (no more ClientProxy class)
- Handles `EntityAttributeCreationEvent` — required in 1.16.5, attributes are
  no longer part of the entity class directly
- Registers the `/spawngirl` Brigadier command on server start

### GirlEntity.java
Extends `MobEntity` (was `EntityCreature` in 1.12.2).

Key responsibilities:
- `defineSynchedData()` — registers the STATE DataParameter (replaces `entityInit`)
- `setState(AnimState)` — server-side, writes to DataManager, starts tick counter
- `getAnimState()` — reads DataManager, safe from any thread
- `tick()` — handles PLAY_ONCE → followUp auto-transition
- `mobInteract()` — opens GUI on right-click (replaces `processInteract`)
- `registerControllers()` — GeckoLib callback, creates AnimationController
  and registers both the animation predicate and the sound listener
- `animationPredicate()` — reads state, calls `setAnimation()` only on change
- `onSoundKeyframe()` — receives GeckoLib sound events, plays via SoundMapper

### AnimState.java
Pure data enum. Each entry represents one animation state with:
- `animName` — the key in the .animation.json file
- `loopType` — LOOP / PLAY_ONCE / HOLD_ON_LAST_FRAME
- `hasPlayer` — whether to face the nearest player on entry
- `followUp` — next state after PLAY_ONCE finishes (null = stay)
- `durationTicks` — approximate duration for PLAY_ONCE timing (20 ticks = 1 second)

### GirlModel.java
Extends `AnimatedGeoModel<GirlEntity>`. Implements three abstract methods:
- `getModelLocation()` → path to .geo.json
- `getTextureLocation()` → path to .png
- `getAnimationFileLocation()` → path to .animation.json

Currently all three point at fapcraft's sexmod assets.

### GirlRenderer.java
Extends `GeoEntityRenderer<GirlEntity>`. Minimal — just passes the model to
the GeckoLib renderer. `shadowRadius` controls the shadow size under the entity.

### GuiGirlInteract.java
Extends `Screen` (was `GuiScreen` in 1.12.2). Uses Forge's widget `Button`
with lambda callbacks instead of `actionPerformed` override. Sends
`PacketSetState` on click and immediately closes.

### SoundMapper.java
Static utility class. Maps GeckoLib effect name strings (from the animation JSON's
`sound_effects` blocks) to arrays of sexmod SoundEvent resource paths.
Picks a random variant on each call for variety. Non-sound effects (state
transition triggers like `"stripDone"`) are listed in `NON_SOUND_EFFECTS`
and silently ignored.

### PacketSetState.java
Client→Server packet. Contains entityId (int) and stateName (String).
Uses 1.16.5's encode/decode/handle static method pattern instead of
the 1.12.2 `IMessage` / `IMessageHandler` interface pair.
Server validates entity type and player distance before calling `setState()`.

### ModEntities.java
Uses `DeferredRegister<EntityType<?>>` to declare entity types. The actual
Forge registry event is handled automatically by attaching to the mod bus
in `GirlMod` constructor. `EntityType.Builder` replaces `EntityRegistry.registerModEntity`.

---

## 4. Animation System

### How GeckoLib drives animations

1. On first render, GeckoLib's `AnimationFactory.getOrCreateAnimationData(entityId)` 
   is called. If no data exists yet, it calls `entity.registerControllers(data)`.
2. Every render frame, `GeoEntityRenderer.doRender()` calls
   `model.setLivingAnimations()` which calls `animationProcessor.tickAnimation()`.
3. `tickAnimation()` loops over every registered `AnimationController` and calls
   `testAnimationPredicate()` which invokes your predicate lambda.
4. Your predicate returns `PlayState.CONTINUE` or `PlayState.STOP`.
5. If the predicate called `controller.setAnimation(builder)`, GeckoLib transitions
   to that animation (with the configured transition tick count).

### The `lastRenderedState` guard

Without this guard, `setAnimation()` would be called every frame. GeckoLib
handles this but it resets the animation to frame 0 every frame, making it
appear frozen. The guard ensures `setAnimation()` is only called when the
state actually changes.

```java
if (state != lastRenderedState) {
    lastRenderedState = state;
    event.getController().setAnimation(buildAnimation(state));
}
return PlayState.CONTINUE;
```

### PLAY_ONCE auto-transition

PLAY_ONCE animations (like `COWGIRL_START`) automatically transition to their
`followUp` state (like `COWGIRL_SLOW`) after `durationTicks` ticks on the
server. This is tracked in `GirlEntity.tick()`. The duration is an approximation
in ticks (20/sec) — update `AnimState.durationTicks` to match actual animation
lengths when you know them.

### Loop types

| Type | GeckoLib constant | Behavior |
|------|------------------|----------|
| LOOP | `ILoopType.EDefaultLoopTypes.LOOP` | Plays forever |
| PLAY_ONCE | `ILoopType.EDefaultLoopTypes.PLAY_ONCE` | Plays once, freezes |
| HOLD_ON_LAST_FRAME | `ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME` | Plays once, holds last frame |

---

## 5. Sound System

### How GeckoLib sound keyframes work

The animation JSON contains `sound_effects` blocks:
```json
"animation.ellie.hug": {
  "sound_effects": {
    "1.1":  { "effect": "hugMSG2" },
    "2.6":  { "effect": "hugMSG3" }
  }
}
```

When the animation tick passes `1.1` seconds, GeckoLib fires the registered
`ISoundListener.playSound(SoundKeyframeEvent)` where `event.sound = "hugMSG2"`.

### Registration

```java
controller.registerSoundListener(this::onSoundKeyframe);
```

This is done in `registerControllers()`. Without this call, all sound_effects
keyframes in the JSON are silently ignored (this is what fapcraft does — it
manages sounds separately and ignores GeckoLib's system entirely).

### SoundMapper

`SoundMapper.resolve(effectName)` translates the effect string to a SoundEvent:

```
"hugMSG2"  →  random pick from girls.ellie.giggle.giggle0 … giggle4
"lipsound" →  random pick from girls.ellie.lipsound.lipsound0 … lipsound9
"pound"    →  random pick from misc.pounding.pounding0 … pounding35
```

Non-sound effects (state machine triggers like `"stripDone"`, `"openSexUi"`)
are listed in `NON_SOUND_EFFECTS` and return null, which is a no-op.

### Sound event IDs

Fapcraft's sound events use the `sexmod` namespace. The full ResourceLocation
for a sound is `sexmod:girls.ellie.lipsound.lipsound3`. Forge looks this up in
sexmod's `sounds.json` which maps it to the actual .ogg file path.

When you add your own sounds, you'll register them in your own `sounds.json`
under the `girlmod` namespace and update SoundMapper accordingly.

---

## 6. Networking

### Channel setup

```java
SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
    new ResourceLocation("girlmod", "main"),
    () -> "1",          // protocol version
    "1"::equals,        // client accepts server if version matches
    "1"::equals         // server accepts client if version matches
);
```

### Packet flow

```
Client GUI button click
  → CHANNEL.sendToServer(new PacketSetState(entityId, state))
  → PacketSetState.encode() serializes to PacketBuffer
  → (network)
  → PacketSetState.decode() deserializes
  → PacketSetState.handle() called on netty thread
  → ctx.enqueueWork() schedules on server thread
  → GirlEntity.setState(state)
```

### Thread safety

`enqueueWork()` is critical — packet handlers run on the netty IO thread, not
the server tick thread. Entity data must only be modified on the server thread.
Without `enqueueWork()` you will get concurrent modification crashes.

---

## 7. 1.12.2 → 1.16.5 API Changes

| 1.12.2 | 1.16.5 | Notes |
|--------|--------|-------|
| `EntityCreature` | `MobEntity` | Base class renamed |
| `EntityAISwimming` | `SwimGoal` | All AI goals renamed |
| `EntityAIWanderAvoidWater` | `RandomWalkingGoal` | |
| `EntityAIWatchClosest` | `LookAtGoal` | |
| `EntityDataManager` | `EntityDataManager` | Same name, new methods |
| `DataParameter<T>` | `DataParameter<T>` | Same |
| `EntityDataManager.createKey()` | `EntityDataManager.defineId()` | Renamed |
| `entityInit()` | `defineSynchedData()` | Renamed |
| `entityInit` → `register()` | `defineSynchedData` → `define()` | |
| `dataManager.get()` | `entityData.get()` | Field renamed |
| `dataManager.set()` | `entityData.set()` | Field renamed |
| `processInteract()` | `mobInteract()` | Renamed |
| `world.isRemote` | `level.isClientSide` | Field renamed |
| `world.addEntity()` | `world.addFreshEntity()` | Renamed |
| `EntityRegistry.registerModEntity()` | `DeferredRegister<EntityType<?>>` | Completely different |
| Attributes on entity | `EntityAttributeCreationEvent` | Decoupled |
| `RenderingRegistry` (ClientProxy) | `RenderingRegistry` (client setup event) | No more proxy |
| `SimpleNetworkWrapper` | `SimpleChannel` | Different API |
| `IMessage` / `IMessageHandler` | encode/decode/handle statics | Different pattern |
| `GuiScreen` | `Screen` | Renamed |
| `GuiButton` | `Button` (widget) | Lambda-based |
| `drawScreen()` | `render(MatrixStack, ...)` | MatrixStack added |
| `mcmod.info` | `META-INF/mods.toml` | Different format |
| `@Mod.EventHandler` | `modBus.addListener()` | Event bus subscription |
| `posX/Y/Z` | `getX()/getY()/getZ()` | Fields → methods |
| `rotationYaw` | `yRot` | Renamed |

---

## 8. GeckoLib: What Changed Between Versions

Almost nothing in the core API changed between GeckoLib 3.0.57 (1.12.2) and
3.0.106 (1.16.5). The same classes, same method signatures, same patterns:

| Feature | Same in both versions |
|---------|----------------------|
| `AnimatedGeoModel` abstract methods | ✓ identical |
| `AnimationController` constructor | ✓ identical |
| `IAnimationPredicate` / lambda | ✓ identical |
| `AnimationBuilder.loop()` / `.playOnce()` | ✓ identical |
| `ILoopType.EDefaultLoopTypes` | ✓ identical |
| `registerControllers(AnimationData)` | ✓ identical |
| `ISoundListener` / `registerSoundListener()` | ✓ identical |
| `SoundKeyframeEvent.sound` | ✓ identical |
| `PlayState.CONTINUE` / `.STOP` | ✓ identical |
| `GeoEntityRenderer` base class | ✓ identical |
| Asset file formats (.geo.json, .animation.json) | ✓ identical |

The only GeckoLib difference: in 1.16.5 `GeoEntityRenderer` constructor takes
`EntityRendererManager` instead of `RenderManager` (renamed by Mojang).

---

## 9. Swapping to Your Own Assets

When you finish your model, animation, and texture files:

### Step 1 — Add your files

```
src/main/resources/assets/girlmod/
├── geo/girl/girl.geo.json
├── animations/girl/girl.animation.json
└── textures/entity/girl/girl.png
```

### Step 2 — Update GirlModel.java

```java
// Change these three lines:
private static final ResourceLocation GEO  =
    new ResourceLocation("girlmod", "geo/girl/girl.geo.json");
private static final ResourceLocation TEX  =
    new ResourceLocation("girlmod", "textures/entity/girl/girl.png");
private static final ResourceLocation ANIM =
    new ResourceLocation("girlmod", "animations/girl/girl.animation.json");
```

### Step 3 — Update AnimState.java animation names

Change each `animName` string to match the keys in your new JSON:
```java
IDLE("animation.girl.idle", ...),
WALK("animation.girl.walk", ...),
// etc.
```

### Step 4 — Update durationTicks

Measure each PLAY_ONCE animation's length in seconds, multiply by 20:
```java
COWGIRL_START("animation.girl.cowgirl_start", PLAY_ONCE, true, COWGIRL_SLOW, 320),
//                                                                             ^^^
//                                              16 seconds × 20 ticks/sec = 320
```

### Step 5 — fapcraft is no longer required at runtime

Once GirlModel points at `girlmod:` assets instead of `sexmod:`, your mod
runs completely independently. Sounds still need updating (see next section).

---

## 10. Adding New Animations

1. Add the animation to your .animation.json with a unique key.
2. Add a new entry to `AnimState.java`:
   ```java
   MY_ANIM("animation.girl.myanim", LoopType.LOOP, false, null, 0),
   ```
3. Add a button for it in `GuiGirlInteract.java`:
   ```java
   addBtn(colX1, y, "My Anim", AnimState.MY_ANIM); y += GAP;
   ```
4. That's it — the predicate, DataManager sync, and networking all handle
   it automatically.

---

## 11. Adding New Sounds

### Step 1 — Add .ogg files

```
src/main/resources/assets/girlmod/sounds/
└── girl/
    ├── moan0.ogg
    ├── moan1.ogg
    └── moan2.ogg
```

### Step 2 — Create sounds.json

```json
{
  "girl.moan": {
    "sounds": [
      "girlmod:girl/moan0",
      "girlmod:girl/moan1",
      "girlmod:girl/moan2"
    ]
  }
}
```
Place at: `src/main/resources/assets/girlmod/sounds.json`

### Step 3 — Register the SoundEvent

```java
// In a new ModSounds.java:
public static final DeferredRegister<SoundEvent> SOUNDS =
    DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GirlMod.MODID);

public static final RegistryObject<SoundEvent> GIRL_MOAN =
    SOUNDS.register("girl.moan", () ->
        new SoundEvent(new ResourceLocation("girlmod", "girl.moan"))
    );
```
Attach `ModSounds.SOUNDS.register(modBus)` in `GirlMod` constructor.

### Step 4 — Update SoundMapper.java

```java
put("lipsound", new String[]{"girl.moan"}); // now uses your sound
```

Change `ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("sexmod", chosen))`
to `new ResourceLocation("girlmod", chosen)`.

### Step 5 — Add sound_effects to your animation JSON

```json
"animation.girl.carry_slow": {
  "sound_effects": {
    "0.083": { "effect": "lipsound" },
    "1.667": { "effect": "lipsound" }
  }
}
```

GeckoLib fires these automatically — no code changes needed.

---

## 12. Building and Running

### Prerequisites

- JDK 8
- Gradle (wrapper included)
- fapcraft installed in the run/mods folder (for temporary Ellie assets)
- GeckoLib 3.0.106 for 1.16.5 installed in run/mods

### Setup

```bash
./gradlew genEclipseRuns    # Eclipse
./gradlew genIntellijRuns   # IntelliJ IDEA
```

### Run client

```bash
./gradlew runClient
```

### Build jar

```bash
./gradlew build
# Output: build/libs/girlmod-1.0.0.jar
```

### In-game

1. Start a world
2. Run `/spawngirl` (requires op level 2)
3. Right-click the girl
4. Pick an animation from the GUI

---

## 13. Known Limitations

**PLAY_ONCE timing is approximate.** The `durationTicks` values in `AnimState`
are estimates. If an animation is 15.48 seconds, set `durationTicks = 310`
(15.48 × 20). A better approach would be a client→server "animation finished"
packet, but the tick counter is simpler and good enough for now.

**Sound only plays client-side.** `world.playLocalSound()` plays for the local
player only. To make sounds audible to all nearby players, use
`ServerWorld.playSound()` on the server side instead. This requires detecting
the keyframe server-side (currently not possible without a client→server packet).

**Requires fapcraft at runtime (temporarily).** All three asset paths in
`GirlModel` point at `sexmod:` resources. fapcraft must be in the mods folder
for the entity to render. This goes away as soon as you put your own assets in
`assets/girlmod/` and update `GirlModel`.

**AI is basic.** The entity wanders randomly and looks at the player. It makes
no attempt to navigate to the player for `hasPlayer` states. A proper
`MoveToPlayerGoal` that activates during sex states would improve this.
