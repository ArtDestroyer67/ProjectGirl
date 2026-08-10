package com.girlmod.client.model;

import com.girlmod.config.SkinConfig;
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
 * getModelLocation()/getTextureLocation() resolve through SkinConfig based
 * on entity.getSkinId() — GeckoLib re-queries both every render, so
 * picking a different skin (via the GUI "Skin" button / PacketSetSkin)
 * swaps her actual 3D model AND texture live, no reload/relog needed.
 * getModelLocation() also still checks entity.isDressed() the same as
 * before — dressed/nude is a separate, orthogonal toggle from which skin
 * (SkinConfig entry) supplies the actual geo/texture pair for either state.
 *
 * The bundled "default" skin's assets were originally copied from
 * fapcraft's Ellie as a starting point. Add more skins by adding their
 * geo/texture files under assets/girlmod/ (or a resource pack) and a
 * matching entry in config/girlmod/skins.json — see SkinConfig.
 */
@OnlyIn(Dist.CLIENT)
public class GirlModel extends AnimatedGeoModel<GirlEntity> {

    private static final ResourceLocation ANIM =
        new ResourceLocation("girlmod", "animations/girl/girl.animation.json");

    // Separate sheet for the embedded "steve" partner rig, so it never
    // shares UV space with the girl atlas. Lives in its own folder so it
    // can later be swapped for the real local-player skin if desired.
    private static final ResourceLocation TEX_PLAYER =
        new ResourceLocation("girlmod", "textures/player/steve.png");

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
        SkinConfig.SkinDefinition skin = SkinConfig.get(entity.getSkinId());
        String path = entity.isDressed() ? skin.geoDressed : skin.geoNude;
        return resourceLocationOf(path);
    }

    @Override
    public ResourceLocation getTextureLocation(GirlEntity entity) {
        if (!renderingPartnerPass) {
            return resourceLocationOf(SkinConfig.get(entity.getSkinId()).texture);
        }
        return resolvePartnerTexture(entity.getPartnerSkinKey());
    }

    /** Namespace-optional path -> ResourceLocation, same convention as SoundMapper's mappings: no colon = assumed under "girlmod". */
    private static ResourceLocation resourceLocationOf(String path) {
        return path.indexOf(':') >= 0 ? new ResourceLocation(path) : new ResourceLocation("girlmod", path);
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
