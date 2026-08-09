package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.apache.logging.log4j.Level;

public class v extends WorldSavedData {
   public static boolean f = true;
   public static final float c = 60.0F;
   public static final String e = "sexmod:galath_owner_ship";
   public static final String d = "sexmod:ownershipdata";
   public static final String g = "sexmod:mangownershipdata";
   static final long a = 0L;
   static gl<UUID, UUID> h = new gl<UUID, UUID>();
   static HashMap<UUID, Long> b = new HashMap();
   static HashSet<UUID> i = new HashSet();

   public v() {
      super("sexmod:galath_owner_ship");
   }

   public v(String var1) {
      super("sexmod:galath_owner_ship");
   }

   public static void a() {
      i.clear();
      h.b();
   }

   public static void e(UUID var0) {
      UUID var1 = f(var0);

      try {
         if (var1 == null) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      i.add(var1);
   }

   public static boolean b(UUID var0) {
      return i.contains(var0);
   }

   public static boolean c(f_ var0) {
      UUID var1 = h.b(var0.f());

      try {
         if (var1 == null) {
            return false;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      World var2 = var0.field_70170_p;
      EntityPlayer var3 = var2.func_152378_a(var1);

      try {
         if (var3 == null) {
            return true;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (var3.field_71093_bK != var0.field_71093_bK) {
            return false;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         return !(var3.func_70032_d(var0) > 60.0F);
      } catch (RuntimeException var4) {
         throw a(var4);
      }
   }

   public static boolean b(EntityPlayer var0, f_ var1) {
      return var1.f().equals(h.c(var0.getPersistentID()));
   }

   public static void a(f_ var0) {
      f8 var1 = var0.a(true);

      try {
         if (var1 != null) {
            var0.field_70170_p.func_72900_e(var1);
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      UUID var2 = h.b(var0.f());

      try {
         if (var2 == null) {
            var0.field_70170_p.func_72900_e(var0);
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      World var3 = var0.field_70170_p;
      EntityPlayer var4 = var3.func_152378_a(var2);

      try {
         var0.field_70170_p.func_72900_e(var0);
         h.a(var2);
         if (var4 != null) {
            ge.b.sendTo(new gf(false), (EntityPlayerMP)var4);
         }

      } catch (RuntimeException var5) {
         throw a(var5);
      }
   }

   public static boolean c(UUID var0) {
      boolean var10000;
      try {
         if (h.c(var0) != null) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      var10000 = false;
      return var10000;
   }

   public static UUID f(UUID var0) {
      return h.b(var0);
   }

   public static UUID b(f_ var0) {
      try {
         if (var0 == null) {
            return null;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      return f(var0.f());
   }

   public static UUID a(UUID var0) {
      return h.c(var0);
   }

   public static UUID b(EntityPlayer var0) {
      try {
         if (var0 == null) {
            return null;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      return a(var0.getPersistentID());
   }

   public static void a(UUID var0, UUID var1) {
      h.a(var0, var1);
   }

   public static void a(EntityPlayer var0, f_ var1) {
      try {
         if (var0 == null) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      try {
         if (var1 == null) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      a(var0.getPersistentID(), var1.f());
   }

   public static void d(UUID var0) {
      h.a(var0);
   }

   public static void a(EntityPlayer var0) {
      try {
         if (var0 == null) {
            return;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      d(var0.getPersistentID());
   }

   public static boolean a(UUID var0, World var1) {
      Long var2 = (Long)b.get(var0);

      try {
         if (!b(var0)) {
            return false;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (var2 == null) {
            return true;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      boolean var10000;
      try {
         if (var1.func_82737_E() - var2 > 0L) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      var10000 = false;
      return var10000;
   }

   public static void a(UUID var0, Long var1) {
      try {
         if (var0 == null) {
            Main.LOGGER.log(Level.WARN, "tried to save last cum dosage time on NULL player");
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      b.put(var0, var1);
   }

   @SubscribeEvent
   public void a(TickEvent.ServerTickEvent var1) {
      try {
         if (var1.phase != Phase.END) {
            return;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      World var2 = FMLCommonHandler.instance().getMinecraftServerInstance().func_130014_f_();
      ArrayList var3 = new ArrayList();

      for(Map.Entry var5 : h.c()) {
         UUID var6 = (UUID)var5.getKey();
         UUID var7 = (UUID)var5.getValue();
         EntityPlayer var8 = var2.func_152378_a(var6);

         try {
            if (var8 == null) {
               continue;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         try {
            if (em.a(var7) == null) {
               var3.add(var8);
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }
      }

      for(EntityPlayer var13 : var3) {
         h.a(var13.getPersistentID());
         ge.b.sendTo(new gf(false), (EntityPlayerMP)var13);
      }

   }

   @SubscribeEvent
   public void a(WorldEvent.Save var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75745_a("sexmod:galath_owner_ship", this);
      this.func_76185_a();
   }

   @SubscribeEvent
   public void a(WorldEvent.Load var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75742_a(v.class, "sexmod:galath_owner_ship");
   }

   public void func_76184_a(NBTTagCompound param1) {
      // $FF: Couldn't be decompiled
   }

   public NBTTagCompound func_189551_b(NBTTagCompound var1) {
      NBTTagCompound var2 = new NBTTagCompound();
      var2.func_74768_a("amount", h.e());
      int var3 = 0;

      for(Map.Entry var5 : h.c()) {
         UUID var6 = (UUID)var5.getKey();
         UUID var7 = (UUID)var5.getValue();
         Long var8 = (Long)b.get(var6);
         if (var8 == null) {
            var8 = 0L;
         }

         var2.func_186854_a("galath" + var3, var7);
         var2.func_186854_a("master" + var3, var6);
         var2.func_74772_a("lastcumdosage" + var3, var8);
         ++var3;
      }

      NBTTagCompound var10 = new NBTTagCompound();
      var3 = 0;

      for(UUID var12 : i) {
         var10.func_186854_a("mang" + var3++, var12);
      }

      var1.func_74782_a("sexmod:ownershipdata", var2);
      var1.func_74782_a("sexmod:mangownershipdata", var10);
      return var1;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
