package grupobala.Entities.Iterator.ReverseIterator;

import java.util.ArrayList;

import grupobala.Entities.Iterator.IteratorInterface.IteratorInterface;

public class ReverseIterator<T> implements IteratorInterface<T> {

    private ArrayList<T> list;
    private int position;

    public ReverseIterator(ArrayList<T> newList) {
        this.list = newList;
        this.position = newList.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return this.position >= 0;
    }

    @Override
    public T next() {
        T value = this.list.get(this.position);
        this.position -= 1;
        return value;
    }
}
