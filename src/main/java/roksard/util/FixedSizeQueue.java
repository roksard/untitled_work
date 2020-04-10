package roksard.util;

import java.util.Iterator;
import java.util.function.Consumer;

public class FixedSizeQueue<T> implements Iterable<T>{
    Object[] array;
    int fullSize;
    int currentSize = 0;

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
        array[currentSize++ % fullSize] = t;
    }
}
