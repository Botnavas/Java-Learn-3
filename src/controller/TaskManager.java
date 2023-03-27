package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.Collection;

public interface TaskManager {
    void addTask(Task task, boolean setId);
    void addSubTask(SubTask subTask, boolean setId);
    void addEpicTask(EpicTask epicTask, boolean setId);
    void changeTask(Task task);
    void changeSubTask(SubTask subTask);
    void changeEpicTask(EpicTask epicTask);
    Collection<Task> getTasks();
    Collection<Task> getAllTasks();
    Collection<EpicTask> getEpicTasks();
    Collection<Task> getHistory();
    Collection<SubTask> getSubTasksByEpicID(int id);
    Task getTask(int id);
    EpicTask getEpicTask(int id);
    SubTask getSubTaskByID(int id);
    void removeSubTaskByID(int id);
    void removeEpicTaskByID(int id);
    void removeAllTasks();
    void removeAllSubTasks();
    void removeAllEpicTasks();
    void removeTaskByID(int id);

}
