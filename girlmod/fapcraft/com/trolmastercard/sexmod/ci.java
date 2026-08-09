package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class ci extends cv {
   final float g = 60.0F;
   Minecraft f = Minecraft.func_71410_x();

   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/goblin/goblin.geo.json"), new ResourceLocation("sexmod", "geo/goblin/armored.geo.json")};
   }

   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/goblin/goblin.png");
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/goblin/goblin.animation.json");
   }

   protected boolean f(em var1) {
      try {
         if (!(var1 instanceof e3)) {
            return super.f(var1);
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      e3 var2 = (e3)var1;
      UUID var3 = var2.ae();
      if (var3 == null) {
         var3 = var2.e();
      }

      try {
         if (var3 == null) {
            return true;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      World var4 = var2.field_70170_p;
      AbstractClientPlayer var5 = (AbstractClientPlayer)var4.func_152378_a(var3);

      try {
         if (var5 == null) {
            return true;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      return "default".equals(var5.func_175154_l());
   }

   public void a(em param1, Integer param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   void a(AnimationProcessor param1, em param2) {
      // $FF: Couldn't be decompiled
   }

   void b(AnimationProcessor param1, em param2) {
      // $FF: Couldn't be decompiled
   }

   void a(AnimationProcessor var1, ai var2, em var3) {
      UUID var4 = var2.e();

      try {
         if (var4 == null) {
            var3.ae();
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      try {
         if (var4 == null) {
            return;
         }
      } catch (RuntimeException var14) {
         throw a(var14);
      }

      EntityPlayer var5 = var3.field_70170_p.func_152378_a(var4);

      try {
         if (var5 == null) {
            return;
         }
      } catch (RuntimeException var13) {
         throw a(var13);
      }

      float var6 = b6.a(var5.field_184618_aE, var5.field_70721_aZ, this.f.func_184121_ak());
      float var7 = var5.field_184619_aG;
      float var8 = (float)Math.sin((double)var7);
      IBone var9 = var1.getBone("LeftLeg");
      IBone var10 = var1.getBone("RightLeg");
      float var11 = gc.c(60.0F * var8 * var6);
      var9.setRotationX(var11);
      var10.setRotationX(-var11);
   }

   void a(em param1, IBone param2) {
      // $FF: Couldn't be decompiled
   }

   void a(em var1, IBone var2, IBone var3) {
      EntityPlayer var4 = var1.field_70170_p.func_72890_a(var1, (double)15.0F);

      try {
         if (var4 == null) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      Vec3d var5 = var4.func_174791_d();
      Vec3d var6 = var1.func_174791_d();
      Vec3d var7 = var5.func_178788_d(var6);
      float var8 = (float)(-(Math.atan2(var7.field_72449_c, var7.field_72450_a) * (180D / Math.PI))) + 90.0F;
      float var9 = be.b((float)((double)var4.func_70047_e() + var5.field_72448_b - ((double)var1.func_70047_e() + var6.field_72448_b)), -0.75F, 0.75F);
      var2.setRotationY(gc.c(var8));
      var3.setRotationX(var9);
   }

   void a(IBone var1, AnimationProcessor var2, em var3) {
      try {
         if (var3.h()) {
            var1.setHidden(true);
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      var1.setHidden(false);
      var2.getBone("steve").setHidden(true);
   }

   void a(IBone var1, AnimationProcessor var2, em var3, ai var4) {
      label38: {
         try {
            if (var3.h()) {
               var1.setHidden(true);
               break label38;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         IBone var10000;
         boolean var10001;
         label29: {
            try {
               var10000 = var1;
               if (var4.a() < 15) {
                  var10001 = true;
                  break label29;
               }
            } catch (RuntimeException var6) {
               throw a(var6);
            }

            var10001 = false;
         }

         var10000.setHidden(var10001);
      }

      try {
         if (!var3.h()) {
            var2.getBone("steve").setHidden(true);
         }

      } catch (RuntimeException var5) {
         throw a(var5);
      }
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   public String[] f() {
      return new String[]{"armorBoobL", "armorBoobR"};
   }

   public String[] a() {
      return new String[]{"nippleL", "nippleR"};
   }

   public String[] h() {
      return new String[]{"armorCheekR", "armorCheekL", "armorLegL", "armorLegR", "armorShinL", "armorShinR", "armorTorso"};
   }

   public String[] e() {
      return new String[]{"fuckhole", "vagina", "meatCheekR", "meatCheekL", "meatLegL", "meatLegR", "meatShinL", "meatShinR"};
   }

   public String[] b() {
      return new String[]{"armorFootL", "armorFootR"};
   }

   public String[] d() {
      return new String[]{"meatFootL", "meatFootR"};
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
