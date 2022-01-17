import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class Main {
    private static String[] animals = {
            "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse",
            "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat"
    };

    private static Map<String, Integer> whenBorn = new TreeMap<>();

    private static String getAnimal(int year) {
        int currAnimal = 0, currYear = 0;
        while (currYear < year) {
            currYear++;
            currAnimal++;
            if (currAnimal == 12) {
                currAnimal = 0;
            }
        }
        while (currYear > year) {
            currYear--;
            currAnimal--;
            if (currAnimal == -1) {
                currAnimal = 11;
            }
        }

        return animals[currAnimal];
    }

    public static void main(String[] args) {
        Kattio io = new Kattio();

        int n = io.nextInt();
        whenBorn.put("Bessie", 0);
        String cowA, born, in, relation, animal, year, from, cowB;
        for (int i = 0; i < n; i++) {
            cowA = io.next();
            born = io.next();
            in = io.next();
            relation = io.next();
            animal = io.next();
            year = io.next();
            from = io.next();
            cowB = io.next();

            whenBorn.put(cowA, whenBorn.get(cowB));

            do {
                if (relation.equals("previous")) {
                    whenBorn.put(cowA, whenBorn.get(cowA) - 1);
                } else {
                    whenBorn.put(cowA, whenBorn.get(cowA) + 1);
                }
            } while (!getAnimal(whenBorn.get(cowA)).equals(animal));
        }

        io.println(Math.abs(whenBorn.get("Elsie")));

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