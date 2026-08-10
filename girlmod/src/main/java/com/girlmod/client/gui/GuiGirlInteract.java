package com.girlmod.client.gui;

import com.girlmod.config.AnimationSetConfig;
import com.girlmod.config.SkinConfig;
import com.girlmod.config.StateConfig;
import com.girlmod.config.StateDefinition;
import com.girlmod.entity.GirlEntity;
import com.girlmod.network.PacketHandler;
import com.girlmod.network.PacketOpenInventory;
import com.girlmod.network.PacketRecover;
import com.girlmod.network.PacketSetAnimSet;
import com.girlmod.network.PacketSetFlag;
import com.girlmod.network.PacketSetSkin;
import com.girlmod.network.PacketSetState;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Interaction GUI — right-click GirlEntity to open.
 *
 * Changes from original:
 *   1. Paginated pose buttons — no more buttons flying off screen.
 *      Page size is calculated dynamically from screen height.
 *   2. Armor toggle button — "Armor ON / Armor OFF" — only shown
 *      when entity is dressed (armor only exists on the dressed model).
 *
 * Layout:
 *   Row 0  : title + state info              (drawn in render())
 *   Row 1  : Follow | Dress | Armor (if dressed)    toggle buttons
 *   Row 2+ : pose buttons, paginated (2 columns)
 *   Bottom : Prev Page / Page X/Y / Next Page
 */
@OnlyIn(Dist.CLIENT)
public class GuiGirlInteract extends Screen {

    private final GirlEntity  entity;
    private final PlayerEntity player;

    // Layout constants
    private static final int BTN_W       = 130;
    private static final int BTN_H       = 18;
    private static final int GAP         = 22;   // vertical spacing between buttons
    private static final int TOGGLE_Y    = 36;
    private static final int PAGE_BTN_H  = 16;
    private static final int MARGIN_BOT  = 28;   // space reserved at bottom for page controls
    private static final int ROW_MARGIN  = 10;   // left/right margin used when deciding how many buttons fit per row

    // Recomputed each buildButtons() call based on how many rows the
    // toggle row (and, for poses, the group row) actually needed to wrap
    // into on the current screen width — see layoutButtonRow().
    private int stateTopY = 62;

    // Pagination state
    private int currentPage = 0;
    private int pagesTotal  = 1;
    // Which list the bottom panel currently shows — cycled by the
    // "Poses"/"Skins"/"Anims" button (kept as one button rather than one
    // per list to leave room in the toggle row).
    private enum ViewMode { POSES, SKINS, ANIMS }
    private ViewMode viewMode = ViewMode.POSES;
    // Which group (from states.json's optional "group" field) the pose
    // grid is currently filtered to — cycled by its own button, only
    // shown while viewMode == POSES. Null = not yet initialized; resolved
    // to the first available group on first build.
    private String currentGroup = null;

    public GuiGirlInteract(GirlEntity entity, PlayerEntity player) {
        super(new StringTextComponent("Girl"));
        this.entity = entity;
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
        currentPage = Math.min(currentPage, Math.max(0, pagesTotal - 1));
        buildButtons();
    }

    private void buildButtons() {
        this.buttons.clear();
        this.children.clear();

        // ── Toggle row — wraps onto additional rows automatically if the
        //    5 buttons don't fit the current screen width, instead of
        //    running off-screen. ────────────────────────────────────────
        int cx = this.width / 2;

        List<BiConsumer<Integer, Integer>> toggles = new ArrayList<>();
        toggles.add(this::addFollowBtn);
        toggles.add(this::addDressBtn);
        toggles.add(this::addPartnerBtn);
        toggles.add(this::addInventoryBtn);
        toggles.add(this::addViewModeToggleBtn);
        int toggleRows = layoutButtonRow(TOGGLE_Y, toggles, BTN_W);

        stateTopY = TOGGLE_Y + toggleRows * GAP;

        // ── Pose buttons, paginated (or a Recover button while downed, or
        //    the skin/anim-set list depending on viewMode) ─────────────────
        // Pose picking is hidden entirely while downed — she stays downed
        // indefinitely (no automatic recovery) until a player manually
        // clicks Recover, sent via PacketRecover. A pose click would be
        // silently ignored server-side by PacketSetState anyway, so this
        // avoids showing a grid of buttons that don't do anything.
        if (entity.isDowned()) {
            pagesTotal = 1;
            addRecoverBtn(cx - BTN_W / 2, stateTopY);
            return;
        }

        if (viewMode == ViewMode.SKINS) {
            buildSkinButtons(cx);
            return;
        }
        if (viewMode == ViewMode.ANIMS) {
            buildAnimSetButtons(cx);
            return;
        }

        // ── Group filter row (only for poses) ───────────────────────────────
        List<String> groups = StateConfig.getGroups();
        if (groups.isEmpty()) return;
        if (currentGroup == null || !groups.contains(currentGroup)) {
            currentGroup = groups.get(0);
        }
        addGroupToggleBtn(cx - BTN_W / 2, stateTopY, groups);
        int poseTopY = stateTopY + GAP;

        List<String> stateIds = new ArrayList<>();
        for (String id : StateConfig.getAllIds()) {
            StateDefinition def = StateConfig.get(id);
            // Movement states (IDLE/WALK) are driven automatically by her
            // own locomotion, not something to pick from the GUI. "hidden"
            // states are opted out entirely regardless of group (combat
            // swings, etc — see the "hidden" field in states.json). Only
            // the currently selected group's states show up as buttons.
            if (def.isMovement || def.hidden) continue;
            if (!def.group.equals(currentGroup)) continue;
            stateIds.add(id);
        }
        if (stateIds.isEmpty()) return;

        // How many rows fit between the pose grid's top and bottom margin?
        int usableH  = this.height - poseTopY - MARGIN_BOT;
        int rowsPerPage = Math.max(1, usableH / GAP);
        int perPage  = rowsPerPage * 2; // 2 columns

        pagesTotal  = Math.max(1, (int) Math.ceil((double) stateIds.size() / perPage));
        currentPage = Math.min(currentPage, pagesTotal - 1);

        int startIdx = currentPage * perPage;
        int endIdx   = Math.min(startIdx + perPage, stateIds.size());
        List<String> pageIds = stateIds.subList(startIdx, endIdx);

        int halfPage = (int) Math.ceil(pageIds.size() / 2.0);
        for (int i = 0; i < pageIds.size(); i++) {
            String id  = pageIds.get(i);
            int col    = i / halfPage;
            int row    = i % halfPage;
            int x      = cx + (col == 0 ? -BTN_W - 3 : 3);
            int y      = poseTopY + row * GAP;
            addStateBtn(x, y, id);
        }

        addPaginationControls(cx);
    }

    /** Same paginated 2-column grid layout as the pose buttons, sourced from SkinConfig instead of StateConfig. */
    private void buildSkinButtons(int cx) {
        List<String> skinIds = new ArrayList<>(SkinConfig.getAll().keySet());
        if (skinIds.isEmpty()) return;

        int usableH  = this.height - stateTopY - MARGIN_BOT;
        int rowsPerPage = Math.max(1, usableH / GAP);
        int perPage  = rowsPerPage * 2; // 2 columns

        pagesTotal  = Math.max(1, (int) Math.ceil((double) skinIds.size() / perPage));
        currentPage = Math.min(currentPage, pagesTotal - 1);

        int startIdx = currentPage * perPage;
        int endIdx   = Math.min(startIdx + perPage, skinIds.size());
        List<String> pageIds = skinIds.subList(startIdx, endIdx);

        int halfPage = (int) Math.ceil(pageIds.size() / 2.0);
        for (int i = 0; i < pageIds.size(); i++) {
            String id  = pageIds.get(i);
            int col    = i / halfPage;
            int row    = i % halfPage;
            int x      = cx + (col == 0 ? -BTN_W - 3 : 3);
            int y      = stateTopY + row * GAP;
            addSkinBtn(x, y, id);
        }

        addPaginationControls(cx);
    }

    /** Same paginated 2-column grid layout as the pose buttons, sourced from AnimationSetConfig instead of StateConfig. */
    private void buildAnimSetButtons(int cx) {
        List<String> setIds = new ArrayList<>(AnimationSetConfig.getAll().keySet());
        if (setIds.isEmpty()) return;

        int usableH  = this.height - stateTopY - MARGIN_BOT;
        int rowsPerPage = Math.max(1, usableH / GAP);
        int perPage  = rowsPerPage * 2; // 2 columns

        pagesTotal  = Math.max(1, (int) Math.ceil((double) setIds.size() / perPage));
        currentPage = Math.min(currentPage, pagesTotal - 1);

        int startIdx = currentPage * perPage;
        int endIdx   = Math.min(startIdx + perPage, setIds.size());
        List<String> pageIds = setIds.subList(startIdx, endIdx);

        int halfPage = (int) Math.ceil(pageIds.size() / 2.0);
        for (int i = 0; i < pageIds.size(); i++) {
            String id  = pageIds.get(i);
            int col    = i / halfPage;
            int row    = i % halfPage;
            int x      = cx + (col == 0 ? -BTN_W - 3 : 3);
            int y      = stateTopY + row * GAP;
            addAnimSetBtn(x, y, id);
        }

        addPaginationControls(cx);
    }

    private void addPaginationControls(int cx) {
        if (pagesTotal > 1) {
            int pageY = this.height - MARGIN_BOT + 4;

            // Prev
            this.addButton(new Button(cx - 120, pageY, 50, PAGE_BTN_H,
                new StringTextComponent("< Prev"),
                btn -> { if (currentPage > 0) { currentPage--; init(); } }
            ));
            // Next
            this.addButton(new Button(cx + 70, pageY, 50, PAGE_BTN_H,
                new StringTextComponent("Next >"),
                btn -> { if (currentPage < pagesTotal - 1) { currentPage++; init(); } }
            ));
        }
    }

    /**
     * Lays out a row of same-width buttons centered on screen, wrapping
     * onto additional rows (also centered) if they don't all fit within
     * the current screen width — fixes buttons running off-screen on
     * narrower windows/resolutions instead of assuming a fixed width
     * always fits. Returns how many rows were used, so the caller can
     * position whatever comes next below them.
     */
    private int layoutButtonRow(int startY, List<BiConsumer<Integer, Integer>> buttons, int btnWidth) {
        int available = this.width - ROW_MARGIN * 2;
        int perRow = Math.max(1, Math.min(buttons.size(), (available + 6) / (btnWidth + 6)));
        int rows = (int) Math.ceil((double) buttons.size() / perRow);

        for (int i = 0; i < buttons.size(); i++) {
            int row = i / perRow;
            int col = i % perRow;
            int itemsInRow = Math.min(perRow, buttons.size() - row * perRow);
            int rowWidth = itemsInRow * btnWidth + (itemsInRow - 1) * 6;
            int rowStartX = this.width / 2 - rowWidth / 2;
            int x = rowStartX + col * (btnWidth + 6);
            int y = startY + row * GAP;
            buttons.get(i).accept(x, y);
        }
        return rows;
    }

    // ── Toggle button helpers ─────────────────────────────────────────────────

    private void addFollowBtn(int x, int y) {
        boolean following = entity.isFollowing();
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(following ? "Stop Following" : "Follow Me"),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetFlag(entity.getId(), PacketSetFlag.FLAG_FOLLOWING, !following));
                this.onClose();
            }
        ));
    }

    private void addDressBtn(int x, int y) {
        boolean dressed = entity.isDressed();
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(dressed ? "Strip" : "Dress"),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetFlag(entity.getId(), PacketSetFlag.FLAG_DRESSED, !dressed));
                this.onClose();
            }
        ));
    }

    private void addInventoryBtn(int x, int y) {
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent("Inventory"),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(new PacketOpenInventory(entity.getId()));
                this.onClose();
            }
        ));
    }

    private void addPartnerBtn(int x, int y) {
        boolean forced = entity.isPartnerForced();
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(forced ? "Partner: ON" : "Partner: OFF"),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetFlag(entity.getId(), PacketSetFlag.FLAG_PARTNER, !forced));
                // Rebuild in place so the button label updates immediately
                init();
            }
        ));
    }

    private void addViewModeToggleBtn(int x, int y) {
        String label;
        switch (viewMode) {
            case POSES: label = "Poses";  break;
            case SKINS: label = "Skins";  break;
            default:    label = "Anims";  break;
        }
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent("View: " + label),
            btn -> {
                ViewMode[] order = ViewMode.values();
                viewMode = order[(viewMode.ordinal() + 1) % order.length];
                currentPage = 0; // switching lists — start back at page 1
                init();
            }
        ));
    }

    private void addGroupToggleBtn(int x, int y, List<String> groups) {
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent("Group: " + currentGroup),
            btn -> {
                int idx = groups.indexOf(currentGroup);
                currentGroup = groups.get((idx + 1) % groups.size());
                currentPage = 0; // switching groups — start back at page 1
                init();
            }
        ));
    }

    // ── Pose button ───────────────────────────────────────────────────────────

    private void addStateBtn(int x, int y, String stateId) {
        boolean active = entity.getStateId().equals(stateId);
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(active ? "\u25BA " + prettyName(stateId) : prettyName(stateId)),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetState(entity.getId(), stateId));
                this.onClose();
            }
        ));
    }

    // ── Skin button ───────────────────────────────────────────────────────────

    private void addSkinBtn(int x, int y, String skinId) {
        boolean active = entity.getSkinId().equals(skinId);
        String displayName = SkinConfig.get(skinId).displayName;
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(active ? "\u25BA " + displayName : displayName),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(new PacketSetSkin(entity.getId(), skinId));
                this.onClose();
            }
        ));
    }

    // ── Animation set button ─────────────────────────────────────────────────

    private void addAnimSetBtn(int x, int y, String setId) {
        boolean active = entity.getAnimationSetId().equals(setId);
        String displayName = AnimationSetConfig.get(setId).displayName;
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(active ? "\u25BA " + displayName : displayName),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(new PacketSetAnimSet(entity.getId(), setId));
                this.onClose();
            }
        ));
    }

    // ── Recover button (shown instead of the pose grid while downed) ───────────

    private void addRecoverBtn(int x, int y) {
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent("Recover"),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(new PacketRecover(entity.getId()));
                this.onClose();
            }
        ));
    }

    // ── Render ────────────────────────────────────────────────────────────────

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);

        // Title
        drawCenteredString(stack, this.font, "Girl", this.width / 2, 10, 0xFFFFD700);

        // Status line
        String status = "State: " + prettyName(entity.getStateId())
            + (entity.isFollowing() ? "  |  Following"  : "")
            + (entity.isDressed()   ? "  |  Dressed"    : "  |  Nude")
            + (entity.hasAnyArmorEquipped() ? "  |  Armored" : "")
            + (entity.isPartnerForced() ? "  |  Partner Forced" : "")
            + "  |  Skin: " + SkinConfig.get(entity.getSkinId()).displayName
            + "  |  Anim: " + AnimationSetConfig.get(entity.getAnimationSetId()).displayName;
        drawCenteredString(stack, this.font, status, this.width / 2, 22, 0xFFFFFFFF);

        if (entity.isDowned()) {
            drawCenteredString(stack, this.font, "Downed — click Recover to heal her",
                this.width / 2, stateTopY, 0xFFFF5555);
        }

        // Page indicator (only shown when there are multiple pages)
        if (pagesTotal > 1) {
            drawCenteredString(stack, this.font,
                "Page " + (currentPage + 1) + " / " + pagesTotal,
                this.width / 2, this.height - MARGIN_BOT + 6, 0xFFAAAAAA);
        }

        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() { return false; }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** "COWGIRL_SLOW" → "Cowgirl Slow" */
    private String prettyName(String id) {
        String[] parts = id.split("_");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (p.isEmpty()) continue;
            if (sb.length() > 0) sb.append(' ');
            sb.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1).toLowerCase());
        }
        return sb.toString();
    }
}
