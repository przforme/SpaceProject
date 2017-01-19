package com.leonelle.pg.pwir.project1617;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class SpaceFight {
    public static void main(String[] args) {

        Battlestation mothership = new Battlestation(3657);
        int mode = 2;

        switch (mode) {
            case 1:
                int fleetSize = 100;
                int squads = 10;

                Spaceship[] fleet = new Spaceship[fleetSize];
                for (int i = 0; i < fleetSize; i++) {
                    fleet[i] = new Spaceship(2, 1);
                }

                LinkedList<Callable<Integer>> squadLeaders = new LinkedList<>();

                int first = 0;
                int last;
                int squadSize = fleetSize / squads;

                for (int s = 0; s < squads; s++) {
                    last = first + squadSize - 1;
                    if (last > fleetSize) last = fleetSize - 1;
                    System.out.println("Squad " + (s + 1) + ". Leader: " + first + ", Tail: " + last);
                    squadLeaders.add(new SpaceCommander(fleet, first, last));
                    first = last + 1;
                }

                while (mothership.getHealth() > 0) {

                    ExecutorService order = Executors.newFixedThreadPool(squads);
                    try {
                        List<Future<Integer>> futureList;
                        futureList = order.invokeAll(squadLeaders);

                        int firepower = 0;
                        for (Future<Integer> future : futureList) {
                            firepower += future.get();
                        }

                        mothership.damage(firepower);
                        System.out.println("Fleet did " + firepower + " damage to the battle station.");
                        System.out.println(mothership);
                        order.shutdown();
                    } catch (Exception e) {
                        System.out.println("Łahapen");
                    }
                }
                break;
            case 2:
                int pirateNumber = 5;
                LinkedList<Spaceship> pirates = new LinkedList<>();

                for (int i = 0; i < pirateNumber; i++) {
                    //Spaceship pirateShip = new Spaceship(6, 1);
                    Spaceship pirateShip = new Spaceship(5);
                    pirateShip.getTarget(mothership);
                    pirates.add(pirateShip);
                }

                for (Spaceship pirateShip : pirates) {
                    if (pirateShip.getState() == Thread.State.NEW)
                        pirateShip.start();
                }
                while (mothership.getHealth() > 0)
                    System.out.println(mothership);
                for (Spaceship pirateShip : pirates) {
                    try {
                        pirateShip.join();
                    } catch (InterruptedException e) {
                        System.out.println("Join exception");
                    }
                }
        }
        System.exit(1);
    }
}
