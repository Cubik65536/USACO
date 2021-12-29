import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("blist");

        int n = io.nextInt();
        int[][] cows = new int[n][3];
        for (int i = 0; i < n; i++) {
            cows[i][0] = io.nextInt();
            cows[i][1] = io.nextInt();
            cows[i][2] = io.nextInt();
        }

        int[] bukkit = new int[1000];
        for (int i = 0; i < n; i++) {
            for (int j = cows[i][0] - 1; j < cows[i][1]; j++) {
                bukkit[j] += cows[i][2];
            }
        }

        Arrays.sort(bukkit);

        io.println(bukkit[999]);

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
