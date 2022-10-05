package Item;

import java.util.Scanner;

public class Item {
    private final String name;
    private final int damage;
    private final int health;
    private final int protect;

    public Item(String name, int damage, int health, int protect) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.protect = protect;
    }

    public String getName() {return name;}

    public int getDamage() {return damage;}

    public int getHealth() {return health;}

    public int getProtect() {return protect;}

    public int addPercentProtect(){
        if (protect >= 1){
            return 30;
        } else if (protect == 0) {
            return 0;
        } else{
            return -10;
        }
    }

    @Override
    public String toString(){
        return "Name: "+ name+" damage: "+damage+" health: "+health+" protect: "+protect;
    }
}
