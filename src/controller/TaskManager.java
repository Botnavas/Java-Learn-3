package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.*;

public class TaskManager {
    private HashMap <Integer, Task> tasks = new HashMap<>();

    //TODO: add id generation
    public Task addTask(Task task) {
        if (task instanceof EpicTask) {
            return addEpicTask((EpicTask) task);
        }
        if (task instanceof SubTask) {
            return addSubTask((SubTask) task);
        }

        Task toPut = new Task(task.getName(), task.getDescription(), calculateNewID());
        tasks.put(toPut.getId(), toPut);
        return toPut;
    }

    //TODO: refactor
    public SubTask addSubTask(SubTask subTask) {
        for (Task task : tasks.values()) {
            if (task instanceof EpicTask) {
                if (((EpicTask) task).equals(subTask.getEpic())) {
                    SubTask toPut = new SubTask(subTask.getName(), subTask.getDescription(),
                           calculateNewID() , (EpicTask) task);
                    ((EpicTask) task).addSubTask(toPut);
                    return toPut;
                }
            }
        }
        return null;
    }

    public EpicTask addEpicTask(EpicTask epic) {
        EpicTask epicTask = new EpicTask(epic.getName(), epic.getDescription(), calculateNewID());
        tasks.put(epicTask.getId(), epicTask);
        for (SubTask subTask : epic.getSubTasks()) {
            SubTask subTaskToPut = new SubTask(subTask.getName(), subTask.getDescription(),
                    calculateNewID(), epicTask);
            ((EpicTask) tasks.get(epicTask.getId())).addSubTask(subTaskToPut);
        }
        return (EpicTask) tasks.get(epicTask.getId());
    }

    //TODO: refactor
    public void changeTask(Task task, int id) {
        if (!(task instanceof SubTask) && !(task instanceof EpicTask)) {
            tasks.replace(id, task);
        }
    }

    public void changeEpicTask(EpicTask epic, int id) {
        if ((tasks.get(id) instanceof EpicTask)) {
            tasks.replace(id, epic);
        }
    }

    public void changeStatus(int id, TaskStatus status) {
        if (tasks.keySet().contains(id)) {
            if (!(tasks.get(id) instanceof EpicTask)) {
                tasks.get(id).setStatus(status);
            }
        } else {
            for (Task task : tasks.values()) {
                if (task instanceof EpicTask) {
                    if (((EpicTask) task).getSubtasksIDs().contains(id)) {
                        ((EpicTask) task).setStatusForSubTaskById(id, status);
                    }
                }
            }
        }
    }

    //TODO: return only interface
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

    //TODO: refactor
    public Collection<SubTask> getSubTaskOfEpicByID(int id) {
        if (tasks.get(id) instanceof EpicTask) {
            return ((EpicTask) tasks.get(id)).getSubTasks();
        } else {
            return null;
        }
    }

    //TODO: get epic, sub, task by id, epic
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

    public void removeAllTasks() {
        tasks.clear();
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

    public void removeAllSubTasksOfEpicById(int id) {
        if (tasks.get(id) instanceof EpicTask) {
            ((EpicTask) tasks.get(id)).getSubTasks().clear();
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

    private int calculateNewID() {
        int taskId = 0;
        Set<Integer> idSet =  new HashSet<>(tasks.keySet());
        for (Task task : tasks.values()) {
            if (task instanceof EpicTask) {
                idSet.addAll(((EpicTask) task).getSubtasksIDs());
            }
        }
        for (int id : idSet) {
            if (taskId != id) {
                break;
            } else {
                taskId++;
            }
        }
        return taskId;
    }

    //TODO: remove tasks and etc by id and by epic
}
