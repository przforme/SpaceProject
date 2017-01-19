package com.leonelle.pg.pwir.project1617;

import java.util.concurrent.Callable;

public class SpaceCommander implements Callable<Integer> {
    private Spaceship[] fleet;
    private int squadFront;
    private int squadBack;

    public SpaceCommander(Spaceship[] fleet, int squadFront, int squadBack) {
        super();
        this.fleet = fleet;
        this.squadFront = squadFront;
        this.squadBack = squadBack;
    }

    @Override
    public Integer call() throws Exception {
        Integer firepower = 0;
        for (int i = squadFront; i <= squadBack; i++) {
            firepower += fleet[i].getBlast();
        }
        return firepower;
    }
}
