package grupobala.Database.Course.IDBCourse;

import grupobala.Entities.Course.ICourse.ICourse;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDBCourse {
    public ArrayList<ICourse> getAll() throws SQLException;
}
