package grupobala.Entities.Lesson;

import grupobala.Entities.Lesson.ILesson.ILesson;

public class Lesson implements ILesson {
    private int id;
    private String name;
    private int durationInSeconds;
    private String URL;
    private boolean isWatched;

    public Lesson(int id, String name, int durationInSeconds, String URL, boolean isWatched) {
        this.id = id;
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.URL = URL;
        this.isWatched = isWatched;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDurationInSeconds() {
        return this.durationInSeconds;
    }

    @Override
    public String getURL() {
        return this.URL;
    }

    @Override
    public boolean getIsWatched() {
        return this.isWatched;
    }

    @Override
    public void setIsWatched(boolean isWatched) {
        this.isWatched = isWatched;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
