package com.leonelle.pg.pwir.project1617;

import java.util.concurrent.ThreadLocalRandom;

public class Spaceship extends Thread {
    private int baseDamage;
    private int damageSpectrum;
    private int damageType;
    private Battlestation target;

    public Spaceship(int baseDamage, int damageSpectrum) {
        this.baseDamage = baseDamage;
        this.damageSpectrum = damageSpectrum;
        this.damageType = 0;
    }

    public Spaceship(int baseDamage) {
        this.baseDamage = baseDamage;
        this.damageType = 1;
    }

    public int getBlast() {
        int damage;
        if (damageType == 0) {
            int minDamage = baseDamage - damageSpectrum;
            int maxDamage = baseDamage + damageSpectrum;
            damage = ThreadLocalRandom.current().nextInt(minDamage, maxDamage);
        } else {
            damage = baseDamage;
        }
        System.out.println(this + " \"" + ((Thread.currentThread().getName())) + "\" shoots for " + damage + " damage.");
        return damage;
    }

    public int getBlastThread() {
        int damage;
        if (damageType == 0) {
            int minDamage = baseDamage - damageSpectrum;
            int maxDamage = baseDamage + damageSpectrum;
            damage = ThreadLocalRandom.current().nextInt(minDamage, maxDamage);
        } else {
            damage = baseDamage;
        }
        int damageDealt = target.damage(damage);
        if (damageDealt == 0) {
            System.out.println(this + " shoots for " + damage + " damage.");
        }
        return damageDealt;
    }

    public void getTarget(Battlestation target) {
        this.target = target;
    }

    @Override
    public void run() {
        int done = 0;
        while (done == 0) {
            done = getBlastThread();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " \"" + this.getName() + "\"";
    }
}
