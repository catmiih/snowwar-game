/*     */ package com.emilyzinha.main;
/*     */ 
/*     */ import com.emilyzinha.entities.BulletShoot;
/*     */ import com.emilyzinha.entities.Enemy;
/*     */ import com.emilyzinha.entities.Entity;
/*     */ import com.emilyzinha.entities.Player;
/*     */ import com.emilyzinha.graficos.Spritesheet;
/*     */ import com.emilyzinha.graficos.UI;
/*     */ import com.emilyzinha.world.World;
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.image.BufferStrategy;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JFrame;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Game
/*     */   extends Canvas
/*     */   implements Runnable, KeyListener, MouseListener, MouseMotionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static JFrame frame;
/*     */   private Thread thread;
/*     */   private boolean isRunning = true;
/*     */   public static final int WIDTH = 240;
/*     */   public static final int HEIGHT = 160;
/*     */   public static final int SCALE = 4;
/*  45 */   public static int CUR_LEVEL = 1; public static int MAX_LEVEL = 2;
/*     */   
/*     */   private BufferedImage image;
/*     */   
/*     */   public static boolean som = false;
/*     */   
/*     */   public static List<Entity> entities;
/*     */   
/*     */   public static List<Enemy> enemies;
/*     */   
/*     */   public static List<BulletShoot> bullets;
/*     */   
/*     */   public static Spritesheet spritesheet;
/*     */   
/*     */   public static World world;
/*     */   
/*     */   public static Player player;
/*     */   
/*     */   public static Random rand;
/*     */   
/*     */   public UI ui;
/*     */   public int xx;
/*     */   public int yy;
/*  68 */   public static String gameState = "MENU";
/*     */   private boolean showMessengeGameOver = false;
/*  70 */   private int framesGameOver = 0;
/*     */   
/*     */   public static boolean restartGame = false;
/*     */   
/*     */   public Menu menu;
/*     */   public boolean saveGame = false;
/*     */   public int[] pixels;
/*     */   public BufferedImage lightmap;
/*     */   public int[] lightMapPixels;
/*     */   public int mx;
/*     */   public int my;
/*     */   
/*     */   public Game() {
/*  83 */     Sound.musicbackground.loop();
/*     */     
/*  85 */     rand = new Random();
/*  86 */     addKeyListener(this);
/*  87 */     addMouseListener(this);
/*  88 */     addMouseMotionListener(this);
/*  89 */     setPreferredSize(new Dimension(960, 640));
/*  90 */     initFrame();
/*     */     
/*  92 */     this.ui = new UI();
/*     */     
/*  94 */     this.image = new BufferedImage(240, 160, 1);
/*     */     try {
/*  96 */       this.lightmap = ImageIO.read(getClass().getResource("/lightmap.png"));
/*  97 */     } catch (IOException e) {
/*  98 */       e.printStackTrace();
/*     */     } 
/* 100 */     this.lightMapPixels = new int[this.lightmap.getWidth() * this.lightmap.getHeight()];
/* 101 */     this.lightmap.getRGB(0, 0, this.lightmap.getWidth(), this.lightmap.getHeight(), this.lightMapPixels, 0, this.lightmap.getWidth());
/* 102 */     this.pixels = ((DataBufferInt)this.image.getRaster().getDataBuffer()).getData();
/*     */ 
/*     */     
/* 105 */     entities = new ArrayList<>();
/* 106 */     enemies = new ArrayList<>();
/* 107 */     bullets = new ArrayList<>();
/* 108 */     spritesheet = new Spritesheet("/spritesheet1.png");
/*     */     
/* 110 */     player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
/* 111 */     entities.add(player);
/* 112 */     world = new World("/level1.png");
/*     */     
/* 114 */     this.menu = new Menu();
/*     */   }
/*     */   public void initFrame() {
/* 117 */     frame = new JFrame("Snow War 2D");
/* 118 */     frame.add(this);
/* 119 */     frame.setResizable(false);
/* 120 */     frame.pack();
/* 121 */     frame.setLocationRelativeTo((Component)null);
/* 122 */     frame.setDefaultCloseOperation(3);
/* 123 */     frame.setVisible(true);
/*     */   }
/*     */   
/*     */   public synchronized void start() {
/* 127 */     this.thread = new Thread(this);
/* 128 */     this.isRunning = true;
/* 129 */     this.thread.start();
/*     */   }
/*     */   
/*     */   public synchronized void stop() {
/* 133 */     this.isRunning = false;
/*     */     try {
/* 135 */       this.thread.join();
/* 136 */     } catch (InterruptedException e) {
/* 137 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
public static void main(String[] args) {
	Game game = new Game();
	game.start();
}
/*     */   
/*     */   public void tick() {
/* 147 */     if (gameState == "NORMAL") {
/* 148 */       this.xx++;
/* 149 */       if (this.saveGame) {
/* 150 */         this.saveGame = false;
/* 151 */         String[] opt1 = { "level" };
/* 152 */         int[] opt2 = { CUR_LEVEL };
/* 153 */         Menu.saveGame(opt1, opt2, 10);
/* 154 */         System.out.println("JOGO SALVO!");
/*     */       } 
/* 156 */       restartGame = false; int i;
/* 157 */       for (i = 0; i < entities.size(); i++) {
/* 158 */         Entity e = entities.get(i);
/* 159 */         e.tick();
/*     */       } 
/* 161 */       for (i = 0; i < bullets.size(); i++) {
/* 162 */         ((BulletShoot)bullets.get(i)).tick();
/*     */       }
/* 164 */       if (enemies.size() == 0) {
/*     */         
/* 166 */         Player.score = 0;
/* 167 */         CUR_LEVEL++;
/* 168 */         if (CUR_LEVEL > MAX_LEVEL) {
/* 169 */           CUR_LEVEL = 1;
/*     */         }
/* 171 */         String newWorld = "level" + CUR_LEVEL + ".png";
/* 172 */         World.restartGame(newWorld);
/*     */       } 
/* 174 */     } else if (gameState == "GAME_OVER") {
/* 175 */       this.framesGameOver++;
/* 176 */       if (this.framesGameOver == 35) {
/* 177 */         this.framesGameOver = 0;
/* 178 */         if (this.showMessengeGameOver) {
/* 179 */           this.showMessengeGameOver = false;
/*     */         } else {
/* 181 */           this.showMessengeGameOver = true;
/*     */         } 
/* 183 */         if (restartGame) {
/* 184 */           gameState = "NORMAL";
/* 185 */           CUR_LEVEL = 1;
/* 186 */           Player.record = 0;
/* 187 */           Player.score = 0;
/* 188 */           String newWorld = "level" + CUR_LEVEL + ".png";
/* 189 */           World.restartGame(newWorld);
/* 190 */           restartGame = false;
/*     */         }
/*     */       
/*     */       } 
/* 194 */     } else if (gameState == "MENU") {
/* 195 */       this.menu.tick();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyLight() {
/* 214 */     for (int xx = 0; xx < 240; xx++) {
/* 215 */       for (int yy = 0; yy < 160; yy++) {
/* 216 */         if (this.lightMapPixels[xx + yy * 240] == -1) {
/* 217 */           this.pixels[xx + yy * 240] = 0;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render() {
/* 224 */     BufferStrategy bs = getBufferStrategy();
/* 225 */     if (bs == null) {
/* 226 */       createBufferStrategy(3);
/*     */       return;
/*     */     } 
/* 229 */     Graphics g = this.image.getGraphics();
/* 230 */     g.setColor(new Color(0, 0, 0));
/* 231 */     g.fillRect(0, 0, 240, 160);
/*     */ 
/*     */ 
/*     */     
/* 235 */     world.render(g); int i;
/* 236 */     for (i = 0; i < entities.size(); i++) {
/* 237 */       Entity e = entities.get(i);
/* 238 */       e.render(g);
/*     */     } 
/* 240 */     for (i = 0; i < bullets.size(); i++) {
/* 241 */       ((BulletShoot)bullets.get(i)).render(g);
/*     */     }
/*     */ 
/*     */     
/* 245 */     applyLight();
/* 246 */     this.ui.render(g);
/*     */ 
/*     */     
/* 249 */     g.dispose();
/* 250 */     g = bs.getDrawGraphics();
/*     */ 
/*     */ 
/*     */     
/* 254 */     g.drawImage(this.image, 0, 0, 960, 640, null);
/* 255 */     if (gameState == "GAME_OVER") {
/* 256 */       Graphics2D g2 = (Graphics2D)g;
/* 257 */       g2.setColor(new Color(255, 0, 0, 100));
/* 258 */       g2.fillRect(0, 0, 960, 640);
/* 259 */       g.setColor(Color.white);
/* 260 */       g.setFont(new Font("arial", 1, 70));
/* 261 */       g.drawString("Você morreu!", 230, 290);
/*     */       
/* 263 */       g.setFont(new Font("arial", 1, 40));
/* 264 */       if (this.showMessengeGameOver) {
/* 265 */         g.drawString("<Pressione ENTER para reiniciar>", 150, 350);
/*     */       }
/* 267 */     } else if (gameState == "MENU") {
/* 268 */       this.menu.render(g);
/*     */     } 
/* 270 */     bs.show();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 276 */     long lastTime = System.nanoTime();
/* 277 */     double amountOfTicks = 60.0D;
/* 278 */     double ns = 1.0E9D / amountOfTicks;
/* 279 */     double delta = 0.0D;
/* 280 */     int frames = 0;
/* 281 */     double timer = System.currentTimeMillis();
/* 282 */     requestFocus();
/* 283 */     while (this.isRunning) {
/* 284 */       long now = System.nanoTime();
/* 285 */       delta += (now - lastTime) / ns;
/* 286 */       lastTime = now;
/* 287 */       if (delta >= 1.0D) {
/* 288 */         tick();
/* 289 */         render();
/* 290 */         frames++;
/* 291 */         delta--;
/*     */       } 
/* 293 */       if (System.currentTimeMillis() - timer >= 1000.0D) {
/* 294 */         System.out.println("FPS: " + frames);
/* 295 */         frames = 0;
/* 296 */         timer += 1000.0D;
/*     */       } 
/*     */     } 
/*     */     
/* 300 */     stop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/* 306 */     if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
/* 307 */       player.right = true;
/*     */     }
/* 309 */     else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
/* 310 */       player.left = true;
/*     */     } 
/*     */     
/* 313 */     if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
/* 314 */       player.up = true;
/*     */       
/* 316 */       if (gameState == "MENU") {
/* 317 */         this.menu.up = true;
/*     */       }
/* 319 */     } else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
/* 320 */       if (gameState == "NORMAL")
/*     */       {
/* 322 */         player.down = true;
/*     */       }
/* 324 */       if (gameState == "MENU") {
/* 325 */         this.menu.down = true;
/*     */       }
/*     */     } 
/* 328 */     if (e.getKeyCode() == 32 && 
/* 329 */       gameState == "NORMAL") {
/* 330 */       this.saveGame = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {
/* 337 */     if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
/* 338 */       player.right = false;
/* 339 */     } else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
/* 340 */       player.left = false;
/*     */     } 
/*     */ 
/*     */     
/* 344 */     if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
/* 345 */       player.up = false;
/*     */     }
/* 347 */     else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
/* 348 */       player.down = false;
/*     */     } 
/*     */     
/* 351 */     if (e.getKeyCode() == 10) {
/* 352 */       restartGame = true;
/* 353 */       if (gameState == "MENU") {
/* 354 */         this.menu.enter = true;
/*     */       }
/*     */     } 
/*     */     
/* 358 */     if (e.getKeyCode() == 27) {
/* 359 */       Sound.select.play();
/* 360 */       gameState = "MENU";
/* 361 */       Menu.pause = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent r) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 385 */     if (gameState == "NORMAL") {
/* 386 */       player.shoot = true;
/* 387 */       player.mx = e.getX() / 4;
/* 388 */       player.my = e.getY() / 4;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseDragged(MouseEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {
/* 404 */     this.mx = e.getX();
/* 405 */     this.my = e.getY();
/*     */   }
/*     */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\main\Game.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */