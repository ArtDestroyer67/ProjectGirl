package com.trolmastercard.sexmod;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBed.EnumPartType;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class cj {
   public static float a(float var0, float var1) {
      var0 = gc.b(var0);
      var1 = gc.b(var1);
      float var2 = Math.abs(var0 - var1);
      float var3 = 360.0F - var2;
      float var4 = Math.min(var2, var3);

      try {
         return var0 > var1 ? -var4 : var4;
      } catch (RuntimeException var5) {
         throw a(var5);
      }
   }

   public static Vec3d a(EntityLivingBase var0, float var1) {
      World var2 = var0.field_70170_p;

      try {
         if (var2 instanceof gj) {
            return new Vec3d((double)0.0F, (double)1.0F, (double)0.0F);
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      BlockPos var3 = new BlockPos(Math.floor(var0.field_70165_t), Math.floor(var0.field_70163_u), Math.floor(var0.field_70161_v));
      HashMap var4 = new HashMap();
      int var5 = 0;

      for(int var6 = -1; var6 < 2; ++var6) {
         for(int var7 = -1; var7 < 2; ++var7) {
            for(int var8 = -1; var8 < 2; ++var8) {
               int var9 = var2.func_175721_c(var3.func_177982_a(var6, var7, var8), false);
               var4.put(new Vec3d((double)var6, (double)var7, (double)var8), var9);
               if (var9 > var5) {
                  var5 = var9;
               }
            }
         }
      }

      Vec3d var12 = null;

      for(Map.Entry var17 : var4.entrySet()) {
         try {
            if ((Integer)var17.getValue() != var5) {
               continue;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         if (var12 != null) {
            var12 = null;
            break;
         }

         var12 = (Vec3d)var17.getKey();
      }

      if (var12 == null) {
         var12 = new Vec3d(0.2, 0.8, (double)0.0F);
      } else {
         var12 = new Vec3d(var12.field_72450_a, var12.field_72448_b, -var12.field_72449_c);
         float var16 = -b6.a(var0.field_70760_ar, var0.field_70761_aq, var1);
         var12 = ck.a(var12, var16);
      }

      return var12.func_72432_b();
   }

   public static int a(World var0, int var1, int var2) {
      HashSet var3 = Sets.newHashSet(new Block[]{Blocks.field_150349_c, Blocks.field_150354_m, Blocks.field_180395_cM, Blocks.field_150355_j, Blocks.field_150348_b, Blocks.field_150347_e});
      int var4 = var0.func_72800_K();
      boolean var5 = false;

      while(true) {
         try {
            if (var5 || var4-- < 0) {
               return var4;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         Block var6 = var0.func_180495_p(new BlockPos(var1, var4, var2)).func_177230_c();
         var5 = var3.contains(var6);
      }
   }

   public static BlockPos a(World var0, BlockPos var1) {
      return new BlockPos(var1.func_177958_n(), a(var0, var1.func_177958_n(), var1.func_177952_p()), var1.func_177952_p());
   }

   public static boolean b(World var0, BlockPos var1) {
      return a(var0, var1, (Vec3d)null, (EnumFacing)null, (EntityPlayer)null);
   }

   public static boolean a(World param0, BlockPos param1, Vec3d param2, EnumFacing param3, EntityPlayer param4) {
      // $FF: Couldn't be decompiled
   }

   public static void a(World var0, EnumParticleTypes var1, Vec3d var2, int var3, double var4, double var6) {
      for(int var8 = 0; var8 < var3; ++var8) {
         float var9 = (float)var8 / (float)var3;
         double var10 = (Math.PI * 2D) * (double)var9;
         double var12 = Math.sin(var10);
         double var14 = Math.cos(var10);
         var12 *= var4;
         var14 *= var4;
         var0.func_175688_a(var1, var2.field_72450_a + var12, var2.field_72448_b, var2.field_72449_c + var14, (double)0.0F, (double)r.f.nextFloat() * var6, (double)0.0F, new int[0]);
      }

   }

   public static BlockPos a(BlockPos var0, IBlockState var1) {
      ImmutableMap var2 = var1.func_177228_b();
      EnumFacing var3 = null;
      BlockBed.EnumPartType var4 = null;
      UnmodifiableIterator var5 = var2.entrySet().iterator();

      while(var5.hasNext()) {
         Map.Entry var6 = (Map.Entry)var5.next();
         if (var6.getKey() instanceof PropertyDirection) {
            var3 = (EnumFacing)var6.getValue();
         } else if (var6.getKey() instanceof PropertyEnum) {
            var4 = (BlockBed.EnumPartType)var6.getValue();
         }
      }

      try {
         if (var3 == null) {
            System.out.println("bed is fucked up - it has no facing value");
            return null;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if (var4 == null) {
            System.out.println("bed is fucked up - it has no partType value");
            return null;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      BlockPos var11 = null;

      label91: {
         label92: {
            label72: {
               try {
                  if (var4 != EnumPartType.FOOT) {
                     break label92;
                  }

                  if (var3 != EnumFacing.NORTH) {
                     break label72;
                  }
               } catch (RuntimeException var9) {
                  throw a(var9);
               }

               var11 = var0.func_177978_c();
            }

            if (var3 == EnumFacing.EAST) {
               var11 = var0.func_177974_f();
            }

            if (var3 == EnumFacing.SOUTH) {
               var11 = var0.func_177968_d();
            }

            if (var3 == EnumFacing.WEST) {
               var11 = var0.func_177976_e();
            }
            break label91;
         }

         if (var3 == EnumFacing.NORTH) {
            var11 = var0.func_177968_d();
         }

         if (var3 == EnumFacing.EAST) {
            var11 = var0.func_177976_e();
         }

         if (var3 == EnumFacing.SOUTH) {
            var11 = var0.func_177978_c();
         }

         if (var3 == EnumFacing.WEST) {
            var11 = var0.func_177974_f();
         }
      }

      try {
         if (var11 == null) {
            System.out.println("bed is fucked up - it appears to be positioned vertically (wtf?)");
            return null;
         } else {
            return var11;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }
   }

   public static Set<? extends EntityPlayer> a(Entity var0) {
      try {
         if (var0 == null) {
            return Collections.emptySet();
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      return FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(var0.field_71093_bK).func_73039_n().getTrackingPlayers(var0);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
