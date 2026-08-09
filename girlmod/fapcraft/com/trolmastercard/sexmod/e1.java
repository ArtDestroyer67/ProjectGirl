package com.trolmastercard.sexmod;

public class e1 {
   public static final e1 a = new e1(0, 0);
   public int c;
   public int b;

   public e1(int var1, int var2) {
      this.c = var1;
      this.b = var2;
   }

   public float a(int var1, int var2) {
      float var3 = (float)(var1 - this.c);
      float var4 = (float)(var2 - this.b);
      return (float)Math.sqrt((double)(var3 * var3 + var4 * var4));
   }

   public String toString() {
      return String.format("(%s, %s)", this.c, this.b);
   }
}
