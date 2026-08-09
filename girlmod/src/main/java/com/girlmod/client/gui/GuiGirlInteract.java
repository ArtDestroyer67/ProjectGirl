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
 * Interaction GUI shown when the player right-clicks the GirlEntity.
 *
 * Two kinds of buttons:
 *   - Toggle row (Follow Me/Stop Following, Dress/Strip) — booleans sent
 *     via PacketSetFlag, GUI stays open so you can flip more than one.
 *   - Pose buttons — generated dynamically from StateConfig.getAllIds(),
 *     sent via PacketSetState, GUI closes after picking one.
 *
 * Add a new state to states.json and run /girlmod reload; a pose button
 * for it appears here automatically next time the GUI opens.
 */
@OnlyIn(Dist.CLIENT)
public class GuiGirlInteract extends Screen {

    private final GirlEntity entity;
    private final PlayerEntity player;

    private static final int BTN_W = 140;
    private static final int BTN_H = 18;
    private static final int GAP   = 22;
    private static final int TOGGLE_Y = 40;
    private static final int STATE_TOP_Y = 66; // leaves room below the toggle row

    public GuiGirlInteract(GirlEntity entity, PlayerEntity player) {
        super(new StringTextComponent("Girl"));
        this.entity = entity;
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();

        // ── Toggle row: Follow / Dress ──────────────────────────────────────
        int toggleX1 = this.width / 2 - BTN_W - 5;
        int toggleX2 = this.width / 2 + 5;
        addFollowToggleBtn(toggleX1, TOGGLE_Y);
        addDressToggleBtn(toggleX2, TOGGLE_Y);

        // ── Pose buttons, generated from StateConfig ────────────────────────
        List<String> stateIds = StateConfig.getAllIds();
        if (stateIds.isEmpty()) return;

        int perCol = (stateIds.size() + 1) / 2;
        for (int i = 0; i < stateIds.size(); i++) {
            String id  = stateIds.get(i);
            int col    = i / perCol;
            int row    = i % perCol;
            int x      = this.width / 2 + (col == 0 ? -BTN_W - 5 : 5);
            int y      = STATE_TOP_Y + row * GAP;
            addStateBtn(x, y, id);
        }
    }

    private void addFollowToggleBtn(int x, int y) {
        boolean following = entity.isFollowing();
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(following ? "Stop Following" : "Follow Me"),
            btn -> {
                boolean newValue = !entity.isFollowing();
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetFlag(entity.getId(), PacketSetFlag.FLAG_FOLLOWING, newValue)
                );
                this.onClose();
            }
        ));
    }

    private void addDressToggleBtn(int x, int y) {
        boolean dressed = entity.isDressed();
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(dressed ? "Strip" : "Dress"),
            btn -> {
                boolean newValue = !entity.isDressed();
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetFlag(entity.getId(), PacketSetFlag.FLAG_DRESSED, newValue)
                );
                this.onClose();
            }
        ));
    }

    private void addStateBtn(int x, int y, String stateId) {
        boolean active = entity.getStateId().equals(stateId);
        String label = prettyName(stateId);
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(active ? "\u25BA " + label : label), // ► prefix on the active state
            btn -> {
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetState(entity.getId(), stateId)
                );
                this.onClose();
            }
        ));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        drawCenteredString(stack, this.font, "Girl", this.width / 2, 12, 0xFFFFD700);
        drawCenteredString(stack, this.font,
            "State: " + prettyName(entity.getStateId())
            + (entity.isFollowing() ? "  |  Following" : "")
            + (entity.isDressed()   ? "  |  Dressed"   : "  |  Nude"),
            this.width / 2, 26, 0xFFFFFFFF);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() { return false; }

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
