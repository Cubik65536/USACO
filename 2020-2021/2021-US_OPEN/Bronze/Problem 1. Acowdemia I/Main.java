import java.io.*;
import java.util.*;

public class Main {
    private static int calculateH(Integer[] papers) {
        Arrays.sort(papers, Collections.reverseOrder());
        int h = 0;
        for (int i = 0; i < papers.length; i++) {
            if (papers[i] > i) {
                h++;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        Kattio io = new Kattio();

        int n = io.nextInt();
        int k = io.nextInt();
        Integer[] c = new Integer[n];
        for (int i = 0; i < n; i++) {
            c[i] = io.nextInt();
        }

        Arrays.sort(c, Collections.reverseOrder());

        int h = calculateH(c);
        if (h != n) {
            for (int i = h; i >= 0 && i > h - k; i--) {
                c[i]++;
            }
        }

        Arrays.sort(c, Collections.reverseOrder());
        io.println(calculateH(c));

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