package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongToDoubleFunction<E extends Throwable> {
   FailableLongToDoubleFunction NOP = (t) -> (double)0.0F;

   static <E extends Throwable> FailableLongToDoubleFunction<E> nop() {
      return NOP;
   }

   double applyAsDouble(long var1) throws E;
}
