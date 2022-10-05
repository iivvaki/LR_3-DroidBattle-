package com.droid;

import java.util.Random;
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



    @Override
    public String toString(){
        return id +". "+ name + ": " + "health = "+this.health + "| protect = "+protect;
    }

}
