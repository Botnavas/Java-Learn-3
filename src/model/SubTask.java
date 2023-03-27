package model;

public class SubTask extends Task {

    private EpicTask epic;

    public SubTask(String name, String description, EpicTask epic) {
        super(name, description);
        this.epic = epic;
    }

    public SubTask(String[] info, EpicTask epic) {
        super(info);
        this.epic = epic;
    }

    public EpicTask getEpic() {
        return epic;
    }

    public void setEpic(EpicTask epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Sub task of epic task: " +
                epic.name +
                " with ID: " +
                epic.id +
                "\n" +
                super.toString();
    }

    @Override
    public String toCsvString() {
        return id + "," + TaskType.SUBTASK + "," + name + "," + status + "," +
                description + "," + epic.id + System.lineSeparator();
    }
}
