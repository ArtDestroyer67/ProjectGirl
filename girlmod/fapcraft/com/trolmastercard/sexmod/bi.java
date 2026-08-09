package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class bi {
   public static void a() {
      a("jenny", ex.class, fy.JENNY.npcID, 3286592, 12655237);
      a("ellie", el.class, fy.ELLIE.npcID, 1447446, 9961472);
      a("slime", fn.class, fy.SLIME.npcID, 13167780, 8244330);
      a("bia", e0.class, fy.BIA.npcID, 7488816, 7254603);
      a("bee", fz.class, fy.BEE.npcID, 16701032, 4400155);
      a("luna", eb.class, fy.LUNA.npcID, 7881787, 7940422);
      a("allie", ev.class, fy.ALLIE.npcID);
      a("kobold", ff.class, fy.KOBOLD.npcID);
      a("kobold_egg", i.class, 4674237);
      a("goblin", e3.class, fy.GOBLIN.npcID, 39424, 19456);
      a("galath", f_.class, fy.GALATH.npcID, 16711680, 16711680);
      a("manglelie", f8.class, fy.MANGLELIE.npcID, 16382457, 8485574);
      a("custom_model", cy.class, 6281823);
      b("player_jenny", es.class, fy.JENNY.playerID);
      b("player_ellie", ee.class, fy.ELLIE.playerID);
      b("player_slime", ec.class, fy.SLIME.playerID);
      b("player_bia", eg.class, fy.BIA.playerID);
      b("player_bee", e9.class, fy.BEE.playerID);
      b("player_allie", e5.class, fy.ALLIE.playerID);
      b("player_kobold", e7.class, fy.KOBOLD.playerID);
      b("player_goblin", eq.class, fy.GOBLIN.playerID);
      b("player_luna", ed.class, fy.LUNA.playerID);
      b("player_galath", er.class, fy.GALATH.playerID);
      a("friendly_slime", ay.class, 5548484);
      a("luna_hook", gi.class, 4768742);
      a("energy_ball", c4.class, 2565153);
      a("pyrocinical", al.class, 515153);
      EntityRegistry.addSpawn(fn.class, 10, 1, 1, EnumCreatureType.CREATURE, new Biome[]{Biomes.field_76780_h, Biomes.field_150599_m});
      EntityRegistry.addSpawn(fz.class, 5, 1, 1, EnumCreatureType.CREATURE, new Biome[]{Biomes.field_76767_f, Biomes.field_76785_t});
      EntityRegistry.addSpawn(al.class, 3, 1, 1, EnumCreatureType.AMBIENT, new Biome[]{Biomes.field_76778_j});
      EntityRegistry.addSpawn(f8.class, 5, 1, 1, EnumCreatureType.AMBIENT, new Biome[]{Biomes.field_76778_j});
   }

   private static void b(String var0, Class<? extends Entity> var1, int var2) {
      EntityRegistry.registerModEntity(new ResourceLocation("sexmod:" + var0), var1, var0, var2, Main.instance, 100, 1, false);
   }

   private static void a(String var0, Class<? extends Entity> var1, int var2, int var3, int var4) {
      EntityRegistry.registerModEntity(new ResourceLocation("sexmod:" + var0), var1, var0, var2, Main.instance, 50, 1, true, var3, var4);
   }

   private static void a(String var0, Class<? extends Entity> var1, int var2) {
      EntityRegistry.registerModEntity(new ResourceLocation("sexmod:" + var0), var1, var0, var2, Main.instance, 50, 1, true);
   }
}
