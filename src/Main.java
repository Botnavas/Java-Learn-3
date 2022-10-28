import controller.TaskManager;
import model.EpicTask;
import model.SubTask;
import model.Task;
import model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        EpicTask epic1 = new EpicTask("Test epic", "Create first epic");
        SubTask epic1FirstSubTask = new SubTask("SubTask for epic1",
                "Create first subTask", epic1);
        SubTask epic1SecondSubTask = new SubTask("Second SubTask",
                "Create second SubTask for first epic", epic1);
        EpicTask epic2 = new EpicTask("Second epic", "Create second epic");
        SubTask epic2FirstSubTask = new SubTask("SubTask for second epic",
                "Create subTask for second epic", epic2);
        taskManager.addEpicTask(epic1);
        taskManager.addEpicTask(epic2);
        taskManager.addSubTask(epic1FirstSubTask);
        taskManager.addSubTask(epic1SecondSubTask);
        taskManager.addSubTask(epic2FirstSubTask);

        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }

        epic1FirstSubTask.setStatus(TaskStatus.DONE);
        System.out.println("New status for first epic and first SubTask");

        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }

    }
}
