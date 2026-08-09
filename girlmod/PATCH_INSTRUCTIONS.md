# GirlMod Patch — Armor Toggle + GUI Pagination

## Files to REPLACE entirely:
- `src/main/java/com/girlmod/network/PacketSetFlag.java`
- `src/main/java/com/girlmod/client/renderer/GirlRenderer.java`
- `src/main/java/com/girlmod/client/gui/GuiGirlInteract.java`

## File to EDIT manually: GirlEntity.java

### Step 1 — Add DataParameter field (near STATE and FOLLOWING):
```java
private static final DataParameter<Boolean> ARMORED =
    EntityDataManager.defineId(GirlEntity.class, DataSerializers.BOOLEAN);
```

### Step 2 — Register in defineSynchedData():
```java
this.entityData.define(ARMORED, true); // default armor ON
```

### Step 3 — Add getter/setter (near isDressed/setDressed):
```java
public boolean isArmored() { return this.entityData.get(ARMORED); }
public void setArmored(boolean armored) { this.entityData.set(ARMORED, armored); }
```

### Step 4 — Save in addAdditionalSaveData():
```java
nbt.putBoolean("Armored", isArmored());
```

### Step 5 — Load in readAdditionalSaveData():
```java
if (nbt.contains("Armored")) setArmored(nbt.getBoolean("Armored"));
```

## What changed:

### Armor Toggle
- New FLAG_ARMOR in PacketSetFlag
- GirlRenderer hides/shows armor bones by scaling to 0
- Armor button only appears in GUI when entity is Dressed
- Toggles live without closing GUI

### GUI Pagination
- Page size calculated dynamically from screen height
- Two columns of pose buttons, no overflow
- Prev/Next buttons at bottom
- Page X/Y indicator
- Armor: ON/OFF button added to toggle row
