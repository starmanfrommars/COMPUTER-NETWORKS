/* *******************************************************************************************
 * Program No.: 9
 * Question : Write a program for simple RSA algorithm to encrypt and decrypt the data.
 * Explanation : RSA is a public-key crypto system, one of the oldest widely used for secure
 * data transmission. The initialism "RSA" comes from the surnames of Ron Rivest, Adi Shamir
 * and Leonard Adleman, who publicly described the algorithm in 197
 * Date : 14 - 11 - 2024
 ****************************************************************************************** */

import java.io.*;
import java.util.*;

public class Lab_9 {
    static int multiply(int x, int y, int n) {
        int k = 1;
        int j;
        for (j = 1; j <=y ; j++) {
            k = ( k * x ) % n;
        }
        return k;
    }

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);

        String msg1;
        int[] pt = new int[100];
        int[] ct = new int[100];
        int n,d,e,z,p,q,i;
        System.out.println("Enter prime no.s p, q : ");
        p = s.nextInt();
        q = s.nextInt();
        n = p * q;
        z = (p-1) * (q-1);

        System.out.println("Select e value : ");
        e = s.nextInt();

        System.out.println("Enter message : ");
        msg1 = br.readLine();
        char[] msg = msg1.toCharArray();

        for ( i = 0; i < msg.length; i++) {
            pt[i] = msg[i];
        }
        for (d = 1; d < z; ++d) {
            if(((e*d)%z)==1) break;
        }
        System.out.println("p="+p+"\tq="+q+"\tn="+n+"\tz="+z+"\te="+e+"\td="+d);

        System.out.println("Cipher Text = ");
        for (i = 0; i < msg.length; i++) {
            ct[i] = multiply(pt[i],e,n);
        }
        for ( i = 0; i < msg.length; i++) {
            System.out.print("\t"+ct[i]);
        }
        System.out.println("Plain Text = ");
        for ( i = 0; i < msg.length ; i++) {
            pt[i] = multiply(ct[i],d,n);
        }
        for ( i = 0; i < msg.length; i++) {
            System.out.print((char)pt[i]);
        }
    }
}
