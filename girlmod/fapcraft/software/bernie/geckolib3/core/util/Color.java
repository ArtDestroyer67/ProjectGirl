package software.bernie.geckolib3.core.util;

public final class Color {
   private final int color;
   public static final Color WHITE = new Color(-1);
   public static final Color LIGHT_GRAY = new Color(-4144960);
   public static final Color GRAY = new Color(-8355712);
   public static final Color DARK_GRAY = new Color(-12566464);
   public static final Color BLACK = new Color(-16777216);
   public static final Color RED = new Color(-65536);
   public static final Color PINK = new Color(-20561);
   public static final Color ORANGE = new Color(-14336);
   public static final Color YELLOW = new Color(-256);
   public static final Color GREEN = new Color(-16711936);
   public static final Color MAGENTA = new Color(-65281);
   public static final Color CYAN = new Color(-16711681);
   public static final Color BLUE = new Color(-16776961);

   private Color(int color) {
      this.color = color;
   }

   public static Color ofTransparent(int color) {
      return new Color(color);
   }

   public static Color ofOpaque(int color) {
      return new Color(-16777216 | color);
   }

   public static Color ofRGB(float r, float g, float b) {
      return ofRGBA(r, g, b, 1.0F);
   }

   public static Color ofRGB(int r, int g, int b) {
      return ofRGBA(r, g, b, 255);
   }

   public static Color ofRGBA(float r, float g, float b, float a) {
      return ofRGBA((int)((double)(r * 255.0F) + (double)0.5F), (int)((double)(g * 255.0F) + (double)0.5F), (int)((double)(b * 255.0F) + (double)0.5F), (int)((double)(a * 255.0F) + (double)0.5F));
   }

   public static Color ofRGBA(int r, int g, int b, int a) {
      return new Color((a & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | b & 255);
   }

   public static Color ofHSB(float hue, float saturation, float brightness) {
      return ofOpaque(HSBtoRGB(hue, saturation, brightness));
   }

   public static int HSBtoRGB(float hue, float saturation, float brightness) {
      int r = 0;
      int g = 0;
      int b = 0;
      if (saturation == 0.0F) {
         r = g = b = (int)(brightness * 255.0F + 0.5F);
      } else {
         float h = (hue - (float)Math.floor((double)hue)) * 6.0F;
         float f = h - (float)Math.floor((double)h);
         float p = brightness * (1.0F - saturation);
         float q = brightness * (1.0F - saturation * f);
         float t = brightness * (1.0F - saturation * (1.0F - f));
         switch ((int)h) {
            case 0:
               r = (int)(brightness * 255.0F + 0.5F);
               g = (int)(t * 255.0F + 0.5F);
               b = (int)(p * 255.0F + 0.5F);
               break;
            case 1:
               r = (int)(q * 255.0F + 0.5F);
               g = (int)(brightness * 255.0F + 0.5F);
               b = (int)(p * 255.0F + 0.5F);
               break;
            case 2:
               r = (int)(p * 255.0F + 0.5F);
               g = (int)(brightness * 255.0F + 0.5F);
               b = (int)(t * 255.0F + 0.5F);
               break;
            case 3:
               r = (int)(p * 255.0F + 0.5F);
               g = (int)(q * 255.0F + 0.5F);
               b = (int)(brightness * 255.0F + 0.5F);
               break;
            case 4:
               r = (int)(t * 255.0F + 0.5F);
               g = (int)(p * 255.0F + 0.5F);
               b = (int)(brightness * 255.0F + 0.5F);
               break;
            case 5:
               r = (int)(brightness * 255.0F + 0.5F);
               g = (int)(p * 255.0F + 0.5F);
               b = (int)(q * 255.0F + 0.5F);
         }
      }

      return -16777216 | r << 16 | g << 8 | b;
   }

   public int getColor() {
      return this.color;
   }

   public int getAlpha() {
      return this.color >> 24 & 255;
   }

   public int getRed() {
      return this.color >> 16 & 255;
   }

   public int getGreen() {
      return this.color >> 8 & 255;
   }

   public int getBlue() {
      return this.color & 255;
   }

   public Color brighter(double factor) {
      int r = this.getRed();
      int g = this.getGreen();
      int b = this.getBlue();
      int i = (int)((double)1.0F / ((double)1.0F - (double)1.0F / factor));
      if (r == 0 && g == 0 && b == 0) {
         return ofRGBA(i, i, i, this.getAlpha());
      } else {
         if (r > 0 && r < i) {
            r = i;
         }

         if (g > 0 && g < i) {
            g = i;
         }

         if (b > 0 && b < i) {
            b = i;
         }

         return ofRGBA(Math.min((int)((double)r / ((double)1.0F / factor)), 255), Math.min((int)((double)g / ((double)1.0F / factor)), 255), Math.min((int)((double)b / ((double)1.0F / factor)), 255), this.getAlpha());
      }
   }

   public Color darker(double factor) {
      return ofRGBA(Math.max((int)((double)this.getRed() * ((double)1.0F / factor)), 0), Math.max((int)((double)this.getGreen() * ((double)1.0F / factor)), 0), Math.max((int)((double)this.getBlue() * ((double)1.0F / factor)), 0), this.getAlpha());
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         return this.color == ((Color)other).color;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.color;
   }

   public String toString() {
      return String.valueOf(this.color);
   }
}
