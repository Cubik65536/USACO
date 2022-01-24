import java.io.*;
import java.util.*;

public class Main {
    private static boolean countAverage(List<Double> list) {
        double average = 0.0;
        for (int i = 0; i < list.size(); i++) {
            average += list.get(i);
        }
        average /= list.size();

        return list.contains(average);
    }

    public static void main(String[] args) {
        Kattio io = new Kattio();

        int n = io.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = io.nextInt();
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            List<Double> list = new ArrayList<>();
            for (int j = i; j < n; j++) {
                list.add((double) p[j]);
                result += countAverage(list) ? 1 : 0;
            }
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