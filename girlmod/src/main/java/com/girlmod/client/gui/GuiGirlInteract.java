package com.girlmod.client.gui;

import com.girlmod.config.StateConfig;
import com.girlmod.entity.GirlEntity;
import com.girlmod.network.PacketHandler;
import com.girlmod.network.PacketSetFlag;
import com.girlmod.network.PacketSetState;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

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
    private static final int STATE_TOP_Y = 62;   // first pose button Y
    private static final int PAGE_BTN_H  = 16;
    private static final int MARGIN_BOT  = 28;   // space reserved at bottom for page controls

    // Pagination state
    private int currentPage = 0;
    private int pagesTotal  = 1;

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

        // ── Toggle row ────────────────────────────────────────────────────
        int cx = this.width / 2;

        // How many toggles? Follow + Dress + Partner + (Armor if dressed) = 3 or 4
        boolean showArmor = entity.isDressed();
        int toggleCount   = showArmor ? 4 : 3;
        int totalToggleW  = toggleCount * BTN_W + (toggleCount - 1) * 6;
        int toggleStartX  = cx - totalToggleW / 2;

        // Follow toggle
        addFollowBtn(toggleStartX, TOGGLE_Y);
        // Dress toggle
        addDressBtn(toggleStartX + BTN_W + 6, TOGGLE_Y);
        // Partner rig toggle (force-show steve for testing, independent of pose)
        addPartnerBtn(toggleStartX + (BTN_W + 6) * 2, TOGGLE_Y);
        // Armor toggle (only when dressed)
        if (showArmor) {
            addArmorBtn(toggleStartX + (BTN_W + 6) * 3, TOGGLE_Y);
        }

        // ── Pose buttons, paginated ───────────────────────────────────────
        List<String> stateIds = StateConfig.getAllIds();
        if (stateIds.isEmpty()) return;

        // How many rows fit between STATE_TOP_Y and bottom margin?
        int usableH  = this.height - STATE_TOP_Y - MARGIN_BOT;
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
            int y      = STATE_TOP_Y + row * GAP;
            addStateBtn(x, y, id);
        }

        // ── Pagination controls ────────────────────────────────────────────
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

    private void addArmorBtn(int x, int y) {
        boolean armored = entity.isArmored();
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(armored ? "Armor: ON" : "Armor: OFF"),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetFlag(entity.getId(), PacketSetFlag.FLAG_ARMOR, !armored));
                // Rebuild in place so the button label updates immediately
                init();
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
            + (entity.isDressed()   ? (entity.isArmored() ? "  |  Armored" : "  |  No Armor") : "")
            + (entity.isPartnerForced() ? "  |  Partner Forced" : "");
        drawCenteredString(stack, this.font, status, this.width / 2, 22, 0xFFFFFFFF);

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
