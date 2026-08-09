package software.bernie.geckolib3.core.builder;

import java.util.ArrayList;
import java.util.List;
import software.bernie.geckolib3.core.keyframe.BoneAnimation;
import software.bernie.geckolib3.core.keyframe.EventKeyFrame;
import software.bernie.geckolib3.core.keyframe.ParticleEventKeyFrame;

public class Animation {
   public String animationName;
   public Double animationLength;
   public ILoopType loop;
   public List<BoneAnimation> boneAnimations;
   public List<EventKeyFrame<String>> soundKeyFrames;
   public List<ParticleEventKeyFrame> particleKeyFrames;
   public List<EventKeyFrame<String>> customInstructionKeyframes;

   public Animation() {
      this.loop = ILoopType.EDefaultLoopTypes.LOOP;
      this.soundKeyFrames = new ArrayList();
      this.particleKeyFrames = new ArrayList();
      this.customInstructionKeyframes = new ArrayList();
   }
}
