// Ningning Song
// CS141
// HW Core Topic: practice with arrays
// This program will play a solitaire game of Mancala.

import java.util.*;
public class SolitaireMancala {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      Random r = new Random();
   
      int goal = 1;
      int moveNum = 1;
   
      intro();
      int[] bucket = setupBoard(console, r);
      boolean isTrue = false;
      while(!isTrue) {  
         System.out.println("\n== Move #" + moveNum + " ==");
         printBoard(bucket,goal);
         goal = moveStones(bucket,goal,console);
         moveNum++;
         isTrue = isOver(bucket);
         
      }
      System.out.println("\nNice job! all stones moved to goal in " + (moveNum-1) + " moves.");
   } 
   
   // this mothoed is used for introducing the game.
   public static void intro() {
      System.out.println("This program will play a modified solitaire version of");
      System.out.println("the game of Mancala. Your goal is to get all the stones");
      System.out.println("from the buckets on the left into the goal on the right");
      System.out.println("in the least number of moves possible.\n");
   }

   // this method is for setup the game, ask buckets and stones from player.
   public static int[] setupBoard(Scanner console, Random r) {
      System.out.println("Before we begin the game, choose:");
	   System.out.print("        How many buckets do you want? > ");
      int numOfBuckets = console.nextInt();
	   System.out.print("        How many stones do you want? > "); 
      int stones = console.nextInt();
      System.out.println("\nOK, let's play..."); 
      
      int[] bucket = new int[numOfBuckets];
      for(int i = 1; i <= stones; i++) {
         int index = r.nextInt(numOfBuckets);
         bucket[index]++;
      }
      return bucket;
   }
   
   //this method is for testing is all the buckets are empty.
   public static boolean isOver(int[] bucket) {
      int sum = 0;
      for(int i = 0; i < bucket.length; i++) {
         sum += bucket[i];
      }
      return sum == 0;

   }
   
   //this method is for printout the play board
   public static void printBoard(int[] bucket, int goal) { 
      System.out.print("Stones: | ");
      for(int i = 0; i < bucket.length; i++) {
         System.out.print(bucket[i] + " | ");
      }
      System.out.println(">> " + goal + " <<");

      System.out.print("Buckets:");
      for(int i = 1; i <= bucket.length; i++) {
         System.out.print(" -" + i + "-");
      }
      System.out.println("    GOAL");


   }
   
   //this method is for moving stones after play pick a bucket each time. 
   public static int moveStones(int[] bucket, int goal, Scanner console) {
      System.out.print("Which buckets? > ");
      int num = console.nextInt();
 
      int stonesInHand = bucket[num-1];
      int count = stonesInHand;
      bucket[num-1] = 0;
      
      if(stonesInHand == 0) { //when the bucket the player chose is empty
         System.out.println("That bucket has no stones. 0 stones added to goal.");
      }
      
      if(stonesInHand > 0){// when the bucket has stones inside
         int i = 0;
         while(i < bucket.length- num && stonesInHand > 0) {//how stones move before filling the first goal
            bucket[num+i]++;
            stonesInHand--;
            i++;
         }
         while(stonesInHand > 0) {//how rest of the stones will move 
            goal++;
            stonesInHand--;
            if(stonesInHand > 0) {
               for(int j = 0; j < bucket.length; j++){
                  bucket[j] ++;
                  stonesInHand--;
                  if(stonesInHand == 0){
                     return goal;
                  }
               }
            }
               
         } 
       }         
         System.out.println(count + " stones moved. " + goal + " stones added to goal.");
 
         return goal; 
      } 
                
   }
      

/*
      ----jGRASP exec: java SolitaireMancala
 This program will play a modified solitaire version of
 the game of Mancala. Your goal is to get all the stones
 from the buckets on the left into the goal on the right
 in the least number of moves possible.
 
 Before we begin the game, choose:
         How many buckets do you want? > 3
         How many stones do you want? > 2
 
 OK, let's play...
 
 == Move #1 ==
 Stones: | 1 | 0 | 1 | >> 0 <<
 Buckets: -1- -2- -3-    GOAL
 Which buckets? > 1
 1 stones moved. 0 stones added to goal.
 
 == Move #2 ==
 Stones: | 0 | 1 | 1 | >> 0 <<
 Buckets: -1- -2- -3-    GOAL
 Which buckets? > 3
 1 stones moved. 1 stones added to goal.
 
 == Move #3 ==
 Stones: | 0 | 1 | 0 | >> 1 <<
 Buckets: -1- -2- -3-    GOAL
 Which buckets? > 2
 1 stones moved. 1 stones added to goal.
 
 == Move #4 ==
 Stones: | 0 | 0 | 1 | >> 1 <<
 Buckets: -1- -2- -3-    GOAL
 Which buckets? > 3
 1 stones moved. 2 stones added to goal.
 
 Nice job! all stones moved to goal in 4 moves.
 
  ----jGRASP: operation complete.

*/
      