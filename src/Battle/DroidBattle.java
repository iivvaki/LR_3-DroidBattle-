package Battle;

import Battle.BattleArea;
import Item.Item;
import com.droid.Droid;
import com.droid.PrintResult;
import Battle.BattleArea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DroidBattle {
//--------------------------Lists---------------------------------
    List<Droid> droids = chooseDroid();
    private List<Droid> chooseDroid(){
        List<Droid> droid = new ArrayList<>();
        droid.add(new Droid("Yasuo", 35, 150, 50,1));
        droid.add(new Droid("Zed", 40, 100, 50,2));
        droid.add(new Droid("Miss Fortune", 20, 200, 50,3));
        droid.add(new Droid("Hecarim", 50, 90, 50,4));
        droid.add(new Droid("Kha'Zix", 80, 50, 90,5));
        droid.add(new Droid("Mordekaiser", 30, 100, 200,6));
        return droid;
    }

    List<Item> items = chooseItem();
    private List<Item> chooseItem() {
        List<Item> item = new ArrayList<>();
        item.add(new Item("Riftmaker", 10, 30, 0));
        item.add(new Item("Crystal Cricketer", 5, 50, -1));
        item.add(new Item("Thornmail", 40, 10, 1));
        item.add(new Item("Demonic Embrace", 23, -15, 1));
        return item;
    }

    List<Droid> team1 = new ArrayList<>();
    List<Droid> team2 = new ArrayList<>();

//--------------------------Items---------------------------------
    private Item choseItemForDroid() {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter item name: ");
        return findItem(in.nextLine());
    }
    public Item findItem(String name) {

        String itemName;

        for (Item item : items) {
            itemName = item.getName();
            if (itemName.equals(name)) {
                return item;
            }
        }
        return null;
    }
//--------------------------Droids---------------------------------
    private Droid selectDroid() {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter id droid: ");
        return findDroid(in.nextInt());
    }
    public Droid findDroid(int id) {

        int idDroid;

        for (Droid droid : droids) {
            idDroid = droid.getId();
            if (idDroid == id) {
                return droid;
            }
        }
        return null;
    }
    public Droid createNewDroid(){
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter name droid: ");
        String name = in.nextLine();
        System.out.print("\nEnter damage droid: ");
        int damage = in.nextInt();
        System.out.print("\nEnter health droid: ");
        int health = in.nextInt();
        System.out.print("\nEnter protect droid: ");
        int protect = in.nextInt();
        System.out.println("\nEnter droid id: ");
        int id = in.nextInt();
        Droid newDroid = new Droid(name, damage, health, protect, id);

        return newDroid;
    }
//----------------------------Battle--------------------------------------
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
        if(num == 1){

            createForPvP();

        }else if(num == 2){
            chooseForPvp();

        }else if(num == 3){
                teams();
                teamFight();
        }else{
            PrintResult file = new PrintResult();
            file.PrintFight();
        }
    }
//----------------------------Fight--------------------------------------
    public void createForPvP() throws InterruptedException {
        Droid droid1 = createNewDroid();
        Droid droid2 = createNewDroid();
        Item item1 = null, item2 = null;

        System.out.println("""
                    Would you like choose items for droids?\s
                    1. Yes
                    2. No""");
        int num2 = 0;
        if(num2 == 1){

            printListItem();

            itemForDroid(item1, item2);

            ByteArrayOutputStream buffer = StartPrintToFile();

            BattleArea arena = new BattleArea(droid1, droid2, item1, item2);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

            EndPrintToFile(buffer);
        }
        else{
            ByteArrayOutputStream buffer = StartPrintToFile();
            BattleArea arena = new BattleArea(droid1, droid2, null, null);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

            EndPrintToFile(buffer);
        }



    }
    private void itemForDroid(Item item1, Item item2){
        printListItem();
        System.out.println("Please enter number of item for");
        item1 = choseItemForDroid();
        item2 = choseItemForDroid();
        items.remove(item1);
        items.remove(item2);
    }
    public void chooseForPvp() throws InterruptedException {
        printListDroid();
        Droid droid1 = selectDroid();
        Droid droid2 = selectDroid();



        System.out.println("Would you like choose items for droids? \n"+"1. Yes\n"+"2. No");
        Scanner scan = new Scanner(System.in);
        int num2 = scan.nextInt();
        Item item1 = null, item2 = null;


        ByteArrayOutputStream buffer = StartPrintToFile();
        BattleArea arena;
        if(num2 == 1){
            itemForDroid(item1, item2);
            arena = new BattleArea(droid1, droid2, item1, item2);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

        }
        else{

            arena = new BattleArea(droid1, droid2, item1, item2);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

        }
        EndPrintToFile(buffer);

    }
    public void teamFight() throws InterruptedException{
        int score1 = 0, score2 = 0;
        Random random = new Random();
        ByteArrayOutputStream buffer;

        int j = 0;
        do{
            int i = random.nextInt(team1.size());
            Droid droid1 = team1.get(i);
            int k = random.nextInt(team2.size());
            Droid droid2 = team2.get(k);

            buffer = StartPrintToFile();

            BattleArea battle = new BattleArea(droid1, droid2, null, null);
            Droid winner = battle.startFight();
            System.out.println("Winner is " + winner.getName());

            if (winner == droid1){
                score1++;

            }else {
                score2++;
            }
            team1.remove(droid1);
            team2.remove(droid2);
            j++;
        }while(j < 3);

        if (score1 > score2){
            System.out.println("First team is winner");
        } else {
            System.out.println("Second team is winner");
        }
        EndPrintToFile(buffer);
    }


//---------------------------------Teams----------------------------------

    public void teams(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like auto fight team or create new team?\n"+"1. Auto team\n"+"2. Create new team");
        int num3 = scan.nextInt();
        if(num3 == 1){
            chooseDroidsForTeam(team1, team2);
        }
        else {
            createNewTeam(team1, team2);
        }
    }
    public void createNewTeam(List<Droid> Team, List<Droid> Team1){
        System.out.println("Create 3 droids for first team");
        int i = 0;
        while(i < 3){
            Droid newDroid = createNewDroid();
            Team.add(newDroid);
            i++;
        }
        int j = 0;
        while(j < 3){
            Droid newDroid = createNewDroid();
            Team1.add(newDroid);
            j++;
        }
    }

    public void chooseDroidsForTeam(List<Droid> Team, List<Droid> Team1){
        printListDroid();

        int i = 0;
        System.out.println("\nChoose droids for team 1:");
        while(i < 3){
            Droid newDroid = selectDroid();
            Team.add(newDroid);
            droids.remove(newDroid);
            i++;
        }
        System.out.println("\nChoose droids for team 2:");
        int j = 0;
        while(j < 3){
            Droid newDroid = selectDroid();
            Team1.add(newDroid);
            droids.remove(newDroid);
            j++;
        }

    }


//------------------------------PrintToFile--------------------------------------

    public ByteArrayOutputStream StartPrintToFile(){
        // для запису консолі у текстовий документ
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new TeeOutputStream(System.out, buffer);
        System.setOut(new PrintStream(teeStream));

        return buffer;
    }
    public void EndPrintToFile(ByteArrayOutputStream buffer){
        // Збереження буферу в текстовий документ
        try(OutputStream fileStream = new FileOutputStream("result.txt")){
            buffer.writeTo(fileStream);
            System.out.println(" \n\n The Game is successfully written in file! ");
        } catch(IOException e){
            System.out.println("error");
        }
    }

//---------------------------------PrintList----------------------------------

    public void printListDroid(){
        for(int i = 0; i < droids.size();i++){
            System.out.println(droids.get(i)+" ");
        }
    }
    public void printListItem(){
        for(int i = 0; i < items.size();i++){
            System.out.println(items.get(i)+" ");
        }
    }


    public class TeeOutputStream extends PrintStream {
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
