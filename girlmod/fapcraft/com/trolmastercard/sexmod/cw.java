package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class cw extends EntityAIBase {
   private final EntityVillager c;
   private EntityVillager d;
   private final World a;
   private int b;

   public cw(EntityVillager var1) {
      this.c = var1;
      this.a = var1.field_70170_p;
      this.func_75248_a(3);
   }

   public boolean func_75250_a() {
      try {
         if (this.b != 0) {
            return false;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      Entity var1 = this.a.func_72857_a(EntityVillager.class, this.c.func_174813_aQ().func_72314_b((double)8.0F, (double)3.0F, (double)8.0F), this.c);

      try {
         if (var1 == null) {
            return false;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      this.d = (EntityVillager)var1;
      return true;
   }

   public void func_75249_e() {
      this.b = 300;
      this.c.func_70947_e(true);
   }

   public void func_75251_c() {
   }

   public boolean func_75253_b() {
      return true;
   }

   public void func_75246_d() {
      try {
         --this.b;
         this.c.func_70671_ap().func_75651_a(this.d, 10.0F, 30.0F);
         if (this.c.func_70068_e(this.d) > (double)2.25F) {
            this.c.func_70661_as().func_75497_a(this.d, (double)0.25F);
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      try {
         if (this.b <= 0) {
            this.a();
            this.c.field_70714_bg.func_85156_a(this);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (this.c.func_70681_au().nextInt(35) == 0) {
            this.a.func_72960_a(this.c, (byte)12);
         }

      } catch (RuntimeException var1) {
         throw a(var1);
      }
   }

   private void a() {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
