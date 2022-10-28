package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.*;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    private int idGenerator = 0;

    public void addTask(Task task) {
        if (task == null) {
            return;
        }
        if (task instanceof EpicTask) {
            addEpicTask((EpicTask) task);
            return;
        }
        if (task instanceof SubTask) {
            addSubTask((SubTask) task);
            return;
        }
        task.setId(generateID());
        tasks.put(task.getId(), task);
    }

    public void addSubTask(SubTask subTask) {
        if (subTask == null) {
            return;
        }
        subTask.setId(generateID());
        epicTasks.get(subTask.getEpic().getId()).addSubTask(subTask);
    }

    public void addEpicTask(EpicTask epic) {
        if (epic == null) {
            return;
        }
        epic.setId(generateID());
        epicTasks.put(epic.getId(), epic);
    }

    public void changeTask(Task task) {
        if (task != null) {
            tasks.put(task.getId(), task);
        }
    }

    public void changeEpicTask(EpicTask epic) {
        if (epic != null) {
            epicTasks.put(epic.getId(), epic);
        }
    }

    public void changeSubTask(SubTask subTask) {
        if (subTask != null) {
            epicTasks.get(subTask.getEpic().getId()).addSubTask(subTask);
        }
    }

    public Collection<Task> getTasks() {
        return tasks.values();
    }

    public Collection<EpicTask> getEpicTasks() {
        return epicTasks.values();
    }

    public Collection<Task> getAllTasks() {
        Collection<Task> taskCollection = new ArrayList<>(tasks.values());
        taskCollection.addAll(epicTasks.values());
        return taskCollection;
    }

    public Collection<SubTask> getSubTasksByEpicID(int id) {
        if (epicTasks.get(id) != null) {
            return  epicTasks.get(id).getSubTasks();
        } else {
            return null;
        }
    }

    public Task getTask(int id) {
        if (tasks.get(id) != null) {
            return tasks.get(id);
        } else {
            return null;
        }
    }

    public EpicTask getEpicTask(int id) {
        if (epicTasks.get(id) != null) {
            return (epicTasks.get(id));
        } else {
            return null;
        }
    }

    public SubTask getSubTaskByID(int id) {
        for (EpicTask epicTask : epicTasks.values()) {
            if ( epicTask.getSubTaskByID(id) != null) {
                return epicTask.getSubTaskByID(id);
            }
        }
        return null;
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpicTasks() {
        epicTasks.clear();
    }

    public void removeAllSubTasks() {
        for (EpicTask epicTask : epicTasks.values()) {
            epicTask.removeAllSubTasks();
        }
    }

    public void removeSubTaskByID(int id) {
        if (getSubTaskByID(id) != null) {
            epicTasks.get(getSubTaskByID(id).getEpic().getId()).removeSubTaskByID(id);
        }
    }

    public void removeEpicTaskByID(int id) {
        epicTasks.remove(id);
    }

    public void removeTaskByID(int id) {
        tasks.remove(id);
    }

    private int generateID() {
        return ++idGenerator;
    }
}
