package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class gp extends ItemFishingRod {
   public static final gp a = new gp();

   public gp() {
      this.func_77656_e(64);
      this.func_77625_d(1);
      this.func_185043_a(new ResourceLocation("cast"), new IItemPropertyGetter() {
         @SideOnly(Side.CLIENT)
         public float func_185085_a(ItemStack var1, @Nullable World var2, @Nullable EntityLivingBase var3) {
            try {
               if (var3 == null) {
                  return 0.0F;
               }
            } catch (RuntimeException var6) {
               throw a(var6);
            }

            try {
               if (!(var3 instanceof eb)) {
                  return 0.0F;
               }
            } catch (RuntimeException var4) {
               throw a(var4);
            }

            float var10000;
            try {
               if ((Boolean)var3.func_184212_Q().func_187225_a(eb.af)) {
                  var10000 = 1.0F;
                  return var10000;
               }
            } catch (RuntimeException var5) {
               throw a(var5);
            }

            var10000 = 0.0F;
            return var10000;
         }

         private static RuntimeException a(RuntimeException var0) {
            return var0;
         }
      });
   }

   public static void a() {
      a.setRegistryName("sexmod", "luna_rod");
      a.func_77655_b("luna_rod");
      MinecraftForge.EVENT_BUS.register(gp.class);
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Item> var0) {
      var0.getRegistry().register(a);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void a(ModelRegistryEvent var0) {
      ModelLoader.setCustomModelResourceLocation(a, 0, new ModelResourceLocation("fishing_rod"));
   }

   public ActionResult<ItemStack> a(World var1, eb var2, EnumHand var3) {
      ItemStack var4 = var2.func_184586_b(var3);
      if (var2.av != null) {
         int var5 = var2.av.c();
         var4.func_77972_a(var5, var2);
         var2.func_184609_a(var3);
         var1.func_184148_a((EntityPlayer)null, var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, SoundEvents.field_193780_J, SoundCategory.NEUTRAL, 1.0F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
      } else {
         var1.func_184148_a((EntityPlayer)null, var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, SoundEvents.field_187612_G, SoundCategory.NEUTRAL, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
         if (!var1.field_72995_K) {
            gi.b = var2;
            double var12 = var2.func_174791_d().func_72438_d(new Vec3d((double)var2.ai.func_177958_n(), (double)var2.ai.func_177956_o(), (double)var2.ai.func_177952_p()));
            gi var7 = new gi(var1, var2, var12 * eb.ap);
            int var8 = EnchantmentHelper.func_191528_c(var4);

            try {
               if (var8 > 0) {
                  var7.b(var8);
               }
            } catch (RuntimeException var11) {
               throw a(var11);
            }

            int var9 = EnchantmentHelper.func_191529_b(var4);

            try {
               if (var9 > 0) {
                  var7.a(var9);
               }
            } catch (RuntimeException var10) {
               throw a(var10);
            }

            var1.func_72838_d(var7);
         }

         var2.func_184609_a(var3);
      }

      return new ActionResult(EnumActionResult.SUCCESS, var4);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
