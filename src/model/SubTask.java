package model;

public class SubTask extends Task {

    private EpicTask epic;

    public SubTask(String name, String description, int identifier, EpicTask epic) {
        super(name, description, identifier);
        this.epic = epic;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Sub task of epic task: ");
        result.append(epic.name);
        result.append(" with ID: ");
        result.append(epic.id);
        result.append("\n");
        result.append(super.toString());
        return result.toString();
    }

    public EpicTask getEpic() {
        return epic;
    }
}
