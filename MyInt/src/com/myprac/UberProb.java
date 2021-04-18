package com.myprac;
import java.util.Collection;
import java.util.Scanner;

public class UberProb {

  public static int[] UberProb(int intArray[]){
     // int outArray[];
      int count ;

      int outArray[] = new int[intArray.length];
      //   System.out.println(intArray.length);

      for (int i=0;i<=intArray.length-1;i++){
          count = 1 ;
          for (int j = 0; j <=intArray.length-1; j++) {
              if (i != j){
                  count =   intArray[j] *count ;
              }
          }
          outArray[i] = count;
      }

      return outArray;
  }


    public static void main(String[] args) {
        int tot =0;
        long count  ;
        int b[];
        int intArray[] = {3,2,1};
        b = UberProb(intArray);
       // int intArray[] = {1,2,3,4,5};

       /* for (int i=0; i<=b.length-1; i++)
        {
            System.out.println(b[i]);
        }*/

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a starting number: ");
        int j = reader.nextInt(); // Scans the next token of the input as an int.
//once finished
       // reader.close();

        System.out.println("Enter a ending number: ");
        int k = reader.nextInt(); // Scans the next token of the input as an int.
//once finished
        reader.close();


       count = 0 ;
     for (int i =j; i <=k ; i++)
     {
         if (i % 2 > 0 ){
         String str = Integer.toString(i);

          count = count + str.length(); //.filter(ch -> ch == '3').count();

              }}
        System.out.println("No of odd digits are: " + count);
         }
     }





