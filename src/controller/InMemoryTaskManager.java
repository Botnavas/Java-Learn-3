package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private HistoryManager history;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, EpicTask> epicTasks = new HashMap<>();
    private int idGenerator = 0;

    public InMemoryTaskManager(HistoryManager history) {
        this.history = history;
    }

    @Override
    public void addTask(Task task) {
        if (task == null) {
            return;
        }
        task.setId(generateID());
        tasks.put(task.getId(), task);
    }

    @Override
    public void addSubTask(SubTask subTask) {
        if (subTask == null) {
            return;
        }

        if (epicTasks.containsKey(subTask.getEpic().getId())) {
            EpicTask epicTask = epicTasks.get(subTask.getEpic().getId());
            if (epicTask.containsSubTask(subTask.getId())) {
                epicTask.removeSubTaskByID(subTask.getId());
            }
            subTask.setId(generateID());
            epicTask.addSubTask(subTask);
        } else {
            addEpicTask(subTask.getEpic());
        }
    }

    @Override
    public void addEpicTask(EpicTask epic) {
        if (epic == null) {
            return;
        }

        epic.setId(generateID());
        epicTasks.put(epic.getId(), epic);
        for (SubTask subTask : epic.getSubTasks()) {
            addSubTask(subTask);
        }
    }

    @Override
    public void changeTask(Task task) {
        if (task != null) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void changeEpicTask(EpicTask epic) {
        if (epic != null) {
            epicTasks.put(epic.getId(), epic);
        }
    }

    @Override
    public void changeSubTask(SubTask subTask) {
        if (subTask != null) {
            epicTasks.get(subTask.getEpic().getId()).addSubTask(subTask);
        }
    }

    @Override
    public Collection<Task> getTasks() {
        return tasks.values();
    }

    @Override
    public Collection<EpicTask> getEpicTasks() {
        return epicTasks.values();
    }

    @Override
    public Collection<Task> getAllTasks() {
        Collection<Task> resultCollection = new ArrayList<>(tasks.values());
        resultCollection.addAll(epicTasks.values());
        return resultCollection;
    }

    @Override
    public Collection<SubTask> getSubTasksByEpicID(int id) {
        return  epicTasks.get(id).getSubTasks();
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);

        if (task != null) {
            history.addTask(task);
            return task;
        } else {
            return null;
        }
    }

    @Override
    public EpicTask getEpicTask(int id) {
        EpicTask epicTask = epicTasks.get(id);

        if (epicTask != null) {
            history.addTask(epicTask);
            return epicTask;
        } else {
            return null;
        }
    }

    @Override
    public SubTask getSubTaskByID(int id) {
        for (EpicTask epicTask : epicTasks.values()) {
            if (epicTask.getSubTaskByID(id) != null) {
                history.addTask(epicTask.getSubTaskByID(id));
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

    @Override
    public void removeSubTaskByID(int id) {
        if (getSubTaskByID(id) != null) {
            epicTasks.get(getSubTaskByID(id).getEpic().getId()).removeSubTaskByID(id);
        }
    }

    @Override
    public void removeEpicTaskByID(int id) {
        epicTasks.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }

    public void removeTaskByID(int id) {
        tasks.remove(id);
    }

    private int generateID() {
        return ++idGenerator;
    }
}
