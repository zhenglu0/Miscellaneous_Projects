// http://www.mathcs.emory.edu/~cheung/Courses/
// 323/Syllabus/Text/Matching-Boyer-Moore2.html
/* --------------------------------
   Code by S.Y.Cheung
   -------------------------------- */

import java.util.*;

public class Horspool
{

  /* ===================================================================
     buildLastFunction(P): find the last occurrence of each char
			   in pattern P

     (Without using P[m-1] !)
     =================================================================== */
   public static int[] computeLastOcc(String P) 
   {
      int[] lastOcc = new int[128]; // assume ASCII character set 
 
      for (int i = 0; i < 128; i++) 
      {
         lastOcc[i] = -1; // initialize all elements to -1
      }
 
      /* ===================================================
	 Horspool omits P[m-1] to compute lastOcc[]
	 =================================================== */
      for (int i = 0; i < P.length()-1; i++) 
      {
         lastOcc[P.charAt(i)] = i; // The LAST value will be store
      }
 
      return lastOcc;
   }


   public static int BMH (String T, String P) 
   {
     int[] lastOcc;
     int   i0, j, m, n;

     n = T.length();
     m = P.length();

     lastOcc = computeLastOcc(P);  // Find last occurence of all characters in P

     printLastOcc(lastOcc);

     i0 = 0;         // Line P up at T[0]

     while ( i0 < (n-m) )
     {
        j = m-1;	// Start at the last char in P

        System.out.println("+++++++++++++++++++++++++++++++++++++");
        printState( T, P, i0+j, j);

	while ( P.charAt(j) == T.charAt(i0+j) )
	{
	   j--;		// Check "next" (= previous) character

           printState( T, P, i0+j, j);

	   if ( j < 0 )
	      return (i0);	// P found !
        }

        /* =============================================
	   Use T[i0+(m-1)] to slide
	   ============================================= */
	i0 = i0 + (m-1) - lastOcc[T.charAt(i0+(m-1))];
    } 

     return -1; // no match
  }


  public static void printLastOcc(int[] lastOcc)
  {
     int i, j=0;

     System.out.println();
     for (i = (int)'a'; i <= (int)'z'; i++)
     {
        System.out.print((char)i + " " + lastOcc[i] + "; ");
        if (++j % 13 == 0)
	   System.out.println();
     }
     System.out.println();
     System.out.println();
  }


  public static void main(String[] args)
  {
     Scanner in = new Scanner(System.in);

     String T, P;
     int r;

     System.out.println("Try");
     System.out.println("T = abacaxbaccabacbbaabb");
     System.out.println("P = abacbb");
     System.out.println();

     System.out.print("T = ");
     T = in.next();
     System.out.print("P = ");
     P = in.next();

     System.out.println();
     System.out.println("012345678901234567890");
     System.out.println(T);
     System.out.println("012345678901234567890");
     System.out.println(P);
     System.out.println();

     r = BMH (T, P);

     System.out.println("Found " + P + " at pos: " + r);

  }



   /* =====================================================
      Variables and Methods to make the algorithm visual
      ===================================================== */
   public static String T_ruler, P_ruler;

   public static String ruler(int n)
   {
      String out = "";
      char   x = '0';

      for ( int i = 0; i < n; i++ )
      {
         out = out + x;
	 x++;
	 if ( x > '9' )
	    x = '0';
      }

      return out;
   }

   public static void printState(String T, String P, int i, int j)
   {
      if ( T_ruler  == null )
	 T_ruler = ruler( T.length() );

      if ( P_ruler  == null )
	 P_ruler = ruler( P.length() );

      System.out.println("=====================================");
      System.out.println("Matching: i = " + i + ", j = " + j);

      System.out.println("   " + T_ruler );
      System.out.println("   " + T);
      System.out.print("   ");
      for ( int k = 0; k < i-j; k++)
         System.out.print(" ");
      System.out.println(P);

      System.out.print("   ");
      for ( int k = 0; k < i-j; k++)
         System.out.print(" ");
      System.out.println( P_ruler );

      System.out.print("   ");
      for ( int k = 0; k < i; k++)
         System.out.print(" ");
      System.out.println("^");

      System.out.print("   ");
      for ( int k = 0; k < i; k++)
         System.out.print(" ");
      System.out.println("|");
      System.out.println();
   }




}
