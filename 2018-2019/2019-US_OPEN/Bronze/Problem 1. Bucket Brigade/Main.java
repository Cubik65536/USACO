import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("buckets");

        int barnX = 0, barnY = 0, rockX = 0, rockY = 0, lakeX = 0, lakeY = 0;

        for (int i = 0; i < 10; i++) {
            char[] line = io.readLine().toCharArray();
            for (int j = 0; j < 10; j++) {
                if (line[j] == 'B') {
                    barnX = i;
                    barnY = j;
                } else if (line[j] == 'R') {
                    rockX = i;
                    rockY = j;
                } else if (line[j] == 'L') {
                    lakeX = i;
                    lakeY = j;
                } else;
            }
        }

        int distanceBarnToLake = Math.abs(barnX - lakeX) + Math.abs(barnY - lakeY);
        int distanceBarnToRock = Math.abs(barnX - rockX) + Math.abs(barnY - rockY);
        int distanceRockToLake = Math.abs(rockX - lakeX) + Math.abs(rockY - lakeY);

        if ((barnX == lakeX || barnY == lakeY) && distanceBarnToLake == distanceBarnToRock + distanceRockToLake) {
            io.println(distanceBarnToLake + 1);
        } else {
            io.println(distanceBarnToLake - 1);
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