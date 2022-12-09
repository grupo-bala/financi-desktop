package grupobala.Controller.Lesson;

import java.util.ArrayList;

import grupobala.Controller.Lesson.ILessonController.ILessonController;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Lesson.DBLesson;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Course.DBCourse;
import grupobala.Entities.Course.ICourse.ICourse;
import grupobala.Entities.Lesson.ILesson.ILesson;
import java.sql.SQLException;


public class LessonController implements ILessonController{
    IDBConnection databaseConnection = new DBConnection();
    DBLesson dbLesson = new DBLesson(databaseConnection);
    DBCourse dbCourse = new DBCourse(databaseConnection);

    @Override
    public ArrayList<ICourse> getCourses() throws SQLException, Exception {
        try{
            ArrayList<ICourse> cursos = dbCourse.getAll();
            return cursos;
        }
        catch(SQLException error){
            throw new Exception("Não foi possivel pegar os cursos");
        }
    }

    @Override
    public ArrayList<ILesson> getLessons(int courseId) throws SQLException, Exception{
        
        try{
            ArrayList<ILesson> lessons =  dbLesson.getAll(courseId);
            return lessons;
        }
        catch(SQLException error){
            throw new Exception("Não foi possivel pegar as aulas");
        }     
    }

    @Override
    public void updateWatched(boolean status, int lessonId, int userId) throws SQLException, Exception {
        try{
            dbLesson.updateWatched(status, lessonId, userId);
        }
        catch(SQLException error){
            throw new Exception("Não foi possivel atualizar o status da aula");
        }     
    }

    public ICourse getCourse(int courseId) throws SQLException, Exception {
        ArrayList<ICourse> courses =  getCourses();
        for(ICourse course : courses){
            if(courseId == course.getId()){
                return course;
            }
        }
        throw new Exception("Curso não existe");
    }

    public ILesson getLesson(int courseId, int lessonId) throws SQLException, Exception {
        ArrayList<ILesson> lessons =  getLessons(courseId);
        for(ILesson lesson : lessons){
            if(lessonId == lesson.getId()){
                return lesson;
            }
        }
        throw new Exception("Aula não existe");
    }


    
}
