/*    */ package com.emilyzinha.world;
/*    */ 
/*    */ public class Camera
/*    */ {
/*  5 */   public static int x = 0;
/*  6 */   public static int y = 0;
/*    */   
/*    */   public static int clamp(int Atual, int Min, int Max) {
/*  9 */     if (Atual < Min) {
/* 10 */       Atual = Min;
/*    */     }
/* 12 */     if (Atual > Max) {
/* 13 */       Atual = Max;
/*    */     }
/* 15 */     return Atual;
/*    */   }
/*    */ }


/* Location:              C:\Users\emily\Downloads\SnowWar Game\SnowWar Game\!\com\emilyzinha\world\Camera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */