package com.girlmod.client.gui;

import com.girlmod.entity.AnimState;
import com.girlmod.entity.GirlEntity;
import com.girlmod.network.PacketHandler;
import com.girlmod.network.PacketSetState;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiGirlInteract extends Screen {

    private final GirlEntity entity;
    private final PlayerEntity player;

    private static final int BTN_W = 130;
    private static final int BTN_H = 18;
    private static final int GAP   = 22;

    public GuiGirlInteract(GirlEntity entity, PlayerEntity player) {
        super(new StringTextComponent("Girl"));
        this.entity = entity;
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
        int colX1 = this.width / 2 - BTN_W - 5;
        int colX2 = this.width / 2 + 5;
        int y;

        // Left column: General
        y = 50;
        addBtn(colX1, y, "Idle",     AnimState.IDLE);     y += GAP;
        addBtn(colX1, y, "Sit",      AnimState.SIT);      y += GAP;
        addBtn(colX1, y, "Sit Down", AnimState.SITDOWN);  y += GAP;
        addBtn(colX1, y, "Hug",      AnimState.HUG);      y += GAP;
        addBtn(colX1, y, "Hug Idle", AnimState.HUGIDLE);  y += GAP;
        addBtn(colX1, y, "Strip",    AnimState.STRIP);

        // Right column: Cowgirl
        y = 50;
        addBtn(colX2, y, "Cowgirl Start", AnimState.COWGIRL_START); y += GAP;
        addBtn(colX2, y, "Cowgirl Slow",  AnimState.COWGIRL_SLOW);  y += GAP;
        addBtn(colX2, y, "Cowgirl Fast",  AnimState.COWGIRL_FAST);  y += GAP;
        addBtn(colX2, y, "Cowgirl Cum",   AnimState.COWGIRL_CUM);   y += GAP + 10;

        // Right column: Missionary
        addBtn(colX2, y, "Miss. Start", AnimState.MISSIONARY_START); y += GAP;
        addBtn(colX2, y, "Miss. Slow",  AnimState.MISSIONARY_SLOW);  y += GAP;
        addBtn(colX2, y, "Miss. Fast",  AnimState.MISSIONARY_FAST);  y += GAP;
        addBtn(colX2, y, "Miss. Cum",   AnimState.MISSIONARY_CUM);   y += GAP + 10;

        // Right column: Carry
        addBtn(colX2, y, "Carry Intro", AnimState.CARRY_INTRO); y += GAP;
        addBtn(colX2, y, "Carry Slow",  AnimState.CARRY_SLOW);  y += GAP;
        addBtn(colX2, y, "Carry Fast",  AnimState.CARRY_FAST);  y += GAP;
        addBtn(colX2, y, "Carry Cum",   AnimState.CARRY_CUM);
    }

    private void addBtn(int x, int y, String label, AnimState state) {
        boolean active = entity.getAnimState() == state;
        this.addButton(new Button(x, y, BTN_W, BTN_H,
            new StringTextComponent(active ? "► " + label : label),
            btn -> {
                PacketHandler.CHANNEL.sendToServer(
                    new PacketSetState(entity.getId(), state)
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
            "State: " + entity.getAnimState().name(), this.width / 2, 26, 0xFFFFFFFF);

        int colX1 = this.width / 2 - BTN_W - 5;
        int colX2 = this.width / 2 + 5;
        drawString(stack, this.font, "\u00A7eGeneral",    colX1, 40, 0xFFAAAAAA);
        drawString(stack, this.font, "\u00A7eCowgirl",    colX2, 40, 0xFFAAAAAA);
        drawString(stack, this.font, "\u00A7eMissionary", colX2, 40 + GAP * 4 + 10, 0xFFAAAAAA);
        drawString(stack, this.font, "\u00A7eCarry",      colX2, 40 + GAP * 8 + 20, 0xFFAAAAAA);

        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
