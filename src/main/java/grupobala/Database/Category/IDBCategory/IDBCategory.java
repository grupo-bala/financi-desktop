package grupobala.Database.Category.IDBCategory;

import java.sql.SQLException;

import grupobala.Entities.Category.CategoryEnum;

public interface IDBCategory {
    public int getCategoryID(CategoryEnum category) throws SQLException;
    
    public String getCategoryName(int categoryID) throws SQLException;
}
