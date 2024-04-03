/*    */ package com.emilyzinha.graficos;
/*    */ 
/*    */ import com.emilyzinha.entities.Player;
/*    */ import com.emilyzinha.main.Game;
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UI
/*    */ {
/*    */   private BufferedImage vida;
/*    */   private BufferedImage bala;
/*    */   private BufferedImage arma;
/*    */   private BufferedImage score2;
/*    */   
/*    */   public void render(Graphics g) {
/* 22 */     g.setColor(Color.black);
/* 23 */     g.fillRect(7, 3, 80, 12);
/*    */ 
/*    */     
/* 26 */     g.setColor(Color.red);
/* 27 */     g.fillRect(20, 5, 65, 8);
/*    */ 
/*    */     
/* 30 */     g.setColor(Color.green);
/* 31 */     g.fillRect(20, 5, (int)(Game.player.life / Game.player.maxlife * 65.0D), 8);
/*    */ 
/*    */     
/* 34 */     g.setColor(Color.white);
/* 35 */     g.setFont(new Font("arial", 1, 9));
/* 36 */     g.drawString(String.valueOf((int)Game.player.life) + " / " + (int)Game.player.maxlife, 22, 13);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     g.setColor(Color.black);
/* 42 */     g.fillRect(158, 1, 78, 17);
/*    */ 
/*    */     
/* 45 */     g.setColor(Color.white);
/* 46 */     g.fillRect(160, 3, 74, 13);
/*    */ 
/*    */     
/* 49 */     g.setColor(Color.black);
/* 50 */     g.setFont(new Font("arial", 1, 9));
/* 51 */     g.drawString("Magia: " + Player.ammo, 177, 13);
/*    */ 
/*    */     
/* 54 */     this.score2 = Game.spritesheet.getSprite(128, 16, 16, 16);
/* 55 */     this.arma = Game.spritesheet.getSprite(128, 0, 16, 16);
/* 56 */     this.vida = Game.spritesheet.getSprite(112, 0, 16, 16);
/* 57 */     this.bala = Game.spritesheet.getSprite(112, 16, 16, 16);
/*    */ 
/*    */     
/* 60 */     g.setColor(Color.black);
/* 61 */     g.setFont(new Font("arial", 1, 11));
/* 62 */     g.drawString("x " + Player.score, 113, 14);
/* 63 */     g.setFont(new Font("arial", 1, 10));
/* 64 */     g.drawString("Recorde: " + Player.record, 87, 26);
/* 65 */     g.drawImage(this.score2, 95, 0, null);
/*    */ 
/*    */     
/* 68 */     if (Game.player.arma) {
/*    */ 
/*    */       
/* 71 */       g.setColor(Color.black);
/* 72 */       g.fillRect(138, 1, 21, 17);
/*    */ 
/*    */       
/* 75 */       g.setColor(Color.GRAY);
/* 76 */       g.fillRect(140, 3, 17, 13);
/* 77 */       g.drawImage(this.arma, 140, 2, null);
/*    */ 
/*    */       
/* 80 */       g.setColor(Color.black);
/* 81 */       g.setFont(new Font("arial", 1, 10));
/* 82 */       g.drawString("Use o mouse", 175, 26);
/* 83 */       g.drawString("para atirar", 188, 36);
/*    */     } 
/*    */     
/* 86 */     g.drawImage(this.vida, 5, 1, null);
/* 87 */     g.drawImage(this.bala, 160, 2, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\graficos\UI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */