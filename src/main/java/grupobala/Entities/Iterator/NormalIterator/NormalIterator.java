package grupobala.Entities.Iterator.NormalIterator;

import java.util.ArrayList;

import grupobala.Entities.Iterator.IteratorInterface.IteratorInterface;

public class NormalIterator<T> implements IteratorInterface<T> {
    
    private ArrayList<T> list;
    private int position;

    public NormalIterator(ArrayList<T> newList) {
        this.list = newList;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return this.position <= this.list.size() - 1;
    }

    @Override
    public T next() {
        T value = this.list.get(this.position);
        this.position += 1;
        return value;
    }
}
