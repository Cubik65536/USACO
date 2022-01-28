import java.io.*;
import java.util.*;

public class Main {
    private static int[] findLargestGap (String s, int start) {
        int largestGap = 0, currentStart = -1, n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (currentStart != -1 && i - currentStart > largestGap) {
                    largestGap = i - currentStart;
                    start = currentStart;
                }
                currentStart = i;
            }
        }
        return new int[]{start, largestGap};
    }

    private static int findSmallestGap (String s) {
        int smallestGap = Integer.MAX_VALUE, currentStart = -1, n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (currentStart != -1 && i - currentStart < smallestGap) {
                    smallestGap = i - currentStart;
                }
                currentStart = i;
            }
        }
        return smallestGap;
    }

    private static int tryCowInLargestGap (String s) {
        int[] result = findLargestGap(s, 0);
        int start = result[0], largestGap = result[1];
        if (largestGap >= 2) {
            char[] newS = s.toCharArray();
            newS[start + largestGap / 2] = '1';
            return findSmallestGap(new String(newS));
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("socdist1");

        int n = io.nextInt();
        String s = io.next();

        int result = 0;

        int[] temp = findLargestGap(s, 0);
        int start = temp[0], largestGap = temp[1];
        if (largestGap >= 3) {
            char[] tempS = s.toCharArray();
            tempS[start + largestGap / 3] = '1';
            tempS[start + largestGap / 3 * 2] = '1';
            result = Math.max(result, findSmallestGap(new String(tempS)));
        }

        if (s.charAt(0) == '0' && s.charAt(s.length() - 1) == '0') {
            char[] tempS = s.toCharArray();
            tempS[0] = '1';
            tempS[s.length() - 1] = '1';
            result = Math.max(result, findSmallestGap(new String(tempS)));
        }

        if (s.charAt(0) == '0') {
            char[] tempS = s.toCharArray();
            tempS[0] = '1';
            result = Math.max(result, tryCowInLargestGap(new String(tempS)));
        }

        if (s.charAt(s.length() - 1) == '0') {
            char[] tempS = s.toCharArray();
            tempS[s.length() - 1] = '1';
            result = Math.max(result, tryCowInLargestGap(new String(tempS)));
        }

        if (largestGap >= 2) {
            char[] tempS = s.toCharArray();
            tempS[start + largestGap / 2] = '1';
            result = Math.max(result, tryCowInLargestGap(new String(tempS)));
        }

        io.println(result);

        io.close();
    }
}

class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st;

    // standard input
    public Kattio() {
        this(System.in, System.out);
    }

    public Kattio(InputStream i, OutputStream o) {
        super(o);
        r = new BufferedReader(new InputStreamReader(i));
    }

    // USACO-style file input
    public Kattio(String problemName) throws IOException {
        super(new FileWriter(problemName + ".out"));
        r = new BufferedReader(new FileReader(problemName + ".in"));
    }

    // read next line
    public String readLine() {
        try {
            return r.readLine();
        } catch (IOException e) {
        }
        return null;
    }

    // returns null if no more input
    public String next() {
        try {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(r.readLine());
            return st.nextToken();
        } catch (Exception e) {
        }
        return null;
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}