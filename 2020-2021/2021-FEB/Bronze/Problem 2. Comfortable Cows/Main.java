import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static boolean[][] pasture = new boolean[1001][1001];
    private static int dX[] = {-1, 1, 0, 0};
    private static int dY[] = {0, 0, -1, 1};

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static boolean isComfortable(int x, int y) {
        if (!pasture[x][y]) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (isValid(x + dX[i], y + dY[i])) {
                if (pasture[x + dX[i]][y + dY[i]]) {
                    count++;
                }
            }
        }
        return count == 3;
    }

    public static void main(String[] args) {
        Kattio io = new Kattio();

        n = io.nextInt();
        int comfortable = 0;
        for (int i = 0; i < n; i++) {
            int x = io.nextInt();
            int y = io.nextInt();
            for (int j = 0; j < 4; j++) {
                if (isValid(x + dX[j], y + dY[j])) {
                    comfortable -= isComfortable(x + dX[j], y + dY[j]) ? 1 : 0;
                }
            }
            pasture[x][y] = true;
            for (int j = 0; j < 4; j++) {
                if (isValid(x + dX[j], y + dY[j])) {
                    comfortable += isComfortable(x + dX[j], y + dY[j]) ? 1 : 0;
                }
            }
            comfortable += isComfortable(x, y) ? 1 : 0;
            io.println(comfortable);
        }

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