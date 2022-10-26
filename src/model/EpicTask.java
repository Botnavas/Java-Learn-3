package model;

import java.util.ArrayList;
import java.util.HashMap;

public class EpicTask extends Task {

    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    public EpicTask(String name, String description, int identifier) {
        super(name, description, identifier);
    }

    public void addSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        refreshStatus();
    }

    //1 - new; 2 - in progress; 3 - done
    private void refreshStatus() {
        boolean isNew = false;
        boolean isInProgress = false;
        boolean isDone = false;
        for (SubTask subTask : subTasks.values()) {
            switch (subTask.status) {
                case NEW :
                    isNew = true;
                    break;
                case IN_PROGRESS:
                    isInProgress = true;
                    break;
                case DONE:
                    isDone = true;
                    break;
                default:
            }
        }
        if ((isNew && isInProgress) || (isNew && isDone) || (isInProgress && isDone)) {
            status = TaskStatus.IN_PROGRESS;
        } else if (isNew) {
            status = TaskStatus.NEW;
        } else {
            status = TaskStatus.DONE;
        }
    }

    public void setStatusForSubtask(int subTaskId, TaskStatus status) {
        subTasks.get(subTaskId).setStatus(status);
    }

    public HashMap<Integer, SubTask> getSubTasksHashMap() {
        return subTasks;
    }

    public ArrayList<SubTask> getSubTasksList() {
        ArrayList<SubTask> subTasks = new ArrayList<>();
        subTasks.addAll(this.subTasks.values());
        return subTasks;
    }

    @Override
    public void setStatus(TaskStatus status) {
        if (status == TaskStatus.DONE) {
            for (SubTask subTask : subTasks.values()) {
                subTask.setStatus(status);
            }
            this.status = status;
        }
    }

    @Override
    public TaskStatus getStatus() {
        refreshStatus();
        return status;
    }

    @Override
    public String toString() {
        refreshStatus();
        StringBuilder result = new StringBuilder("Epic task:\n" + super.toString());
        for (SubTask subTask : subTasks.values()) {
            result.append(subTask.toString());
        }
        return result.toString();
    }
}
