package com.trolmastercard.sexmod;

import com.mojang.realmsclient.util.Pair;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public abstract class em extends EntityCreature implements IAnimatable {
   public static int j = 22;
   protected static final long t = 20L;
   private final AnimationFactory g;
   public EntityAIWanderAvoidWater z;
   public df o;
   public static HashSet<em> k = new HashSet();
   public Vec3d B;
   protected float r;
   protected EntityDataManager m;
   public PathNavigate f;
   public Vec3d l;
   public EntityEnderPearl q;
   public float n;
   public boolean F;
   private boolean i;
   HashMap<String, Vec3d> x;
   public static final DataParameter<String> v;
   public static final DataParameter<Boolean> G;
   public static final DataParameter<String> e;
   public static final DataParameter<Float> w;
   public static final DataParameter<String> u;
   public static final DataParameter<Integer> D;
   public static final DataParameter<String> J;
   public static final DataParameter<String> h;
   public static final DataParameter<String> y;
   public static final DataParameter<String> a;
   public static final DataParameter<String> b;
   public static final DataParameter<String> c;
   protected static final List<Item> I;
   public AnimationController C;
   public AnimationController E;
   public AnimationController s;
   HashMap<String, Pair<Integer, Integer>> A;
   AnimationProcessor<?> H;
   public List<String> p;
   protected List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> d;

   public void a(a var1) {
      this.m.func_187227_b(a, var1.toString());
   }

   public a q() {
      return em.a.valueOf((String)this.m.func_187225_a(a));
   }

   @SideOnly(Side.CLIENT)
   protected void a(String var1, String var2) {
      ge.b.sendToServer(new n(this.f(), var1, var2));
   }

   public UUID f() {
      try {
         return UUID.fromString((String)this.m.func_187225_a(u));
      } catch (Exception var3) {
         UUID var2 = UUID.randomUUID();
         this.m.func_187227_b(u, var2.toString());
         return var2;
      }
   }

   public fp y() {
      return fp.valueOf((String)this.m.func_187225_a(J));
   }

   public void b(fp param1) {
      // $FF: Couldn't be decompiled
   }

   public int ah() {
      return (Integer)this.m.func_187225_a(D);
   }

   public void f(int var1) {
      try {
         if (this.field_70170_p.field_72995_K) {
            this.a("currentModel", "0");
            return;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      this.m.func_187227_b(D, var1);
   }

   public boolean m() {
      return false;
   }

   @Nullable
   public EntityPlayer S() {
      UUID var1 = this.ae();

      try {
         if (var1 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      return this.field_70170_p.func_152378_a(var1);
   }

   public static void a(em var0, String var1) {
      for(EntityPlayer var3 : cj.a((Entity)var0)) {
         var3.func_145747_a(new TextComponentString(var1));
      }

   }

   public static void a(em var0, SoundEvent var1, boolean var2) {
      Vec3d var3 = var0.func_174791_d();

      for(EntityPlayer var5 : cj.a((Entity)var0)) {
         Vec3d var6;
         if (!var2) {
            var6 = var3;
         } else {
            Vec3d var7 = var5.func_174791_d();
            Vec3d var8 = var3.func_178788_d(var7).func_72432_b();
            var6 = var7.func_178787_e(var8);
         }

         ((EntityPlayerMP)var5).field_71135_a.func_147359_a(new SPacketSoundEffect(var1, SoundCategory.AMBIENT, var6.field_72450_a, var6.field_72448_b, var6.field_72449_c, 1.0F, 1.0F));
      }

   }

   public static void a(em var0, SoundEvent var1) {
      a(var0, var1, false);
   }

   public static void a(em var0, SoundEvent[] var1) {
      a(var0, com.trolmastercard.sexmod.c.a(var1));
   }

   public static void a(em var0, SoundEvent[] var1, boolean var2) {
      a(var0, com.trolmastercard.sexmod.c.a(var1), var2);
   }

   @SideOnly(Side.CLIENT)
   public Vec3d A() {
      Vec3d var1 = Minecraft.func_71410_x().field_71439_g.func_174791_d();
      Vec3d var2 = this.func_174791_d();
      Vec3d var3 = var2.func_178788_d(var1).func_72432_b();
      return var1.func_178787_e(var3);
   }

   @Nullable
   public UUID ae() {
      String var1 = (String)this.m.func_187225_a(y);

      try {
         if (var1.equals("null")) {
            return null;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      return UUID.fromString(var1);
   }

   public void e(UUID param1) {
      // $FF: Couldn't be decompiled
   }

   public void a(@Nonnull EntityPlayer var1) {
      this.e(var1.getPersistentID());
   }

   public Vec3d o() {
      String[] var1 = ((String)this.m.func_187225_a(e)).split("\\|");
      return new Vec3d(Double.parseDouble(var1[0]), Double.parseDouble(var1[1]), Double.parseDouble(var1[2]));
   }

   public void c(Vec3d var1) {
      if (this.field_70170_p.field_72995_K) {
         String var2 = var1.field_72450_a + "f" + var1.field_72448_b + "f" + var1.field_72449_c + "f";
         this.a("targetPos", var2);
      } else {
         this.m.func_187227_b(e, var1.field_72450_a + "|" + var1.field_72448_b + "|" + var1.field_72449_c);
      }
   }

   public void a(Vec3d var1) {
      this.m.func_187227_b(e, var1.field_72450_a + "|" + var1.field_72448_b + "|" + var1.field_72449_c);
   }

   public Float I() {
      return (Float)this.m.func_187225_a(w);
   }

   public void b(float var1) {
      this.m.func_187227_b(w, var1);
   }

   public void a(boolean var1) {
      try {
         if (this.field_70170_p.field_72995_K) {
            this.a("shouldbeattargetpos", String.valueOf(var1));
            return;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      this.m.func_187227_b(G, var1);
   }

   public boolean Q() {
      return (Boolean)this.m.func_187225_a(G);
   }

   protected boolean func_70692_ba() {
      return false;
   }

   protected em(World param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   protected void p() {
      this.C = new AnimationController(this, "action", 0.0F, this::a);
      this.E = new AnimationController(this, "movement", 5.0F, this::a);
      this.s = new AnimationController(this, "eyes", 10.0F, this::a);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.f = this.func_70661_as();
      this.m = this.func_184212_Q();
      this.m.func_187214_a(u, UUID.randomUUID().toString());
      this.m.func_187214_a(D, 1);
      this.m.func_187214_a(J, fp.NULL.toString());
      this.m.func_187214_a(h, "");
      this.m.func_187214_a(y, "null");
      this.m.func_187214_a(G, false);
      this.m.func_187214_a(w, 0.0F);
      this.m.func_187214_a(e, "0|0|0");
      this.m.func_187214_a(v, "");
      this.m.func_187214_a(a, em.a.WALK.toString());
      this.m.func_187214_a(b, "");
      this.m.func_187214_a(c, "");
   }

   public void b(boolean var1) {
      try {
         this.i = var1;
         if (var1) {
            fs.b(this);
            return;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      fs.a(this);
   }

   public boolean h() {
      return this.i;
   }

   public static List<em> ad() {
      try {
         if (!g0.a()) {
            return Z();
         }
      } catch (ConcurrentModificationException var7) {
         throw c((RuntimeException)var7);
      }

      WorldServer[] var0 = FMLCommonHandler.instance().getMinecraftServerInstance().field_71305_c;

      try {
         if (var0.length == 0) {
            return new ArrayList();
         }
      } catch (ConcurrentModificationException var6) {
         throw c((RuntimeException)var6);
      }

      ArrayList var1 = new ArrayList();

      for(WorldServer var5 : var0) {
         var1.addAll(((World)var5).func_175644_a(em.class, (var0x) -> true));
      }

      return var1;
   }

   @SideOnly(Side.CLIENT)
   private static List<em> Z() {
      WorldClient var0 = Minecraft.func_71410_x().field_71441_e;

      try {
         if (var0 == null) {
            return new ArrayList();
         }
      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }

      return ((World)var0).func_175644_a(em.class, (var0x) -> true);
   }

   public boolean B() {
      return true;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)20.0F);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.5F);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a((double)30.0F);
   }

   protected void func_184651_r() {
      this.z = new EntityAIWanderAvoidWater(this, 0.35);
      this.o = new df(this, EntityPlayer.class, 3.0F, 1.0F);
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 0.4, false, new HashSet(I)));
      this.field_70714_bg.func_75776_a(3, new hz(this));
      this.field_70714_bg.func_75776_a(5, this.o);
      this.field_70714_bg.func_75776_a(5, this.z);
   }

   public void func_70014_b(NBTTagCompound var1) {
      var1.func_74780_a("homeX", this.l.field_72450_a);
      var1.func_74780_a("homeY", this.l.field_72448_b);
      var1.func_74780_a("homeZ", this.l.field_72449_c);
      var1.func_74778_a("girlID", (String)this.m.func_187225_a(u));
      String var2 = this.w();

      try {
         if (!"".equals(var2)) {
            var1.func_74778_a("sexmod:customname", var2);
         }
      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }

      try {
         if (this.X()) {
            var1.func_74778_a("sexmod:customModel", this.C());
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      super.func_70014_b(var1);
   }

   protected boolean X() {
      return a((Entity)this);
   }

   public void func_70037_a(NBTTagCompound var1) {
      super.func_70037_a(var1);
      this.l = new Vec3d(var1.func_74769_h("homeX"), var1.func_74769_h("homeY"), var1.func_74769_h("homeZ"));
      String var2 = var1.func_74779_i("sexmod:customname");

      try {
         if (!"".equals(var2)) {
            this.g(var2);
         }
      } catch (ConcurrentModificationException var15) {
         throw c((RuntimeException)var15);
      }

      String var3 = var1.func_74779_i("girlID");

      try {
         if ("".equals(var3)) {
            return;
         }
      } catch (ConcurrentModificationException var14) {
         throw c((RuntimeException)var14);
      }

      UUID var4 = UUID.fromString(var3);
      boolean var5 = false;

      for(em var7 : g(var4)) {
         try {
            if (var7.field_70170_p.field_72995_K) {
               continue;
            }
         } catch (ConcurrentModificationException var13) {
            throw c((RuntimeException)var13);
         }

         try {
            if (var7 == this) {
               continue;
            }
         } catch (ConcurrentModificationException var12) {
            throw c((RuntimeException)var12);
         }

         try {
            if (var7.field_70128_L) {
               continue;
            }
         } catch (ConcurrentModificationException var11) {
            throw c((RuntimeException)var11);
         }

         try {
            if (!var7.isAddedToWorld()) {
               continue;
            }
         } catch (ConcurrentModificationException var10) {
            throw c((RuntimeException)var10);
         }

         var5 = true;
         break;
      }

      try {
         if (var5) {
            Main.LOGGER.log(Level.WARN, String.format("got a duped %s with id '%s'. Deleted her", this.c(), var4));
            this.field_70170_p.func_72900_e(this);
            return;
         }
      } catch (ConcurrentModificationException var9) {
         throw c((RuntimeException)var9);
      }

      try {
         this.m.func_187227_b(u, var4.toString());
         if (this.X()) {
            this.f(var1.func_74779_i("sexmod:customModel"));
         }

      } catch (ConcurrentModificationException var8) {
         throw c((RuntimeException)var8);
      }
   }

   public boolean d() {
      return true;
   }

   public void func_70016_h(double var1, double var3, double var5) {
      this.field_70159_w = var1;
      this.field_70181_x = var3;
      this.field_70179_y = var5;
   }

   public void b(Vec3d var1) {
      this.field_70159_w = var1.field_72450_a;
      this.field_70181_x = var1.field_72448_b;
      this.field_70179_y = var1.field_72449_c;
   }

   public Vec3d j() {
      return new Vec3d(this.field_70142_S, this.field_70137_T, this.field_70136_U);
   }

   public void func_70619_bc() {
      try {
         if ((Boolean)this.m.func_187225_a(G)) {
            this.func_70034_d(this.I());
            this.func_70080_a(this.o().field_72450_a, this.o().field_72448_b, this.o().field_72449_c, this.I(), 0.0F);
            this.func_70101_b(this.I(), this.field_70125_A);
         }
      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }

      try {
         if (this.l.equals(Vec3d.field_186680_a)) {
            this.l = new Vec3d(this.func_180425_c());
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      this.G();
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.l();
   }

   protected void G() {
      try {
         if (!br.e) {
            return;
         }
      } catch (ConcurrentModificationException var13) {
         throw c((RuntimeException)var13);
      }

      HashSet var1 = this.Y();
      fy var2 = fy.a((Entity)this);
      HashSet var3 = new HashSet();
      String var4 = br.h();

      for(String var6 : var1) {
         try {
            if (!"".equals(br.a(var6, var4))) {
               var3.add(var6);
               continue;
            }
         } catch (ConcurrentModificationException var12) {
            throw c((RuntimeException)var12);
         }

         HashSet var7 = br.a(var6);

         try {
            if (var7 == null) {
               var3.add(var6);
               continue;
            }
         } catch (ConcurrentModificationException var11) {
            throw c((RuntimeException)var11);
         }

         try {
            if (var7.isEmpty()) {
               continue;
            }
         } catch (ConcurrentModificationException var10) {
            throw c((RuntimeException)var10);
         }

         try {
            if (!var7.contains(var2)) {
               var3.add(var6);
            }
         } catch (ConcurrentModificationException var8) {
            throw c((RuntimeException)var8);
         }
      }

      try {
         if (var3.isEmpty()) {
            return;
         }
      } catch (ConcurrentModificationException var9) {
         throw c((RuntimeException)var9);
      }

      var1.removeAll(var3);
      this.f(a(var1));
   }

   protected void l() {
      fp var1 = this.y();

      int[] var10000;
      byte var10001;
      label42: {
         try {
            var10000 = var1.ticksPlaying;
            if (this.field_70170_p.field_72995_K) {
               var10001 = 1;
               break label42;
            }
         } catch (ConcurrentModificationException var5) {
            throw c((RuntimeException)var5);
         }

         var10001 = 0;
      }

      try {
         if (++var10000[var10001] < var1.length) {
            return;
         }
      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }

      try {
         if (var1.followUp == null) {
            return;
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      try {
         if (!this.field_70170_p.field_72995_K) {
            this.b(var1.followUp);
         }

      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }
   }

   protected void k() {
      // $FF: Couldn't be decompiled
   }

   public void g() {
   }

   @SideOnly(Side.CLIENT)
   public boolean b(EntityPlayer var1) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   protected static void a(EntityPlayer var0, em var1) {
      Minecraft.func_71410_x().func_147108_a(new m(var1, var0));
   }

   @SideOnly(Side.CLIENT)
   protected static void a(EntityPlayer var0, em var1, String[] var2, ItemStack[] var3, boolean var4) {
      Minecraft.func_71410_x().func_147108_a(new m(var1, var0, var2, var3, var4));
   }

   @SideOnly(Side.CLIENT)
   protected static void a(EntityPlayer var0, em var1, String[] var2, boolean var3) {
      Minecraft.func_71410_x().func_147108_a(new m(var1, var0, var2, (ItemStack[])null, var3));
   }

   public void a(ItemStack var1) {
      this.field_184627_bm = var1;
   }

   public void d(int var1) {
      this.field_184628_bn = var1;
   }

   public Vec3d M() {
      return new Vec3d(this.field_70169_q, this.field_70167_r, this.field_70166_s);
   }

   protected static Vec3d a(em var0) {
      return new Vec3d(var0.field_70169_q, var0.field_70167_r, var0.field_70166_s);
   }

   public em af() {
      return this;
   }

   public void x() {
      try {
         if (this.field_70170_p.field_72995_K) {
            this.a("master", "");
            this.a("walk speed", em.a.WALK.toString());
            return;
         }
      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }

      this.m.func_187227_b(v, "");
      this.m.func_187227_b(a, em.a.WALK.toString());
   }

   protected void a(EntityPlayerMP var1, boolean var2) {
      var1.field_70159_w = (double)0.0F;
      var1.field_70181_x = (double)0.0F;
      var1.field_70179_y = (double)0.0F;
      if (var2) {
         Vec3d var3 = this.a(0.35);
         var1.func_70634_a(var3.field_72450_a, var3.field_72448_b, var3.field_72449_c);
      }

   }

   public void j(UUID var1) {
      EntityPlayer var2 = this.field_70170_p.func_152378_a(var1);
      var2.field_70159_w = (double)0.0F;
      var2.field_70181_x = (double)0.0F;
      var2.field_70179_y = (double)0.0F;
      Vec3d var3 = this.a(0.35);
      var2.func_70634_a(var3.field_72450_a, var3.field_72448_b, var3.field_72449_c);
      this.b(var2.field_70759_as + 180.0F);
   }

   protected void a(boolean var1, boolean var2, UUID var3) {
      try {
         if (this.field_70170_p.field_72995_K) {
            ge.b.sendToServer(new dc(this.f(), var3, var1, var2));
            return;
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      dc.a.a(this.f(), var3, var1, var2);
   }

   public static em b(UUID var0) {
      try {
         if (var0 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      for(em var2 : g(var0)) {
         try {
            if (var2.field_70170_p.field_72995_K) {
               return var2;
            }
         } catch (ConcurrentModificationException var3) {
            throw c((RuntimeException)var3);
         }
      }

      return null;
   }

   public static em a(UUID var0) {
      try {
         if (var0 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      for(em var2 : g(var0)) {
         try {
            if (!var2.field_70170_p.field_72995_K) {
               return var2;
            }
         } catch (ConcurrentModificationException var3) {
            throw c((RuntimeException)var3);
         }
      }

      return null;
   }

   public static ArrayList<em> g(UUID var0) {
      ArrayList var1 = new ArrayList();

      try {
         for(em var3 : ad()) {
            try {
               if (var3 == null) {
                  continue;
               }
            } catch (ConcurrentModificationException var5) {
               throw c((RuntimeException)var5);
            }

            try {
               if (var3.f().equals(var0)) {
                  var1.add(var3);
               }
            } catch (ConcurrentModificationException var4) {
               throw c((RuntimeException)var4);
            }
         }
      } catch (ConcurrentModificationException var6) {
         System.out.println("had a ConcurrentModificationException while cycling through the girl list... hopefully nothin borke owo");
         var6.printStackTrace();
      }

      return var1;
   }

   protected BlockPos a(BlockPos var1) {
      return this.a(var1, 1);
   }

   public BlockPos a(BlockPos var1, int var2) {
      return this.a(var1, var2, Blocks.field_150324_C, 22, 3, (HashSet)null);
   }

   public void W() {
      this.m.func_187227_b(field_184621_as, Byte.valueOf("1"));
   }

   public void K() {
      this.m.func_187227_b(field_184621_as, Byte.valueOf("0"));
   }

   public BlockPos a(BlockPos param1, int param2, Block param3, int param4, int param5, @Nullable HashSet<Biome> param6) {
      // $FF: Couldn't be decompiled
   }

   protected List<BlockPos> a(BlockPos param1, Class param2, int param3, int param4, @Nullable HashSet<Biome> param5) {
      // $FF: Couldn't be decompiled
   }

   public boolean J() {
      boolean var10000;
      try {
         if (!((String)this.m.func_187225_a(v)).equals("")) {
            var10000 = true;
            return var10000;
         }
      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }

      var10000 = false;
      return var10000;
   }

   @Nullable
   public UUID O() {
      String var1 = (String)this.m.func_187225_a(v);

      try {
         if ("".equals(var1)) {
            return null;
         }
      } catch (IllegalArgumentException var4) {
         throw c((RuntimeException)var4);
      }

      try {
         return UUID.fromString(var1);
      } catch (IllegalArgumentException var3) {
         return null;
      }
   }

   @Nullable
   public EntityPlayer z() {
      UUID var1 = this.O();

      try {
         if (var1 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      return this.field_70170_p.func_152378_a(var1);
   }

   protected ResourceLocation func_184647_J() {
      return dz.d;
   }

   @SideOnly(Side.CLIENT)
   public void a(String var1, UUID var2) {
   }

   @SideOnly(Side.CLIENT)
   protected abstract <E extends IAnimatable> PlayState a(AnimationEvent<E> var1);

   @SideOnly(Side.CLIENT)
   protected boolean a(fp var1, String var2, boolean var3, AnimationEvent var4) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   protected void a(String param1, boolean param2, AnimationEvent param3, boolean param4) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   protected void a(String var1, boolean var2, AnimationEvent var3) {
      this.a(var1, var2, var3, false);
   }

   @SideOnly(Side.CLIENT)
   protected void a(String param1, int param2, float param3, AnimationEvent param4, boolean param5) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   protected void a(String var1, int var2, float var3, AnimationEvent var4) {
      this.a(var1, var2, var3, var4, false);
   }

   int a(int var1, int var2, int var3, float var4) {
      try {
         if (var1 != 0) {
            return 0;
         }
      } catch (ConcurrentModificationException var11) {
         throw c((RuntimeException)var11);
      }

      Random var5 = this.func_70681_au();

      try {
         if (var5.nextFloat() > var4) {
            return 0;
         }
      } catch (ConcurrentModificationException var7) {
         throw c((RuntimeException)var7);
      }

      int var6;
      ConcurrentModificationException var10000;
      while(true) {
         var6 = var5.nextInt(var3);
         if (var6 != var2) {
            try {
               if (var6 != 0) {
                  break;
               }
            } catch (ConcurrentModificationException var10) {
               var10000 = var10;
               boolean var10001 = false;
               throw c((RuntimeException)var10000);
            }
         }

         try {
            if (var3 > 2) {
               continue;
            }
            break;
         } catch (ConcurrentModificationException var9) {
            var10000 = var9;
            boolean var12 = false;
            throw c((RuntimeException)var10000);
         }
      }

      try {
         return var6;
      } catch (ConcurrentModificationException var8) {
         var10000 = var8;
         boolean var13 = false;
         throw c((RuntimeException)var10000);
      }
   }

   @SideOnly(Side.CLIENT)
   public abstract void registerControllers(AnimationData var1);

   protected void s() {
      // $FF: Couldn't be decompiled
   }

   public static em c(EntityPlayer var0) {
      try {
         if (var0 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }

      return i(var0.getPersistentID());
   }

   @SideOnly(Side.CLIENT)
   public Vec3d a(Minecraft var1, cy var2, EntityLivingBase var3, float var4) {
      return com.trolmastercard.sexmod.b.a(var1, var2, var3, this, var4);
   }

   public static em i(@Nonnull UUID var0) {
      return a((UUID)var0, (Boolean)null);
   }

   public static em a(@Nonnull UUID var0, Boolean var1) {
      try {
         Iterator var2 = ad().iterator();

         while(true) {
            em var3;
            while(true) {
               while(true) {
                  if (!var2.hasNext()) {
                     return null;
                  }

                  var3 = (em)var2.next();

                  try {
                     if (var3.field_70128_L) {
                        continue;
                     }
                     break;
                  } catch (ConcurrentModificationException var5) {
                     throw c((RuntimeException)var5);
                  }
               }

               try {
                  if (!var0.equals(var3.ae())) {
                     continue;
                  }
                  break;
               } catch (ConcurrentModificationException var6) {
                  throw c((RuntimeException)var6);
               }
            }

            if (var1 == null) {
               return var3;
            }

            boolean var4 = var3.field_70170_p.field_72995_K;

            try {
               if (var4 && !var1) {
                  return var3;
               }
            } catch (ConcurrentModificationException var8) {
               throw c((RuntimeException)var8);
            }

            try {
               if (var4 || !var1) {
                  continue;
               }
            } catch (ConcurrentModificationException var7) {
               throw c((RuntimeException)var7);
            }

            return var3;
         }
      } catch (ConcurrentModificationException var9) {
         return null;
      }
   }

   @Nullable
   public static em c(@Nonnull UUID var0) {
      boolean var10000;
      label55: {
         try {
            if (FMLCommonHandler.instance().getMinecraftServerInstance() == null) {
               var10000 = true;
               break label55;
            }
         } catch (ConcurrentModificationException var8) {
            throw c((RuntimeException)var8);
         }

         var10000 = false;
      }

      boolean var1 = var10000;

      try {
         for(em var3 : ad()) {
            try {
               if (var3.field_70128_L) {
                  continue;
               }
            } catch (ConcurrentModificationException var6) {
               throw c((RuntimeException)var6);
            }

            boolean var4 = var3.field_70170_p.field_72995_K;

            try {
               if (var4 != var1) {
                  continue;
               }
            } catch (ConcurrentModificationException var5) {
               throw c((RuntimeException)var5);
            }

            if (var0.equals(var3.ae())) {
               return var3;
            }
         }
      } catch (ConcurrentModificationException var7) {
      }

      return null;
   }

   public static em d(@Nonnull EntityPlayer var0) {
      return c(var0.getPersistentID());
   }

   @SideOnly(Side.CLIENT)
   public void ac() {
   }

   public void r() {
      try {
         this.B = null;
         this.func_189654_d(false);
         this.b((fp)null);
         if (this.field_70170_p.field_72995_K) {
            this.V();
         }

      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }
   }

   @SideOnly(Side.CLIENT)
   protected void V() {
      try {
         if (this.n()) {
            d3.a(true);
            Minecraft.func_71410_x().field_71439_g.func_82142_c(false);
            ge.b.sendToServer(new s(this.f()));
         }

      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }
   }

   @SideOnly(Side.CLIENT)
   public static void k(UUID var0) {
      try {
         Iterator var1 = ad().iterator();

         em var2;
         while(true) {
            UUID var3;
            while(true) {
               if (!var1.hasNext()) {
                  return;
               }

               var2 = (em)var1.next();
               var3 = var2.ae();

               try {
                  if (var3 == null) {
                     continue;
                  }
                  break;
               } catch (ConcurrentModificationException var5) {
                  throw c((RuntimeException)var5);
               }
            }

            try {
               if (!var3.equals(var0)) {
                  continue;
               }
               break;
            } catch (ConcurrentModificationException var6) {
               throw c((RuntimeException)var6);
            }
         }

         fp var4 = var2.c(var2.y());
         if (var4 != null) {
            var2.b(var4);
         }
      } catch (ConcurrentModificationException var7) {
      }
   }

   @SideOnly(Side.CLIENT)
   public static void f(UUID var0) {
      try {
         for(em var2 : ad()) {
            try {
               if (var2.field_70128_L) {
                  continue;
               }
            } catch (ConcurrentModificationException var9) {
               throw c((RuntimeException)var9);
            }

            try {
               if (!var2.field_70170_p.field_72995_K) {
                  continue;
               }
            } catch (ConcurrentModificationException var8) {
               throw c((RuntimeException)var8);
            }

            UUID var3 = var2.ae();

            try {
               if (var3 == null) {
                  continue;
               }
            } catch (ConcurrentModificationException var7) {
               throw c((RuntimeException)var7);
            }

            try {
               if (!var3.equals(var0)) {
                  continue;
               }
            } catch (ConcurrentModificationException var6) {
               throw c((RuntimeException)var6);
            }

            fp var4 = var2.a(var2.y());

            try {
               if (var4 == null) {
                  continue;
               }
            } catch (ConcurrentModificationException var5) {
               throw c((RuntimeException)var5);
            }

            var2.b(var4);
         }
      } catch (ConcurrentModificationException var10) {
      }

   }

   public void N() {
      this.ag();
      ge.b.sendToServer(new a1(this.f()));
   }

   @SideOnly(Side.CLIENT)
   public void ag() {
      this.C.tickOffset = (double)0.0F;
   }

   @SideOnly(Side.CLIENT)
   @Nullable
   protected abstract fp c(fp var1);

   @SideOnly(Side.CLIENT)
   protected abstract fp a(fp var1);

   public NetworkRegistry.TargetPoint P() {
      return new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, (double)50.0F);
   }

   protected void a(double var1, double var3, double var5, float var7, float var8) {
      try {
         if (this.ae() == null) {
            System.out.println("couldnt move camera because the player isn't set");
            return;
         }
      } catch (ConcurrentModificationException var13) {
         throw c((RuntimeException)var13);
      }

      EntityPlayer var9 = this.field_70170_p.func_152378_a(this.ae());

      try {
         if (this.B == null) {
            this.B = var9.func_174791_d();
         }
      } catch (ConcurrentModificationException var12) {
         throw c((RuntimeException)var12);
      }

      Vec3d var10 = this.B;
      var10 = var10.func_72441_c(-Math.sin((double)(this.r + 90.0F) * (Math.PI / 180D)) * var1, (double)0.0F, Math.cos((double)(this.r + 90.0F) * (Math.PI / 180D)) * var1);
      var10 = var10.func_72441_c((double)0.0F, var3, (double)0.0F);
      var10 = var10.func_72441_c(-Math.sin((double)this.r * (Math.PI / 180D)) * var5, (double)0.0F, Math.cos((double)this.r * (Math.PI / 180D)) * var5);

      try {
         if (this.field_70170_p.field_72995_K) {
            ge.b.sendToServer(new a8(var9.getPersistentID().toString(), var10, this.r + var7, var8));
            return;
         }
      } catch (ConcurrentModificationException var11) {
         throw c((RuntimeException)var11);
      }

      var9.func_70080_a(var10.field_72450_a, var10.field_72448_b, var10.field_72449_c, this.r + var7, var8);
      var9.func_70634_a(var10.field_72450_a, var10.field_72448_b, var10.field_72449_c);
      this.field_70159_w = (double)0.0F;
      this.field_70181_x = (double)0.0F;
      this.field_70179_y = (double)0.0F;
   }

   @SideOnly(Side.CLIENT)
   protected boolean n() {
      // $FF: Couldn't be decompiled
   }

   protected void U() {
   }

   public void g(String var1) {
      this.m.func_187227_b(c, var1);
   }

   public String w() {
      return (String)this.m.func_187225_a(c);
   }

   public abstract String c();

   public String ab() {
      String var1 = (String)this.m.func_187225_a(c);

      try {
         if (!"".equals(var1)) {
            return var1;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      return this.c();
   }

   public abstract float i();

   @SideOnly(Side.CLIENT)
   public boolean t() {
      return true;
   }

   public void h(String var1) {
      try {
         if (!this.field_70170_p.field_72995_K) {
            ge.b.sendToAllAround(new gh(String.format("<%s> %s", this.ab(), var1), this.field_71093_bK, this.f()), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, (double)40.0F));
            return;
         }
      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }

      try {
         if (this.n()) {
            ge.b.sendToServer(new gh(String.format("<%s> %s", this.ab(), var1), this.field_71093_bK, this.f()));
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

   }

   protected void b(String var1, boolean var2) {
      try {
         if (!var2) {
            this.h(var1);
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      try {
         if (!this.field_70170_p.field_72995_K) {
            ge.b.sendToAllAround(new gh(var1, this.field_71093_bK, this.f()), new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, (double)40.0F));
            return;
         }
      } catch (ConcurrentModificationException var5) {
         throw c((RuntimeException)var5);
      }

      try {
         if (this.n()) {
            ge.b.sendToServer(new gh(var1, this.field_71093_bK, this.f()));
         }

      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }
   }

   protected void a(String var1) {
      try {
         if (this.field_70170_p.field_72995_K) {
            Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString(String.format("<%s> %s", this.ab(), var1)));
         }

      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }
   }

   protected void a(UUID var1, String var2) {
      EntityPlayer var3 = this.field_70170_p.func_152378_a(var1);

      try {
         if (var3 == null) {
            System.out.println("Player with UUID " + var1.toString() + " not found");
            return;
         }
      } catch (ConcurrentModificationException var5) {
         throw c((RuntimeException)var5);
      }

      try {
         if (this.field_70170_p.field_72995_K) {
            Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString("<" + var3.func_70005_c_() + "> " + var2));
         }

      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }
   }

   public void a(SoundEvent var1, float var2, float var3) {
      this.field_70170_p.func_184134_a((double)this.func_180425_c().func_177958_n(), (double)this.func_180425_c().func_177956_o(), (double)this.func_180425_c().func_177952_p(), var1, SoundCategory.NEUTRAL, var2, var3, false);
   }

   public void a(SoundEvent var1) {
      this.a(var1, 1.0F, 1.0F);
   }

   public void a(SoundEvent[] var1, int... var2) {
      try {
         if (var2.length == 0) {
            this.a(var1[this.func_70681_au().nextInt(var1.length)]);
            return;
         }
      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }

      this.a(var1[var2[this.func_70681_au().nextInt(var2.length)]], 1.0F, 1.0F);
   }

   public void a(SoundEvent[] var1, float var2) {
      this.a(var1[this.func_70681_au().nextInt(var1.length)], var2, 1.0F);
   }

   public void a(SoundEvent var1, float var2) {
      this.a(var1, var2, 1.0F);
   }

   public static boolean a(Entity var0) {
      try {
         if (var0 == null) {
            return false;
         }
      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }

      try {
         if (!(var0 instanceof em)) {
            return false;
         }
      } catch (ConcurrentModificationException var1) {
         throw c((RuntimeException)var1);
      }

      boolean var10000;
      try {
         if (!(var0 instanceof ei)) {
            var10000 = true;
            return var10000;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      var10000 = false;
      return var10000;
   }

   @SideOnly(Side.CLIENT)
   public em E() {
      return this;
   }

   @SideOnly(Side.CLIENT)
   public boolean e() {
      EntityPlayer var1 = this.field_70170_p.func_72890_a(this, (double)50.0F);

      try {
         if (var1 == null) {
            return false;
         }
      } catch (ConcurrentModificationException var2) {
         throw c((RuntimeException)var2);
      }

      return var1.getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID());
   }

   public Vec3d aa() {
      return this.a((double)1.0F);
   }

   public Vec3d a(double var1) {
      EntityPlayer var3 = this.field_70170_p.func_152378_a(this.ae());
      float var4 = var3.field_70177_z;
      return var3.func_174791_d().func_72441_c(-Math.sin((double)var4 * (Math.PI / 180D)) * var1, (double)0.0F, Math.cos((double)var4 * (Math.PI / 180D)) * var1);
   }

   public Vec3d a(Vec3d var1, float var2) {
      return var1;
   }

   public static void a(EnumParticleTypes var0, em var1) {
      double var2 = com.trolmastercard.sexmod.r.f.nextGaussian() * 0.02;
      double var4 = com.trolmastercard.sexmod.r.f.nextGaussian() * 0.02;
      double var6 = com.trolmastercard.sexmod.r.f.nextGaussian() * 0.02;
      var1.field_70170_p.func_175688_a(var0, var1.field_70165_t + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * var1.field_70130_N * 2.0F) - (double)var1.field_70130_N, var1.field_70163_u + (double)0.5F + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * var1.field_70131_O), var1.field_70161_v + (double)(com.trolmastercard.sexmod.r.f.nextFloat() * var1.field_70130_N * 2.0F) - (double)var1.field_70130_N, var2, var4, var6, new int[0]);
   }

   public static void a(EnumParticleTypes var0, em var1, int var2) {
      int var3 = 0;

      try {
         while(var3 < var2) {
            a(var0, var1);
            ++var3;
         }

      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }
   }

   public AnimationFactory getFactory() {
      return this.g;
   }

   public boolean func_70104_M() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   protected SoundEvent func_184639_G() {
      // $FF: Couldn't be decompiled
   }

   public float T() {
      return 0.0F;
   }

   public float ai() {
      return 0.0F;
   }

   @SideOnly(Side.CLIENT)
   public MatrixStack a(String param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   protected MatrixStack a(MatrixStack var1) {
      return var1;
   }

   @SideOnly(Side.CLIENT)
   public Vec3d b(String var1) {
      Vec3d var2 = (Vec3d)this.x.get(var1);

      try {
         if (var2 != null) {
            return var2;
         }
      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }

      try {
         if (!this.p.contains(var1)) {
            this.p.add(var1);
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      return Vec3d.field_186680_a;
   }

   @SideOnly(Side.CLIENT)
   public Vec3d d(String var1) {
      return this.b(var1).func_178787_e(this.func_174791_d());
   }

   public void a(String var1, Vec3d var2) {
      this.x.put(var1, var2);
   }

   @SideOnly(Side.CLIENT)
   public float R() {
      AnimationProcessor var1 = this.b();
      IBone var2 = var1.getBone("girlCam");

      try {
         if (var2 == null) {
            return 0.0F;
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      float var3 = var2.getPivotY();
      var3 = this.a(var3);
      return var3 / 16.0F;
   }

   @SideOnly(Side.CLIENT)
   public float v() {
      return 1.0F;
   }

   protected float a(float var1) {
      return var1;
   }

   public AnimatedGeoModel<? extends em> a() {
      Minecraft var1 = Minecraft.func_71410_x();
      Render var2 = var1.func_175598_ae().func_78713_a(this);

      try {
         if (var2 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var6) {
         throw c((RuntimeException)var6);
      }

      try {
         if (!(var2 instanceof d_)) {
            return null;
         }
      } catch (ConcurrentModificationException var8) {
         throw c((RuntimeException)var8);
      }

      GeoEntityRenderer var3 = (GeoEntityRenderer)var2;
      GeoModelProvider var4 = var3.getGeoModelProvider();

      try {
         if (var4 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var5) {
         throw c((RuntimeException)var5);
      }

      try {
         if (!(var4 instanceof AnimatedGeoModel)) {
            return null;
         }
      } catch (ConcurrentModificationException var7) {
         throw c((RuntimeException)var7);
      }

      return (AnimatedGeoModel)var4;
   }

   public AnimationProcessor<?> b() {
      return this.a().getAnimationProcessor();
   }

   public boolean h(int var1) {
      ArrayList var2 = this.D();

      try {
         if (var2.size() - 1 < var1) {
            return false;
         }
      } catch (ConcurrentModificationException var3) {
         throw c((RuntimeException)var3);
      }

      boolean var10000;
      try {
         if ((Integer)var2.get(var1) == 101) {
            var10000 = true;
            return var10000;
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      var10000 = false;
      return var10000;
   }

   public e1 g(int var1) {
      return e1.a;
   }

   public void a(List<Integer> param1) {
      // $FF: Couldn't be decompiled
   }

   public String F() {
      // $FF: Couldn't be decompiled
   }

   public static String c(List<Integer> var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var3 : var0) {
         var1.append(var3);
         var1.append("-");
      }

      return var1.toString();
   }

   public static List<Integer> c(String var0) {
      ArrayList var1 = new ArrayList();
      String[] var2 = var0.split("-");

      for(String var6 : var2) {
         var1.add(Integer.parseInt(var6));
      }

      return var1;
   }

   public static List<Integer> h(UUID param0) {
      // $FF: Couldn't be decompiled
   }

   public ArrayList<Integer> L() {
      return new ArrayList();
   }

   public List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> d(UUID var1) {
      try {
         if (this.d != null) {
            return this.d;
         }
      } catch (ConcurrentModificationException var8) {
         throw c((RuntimeException)var8);
      }

      ArrayList var2 = this.D();

      try {
         if (var2.isEmpty()) {
            this.d = new ArrayList();
            return this.d;
         }
      } catch (ConcurrentModificationException var7) {
         throw c((RuntimeException)var7);
      }

      ArrayList var3 = new ArrayList();
      List var4 = h(var1);
      int var5 = 0;

      try {
         while(var5 < var2.size()) {
            var3.add(new AbstractMap.SimpleEntry(gw.GIRL_SPECIFIC, new AbstractMap.SimpleEntry(this.e((Integer)var2.get(var5)), var4.get(var5))));
            ++var5;
         }
      } catch (ConcurrentModificationException var6) {
         throw c((RuntimeException)var6);
      }

      this.d = var3;
      return var3;
   }

   public void b(List<Map.Entry<gw, Map.Entry<List<String>, Integer>>> var1) {
      this.d = var1;
   }

   public void a(int var1, int var2) {
      try {
         if (this.d == null) {
            return;
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      try {
         if (this.d.size() - 1 < var1) {
            return;
         }
      } catch (ConcurrentModificationException var5) {
         throw c((RuntimeException)var5);
      }

      Map.Entry var3 = (Map.Entry)this.d.get(var1);
      ((Map.Entry)var3.getValue()).setValue(var2);
      this.d.set(var1, var3);
   }

   public void e(String param1) {
      // $FF: Couldn't be decompiled
   }

   private List<String> e(int var1) {
      ArrayList var2 = new ArrayList();
      int var3 = 0;

      try {
         while(var3 < var1) {
            var2.add("");
            ++var3;
         }

         return var2;
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }
   }

   public ArrayList<Integer> D() {
      return new ArrayList();
   }

   public List<Integer> u() {
      return new ArrayList();
   }

   public void f(String var1) {
      this.m.func_187227_b(b, var1);
   }

   public String C() {
      return (String)this.m.func_187225_a(b);
   }

   public static String a(HashSet<String> var0) {
      try {
         if (var0 == null) {
            return "";
         }
      } catch (ConcurrentModificationException var4) {
         throw c((RuntimeException)var4);
      }

      try {
         if (var0.isEmpty()) {
            return "";
         }
      } catch (ConcurrentModificationException var5) {
         throw c((RuntimeException)var5);
      }

      StringBuilder var1 = new StringBuilder();

      for(String var3 : var0) {
         var1.append(var3);
         var1.append("#");
      }

      return var1.toString();
   }

   public HashSet<String> Y() {
      String var1 = this.C();
      String[] var2 = var1.split("#");
      HashSet var3 = new HashSet();

      for(String var7 : var2) {
         try {
            if ("".equals(var7)) {
               continue;
            }
         } catch (ConcurrentModificationException var9) {
            throw c((RuntimeException)var9);
         }

         try {
            if ("cross".equals(var7)) {
               continue;
            }
         } catch (ConcurrentModificationException var8) {
            throw c((RuntimeException)var8);
         }

         var3.add(var7);
      }

      return var3;
   }

   @SideOnly(Side.CLIENT)
   public boolean H() {
      return true;
   }

   static {
      v = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(110);
      G = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187198_h).func_187156_b().func_187161_a(109);
      e = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(108);
      w = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187193_c).func_187156_b().func_187161_a(107);
      u = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(106);
      D = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187192_b).func_187156_b().func_187161_a(105);
      J = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(104);
      h = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(103);
      y = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(102);
      a = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(101);
      b = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(100);
      c = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(99);
      I = Arrays.asList(Items.field_151166_bC, Items.field_151045_i, Items.field_151043_k, Items.field_151079_bi);
   }

   private static RuntimeException c(RuntimeException var0) {
      return var0;
   }

   public static enum a {
      WALK,
      FAST_WALK,
      RUN;
   }
}
