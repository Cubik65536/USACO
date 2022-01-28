import java.io.*;
import java.util.*;

public class Main {
    private static class Cow {
        public int i;
        public int x;
        public int y;
        public int stop;

        public Cow(int i, int x, int y, int stop) {
            this.i = i;
            this.x = x;
            this.y = y;
            this.stop = stop;
        }

    }

    public static void main(String[] args) {
        Kattio io = new Kattio();

        List<Cow> eastCows = new ArrayList<>(), northCows = new ArrayList<>();

        int n = io.nextInt();
        for (int i = 0; i < n; i++) {
            String direction = io.next();
            int x = io.nextInt();
            int y = io.nextInt();

            if (direction.equals("E")) {
                eastCows.add(new Cow(i, x, y, Integer.MAX_VALUE));
            } else if (direction.equals("N")) {
                northCows.add(new Cow(i, x, y, Integer.MAX_VALUE));
            }

        }

        eastCows.sort(Comparator.comparingInt(cow -> cow.y));
        northCows.sort(Comparator.comparingInt(cow -> cow.x));

        for (Cow eastCow : eastCows) {
            for (Cow northCow : northCows) {
                if (eastCow.stop != Integer.MAX_VALUE || northCow.stop != Integer.MAX_VALUE) {
                    continue;
                }
                if (eastCow.x >= northCow.x || northCow.y >= eastCow.y) {
                    continue;
                }
                if (northCow.x - eastCow.x == eastCow.y - northCow.y) {
                    continue;
                }
                if (northCow.x - eastCow.x > eastCow.y - northCow.y) {
                    eastCow.stop = northCow.x;
                } else {
                    northCow.stop = eastCow.y;
                }
            }
        }

        String[] result = new String[n];
        for (Cow eastCow : eastCows) {
            if (eastCow.stop == Integer.MAX_VALUE) {
                result[eastCow.i] = "Infinity";
            } else {
                result[eastCow.i] = String.valueOf(eastCow.stop - eastCow.x);
            }
        }

        for (Cow northCow : northCows) {
            if (northCow.stop == Integer.MAX_VALUE) {
                result[northCow.i] = "Infinity";
            } else {
                result[northCow.i] = String.valueOf(northCow.stop - northCow.y);
            }
        }

        for (String s : result) {
            io.println(s);
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