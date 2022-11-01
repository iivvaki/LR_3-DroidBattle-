package com.droid;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Droid {
    private final int id;
    private final String name;
    private final int damage;
    private int health;
    private int protect;
    private int protectDamage;
    private int percent = 20;
    private int actualDamage;
    public Droid(String name, int damage, int health, int protect, int id) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.protect = protect;
    }

    public int getPercent() {
        return percent;
    }
    public int addPercent(int add){
        return percent += add;
    }

    public int getProtectDamage() {
        return protectDamage;
    }

    public int getActualDamage() {
        return actualDamage;
    }

    public int getId() {return id;}


    public int addDamage(int add){
        return actualDamage += add;
    }
    public int addHealth(int add){

        return health += add;
    }
    public String getName() {return name;}

    public int setActualDamage(int damage) {
        Random random = new Random();
        this.actualDamage = random.nextInt(damage);
        return actualDamage;
    }

    public int getDamage() {return damage;}

    public boolean isAlive() {return health > 0;}

    public int getHealth() {
        return health;
    }

    public int getHit(int damage){

        if (protect <= 0){
            this.health -= damage;
        }else{

            this.protectDamage = (damage * this.percent) / 100;
            damage -= this.protectDamage;
            this.health -= damage;
            this.protect -= this.protectDamage;
        }
        if (health <= 0) {
            health = 0;
        }

        return damage;
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

    public Droid selectDroid(List<Droid> droids) {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter id droid: ");
        return findDroid(in.nextInt(), droids);
    }
    private Droid findDroid(int id, List<Droid> droids) {

        int idDroid;

        for (Droid droid : droids) {
            idDroid = droid.getId();
            if (idDroid == id) {
                return droid;
            }
        }
        return null;
    }


    public void printListDroid(List<Droid> droids){
        for(int i = 0; i < droids.size();i++){
            System.out.println(droids.get(i)+" ");
        }
    }

    @Override
    public String toString(){
        return id +". "+ name + ": " + "health = "+this.health + "| protect = "+protect;
    }

}
