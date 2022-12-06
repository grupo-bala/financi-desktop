package grupobala.Entities.Lesson.ILesson;

public interface ILesson {
    public String getName();
    public int getDurationInSeconds();
    public String getURL();
    public boolean getIsWatched();
    public void setIsWatched(boolean isWatched);
    public int getId();
}
