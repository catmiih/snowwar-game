/*    */ package com.emilyzinha.graficos;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Spritesheet
/*    */ {
/*    */   private BufferedImage spritesheet;
/*    */   
/*    */   public Spritesheet(String path) {
/*    */     try {
/* 15 */       this.spritesheet = ImageIO.read(getClass().getResource(path));
/* 16 */     } catch (IOException e) {
/* 17 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public BufferedImage getSprite(int x, int y, int width, int height) {
/* 22 */     return this.spritesheet.getSubimage(x, y, width, height);
/*    */   }
/*    */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\graficos\Spritesheet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */