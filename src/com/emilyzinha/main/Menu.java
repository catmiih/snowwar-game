/*     */ package com.emilyzinha.main;
/*     */ 
/*     */ import com.emilyzinha.entities.Player;
/*     */ import com.emilyzinha.world.World;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Menu
/*     */ {
/*     */   private BufferedImage menuImage;
/*  25 */   public String[] options = new String[] { "novo jogo", "carregar jogo", "sair" };
/*     */   
/*  27 */   public int currentOption = 0;
/*  28 */   public int maxOption = this.options.length - 1;
/*     */   public boolean up;
/*     */   public boolean down;
/*     */   public boolean enter;
/*     */   public static boolean pause = false;
/*     */   public static boolean saveExist = false;
/*     */   public static boolean saveGame = false;
/*     */   
/*     */   public static void applySave(String str) {
/*  37 */     String[] spl = str.split("/");
/*  38 */     for (int i = 0; i < spl.length; i++) {
/*  39 */       String carregar, spl2[] = spl[i].split(":"); String str1;
/*  40 */       switch ((str1 = spl2[0]).hashCode()) { case 102865796: if (!str1.equals("level")) {
/*     */             break;
/*     */           }
/*  43 */           carregar = "level" + spl2[1] + ".png";
/*  44 */           World.restartGame(carregar);
/*  45 */           Game.gameState = "NORMAL";
/*  46 */           pause = false;
/*     */           break; }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String loadGame(int encode) {
/*  53 */     String line = "";
/*  54 */     File file = new File("save.txt");
/*  55 */     if (file.exists()) {
/*     */       try {
/*  57 */         String singleLine = null;
/*  58 */         BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
/*     */         try {
/*  60 */           while ((singleLine = reader.readLine()) != null) {
/*  61 */             String[] trans = singleLine.split(":");
/*  62 */             char[] val = trans[1].toCharArray();
/*  63 */             trans[1] = "";
/*  64 */             for (int i = 0; i < val.length; i++) {
/*  65 */               val[i] = (char)(val[i] - encode);
/*  66 */               trans[1] = String.valueOf(trans[1]) + val[i];
/*     */             } 
/*  68 */             line = String.valueOf(line) + trans[0];
/*  69 */             line = String.valueOf(line) + ":";
/*  70 */             line = String.valueOf(line) + trans[1];
/*  71 */             line = String.valueOf(line) + "/";
/*     */           } 
/*  73 */         } catch (IOException iOException) {}
/*  74 */       } catch (FileNotFoundException fileNotFoundException) {}
/*     */     }
/*  76 */     return line;
/*     */   }
/*     */   
/*     */   public static void saveGame(String[] val1, int[] val2, int encode) {
/*  80 */     BufferedWriter write = null;
/*     */     try {
/*  82 */       write = new BufferedWriter(new FileWriter("save.txt"));
/*  83 */     } catch (IOException iOException) {}
/*  84 */     for (int i = 0; i < val1.length; i++) {
/*  85 */       String current = val1[i];
/*  86 */       current = String.valueOf(current) + ":";
/*  87 */       char[] value = Integer.toString(val2[i]).toCharArray();
/*  88 */       for (int n = 0; n < value.length; n++) {
/*  89 */         value[n] = (char)(value[n] + encode);
/*  90 */         current = String.valueOf(current) + value[n];
/*     */         try {
/*  92 */           write.write(current);
/*  93 */           if (i < val1.length - 1) {
/*  94 */             write.newLine();
/*     */           }
/*  96 */         } catch (IOException iOException) {}
/*     */       } 
/*     */     } 
/*     */     try {
/* 100 */       write.flush();
/* 101 */       write.close();
/* 102 */     } catch (IOException iOException) {}
/*     */   }
/*     */   
/*     */   public void tick() {
/* 106 */     File file = new File("save.txt");
/* 107 */     if (file.exists()) {
/* 108 */       saveExist = true;
/*     */     } else {
/* 110 */       saveExist = false;
/*     */     } 
/*     */     
/* 113 */     if (this.up) {
/* 114 */       Sound.menu.play();
/* 115 */       this.up = false;
/* 116 */       this.currentOption--;
/* 117 */       if (this.currentOption < 0)
/* 118 */         this.currentOption = this.maxOption; 
/*     */     } 
/* 120 */     if (this.down) {
/* 121 */       Sound.menu.play();
/* 122 */       this.down = false;
/* 123 */       this.currentOption++;
/* 124 */       if (this.currentOption > this.maxOption) {
/* 125 */         this.currentOption = 0;
/*     */       }
/*     */     } 
/* 128 */     if (this.enter) {
/* 129 */       Sound.select.play();
/* 130 */       this.enter = false;
/* 131 */       if (this.options[this.currentOption] == "novo jogo") {
/* 132 */         if (!pause) {
/* 133 */           file = new File("save.txt");
/* 134 */           file.delete();
/* 135 */           World.restartGame("level1.png");
/* 136 */           Player.score = 0;
/* 137 */           Player.record = 0;
/* 138 */           Game.gameState = "NORMAL";
/*     */         } else {
/* 140 */           Game.gameState = "NORMAL";
/*     */         } 
/* 142 */       } else if (this.options[this.currentOption] == "carregar jogo") {
/* 143 */         file = new File("save.txt");
/* 144 */         if (file.exists()) {
/* 145 */           String saver = loadGame(10);
/* 146 */           applySave(saver);
/*     */         } 
/* 148 */       } else if (this.options[this.currentOption] == "sair") {
/* 149 */         if (!pause) {
/* 150 */           System.exit(1);
/*     */         } else {
/* 152 */           pause = false;
/* 153 */           Game.gameState = "MENU";
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(Graphics g) {
/* 160 */     Graphics2D g2 = (Graphics2D)g;
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (!pause) {
/* 165 */       g.setColor(new Color(255, 255, 255));
/* 166 */       g.fillRect(0, 0, 960, 640);
/*     */       
/* 168 */       g2.setColor(new Color(139, 233, 239, 100));
/* 169 */       g2.fillRect(0, 30, 1000, 110);
/*     */       
/* 171 */       g2.setColor(new Color(139, 233, 239, 50));
/* 172 */       g2.fillRect(200, 200, 620, 330);
/*     */       
/* 174 */       g.setColor(new Color(139, 233, 239));
/* 175 */       g.fillRect(250, 30, 550, 110);
/*     */ 
/*     */       
/* 178 */       g.setColor(Color.white);
/* 179 */       g.setFont(new Font("arial", 1, 100));
/* 180 */       g.drawString("Snow war", 285, 120);
/*     */     } else {
/*     */       
/* 183 */       g2.setColor(new Color(139, 233, 239, 100));
/* 184 */       g2.fillRect(0, 0, 960, 640);
/*     */ 
/*     */ 
/*     */       
/* 188 */       g.setColor(Color.WHITE);
/* 189 */       g.setFont(new Font("arial", 1, 70));
/* 190 */       g.drawString("Jogo Pausado", 290, 150);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 195 */     g.setColor(new Color(139, 233, 239));
/* 196 */     g.fillRect(300, 255, 450, 60);
/*     */     
/* 198 */     g.setColor(new Color(139, 233, 239));
/* 199 */     g.fillRect(300, 330, 450, 60);
/*     */     
/* 201 */     g.setColor(new Color(139, 233, 239));
/* 202 */     g.fillRect(300, 410, 450, 60);
/*     */ 
/*     */     
/* 205 */     g.setColor(Color.white);
/* 206 */     g.setFont(new Font("arial", 1, 50));
/* 207 */     if (!pause) {
/* 208 */       g.drawString("Novo jogo", 400, 300);
/*     */     } else {
/* 210 */       g.drawString("Resumir", 425, 300);
/*     */     } 
/* 212 */     g.drawString("Carregar jogo", 360, 378);
/*     */     
/* 214 */     if (!pause) {
/* 215 */       g.drawString("Sair", 480, 460);
/*     */     } else {
/* 217 */       g.drawString("Menu principal", 350, 460);
/*     */     } 
/*     */ 
/*     */     
/* 221 */     if (this.options[this.currentOption] == "novo jogo") {
/* 222 */       if (!pause) {
/* 223 */         g.drawString(">", 360, 300);
/* 224 */         g.drawString("<", 660, 305);
/*     */       } else {
/* 226 */         g.drawString(">", 390, 300);
/* 227 */         g.drawString("<", 630, 305);
/*     */       }
/*     */     
/* 230 */     } else if (this.options[this.currentOption] == "carregar jogo") {
/* 231 */       g.drawString(">", 320, 380);
/* 232 */       g.drawString("<", 695, 385);
/*     */     } 
/* 234 */     if (this.options[this.currentOption] == "sair")
/* 235 */       if (!pause) {
/* 236 */         g.drawString(">", 440, 460);
/* 237 */         g.drawString("<", 580, 460);
/*     */       } else {
/*     */         
/* 240 */         g.drawString(">", 310, 460);
/* 241 */         g.drawString("<", 710, 460);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\main\Menu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */