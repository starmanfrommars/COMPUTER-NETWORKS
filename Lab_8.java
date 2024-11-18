/* *******************************************************************************************
 * Program No.: 8
 * Question : Write a program on datagram socket for client/server to display the messages on client side,
 * typed at the server side.
 * Explanation : A datagram socket provides a symmetric data exchange interface without requiring
 * connection establishment. Each message carries the destination address.
 * Date : 15 - 11 - 2024
 ****************************************************************************************** */

package Lab8;

import java.io.*;
import java.net.*;
import java.util.*;

public class DSender {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message and press ENTER to Send : ");
        String str = s.nextLine();
        InetAddress ip = InetAddress.getByName("127.0.0.1");

        DatagramPacket dp = new DatagramPacket(str.getBytes(),str.length(),ip,21);
        ds.send(dp);
        ds.close();
    }
}

/*

OUTPUT:

Enter the message and press ENTER to Send :
Java is a OOP Language.

*/

////////////////////////////////////////////////////////////////////////////////////////////////

package Lab8;

import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(21);
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf,1024);

        // System.out.println("Waiting for a message...");
        ds.receive(dp);

        String str = new String(dp.getData(),0,dp.getLength());
        System.out.println("Message from server");
        System.out.println(str);
        ds.close();
    }
}

/*

OUTPUT:

Message from server
Java is a OOP Language.

*/
