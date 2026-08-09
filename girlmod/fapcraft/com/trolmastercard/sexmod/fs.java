package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.UUID;

public class fs {
   static HashMap<UUID, em> a = new HashMap();

   public static void b(em var0) {
      a.put(var0.f(), var0);
   }

   public static void a(em var0) {
      a.remove(var0.f());
   }

   public static void a() {
      a.clear();
   }

   public static em a(UUID var0) {
      return (em)a.get(var0);
   }
}
