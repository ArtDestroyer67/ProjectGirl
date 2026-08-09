package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class hj extends Item {
   public static final hj a = new hj();

   public hj() {
      this.func_77637_a(CreativeTabs.field_78040_i);
      this.field_77777_bU = 1;
   }

   public void func_77663_a(ItemStack var1, World var2, Entity var3, int var4, boolean var5) {
      try {
         if (var2.field_72995_K) {
            this.a(var3, var1);
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      super.func_77663_a(var1, var2, var3, var4, var5);
   }

   @SideOnly(Side.CLIENT)
   void a(Entity param1, ItemStack param2) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(PlayerInteractEvent.EntityInteract param1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(AttackEntityEvent var1) {
      Entity var2 = var1.getTarget();

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      try {
         if (!(var2 instanceof em)) {
            return;
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      EntityPlayer var3 = var1.getEntityPlayer();

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      ItemStack var4 = var3.func_184614_ca();
      if (var4.func_77973_b() != a) {
         var4 = var3.func_184592_cb();
      }

      try {
         if (var4.func_77973_b() != a) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         var1.setCanceled(true);
         if (!var3.field_70170_p.field_72995_K) {
            return;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      em var5 = (em)var2;
      String var6 = var5.C();
      String var7 = em.c(em.h(var5.f()));
      var3.func_145747_a(new TextComponentString(String.format("%s's model-code: %s%s$%s", var5.c(), TextFormatting.YELLOW, var6, var7)));
      var3.func_145747_a(new TextComponentString(TextFormatting.ITALIC + "copied to clipboard"));
      be.a(String.format("%s$%s", var6, var7));
   }

   @SubscribeEvent
   public void a(PlayerInteractEvent.LeftClickBlock var1) {
      try {
         if (this.a(var1.getEntityPlayer(), var1.getWorld())) {
            var1.setCanceled(true);
         }

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   @SubscribeEvent
   public void a(PlayerInteractEvent.LeftClickEmpty var1) {
      this.a(var1.getEntityPlayer(), var1.getWorld());
   }

   boolean a(EntityPlayer var1, World var2) {
      try {
         if (var1 == null) {
            return false;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      ItemStack var3 = var1.func_184614_ca();
      if (var3.func_77973_b() != a) {
         var3 = var1.func_184592_cb();
      }

      try {
         if (var3.func_77973_b() != a) {
            return false;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (!var2.field_72995_K) {
            return true;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      ei var4 = ei.d(var1.getPersistentID());

      try {
         if (var4 == null) {
            var1.func_146105_b(new TextComponentString("you gotta turn into the girl, you want to copy the model-code off"), true);
            return true;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      String var5 = var4.C();
      String var6 = em.c(em.h(var4.f()));
      var1.func_145747_a(new TextComponentString(String.format("%s's model-code: %s%s$%s", be.b(fy.a((Entity)var4).toString()), TextFormatting.YELLOW, var5, var6)));
      var1.func_145747_a(new TextComponentString(TextFormatting.ITALIC + "copied to clipboard"));
      be.a(String.format("%s$%s", var5, var6));
      return true;
   }

   public static void a() {
      a.setRegistryName("sexmod", "npc_editor_wand");
      a.func_77655_b("npc_editor_wand");
      MinecraftForge.EVENT_BUS.register(hj.class);
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Item> var0) {
      var0.getRegistry().register(a);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void a(ModelRegistryEvent var0) {
      ModelLoader.setCustomModelResourceLocation(a, 0, new ModelResourceLocation("sexmod:npc_editor_wand"));
      ModelLoader.setCustomModelResourceLocation(a, 1, new ModelResourceLocation("sexmod:npc_editor_wand_active"));
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
