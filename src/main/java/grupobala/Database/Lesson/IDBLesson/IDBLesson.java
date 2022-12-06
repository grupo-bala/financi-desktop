package grupobala.Database.Lesson.IDBLesson;

import java.sql.SQLException;
import java.util.ArrayList;

import grupobala.Entities.Lesson.ILesson.ILesson;

public interface IDBLesson {
    public ArrayList<ILesson> getAll(int courseId) throws SQLException;
    public void updateWatched(boolean status, int lessonId, int userId) throws SQLException;
}
