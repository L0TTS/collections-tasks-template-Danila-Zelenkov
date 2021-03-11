package tasks.first;

import java.util.Scanner;

public class BFSManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FirstTaskSolution bfs = new FirstTaskSolution();
        int n = scanner.nextByte();
        scanner.nextLine();
        boolean[][] adjacencyMatrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            int nCol = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '0') {
                    adjacencyMatrix[i][nCol] = false;
                    nCol++;
                } else if (s.charAt(j) == '1') {
                    adjacencyMatrix[i][nCol] = true;
                    nCol++;
                }
            }
        }
        System.out.println("Choose the starting vertex");
        int v = scanner.nextInt();
        System.out.println(bfs.breadthFirst(adjacencyMatrix, v));
    }
}
