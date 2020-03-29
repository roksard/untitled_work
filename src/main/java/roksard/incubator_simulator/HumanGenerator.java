package roksard.incubator_simulator;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class HumanGenerator {
    ExecutorService exec;
    Queue<Human> partners = new LinkedBlockingQueue<>();
    int count = 0;

    public HumanGenerator(ExecutorService exec) {
        this.exec = exec;
    }

    public Human createHuman() {
        Human h = new Human(this);
        exec.execute(h);
        synchronized (this) {
            count++;
        }
        System.out.println("current human count: " + count);
        return h;
    }

    public void registerAsPartner(Human h) {
        synchronized (partners) {
            partners.add(h);
        }
        System.out.println("registered partner: " + h);
        createChild();
    }

    void createChild() {
        synchronized (partners) {
            if (partners.size() >= 2) {
                Human h = createHuman();
                System.out.println("new child from " + partners.poll() + " and " + partners.poll() + ": " + h);
            }
        }
    }

    public void die(Human h) {
        System.out.println("death: " + h);
        synchronized (this) {
            count--;
        }
    }
}
