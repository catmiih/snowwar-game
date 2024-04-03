/*    */ package com.emilyzinha.world;
/*    */ 
/*    */ import com.emilyzinha.main.Game;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ public class Tile
/*    */ {
/* 10 */   public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
/* 11 */   public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);
/*    */   private BufferedImage sprite;
/*    */   private int x;
/*    */   private int y;
/*    */   
/*    */   public Tile(int x, int y, BufferedImage sprite) {
/* 17 */     this.x = x;
/* 18 */     this.y = y;
/* 19 */     this.sprite = sprite;
/*    */   }
/*    */   
/*    */   public void render(Graphics g) {
/* 23 */     g.drawImage(this.sprite, this.x - Camera.x, this.y - Camera.y, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\world\Tile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */