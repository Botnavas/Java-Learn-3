package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.*;

public class TaskManager {
    private HashMap <Integer, Task> tasks = new HashMap<>();
    private int idGenerator = 0;

    //TODO: add id generation
    public void addTask(Task task) {
        if (task instanceof EpicTask) {
            addEpicTask((EpicTask) task);
            return;
        }
        if (task instanceof SubTask) {
            addSubTask((SubTask) task);
            return;
        }
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    //TODO: refactor
    public void addSubTask(SubTask subTask) {
        subTask.setId(generateId());
        ((EpicTask) tasks.get(subTask.getEpic().getId())).addSubTask(subTask);
    }

    public void addEpicTask(EpicTask epic) {
        epic.setId(generateId());
        tasks.put(epic.getId(), epic);
    }

    public void changeTask(Task task) {
        if (!(task instanceof SubTask) && !(task instanceof EpicTask)) {
            tasks.put(task.getId(), task);
        }
    }

    public void changeEpicTask(EpicTask epic) {
        if ((tasks.get(epic.getId()) instanceof EpicTask)) {
            tasks.put(epic.getId(), epic);
        }
    }

    public void changeSubTask(SubTask subTask) {
        ((EpicTask) tasks.get(subTask.getEpic().getId())).addSubTask(subTask);
    }

    public Collection<Task> getTasks() {
        return tasks.values();
    }

    public Collection<EpicTask> getEpicTasks() {
        Collection<EpicTask> epicTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task instanceof EpicTask) {
                epicTasks.add((EpicTask) task);
            }
        }
        return epicTasks;
    }

    public Collection<SubTask> getSubTaskOfEpicByID(int id) {
        if (tasks.get(id) instanceof EpicTask) {
            return ((EpicTask) tasks.get(id)).getSubTasks();
        } else {
            return null;
        }
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public EpicTask getEpicTaskByID(int id) {
        if (tasks.get(id) instanceof EpicTask) {
            return ((EpicTask) tasks.get(id));
        } else {
            return null;
        }
    }

    public SubTask getSubTaskByID(int id) {
        for (Task task : tasks.values()) {
            if (task instanceof EpicTask) {
                if (((EpicTask) task).getSubTaskByID(id) != null) {
                    return ((EpicTask) task).getSubTaskByID(id);
                }
            }
        }
        return null;
    }

    public void removeAllTypesOfTasks() {
        tasks.clear();
    }

    public void removeAllTasks() {
        for (Task task : tasks.values()) {
            if (!(task instanceof EpicTask)) {
                tasks.remove(task.getId());
            }
        }
    }

    public void removeAllEpicTasks() {
        for (Task task : tasks.values()) {
            if (task instanceof EpicTask) {
                tasks.remove(task.getId());
            }
        }
    }

    public void removeAllSubTasks() {
        for (Task task : tasks.values()) {
            if (task instanceof EpicTask) {
                ((EpicTask) task).removeAllSubTasks();
            }
        }
    }

    public void removeSubTaskByID(int id) {
        for (Task task : tasks.values()) {
            if (task instanceof EpicTask) {
                if (((EpicTask) task).getSubTaskByID(id) != null) {
                    ((EpicTask) task).removeSubTaskByID(id);
                    return;
                }
            }
        }
    }

    public void removeEpicTaskById(int id) {
        if (tasks.get(id) instanceof EpicTask) {
            tasks.remove(id);
        }
    }

    public void removeTaskByID(int id) {
        if (!(tasks.get(id) instanceof EpicTask)) {
            tasks.remove(id);
        }
    }

    private int generateId() {
        return ++idGenerator;
    }
}
