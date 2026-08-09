package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class hy extends Item implements IAnimatable {
   public static final hy b = new hy();
   private final AnimationFactory a = new AnimationFactory(this);

   public hy() {
      this.func_77637_a(CreativeTabs.field_78040_i);
      this.field_77777_bU = 1;
   }

   public static void a() {
      b.setRegistryName("sexmod", "dragon_staff");
      b.func_77655_b("dragon_staff");
      MinecraftForge.EVENT_BUS.register(hy.class);
   }

   public ActionResult<ItemStack> func_77659_a(World var1, EntityPlayer var2, EnumHand var3) {
      return new ActionResult(EnumActionResult.FAIL, var2.func_184586_b(var3));
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Item> var0) {
      var0.getRegistry().register(b);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void a(ModelRegistryEvent var0) {
      ModelLoader.setCustomModelResourceLocation(b, 0, new ModelResourceLocation("sexmod:dragon_staff"));
      b.setTileEntityItemStackRenderer(new fa());
   }

   public void registerControllers(AnimationData var1) {
   }

   public AnimationFactory getFactory() {
      return this.a;
   }

   public static class a {
      @SubscribeEvent
      public void a(PlayerInteractEvent.RightClickItem param1) {
         // $FF: Couldn't be decompiled
      }

      @SideOnly(Side.CLIENT)
      void a() {
         Minecraft.func_71410_x().func_147108_a(new j());
         ge.b.sendToServer(new b3());
      }

      @SubscribeEvent
      public void a(PlayerInteractEvent.RightClickBlock param1) {
         // $FF: Couldn't be decompiled
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
