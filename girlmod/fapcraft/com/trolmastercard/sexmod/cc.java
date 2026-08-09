package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class cc extends Item implements IAnimatable {
   public static final cc r = new cc();
   public static final long c = 4000L;
   public static final long g = 1000L;
   public static final long j = 3000L;
   public static final float q = 0.1F;
   public static final float p = -0.01F;
   public static final float e = 0.0015F;
   public static final float k = 2.0F;
   public static final float h = 1.5F;
   public static final float d = 0.03F;
   public static final float s = 100.0F;
   public static final float l = 0.2F;
   public static final float o = 1.5F;
   public static final String b = "sexmod:galath_coin_activation_time";
   public static final String m = "sexmod:galath_coin_deactivation_time";
   public static final String n = "sexmod:galath_coin_de_summoning_animation_time";
   public static final String f = "Defeating a succubus makes her accept the victor as her master, granting him a coin to which her soul is bound. Using the coin summons her, offering services on demand. If her master uses the coin on her or goes too far, she returns to the coin";
   private final AnimationFactory i = new AnimationFactory(this);
   AnimationController<cc> a;

   public cc() {
      this.field_77777_bU = 1;
   }

   public static void a() {
      r.setRegistryName("sexmod", "galath_coin");
      r.func_77655_b("galath_coin");
      MinecraftForge.EVENT_BUS.register(cc.class);
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Item> var0) {
      var0.getRegistry().register(r);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void a(ModelRegistryEvent var0) {
      ModelLoader.setCustomModelResourceLocation(r, 0, new ModelResourceLocation("sexmod:galath_coin"));
      r.setTileEntityItemStackRenderer(new av());
   }

   public ActionResult<ItemStack> func_77659_a(World var1, EntityPlayer var2, EnumHand var3) {
      NBTTagCompound var4 = var2.getEntityData();
      ActionResult var5 = new ActionResult(EnumActionResult.FAIL, var2.func_184586_b(var3));

      try {
         if (var4.func_74763_f("sexmod:galath_coin_deactivation_time") != 0L) {
            return var5;
         }
      } catch (ConcurrentModificationException var8) {
         throw a(var8);
      }

      try {
         if (var4.func_74763_f("sexmod:galath_coin_activation_time") != 0L) {
            return var5;
         }
      } catch (ConcurrentModificationException var6) {
         throw a(var6);
      }

      try {
         if (!this.a(var1, var2)) {
            var1.func_184134_a(var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, com.trolmastercard.sexmod.c.MISC_BEEW[0], SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            return new ActionResult(EnumActionResult.SUCCESS, var2.func_184586_b(var3));
         }
      } catch (ConcurrentModificationException var7) {
         throw a(var7);
      }

      var1.func_184134_a(var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, com.trolmastercard.sexmod.c.MISC_WEOWEO[1], SoundCategory.PLAYERS, 1.0F, 1.0F, false);
      var4.func_74772_a("sexmod:galath_coin_activation_time", System.currentTimeMillis());
      return new ActionResult(EnumActionResult.SUCCESS, var2.func_184586_b(var3));
   }

   boolean a(World param1, EntityPlayer param2) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(PlayerInteractEvent.EntityInteract var1) {
      EntityPlayer var2 = var1.getEntityPlayer();
      ItemStack var3 = var2.func_184586_b(var1.getHand());

      try {
         if (!r.equals(var3.func_77973_b())) {
            return;
         }
      } catch (ConcurrentModificationException var8) {
         throw a(var8);
      }

      Entity var4 = var1.getTarget();

      try {
         if (!(var4 instanceof f_)) {
            return;
         }
      } catch (ConcurrentModificationException var7) {
         throw a(var7);
      }

      f_ var5 = (f_)var4;

      try {
         if (!var2.getPersistentID().equals(var5.O())) {
            return;
         }
      } catch (ConcurrentModificationException var6) {
         throw a(var6);
      }

      var2.field_70170_p.func_184134_a(var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, com.trolmastercard.sexmod.c.MISC_WEOWEO[0], SoundCategory.PLAYERS, 1.0F, 1.0F, false);
      var2.getEntityData().func_74772_a("sexmod:galath_coin_deactivation_time", System.currentTimeMillis());
      var1.setCanceled(true);
   }

   public void func_77663_a(ItemStack param1, World param2, Entity param3, int param4, boolean param5) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   void b(EntityPlayer param1, long param2, long param4) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   void a(EntityPlayer var1) {
      try {
         if (!Minecraft.func_71410_x().field_71439_g.getPersistentID().equals(var1.getPersistentID())) {
            return;
         }
      } catch (ConcurrentModificationException var2) {
         throw a(var2);
      }

      v.f = true;
   }

   @SideOnly(Side.CLIENT)
   void a(EntityPlayer param1, long param2, long param4) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(PlayerEvent.PlayerChangedDimensionEvent var1) {
      EntityPlayer var2 = var1.player;

      try {
         if (var2.field_70170_p.field_72995_K) {
            return;
         }
      } catch (ConcurrentModificationException var6) {
         throw a(var6);
      }

      UUID var3 = v.b(var2);
      em var4 = em.a(var3);

      try {
         if (var4 == null) {
            return;
         }
      } catch (ConcurrentModificationException var5) {
         throw a(var5);
      }

      v.a((f_)var4);
      ge.b.sendTo(new gf(false), (EntityPlayerMP)var2);
   }

   void b(EntityPlayer var1, NBTTagCompound var2, long var3, long var5) {
      try {
         if (var5 == 0L) {
            return;
         }
      } catch (ConcurrentModificationException var13) {
         throw a(var13);
      }

      try {
         if (var3 - var5 <= 4000L) {
            return;
         }
      } catch (ConcurrentModificationException var16) {
         throw a(var16);
      }

      var2.func_74772_a("sexmod:galath_coin_activation_time", 0L);
      Vec3d var7 = var1.func_174791_d().func_72441_c((double)0.0F, (double)var1.func_70047_e(), (double)0.0F);
      Vec3d var8 = var7.func_178787_e(var1.func_70040_Z().func_72432_b().func_186678_a((double)2.0F));
      Random var9 = var1.func_70681_au();
      int var10 = 0;

      try {
         while((float)var10 < 100.0F) {
            var1.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, var8.field_72450_a, var8.field_72448_b, var8.field_72449_c, (double)((2.0F * var9.nextFloat() - 1.0F) * 0.2F), (double)((2.0F * var9.nextFloat() - 1.0F) * 0.2F), (double)((2.0F * var9.nextFloat() - 1.0F) * 0.2F), new int[0]);
            ++var10;
         }
      } catch (ConcurrentModificationException var15) {
         throw a(var15);
      }

      World var17 = var1.field_70170_p;

      try {
         if (var17.field_72995_K) {
            this.a(var1);
            return;
         }
      } catch (ConcurrentModificationException var14) {
         throw a(var14);
      }

      f_ var11 = new f_(var1.field_70170_p, var1, var8);

      try {
         var11.func_70634_a(var8.field_72450_a, var8.field_72448_b, var8.field_72449_c);
         v.a(var1, var11);
         var1.field_70170_p.func_72838_d(var11);
         if (v.b(var1.getPersistentID())) {
            var11.v();
         }

      } catch (ConcurrentModificationException var12) {
         throw a(var12);
      }
   }

   void d(EntityPlayer var1) {
      try {
         if (var1.field_70170_p.field_72995_K) {
            this.b(var1);
            return;
         }
      } catch (ConcurrentModificationException var2) {
         throw a(var2);
      }

      this.c(var1);
   }

   void c(EntityPlayer var1) {
      UUID var2 = v.b(var1);
      em var3 = em.a(var2);

      try {
         if (var3 instanceof f_) {
            a((f_)var3);
         }

      } catch (ConcurrentModificationException var4) {
         throw a(var4);
      }
   }

   public static void a(f_ var0) {
      var0.b(fp.GALATH_DE_SUMMON);
      var0.aC();
      var0.a(true);
      var0.c((Vec3d)var0.func_174791_d());
      var0.b(var0.field_70177_z);
   }

   @SideOnly(Side.CLIENT)
   void b(EntityPlayer var1) {
      f_ var2 = null;

      try {
         label69: {
            Iterator var3 = em.ad().iterator();

            em var4;
            while(true) {
               while(true) {
                  while(true) {
                     while(true) {
                        if (!var3.hasNext()) {
                           break label69;
                        }

                        var4 = (em)var3.next();

                        try {
                           if (var4.field_70128_L) {
                              continue;
                           }
                           break;
                        } catch (ConcurrentModificationException var6) {
                           throw a(var6);
                        }
                     }

                     try {
                        if (!var4.field_70170_p.field_72995_K) {
                           continue;
                        }
                        break;
                     } catch (ConcurrentModificationException var7) {
                        throw a(var7);
                     }
                  }

                  try {
                     if (!(var4 instanceof f_)) {
                        continue;
                     }
                     break;
                  } catch (ConcurrentModificationException var8) {
                     throw a(var8);
                  }
               }

               try {
                  if (!var1.equals(var4.z())) {
                     continue;
                  }
                  break;
               } catch (ConcurrentModificationException var9) {
                  throw a(var9);
               }
            }

            var2 = (f_)var4;
         }
      } catch (ConcurrentModificationException var10) {
      }

      try {
         if (var2 == null) {
            return;
         }
      } catch (ConcurrentModificationException var5) {
         throw a(var5);
      }

      a(var1, var2);
   }

   @SideOnly(Side.CLIENT)
   public static void a(UUID var0, f_ var1) {
      World var2 = var1.field_70170_p;

      Vec3d var10000;
      label34: {
         try {
            if (var1.Q()) {
               var10000 = var1.o();
               break label34;
            }
         } catch (ConcurrentModificationException var11) {
            throw a(var11);
         }

         var10000 = var1.func_174791_d();
      }

      Vec3d var3 = var10000;
      Vec3d var4 = var3.func_72441_c((double)0.0F, (double)1.5F, (double)0.0F);
      Random var5 = var1.func_70681_au();

      for(int var6 = 0; (float)var6 < 100.0F; ++var6) {
         Vec3d var7 = new Vec3d((double)((var5.nextFloat() * 2.0F - 1.0F) * 1.5F), (double)((var5.nextFloat() * 2.0F - 1.0F) * 1.5F), (double)((var5.nextFloat() * 2.0F - 1.0F) * 1.5F));
         Vec3d var8 = var4.func_178787_e(var7);
         Vec3d var9 = var7.func_186678_a((double)-0.03F);
         var2.func_175688_a(EnumParticleTypes.DRAGON_BREATH, var8.field_72450_a, var8.field_72448_b, var8.field_72449_c, var9.field_72450_a, var9.field_72448_b, var9.field_72449_c, new int[0]);
      }

      try {
         if (Minecraft.func_71410_x().field_71439_g.getPersistentID().equals(var0)) {
            v.f = false;
         }

      } catch (ConcurrentModificationException var10) {
         throw a(var10);
      }
   }

   public static void a(EntityPlayer var0, f_ var1) {
      a(var0.getPersistentID(), var1);
   }

   void a(EntityPlayer param1, NBTTagCompound param2, long param3, long param5) {
      // $FF: Couldn't be decompiled
   }

   public void registerControllers(AnimationData var1) {
      this.a = new AnimationController<cc>(this, "controller", 0.0F, this::a);
      var1.addAnimationController(this.a);
   }

   @SideOnly(Side.CLIENT)
   protected <segs extends IAnimatable> PlayState a(AnimationEvent<segs> param1) {
      // $FF: Couldn't be decompiled
   }

   public AnimationFactory getFactory() {
      return this.i;
   }

   private static ConcurrentModificationException a(ConcurrentModificationException var0) {
      return var0;
   }
}
