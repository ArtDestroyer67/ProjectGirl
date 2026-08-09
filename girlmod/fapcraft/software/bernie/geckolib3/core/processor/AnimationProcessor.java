package software.bernie.geckolib3.core.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.keyframe.AnimationPoint;
import software.bernie.geckolib3.core.keyframe.BoneAnimationQueue;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.snapshot.BoneSnapshot;
import software.bernie.geckolib3.core.snapshot.DirtyTracker;
import software.bernie.geckolib3.core.util.MathUtil;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class AnimationProcessor<T extends IAnimatable> {
   public boolean reloadAnimations = false;
   private List<IBone> modelRendererList = new ArrayList();
   private double lastTickValue = (double)-1.0F;
   private Set<Integer> animatedEntities = new HashSet();
   private final IAnimatableModel animatedModel;

   public AnimationProcessor(IAnimatableModel animatedModel) {
      this.animatedModel = animatedModel;
   }

   public void tickAnimation(IAnimatable entity, Integer uniqueID, double seekTime, AnimationEvent event, MolangParser parser, boolean crashWhenCantFindBone) {
      if (seekTime != this.lastTickValue) {
         this.animatedEntities.clear();
      } else if (this.animatedEntities.contains(uniqueID)) {
         return;
      }

      this.lastTickValue = seekTime;
      this.animatedEntities.add(uniqueID);
      AnimationData manager = entity.getFactory().getOrCreateAnimationData(uniqueID);
      HashMap<String, DirtyTracker> modelTracker = this.createNewDirtyTracker();
      this.updateBoneSnapshots(manager.getBoneSnapshotCollection());
      HashMap<String, Pair<IBone, BoneSnapshot>> boneSnapshots = manager.getBoneSnapshotCollection();

      for(AnimationController<T> controller : manager.getAnimationControllers().values()) {
         if (this.reloadAnimations) {
            controller.markNeedsReload();
            controller.getBoneAnimationQueues().clear();
         }

         controller.isJustStarting = manager.isFirstTick;
         event.setController(controller);
         controller.process(seekTime, event, this.modelRendererList, boneSnapshots, parser, crashWhenCantFindBone);

         for(BoneAnimationQueue boneAnimation : controller.getBoneAnimationQueues().values()) {
            IBone bone = boneAnimation.bone;
            BoneSnapshot snapshot = (BoneSnapshot)((Pair)boneSnapshots.get(bone.getName())).getRight();
            BoneSnapshot initialSnapshot = bone.getInitialSnapshot();
            AnimationPoint rXPoint = (AnimationPoint)boneAnimation.rotationXQueue.poll();
            AnimationPoint rYPoint = (AnimationPoint)boneAnimation.rotationYQueue.poll();
            AnimationPoint rZPoint = (AnimationPoint)boneAnimation.rotationZQueue.poll();
            AnimationPoint pXPoint = (AnimationPoint)boneAnimation.positionXQueue.poll();
            AnimationPoint pYPoint = (AnimationPoint)boneAnimation.positionYQueue.poll();
            AnimationPoint pZPoint = (AnimationPoint)boneAnimation.positionZQueue.poll();
            AnimationPoint sXPoint = (AnimationPoint)boneAnimation.scaleXQueue.poll();
            AnimationPoint sYPoint = (AnimationPoint)boneAnimation.scaleYQueue.poll();
            AnimationPoint sZPoint = (AnimationPoint)boneAnimation.scaleZQueue.poll();
            DirtyTracker dirtyTracker = (DirtyTracker)modelTracker.get(bone.getName());
            if (dirtyTracker != null) {
               if (rXPoint != null && rYPoint != null && rZPoint != null) {
                  bone.setRotationX(MathUtil.lerpValues(rXPoint, controller.easingType, controller.customEasingMethod) + initialSnapshot.rotationValueX);
                  bone.setRotationY(MathUtil.lerpValues(rYPoint, controller.easingType, controller.customEasingMethod) + initialSnapshot.rotationValueY);
                  bone.setRotationZ(MathUtil.lerpValues(rZPoint, controller.easingType, controller.customEasingMethod) + initialSnapshot.rotationValueZ);
                  snapshot.rotationValueX = bone.getRotationX();
                  snapshot.rotationValueY = bone.getRotationY();
                  snapshot.rotationValueZ = bone.getRotationZ();
                  snapshot.isCurrentlyRunningRotationAnimation = true;
                  dirtyTracker.hasRotationChanged = true;
               }

               if (pXPoint != null && pYPoint != null && pZPoint != null) {
                  bone.setPositionX(MathUtil.lerpValues(pXPoint, controller.easingType, controller.customEasingMethod));
                  bone.setPositionY(MathUtil.lerpValues(pYPoint, controller.easingType, controller.customEasingMethod));
                  bone.setPositionZ(MathUtil.lerpValues(pZPoint, controller.easingType, controller.customEasingMethod));
                  snapshot.positionOffsetX = bone.getPositionX();
                  snapshot.positionOffsetY = bone.getPositionY();
                  snapshot.positionOffsetZ = bone.getPositionZ();
                  snapshot.isCurrentlyRunningPositionAnimation = true;
                  dirtyTracker.hasPositionChanged = true;
               }

               if (sXPoint != null && sYPoint != null && sZPoint != null) {
                  bone.setScaleX(MathUtil.lerpValues(sXPoint, controller.easingType, controller.customEasingMethod));
                  bone.setScaleY(MathUtil.lerpValues(sYPoint, controller.easingType, controller.customEasingMethod));
                  bone.setScaleZ(MathUtil.lerpValues(sZPoint, controller.easingType, controller.customEasingMethod));
                  snapshot.scaleValueX = bone.getScaleX();
                  snapshot.scaleValueY = bone.getScaleY();
                  snapshot.scaleValueZ = bone.getScaleZ();
                  snapshot.isCurrentlyRunningScaleAnimation = true;
                  dirtyTracker.hasScaleChanged = true;
               }
            }
         }
      }

      this.reloadAnimations = false;
      double resetTickLength = manager.getResetSpeed();

      for(Map.Entry<String, DirtyTracker> tracker : modelTracker.entrySet()) {
         IBone model = ((DirtyTracker)tracker.getValue()).model;
         BoneSnapshot initialSnapshot = model.getInitialSnapshot();
         BoneSnapshot saveSnapshot = (BoneSnapshot)((Pair)boneSnapshots.get(tracker.getKey())).getRight();
         if (saveSnapshot == null) {
            if (crashWhenCantFindBone) {
               throw new RuntimeException("Could not find save snapshot for bone: " + ((DirtyTracker)tracker.getValue()).model.getName() + ". Please don't add bones that are used in an animation at runtime.");
            }
         } else {
            if (!((DirtyTracker)tracker.getValue()).hasRotationChanged) {
               if (saveSnapshot.isCurrentlyRunningRotationAnimation) {
                  saveSnapshot.mostRecentResetRotationTick = (float)seekTime;
                  saveSnapshot.isCurrentlyRunningRotationAnimation = false;
               }

               double percentageReset = Math.min((seekTime - (double)saveSnapshot.mostRecentResetRotationTick) / resetTickLength, (double)1.0F);
               model.setRotationX(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.rotationValueX, (double)initialSnapshot.rotationValueX));
               model.setRotationY(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.rotationValueY, (double)initialSnapshot.rotationValueY));
               model.setRotationZ(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.rotationValueZ, (double)initialSnapshot.rotationValueZ));
               if (percentageReset >= (double)1.0F) {
                  saveSnapshot.rotationValueX = model.getRotationX();
                  saveSnapshot.rotationValueY = model.getRotationY();
                  saveSnapshot.rotationValueZ = model.getRotationZ();
               }
            }

            if (!((DirtyTracker)tracker.getValue()).hasPositionChanged) {
               if (saveSnapshot.isCurrentlyRunningPositionAnimation) {
                  saveSnapshot.mostRecentResetPositionTick = (float)seekTime;
                  saveSnapshot.isCurrentlyRunningPositionAnimation = false;
               }

               double percentageReset = Math.min((seekTime - (double)saveSnapshot.mostRecentResetPositionTick) / resetTickLength, (double)1.0F);
               model.setPositionX(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.positionOffsetX, (double)initialSnapshot.positionOffsetX));
               model.setPositionY(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.positionOffsetY, (double)initialSnapshot.positionOffsetY));
               model.setPositionZ(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.positionOffsetZ, (double)initialSnapshot.positionOffsetZ));
               if (percentageReset >= (double)1.0F) {
                  saveSnapshot.positionOffsetX = model.getPositionX();
                  saveSnapshot.positionOffsetY = model.getPositionY();
                  saveSnapshot.positionOffsetZ = model.getPositionZ();
               }
            }

            if (!((DirtyTracker)tracker.getValue()).hasScaleChanged) {
               if (saveSnapshot.isCurrentlyRunningScaleAnimation) {
                  saveSnapshot.mostRecentResetScaleTick = (float)seekTime;
                  saveSnapshot.isCurrentlyRunningScaleAnimation = false;
               }

               double percentageReset = Math.min((seekTime - (double)saveSnapshot.mostRecentResetScaleTick) / resetTickLength, (double)1.0F);
               model.setScaleX(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.scaleValueX, (double)initialSnapshot.scaleValueX));
               model.setScaleY(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.scaleValueY, (double)initialSnapshot.scaleValueY));
               model.setScaleZ(MathUtil.lerpValues(percentageReset, (double)saveSnapshot.scaleValueZ, (double)initialSnapshot.scaleValueZ));
               if (percentageReset >= (double)1.0F) {
                  saveSnapshot.scaleValueX = model.getScaleX();
                  saveSnapshot.scaleValueY = model.getScaleY();
                  saveSnapshot.scaleValueZ = model.getScaleZ();
               }
            }
         }
      }

      manager.isFirstTick = false;
   }

   private HashMap<String, DirtyTracker> createNewDirtyTracker() {
      HashMap<String, DirtyTracker> tracker = new HashMap();

      for(IBone bone : this.modelRendererList) {
         tracker.put(bone.getName(), new DirtyTracker(false, false, false, bone));
      }

      return tracker;
   }

   private void updateBoneSnapshots(HashMap<String, Pair<IBone, BoneSnapshot>> boneSnapshotCollection) {
      for(IBone bone : this.modelRendererList) {
         if (!boneSnapshotCollection.containsKey(bone.getName())) {
            boneSnapshotCollection.put(bone.getName(), Pair.of(bone, new BoneSnapshot(bone.getInitialSnapshot())));
         }
      }

   }

   public IBone getBone(String boneName) {
      return (IBone)this.modelRendererList.stream().filter((x) -> x.getName().equals(boneName)).findFirst().orElse((Object)null);
   }

   public void registerModelRenderer(IBone modelRenderer) {
      modelRenderer.saveInitialSnapshot();
      this.modelRendererList.add(modelRenderer);
   }

   public void clearModelRendererList() {
      this.modelRendererList.clear();
   }

   public List<IBone> getModelRendererList() {
      return this.modelRendererList;
   }

   public void preAnimationSetup(IAnimatable animatable, double seekTime) {
      this.animatedModel.setMolangQueries(animatable, seekTime);
   }
}
