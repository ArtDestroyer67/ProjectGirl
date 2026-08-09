package software.bernie.geckolib3.core.builder;

public interface ILoopType {
   boolean isRepeatingAfterEnd();

   public static enum EDefaultLoopTypes implements ILoopType {
      LOOP(true),
      PLAY_ONCE,
      HOLD_ON_LAST_FRAME;

      private final boolean looping;

      private EDefaultLoopTypes(boolean looping) {
         this.looping = looping;
      }

      private EDefaultLoopTypes() {
         this(false);
      }

      public boolean isRepeatingAfterEnd() {
         return this.looping;
      }
   }
}
