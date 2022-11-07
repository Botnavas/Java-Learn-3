package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.Collection;

public interface TaskManager {
    void addTask(Task task);
    void addSubTask(SubTask subTask);
    void addEpicTask(EpicTask epicTask);
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
