package software.bernie.geckolib3.molang;

import software.bernie.shadowed.eliotlash.mclib.math.Variable;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class MolangRegistrar {
   public static void registerVars(MolangParser parser) {
      parser.register(new Variable("query.anim_time", (double)0.0F));
      parser.register(new Variable("query.actor_count", (double)0.0F));
      parser.register(new Variable("query.health", (double)0.0F));
      parser.register(new Variable("query.max_health", (double)0.0F));
      parser.register(new Variable("query.distance_from_camera", (double)0.0F));
      parser.register(new Variable("query.yaw_speed", (double)0.0F));
      parser.register(new Variable("query.is_in_water_or_rain", (double)0.0F));
      parser.register(new Variable("query.is_in_water", (double)0.0F));
      parser.register(new Variable("query.is_on_ground", (double)0.0F));
      parser.register(new Variable("query.time_of_day", (double)0.0F));
      parser.register(new Variable("query.is_on_fire", (double)0.0F));
      parser.register(new Variable("query.ground_speed", (double)0.0F));
   }
}
