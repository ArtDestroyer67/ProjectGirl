package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
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

public class c7 extends Item implements IAnimatable {
   private final AnimationFactory b = new AnimationFactory(this);
   public static c7 a = new c7();

   public c7() {
      this.func_77625_d(1);
   }

   public static void a() {
      a.setRegistryName("sexmod", "kobold_egg_item");
      a.func_77655_b("kobold_egg_item");
      MinecraftForge.EVENT_BUS.register(c7.class);
   }

   public void registerControllers(AnimationData var1) {
   }

   public AnimationFactory getFactory() {
      return this.b;
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void a(ModelRegistryEvent var0) {
      ModelResourceLocation var1 = new ModelResourceLocation("sexmod:kobold_egg_item");
      ModelLoader.setCustomMeshDefinition(a, (var1x) -> var1);
      ModelBakery.registerItemVariants(a, new ResourceLocation[]{var1});
      a.setTileEntityItemStackRenderer(new hn());
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Item> var0) {
      var0.getRegistry().register(a);
   }

   @SubscribeEvent
   public static void a(PlayerInteractEvent.RightClickBlock var0) {
      World var1 = var0.getWorld();
      ItemStack var2 = var0.getItemStack();
      Vec3d var3 = var0.getHitVec();

      try {
         if (var1.field_72995_K) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (var2.func_77973_b() != a) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      i var4 = new i(var1);
      var4.func_70107_b(var3.field_72450_a, var3.field_72448_b, var3.field_72449_c);
      var4.func_184212_Q().func_187227_b(i.b, EyeAndKoboldColor.getColorByWoolId(var2.func_77960_j()).toString());
      NBTTagCompound var5 = var2.func_77978_p();

      try {
         if (var5 != null) {
            var4.f = UUID.fromString(var5.func_74779_i("tribeID"));
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      var1.func_72838_d(var4);
      var2.func_190918_g(1);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
