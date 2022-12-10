package grupobala.Database.Course;

import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Course.IDBCourse.IDBCourse;
import grupobala.Entities.Course.Course;
import grupobala.Entities.Course.ICourse.ICourse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCourse implements IDBCourse {

    private IDBConnection databaseConnection;

    public DBCourse(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<ICourse> getAll() throws SQLException {
        String query = "SELECT id, nome, descricao FROM curso";

        ResultSet result = this.databaseConnection.executeQuery(query);

        ArrayList<ICourse> courses = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("nome");
            String description = result.getString("descricao");

            ICourse course = new Course(id, name, description);

            courses.add(course);
        }

        result.close();

        return courses;
    }
}
