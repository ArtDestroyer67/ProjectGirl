package software.bernie.geckolib3.geo.render;

import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Vector3f;
import org.apache.commons.lang3.ArrayUtils;
import software.bernie.geckolib3.geo.raw.pojo.Bone;
import software.bernie.geckolib3.geo.raw.pojo.Cube;
import software.bernie.geckolib3.geo.raw.pojo.ModelProperties;
import software.bernie.geckolib3.geo.raw.tree.RawBoneGroup;
import software.bernie.geckolib3.geo.raw.tree.RawGeometryTree;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.util.VectorUtils;

public class GeoBuilder implements IGeoBuilder {
   private static Map<String, IGeoBuilder> moddedGeoBuilders = new HashMap();
   private static IGeoBuilder defaultBuilder = new GeoBuilder();

   public static void registerGeoBuilder(String modID, IGeoBuilder builder) {
      moddedGeoBuilders.put(modID, builder);
   }

   public static IGeoBuilder getGeoBuilder(String modID) {
      IGeoBuilder builder = (IGeoBuilder)moddedGeoBuilders.get(modID);
      return builder == null ? defaultBuilder : builder;
   }

   public GeoModel constructGeoModel(RawGeometryTree geometryTree) {
      GeoModel model = new GeoModel();
      model.properties = geometryTree.properties;

      for(RawBoneGroup rawBone : geometryTree.topLevelBones.values()) {
         model.topLevelBones.add(this.constructBone(rawBone, geometryTree.properties, (GeoBone)null));
      }

      return model;
   }

   public GeoBone constructBone(RawBoneGroup bone, ModelProperties properties, GeoBone parent) {
      GeoBone geoBone = new GeoBone();
      Bone rawBone = bone.selfBone;
      Vector3f rotation = VectorUtils.convertDoubleToFloat(VectorUtils.fromArray(rawBone.getRotation()));
      Vector3f pivot = VectorUtils.convertDoubleToFloat(VectorUtils.fromArray(rawBone.getPivot()));
      rotation.x *= -1.0F;
      rotation.y *= -1.0F;
      geoBone.mirror = rawBone.getMirror();
      geoBone.dontRender = rawBone.getNeverRender();
      geoBone.reset = rawBone.getReset();
      geoBone.inflate = rawBone.getInflate();
      geoBone.parent = parent;
      geoBone.setModelRendererName(rawBone.getName());
      geoBone.setRotationX((float)Math.toRadians((double)rotation.getX()));
      geoBone.setRotationY((float)Math.toRadians((double)rotation.getY()));
      geoBone.setRotationZ((float)Math.toRadians((double)rotation.getZ()));
      geoBone.rotationPointX = -pivot.getX();
      geoBone.rotationPointY = pivot.getY();
      geoBone.rotationPointZ = pivot.getZ();
      if (!ArrayUtils.isEmpty((Object[])rawBone.getCubes())) {
         for(Cube cube : rawBone.getCubes()) {
            geoBone.childCubes.add(GeoCube.createFromPojoCube(cube, properties, geoBone.inflate == null ? null : geoBone.inflate / (double)16.0F, geoBone.mirror));
         }
      }

      for(RawBoneGroup child : bone.children.values()) {
         geoBone.childBones.add(this.constructBone(child, properties, geoBone));
      }

      return geoBone;
   }
}
