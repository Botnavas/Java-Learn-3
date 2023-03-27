package controller;

import java.nio.file.Paths;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }

    public static  TaskManager getFileBackedManager(String path) {
        return new FileBackedTaskManager(getDefaultHistory(), Paths.get(path));
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager(10);
    }
}
