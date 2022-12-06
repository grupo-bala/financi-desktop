package grupobala.Database.Lesson;

import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Lesson.IDBLesson.IDBLesson;
import grupobala.Entities.Lesson.ILesson.ILesson;
import grupobala.Entities.Lesson.Lesson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class DBLesson implements IDBLesson {

    private IDBConnection databaseConnection;

    public DBLesson(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<ILesson> getAll(int courseId) throws SQLException {
        String query = String.format(
            Locale.US,
            "SELECT id, videourl, duracaosegundos, nome FROM aula WHERE idcurso = %d",
            courseId
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        ArrayList<ILesson> lessons = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt("id");
            String videoURL = result.getString("videourl");
            int durationInSeconds = result.getInt("duracaosegundos");
            String name = result.getString("nome");
            boolean isWatched = true;

            String queryToCheckIfIsWatched = String.format(
                Locale.US,
                "SELECT * FROM aulaassistida WHERE idaula = %d",
                id
            );

            ResultSet checkWatchedResult =
                this.databaseConnection.executeQuery(queryToCheckIfIsWatched);

            if (!result.isBeforeFirst()) {
                isWatched = false;
            }

            ILesson lesson = new Lesson(
                id,
                name,
                durationInSeconds,
                videoURL,
                isWatched
            );

            lessons.add(lesson);

            checkWatchedResult.close();
        }

        result.close();

        return lessons;
    }

    @Override
    public void updateWatched(boolean status, int lessonId, int userId)
        throws SQLException {
        String query = String.format(
            Locale.US,
            "INSERT INTO aulaassistida(idusuario, idaula) VALUES (%d, %d)",
            userId,
            lessonId
        );

        if (!status) {
            query =
                String.format(
                    Locale.US,
                    "DELETE FROM aulaassistida WHERE idaula = %d AND idusuario = %d",
                    lessonId,
                    userId
                );
        }

        int updateCount = this.databaseConnection.executeUpdate(query);

        if (updateCount == 0) {
            throw new SQLException("Aula ou usuário não existe!");
        }
    }
}
