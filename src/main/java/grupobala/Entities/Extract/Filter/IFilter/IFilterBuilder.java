package grupobala.Entities.Extract.Filter.IFilter;

import java.util.Date;

import grupobala.Entities.Category.CategoryEnum;

public interface IFilterBuilder {
    public IFilterBuilder fromDate(Date date);
    public IFilterBuilder toDate(Date date);
    public IFilterBuilder withCategory(CategoryEnum category);
    public IFilter build();
    public void reset();
}
