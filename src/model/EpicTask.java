package model;

import java.util.Collection;
import java.util.HashMap;

public class EpicTask extends Task {

    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    public EpicTask(int id) {
        super(id);
    }

    public EpicTask(String name, String description) {
        super(name, description);
    }

    public EpicTask(String[] info) {
        super(info);
    }

    public void addSubTask(SubTask subTask) {
        if (subTask == null) {
            return;
        }
        subTasks.put(subTask.getId(), subTask);
    }

    public boolean containsSubTask(int id) {
        return subTasks.containsKey(id);
    }

    public Collection<SubTask> getSubTasks() {
        return subTasks.values();
    }

    public SubTask getSubTaskByID(int id) {
        return subTasks.get(id);
    }

    public void removeAllSubTasks() {
        subTasks.clear();
    }

    public void removeSubTaskByID(int id) {
        subTasks.remove(id);
    }

    @Override
    public TaskStatus getStatus() {
        boolean isNew = true;
        boolean isDone = true;
        for (SubTask subTask : subTasks.values()) {
            switch (subTask.status) {
                case NEW -> isDone = false;
                case IN_PROGRESS -> {
                    isNew = false;
                    isDone = false;
                }
                case DONE -> isNew = false;
            }
        }
        if (isNew) {
            status = TaskStatus.NEW;
        } else if (isDone) {
            status = TaskStatus.DONE;
        } else {
            status = TaskStatus.IN_PROGRESS;
        }
        return status;
    }

    @Override
    public String toString() {
        status = getStatus();
        StringBuilder result = new StringBuilder("Epic task:\n" + super.toString());
        for (SubTask subTask : subTasks.values()) {
            result.append(subTask.toString());
        }
        result.append("\n");
        return result.toString();
    }

    @Override
    public String toCsvString() {
        return id + "," + TaskType.EPIC + "," + name + "," + status + "," +
                description +"," + System.lineSeparator();
    }
}
