package item;

import java.util.List;
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

    public Item itemForDroid(Item item, List<Item> items){
        //printListItem(items);
        System.out.println("Please enter number of item for");
        item = choseItemForDroid(items);
        items.remove(item);
        return item;
    }

    private Item choseItemForDroid(List<Item> items) {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter item name: ");
        return findItem(in.nextLine(), items);
    }
    public Item findItem(String name, List<Item> items) {

        String itemName;

        for (Item item : items) {
            itemName = item.getName();
            if (itemName.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void printListItem(List<Item> items){
        for(int i = 0; i < items.size();i++){
            System.out.println(items.get(i)+" ");
        }
    }
    @Override
    public String toString(){
        return "Name: "+ name+" damage: "+damage+" health: "+health+" protect: "+protect;
    }
}
