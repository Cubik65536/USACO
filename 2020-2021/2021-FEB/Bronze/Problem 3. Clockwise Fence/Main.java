import java.io.*;
import java.util.*;

public class Main {
    private static int heading(char direction) {
        switch (direction) {
            case 'N': return 0;
            case 'E': return 90;
            case 'S': return 180;
            case 'W': return 270;
        }
        return Integer.MIN_VALUE;
    }

    private static int change(char directionA, char directionB) {
        int headingA = heading(directionA);
        int headingB = heading(directionB);
        if (headingA == headingB) {
            return 0;
        } else if ((headingA + 90) % 360 == headingB) {
            return 90;
        } else if ((headingA + 270) % 360 == headingB) {
            return -90;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    private static String solve(String s) {
        int solution = 0;
        for (int i = 0; i < s.length(); i++) {
            solution += change(s.charAt(i), s.charAt((i + 1) % s.length()));
        }
        if (solution == 360) {
            return "CW";
        } else {
            return "CCW";
        }
    }

    public static void main(String[] args) {
        Kattio io = new Kattio();

        int n = io.nextInt();
        for (int i = 0; i < n; i++) {
            io.println(solve(io.next()));
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