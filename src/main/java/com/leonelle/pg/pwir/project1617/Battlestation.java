package com.leonelle.pg.pwir.project1617;

import java.util.concurrent.locks.ReentrantLock;

public class Battlestation extends Thread {
    private Integer health;
    private Integer maxHealth;
    private final ReentrantLock lock = new ReentrantLock();

    public Battlestation(Integer maxHealth) {
        this.health = this.maxHealth = maxHealth;
    }

    public int damage(int blast) {
        int retVal;
        lock.lock();
        try {
            if (this.health > 0) {
                this.health -= blast;
            }
        } finally {
            if (this.health > 0) {
                retVal = 0;
            } else {
                retVal = 1;
            }
            lock.unlock();
            return retVal;
        }
    }

    @Override
    public void run() {
        while (this.health > 0) System.out.println(this);
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "battlestation: " + this.health + "/" + this.maxHealth + " health left.";
    }
}
