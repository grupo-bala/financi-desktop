package grupobala.Database.Lesson.IDBLesson;

import grupobala.Entities.Lesson.ILesson.ILesson;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDBLesson {
    public ArrayList<ILesson> getAll(int courseId) throws SQLException;

    public void updateWatched(boolean status, int lessonId, int userId)
        throws SQLException;
}
