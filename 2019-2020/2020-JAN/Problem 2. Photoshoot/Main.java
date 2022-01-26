import java.io.*;
import java.util.*;

public class Main {
    private static boolean isValid(int[] a) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= 0) {
                return false;
            }
            set.add(a[i]);
        }
        return set.size() == a.length;
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("photo");

        int n = io.nextInt();
        int[] b = new int[n];
        for (int i = 0; i < n - 1; i++) {
            b[i] = io.nextInt();
        }

        int[] d = new int[n];
        d[0] = 0;
        d[1] = 0;
        for (int i = 2; i < n; i++) {
            d[i] = b[i - 1] - b[i - 2];
        }

        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[0] = i;
            a[1] = b[0] - i;
            for (int j = 2; j < n; j++) {
                a[j] = a[j - 2] + d[j];
            }

            if (isValid(a)) {
                for (int j = 0; j < n - 1; j++) {
                    io.print(a[j] + " ");
                }

                io.print(a[n - 1]);

                io.close();

                return;

            }

        }

        for (int i = 0; i < n - 1; i++) {
            io.print(a[i] + " ");
        }

        io.print(a[n - 1]);

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