package grupobala.Controller.Lesson.ILessonController;

import grupobala.Controller.Lesson.ILessonController.ILessonController;
import grupobala.Entities.Course.ICourse.ICourse;
import grupobala.Entities.Lesson.ILesson.ILesson;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ILessonController {
    public ArrayList<ILesson> getLessons(int courseId)
        throws SQLException, Exception;

    public ArrayList<ICourse> getCourses() throws SQLException, Exception;

    public void updateWatched(boolean status, int lessonId, int userId)
        throws SQLException, Exception;

    public ILesson getLesson(int courseId, int lessonId)
        throws SQLException, Exception;
}
