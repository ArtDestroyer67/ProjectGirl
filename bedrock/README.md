# GirlMod — Bedrock port (private use only)

This is a **from-scratch Bedrock Add-On**, not a converted Java mod — Bedrock's
architecture doesn't support most of what the Forge version does. See
"What's NOT here" below before expecting feature parity.

## What this includes

- A summonable/spawnable entity `girlmod:girl` with health, collision,
  movement, and basic wandering AI (`random_stroll`, `look_at_player`,
  `random_look_around`) — the Bedrock equivalent of the Java version's
  baseline idle/follow-ish behavior.
- Idle/Walk animations switch automatically based on actual movement
  (`query.is_moving`), same idea as the Java mod's auto IDLE↔WALK switch —
  no command needed for this part.
- Two example poses (**hug**, **sit**) wired end-to-end as a template for
  adding more: a `girlmod:pose` entity property drives an animation
  controller, changed via entity events.
- The actual `.geo.json` model, `.animation.json` (all ~40 animations, not
  just the 2 wired up), and the texture, copied over as-is — these formats
  are close enough to native Bedrock that they needed no conversion.

## What's NOT here (and can't be, without a much bigger scripting-based build)

- **No GUI** — no pose picker, no inventory/armor screen, no Recover
  button. Bedrock add-ons can't show custom screens like that at all.
  Poses are switched with commands (see below) instead of clicking a button.
- **No armor/weapon equip system, no held-item rendering.**
- **No HP/downed/recovery system, no mob whitelist/blacklist, no
  approach-and-attach behavior.**
- **No sound-effect keyframe dispatch, no particle/blackout/zoom effects.**
- Only 2 of the ~40 available animations are wired into the animation
  controller (idle/walk/hug/sit) — everything else is copied over and
  ready to use, just not connected to a trigger yet. See "Adding more
  poses" below.

Rebuilding any of the above would mean using Bedrock's Script API
(JavaScript/TypeScript, a genuinely different, more limited environment
than Forge's Java access) — a much larger, separate undertaking.

## Installing (private use)

1. Copy the `BP` folder into
   `com.mojang/development_behavior_packs/GirlMod_BP` (in your world's
   `com.mojang` folder, or the global one depending on platform).
2. Copy the `RP` folder into
   `com.mojang/development_resource_packs/GirlMod_RP`.
3. In-game: create/edit a world → Behavior Packs → activate "GirlMod
   (Bedrock, private)" → it should auto-activate the matching resource
   pack via the dependency link in the manifest (activate it manually if not).
4. You'll need "Holiday Creator Features" / experimental toggles OFF is
   fine here — nothing in this pack currently requires an experimental
   toggle, entity properties are stable as of the engine version targeted
   (1.21.70).

## Testing

```
/summon girlmod:girl ~ ~ ~
```

She should wander, look around, and idle/walk automatically. To test the
pose system:

```
/event entity @e[type=girlmod:girl,c=1] girlmod:set_pose_hug
/event entity @e[type=girlmod:girl,c=1] girlmod:set_pose_sit
/event entity @e[type=girlmod:girl,c=1] girlmod:set_pose_idle
```

## One thing to verify visually first

`girl.geo.json`'s `texture_width`/`texture_height` is declared as `64x64`,
but the actual `girl.png` is `4096x4096` — a clean 64× multiple. That's
very likely intentional (a common technique: author the UV layout against
a small reference grid, then use a proportionally-scaled high-res texture
— Bedrock maps UVs proportionally regardless of the real bound texture's
pixel size, same as Java). It *should* just work, but I can't render it
myself to confirm — if she looks like a distorted texture mess on first
spawn, this field is the first thing to check.

## Adding more poses

1. Pick an animation name from `RP/animations/girl.animation.json`
   (e.g. `animation.ellie.sitdown`).
2. Add a value to the `girlmod:pose` enum in
   `BP/entities/girl.behavior.json` and a matching
   `girlmod:set_pose_<name>` event.
3. Add the animation as a short-name in `RP/entity/girl.entity.json`'s
   `animations` block.
4. Add a state to `RP/animation_controllers/girl.animation_controllers.json`
   following the `hug`/`sit` pattern.

No Java/scripting needed for this — it's the same pattern repeated.
