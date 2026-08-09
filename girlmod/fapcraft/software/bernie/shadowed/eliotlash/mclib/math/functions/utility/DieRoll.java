package software.bernie.shadowed.eliotlash.mclib.math.functions.utility;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.mclib.math.functions.Function;

public class DieRoll extends Function {
   public java.util.Random random = new java.util.Random();

   public DieRoll(IValue[] values, String name) throws Exception {
      super(values, name);
   }

   public int getRequiredArguments() {
      return 3;
   }

   public double get() {
      double i = (double)0.0F;

      double total;
      for(total = (double)0.0F; i < this.getArg(0); total += Math.random() * (this.getArg(2) - this.getArg(2))) {
      }

      return total;
   }
}
