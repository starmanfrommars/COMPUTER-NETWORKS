/* *******************************************************************************************
 * Program No.: 10
 * Question : Develop a program for congestion control using a leaky bucket algorithm.
 * Explanation : The leaky bucket is an algorithm based on an analogy of how a bucket with a
 * constant leak will overflow if either the average rate at which water is poured in exceeds
 * the rate at which the bucket leaks or if more water than the capacity of the bucket is
 * poured in all at once
 * Date : 14 - 11 - 2024
 ****************************************************************************************** */

import java.util.Scanner;

public class Lab_10 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int i,size,nop,opr,temp;
        int[] dataRate = new int[100];

        System.out.println("Enter the bucket size : ");
        size = sc.nextInt();

        System.out.println("Enter the number of packets : ");
        nop = sc.nextInt();

        System.out.println("Enter the dara rate : ");
        for(i=0;i<nop;i++)
            dataRate[i]=sc.nextInt();

        System.out.println("enter the output rate : ");
        opr=sc.nextInt();

        for(i=0;i<nop;i++) {
            if(dataRate[i]>size) {
                System.out.println("Bucket Overflow");
            }
            else {
                temp=dataRate[i];

                while(temp>opr) {
                    System.out.println("packet transmission "+opr);
                    temp=temp-opr;
                }
                System.out.println("packet transmission "+temp);
            }
        }
    }
}
