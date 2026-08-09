package software.bernie.geckolib3.core.builder;

import java.util.Objects;

public class RawAnimation {
   public String animationName;
   public ILoopType loopType;

   public RawAnimation(String animationName, ILoopType loop) {
      this.animationName = animationName;
      this.loopType = loop;
   }

   /** @deprecated */
   @Deprecated
   public RawAnimation(String animationName, boolean loop) {
      this(animationName, loop ? ILoopType.EDefaultLoopTypes.LOOP : ILoopType.EDefaultLoopTypes.PLAY_ONCE);
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof RawAnimation)) {
         return false;
      } else {
         RawAnimation animation = (RawAnimation)obj;
         return animation.loopType == this.loopType && animation.animationName.equals(this.animationName);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.animationName, this.loopType});
   }
}
