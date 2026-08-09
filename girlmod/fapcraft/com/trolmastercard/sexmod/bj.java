package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class bj extends WorldSavedData {
   static final String a = "sexmod:static_custom_model_manager";
   static final String d = "sexmod:static_custom_model_manager";
   public static HashMap<UUID, String> c = new HashMap();
   public static HashMap<UUID, String> b = new HashMap();

   public bj() {
      super("sexmod:static_custom_model_manager");
   }

   public bj(String var1) {
      super("sexmod:static_custom_model_manager");
   }

   public static String c(em var0) {
      String var1 = b(var0);

      try {
         return var1 == null ? "" : var1;
      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   private static String b(em var0) {
      if (var0 instanceof f_) {
         UUID var4 = var0.f();
         UUID var2 = v.f(var4);
         if (var2 == null) {
            var2 = var4;
         }

         return (String)c.get(var2);
      } else if (var0 instanceof f8) {
         UUID var1 = v.f(((f8)var0).v());

         HashMap var10000;
         UUID var10001;
         try {
            var10000 = b;
            if (var1 == null) {
               var10001 = var0.f();
               return (String)var10000.get(var10001);
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10001 = var1;
         return (String)var10000.get(var10001);
      } else {
         return null;
      }
   }

   public static void a(em var0) {
      if (var0 instanceof f_) {
         UUID var4 = var0.f();
         UUID var2 = v.f(var4);
         if (var2 == null) {
            var2 = var4;
         }

         c.put(var2, var0.C());
      } else {
         if (var0 instanceof f8) {
            UUID var1 = v.f(((f8)var0).v());

            HashMap var10000;
            UUID var10001;
            label26: {
               try {
                  var10000 = b;
                  if (var1 == null) {
                     var10001 = var0.f();
                     break label26;
                  }
               } catch (RuntimeException var3) {
                  throw a(var3);
               }

               var10001 = var1;
            }

            var10000.put(var10001, var0.C());
         }

      }
   }

   @SubscribeEvent
   public void a(WorldEvent.Save var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75745_a("sexmod:static_custom_model_manager", this);
      this.func_76185_a();
   }

   @SubscribeEvent
   public void a(WorldEvent.Load var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75742_a(bj.class, "sexmod:static_custom_model_manager");
   }

   public void func_76184_a(NBTTagCompound var1) {
      NBTTagCompound var2 = var1.func_74775_l("sexmod:static_custom_model_manager");
      this.a(var2.func_74775_l("galath"), c);
      this.a(var2.func_74775_l("mang"), b);
   }

   public NBTTagCompound func_189551_b(NBTTagCompound var1) {
      NBTTagCompound var2 = new NBTTagCompound();
      var2.func_74782_a("galath", this.a(c));
      var2.func_74782_a("mang", this.a(b));
      var1.func_74782_a("sexmod:static_custom_model_manager", var2);
      return var1;
   }

   NBTTagCompound a(HashMap<UUID, String> var1) {
      NBTTagCompound var2 = new NBTTagCompound();
      int var3 = 0;

      for(Map.Entry var5 : var1.entrySet()) {
         UUID var6 = (UUID)var5.getKey();
         var2.func_74778_a("UUID" + var3, var6.toString());
         var2.func_74778_a("MODEL" + var3, (String)var5.getValue());
         ++var3;
      }

      return var2;
   }

   void a(NBTTagCompound var1, HashMap<UUID, String> var2) {
      int var3 = 0;

      while(true) {
         String var4 = var1.func_74779_i("UUID" + var3);

         try {
            if ("".equals(var4)) {
               return;
            }
         } catch (RuntimeException var5) {
            throw a(var5);
         }

         var2.put(UUID.fromString(var4), var1.func_74779_i("MODEL" + var3));
         ++var3;
      }
   }

   public static void a() {
      c.clear();
      b.clear();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
