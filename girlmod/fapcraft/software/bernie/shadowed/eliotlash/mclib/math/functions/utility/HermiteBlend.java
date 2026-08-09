package software.bernie.shadowed.eliotlash.mclib.math.functions.utility;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.mclib.math.functions.Function;

public class HermiteBlend extends Function {
   public java.util.Random random = new java.util.Random();

   public HermiteBlend(IValue[] values, String name) throws Exception {
      super(values, name);
   }

   public int getRequiredArguments() {
      return 1;
   }

   public double get() {
      double min = Math.ceil(this.getArg(0));
      return Math.floor((double)3.0F * Math.pow(min, (double)2.0F) - (double)2.0F * Math.pow(min, (double)3.0F));
   }
}
