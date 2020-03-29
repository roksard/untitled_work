package roksard.incubator_simulator;

import java.time.Instant;

public class Human implements Runnable {
    static int yearTime = 10;
    static final int yearsLive = 100;
    static int maxChildBorn = 3;
    int childBorn = 0;
    int year = 0;
    static int count = 0;
    int id = count++;
    Instant bornDate;
    HumanGenerator humanGenerator;
    boolean isAlive = true;

    public Human(HumanGenerator hg) {
        bornDate = Instant.now();
        humanGenerator = hg;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        humanGenerator.die(this);
    }

    @Override
    public String toString() {
        return (isAlive ? "+" : "-") + "h" + id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted() && isAlive) {
                Thread.sleep(yearTime);
                if (year % 10 == 0) {
                    //System.out.println(this.toString() + " " + year + " yrs.old");
                }
                year++;
                if (year >= 20 && childBorn <= maxChildBorn) {
                    humanGenerator.registerAsPartner(this);
                    childBorn++;
                }
                if (year >= yearsLive) {
                    die();
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
