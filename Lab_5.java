/* *******************************************************************************************
 * Program No.: 5
 * Question : Develop a program to implement sliding window protocol in the data link layer
 * Explanation : A sliding window protocol is a feature of packet-based data transmission
 * protocols. Sliding window protocols are used where reliable in-order delivery of packets
 * is required, such as in the data link layer as well as in the Transmission Control Protocol.
 * Date : 25 - 10 - 2024
****************************************************************************************** */

import java.util.Random;
import java.util.Scanner;

public class SWP_Lab_2 {
    final private int windowSize;
    final private int totalFrames;
    private int ackFrames = 0;
    final private boolean[] receivedAck;

    public SWP_Lab_2(int windowSize, int totalFrames) {
        this.windowSize = windowSize;
        this.totalFrames = totalFrames;
        receivedAck = new boolean[totalFrames];
    }

    public void startTransmission() {
        while(ackFrames < totalFrames) {
            sendFrames();
            receiveAcknowledgements();
        }
        System.out.println("Transmission Complete!");
    }

    private void sendFrames() {
        System.out.println("Sending frames...");
        for (int i = ackFrames; i < Math.min(ackFrames + windowSize, totalFrames); i++) {
            if(!receivedAck[i]){
                System.out.println("Sent: Frame "+i);
            }
        }
    }

    private void receiveAcknowledgements() {
        Random random = new Random();

        for (int i = ackFrames; i < Math.min(ackFrames + windowSize, totalFrames); i++) {
            if(!receivedAck[i]){
                boolean isAcked = random.nextBoolean();

                if(isAcked){
                    receivedAck[i] = true;
                    System.out.println("Ack received for Frame "+i);
                    ackFrames++;
                }
                else {
                    System.out.println("No Ack received for Frame "+i+", retransmitting...");
                    break;  // retransmitting start from this frame
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter window size : ");
        int windowSize = scanner.nextInt();

        System.out.println("Enter total number of frames : ");
        int totalFrames = scanner.nextInt();

        SWP_Lab_2 protocol = new SWP_Lab_2(windowSize,totalFrames);
        protocol.startTransmission();

        scanner.close();
    }
}

/* *******************************************************************************************
 OUTPUT:
 
 Enter window size : 
 3
 Enter total number of frames : 
 4
 Sending frames...
 Sent: Frame 0
 Sent: Frame 1
 Sent: Frame 2
 No Ack received for Frame 0, retransmitting...
 Sending frames...
 Sent: Frame 0
 Sent: Frame 1
 Sent: Frame 2
 No Ack received for Frame 0, retransmitting...
 Sending frames...
 Sent: Frame 0
 Sent: Frame 1
 Sent: Frame 2
 Ack received for Frame 0
 Ack received for Frame 1
 Ack received for Frame 2
 No Ack received for Frame 3, retransmitting...
 Sending frames...
 Sent: Frame 3
 Ack received for Frame 3
 Transmission Complete!
 [ Output can vary each time program is executed ]
 *******************************************************************************************/
