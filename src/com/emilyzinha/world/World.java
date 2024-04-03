/*     */ package com.emilyzinha.world;
/*     */ 
/*     */ import com.emilyzinha.entities.Bullet;
/*     */ import com.emilyzinha.entities.Enemy;
/*     */ import com.emilyzinha.entities.Entity;
/*     */ import com.emilyzinha.entities.Lifepack;
/*     */ import com.emilyzinha.entities.Player;
/*     */ import com.emilyzinha.entities.Weapon;
/*     */ import com.emilyzinha.graficos.Spritesheet;
/*     */ import com.emilyzinha.main.Game;
/*     */ import com.emilyzinha.main.Sound;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class World
/*     */ {
/*     */   public static Tile[] tiles;
/*     */   public static int WIDTH;
/*     */   public static int HEIGHT;
/*     */   public static final int TILE_SIZE = 16;
/*     */   
/*     */   public World(String path) {
/*     */     try {
/*  29 */       BufferedImage map = ImageIO.read(getClass().getResource(path));
/*  30 */       int[] pixels = new int[map.getTileWidth() * map.getHeight()];
/*     */       
/*  32 */       WIDTH = map.getWidth();
/*  33 */       HEIGHT = map.getHeight();
/*     */       
/*  35 */       tiles = new Tile[map.getTileWidth() * map.getHeight()];
/*     */       
/*  37 */       map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
/*  38 */       for (int xx = 0; xx < map.getWidth(); xx++) {
/*  39 */         for (int yy = 0; yy < map.getHeight(); yy++) {
/*     */           
/*  41 */           int pixelAtual = pixels[xx + yy * map.getWidth()];
/*  42 */           tiles[xx + yy * WIDTH] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
/*     */           
/*  44 */           if (pixelAtual == -16711936) {
/*  45 */             tiles[xx + yy * WIDTH] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
/*     */           }
/*  47 */           else if (pixelAtual == -16777216) {
/*  48 */             tiles[xx + yy * WIDTH] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
/*     */ 
/*     */           
/*     */           }
/*  52 */           else if (pixelAtual == -16776961) {
/*  53 */             Game.player.setX(xx * 16);
/*  54 */             Game.player.setY(yy * 16);
/*     */           }
/*  56 */           else if (pixelAtual == -65536) {
/*  57 */             Enemy en = new Enemy(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
/*  58 */             Game.entities.add(en);
/*  59 */             Game.enemies.add(en);
/*     */           }
/*  61 */           else if (pixelAtual == -1) {
/*  62 */             Game.entities.add(new Weapon(xx * 16, yy * 16, 16, 16, Entity.WEAPON_EN));
/*     */           }
/*  64 */           else if (pixelAtual == -2560) {
/*  65 */             Game.entities.add(new Bullet(xx * 16, yy * 16, 16, 16, Entity.BULLET_EN));
/*     */           }
/*  67 */           else if (pixelAtual == -34816) {
/*  68 */             Game.entities.add(new Lifepack(xx * 16, yy * 16, 16, 16, Entity.LIFEPACK_EN));
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*  73 */     } catch (IOException e) {
/*  74 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   public static boolean isFree(int xnext, int ynext) {
/*  78 */     int x1 = xnext / 16;
/*  79 */     int y1 = ynext / 16;
/*     */     
/*  81 */     int x2 = (xnext + 16 - 3) / 16;
/*  82 */     int y2 = ynext / 16;
/*     */     
/*  84 */     int x3 = xnext / 16;
/*  85 */     int y3 = (ynext + 16 - 1) / 16;
/*     */     
/*  87 */     int x4 = (xnext + 16 - 3) / 16;
/*  88 */     int y4 = (ynext + 16 - 1) / 16;
/*     */     
/*  90 */     return !(tiles[x1 + y1 * WIDTH] instanceof WallTile || 
/*  91 */       tiles[x2 + y2 * WIDTH] instanceof WallTile || 
/*  92 */       tiles[x3 + y3 * WIDTH] instanceof WallTile || 
/*  93 */       tiles[x4 + y4 * WIDTH] instanceof WallTile);
/*     */   }
/*     */   
/*     */   public static void restartGame(String level) {
/*  97 */     Sound.select.play();
/*  98 */     Game.entities = new ArrayList();
/*  99 */     Game.enemies = new ArrayList();
/* 100 */     Game.bullets = new ArrayList();
/* 101 */     Game.spritesheet = new Spritesheet("/spritesheet1.png");
/* 102 */     Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSprite(32, 0, 16, 16));
/* 103 */     Game.entities.add(Game.player);
/* 104 */     Game.world = new World("/" + level);
/* 105 */     Player.ammo = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Graphics g) {
/* 110 */     int xstart = Camera.x / 16;
/* 111 */     int ystart = Camera.y / 16;
/*     */     
/* 113 */     int xfinal = xstart + 15;
/* 114 */     int yfinal = ystart + 10;
/*     */     
/* 116 */     for (int xx = xstart; xx <= xfinal; xx++) {
/* 117 */       for (int yy = ystart; yy <= yfinal; yy++) {
/* 118 */         if (xx >= 0 && yy >= 0 && xx < WIDTH && yy < HEIGHT) {
/*     */ 
/*     */           
/* 121 */           Tile tile = tiles[xx + yy * WIDTH];
/* 122 */           tile.render(g);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\world\World.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */