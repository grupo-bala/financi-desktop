package grupobala.Entities.Extract.Filter.IFilter;

import grupobala.Entities.Category.CategoryEnum;
import java.util.Date;

public interface IFilterBuilder {
    public IFilterBuilder fromDate(Date date);

    public IFilterBuilder toDate(Date date);

    public IFilterBuilder withCategory(CategoryEnum category);

    public IFilter build();

    public void reset();
}
