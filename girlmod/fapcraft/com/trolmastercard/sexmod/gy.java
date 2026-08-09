package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class gy extends WorldSavedData {
   static final String a = "sexmod:customstaticgirlnames";
   static final HashMap<UUID, HashMap<fy, String>> b = new HashMap();

   public gy() {
      super("sexmod:customstaticgirlnames");
   }

   public gy(String var1) {
      super("sexmod:customstaticgirlnames");
   }

   @SubscribeEvent
   public void a(WorldEvent.Save var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75745_a("sexmod:customstaticgirlnames", this);
      this.func_76185_a();
   }

   @SubscribeEvent
   public void a(WorldEvent.Load var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75742_a(gy.class, "sexmod:customstaticgirlnames");
   }

   public static void a(UUID var0, fy var1, String var2) {
      HashMap var3 = (HashMap)b.get(var0);
      if (var3 == null) {
         var3 = new HashMap();
      }

      var3.put(var1, var2);
      b.put(var0, var3);
   }

   @Nullable
   public static String a(UUID var0, fy var1) {
      HashMap var2 = (HashMap)b.get(var0);

      try {
         if (var2 == null) {
            return null;
         }
      } catch (IllegalArgumentException var3) {
         throw a(var3);
      }

      return (String)var2.get(var1);
   }

   public void func_76184_a(NBTTagCompound var1) {
      for(String var3 : var1.func_150296_c()) {
         UUID var4;
         try {
            var4 = UUID.fromString(var3);
         } catch (IllegalArgumentException var6) {
            continue;
         }

         b.put(var4, this.a(var1.func_74775_l(var3)));
      }

   }

   public NBTTagCompound func_189551_b(NBTTagCompound var1) {
      for(Map.Entry var3 : b.entrySet()) {
         UUID var4 = (UUID)var3.getKey();
         var1.func_74782_a(var4.toString(), this.a((HashMap)var3.getValue()));
      }

      return var1;
   }

   private NBTTagCompound a(HashMap<fy, String> var1) {
      NBTTagCompound var2 = new NBTTagCompound();

      for(Map.Entry var4 : var1.entrySet()) {
         var2.func_74778_a(((fy)var4.getKey()).name(), (String)var4.getValue());
      }

      return var2;
   }

   private HashMap<fy, String> a(NBTTagCompound var1) {
      HashMap var2 = new HashMap();

      for(fy var6 : fy.values()) {
         String var7 = var1.func_74779_i(var6.name());

         try {
            if (!"".equals(var7)) {
               var2.put(var6, var7);
            }
         } catch (IllegalArgumentException var8) {
            throw a(var8);
         }
      }

      return var2;
   }

   private static IllegalArgumentException a(IllegalArgumentException var0) {
      return var0;
   }
}
