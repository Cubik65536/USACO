import java.io.*;
import java.util.*;

public class Main {
    private static boolean[] cowInfected = new boolean[101];
    private static int N;
    private static int[] cowX = new int[251], cowY = new int[251];

    private static boolean isValid (int patientZero, int K) {
        boolean[] infected = new boolean[101];
        int[] handshaken = new int[101];

        infected[patientZero] = true;

        for (int t = 0; t <= 250; t++) {
            int x = cowX[t];
            int y = cowY[t];
            if (x > 0) {
                if (infected[x]) {
                    handshaken[x]++;
                }
                if (infected[y]) {
                    handshaken[y]++;
                }
                if (handshaken[x] <= K && infected[x]) {
                    infected[y] = true;
                }
                if (handshaken[y] <= K && infected[y]) {
                    infected[x] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (infected[i] != cowInfected[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("tracing");

        N = io.nextInt();
        int T = io.nextInt();

        char[] s = io.next().toCharArray();
        for (int i = 1; i <= N; i++) {
            cowInfected[i] = s[i - 1] == '1';
        }

        for (int i = 0; i < T; i++) {
            int t = io.nextInt();
            int x = io.nextInt();
            int y = io.nextInt();
            cowX[t] = x;
            cowY[t] = y;
        }

        boolean[] possiblePatientZeros = new boolean[101];
        boolean[] possibleKs = new boolean[252];
        for (int i = 1; i <= N; i++) {
            for (int k = 0; k <= 251; k++) {
                if (isValid(i, k)) {
                    possiblePatientZeros[i] = true;
                    possibleKs[k] = true;
                }
            }
        }

        int maxK = 0, minK = 251, patientZero = 0;
        for (int k = 0; k <= 251; k++) {
            if (possibleKs[k]) {
                maxK = k;
            }
        }
        for (int k = 251; k >= 0; k--) {
            if (possibleKs[k]) {
                minK = k;
            }
        }
        for (int i = 1; i <= N; i++) {
            if (possiblePatientZeros[i]) {
                patientZero++;
            }
        }

        String result = patientZero + " " + minK + " ";
        if (maxK == 251) {
            result += "Infinity";
        } else {
            result += maxK;
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