package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;

public enum fy {
   JENNY(ex.class, 177013, es.class, 12388645),
   ELLIE(el.class, 228922, ee.class, 46348348),
   BIA(e0.class, 230053, eg.class, 65456415),
   SLIME(fn.class, 168597, ec.class, 54816432),
   BEE(fz.class, 4663354, e9.class, 48648638),
   ALLIE(ev.class, 5614613, e5.class, 64867483),
   LUNA(eb.class, 6816463, ed.class, 81234824),
   KOBOLD(ff.class, 5648456, e7.class, 62484851, true),
   GOBLIN(e3.class, 4567275, eq.class, 6584344, true),
   GALATH(f_.class, 314351, er.class, 652535516),
   MANGLELIE(f8.class, 618151);

   public final int npcID;
   public final int playerID;
   public final Class<? extends em> npcClass;
   public final Class<? extends ei> playerClass;
   public final boolean isNpcOnly;
   public final int editorID;
   public final boolean hasSpecifics;

   private fy(Class<? extends em> var3, int var4, Class<? extends ei> var5, int var6, boolean var7) {
      this.npcID = var4;
      this.playerID = var6;
      this.npcClass = var3;
      this.playerClass = var5;
      this.isNpcOnly = false;
      this.hasSpecifics = var7;
      this.editorID = r.b++;
   }

   private fy(Class<? extends em> var3, int var4, Class<? extends ei> var5, int var6) {
      this.npcID = var4;
      this.playerID = var6;
      this.npcClass = var3;
      this.playerClass = var5;
      this.isNpcOnly = false;
      this.hasSpecifics = false;
      this.editorID = r.b++;
   }

   private fy(Class<? extends em> var3, int var4) {
      this.npcID = var4;
      this.npcClass = var3;
      this.isNpcOnly = true;
      this.hasSpecifics = false;
      this.editorID = r.b++;
      this.playerClass = null;
      this.playerID = 0;
   }

   public static fy a(String var0) {
      for(fy var4 : values()) {
         try {
            if (var4.toString().equalsIgnoreCase(var0)) {
               return var4;
            }
         } catch (RuntimeException var5) {
            throw a(var5);
         }
      }

      return JENNY;
   }

   public static fy a(Entity var0) {
      try {
         if (!(var0 instanceof em)) {
            return null;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      em var1 = (em)var0;
      Class var2 = var1.getClass();

      for(fy var6 : values()) {
         try {
            if (var2.equals(var6.npcClass)) {
               return var6;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         try {
            if (var2.equals(var6.playerClass)) {
               return var6;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }
      }

      return null;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
