package grupobala.Entities.Iterator;

import java.util.ArrayList;

public class ReverseIterator <T> implements IteratorInterface <T> {

    private ArrayList<T> list;
    private int position;

    public ReverseIterator(ArrayList<T> new_list) {
        this.list = new_list;
        this.position = new_list.size() - 1;
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
