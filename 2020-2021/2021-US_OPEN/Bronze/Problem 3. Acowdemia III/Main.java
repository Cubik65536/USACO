import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio();

        int n = io.nextInt();
        int m = io.nextInt();
        char[][] pastures = new char[n + 2][];
        pastures[0] = new char[m + 2];
        Arrays.fill(pastures[0], '.');
        pastures[n + 1] = pastures[0];
        for (int i = 1; i <= n; i++) {
            pastures[i] = ('.' + io.readLine() + '.').toCharArray();
        }

        int result = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (pastures[i][j] == 'G' && ((pastures[i][j - 1] == 'C' && pastures[i][j + 1] == 'C') || (pastures[i - 1][j] == 'C' && pastures[i + 1][j] == 'C'))) {
                    pastures[i][j] = '.';
                    result++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (pastures[i][j] == 'C') {
                    if (pastures[i + 1][j - 1] == 'C') {
                        if(pastures[i][j - 1] == 'G') {
                            pastures[i][j - 1] = '.';
                            result++;
                        } else if (pastures[i + 1][j] == 'G') {
                            pastures[i + 1][j] = '.';
                            result++;
                        }
                    }
                    if (pastures[i + 1][j + 1] == 'C') {
                        if (pastures[i][j + 1] == 'G') {
                            pastures[i][j + 1] = '.';
                            result++;
                        } else if (pastures[i + 1][j] == 'G') {
                            pastures[i + 1][j] = '.';
                            result++;
                        }
                    }
                }
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