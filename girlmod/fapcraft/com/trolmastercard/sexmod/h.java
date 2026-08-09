package com.trolmastercard.sexmod;

public class h extends f {
   int j = 0;
   int i = 0;

   public h(em var1) {
      super(var1);
   }

   public void func_75251_c() {
      super.func_75251_c();
      this.d.field_70747_aH = 0.02F;
   }

   protected f.a a() {
      // $FF: Couldn't be decompiled
   }

   protected void a(f.a var1) {
      switch (var1) {
         case FOLLOW:
            double var2 = (double)this.d.func_70032_d(this.a);

            label20: {
               try {
                  if ((double)this.c.func_111269_d() > var2) {
                     this.c.func_75499_g();
                     this.c.func_75497_a(this.a, (double)0.5F);
                     break label20;
                  }
               } catch (RuntimeException var4) {
                  throw a(var4);
               }

               this.c();
            }

            this.i = 300;
            this.b();
            break;
         case IDLE:
            this.b();
      }

   }

   protected double b() {
      float var1 = this.d.func_70032_d(this.a);
      float var2 = 0.02F;
      double var3 = Math.min(0.7, Math.floor((double)(var1 / 3.0F)) * 0.05);
      var2 = (float)((double)var2 + var3);
      this.d.field_70747_aH = var2;
      return (double)var2;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
