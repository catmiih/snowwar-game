/*    */ package com.emilyzinha.entities;
/*    */ 
/*    */ import com.emilyzinha.main.Game;
/*    */ import com.emilyzinha.world.Camera;
/*    */ import com.emilyzinha.world.World;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BulletShoot
/*    */   extends Entity
/*    */ {
/*    */   private double dx;
/*    */   private double dy;
/* 18 */   private double spd = 4.0D;
/*    */   
/* 20 */   private int life = 30; private int curLife = 0;
/*    */   
/*    */   public static BufferedImage bullet;
/*    */   
/* 24 */   private int maskx = 8, masky = 8, maskw = 10, maskh = 10;
/*    */   
/*    */   public BulletShoot(int x, int y, int width, int height, BufferedImage sprite, double dx, double dy) {
/* 27 */     super(x, y, width, height, sprite);
/* 28 */     bullet = Game.spritesheet.getSprite(112, 16, 16, 16);
/*    */     
/* 30 */     this.dx = dx;
/* 31 */     this.dy = dy;
/*    */   }
/*    */   
/*    */   public void tick() {
/* 35 */     this.x += this.dx * this.spd;
/* 36 */     this.y += this.dy * this.spd;
/*    */     
/* 38 */     this.curLife++;
/* 39 */     if (this.curLife == this.life || !World.isFree((int)(this.x - 8.0D), (int)(this.y - 8.0D))) {
/* 40 */       Game.bullets.remove(this);
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Graphics g) {
/* 49 */     g.drawImage(bullet, getX() - Camera.x, getY() - Camera.y, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\entities\BulletShoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */