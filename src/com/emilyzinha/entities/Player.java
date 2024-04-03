/*     */ package com.emilyzinha.entities;
/*     */ 
/*     */ import com.emilyzinha.main.Game;
/*     */ import com.emilyzinha.main.Sound;
/*     */ import com.emilyzinha.world.Camera;
/*     */ import com.emilyzinha.world.World;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ public class Player
/*     */   extends Entity {
/*  12 */   public static int score = 0;
/*  13 */   public static int record = 0;
/*     */   public boolean right;
/*     */   public boolean up;
/*  16 */   public int right_dir = 0, left_dir = 1; public boolean left; public boolean down;
/*  17 */   public int up_dir = 2, down_dir = 3;
/*  18 */   public int dir = this.right_dir;
/*  19 */   public double speed = 1.2D;
/*     */   
/*  21 */   private int frames = 0; private int maxFrames = 8; private int index = 0; private int maxIndex = 4;
/*     */   
/*     */   private boolean moved = false;
/*     */   
/*     */   private BufferedImage[] rightPlayer;
/*     */   
/*     */   private BufferedImage[] leftPlayer;
/*     */   private BufferedImage[] downPlayer;
/*     */   private BufferedImage[] upPlayer;
/*     */   private BufferedImage playerDamage;
/*     */   public boolean arma = false;
/*     */   public boolean isDamaged = false;
/*  33 */   private int damageFrames = 0;
/*     */   
/*     */   public boolean shoot = false;
/*     */   
/*  37 */   public static int ammo = 0;
/*     */   
/*  39 */   public double life = 100.0D; public double maxlife = 100.0D;
/*  40 */   public int mx = 0, my = 0;
/*     */   
/*     */   public Player(int x, int y, int width, int height, BufferedImage sprite) {
/*  43 */     super(x, y, width, height, sprite);
/*     */     
/*  45 */     this.rightPlayer = new BufferedImage[5];
/*  46 */     this.leftPlayer = new BufferedImage[5];
/*  47 */     this.downPlayer = new BufferedImage[5];
/*  48 */     this.upPlayer = new BufferedImage[5];
/*  49 */     this.playerDamage = Game.spritesheet.getSprite(0, 16, 16, 16);
/*     */     int i;
/*  51 */     for (i = 0; i < 5; i++) {
/*  52 */       this.rightPlayer[i] = Game.spritesheet.getSprite(32 + i * 16, 0, 16, 16);
/*     */     }
/*  54 */     for (i = 0; i < 5; i++) {
/*  55 */       this.leftPlayer[i] = Game.spritesheet.getSprite(32 + i * 16, 16, 16, 16);
/*     */     }
/*  57 */     for (i = 0; i < 5; i++) {
/*  58 */       this.downPlayer[i] = Game.spritesheet.getSprite(32 + i * 16, 32, 16, 16);
/*     */     }
/*  60 */     for (i = 0; i < 5; i++) {
/*  61 */       this.upPlayer[i] = Game.spritesheet.getSprite(32 + i * 16, 48, 16, 16);
/*     */     }
/*     */   }
/*     */   
/*     */   public void tick() {
/*  66 */     this.moved = false;
/*     */ 
/*     */     
/*  69 */     if (this.right && World.isFree((int)(this.x + this.speed), getY())) {
/*  70 */       this.moved = true;
/*  71 */       this.dir = this.right_dir;
/*  72 */       this.x += this.speed;
/*  73 */     } else if (this.left && World.isFree((int)(this.x - this.speed), getY())) {
/*  74 */       this.moved = true;
/*  75 */       this.dir = this.left_dir;
/*  76 */       this.x -= this.speed;
/*  77 */     } else if (this.up && World.isFree(getX(), (int)(this.y - this.speed))) {
/*  78 */       this.moved = true;
/*  79 */       this.dir = this.up_dir;
/*  80 */       this.y -= this.speed;
/*  81 */     } else if (this.down && World.isFree(getX(), (int)(this.y + this.speed))) {
/*  82 */       this.moved = true;
/*  83 */       this.dir = this.down_dir;
/*  84 */       this.y += this.speed;
/*     */     } else {
/*  86 */       this.index = 0;
/*     */     } 
/*     */ 
/*     */     
/*  90 */     if (this.moved) {
/*  91 */       Sound.andar.loop();
/*  92 */       this.frames++;
/*  93 */       if (this.frames == this.maxFrames) {
/*  94 */         this.frames = 0;
/*  95 */         this.index++;
/*  96 */         if (this.index > this.maxIndex) {
/*  97 */           this.index = 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 104 */     checkCollisionLifePack();
/* 105 */     checkCollisionAmmo();
/* 106 */     checkCollisionGun();
/*     */     
/* 108 */     if (this.isDamaged) {
/* 109 */       this.damageFrames++;
/* 110 */       if (this.damageFrames == 8) {
/* 111 */         this.damageFrames = 0;
/* 112 */         this.isDamaged = false;
/*     */       } 
/*     */     } 
/*     */     
/* 116 */     if (this.shoot) {
/* 117 */       this.shoot = false;
/*     */ 
/*     */       
/* 120 */       if (this.arma && ammo > 0) {
/* 121 */         Sound.atk_player.play();
/* 122 */         ammo--;
/*     */ 
/*     */         
/* 125 */         int px = 8, py = 8;
/* 126 */         double angle = 0.0D;
/* 127 */         if (this.dir == this.right_dir) {
/* 128 */           px = 18;
/* 129 */           angle = Math.atan2((this.my - getY() + 8 - Camera.y), (this.mx - getX() + 8 - Camera.x));
/*     */         } else {
/* 131 */           px = -8;
/* 132 */           angle = Math.atan2((this.my - getY() + 8 - Camera.y), (this.mx - getX() + 8 - Camera.x));
/*     */         } 
/*     */         
/* 135 */         double dx = Math.cos(angle);
/* 136 */         double dy = Math.sin(angle);
/*     */         
/* 138 */         BulletShoot bullet = new BulletShoot(getX() + 8, getY() + 8, 7, 7, null, dx, dy);
/* 139 */         Game.bullets.add(bullet);
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     if (this.life <= 0.0D) {
/* 144 */       Sound.morte.play();
/* 145 */       this.life = 0.0D;
/* 146 */       Game.gameState = "GAME_OVER";
/*     */     } 
/*     */   }
/*     */   public void checkCollisionGun() {
/* 150 */     for (int i = 0; i < Game.entities.size(); i++) {
/* 151 */       Entity atual = Game.entities.get(i);
/* 152 */       if (atual instanceof Weapon && 
/* 153 */         Entity.isColliding(this, atual)) {
/* 154 */         Sound.menu.play();
/* 155 */         this.arma = true;
/* 156 */         Game.entities.remove(atual);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkCollisionAmmo() {
/* 164 */     for (int i = 0; i < Game.entities.size(); i++) {
/* 165 */       Entity atual = Game.entities.get(i);
/* 166 */       if (atual instanceof Bullet && 
/* 167 */         Entity.isColliding(this, atual)) {
/* 168 */         Sound.menu.play();
/* 169 */         ammo += 6;
/* 170 */         Game.entities.remove(atual);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkCollisionLifePack() {
/* 178 */     for (int i = 0; i < Game.entities.size(); i++) {
/* 179 */       Entity atual = Game.entities.get(i);
/* 180 */       if (atual instanceof Lifepack && 
/* 181 */         Entity.isColliding(this, atual)) {
/* 182 */         Sound.menu.play();
/* 183 */         this.life += 20.0D;
/* 184 */         Game.entities.remove(atual);
/* 185 */         if (this.life > 100.0D) {
/* 186 */           this.life = 100.0D;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Graphics g) {
/* 198 */     Camera.x = Camera.clamp(getX() - 120, 0, World.WIDTH * 16 - 240);
/* 199 */     Camera.y = Camera.clamp(getY() - 80, 0, World.HEIGHT * 16 - 160);
/* 200 */     if (Game.gameState == "NORMAL")
/* 201 */       if (!this.isDamaged) {
/* 202 */         if (this.dir == this.right_dir) {
/* 203 */           g.drawImage(this.rightPlayer[this.index], getX() - Camera.x, getY() - Camera.y, null);
/*     */           
/* 205 */           if (this.arma) {
/* 206 */             g.drawImage(Entity.GUN_RIGHTDOWN, getX() - Camera.x, getY() - Camera.y, null);
/*     */           }
/*     */         }
/* 209 */         else if (this.dir == this.left_dir) {
/* 210 */           if (this.arma) {
/* 211 */             g.drawImage(Entity.GUN_LEFTUP, getX() - Camera.x, getY() - Camera.y, null);
/*     */           }
/* 213 */           g.drawImage(this.leftPlayer[this.index], getX() - Camera.x, getY() - Camera.y, null);
/*     */         } 
/* 215 */         if (this.dir == this.down_dir) {
/* 216 */           g.drawImage(this.downPlayer[this.index], getX() - Camera.x, getY() - Camera.y, null);
/* 217 */           if (this.arma) {
/* 218 */             g.drawImage(Entity.GUN_RIGHTDOWN, getX() - Camera.x, getY() - Camera.y, null);
/*     */           
/*     */           }
/*     */         }
/* 222 */         else if (this.dir == this.up_dir) {
/* 223 */           if (this.arma) {
/* 224 */             g.drawImage(Entity.GUN_LEFTUP, getX() - Camera.x, getY() - Camera.y, null);
/*     */           }
/* 226 */           g.drawImage(this.upPlayer[this.index], getX() - Camera.x, getY() - Camera.y, null);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 231 */         g.drawImage(this.playerDamage, getX() - Camera.x, getY() - Camera.y, null);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\entities\Player.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */