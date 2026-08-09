package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class g3 extends WorldSavedData implements IWorldGenerator {
   static final String j = "sexmod:generation";
   static final int h = 156;
   static final int a = 62;
   static final int b = 6;
   final double f;
   public static boolean i = true;
   final List<b> e;
   final List<a> d;
   private static g3 g = null;
   static boolean c = true;

   public static g3 b() {
      try {
         if (g == null) {
            g = new g3();
         }
      } catch (RuntimeException var0) {
         throw a(var0);
      }

      return g;
   }

   public g3(String var1) {
      this();
   }

   private g3() {
      super("sexmod:generation");
      this.f = (double)0.004F;
      this.e = new ArrayList();
      this.d = new ArrayList();
      g = this;
      this.e.add(new b("ellie", new HashSet(Arrays.asList(Biomes.field_150578_U, Biomes.field_150584_S, Biomes.field_76768_g, Biomes.field_150585_R)), new Vec3i(30, 27, 26), 9, true));
      this.e.add(new b("jenny", new HashSet(Arrays.asList(Biomes.field_76772_c, Biomes.field_76767_f)), new Vec3i(9, 4, 9), 1, true));
      this.e.add(new b("ellie", new HashSet(Arrays.asList(Biomes.field_150578_U, Biomes.field_150584_S, Biomes.field_76768_g, Biomes.field_150585_R)), new Vec3i(30, 27, 26), 9, true));
      this.e.add(new b("bia", new HashSet(Arrays.asList(Biomes.field_185448_Z, Biomes.field_150583_P)), new Vec3i(11, 9, 15), 2, true));
      this.e.add(new b("luna", new HashSet(Arrays.asList(Biomes.field_76771_b, Biomes.field_150575_M)), new Vec3i(3, 7, 10), 0, false));
   }

   public void a() {
      this.d.clear();
   }

   @SubscribeEvent
   public void a(WorldEvent.Save var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75745_a("sexmod:generation", this);
      this.func_76185_a();
   }

   @SubscribeEvent
   public void a(WorldEvent.Load var1) {
      World var2 = var1.getWorld();
      var2.func_175693_T().func_75742_a(g3.class, "sexmod:generation");
   }

   public void func_76184_a(NBTTagCompound var1) {
      this.a();
      NBTTagCompound var2 = var1.func_74775_l("sexmod:generation");
      int var3 = 0;

      while(true) {
         String var4 = var2.func_74779_i("sexmod:name" + var3);
         String var5 = var2.func_74779_i("sexmod:pos" + var3);

         try {
            if ("".equals(var4)) {
               break;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         try {
            if ("".equals(var5)) {
               break;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         this.d.add(new a(a(var5), var4));
         ++var3;
      }

   }

   public NBTTagCompound func_189551_b(NBTTagCompound var1) {
      var1.func_74782_a("sexmod:generation", new NBTTagCompound());
      NBTTagCompound var2 = new NBTTagCompound();
      int var3 = 0;

      for(a var5 : this.d) {
         var2.func_74778_a("sexmod:name" + var3, var5.a);
         var2.func_74778_a("sexmod:pos" + var3++, a(var5.b));
      }

      var1.func_74782_a("sexmod:generation", var2);
      return var1;
   }

   static String a(e1 var0) {
      return var0.c + "|" + var0.b;
   }

   static e1 a(String var0) {
      String[] var1 = var0.split("\\|");
      return new e1(Integer.parseInt(var1[0]), Integer.parseInt(var1[1]));
   }

   public void generate(Random var1, int var2, int var3, World var4, IChunkGenerator var5, IChunkProvider var6) {
      try {
         if (!i) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (var4.func_175624_G() == WorldType.field_77138_c) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      this.b(var4, var1, var2, var3);
      this.a(var4, var1, var2, var3);
      this.a(var1, var2, var3, var4);
   }

   void a(Random var1, int var2, int var3, World var4) {
      try {
         if (!c) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      c = false;

      for(b var6 : this.e) {
         this.a(var6, var1, var2, var3, var4);
      }

      c = true;
   }

   void a(b param1, Random param2, int param3, int param4, World param5) {
      // $FF: Couldn't be decompiled
   }

   void b(World var1, Random var2, int var3, int var4) {
      try {
         if (var2.nextDouble() > (double)0.004F) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      int var5 = var3 * 16 + 8;
      int var6 = var4 * 16 + 8;
      int var7 = cj.a(var1, var5, var6);

      try {
         if (var1.func_180495_p(new BlockPos(var5, var7, var6)).func_185904_a().func_76224_d()) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      ax.a(var1, new Vec3d((double)var5, (double)var7, (double)var6));
   }

   void a(World param1, Random param2, int param3, int param4) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   static class a {
      e1 b;
      String a;

      public a(e1 var1, String var2) {
         this.b = var1;
         this.a = var2;
      }
   }

   static class b {
      public final String f;
      public final b4 b;
      public final HashSet<Biome> e;
      public final Vec3i c;
      public final boolean d;
      public final int a;

      public b(String var1, HashSet<Biome> var2, Vec3i var3, int var4, boolean var5) {
         this.f = var1;
         this.e = var2;
         this.c = var3;
         this.d = var5;
         this.a = var4;
         this.b = new b4(var1);
      }
   }
}
