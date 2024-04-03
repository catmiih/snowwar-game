/*    */ package com.emilyzinha.main;
/*    */ 
/*    */ import java.applet.Applet;
/*    */ import java.applet.AudioClip;
/*    */ 
/*    */ 
/*    */ public class Sound
/*    */ {
/*    */   private AudioClip clip;
/* 10 */   public static final Sound musicbackground = new Sound("/trilha.wav");
/* 11 */   public static final Sound andar = new Sound("/andar2.wav");
/* 12 */   public static final Sound atk_inimigo = new Sound("/atk.inimigo.wav");
/* 13 */   public static final Sound atk_player = new Sound("/atk.player.wav");
/* 14 */   public static final Sound menu = new Sound("/menu.wav");
/* 15 */   public static final Sound dano = new Sound("/dano.wav");
/* 16 */   public static final Sound morte = new Sound("/morte.wav");
/* 17 */   public static final Sound morte2 = new Sound("/morte3.wav");
/* 18 */   public static final Sound select = new Sound("/select.wav");
/*    */   
/*    */   private Sound(String name) {
/*    */     try {
/* 22 */       this.clip = Applet.newAudioClip(Sound.class.getResource(name));
/* 23 */     } catch (Throwable throwable) {}
/*    */   }
/*    */   public void play() {
/*    */     try {
/* 27 */       (new Thread() {
/*    */           public void run() {
/* 29 */             Sound.this.clip.play();
/*    */           }
/* 31 */         }).start();
/* 32 */     } catch (Throwable throwable) {}
/*    */   }
/*    */   public void loop() {
/*    */     try {
/* 36 */       (new Thread() {
/*    */           public void run() {
/* 38 */             Sound.this.clip.loop();
/*    */           }
/* 40 */         }).start();
/* 41 */     } catch (Throwable throwable) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\main\Sound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */