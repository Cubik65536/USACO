import java.io.*;
import java.util.*;

public class Main {
    private static List<Cow> cows;

    private static class Cow {
		int i;
		boolean infected;

		public Cow(int i, boolean infected) {
			this.i = i;
			this.infected = infected;
		}

	}

	private static int getR(List<Cow> list) {
		int r = Integer.MAX_VALUE;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).infected != list.get(i).infected) {
				r = Math.min(r, list.get(i).i - list.get(i - 1).i);
			}
		}
		return r;
	}

    private static int getCowSeperateByNotInfected() {
        int count = 0;

        for (int i = 1; i < cows.size(); i++) {
            if (cows.get(i).infected != cows.get(i - 1).infected && cows.get(i).infected) {
                System.out.println("(getCowSeperateByNotInfected) " + cows.get(i).i + " " + cows.get(i - 1).i);
                count++;
            }
        }

        return count;
    }

    private static int getCowBlocks(int r) {
        int count = 0;

        for (int i = 1; i < cows.size(); i++) {
            if (cows.get(i).i - cows.get(i - 1).i >= r && cows.get(i).infected && cows.get(i - 1).infected) {
                System.out.println("(getCowBlocks) " + cows.get(i).i + " " + cows.get(i - 1).i);
                count++;
            }
        }


        return count;
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("socdist2");

        int n = io.nextInt();
		cows = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			cows.add(new Cow(io.nextInt(), io.nextInt() == 1));
		}

		cows.sort(Comparator.comparingInt(cow -> cow.i));

		int r = getR(cows);
        System.out.println("r = " + r);

		io.println(getCowSeperateByNotInfected() + getCowBlocks(r) + 1);

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