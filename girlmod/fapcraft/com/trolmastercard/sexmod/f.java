package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class f extends EntityAIBase {
   public em d;
   public EntityPlayer a;
   public PathNavigate c;
   public EntityDataManager e;
   public a f;
   public static final double g = (double)0.5F;
   public static final double h = 0.7;
   public static final int b = 60;

   public f(em var1) {
      this.f = f.a.IDLE;
      this.d = var1;
      this.c = var1.func_70661_as();
      this.e = var1.func_184212_Q();
   }

   protected void c() {
      int var2 = 0;

      BlockPos var1;
      do {
         var1 = this.a.func_180425_c().func_177982_a(r.f.nextInt(10), 0, r.f.nextInt(10));
         ++var2;
      } while(var2 < 20 && !this.d.func_184595_k((double)var1.func_177958_n(), (double)var1.func_177956_o(), (double)var1.func_177952_p()));

      try {
         if (var2 >= 20) {
            this.d.func_70107_b(this.a.field_70165_t, this.a.field_70163_u, this.a.field_70161_v);
         }
      } catch (RuntimeException var3) {
         throw b(var3);
      }

      this.d.field_70159_w = (double)0.0F;
      this.d.field_70181_x = (double)0.0F;
      this.d.field_70179_y = (double)0.0F;
   }

   protected double b() {
      float var1 = this.d.func_70032_d(this.a);
      double var2;
      em.a var4;
      if (this.a.func_70051_ag()) {
         var2 = 0.7;
         var4 = em.a.RUN;
      } else {
         var2 = (double)0.5F;
         var4 = em.a.WALK;
      }

      double var5 = Math.floor((double)(var1 / 5.0F)) * 0.2;
      var2 += var5;
      if (this.d.func_70090_H()) {
         var2 *= (double)60.0F;
         var4 = em.a.WALK;
      }

      this.c.func_75489_a(var2);
      this.d.a(var4);
      return var2;
   }

   public void func_75251_c() {
      this.c.func_75499_g();
      this.f = f.a.IDLE;
      this.d.b(fp.NULL);
      this.e.func_187227_b(em.v, "");
      this.c = null;
      this.e = null;
      this.a = null;
   }

   public boolean func_75250_a() {
      boolean var10000;
      try {
         if (!((String)this.d.func_184212_Q().func_187225_a(em.v)).equals("")) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw b(var1);
      }

      var10000 = false;
      return var10000;
   }

   public boolean func_75253_b() {
      // $FF: Couldn't be decompiled
   }

   public void func_75249_e() {
      this.c = this.d.func_70661_as();
      this.e = this.d.func_184212_Q();
      this.a = this.d.field_70170_p.func_152378_a(UUID.fromString((String)this.e.func_187225_a(em.v)));
   }

   public void func_75246_d() {
      // $FF: Couldn't be decompiled
   }

   protected abstract a a();

   protected abstract void a(a var1);

   @SubscribeEvent
   public void a(LivingDeathEvent var1) {
      if (var1.getEntityLiving() instanceof em) {
         em var2 = (em)var1.getEntityLiving();

         try {
            if (!((String)var2.func_184212_Q().func_187225_a(em.v)).equals("")) {
               var1.setCanceled(true);
            }
         } catch (RuntimeException var3) {
            throw b(var3);
         }
      }

   }

   private static RuntimeException b(RuntimeException var0) {
      return var0;
   }

   public static enum a {
      ATTACK,
      FOLLOW,
      IDLE,
      RIDE,
      DOWNED;
   }
}
