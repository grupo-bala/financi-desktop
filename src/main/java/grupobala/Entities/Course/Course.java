package grupobala.Entities.Course;

import grupobala.Entities.Course.ICourse.ICourse;

public class Course implements ICourse {

    public int id;
    private String name;
    private String description;

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
