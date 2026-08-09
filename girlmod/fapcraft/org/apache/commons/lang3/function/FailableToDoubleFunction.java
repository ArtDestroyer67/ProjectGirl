package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToDoubleFunction<T, E extends Throwable> {
   FailableToDoubleFunction NOP = (t) -> (double)0.0F;

   static <T, E extends Throwable> FailableToDoubleFunction<T, E> nop() {
      return NOP;
   }

   double applyAsDouble(T var1) throws E;
}
