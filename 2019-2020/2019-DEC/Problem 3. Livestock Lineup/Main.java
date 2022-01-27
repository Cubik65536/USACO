import java.io.*;
import java.util.*;

public class Main {
    final private static String[] COWS = {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};

    private static int n;
    private static int[][] list;

    private static boolean valid(int[] perm) {
        int[] indexOf = new int[perm.length];
        for (int i = 0; i < perm.length; i++) {
            indexOf[perm[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            if (Math.abs(indexOf[list[i][0]] - indexOf[list[i][1]]) != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean go(int[] perm, boolean[] used, int k) {
        if (k == perm.length) {
            return valid(perm);
        }
        for (int i = 0; i < perm.length; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[k] = i;
                if (go(perm, used, k + 1)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("lineup");

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < COWS.length; i++) {
            map.put(COWS[i], i);
        }

        n = io.nextInt();
        list = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] line = io.readLine().split(" ");
            list[i][0] = map.get(line[0]);
            list[i][1] = map.get(line[line.length - 1]);
        }

        int[] perm = new int[COWS.length];
        boolean[] used = new boolean[COWS.length];
        go(perm, used, 0);

        for (int i = 0; i < perm.length; i++) {
            io.println(COWS[perm[i]]);
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