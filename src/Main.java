import controller.Managers;
import controller.TaskManager;
import model.EpicTask;
import model.SubTask;
import model.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        EpicTask epic1 = new EpicTask("Sprint 4", "Pass sprint 4");
        EpicTask epic2 = new EpicTask("History manager", "Create history manager");
        SubTask Sub1Epic1 = new SubTask("Start project", "Open IDE", epic1);
        SubTask Sub2Epic1 = new SubTask("Make polymorphism", "Create interfaces", epic1);
        SubTask Sub3Epic1 = new SubTask("Improve controller", "Create utility class", epic1);
        SubTask Sub1Epic2 = new SubTask("Create fields", "Use interfaces for fields", epic2);
        SubTask Sub2Epic2 = new SubTask("Create methods", "Don't forget @Override", epic2);

        taskManager.addEpicTask(epic1);
        taskManager.addEpicTask(epic2);
        taskManager.addSubTask(Sub1Epic1);
        taskManager.addSubTask(Sub2Epic1);
        taskManager.addSubTask(Sub3Epic1);
        taskManager.addSubTask(Sub1Epic2);
        taskManager.addSubTask(Sub2Epic2);

        taskManager.getEpicTask(epic1.getId());
        taskManager.getSubTaskByID(Sub1Epic1.getId());
        taskManager.getEpicTask(epic2.getId());
        taskManager.getSubTaskByID(Sub2Epic1.getId());
        taskManager.getSubTaskByID(Sub3Epic1.getId());
        taskManager.getSubTaskByID(Sub1Epic2.getId());
        taskManager.getSubTaskByID(Sub2Epic2.getId());

        //Testing 10 Tasks
        taskManager.getEpicTask(epic1.getId());
        taskManager.getSubTaskByID(Sub1Epic1.getId());
        taskManager.getEpicTask(epic2.getId());
        taskManager.getSubTaskByID(Sub2Epic1.getId());
        taskManager.getSubTaskByID(Sub3Epic1.getId());
        taskManager.getSubTaskByID(Sub1Epic2.getId());
        taskManager.getSubTaskByID(Sub2Epic2.getId());

        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

    }
}
