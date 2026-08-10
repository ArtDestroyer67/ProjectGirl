package com.girlmod.client.effect;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Client-side visual effects triggered from animation keyframes — see
 * GirlEntity#onSoundKeyframe, which calls into these for "blackScreen",
 * "carry_introCam"/"carry_introCam2", and (for heart particles) any real
 * voice line firing during a hasPlayer state.
 *
 * These aren't ported from fapcraft's actual implementation — the relevant
 * render methods in their decompiled classes failed to decompile
 * ("$FF: Couldn't be decompiled") across every candidate I checked, so
 * this is a standard/equivalent implementation using normal Forge
 * techniques (RenderGameOverlayEvent for the fade, EntityViewRenderEvent
 * for the zoom), wired to the same keyframe names.
 *
 * Registered as a static event-handler class (MinecraftForge.EVENT_BUS
 * .register(ClientEffects.class)) in GirlMod's clientSetup.
 */
@OnlyIn(Dist.CLIENT)
public class ClientEffects {

    // ── Screen blackout fade ─────────────────────────────────────────────────

    private static final int BLACKOUT_FADE_IN_TICKS  = 6;
    private static final int BLACKOUT_HOLD_TICKS      = 4;
    private static final int BLACKOUT_FADE_OUT_TICKS  = 10;
    private static final int BLACKOUT_TOTAL_TICKS =
        BLACKOUT_FADE_IN_TICKS + BLACKOUT_HOLD_TICKS + BLACKOUT_FADE_OUT_TICKS;

    private static int blackoutTicksLeft = 0;

    public static void triggerBlackout() {
        blackoutTicksLeft = BLACKOUT_TOTAL_TICKS;
    }

    private static float currentBlackoutAlpha() {
        if (blackoutTicksLeft <= 0) return 0f;
        int elapsed = BLACKOUT_TOTAL_TICKS - blackoutTicksLeft;
        if (elapsed < BLACKOUT_FADE_IN_TICKS) {
            return elapsed / (float) BLACKOUT_FADE_IN_TICKS;
        } else if (elapsed < BLACKOUT_FADE_IN_TICKS + BLACKOUT_HOLD_TICKS) {
            return 1f;
        } else {
            int intoFadeOut = elapsed - BLACKOUT_FADE_IN_TICKS - BLACKOUT_HOLD_TICKS;
            return 1f - (intoFadeOut / (float) BLACKOUT_FADE_OUT_TICKS);
        }
    }

    // ── Camera zoom ───────────────────────────────────────────────────────────

    private static final int ZOOM_DURATION_TICKS = 20; // 1 second

    private static int   zoomTicksLeft = 0;
    private static float zoomStrength  = 0f; // 0..1, how much FOV narrows at peak

    /** strength: how strong the zoom-in is, roughly 0 (none) to ~1.5 (strong). */
    public static void triggerCameraZoom(float strength) {
        zoomTicksLeft = ZOOM_DURATION_TICKS;
        zoomStrength  = strength;
    }

    // ── Heart particles ──────────────────────────────────────────────────────

    /** Spawns a small burst of vanilla heart particles above the given position — see GirlEntity#onSoundKeyframe. */
    public static void spawnHearts(net.minecraft.world.World world, double x, double y, double z) {
        if (!(world instanceof net.minecraft.client.world.ClientWorld)) return; // client-local visual only, never networked
        net.minecraft.client.world.ClientWorld clientWorld = (net.minecraft.client.world.ClientWorld) world;
        java.util.Random rand = world.random;
        int count = 2 + rand.nextInt(2); // 2-3 hearts per trigger
        for (int i = 0; i < count; i++) {
            double ox = (rand.nextDouble() - 0.5) * 0.6;
            double oz = (rand.nextDouble() - 0.5) * 0.6;
            clientWorld.addParticle(
                net.minecraft.particles.ParticleTypes.HEART,
                x + ox, y + rand.nextDouble() * 0.3, z + oz,
                0.0, 0.05, 0.0
            );
        }
    }

    // ── Per-tick decay ────────────────────────────────────────────────────────

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (blackoutTicksLeft > 0) blackoutTicksLeft--;
        if (zoomTicksLeft > 0) zoomTicksLeft--;
    }

    // ── Rendering the blackout overlay ──────────────────────────────────────

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        float alpha = currentBlackoutAlpha();
        if (alpha <= 0f) return;

        MainWindow window = Minecraft.getInstance().getWindow();
        int a = Math.round(alpha * 255f);
        int argb = (a << 24); // black, alpha only
        MatrixStack stack = event.getMatrixStack();
        AbstractGui.fill(stack, 0, 0, window.getGuiScaledWidth(), window.getGuiScaledHeight(), argb);
    }

    // ── Applying the camera zoom ─────────────────────────────────────────────

    @SubscribeEvent
    public static void onFov(EntityViewRenderEvent.FOVModifier event) {
        if (zoomTicksLeft <= 0) return;
        float t = zoomTicksLeft / (float) ZOOM_DURATION_TICKS; // 1 -> 0 over the effect's duration
        float narrowing = 1.0f - (zoomStrength * 0.3f * t);
        event.setFOV(event.getFOV() * narrowing);
    }
}
