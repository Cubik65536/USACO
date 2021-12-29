import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] swaps;

    private static int simulate(int startAt) {
        int score = 0;
        int placeOfPebble = startAt;
        for (int i = 0; i < n; i++) {
            if (swaps[i][0] == placeOfPebble) {
                placeOfPebble = swaps[i][1];
            } else if (swaps[i][1] == placeOfPebble) {
                placeOfPebble = swaps[i][0];
            }
            if (swaps[i][2] == placeOfPebble) {
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("shell");

        n = io.nextInt();
        swaps = new int[n][3];
        for (int i = 0; i < n; i++) {
            swaps[i][0] = io.nextInt();
            swaps[i][1] = io.nextInt();
            swaps[i][2] = io.nextInt();
        }

        int maxScore = Integer.MIN_VALUE;
        maxScore = Math.max(maxScore, simulate(1));
        maxScore = Math.max(maxScore, simulate(2));
        maxScore = Math.max(maxScore, simulate(3));

        io.println(maxScore);

        io.close();
    }
}

class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st;
    // standard input
    public Kattio() { this(System.in,System.out); }
    public Kattio(InputStream i, OutputStream o) {
        super(o);
        r = new BufferedReader(new InputStreamReader(i));
    }
    // USACO-style file input
    public Kattio(String problemName) throws IOException {
        super(new FileWriter(problemName+".out"));
        r = new BufferedReader(new FileReader(problemName+".in"));
    }
    // read next line
    public String readLine() {
        try {
            return r.readLine();
        } catch (IOException e) {}
        return null;
    }
    // returns null if no more input
    public String next() {
        try {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(r.readLine());
            return st.nextToken();
        } catch (Exception e) {}
        return null;
    }
    public int nextInt() { return Integer.parseInt(next()); }
    public double nextDouble() { return Double.parseDouble(next()); }
    public long nextLong() { return Long.parseLong(next()); }
}
