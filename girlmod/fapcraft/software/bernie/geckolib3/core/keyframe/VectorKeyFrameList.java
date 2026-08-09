package software.bernie.geckolib3.core.keyframe;

import java.util.ArrayList;
import java.util.List;

public class VectorKeyFrameList<T extends KeyFrame> {
   public List<T> xKeyFrames;
   public List<T> yKeyFrames;
   public List<T> zKeyFrames;

   public VectorKeyFrameList(List<T> XKeyFrames, List<T> YKeyFrames, List<T> ZKeyFrames) {
      this.xKeyFrames = XKeyFrames;
      this.yKeyFrames = YKeyFrames;
      this.zKeyFrames = ZKeyFrames;
   }

   public VectorKeyFrameList() {
      this.xKeyFrames = new ArrayList();
      this.yKeyFrames = new ArrayList();
      this.zKeyFrames = new ArrayList();
   }

   public double getLastKeyframeTime() {
      double xTime = (double)0.0F;

      for(T frame : this.xKeyFrames) {
         xTime += frame.getLength();
      }

      double yTime = (double)0.0F;

      for(T frame : this.yKeyFrames) {
         yTime += frame.getLength();
      }

      double zTime = (double)0.0F;

      for(T frame : this.zKeyFrames) {
         zTime += frame.getLength();
      }

      return Math.max(xTime, Math.max(yTime, zTime));
   }
}
