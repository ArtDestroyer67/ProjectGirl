package software.bernie.shadowed.eliotlash.mclib.math.functions.utility;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.mclib.math.functions.Function;

public class RandomInteger extends Function {
   public java.util.Random random = new java.util.Random();

   public RandomInteger(IValue[] values, String name) throws Exception {
      super(values, name);
   }

   public int getRequiredArguments() {
      return 2;
   }

   public double get() {
      double min = Math.ceil(this.getArg(0));
      double max = Math.floor(this.getArg(1));
      return Math.floor(Math.random() * (max - min) + min);
   }
}
