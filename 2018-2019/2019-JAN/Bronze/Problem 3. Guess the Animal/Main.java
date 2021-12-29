import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("guess");

        int n = io.nextInt();
        List<List<String>> characteristics = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> c = new ArrayList<>();
            String[] line = io.readLine().split(" ");
            for (int j = 2; j < line.length; j++) {
                c.add(line[j]);
            }
            characteristics.add(c);
        }

        int greatestCommon = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int common = 0;
                for (String s : characteristics.get(i)) {
                    for (String t : characteristics.get(j)) {
                        if (s.equals(t)) {
                            common++;
                        }
                    }
                }
                greatestCommon = Math.max(greatestCommon, common);
            }
        }

        io.println(greatestCommon + 1);

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
