package grupobala.Database.Category.IDBCategory;

import grupobala.Entities.Category.CategoryEnum;
import java.sql.SQLException;

public interface IDBCategory {
    public int getCategoryID(CategoryEnum category) throws SQLException;

    public String getCategoryName(int categoryID) throws SQLException;
}
