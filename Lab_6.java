/* *******************************************************************************************
 * Program No.: 6
 * Question : Write a program to find the shortest path between vertices using bellman-ford algorithm
 * Explanation : The Bellman–Ford algorithm is an algorithm that computes shortest paths from a
 * single source vertex to all the other vertices in a weighted digraph.
 * Date : 28 - 10 - 2024
 ****************************************************************************************** */

import java.util.Scanner;

public class DVT_Lab_3 {
    public static void main(String[] args) {
        int[][] dist = new int[20][20];
        int[][] from = new int[20][20];
        int[][] costMat = new int[10][10];
        int i,j,k,nodes;
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the number of nodes : ");
        nodes = s.nextInt();

        System.out.println("Enter the cost matrix : ");
        for(i=1 ; i<=nodes ; i++) {
            for(j=1 ; j<=nodes ; j++) {
                costMat[i][j] = s.nextInt();
                costMat[i][i] = 0;
                dist[i][j] = costMat[i][j];
                from[i][j] = j;
            }
        }

        for(i=1 ; i<=nodes ; i++) {
            for(j=1 ; j<=nodes ; j++) {
                for(k=1 ; k<=nodes ; k++) {
                    if((dist[i][j])>dist[i][k]+dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        from[i][k] = k;
                    }
                }
            }
        }

        for (i = 1 ; i <= nodes ; i++){
            System.out.println("From Router Node : "+i);
            System.out.println("Destination Node\tNext-Hop\tDistance");
            for (j = 1; j <= nodes; j++) {
                System.out.println(j+"\t\t\t\t\t"+from[i][j]+"\t\t\t"+dist[i][j]);
            }
        }
        System.out.println("\n");
    }
}

/*
OUTPUT:

Enter the number of nodes : 
3
Enter the cost matrix : 
0 1 2
1 0 7
2 7 0
From Router Node : 1
Destination Node	Next-Hop	Distance
1					1			0
2					2			1
3					3			2
From Router Node : 2
Destination Node	Next-Hop	Distance
1					1			1
2					2			0
3					3			7
From Router Node : 3
Destination Node	Next-Hop	Distance
1					1			2
2					2			7
3					3			0

*/
