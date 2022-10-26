package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap <Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (task instanceof EpicTask) {
            addEpicTask((EpicTask) task);
            return;
        }
        if (task instanceof SubTask) {
            addSubTask((SubTask) task);
            return;
        }
        tasks.put(task.getId(), task);
    }

    public void addSubTask(SubTask subTask) {
        if (tasks.containsKey(subTask.getEpic().getId())) {
            tasks.put(subTask.getId(), subTask);
            ((EpicTask) tasks.get(subTask.getEpic().getId())).addSubTask(subTask);
        } else {
            tasks.put(subTask.getEpic().getId(), subTask.getEpic());
            if (!subTask.getEpic().getSubTasksHashMap().containsKey(subTask.getId())) {
                subTask.getEpic().addSubTask(subTask);
            }
            tasks.put(subTask.getId(), subTask);
        }
    }

    public void addEpicTask(EpicTask epic) {
        tasks.put(epic.getId(), epic);
        for (SubTask subTask : epic.getSubTasksHashMap().values()) {
            tasks.put(subTask.getId(), subTask);
        }
    }

    public Task changeTask(Task task) {
        return tasks.replace(task.getId(), task);
    }

    public ArrayList<Task> getTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.addAll(this.tasks.values());
        return tasks;
    }

    public ArrayList<SubTask> getSubTaskListForEpicByID(int id) {
        if (tasks.get(id) instanceof EpicTask) {
            return ((EpicTask) tasks.get(id)).getSubTasksList();
        } else {
            return null;
        }
    }

    public TaskStatus getStatusByID(int id) {
        return tasks.get(id).getStatus();
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public void changeStatus(int taskID, TaskStatus status) {
        tasks.get(taskID).setStatus(status);
        if (tasks.get(taskID) instanceof SubTask) {
           int epicId = ((SubTask) tasks.get(taskID)).getEpic().getId();
            ((EpicTask) tasks.get(epicId)).setStatusForSubtask(taskID, status);
        }
    }

    public void deleteAllTasks() {
        tasks.clear();
    }
}
