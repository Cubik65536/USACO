import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio();

        int k = io.nextInt();
		int n = io.nextInt();
		Map<String, Integer> members = new HashMap<>();
        for (int i = 0; i < n; i++) {
            members.put(io.next(), i);
        }

        char[][] result = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(result[i], '?');
            result[i][i] = 'B';
        }

        for (int i = 0; i < k; i++) {
            String[] publications = io.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                boolean alpabetical = true;
                for (int l = j + 1; l < n; l++) {
                    if (publications[l - 1].compareTo(publications[l]) > 0) {
                        alpabetical = false;
                    }
                    if (!alpabetical) {
                        int member1 = members.get(publications[j]);
                        int member2 = members.get(publications[l]);
                        result[member1][member2] = '0';
                        result[member2][member1] = '1';
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                io.print(result[i][j]);
            }
            io.println();
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