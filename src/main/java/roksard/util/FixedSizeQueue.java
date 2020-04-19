package roksard.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class FixedSizeQueue<T> implements Iterable<T>{
    Object[] array;
    int fullSize;
    int currentSize = 0;
    int nextInsert = 0;

    static class Synchronized<T> extends FixedSizeQueue<T> {
        public Synchronized(int size) {
            super(size);
        }

        public Synchronized(FixedSizeQueue queue) {
            super(queue.fullSize);
            array = queue.array;
        }

        @Override
        synchronized public Iterator<T> iterator() {
            return super.iterator();
        }

        @Override
        synchronized public void forEach(Consumer<? super T> action) {
            super.forEach(action);
        }

        @Override
        synchronized public void offer(T t) {
            super.offer(t);
        }

        @Override
        synchronized public void addAll(Collection<?> c) {
            super.addAll(c);
        }
    }

    public FixedSizeQueue(int size) {
        fullSize = size;
        array = new Object[size];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int ind = 0;
            @Override
            public boolean hasNext() {
                return ind < currentSize;
            }

            @Override
            public T next() {
                return (T)array[ind++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for(int i = 0; i < currentSize; i++) {
            action.accept((T)array[i]);
        }
    }

    public void offer(T t) {
        array[nextInsert] = t;
        nextInsert = (nextInsert + 1) % fullSize;
        if (currentSize < fullSize) {
            currentSize++;
        }
    }

    public void addAll(Collection<?> c) {
        c.forEach(item -> offer((T)item));
    }

    public FixedSizeQueue<T> asSynchronized() {
        return new FixedSizeQueue.Synchronized<T>(this);
    }

    public static void main(String[] args) {
        FixedSizeQueue<Integer> que = new FixedSizeQueue<>(3);
        for (int i = 0; i < 1000; i++) {
            que.offer(i);
            System.out.println("add " + i);
            System.out.println("full que: ");
            que.forEach(ii -> System.out.print(ii + ", "));
            System.out.println();
        }
    }
}
