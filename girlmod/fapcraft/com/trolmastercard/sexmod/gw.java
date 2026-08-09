package com.trolmastercard.sexmod;

public enum gw {
   GIRL_SPECIFIC,
   HEAD(0, "customHead"),
   FOOT_L(60, "customShoeL"),
   FOOT_R(80, "customShoeR"),
   HAND_L(100, "customHandL"),
   HAND_R(120, "customHandR"),
   CUSTOM_BONE(140);

   public static final String SEPARATOR = "#";
   public int buttonIDPlus;
   public int buttonIDMinus;
   public String boneName = null;
   public int iconXPos = 0;

   private gw() {
   }

   private gw(int var3) {
      this.iconXPos = var3;
   }

   private gw(int var3, String var4) {
      this.iconXPos = var3;
      this.boneName = var4;
      this.buttonIDPlus = ++r.i;
      this.buttonIDMinus = ++r.i;
   }

   public static int a() {
      return values().length - 2;
   }
}
