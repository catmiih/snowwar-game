/*     */ package com.emilyzinha.entities;
/*     */ 
/*     */ import com.emilyzinha.main.Game;
/*     */ import com.emilyzinha.main.Sound;
/*     */ import com.emilyzinha.world.Camera;
/*     */ import com.emilyzinha.world.World;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ public class Enemy
/*     */   extends Entity
/*     */ {
/*  14 */   private double speed = 0.9D;
/*  15 */   private int maskx = 8, masky = 8, maskw = 10, maskh = 10;
/*     */   
/*  17 */   private int frames = 0; private int maxFrames = 10; private int index = 0; private int maxIndex = 1;
/*     */   
/*     */   private BufferedImage[] sprites;
/*     */   
/*  21 */   private int life = 10;
/*     */   
/*     */   private boolean isDamaged = false;
/*  24 */   private int damageFrames = 10, damageCurrent = 0;
/*     */   
/*     */   public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
/*  27 */     super(x, y, width, height, null);
/*     */     
/*  29 */     this.sprites = new BufferedImage[2];
/*  30 */     this.sprites[0] = Game.spritesheet.getSprite(128, 16, 16, 16);
/*  31 */     this.sprites[1] = Game.spritesheet.getSprite(144, 16, 16, 16);
/*     */   }
/*     */   
/*     */   public void tick() {
/*  35 */     if (calculateDistance(getX(), getY(), Game.player.getX(), Game.player.getY()) < 80.0D) {
/*  36 */       if (!isColiddingWithPlayer()) {
/*  37 */         boolean moved = false;
/*  38 */         if ((int)this.x < Game.player.getX() && World.isFree((int)(this.x + this.speed), getY()) && 
/*  39 */           !isColidding((int)(this.x + this.speed), getY())) {
/*  40 */           this.x += this.speed;
/*  41 */           moved = true;
/*     */         }
/*  43 */         else if ((int)this.x > Game.player.getX() && World.isFree((int)(this.x - this.speed), getY()) && 
/*  44 */           !isColidding((int)(this.x - this.speed), getY())) {
/*  45 */           this.x -= this.speed;
/*  46 */           moved = true;
/*     */         }
/*  48 */         else if ((int)this.y < Game.player.getY() && World.isFree(getX(), (int)(this.y + this.speed)) && 
/*  49 */           !isColidding(getX(), (int)(this.y + this.speed))) {
/*  50 */           this.y += this.speed;
/*  51 */           moved = true;
/*     */         }
/*  53 */         else if ((int)this.y > Game.player.getY() && World.isFree(getX(), (int)(this.y - this.speed)) && 
/*  54 */           !isColidding(getX(), (int)(this.y - this.speed))) {
/*  55 */           this.y -= this.speed;
/*  56 */           moved = true;
/*     */         } 
/*     */         
/*  59 */         if (moved) {
/*  60 */           this.frames++;
/*  61 */           if (this.frames == this.maxFrames) {
/*  62 */             this.frames = 0;
/*  63 */             this.index++;
/*  64 */             if (this.index > this.maxIndex) {
/*  65 */               this.index = 0;
/*     */             }
/*     */           } 
/*     */         } else {
/*  69 */           this.index = 0;
/*     */         } 
/*     */       } else {
/*     */         
/*  73 */         Sound.morte2.play();
/*  74 */         if (Game.rand.nextInt(100) < 40) {
/*  75 */           Game.player.life -= Game.rand.nextInt(3);
/*  76 */           Game.player.isDamaged = true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  81 */       collidingBullet();
/*     */       
/*  83 */       if (this.life <= 0) {
/*  84 */         destroySelf();
/*  85 */         Sound.atk_inimigo.play();
/*  86 */         Player.score++;
/*  87 */         Player.record++;
/*     */       } 
/*  89 */       if (this.isDamaged) {
/*  90 */         this.damageCurrent++;
/*  91 */         if (this.damageCurrent == this.damageFrames) {
/*  92 */           this.damageCurrent = 0;
/*  93 */           this.isDamaged = false;
/*     */         } 
/*     */       } 
/*     */     } else {
/*  97 */       this.index = 0;
/*     */     } 
/*     */   }
/*     */   public void destroySelf() {
/* 101 */     Game.enemies.remove(this);
/* 102 */     Game.entities.remove(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void collidingBullet() {
/* 107 */     for (int i = 0; i < Game.bullets.size(); i++) {
/* 108 */       Entity e = Game.bullets.get(i);
/* 109 */       if (e instanceof BulletShoot && 
/* 110 */         Entity.isColliding(this, e)) {
/* 111 */         this.isDamaged = true;
/* 112 */         Sound.dano.play();
/* 113 */         this.life -= Game.rand.nextInt(5);
/* 114 */         Game.bullets.remove(i);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColiddingWithPlayer() {
/* 122 */     Rectangle enemyCurrent = new Rectangle(getX() + this.maskx, getY() + this.masky, this.maskw, this.maskh);
/* 123 */     Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
/* 124 */     return enemyCurrent.intersects(player);
/*     */   }
/*     */   
/*     */   public boolean isColidding(int xnext, int ynext) {
/* 128 */     Rectangle enemyCurrent = new Rectangle(xnext + this.maskx, ynext + this.masky, this.maskw, this.maskh);
/* 129 */     for (int i = 0; i < Game.enemies.size(); i++) {
/* 130 */       Enemy e = Game.enemies.get(i);
/* 131 */       if (e != this) {
/*     */         
/* 133 */         Rectangle targetEnemy = new Rectangle(e.getX() + this.maskx, e.getY() + this.masky, this.maskw, this.maskh);
/* 134 */         if (enemyCurrent.intersects(targetEnemy))
/* 135 */           return true; 
/*     */       } 
/*     */     } 
/* 138 */     return false;
/*     */   }
/*     */   public void render(Graphics g) {
/* 141 */     if (!this.isDamaged) {
/* 142 */       g.drawImage(this.sprites[this.index], getX() - Camera.x, getY() - Camera.y, null);
/*     */     } else {
/* 144 */       g.drawImage(Entity.ENEMY_FEEDBACK, getX() - Camera.x, getY() - Camera.y, null);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\entities\Enemy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */