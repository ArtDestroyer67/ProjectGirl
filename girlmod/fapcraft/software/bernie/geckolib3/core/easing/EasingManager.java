package software.bernie.geckolib3.core.easing;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import software.bernie.geckolib3.core.util.Memoizer;

public class EasingManager {
   static Function<Double, Double> quart = poly((double)4.0F);
   static Function<Double, Double> quint = poly((double)5.0F);
   static Function<EasingFunctionArgs, Function<Double, Double>> getEasingFunction = Memoizer.<EasingFunctionArgs, Function<Double, Double>>memoize(EasingManager::getEasingFuncImpl);

   public static double ease(double number, EasingType easingType, List<Double> easingArgs) {
      Double firstArg = easingArgs != null && easingArgs.size() >= 1 ? (Double)easingArgs.get(0) : null;
      return (Double)((Function)getEasingFunction.apply(new EasingFunctionArgs(easingType, firstArg))).apply(number);
   }

   static Function<Double, Double> getEasingFuncImpl(EasingFunctionArgs args) {
      switch (args.easingType) {
         case Linear:
         default:
            return in(EasingManager::linear);
         case Step:
            return in(step(args.arg0));
         case EaseInSine:
            return in(EasingManager::sin);
         case EaseOutSine:
            return out(EasingManager::sin);
         case EaseInOutSine:
            return inOut(EasingManager::sin);
         case EaseInQuad:
            return in(EasingManager::quad);
         case EaseOutQuad:
            return out(EasingManager::quad);
         case EaseInOutQuad:
            return inOut(EasingManager::quad);
         case EaseInCubic:
            return in(EasingManager::cubic);
         case EaseOutCubic:
            return out(EasingManager::cubic);
         case EaseInOutCubic:
            return inOut(EasingManager::cubic);
         case EaseInExpo:
            return in(EasingManager::exp);
         case EaseOutExpo:
            return out(EasingManager::exp);
         case EaseInOutExpo:
            return inOut(EasingManager::exp);
         case EaseInCirc:
            return in(EasingManager::circle);
         case EaseOutCirc:
            return out(EasingManager::circle);
         case EaseInOutCirc:
            return inOut(EasingManager::circle);
         case EaseInQuart:
            return in(quart);
         case EaseOutQuart:
            return out(quart);
         case EaseInOutQuart:
            return inOut(quart);
         case EaseInQuint:
            return in(quint);
         case EaseOutQuint:
            return out(quint);
         case EaseInOutQuint:
            return inOut(quint);
         case EaseInBack:
            return in(back(args.arg0));
         case EaseOutBack:
            return out(back(args.arg0));
         case EaseInOutBack:
            return inOut(back(args.arg0));
         case EaseInElastic:
            return in(elastic(args.arg0));
         case EaseOutElastic:
            return out(elastic(args.arg0));
         case EaseInOutElastic:
            return inOut(elastic(args.arg0));
         case EaseInBounce:
            return in(bounce(args.arg0));
         case EaseOutBounce:
            return out(bounce(args.arg0));
         case EaseInOutBounce:
            return inOut(bounce(args.arg0));
      }
   }

   static Function<Double, Double> in(Function<Double, Double> easing) {
      return easing;
   }

   static Function<Double, Double> out(Function<Double, Double> easing) {
      return (t) -> (double)1.0F - (Double)easing.apply((double)1.0F - t);
   }

   static Function<Double, Double> inOut(Function<Double, Double> easing) {
      return (t) -> t < (double)0.5F ? (Double)easing.apply(t * (double)2.0F) / (double)2.0F : (double)1.0F - (Double)easing.apply(((double)1.0F - t) * (double)2.0F) / (double)2.0F;
   }

   static Function<Double, Double> step0() {
      return (n) -> n > (double)0.0F ? (double)1.0F : (double)0.0F;
   }

   static Function<Double, Double> step1() {
      return (n) -> n >= (double)1.0F ? (double)1.0F : (double)0.0F;
   }

   static double linear(double t) {
      return t;
   }

   static double quad(double t) {
      return t * t;
   }

   static double cubic(double t) {
      return t * t * t;
   }

   static Function<Double, Double> poly(double n) {
      return (t) -> Math.pow(t, n);
   }

   static double sin(double t) {
      return (double)1.0F - Math.cos((double)((float)(t * Math.PI / (double)2.0F)));
   }

   static double circle(double t) {
      return (double)1.0F - Math.sqrt((double)1.0F - t * t);
   }

   static double exp(double t) {
      return Math.pow((double)2.0F, (double)10.0F * (t - (double)1.0F));
   }

   static Function<Double, Double> elastic(Double bounciness) {
      double p = (bounciness == null ? (double)1.0F : bounciness) * Math.PI;
      return (t) -> (double)1.0F - Math.pow(Math.cos((double)((float)(t * Math.PI / (double)2.0F))), (double)3.0F) * Math.cos((double)((float)(t * p)));
   }

   static Function<Double, Double> back(Double s) {
      double p = s == null ? 1.70158 : s * 1.70158;
      return (t) -> t * t * ((p + (double)1.0F) * t - p);
   }

   public static Function<Double, Double> bounce(Double s) {
      double k = s == null ? (double)0.5F : s;
      Function<Double, Double> q = (x) -> (double)7.5625F * x * x;
      Function<Double, Double> w = (x) -> (double)30.25F * k * Math.pow(x - 0.5454545454545454, (double)2.0F) + (double)1.0F - k;
      Function<Double, Double> r = (x) -> (double)121.0F * k * k * Math.pow(x - 0.8181818181818182, (double)2.0F) + (double)1.0F - k * k;
      Function<Double, Double> t = (x) -> (double)484.0F * k * k * k * Math.pow(x - 0.9545454545454546, (double)2.0F) + (double)1.0F - k * k * k;
      return (x) -> min((Double)q.apply(x), (Double)w.apply(x), (Double)r.apply(x), (Double)t.apply(x));
   }

   static Function<Double, Double> step(Double stepArg) {
      int steps = stepArg != null ? stepArg.intValue() : 2;
      double[] intervals = stepRange(steps);
      return (t) -> intervals[findIntervalBorderIndex(t, intervals, false)];
   }

   static double min(double a, double b, double c, double d) {
      return Math.min(Math.min(a, b), Math.min(c, d));
   }

   static int findIntervalBorderIndex(double point, double[] intervals, boolean useRightBorder) {
      if (point < intervals[0]) {
         return 0;
      } else if (point > intervals[intervals.length - 1]) {
         return intervals.length - 1;
      } else {
         int indexOfNumberToCompare = 0;
         int leftBorderIndex = 0;
         int rightBorderIndex = intervals.length - 1;

         while(rightBorderIndex - leftBorderIndex != 1) {
            indexOfNumberToCompare = leftBorderIndex + (rightBorderIndex - leftBorderIndex) / 2;
            if (point >= intervals[indexOfNumberToCompare]) {
               leftBorderIndex = indexOfNumberToCompare;
            } else {
               rightBorderIndex = indexOfNumberToCompare;
            }
         }

         return useRightBorder ? rightBorderIndex : leftBorderIndex;
      }
   }

   static double[] stepRange(int steps) {
      double stop = (double)1.0F;
      if (steps < 2) {
         throw new IllegalArgumentException("steps must be > 2, got:" + steps);
      } else {
         double stepLength = (double)1.0F / (double)steps;
         AtomicInteger i = new AtomicInteger();
         return DoubleStream.generate(() -> (double)i.getAndIncrement() * stepLength).limit((long)steps).toArray();
      }
   }

   static class EasingFunctionArgs {
      public final EasingType easingType;
      public final Double arg0;

      public EasingFunctionArgs(EasingType easingType, Double arg0) {
         this.easingType = easingType;
         this.arg0 = arg0;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            EasingFunctionArgs that = (EasingFunctionArgs)o;
            return this.easingType == that.easingType && Objects.equals(this.arg0, that.arg0);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.easingType, this.arg0});
      }
   }
}
