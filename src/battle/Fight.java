package battle;

import com.droid.Droid;
import com.droid.PrintResult;
import item.Item;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Fight {

    private Droid droid = new Droid("noname", 0, 0, 0, 0);
    private Item item = new Item("noitem", 0,0,0);
    private PrintResult printResult = new PrintResult();
//    private Item item;
//    private List<Item> items;
//    private List<Droid> droids;



    public void createForPvP(List<Item> items) throws InterruptedException {
        Droid droid1 = droid.createNewDroid();
        Droid droid2 = droid.createNewDroid();
        Item item1 = null, item2 = null;

        System.out.println("""
                    Would you like choose items for droids?\s
                    1. Yes
                    2. No""");
        int num2 = 0;
        if(num2 == 1){

            item.printListItem(items);
            item1 = item.itemForDroid(item1, items);
            item2 = item.itemForDroid(item2, items);

            ByteArrayOutputStream buffer = printResult.StartPrintToFile();

            BattleArea arena = new BattleArea(droid1, droid2, item1, item2);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());
            printResult.EndPrintToFile(buffer);
        }
        else{
            ByteArrayOutputStream buffer = printResult.StartPrintToFile();
            BattleArea arena = new BattleArea(droid1, droid2, null, null);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

            printResult.EndPrintToFile(buffer);
        }



    }
    public void chooseForPvp(List<Droid> droids, List<Item> items) throws InterruptedException {
        droid.printListDroid(droids);
        Droid droid1 = droid.selectDroid(droids);
        Droid droid2 = droid.selectDroid(droids);



        System.out.println("Would you like choose items for droids? \n"+"1. Yes\n"+"2. No");
        Scanner scan = new Scanner(System.in);
        int num2 = scan.nextInt();
        Item item1 = null, item2 = null;
       ByteArrayOutputStream buffer = printResult.StartPrintToFile();
        BattleArea arena;
        if(num2 == 1){
            item.printListItem(items);

            item1 = item.itemForDroid(item1, items);
            item2 = item.itemForDroid(item2, items);
            arena = new BattleArea(droid1, droid2, item1, item2);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

        }
        else{

            arena = new BattleArea(droid1, droid2, item1, item2);
            Droid winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

        }
       printResult.EndPrintToFile(buffer);

    }


    public void teamFight(List<Droid> team1, List<Droid> team2) throws InterruptedException{
        int score1 = 0, score2 = 0;
        Random random = new Random();
        ByteArrayOutputStream buffer;

        int j = 0;
        do{
            int i = random.nextInt(team1.size());
            Droid droid1 = team1.get(i);
            int k = random.nextInt(team2.size());
            Droid droid2 = team2.get(k);

            buffer = printResult.StartPrintToFile();

            BattleArea battle = new BattleArea(droid1, droid2, null, null);
            Droid winner = battle.startFight();
            System.out.println("Winner is " + winner.getName());

            if (winner == droid1){
                score1++;

            }else { score2++; }
            team1.remove(droid1);
            team2.remove(droid2);
            j++;
        }while(j < 3);

        if (score1 > score2){
            System.out.println("First team is winner");
        } else {
            System.out.println("Second team is winner");
        }
        printResult.EndPrintToFile(buffer);
    }

}
