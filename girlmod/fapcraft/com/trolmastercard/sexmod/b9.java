package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class b9 extends Item {
   public static final b9 a = new b9();

   public b9() {
      this.func_77637_a(CreativeTabs.field_78026_f);
      this.field_77777_bU = 1;
   }

   public ActionResult<ItemStack> func_77659_a(World var1, EntityPlayer var2, EnumHand var3) {
      ItemStack var4 = var2.func_184586_b(var3);
      Vec3d var5 = var2.func_174824_e(0.0F);
      Vec3d var6 = var2.func_70676_i(0.0F);
      Vec3d var7 = var5.func_72441_c(var6.field_72450_a * (double)5.0F, var6.field_72448_b * (double)5.0F, var6.field_72449_c * (double)5.0F);
      RayTraceResult var8 = var1.func_147447_a(var5, var7, false, false, true);

      try {
         if (var8 == null) {
            return new ActionResult(EnumActionResult.FAIL, var2.func_184586_b(var3));
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      try {
         if (var8.field_72313_a == Type.MISS) {
            return new ActionResult(EnumActionResult.FAIL, var2.func_184586_b(var3));
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      try {
         if (!var2.field_71075_bZ.field_75098_d) {
            var4.func_190918_g(1);
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      try {
         if (!var1.field_72995_K) {
            ax.a(var1, var8.field_72307_f);
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      return new ActionResult(EnumActionResult.SUCCESS, var2.func_184586_b(var3));
   }

   public static void a() {
      a.setRegistryName("sexmod", "tribe_egg");
      a.func_77655_b("tribe_egg");
      MinecraftForge.EVENT_BUS.register(b9.class);
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Item> var0) {
      var0.getRegistry().register(a);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void a(ModelRegistryEvent var0) {
      ModelLoader.setCustomModelResourceLocation(a, 0, new ModelResourceLocation("sexmod:tribe_egg"));
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
