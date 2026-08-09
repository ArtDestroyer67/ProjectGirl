package software.bernie.shadowed.eliotlash.mclib.math;

public class Negate implements IValue {
   public IValue value;

   public Negate(IValue value) {
      this.value = value;
   }

   public double get() {
      return this.value.get() == (double)0.0F ? (double)1.0F : (double)0.0F;
   }

   public String toString() {
      return "!" + this.value.toString();
   }
}
