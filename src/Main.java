import controller.TaskManager;
import model.EpicTask;
import model.SubTask;
import model.Task;
import model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        EpicTask epic1 = new EpicTask("Test epic", "Create first epic", 1);
        SubTask epic1FirstSubTask = new SubTask("SubTask for epic1",
                "Create first subTask", 2, epic1);
        SubTask epic1SecondSubTask = new SubTask("Second SubTask",
                "Create second SubTask for first epic", 3, epic1);
        EpicTask epic2 = new EpicTask("Second epic", "Create second epic", 4);
        SubTask epic2FirstSubTask = new SubTask("SubTask for second epic",
                "Create subTask for second epic", 5, epic2);
        taskManager.addTask(epic1);
        taskManager.addTask(epic2);
        epic1FirstSubTask = (SubTask) taskManager.addTask(epic1FirstSubTask);
        taskManager.addTask(epic1SecondSubTask);
        taskManager.addTask(epic2FirstSubTask);

        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }

        taskManager.changeStatus(epic1FirstSubTask.getId(), TaskStatus.DONE);
        System.out.println("New status for first epic and first SubTask");

        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }

    }
}
