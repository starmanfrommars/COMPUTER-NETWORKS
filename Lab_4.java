/********************************************************************************************
 * Program No. : 4
 * Question : Develop a program for error detecting code using CRC-CCITT (16- bits)
 * Explanation : CRC-CCITT is a 16-bit Cyclic Redundancy Check (CRC) algorithm that uses a standard
 * polynomial and an initial value to generate a code that confirms the correct retrieval
 * of information
 * Date : 17 - 10 - 2024
 *******************************************************************************************/

import java.util.Scanner;

public class Crc_Lab_1 {

    // Develop a program for error detecting code using CRC - CCITT [ 16 BIT ]
    static int[] data, cs;
    static int[] g = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};  // CRC-16 : x^16 + x^12 + x^5 + 1
    static int n=0, i, e, pos;
    static final int N = 17;

    // XOR operation for CRC
    static void xor() {
        for( int c=0 ; c<N ; c++ ){
            cs[c] = ((cs[c] == g[c])? 0 : 1);
        }
    }

    // Method to calculate CRC
    static void crc() {
        for (i = 0; i < N; i++) {
            cs[i] = data[i];  // Copy initial bits
        }
        do {
            if(cs[0] == 1) xor();  // Perform XOR if the leftmost bit is 1
            for (int c = 0; c < N - 1; c++) { // Shift left
                cs[c] = cs[c+1];  // Bring in the next data bit
            }
            cs[N-1] = data[i++];
        }while(i <= n + N-1);
    }

    public static void main(String[] args) {
        Scanner br = new Scanner(System.in);

        System.out.println("Enter number of Data bits : ");
        n = br.nextInt();
        data = new int[n+N];  // Extra space for checksum bits
        cs = new int[N];  // Initialise the checksum array with size N

        System.out.println("\nEnter the data bits : ");
        for (int i = 0; i < n; i++) {
            data[i] = br.nextInt();
        }

        // Append N-1 zeros to data ( to make room for checksum )
        for (int i = n; i < n + N - 1; i++) {
            data[i] = 0;
        }

        // show CRC divisor ( generator polynomial )
        System.out.println("\nCRC Divisor : ");
        for (int i = 0; i < N; i++) {
            System.out.print(g[i]);
        }

        //Perform crc operation
        crc();

        // Show CRC checksum
        System.out.println("\n\nCRC Checksum is : ");
        for (int i = 0; i < N - 1; i++) {
            System.out.print(cs[i]);
        }

        // Append checksum to original data
        for (int i = n; i < n + N - 1; i++) {
            data[i] = cs[i-n];
        }

        // Display the final codeword ( data + CRC )
        System.out.println("\n\nFinal codeword is : ");
        for (int i = 0; i < n + N - 1; i++) {
            System.out.print(data[i]);
        }

        // Test error detection
        System.out.println("\n\nTest Error detection [ 0(yes)/1(n)? : ");
        e = br.nextInt();

        if(e==0){
            System.out.println("Enter the position where error is to be inserted : ");
            pos = br.nextInt();
            data[pos] = (data[pos]==0)? 1 : 0; // Toggle the bit to simulate the error

            System.out.println("\nErroneous data : ");
            for (int i = 0; i < data.length ; i++) {
                System.out.print(data[i]);
            }
        }

        System.out.println("check point");

        // perform CRC check on received data
        crc();

        System.out.println("\n\nReceiver Checksum : ");
        for (int i = 0; i < N - 1; i++) {
            System.out.print(cs[i]);
        }

        // Check for errors
        for (int i = 0; i < N-1; i++) {
            if(cs[i] != 0){
                System.out.println("\n\nERROR in Received Codeword : ");
                System.exit(0);
            }
        }
        System.out.println("\nNo Error is Received Codeword : ");
    }
}
