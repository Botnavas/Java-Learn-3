package model;

public class Task {
    protected int id;
    protected TaskStatus status;
    protected String name;
    protected String description;

    public Task(String name, String description, int id) {
        this.id = id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
        StringBuilder result = new StringBuilder("ID: ");
        result.append(id);
        result.append("\nName: ");
        result.append(name);
        result.append("\nStatus: ");
        result.append(status);
        result.append("\nDescription: ");
        result.append(description);
        result.append("\n");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (status != task.status) return false;
        if (!name.equals(task.name)) return false;
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return id;
    }
}

