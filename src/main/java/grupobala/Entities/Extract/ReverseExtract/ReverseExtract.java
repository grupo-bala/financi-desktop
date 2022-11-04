package grupobala.Entities.Extract.ReverseExtract;

import java.util.ArrayList;
import java.util.Iterator;

public class ReverseExtract<ITransaction> implements Iterator<ITransaction> {

    private ArrayList<ITransaction> list;
    private int position;

    public ReverseExtract(ArrayList<ITransaction> new_list) {
        this.list = new_list;
        this.position = new_list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return this.position >= 0;
    }

    @Override
    public ITransaction next() {
        ITransaction value = this.list.get(this.position);
        this.position -= 1;
        return value;
    }
}
