package tasks.first;

import java.util.ArrayDeque;
import java.util.Scanner;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        Scanner scanner = new Scanner(System.in);
        String way = "";
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        int[] used = new int[adjacencyMatrix[0].length];
        int[] p = new int[adjacencyMatrix[0].length];
        arr.addFirst(startIndex);
        used[startIndex - 1] = 1;
        p[startIndex - 1] = -1;
        while (!arr.isEmpty()) {
            int v = arr.getFirst();
            arr.removeFirst();
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                if (adjacencyMatrix[v - 1][j] && used[j] == 0) {
                    used[j] = 1;
                    p[j] = v;
                    arr.addLast(j + 1);
                }
            }
        }
        System.out.println("Choose the number of final vertex");
        int finalV = scanner.nextInt();
        if (used[finalV - 1] == 0) {
            return "No way";
        }
        arr.addFirst(finalV);
        int cur = finalV - 1;
        while (p[cur] != -1) {
            arr.addFirst(p[cur]);
            cur = p[cur] - 1;
        }
        while (!arr.isEmpty()) {
            way += (arr.getFirst() + ", ");
            arr.removeFirst();
        }
        way = way.substring(0, way.length() - 1);
        return way;
    }

    @Override
    public Boolean validateBrackets(String s) {
        char[] arr = new char[s.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.charAt(i);
        }
        ArrayDeque<Character> openArr = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == '(' || arr[i] == '[' || arr[i] == '{') {
                openArr.addLast(arr[i]);
            }
            if (arr[i] == ')' || arr[i] == ']' || arr[i] == '}') {
                if (openArr.isEmpty()) {
                    return false;
                }
                if (isOk(arr[i], openArr.getLast())) {
                    return false;
                }
                openArr.removeLast();
            }
        }
        return true;
    }

    public boolean isOk(char firstC, char lastC) {
        if (firstC == ')' && lastC != '(') {
            return false;
        }
        if (firstC == ']' && lastC != '[') {
            return false;
        }
        if (firstC == '}' && lastC != '{') {
            return false;
        }
        return false;
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<Long> arrNum = new ArrayDeque<>();
        long curNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isNum(s.charAt(i))) {
                if (i > 0 && isNum(s.charAt(i - 1))) {
                    curNum *= 10;
                }
                curNum += (s.charAt(i) - 48);
            } else if (s.charAt(i) == ' ' && isNum(s.charAt(i - 1))) {
                arrNum.addLast(curNum);
                curNum = 0;
            }
            if (s.charAt(i) < '0' && s.charAt(i) != ' ') {
                additionETC(arrNum, s.charAt(i));
            }
        }
        return arrNum.element();
    }

    public boolean isNum(char numeral) {
        return numeral <= '9' && numeral >= '0';

    }

    public void additionETC(ArrayDeque<Long> arrStr, char numeral) {
        long last = arrStr.getLast();
        arrStr.removeLast();
        long postLast = arrStr.getLast();
        arrStr.removeLast();
        if (numeral == '*') {
            arrStr.addLast(postLast * last);
        }
        if (numeral == '/') {
            arrStr.addLast(postLast / last);
        }
        if (numeral == '+') {
            arrStr.addLast(postLast + last);
        }
        if (numeral == '-') {
            arrStr.addLast(postLast - last);
        }
    }
}
