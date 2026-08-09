package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ax {
   static final int d = 4;
   private static final HashMap<UUID, a> c = new HashMap();
   static final Vec3d[] b = new Vec3d[]{new Vec3d((double)0.0F, (double)0.0F, (double)0.0F), new Vec3d((double)0.5F, (double)0.0F, (double)0.0F), new Vec3d((double)-0.5F, (double)0.0F, (double)0.0F), new Vec3d((double)0.0F, (double)0.0F, (double)0.5F), new Vec3d((double)0.0F, (double)0.0F, (double)-0.5F)};
   static HashMap<ff, BlockPos[]> a = new HashMap();

   public static void a() {
      c.clear();
      a.clear();
   }

   public static void a(World var0, Vec3d var1) {
      UUID var2 = UUID.randomUUID();
      float[] var3 = new float[4];
      var3[0] = 0.25F;
      int var4 = 1;

      try {
         while(var4 < var3.length) {
            var3[var4] = ff.j();
            ++var4;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      ArrayList var11 = new ArrayList();

      for(float var8 : var3) {
         ff var9 = ff.a(var0, var2, var8);
         var11.add(var9);
      }

      EyeAndKoboldColor var12 = EyeAndKoboldColor.values()[r.f.nextInt(EyeAndKoboldColor.values().length)];
      a var13 = new a(var2, var12, (ff)var11.get(0), var11);
      c.put(var2, var13);
      int var14 = 0;

      for(ff var16 : var11) {
         var16.func_70107_b(var1.field_72450_a + b[var14].field_72450_a, var1.field_72448_b, var1.field_72449_c + b[var14].field_72449_c);
         var0.func_72838_d(var16);
         ++var14;
      }

   }

   public static boolean o(UUID var0) {
      boolean var10000;
      try {
         if (c.get(var0) != null) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      var10000 = false;
      return var10000;
   }

   public static void a(UUID var0, UUID var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.a(var1);
   }

   public static void a(UUID var0, EyeAndKoboldColor var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 != null) {
            System.out.println("tribe of UUID " + var0.toString() + " does already exist lol");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      c.put(var0, new a(var0, var1));
   }

   public static boolean a(BlockPos var0) {
      for(Map.Entry var2 : a.entrySet()) {
         BlockPos[] var3 = (BlockPos[])var2.getValue();

         try {
            if (var3[0].equals(var0)) {
               return true;
            }
         } catch (RuntimeException var5) {
            throw a(var5);
         }

         try {
            if (var3[1].equals(var0)) {
               return true;
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }
      }

      return false;
   }

   public static BlockPos[] a(ff var0) {
      return (BlockPos[])a.get(var0);
   }

   public static void a(ff var0, BlockPos var1) {
      World var2 = var0.field_70170_p;
      BlockPos var3 = null;
      if (var2.func_180495_p(var1.func_177978_c()).func_177230_c() instanceof BlockBed) {
         var3 = var1.func_177978_c();
      }

      if (var2.func_180495_p(var1.func_177974_f()).func_177230_c() instanceof BlockBed) {
         var3 = var1.func_177974_f();
      }

      if (var2.func_180495_p(var1.func_177968_d()).func_177230_c() instanceof BlockBed) {
         var3 = var1.func_177968_d();
      }

      if (var2.func_180495_p(var1.func_177976_e()).func_177230_c() instanceof BlockBed) {
         var3 = var1.func_177976_e();
      }

      try {
         if (var3 == null) {
            System.out.println("bed @" + var1.toString() + " apparently doesn't have another half.. wtf");
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      a.put(var0, new BlockPos[]{var1, var3});
   }

   public static void b(ff var0) {
      a.remove(var0);
   }

   public static void d(UUID var0, ff var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.g = var1;
   }

   public static void c(UUID var0, ff var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         var2.a(var1);
         c.replace(var0, var2);
         var1.func_184212_Q().func_187227_b(ff.aL, Optional.of(var0));
         if (!var1.aA) {
            var1.func_184212_Q().func_187227_b(ff.N, var2.h.toString());
         }

      } catch (RuntimeException var3) {
         throw a(var3);
      }
   }

   public static void k(UUID param0) {
      // $FF: Couldn't be decompiled
   }

   public static void a(UUID var0, ff var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      label76: {
         try {
            var2.b(var1);
            var2.b(var1.f());
            if (var2.g == null || var2.g.func_145782_y() != var1.func_145782_y()) {
               break label76;
            }
         } catch (RuntimeException var11) {
            throw a(var11);
         }

         ff var3 = var2.b();

         try {
            if (var3 != null) {
               var2.g = var3;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }
      }

      for(bs var4 : var2.f) {
         var4.c(var1);
      }

      try {
         if (!var2.a.isEmpty()) {
            c.replace(var0, var2);
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      try {
         if (!var1.J()) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      EntityPlayer var13 = var1.z();
      if (var13 != null) {
         HashSet var14 = new HashSet();
         var14.addAll(var2.i);
         var14.addAll(var2.b);

         for(bs var6 : var2.f) {
            var14.addAll(var6.b);
         }

         ge.b.sendTo(new h6(var14, false), (EntityPlayerMP)var13);
         var13.func_145747_a(new TextComponentString(String.format("ur %stribe %shas been %seradicated %suwu", TextFormatting.RED, TextFormatting.WHITE, TextFormatting.RED, TextFormatting.WHITE)));
      }

   }

   @Nullable
   public static ff f(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.g;
   }

   public static boolean e(UUID var0, ff var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return false;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (var2.g == null) {
            return false;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      boolean var10000;
      try {
         if (var2.g.func_145782_y() == var1.func_145782_y()) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      var10000 = false;
      return var10000;
   }

   public static EyeAndKoboldColor l(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return ff.aJ;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.h;
   }

   public static HashSet<BlockPos> j(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return new HashSet();
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.b;
   }

   public static void a(UUID var0, BlockPos var1) {
      try {
         if (var1 == null) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.b.add(var1);
   }

   public static void e(UUID var0, BlockPos var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.b.remove(var1);
   }

   public static HashSet<BlockPos> q(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.i;
   }

   public static void f(UUID var0, BlockPos var1) {
      try {
         if (var1 == null) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.i.add(var1);
   }

   public static void d(UUID var0, BlockPos var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.i.remove(var1);
   }

   public static HashSet<BlockPos> a(UUID var0, bs var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return new HashSet();
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (var1 != null) {
            var2.b(var1);
            return var1.b;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      return new HashSet();
   }

   public static HashSet<BlockPos> c(UUID var0, BlockPos var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return new HashSet();
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      bs var3 = null;

      for(bs var5 : var2.f) {
         if (var5.b.contains(var1)) {
            var3 = var5;
            break;
         }
      }

      return a(var0, var3);
   }

   public static void b(UUID var0, bs var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.a(var1);
   }

   public static void b(UUID var0, ff var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      bs var3 = null;

      for(bs var5 : var2.f) {
         if (var5.b(var1)) {
            var3 = var5;
         }
      }

      try {
         if (var3 == null) {
            System.out.println("task of worker " + var1.f() + " not found uwu");
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      var2.b(var3);
   }

   @Nullable
   public static Collection<bs> p(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.f;
   }

   public static fm i(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return fm.REST;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.e();
   }

   public static void a(UUID var0, fm var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.a(var1);
   }

   public static int h(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return 0;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.f();
   }

   public static List<ff> n(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return new ArrayList();
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.a;
   }

   public static void b(UUID var0, BlockPos var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.a(var1);
   }

   @Nullable
   public static BlockPos m(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.g();
   }

   public static HashSet<EntityLivingBase> e(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return new HashSet();
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.c();
   }

   public static void a(UUID var0, EntityLivingBase var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.a(var1);
   }

   public static void b(UUID var0, EntityLivingBase var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.b(var1);
   }

   public static boolean g(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return false;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      for(ff var3 : var1.a) {
         try {
            if (var3.ae() != null) {
               return true;
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }
      }

      return false;
   }

   public static boolean c(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return false;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return var1.c;
   }

   public static void a(UUID var0, boolean var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var2.c = var1;
   }

   @Nullable
   public static UUID a(UUID param0) {
      // $FF: Couldn't be decompiled
   }

   @Nullable
   public static UUID b(UUID var0) {
      a var1 = (a)c.get(var0);

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      List var2 = var1.a;

      try {
         if (var2.isEmpty()) {
            return null;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      ff var3 = (ff)var2.get(0);

      try {
         if (!var3.J()) {
            return null;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      String var4 = (String)((ff)var2.get(0)).func_184212_Q().func_187225_a(em.v);
      return UUID.fromString(var4);
   }

   public static HashSet<BlockPos> d(UUID var0) {
      a var1 = (a)c.get(var0);
      HashSet var2 = new HashSet();

      try {
         if (var1 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return var2;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      for(bs var4 : var1.f) {
         var2.addAll(var4.b);
      }

      var2.addAll(var1.i);
      var2.addAll(var1.b);
      return var2;
   }

   public static HashMap<UUID, BlockPos> a(UUID var0, World var1) {
      a var2 = (a)c.get(var0);

      try {
         if (var2 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return new HashMap();
         }
      } catch (RuntimeException var16) {
         throw a(var16);
      }

      HashMap var3 = var2.k;
      ArrayList var4 = new ArrayList();

      for(Map.Entry var6 : var3.entrySet()) {
         BlockPos var7 = (BlockPos)var6.getValue();
         UUID var8 = (UUID)var6.getKey();

         try {
            if (!var1.func_175697_a(var7, 5)) {
               continue;
            }
         } catch (RuntimeException var15) {
            throw a(var15);
         }

         AxisAlignedBB var9 = new AxisAlignedBB(var7.func_177973_b(new Vec3i(-3, -3, -3)), var7.func_177982_a(3, 3, 3));
         List var10 = var1.func_72872_a(ff.class, var9);
         boolean var11 = false;

         for(ff var13 : var10) {
            if (var8.equals(var13.f())) {
               var11 = true;
               break;
            }
         }

         try {
            if (!var11) {
               var4.add(var8);
            }
         } catch (RuntimeException var14) {
            throw a(var14);
         }
      }

      var2.k = var3;
      return var3;
   }

   public static void a(UUID var0, UUID var1, BlockPos var2) {
      a var3 = (a)c.get(var0);

      try {
         if (var3 == null) {
            System.out.println("tribe of UUID " + var0.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      var3.a(var1, var2);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a {
      UUID m;
      UUID e;
      ff g;
      List<ff> a;
      EyeAndKoboldColor h;
      fm d;
      BlockPos l;
      Collection<bs> f;
      HashSet<EntityLivingBase> j;
      HashSet<BlockPos> i;
      HashSet<BlockPos> b;
      HashMap<UUID, BlockPos> k;
      boolean c;

      public a(UUID var1, EyeAndKoboldColor var2, ff var3, List<ff> var4) {
         this.d = fm.REST;
         this.l = null;
         this.f = new ArrayList();
         this.j = new HashSet();
         this.i = new HashSet();
         this.b = new HashSet();
         this.k = new HashMap();
         this.c = false;
         this.m = var1;
         this.h = var2;
         this.g = var3;
         this.a = var4;
      }

      public a(UUID var1, EyeAndKoboldColor var2) {
         this.d = fm.REST;
         this.l = null;
         this.f = new ArrayList();
         this.j = new HashSet();
         this.i = new HashSet();
         this.b = new HashSet();
         this.k = new HashMap();
         this.c = false;
         this.m = var1;
         this.h = var2;
         this.a = new ArrayList();
      }

      public void a(UUID var1) {
         this.e = var1;
      }

      public UUID a() {
         return this.e;
      }

      public void b(bs param1) {
         // $FF: Couldn't be decompiled
      }

      public HashMap<UUID, BlockPos> d() {
         return this.k;
      }

      public void a(UUID var1, BlockPos var2) {
         this.k.put(var1, var2);
      }

      public void b(UUID var1) {
         this.k.remove(var1);
      }

      public void b(EntityLivingBase var1) {
         this.j.remove(var1);
      }

      public void a(EntityLivingBase var1) {
         this.j.add(var1);
      }

      public HashSet<EntityLivingBase> c() {
         return this.j;
      }

      public int f() {
         HashSet var1 = new HashSet();

         for(ff var3 : this.a) {
            var1.add(var3.f());
         }

         for(Map.Entry var5 : this.k.entrySet()) {
            var1.add(var5.getKey());
         }

         return var1.size();
      }

      public BlockPos g() {
         return this.l;
      }

      public void a(BlockPos var1) {
         this.l = var1;
      }

      public void a(bs var1) {
         this.f.add(var1);
      }

      public fm e() {
         return this.d;
      }

      public void a(fm var1) {
         this.d = var1;
      }

      public void a(ff var1) {
         try {
            if (this.a.contains(var1)) {
               return;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         UUID var2 = var1.f();
         ArrayList var3 = new ArrayList();

         for(ff var5 : this.a) {
            try {
               if (var5.f().equals(var2)) {
                  var3.add(var5);
               }
            } catch (RuntimeException var6) {
               throw a(var6);
            }
         }

         for(ff var9 : var3) {
            Main.LOGGER.warn(String.format("Removed old entry of kobold called %s with UUID %s owned by %s", var9.c(), var9.f(), this.e));
            this.b(var9);
         }

         this.a.add(var1);
      }

      public void b(ff var1) {
         this.a.remove(var1);
      }

      ff b() {
         ff var1 = null;

         for(ff var3 : this.a) {
            try {
               if (var3.field_70128_L) {
                  continue;
               }
            } catch (RuntimeException var6) {
               throw a(var6);
            }

            if (var1 == null) {
               var1 = var3;
            } else {
               float var4 = (Float)var1.func_184212_Q().func_187225_a(ff.aE);
               float var5 = (Float)var3.func_184212_Q().func_187225_a(ff.aE);
               if (var5 < var4) {
                  var1 = var3;
               }
            }
         }

         return var1;
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }

   public static class b extends WorldSavedData {
      public b(String var1) {
         super(var1);
      }

      @SubscribeEvent
      public void a(WorldEvent.Save var1) {
         World var2 = var1.getWorld();
         var2.func_175693_T().func_75745_a("tribes", this);
         this.func_76185_a();
      }

      @SubscribeEvent
      public void a(WorldEvent.Load var1) {
         World var2 = var1.getWorld();
         var2.func_175693_T().func_75742_a(b.class, "tribes");
      }

      @SubscribeEvent
      public void a(PlayerSleepInBedEvent var1) {
         try {
            if (ax.a(var1.getPos())) {
               var1.setResult(SleepResult.OTHER_PROBLEM);
            }

         } catch (RuntimeException var2) {
            throw a(var2);
         }
      }

      @SubscribeEvent
      public void a(BlockEvent.PlaceEvent var1) {
         BlockPos var2 = var1.getPos();
         IBlockState var3 = var1.getState();
         World var4 = var1.getWorld();

         try {
            if (var4.field_72995_K) {
               return;
            }
         } catch (RuntimeException var12) {
            throw a(var12);
         }

         try {
            if (!(var3.func_177230_c() instanceof BlockChest)) {
               return;
            }
         } catch (RuntimeException var21) {
            throw a(var21);
         }

         BlockChest.Type var5 = ((BlockChest)var4.func_180495_p(var2).func_177230_c()).field_149956_a;
         BlockPos var6 = null;

         label115: {
            try {
               if (!(var4.func_180495_p(var2.func_177978_c()).func_177230_c() instanceof BlockChest) || !var5.equals(((BlockChest)var4.func_180495_p(var2.func_177978_c()).func_177230_c()).field_149956_a)) {
                  break label115;
               }
            } catch (RuntimeException var20) {
               throw a(var20);
            }

            var6 = var2.func_177978_c();
         }

         label108: {
            try {
               if (!(var4.func_180495_p(var2.func_177974_f()).func_177230_c() instanceof BlockChest) || !var5.equals(((BlockChest)var4.func_180495_p(var2.func_177974_f()).func_177230_c()).field_149956_a)) {
                  break label108;
               }
            } catch (RuntimeException var19) {
               throw a(var19);
            }

            var6 = var2.func_177974_f();
         }

         label101: {
            try {
               if (!(var4.func_180495_p(var2.func_177968_d()).func_177230_c() instanceof BlockChest) || !var5.equals(((BlockChest)var4.func_180495_p(var2.func_177968_d()).func_177230_c()).field_149956_a)) {
                  break label101;
               }
            } catch (RuntimeException var18) {
               throw a(var18);
            }

            var6 = var2.func_177968_d();
         }

         label94: {
            try {
               if (!(var4.func_180495_p(var2.func_177976_e()).func_177230_c() instanceof BlockChest) || !var5.equals(((BlockChest)var4.func_180495_p(var2.func_177976_e()).func_177230_c()).field_149956_a)) {
                  break label94;
               }
            } catch (RuntimeException var17) {
               throw a(var17);
            }

            var6 = var2.func_177976_e();
         }

         try {
            if (var6 == null) {
               return;
            }
         } catch (RuntimeException var16) {
            throw a(var16);
         }

         for(Map.Entry var8 : ax.c.entrySet()) {
            a var9 = (a)var8.getValue();

            try {
               if (!var9.i.contains(var6)) {
                  continue;
               }
            } catch (RuntimeException var15) {
               throw a(var15);
            }

            var9.i.add(var2);
            UUID var10 = ax.b((UUID)var8.getKey());

            try {
               if (var10 == null) {
                  continue;
               }
            } catch (RuntimeException var14) {
               throw a(var14);
            }

            EntityPlayerMP var11 = (EntityPlayerMP)var4.func_152378_a(var10);

            try {
               if (var11 == null) {
                  continue;
               }
            } catch (RuntimeException var13) {
               throw a(var13);
            }

            ge.b.sendTo(new h6(var2, true), var11);
         }

      }

      @SubscribeEvent
      public void a(EntityJoinWorldEvent var1) {
         Entity var2 = var1.getEntity();
         if (var2 instanceof EntityZombie) {
            EntityZombie var3 = (EntityZombie)var2;
            var3.field_70715_bh.func_75776_a(3, new aa(var3, true, false));
         }

         if (var2 instanceof AbstractSkeleton) {
            AbstractSkeleton var4 = (AbstractSkeleton)var2;
            var4.field_70715_bh.func_75776_a(3, new aa(var4, true, false));
         }

         if (var2 instanceof EntitySpider) {
            EntitySpider var5 = (EntitySpider)var2;
            var5.field_70715_bh.func_75776_a(3, new aa(var5, true, true));
         }

      }

      @SubscribeEvent
      public void a(BlockEvent.BreakEvent var1) {
         BlockPos var2 = var1.getPos();
         World var3 = var1.getWorld();

         try {
            if (var3.field_72995_K) {
               return;
            }
         } catch (RuntimeException var13) {
            throw a(var13);
         }

         IBlockState var4 = var3.func_180495_p(var2);
         Block var5 = var4.func_177230_c();
         if (var5 instanceof BlockChest) {
            for(Map.Entry var7 : ax.c.entrySet()) {
               a var8 = (a)var7.getValue();

               try {
                  if (!var8.i.contains(var2)) {
                     continue;
                  }
               } catch (RuntimeException var19) {
                  throw a(var19);
               }

               var8.i.remove(var2);
               UUID var9 = ax.b((UUID)var7.getKey());

               try {
                  if (var9 == null) {
                     continue;
                  }
               } catch (RuntimeException var18) {
                  throw a(var18);
               }

               EntityPlayerMP var10 = (EntityPlayerMP)var3.func_152378_a(var9);

               try {
                  if (var10 == null) {
                     continue;
                  }
               } catch (RuntimeException var17) {
                  throw a(var17);
               }

               ge.b.sendTo(new h6(var2, false), var10);
            }
         }

         if (var5 instanceof BlockBed) {
            for(Map.Entry var21 : ax.c.entrySet()) {
               a var22 = (a)var21.getValue();

               try {
                  if (!var22.b.contains(var2)) {
                     continue;
                  }
               } catch (RuntimeException var16) {
                  throw a(var16);
               }

               BlockPos var23 = cj.a(var2, var4);
               var22.b.remove(var2);
               var22.b.remove(var23);
               UUID var24 = ax.b((UUID)var21.getKey());

               try {
                  if (var24 == null) {
                     continue;
                  }
               } catch (RuntimeException var15) {
                  throw a(var15);
               }

               EntityPlayerMP var11 = (EntityPlayerMP)var3.func_152378_a(var24);

               try {
                  if (var11 == null) {
                     continue;
                  }
               } catch (RuntimeException var14) {
                  throw a(var14);
               }

               HashSet var12 = new HashSet();
               var12.add(var2);
               var12.add(var23);
               ge.b.sendTo(new h6(var12, false), var11);
            }
         }

      }

      String a(String var1, NBTTagCompound var2) {
         String var3 = var2.func_74779_i(var1);
         var2.func_74778_a(var1, "");
         return var3;
      }

      public void func_76184_a(NBTTagCompound var1) {
         int var2 = 0;

         while(true) {
            String var3 = this.a("tribeId" + var2, var1);

            try {
               if ("".equals(var3)) {
                  return;
               }
            } catch (RuntimeException var29) {
               throw a(var29);
            }

            UUID var4 = UUID.fromString(var3);
            EyeAndKoboldColor var5 = EyeAndKoboldColor.valueOf(this.a("tribeColor" + var2, var1));
            ax.a(var4, var5);
            String var6 = this.a("tribeMaster" + var2, var1);

            try {
               if (!"".equals(var6)) {
                  ax.a(var4, UUID.fromString(var6));
               }
            } catch (RuntimeException var28) {
               throw a(var28);
            }

            int var7 = 0;

            while(true) {
               String var8 = this.a(var4.toString() + "member" + var7 + "pos", var1);

               try {
                  if ("".equals(var8)) {
                     break;
                  }
               } catch (RuntimeException var27) {
                  throw a(var27);
               }

               String var9 = this.a(var4.toString() + "member" + var7 + "id", var1);

               try {
                  if ("".equals(var9)) {
                     break;
                  }
               } catch (RuntimeException var26) {
                  throw a(var26);
               }

               String[] var10 = var8.split("\\|");
               BlockPos var11 = new BlockPos(Integer.parseInt(var10[0]), Integer.parseInt(var10[1]), Integer.parseInt(var10[2]));
               UUID var12 = UUID.fromString(var9);
               ax.a(var4, var12, var11);
               ++var7;
            }

            int var30 = 0;

            while(true) {
               String var31 = this.a(var4.toString() + "bed" + var30, var1);

               try {
                  if ("".equals(var31)) {
                     break;
                  }
               } catch (RuntimeException var25) {
                  throw a(var25);
               }

               String[] var33 = var31.split("\\|");
               BlockPos var36 = new BlockPos(Integer.parseInt(var33[0]), Integer.parseInt(var33[1]), Integer.parseInt(var33[2]));
               ax.a(var4, var36);
               ++var30;
            }

            int var32 = 0;

            while(true) {
               String var34 = this.a(var4.toString() + "chest" + var32, var1);

               try {
                  if ("".equals(var34)) {
                     break;
                  }
               } catch (RuntimeException var24) {
                  throw a(var24);
               }

               String[] var37 = var34.split("\\|");
               BlockPos var39 = new BlockPos(Integer.parseInt(var37[0]), Integer.parseInt(var37[1]), Integer.parseInt(var37[2]));
               ax.f(var4, var39);
               ++var32;
            }

            int var35 = 0;

            while(true) {
               String var38 = this.a(var4.toString() + var35 + "taskKind", var1);

               try {
                  if ("".equals(var38)) {
                     break;
                  }
               } catch (RuntimeException var22) {
                  throw a(var22);
               }

               String var40 = this.a(var4.toString() + var35 + "facing", var1);
               EnumFacing var13 = EnumFacing.NORTH;
               if (!"".equals(var40)) {
                  var13 = EnumFacing.func_176739_a(var40);
               }

               String var14 = this.a(var4.toString() + var35 + "pos", var1);
               String[] var15 = var14.split("\\|");
               BlockPos var16 = new BlockPos(Integer.parseInt(var15[0]), Integer.parseInt(var15[1]), Integer.parseInt(var15[2]));
               HashSet var17 = new HashSet();
               int var18 = 0;

               while(true) {
                  String var19 = this.a(var4.toString() + var35 + "block" + var18, var1);

                  try {
                     if ("".equals(var19)) {
                        break;
                     }
                  } catch (RuntimeException var23) {
                     throw a(var23);
                  }

                  String[] var20 = var19.split("\\|");
                  BlockPos var21 = new BlockPos(Integer.parseInt(var20[0]), Integer.parseInt(var20[1]), Integer.parseInt(var20[2]));
                  var17.add(var21);
                  ++var18;
               }

               ax.b(var4, new bs(var16, bs.a.valueOf(var38), var17, var13));
               ++var35;
            }

            ++var2;
         }
      }

      public NBTTagCompound func_189551_b(NBTTagCompound var1) {
         int var2 = 0;

         for(Map.Entry var4 : ax.c.entrySet()) {
            a var5 = (a)var4.getValue();
            UUID var6 = (UUID)var4.getKey();
            UUID var7 = var5.a();

            try {
               var1.func_74778_a("tribeId" + var2, var6.toString());
               var1.func_74778_a("tribeColor" + var2, var5.h.toString());
               if (var7 != null) {
                  var1.func_74778_a("tribeMaster" + var2, var7.toString());
               }
            } catch (RuntimeException var20) {
               throw a(var20);
            }

            int var8 = 0;
            HashSet var9 = new HashSet();

            for(ff var11 : var5.a) {
               try {
                  if (var11.field_70128_L) {
                     continue;
                  }
               } catch (RuntimeException var19) {
                  throw a(var19);
               }

               BlockPos var12 = var11.func_180425_c();
               UUID var13 = var11.f();
               var1.func_74778_a(var6.toString() + "member" + var8 + "pos", var12.func_177958_n() + "|" + var12.func_177956_o() + "|" + var12.func_177952_p());
               var1.func_74778_a(var6.toString() + "member" + var8 + "id", var13.toString());
               var9.add(var13);
               ++var8;
            }

            for(Map.Entry var23 : var5.k.entrySet()) {
               UUID var26 = (UUID)var23.getKey();
               BlockPos var30 = (BlockPos)var23.getValue();

               try {
                  if (var9.contains(var26)) {
                     continue;
                  }
               } catch (RuntimeException var18) {
                  throw a(var18);
               }

               var1.func_74778_a(var6.toString() + "member" + var8 + "pos", var30.func_177958_n() + "|" + var30.func_177956_o() + "|" + var30.func_177952_p());
               var1.func_74778_a(var6.toString() + "member" + var8 + "id", var26.toString());
               var9.add(var26);
               ++var8;
            }

            int var22 = 0;

            for(BlockPos var27 : var5.b) {
               var1.func_74778_a(var6.toString() + "bed" + var22, var27.func_177958_n() + "|" + var27.func_177956_o() + "|" + var27.func_177952_p());
               ++var22;
            }

            int var25 = 0;

            for(BlockPos var31 : var5.i) {
               var1.func_74778_a(var6.toString() + "chest" + var25, var31.func_177958_n() + "|" + var31.func_177956_o() + "|" + var31.func_177952_p());
               ++var25;
            }

            int var29 = 0;

            for(bs var14 : var5.f) {
               var1.func_74778_a(var6.toString() + var29 + "taskKind", var14.c.toString());
               var1.func_74778_a(var6.toString() + var29 + "pos", var14.a.func_177958_n() + "|" + var14.a.func_177956_o() + "|" + var14.a.func_177952_p());
               var1.func_74778_a(var6.toString() + var29 + "facing", var14.e.func_176610_l());
               int var15 = 0;

               for(BlockPos var17 : var14.b) {
                  var1.func_74778_a(var6.toString() + var29 + "block" + var15, var17.func_177958_n() + "|" + var17.func_177956_o() + "|" + var17.func_177952_p());
                  ++var15;
               }

               ++var29;
            }

            ++var2;
         }

         return var1;
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
