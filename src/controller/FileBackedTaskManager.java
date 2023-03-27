package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;
import model.TaskType;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBackedTaskManager extends InMemoryTaskManager implements TaskManager {
    protected Path savePath;

    public FileBackedTaskManager() {
        super(Managers.getDefaultHistory());
    }

    public FileBackedTaskManager(FileBackedTaskManager manager) {
        super(manager);
        this.savePath = manager.savePath;
    }

    public FileBackedTaskManager(HistoryManager history, Path savePath) {
        super(history);
        this.savePath = savePath;
        loadFromFile();
    }

    public void loadFromFile() {
        if (!Files.exists(savePath))
            return;

        boolean isEndOfTaskList = false;


        try {
            String read = Files.readString(savePath);
            String[] strings = read.split("\n");

            for (String str : strings) {
                String[] info = str.split(",");

                if (info.length > 1 && !isEndOfTaskList) {
                    switch (TaskType.valueOf(info[1])) {
                        case EPIC -> super.addEpicTask(new EpicTask(info), false);
                        case TASK -> super.addTask(new Task(info), false);
                        case SUBTASK -> {
                            int epicId = Integer.parseInt(info[5].substring(0, info[5].length() - 1));
                            super.addSubTask(new SubTask(info,
                                    epicTasks.get(epicId)), false);
                        }
                    }
                    idGenerator++;
                } else {
                    isEndOfTaskList = true;
                }

                if (isEndOfTaskList) {
                    for (String num : info) {
                        history.addTask(getTask(Integer.getInteger(num)));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in reading");
        }
    }

    public void save() {
        if (!Files.exists(savePath)) {
            try {
                Files.createFile(savePath);
            } catch (IOException e) {
                throw new ManagerSaveException("Can't create file");
            }
        }
        try (FileWriter fw = new FileWriter(savePath.toFile())) {
            for (Task task : tasks.values()) {
                fw.write(task.toCsvString());
            }
            for (EpicTask epic : epicTasks.values()) {
                fw.write(epic.toCsvString());

                for (SubTask subTask : epic.getSubTasks()) {
                    fw.write(subTask.toCsvString());
                }
            }

            fw.write(System.lineSeparator());
            fw.write(history.toCsvString());
        } catch (IOException e) {
            throw new ManagerSaveException("Can't write");
        }
    }

    @Override
    public void addTask(Task task, boolean setId) {
        super.addTask(task, setId);
        save();
    }

    @Override
    public void addSubTask(SubTask subTask, boolean setId) {
        super.addSubTask(subTask, setId);
        save();
    }

    @Override
    public void addEpicTask(EpicTask epic, boolean setId) {
        super.addEpicTask(epic, setId);
        save();
    }
}
