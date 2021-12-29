import java.io.*;
import java.util.*;

public class Main {
    private static List<Integer> possibilities = new ArrayList<>();

    private static void simulateTuesday(int bukkit, List<Integer> firstBarn, List<Integer> secondBarn,
                                        int firstVolume, int secondVolume) {
        List<Integer> currentFirstBarn = new ArrayList<>(firstBarn);
        List<Integer> currentSecondBarn = new ArrayList<>(secondBarn);
        int currentFirstVolume = firstVolume;
        int currentSecondVolume = secondVolume;
        currentFirstBarn.remove(currentFirstBarn.indexOf(bukkit));
        currentSecondBarn.add(bukkit);
        currentFirstVolume -= bukkit;
        currentSecondVolume += bukkit;
        for (int i = 0; i < currentSecondBarn.size(); i++) {
            simulateWednesday(currentSecondBarn.get(i), currentFirstBarn, currentSecondBarn, currentFirstVolume, currentSecondVolume);
        }
    }

    private static void simulateWednesday(int bukkit, List<Integer> firstBarn, List<Integer> secondBarn,
                                        int firstVolume, int secondVolume) {
        List<Integer> currentFirstBarn = new ArrayList<>(firstBarn);
        List<Integer> currentSecondBarn = new ArrayList<>(secondBarn);
        int currentFirstVolume = firstVolume;
        int currentSecondVolume = secondVolume;
        currentFirstBarn.add(bukkit);
        currentSecondBarn.remove(currentSecondBarn.indexOf(bukkit));
        currentFirstVolume += bukkit;
        currentSecondVolume -= bukkit;
        for (int i = 0; i < currentFirstBarn.size(); i++) {
            simulateThursday(currentFirstBarn.get(i), currentFirstBarn, currentSecondBarn, currentFirstVolume, currentSecondVolume);
        }

    }

    private static void simulateThursday(int bukkit, List<Integer> firstBarn, List<Integer> secondBarn,
                                        int firstVolume, int secondVolume) {
        List<Integer> currentFirstBarn = new ArrayList<>(firstBarn);
        List<Integer> currentSecondBarn = new ArrayList<>(secondBarn);
        int currentFirstVolume = firstVolume;
        int currentSecondVolume = secondVolume;
        currentFirstBarn.remove(currentFirstBarn.indexOf(bukkit));
        currentSecondBarn.add(bukkit);
        currentFirstVolume -= bukkit;
        currentSecondVolume += bukkit;
        for (int i = 0; i < currentSecondBarn.size(); i++) {
            simulateFriday(currentSecondBarn.get(i), currentFirstBarn, currentSecondBarn, currentFirstVolume, currentSecondVolume);
        }
    }

    private static void simulateFriday(int bukkit, List<Integer> firstBarn, List<Integer> secondBarn,
                                        int firstVolume, int secondVolume) {
        List<Integer> currentFirstBarn = new ArrayList<>(firstBarn);
        List<Integer> currentSecondBarn = new ArrayList<>(secondBarn);
        int currentFirstVolume = firstVolume;
        int currentSecondVolume = secondVolume;
        currentFirstBarn.add(bukkit);
        currentSecondBarn.remove(currentSecondBarn.indexOf(bukkit));
        currentFirstVolume += bukkit;
        currentSecondVolume -= bukkit;
        if (!possibilities.contains(currentFirstVolume)) {
            possibilities.add(currentFirstVolume);
        }
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("backforth");

        int firstVolume = 1000;
        int secondVolume = 1000;
        List<Integer> firstBarn = new ArrayList<>();
        List<Integer> secondBarn = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            firstBarn.add(io.nextInt());
        }
        for (int i = 0; i < 10; i++) {
            secondBarn.add(io.nextInt());
        }

        for (int i = 0; i < firstBarn.size(); i++) {
            simulateTuesday(firstBarn.get(i), firstBarn, secondBarn, firstVolume, secondVolume);
        }

        io.println(possibilities.size());

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
