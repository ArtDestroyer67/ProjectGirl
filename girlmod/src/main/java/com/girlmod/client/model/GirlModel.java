package com.girlmod.client.model;

import com.girlmod.entity.GirlEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.HashMap;
import java.util.Map;

/**
 * GeoModel for GirlEntity.
 *
 * getModelLocation() now checks entity.isDressed() and returns one of
 * two geo files — GeckoLib re-queries this every render, so toggling
 * the DRESSED flag (via the GUI "Dress"/"Strip" button) swaps her
 * actual 3D model live, no animation needed for the swap itself.
 *
 * Both geo files were copied from fapcraft's Ellie as a starting point:
 *   girl.geo.json         - nude body
 *   girl_dressed.geo.json - clothed body
 * Replace either (or both) with your own once you have final assets —
 * nothing else needs to change as long as the filenames match.
 */
@OnlyIn(Dist.CLIENT)
public class GirlModel extends AnimatedGeoModel<GirlEntity> {

    private static final ResourceLocation GEO_NUDE =
        new ResourceLocation("girlmod", "geo/girl/girl.geo.json");
    private static final ResourceLocation GEO_DRESSED =
        new ResourceLocation("girlmod", "geo/girl/girl_dressed.geo.json");

    private static final ResourceLocation TEX_GIRL =
        new ResourceLocation("girlmod", "textures/entity/girl/girl.png");

    // Separate sheet for the embedded "steve" partner rig, so it never
    // shares UV space with the girl atlas. Lives in its own folder so it
    // can later be swapped for the real local-player skin if desired.
    private static final ResourceLocation TEX_PLAYER =
        new ResourceLocation("girlmod", "textures/player/steve.png");

    private static final ResourceLocation ANIM =
        new ResourceLocation("girlmod", "animations/girl/girl.animation.json");

    // Resolved textures/player/<mobName>.png -> whether that file actually
    // exists, cached so we're not hitting the resource manager every frame.
    // Cleared on resource reload (F3+T / resource pack switch) so newly
    // added mob skin files get picked up without a restart.
    private static final Map<String, ResourceLocation> PARTNER_SKIN_CACHE = new HashMap<>();

    /**
     * Set by GirlRenderer immediately before each of its two render passes
     * (see GirlRenderer#render). GeckoLib only binds one texture per
     * render() call, so drawing the girl body and the steve partner rig
     * with two different textures requires two full passes, each flipping
     * this flag before calling super.render().
     */
    private boolean renderingPartnerPass = false;

    public void setRenderingPartnerPass(boolean renderingPartnerPass) {
        this.renderingPartnerPass = renderingPartnerPass;
    }

    @Override
    public ResourceLocation getModelLocation(GirlEntity entity) {
        return entity.isDressed() ? GEO_DRESSED : GEO_NUDE;
    }

    @Override
    public ResourceLocation getTextureLocation(GirlEntity entity) {
        if (!renderingPartnerPass) return TEX_GIRL;
        return resolvePartnerTexture(entity.getPartnerSkinKey());
    }

    /**
     * Picks textures/player/<key>.png if it exists (e.g. key "zombie" ->
     * a zombie-skinned partner rig during a zombie-triggered downed
     * animation — see GirlEntity#applyMobIdentity), otherwise falls back
     * to the default player/steve.png. key is "" whenever no mob identity
     * is currently applied (normal player-sync poses).
     */
    private static ResourceLocation resolvePartnerTexture(String key) {
        if (key == null || key.isEmpty()) return TEX_PLAYER;
        return PARTNER_SKIN_CACHE.computeIfAbsent(key, k -> {
            ResourceLocation candidate = new ResourceLocation("girlmod", "textures/player/" + k + ".png");
            boolean exists = Minecraft.getInstance().getResourceManager().hasResource(candidate);
            return exists ? candidate : TEX_PLAYER;
        });
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GirlEntity entity) { return ANIM; }
}
