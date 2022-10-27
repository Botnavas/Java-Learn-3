package model;

import java.util.Collection;
import java.util.HashMap;

public class EpicTask extends Task {

    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    public EpicTask(String name, String description, int identifier) {
        super(name, description, identifier);
    }

    public void addSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
    }

    public Collection<SubTask> getSubTasks() {
        return subTasks.values();
    }

    public Collection<Integer> getSubtasksIDs() {
        return  subTasks.keySet();
    }

    public SubTask getSubTaskByID(int id) {
        return subTasks.get(id);
    }

    public void setStatusForSubTaskById(int id, TaskStatus status) {
        subTasks.get(id).setStatus(status);
    }

    public void removeAllSubTasks() {
        subTasks.clear();
    }

    public void removeSubTaskByID(int id) {
        subTasks.remove(id);
    }

    //TODO: refactor
    @Override
    public TaskStatus getStatus() {
        boolean isNew = true;
        boolean isDone = true;
        for (SubTask subTask : subTasks.values()) {
            switch (subTask.status) {
                case NEW:
                    isDone = false;
                    break;
                case IN_PROGRESS:
                    isNew = false;
                    isDone = false;
                    break;
                case DONE:
                    isNew = false;
                    break;
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
        return result.toString();
    }
}
