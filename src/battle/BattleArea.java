package battle;

import item.Item;
import com.droid.Droid;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BattleArea {

    private final Droid firstDroid;
    private final Droid secondDroid;
    private final Item item1;
    private final Item item2;
    private Droid attacker;
    private Droid defender;
    private int currentRound = 0;


    public BattleArea(Droid firstDroid, Droid secondDroid, Item item1, Item item2) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
        this.item1 = item1;
        this.item2 = item2;
    }

    private void printInfoItem(Item item1, Item item2){
        System.out.println(attacker.getName() + " have boost damage:"
                + item1.getDamage() + ", health: " + item1.getHealth() +", protect: "+item1.getProtect() + "\n"
                +"Damage now: " + attacker.getDamage() +" + " + item1.getDamage()
                + ", health: "+ attacker.getHealth() +"(+"+item1.getHealth() + ")\n");

        System.out.println(defender.getName() + " have boost health:" + item2.getHealth() +
                ", protect: "+item2.getProtect() + "\n"
                +"Health now: " + defender.getHealth()+"(+" + item2.getHealth() + ")"
                + ", protect percent: " + defender.getPercent() + " + "+item2.addPercentProtect() + "\n");
    }

    public void useItem(Item item1, Item item2){
        attacker.addDamage(item1.getDamage());
        attacker.addHealth(item1.getHealth());
        attacker.addPercent(item1.addPercentProtect());

        defender.addHealth(item2.getHealth());
        defender.addPercent(item2.addPercentProtect());

        printInfoItem(item1, item2);

    }

    public Droid startFight() throws InterruptedException{
        do {
            prepareRound();

            if(item1 != null){
                if (currentRound == 5){
                    if(attacker == firstDroid){
                        useItem(item1, item2);

                    }else if(attacker == secondDroid){
                        useItem(item2, item1);
                    }
                }
            }

            int actualDamage = doFight();


            printRoundInfo(actualDamage);


            TimeUnit.SECONDS.sleep(1);
        }while (defender.isAlive());

        return attacker;
    }

    private void printRoundInfo(int actualDamage) {
        System.out.println(defender.getName() + " health damage: -" + actualDamage +" protect damage: -" + defender.getProtectDamage());
        System.out.println(firstDroid);
        System.out.println(secondDroid);
    }
    private int doFight() {
        int damage = attacker.setActualDamage(attacker.getDamage());
        return defender.getHit(damage);
    }


    public void initFighter(){
        Random random = new Random();
        if(random.nextBoolean()){
            attacker = firstDroid;
            defender = secondDroid;
        }else {
            attacker = secondDroid;
            defender = firstDroid;
        }
    }


    private void prepareRound(){
        initFighter();
        currentRound++;
        System.out.println("------------------------------------");
        System.out.println("\t\t\tRound " + currentRound);
    }


}
