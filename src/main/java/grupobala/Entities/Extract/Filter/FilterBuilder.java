package grupobala.Entities.Extract.Filter;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.Filter.IFilter.IFilter;
import grupobala.Entities.Extract.Filter.IFilter.IFilterBuilder;
import java.util.Date;

public class FilterBuilder implements IFilterBuilder {

    private IFilter filter;

    public FilterBuilder() {
        this.filter = new Filter();
    }

    @Override
    public IFilterBuilder fromDate(Date date) {
        if (date != null) {
            this.filter.setFromDate(date);
        }

        return this;
    }

    @Override
    public IFilterBuilder toDate(Date date) {
        if (date != null) {
            this.filter.setToDate(date);
        }

        return this;
    }

    @Override
    public IFilterBuilder withCategory(CategoryEnum category) {
        if (category != null) {
            this.filter.setCategory(category);
        }

        return this;
    }

    @Override
    public IFilter build() {
        return this.filter;
    }

    @Override
    public void reset() {
        this.filter = new Filter();
    }
}
