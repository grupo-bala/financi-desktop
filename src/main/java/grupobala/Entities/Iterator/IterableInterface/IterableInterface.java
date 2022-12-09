package grupobala.Entities.Iterator.IterableInterface;

import grupobala.Entities.Iterator.IteratorEnum.IteratorEnum;
import grupobala.Entities.Iterator.IteratorInterface.IteratorInterface;

public interface IterableInterface<T> {
    public IteratorInterface<T> iterator(IteratorEnum iteratorEnum);
}
