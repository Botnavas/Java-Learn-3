import controller.Managers;
import controller.TaskManager;
import model.EpicTask;
import model.SubTask;
import model.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getFileBackedManager("Manager.csv");
        EpicTask epic1 = new EpicTask("Sprint 4", "Pass sprint 4");
        EpicTask epic2 = new EpicTask("History manager", "Create history manager");
        SubTask Sub1Epic1 = new SubTask("Start project", "Open IDE", epic1);
        SubTask Sub2Epic1 = new SubTask("Make polymorphism", "Create interfaces", epic1);
        SubTask Sub3Epic1 = new SubTask("Improve controller", "Create utility class", epic1);

        /*taskManager.addTask(epic1);
        taskManager.addTask(epic2);
        taskManager.addTask(Sub1Epic1);
        taskManager.addTask(Sub2Epic1);
        taskManager.addTask(Sub3Epic1);

        taskManager.getTask(epic1.getId());
        taskManager.getTask(Sub1Epic1.getId());
        taskManager.getTask(Sub2Epic1.getId());
        taskManager.getTask(epic2.getId());*/

        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

    }
}
