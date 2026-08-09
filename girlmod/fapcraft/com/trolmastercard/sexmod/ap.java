package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ap extends Item implements IAnimatable {
   static final String e = "sexmodAllieInUse";
   static final String d = "sexmodAllieInUseTicks";
   public static final String j = "sexmodUses";
   public static final String h = "sexmodAllieID";
   static final Integer c = 95;
   static final Integer k = 50;
   public static final int a = 150;
   public static final float f = 0.75F;
   public static final ap b = new ap();
   private final AnimationFactory i = new AnimationFactory(this);
   AnimationController<ap> g;

   public ap() {
      this.func_77637_a(CreativeTabs.field_78026_f);
      this.field_77777_bU = 1;
   }

   public static void a() {
      b.setRegistryName("sexmod", "allies_lamp");
      b.func_77655_b("allies_lamp");
      MinecraftForge.EVENT_BUS.register(ap.class);
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Item> var0) {
      var0.getRegistry().register(b);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void a(ModelRegistryEvent var0) {
      ModelLoader.setCustomModelResourceLocation(b, 0, new ModelResourceLocation("sexmod:allies_lamp"));
      b.setTileEntityItemStackRenderer(new f0());
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderGameOverlayEvent.Pre var1) {
      NBTTagCompound var2 = Minecraft.func_71410_x().field_71439_g.getEntityData();

      try {
         if (var2.func_74767_n("sexmodAllieInUse")) {
            var1.setCanceled(true);
         }

      } catch (RuntimeException var3) {
         throw a(var3);
      }
   }

   @SubscribeEvent
   public void a(LootTableLoadEvent var1) {
      HashSet var2 = new HashSet();
      var2.add(LootTableList.field_186424_f);
      var2.add(LootTableList.field_186429_k);
      var2.add(LootTableList.field_186422_d);
      var2.add(LootTableList.field_191192_o);
      if (var2.contains(var1.getName())) {
         LootPool var3 = var1.getTable().getPool("pool3");
         if (var3 == null) {
            var3 = var1.getTable().getPool("pool2");
         }

         try {
            if (var3 != null) {
               var3.addEntry(new LootEntryItem(b, 5, 0, new LootFunction[0], new LootCondition[0], "sexmod:allies_lamp"));
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }
      }

   }

   public void registerControllers(AnimationData var1) {
      this.g = new AnimationController<ap>(this, "controller", 2.0F, this::a);
      var1.addAnimationController(this.g);
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack var1, World var2, List<String> var3, ITooltipFlag var4) {
      NBTTagCompound var5 = var1.func_77978_p();

      try {
         if (var5 == null) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      int var6 = 3 - var1.func_77978_p().func_74762_e("sexmodUses");

      label47: {
         try {
            switch (var6) {
               case 0:
                  break;
               case 1:
                  break label47;
               case 2:
                  var3.add("2 wishes left");
                  return;
               default:
                  return;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         var3.add("no wishes left");
         return;
      }

      var3.add("1 wish left");
   }

   @SideOnly(Side.CLIENT)
   protected <segs extends IAnimatable> PlayState a(AnimationEvent<segs> var1) {
      EntityPlayerSP var2 = Minecraft.func_71410_x().field_71439_g;
      NBTTagCompound var3 = ((EntityPlayer)var2).getEntityData();
      boolean var4 = var3.func_74767_n("sexmodAllieInUse");

      try {
         if (!var4) {
            var1.getController().clearAnimationCache();
            return PlayState.STOP;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      var1.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.lamp.rub", (ILoopType)ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
      return PlayState.CONTINUE;
   }

   public void func_77663_a(ItemStack param1, World param2, Entity param3, int param4, boolean param5) {
      // $FF: Couldn't be decompiled
   }

   Vec3d a(EntityPlayer var1) {
      return var1.func_174791_d().func_178787_e(ck.a(new Vec3d((double)0.0F, (double)0.0F, (double)2.0F), var1.field_70759_as));
   }

   public AnimationFactory getFactory() {
      return this.i;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a {
      @SubscribeEvent
      public void a(PlayerEvent.PlayerLoggedOutEvent var1) {
         var1.player.getEntityData().func_74757_a("sexmodAllieInUse", false);
      }

      @SubscribeEvent
      public void a(PlayerInteractEvent.RightClickItem param1) {
         // $FF: Couldn't be decompiled
      }

      private static ConcurrentModificationException a(ConcurrentModificationException var0) {
         return var0;
      }
   }
}
