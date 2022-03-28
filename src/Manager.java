import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Task> taskMap = new HashMap<>();
    HashMap<Integer, Epic> epicMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskMap = new HashMap<>();
    private int idCounter;

    int assignId() { // Счетчик идентификаторов
        return idCounter += 1;
    }

    void createTask() { // Cоздание задачи
        Scanner scanner = new Scanner(System.in); /* Каждый раз приходится новый объект Scanner. Иначе при вводе с
        консоли программа проскакивает ввод названия задачи*/
        System.out.println("Введите название задачи:");
        String nameTask = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String descriptionTask = scanner.nextLine();
        Task task = new Task(nameTask, descriptionTask);
        int id = assignId();
        task.setId(id);
        taskMap.put(task.getId(), task);
    }

    void createEpic() { // Создание эпика
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название эпика:");
        String nameEpic = scanner.nextLine();
        System.out.println("Введите описание эпика:");
        String descriptionEpic = scanner.nextLine();
        Epic epic = new Epic(nameEpic, descriptionEpic);
        int id = assignId();
        epic.setId(id);
        epicMap.put(epic.getId(), epic);
    }

    void createSubtask() { // Создание подзадачи
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название подзадачи:");
        String nameSubtask = scanner.nextLine();
        System.out.println("Введите описание подзадачи:");
        String descriptionSubtask = scanner.nextLine();
        System.out.println("Введите ID эпика, в который положить подзадачу:");
        printEpics();
        int epicId = scanner.nextInt();
        Subtask subtask = new Subtask(nameSubtask, descriptionSubtask);
        int id = assignId();
        subtask.setId(id);
        subtaskMap.put(subtask.getId(), subtask);
        epicMap.get(epicId).subtasksListOfEpic.add(subtask);
    }

    void printEpicWithSubtasks(int id) { // Печать эпика по идентификатору с подзадачами
        if (epicMap.containsKey(id)) {
            System.out.println(epicMap.get(id));
            for (Subtask subtask : epicMap.get(id).subtasksListOfEpic) {
                System.out.println(subtask);
            }
        } else {
            System.out.println("Нет Эпика с ID - " + id);
        }
    }

    void printTasks() { // Печать задач
        for (Task task : taskMap.values()) {
            System.out.println(task);
        }
    }

    void printEpics() { // Печать эпиков
        for (Epic epic : epicMap.values()) {
            System.out.println(epic);
        }
    }

    void printSubtasks() { // Печать подзадач
        for (Subtask subtask : subtaskMap.values()) {
            System.out.println(subtask);
        }
    }

    void clearAllTasks() { // Удаление всех задач
        taskMap.clear();
        epicMap.clear();
        subtaskMap.clear();
        System.out.println("Все задачи удалены");
    }

    Task getTaskById(int id) { // Поиск задачи по идентификатору
        if (taskMap.containsKey(id)) {
            return taskMap.get(id);
        } else if (epicMap.containsKey(id)) {
            return epicMap.get(id);
        } else {
            return subtaskMap.get(id);
        }
    }

    Task status(Task task, String status) { // Смена статуса задачи
        task.setStatus(status);
        return task;
    }

    void removeTask(int id) { // Удаление задачи по идентификатору
        if (taskMap.containsKey(id)) { //Удаление задачи
            taskMap.remove(id);
        } else if (epicMap.containsKey(id)) { // Удаление эпика
            for (Subtask subtask1 : subtaskMap.values()) {
                for (Subtask subtask2 : epicMap.get(id).subtasksListOfEpic) {
                    if (subtask2.equals(subtask1)) {
                        int tempId = subtask2.getId();
                        subtaskMap.remove(tempId);
                        break;
                    }
                }
            }
            epicMap.remove(id);
        } else { // Удаление подзадачи
            for (Epic epic : epicMap.values()) {
                for (Subtask subtask : epic.subtasksListOfEpic) {
                    if (subtask.equals(subtaskMap.get(id))) {
                        epic.subtasksListOfEpic.remove(subtask);
                    }
                }
            }
            subtaskMap.remove(id);
        }
    }

    void changeEpicStatus() { // Смена статуса у эпика

        for (Epic epic : epicMap.values()) {
            int counterNEW = 0;
            int counterDONE = 0;

            for (Subtask subtask : epic.subtasksListOfEpic) {
                if (subtask.getStatus().equals("NEW")) {
                    counterNEW += 1;
                } else if (subtask.getStatus().equals("DONE")) {
                    counterDONE +=1;
                }
            }
            if (epic.subtasksListOfEpic.isEmpty() || (counterNEW == epic.subtasksListOfEpic.size())) {
                epic.setStatus("NEW");
            } else if (counterDONE == epic.subtasksListOfEpic.size()) {
                epic.setStatus("DONE");
            } else {
                epic.setStatus("IN PROGRESS");
            }
        }
    }
}

