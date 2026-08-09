package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class gu {
   public gu() {
      gu.a.a(EntityEquipmentSlot.HEAD, ArmorMaterial.LEATHER, 1, 0);
      gu.a.a(EntityEquipmentSlot.HEAD, ArmorMaterial.GOLD, 2, 0);
      gu.a.a(EntityEquipmentSlot.HEAD, ArmorMaterial.CHAIN, 2, 0);
      gu.a.a(EntityEquipmentSlot.HEAD, ArmorMaterial.IRON, 2, 0);
      gu.a.a(EntityEquipmentSlot.HEAD, ArmorMaterial.DIAMOND, 3, 3);
      gu.a.a(EntityEquipmentSlot.CHEST, ArmorMaterial.LEATHER, 3, 0);
      gu.a.a(EntityEquipmentSlot.CHEST, ArmorMaterial.GOLD, 5, 0);
      gu.a.a(EntityEquipmentSlot.CHEST, ArmorMaterial.CHAIN, 5, 0);
      gu.a.a(EntityEquipmentSlot.CHEST, ArmorMaterial.IRON, 6, 0);
      gu.a.a(EntityEquipmentSlot.CHEST, ArmorMaterial.DIAMOND, 8, 3);
      gu.a.a(EntityEquipmentSlot.LEGS, ArmorMaterial.LEATHER, 2, 0);
      gu.a.a(EntityEquipmentSlot.LEGS, ArmorMaterial.GOLD, 3, 0);
      gu.a.a(EntityEquipmentSlot.LEGS, ArmorMaterial.CHAIN, 4, 0);
      gu.a.a(EntityEquipmentSlot.LEGS, ArmorMaterial.IRON, 5, 0);
      gu.a.a(EntityEquipmentSlot.LEGS, ArmorMaterial.DIAMOND, 6, 3);
      gu.a.a(EntityEquipmentSlot.FEET, ArmorMaterial.LEATHER, 1, 0);
      gu.a.a(EntityEquipmentSlot.FEET, ArmorMaterial.GOLD, 1, 0);
      gu.a.a(EntityEquipmentSlot.FEET, ArmorMaterial.CHAIN, 1, 0);
      gu.a.a(EntityEquipmentSlot.FEET, ArmorMaterial.IRON, 2, 0);
      gu.a.a(EntityEquipmentSlot.FEET, ArmorMaterial.DIAMOND, 3, 3);
   }

   @SubscribeEvent
   public void a(LivingDamageEvent var1) {
      try {
         if (!(var1.getEntity() instanceof e2)) {
            return;
         }
      } catch (RuntimeException var22) {
         throw a(var22);
      }

      e2 var2 = (e2)var1.getEntity();
      ItemStack[] var3 = new ItemStack[]{var2.Q.getStackInSlot(2), var2.Q.getStackInSlot(3), var2.Q.getStackInSlot(4), var2.Q.getStackInSlot(5)};
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();

      for(ItemStack var9 : var3) {
         try {
            if (var9.func_77973_b() instanceof ItemArmor) {
               var4.add((ItemArmor)var9.func_77973_b());
               var5.add(var9);
            }
         } catch (RuntimeException var21) {
            throw a(var21);
         }
      }

      try {
         if (var4.size() == 0) {
            return;
         }
      } catch (RuntimeException var18) {
         throw a(var18);
      }

      DamageSource var23 = var1.getSource();
      int var24 = 0;
      int var25 = 0;
      if (!var23.func_76363_c()) {
         for(ItemArmor var10 : var4) {
            var24 += gu.a.a(var10.field_77881_a, var10.func_82812_d());
            var25 += gu.a.b(var10.field_77881_a, var10.func_82812_d());
         }
      }

      float var27 = var1.getAmount();
      var27 *= 1.0F - Math.min(20.0F, Math.max((float)var24 / 5.0F, (float)var24 - 4.0F * var27 / ((float)var25 + 8.0F))) / 25.0F;
      float var29 = 0.0F;

      for(ItemStack var13 : var5) {
         int var14 = EnchantmentHelper.func_77506_a(Enchantments.field_180310_c, var13);
         var27 -= (float)var14 * 0.04F * var27;
         int var15 = EnchantmentHelper.func_77506_a(Enchantments.field_92091_k, var13);

         float var10000;
         float var10001;
         label82: {
            try {
               var10000 = var29;
               if (r.f.nextFloat() < 0.15F * (float)var15) {
                  var10001 = r.f.nextFloat() * 4.0F + 1.0F;
                  break label82;
               }
            } catch (RuntimeException var20) {
               throw a(var20);
            }

            var10001 = 0.0F;
         }

         var29 = var10000 + var10001;
         var29 = Math.min(4.0F, var29);
         if (var23.func_76347_k()) {
            int var16 = EnchantmentHelper.func_77506_a(Enchantments.field_77329_d, var13);
            var27 -= (float)var16 * 0.08F * var27;
         }

         if (var23.func_94541_c()) {
            int var32 = EnchantmentHelper.func_77506_a(Enchantments.field_185297_d, var13);
            var27 -= (float)var32 * 0.08F * var27;
         }

         if (var23.field_76373_n.equals("fall")) {
            int var33 = EnchantmentHelper.func_77506_a(Enchantments.field_180309_e, var13);
            var27 -= (float)var33 * 0.12F * var27;
         }

         if (var23.func_76352_a()) {
            int var34 = EnchantmentHelper.func_77506_a(Enchantments.field_180308_g, var13);
            var27 -= (float)var34 * 0.08F * var27;
         }
      }

      label116: {
         try {
            if (!(var29 > 0.0F) || !(var23 instanceof EntityDamageSource)) {
               break label116;
            }
         } catch (RuntimeException var19) {
            throw a(var19);
         }

         EntityDamageSource var31 = (EntityDamageSource)var23;

         try {
            if (var31.func_76346_g() != null) {
               var31.func_76346_g().func_70097_a(DamageSource.func_92087_a(var2), var29);
            }
         } catch (RuntimeException var17) {
            throw a(var17);
         }
      }

      var1.setAmount(var27);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   static class a {
      public static HashMap<String, Integer[]> a = new HashMap();

      public static int a(EntityEquipmentSlot var0, ItemArmor.ArmorMaterial var1) {
         try {
            return ((Integer[])a.get(var0.toString() + var1.toString()))[0];
         } catch (NullPointerException var3) {
            return 3;
         }
      }

      public static int b(EntityEquipmentSlot var0, ItemArmor.ArmorMaterial var1) {
         try {
            return ((Integer[])a.get(var0.toString() + var1.toString()))[1];
         } catch (NullPointerException var3) {
            return 0;
         }
      }

      public static void a(EntityEquipmentSlot var0, ItemArmor.ArmorMaterial var1, int var2, int var3) {
         a.put(var0.toString() + var1.toString(), new Integer[]{var2, var3});
      }
   }
}
