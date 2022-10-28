package model;

import java.util.Objects;

public class Task {
    protected int id = 0;
    protected TaskStatus status;
    protected String name;
    protected String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        status = TaskStatus.NEW;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "ID: " + id +
                "\nName: " +
                name +
                "\nStatus: " +
                status +
                "\nDescription: " +
                description +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return status == task.status
                && name.equals(task.name)
                && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

