package roksard.concurrency.syncOnLocalObjectWarning;

import java.util.List;

public class RunnableWName implements Runnable {
    private NaturalNumbersGenerator generator;
    private String name;
    private boolean useSync;
    private double someHeavyValue;

    public RunnableWName(NaturalNumbersGenerator generator, String name, boolean useSync) {
        this.generator = generator;
        this.name = name;
        this.useSync = useSync;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (useSync) {
                List<String> listNaturalNumbers = generator.getListNaturalNumbers();
                synchronized (listNaturalNumbers) { //Warning that we sync on local variable, but actual object is not locally created and is actually shared
                    generator.generateNew(name);
                }
            } else {
                generator.generateNew(name);
            }
            Thread.yield();
            //add some cpu load on this thread (more chance that scheduler will switch to another thread)
            calculateSomeHeavyValue();
        }
    }

    /** method to add some cpu time/load on this thread
      */
    private void calculateSomeHeavyValue() {
        for (int i = 0; i < 10000; i++) {
            someHeavyValue = someHeavyValue * 0.1 / someHeavyValue;
        }
    }
}
