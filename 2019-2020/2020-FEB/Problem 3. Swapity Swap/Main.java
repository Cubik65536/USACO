import java.io.*;
import java.util.*;

public class Main {
    private static int[] line;

    private static void swap(int start, int end) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = end; i >= start; i--) {
            temp.add(line[i]);
        }
        for (int i = start; i <= end; i++) {
            line[i] = temp.get(i - start);
        }
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("swap");

        int n = io.nextInt();
        line = new int[n];
        for (int i = 0; i < n; i++) {
            line[i] = i + 1;
        }

        int k = io.nextInt();
        int a1 = io.nextInt() - 1, a2 = io.nextInt() - 1, b1 = io.nextInt() - 1, b2 = io.nextInt() - 1;

        int cycleSize = 0;
        boolean sorted = true;
        do {
            cycleSize++;
            swap(a1, a2);
            swap(b1, b2);
            sorted = true;
            for (int i = 0; sorted && i < n; i++) {
                sorted = line[i] == i + 1;
            }
        } while (!sorted);

        k %= cycleSize;
        for (int i = 0; i < k; i++) {
            swap(a1, a2);
            swap(b1, b2);
        }

        for (int i = 0; i < n; i++) {
            io.println(line[i]);
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