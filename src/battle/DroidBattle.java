package battle;

import item.Item;
import com.droid.Droid;
import com.droid.PrintResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DroidBattle {
    //--------------------------Lists---------------------------------
    private Team obj;
    private Fight fight = new Fight();
    List<Droid> droids = chooseDroid();
    List<Item> items = chooseItem();
    List<Droid> team1 = new ArrayList<>();
    List<Droid> team2 = new ArrayList<>();
    private List<Droid> chooseDroid() {
        List<Droid> droid = new ArrayList<>();
        droid.add(new Droid("Yasuo", 35, 150, 50, 1));
        droid.add(new Droid("Zed", 40, 100, 50, 2));
        droid.add(new Droid("Miss Fortune", 20, 200, 50, 3));
        droid.add(new Droid("Hecarim", 50, 90, 50, 4));
        droid.add(new Droid("Kha'Zix", 80, 50, 90, 5));
        droid.add(new Droid("Mordekaiser", 30, 100, 200, 6));
        return droid;
    }
    private List<Item> chooseItem() {
        List<Item> item = new ArrayList<>();
        item.add(new Item("Riftmaker", 10, 30, 0));
        item.add(new Item("Crystal Cricketer", 5, 50, -1));
        item.add(new Item("Thornmail", 40, 10, 1));
        item.add(new Item("Demonic Embrace", 23, -15, 1));
        return item;
    }
    public void battle() throws InterruptedException {
        System.out.println();
        Scanner scan = new Scanner(System.in);
        int num;

        System.out.print("""
                Menu:\s
                1. Create new droid(1 vs 1)
                2. Choose droids(1 vs 1)
                3. Team fight
                4. Print from file
                """);

        num = scan.nextInt();
        if (num == 1) {

            fight.createForPvP(items);

        } else if (num == 2) {


            fight.chooseForPvp(droids, items);

        } else if (num == 3) {
            obj.teams(team1, team2, droids);
            this.fight.teamFight(team1, team2);
        } else {
            PrintResult file = new PrintResult();
            file.PrintFight();
        }
    }

    public static class TeeOutputStream extends PrintStream {
        private final ByteArrayOutputStream second;

        public TeeOutputStream(OutputStream main, ByteArrayOutputStream second) {
            super(main);
            this.second = second;
        }

        /**
         * Closes the main stream.
         * The second stream is just flushed but <b>not</b> closed.
         * @see java.io.PrintStream#close()
         */
        @Override
        public void close() {
            // just for documentation
            super.close();
        }

        @Override
        public void flush() {
            super.flush();
            try {
                second.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            super.write(buf, off, len);
            second.write(buf, off, len);
        }

        @Override
        public void write(int b) {
            super.write(b);
            second.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            super.write(b);
            second.write(b);
        }
    }
}


