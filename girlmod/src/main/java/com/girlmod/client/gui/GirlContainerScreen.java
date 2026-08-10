package com.girlmod.client.gui;

import com.girlmod.inventory.GirlContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Reuses vanilla's own survival-inventory background (textures/gui/
 * container/inventory.png — the exact same texture the player's own 'e'
 * inventory screen uses, already has the armor slot outlines drawn in the
 * right place) and vanilla's InventoryScreen.renderEntityInInventory helper
 * to draw her as the "doll" instead of the player, per the request to
 * reuse the player's own UI.
 */
@OnlyIn(Dist.CLIENT)
public class GirlContainerScreen extends ContainerScreen<GirlContainer> {

    private static final ResourceLocation TEXTURE =
        new ResourceLocation("textures/gui/container/inventory.png");

    public GirlContainerScreen(GirlContainer container, PlayerInventory playerInv, ITextComponent title) {
        super(container, playerInv, new StringTextComponent("Girl"));
        this.imageWidth  = 176;
        this.imageHeight = 166;
        this.inventoryLabelY = this.imageHeight - 94; // matches vanilla's own inventory screen
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int x = this.leftPos, y = this.topPos;
        this.blit(stack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Doll area — same spot/technique vanilla uses to render the player
        // in their own inventory screen, just pointed at her instead.
        InventoryScreen.renderEntityInInventory(
            x + 51, y + 75, 30,
            (float) (x + 51) - mouseX, (float) (y + 75 - 50) - mouseY,
            this.menu.girl
        );
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
