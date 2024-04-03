/*    */ package com.emilyzinha.entities;
/*    */ 
/*    */ import com.emilyzinha.main.Game;
/*    */ import com.emilyzinha.world.Camera;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ public class Entity
/*    */ {
/* 12 */   public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
/* 13 */   public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(128, 0, 16, 16);
/* 14 */   public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(112, 16, 16, 16);
/* 15 */   public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(128, 16, 16, 16);
/* 16 */   public static BufferedImage ENEMY_FEEDBACK = Game.spritesheet.getSprite(16, 16, 16, 16);
/* 17 */   public static BufferedImage GUN_LEFTUP = Game.spritesheet.getSprite(128, 32, 16, 16);
/* 18 */   public static BufferedImage GUN_RIGHTDOWN = Game.spritesheet.getSprite(112, 32, 16, 16);
/*    */   
/*    */   protected double x;
/*    */   
/*    */   protected double y;
/*    */   
/*    */   protected int width;
/*    */   
/*    */   protected int height;
/*    */   
/*    */   private BufferedImage sprite;
/*    */   protected int maskx;
/*    */   protected int masky;
/*    */   protected int mwidth;
/*    */   protected int mheight;
/*    */   
/*    */   public Entity(int x, int y, int width, int height, BufferedImage sprite) {
/* 35 */     this.x = x;
/* 36 */     this.y = y;
/* 37 */     this.width = width;
/* 38 */     this.height = height;
/* 39 */     this.sprite = sprite;
/*    */     
/* 41 */     this.maskx = 0;
/* 42 */     this.masky = 0;
/* 43 */     this.mwidth = width;
/* 44 */     this.mheight = height;
/*    */   }
/*    */   public void setMask(int maskx, int masky, int mwidth, int mheight) {
/* 47 */     this.maskx = 0;
/* 48 */     this.masky = 0;
/* 49 */     this.mwidth = mwidth;
/* 50 */     this.mheight = mheight;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setX(int newX) {
/* 55 */     this.x = newX;
/*    */   }
/*    */   public void setY(int newY) {
/* 58 */     this.y = newY;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getX() {
/* 63 */     return (int)this.x;
/*    */   }
/*    */   
/*    */   public int getY() {
/* 67 */     return (int)this.y;
/*    */   }
/*    */   public int getWidth() {
/* 70 */     return this.width;
/*    */   }
/*    */   public int getHeight() {
/* 73 */     return this.height;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {}
/*    */ 
/*    */   
/*    */   public double calculateDistance(int x1, int y1, int x2, int y2) {
/* 81 */     return Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
/*    */   }
/*    */   
/*    */   public static boolean isColliding(Entity e1, Entity e2) {
/* 85 */     Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheight);
/* 86 */     Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheight);
/*    */     
/* 88 */     return e1Mask.intersects(e2Mask);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics g) {
/* 93 */     g.drawImage(this.sprite, getX() - Camera.x, getY() - Camera.y, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\entities\Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */