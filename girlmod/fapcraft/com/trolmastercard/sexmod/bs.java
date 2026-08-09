package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class bs {
   public static final int d = 30;
   BlockPos a;
   a c;
   HashSet<BlockPos> b;
   List<ff> f = new ArrayList();
   EnumFacing e;

   public bs(BlockPos var1, a var2, HashSet<BlockPos> var3) {
      this.e = EnumFacing.NORTH;
      this.a = var1;
      this.c = var2;
      this.b = var3;
   }

   public bs(BlockPos var1, a var2, HashSet<BlockPos> var3, EnumFacing var4) {
      this.e = EnumFacing.NORTH;
      this.a = var1;
      this.c = var2;
      this.b = var3;
      this.e = var4;
   }

   public EnumFacing f() {
      return this.e;
   }

   public BlockPos b() {
      return this.a;
   }

   public a d() {
      return this.c;
   }

   public HashSet<BlockPos> g() {
      return this.b;
   }

   public void b(BlockPos var1) {
      this.b.add(var1);
   }

   public void a(HashSet<BlockPos> var1) {
      this.b.addAll(var1);
   }

   public void a(BlockPos var1) {
      this.b.remove(var1);
   }

   public void b(HashSet<BlockPos> var1) {
      try {
         if (!var1.isEmpty()) {
            this.b.removeAll(var1);
         }

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   public boolean c(BlockPos var1) {
      return this.b.contains(var1);
   }

   public boolean a(ff var1) {
      try {
         if (this.c.a <= this.f.size()) {
            return false;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      this.f.add(var1);
      return true;
   }

   public List<ff> c() {
      return this.f;
   }

   public void a() {
      for(ff var2 : this.f) {
         try {
            if (var2.ae() == null) {
               var2.func_189654_d(false);
               var2.field_70145_X = false;
               var2.b(fp.NULL);
               var2.func_184212_Q().func_187227_b(em.G, false);
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }
      }

      this.f.clear();
   }

   public void c(ff var1) {
      this.f.remove(var1);
   }

   public boolean e() {
      boolean var10000;
      try {
         if (this.c.a <= this.f.size()) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      var10000 = false;
      return var10000;
   }

   public boolean b(ff var1) {
      return this.f.contains(var1);
   }

   public static HashSet<BlockPos> a(World param0, BlockPos param1, UUID param2) {
      // $FF: Couldn't be decompiled
   }

   static boolean b(World var0, BlockPos var1) {
      Block var2 = var0.func_180495_p(var1.func_177984_a()).func_177230_c();

      boolean var10000;
      try {
         if (!(var2 instanceof BlockLog)) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var10000 = false;
      return var10000;
   }

   static boolean c(World param0, BlockPos param1) {
      // $FF: Couldn't be decompiled
   }

   static HashSet<BlockPos> a(World var0, BlockPos var1) {
      return a(var0, var1, new HashSet());
   }

   static HashSet<BlockPos> a(World var0, BlockPos var1, HashSet<BlockPos> var2) {
      try {
         if (var2.contains(var1)) {
            return new HashSet();
         }
      } catch (RuntimeException var20) {
         throw a(var20);
      }

      try {
         var2.add(var1);
         if (var0.func_180495_p(var1.func_177982_a(1, 0, 0)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(1, 0, 0), var2));
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(-1, 0, 0)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(-1, 0, 0), var2));
         }
      } catch (RuntimeException var19) {
         throw a(var19);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(0, 0, 1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(0, 0, 1), var2));
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(0, 0, -1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(0, 0, -1), var2));
         }
      } catch (RuntimeException var18) {
         throw a(var18);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(1, 0, 1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(1, 0, 1), var2));
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(-1, 0, -1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(-1, 0, -1), var2));
         }
      } catch (RuntimeException var17) {
         throw a(var17);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(-1, 0, 1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(-1, 0, 1), var2));
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(1, 0, -1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(1, 0, -1), var2));
         }
      } catch (RuntimeException var16) {
         throw a(var16);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(0, 1, 0)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(0, 1, 0), var2));
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(1, 1, 0)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(1, 1, 0), var2));
         }
      } catch (RuntimeException var15) {
         throw a(var15);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(-1, 1, 0)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(-1, 1, 0), var2));
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(0, 1, 1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(0, 1, 1), var2));
         }
      } catch (RuntimeException var14) {
         throw a(var14);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(0, 1, -1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(0, 1, -1), var2));
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(1, 1, 1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(1, 1, 1), var2));
         }
      } catch (RuntimeException var13) {
         throw a(var13);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(-1, 1, -1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(-1, 1, -1), var2));
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(-1, 1, 1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(-1, 1, 1), var2));
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      try {
         if (var0.func_180495_p(var1.func_177982_a(1, 1, -1)).func_177230_c() instanceof BlockLog) {
            var2.addAll(a(var0, var1.func_177982_a(1, 1, -1), var2));
         }

         return var2;
      } catch (RuntimeException var3) {
         throw a(var3);
      }
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static enum a {
      FALL_TREE(1),
      MINE(3);

      int a;

      private a(int var3) {
         this.a = var3;
      }

      int a() {
         return this.a;
      }
   }
}
