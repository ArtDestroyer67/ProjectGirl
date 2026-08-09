package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntToDoubleFunction<E extends Throwable> {
   FailableIntToDoubleFunction NOP = (t) -> (double)0.0F;

   static <E extends Throwable> FailableIntToDoubleFunction<E> nop() {
      return NOP;
   }

   double applyAsDouble(int var1) throws E;
}
