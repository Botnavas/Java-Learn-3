package model;

public class SubTask extends Task {

    private EpicTask epic;

    public SubTask(String name, String description, int identifier, EpicTask epic) {
        super(name, description, identifier);
        this.epic = epic;
    }

    @Override
    public String toString(){
        String result = "Sub task of epic task: " +
                epic.name +
                " with ID: " +
                epic.id +
                "\n" +
                super.toString();
        return result;
    }

    public EpicTask getEpic() {
        return epic;
    }
}
