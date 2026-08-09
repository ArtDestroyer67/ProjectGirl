package com.trolmastercard.sexmod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;

public class a_ extends CommandBase implements IClientCommand {
   static final String d = "sexmod/futa";
   static final int a = 10;
   static final float c = 0.025F;
   public static boolean e = true;
   public static final a_ b = new a_();

   public a_() {
      String var1 = "";

      try {
         var1 = (new BufferedReader(new FileReader("sexmod/futa"))).readLine().toLowerCase();
      } catch (Exception var5) {
      }

      try {
         if ("".equals(var1)) {
            return;
         }
      } catch (Exception var4) {
         throw a(var4);
      }

      try {
         if ("true".equals(var1)) {
            e = true;
         }
      } catch (Exception var6) {
         throw a(var6);
      }

      try {
         if ("false".equals(var1)) {
            e = false;
         }

      } catch (Exception var3) {
         throw a(var3);
      }
   }

   public String func_71517_b() {
      return "futa";
   }

   public String func_71518_a(ICommandSender var1) {
      return "/futa <true|false>";
   }

   public void func_184881_a(MinecraftServer var1, ICommandSender var2, String[] var3) throws CommandException {
      try {
         if (var3.length < 1) {
            this.a(var2);
            return;
         }
      } catch (IOException var18) {
         throw a((Exception)var18);
      }

      String var4 = var3[0].toLowerCase();

      label99: {
         label89: {
            try {
               if ("true".equals(var4)) {
                  e = true;
                  break label89;
               }
            } catch (IOException var17) {
               throw a((Exception)var17);
            }

            try {
               if (!"false".equals(var4)) {
                  break label99;
               }

               e = false;
            } catch (IOException var16) {
               throw a((Exception)var16);
            }
         }

         try {
            FileWriter var5 = new FileWriter("sexmod/futa");
            var5.write(var4);
            var5.close();
         } catch (IOException var10) {
            var10.printStackTrace();
         }

         try {
            Iterator var19 = em.ad().iterator();

            while(true) {
               em var6;
               while(true) {
                  while(true) {
                     while(true) {
                        if (!var19.hasNext()) {
                           return;
                        }

                        var6 = (em)var19.next();

                        try {
                           if (var6.field_70128_L) {
                              continue;
                           }
                           break;
                        } catch (IOException var11) {
                           throw a((Exception)var11);
                        }
                     }

                     try {
                        if (!var6.field_70170_p.field_72995_K) {
                           continue;
                        }
                        break;
                     } catch (IOException var12) {
                        throw a((Exception)var12);
                     }
                  }

                  try {
                     if (!(var6 instanceof f_)) {
                        continue;
                     }
                     break;
                  } catch (IOException var13) {
                     throw a((Exception)var13);
                  }
               }

               Vec3d var7 = var6.b("cockParticles").func_178787_e(var6.func_174791_d());
               Random var8 = var6.func_70681_au();
               int var9 = 0;

               try {
                  while(var9 < 10) {
                     var6.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, var7.field_72450_a, var7.field_72448_b, var7.field_72449_c, (double)(var8.nextFloat() * 0.025F * (float)be.a()), (double)(var8.nextFloat() * 0.025F * (float)be.a()), (double)(var8.nextFloat() * 0.025F * (float)be.a()), new int[0]);
                     ++var9;
                  }
               } catch (IOException var14) {
                  throw a((Exception)var14);
               }
            }
         } catch (ConcurrentModificationException var15) {
            return;
         }
      }

      this.a(var2);
   }

   void a(ICommandSender var1) {
      var1.func_145747_a(new TextComponentString(String.format("%sYou can either do %s/futa true %sor %s/futa false", TextFormatting.YELLOW, TextFormatting.GRAY, TextFormatting.YELLOW, TextFormatting.GRAY)));
   }

   public boolean allowUsageWithoutPrefix(ICommandSender var1, String var2) {
      return false;
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
