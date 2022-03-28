import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            switch (printMenu()) {

                case 1:
                    manager.createTask();
                    break;

                case 2:
                    manager.createEpic();
                    break;

                case 3:
                    manager.createSubtask();
                    break;

                case 4:
                    manager.printTasks();
                    manager.printEpics();
                    manager.printSubtasks();
                    break;

                case 5:
                    manager.clearAllTasks();
                    break;

                case 6:
                    System.out.println("Введите ID:");
                    int id = scanner.nextInt();
                    if (manager.taskMap.containsKey(id) || manager.epicMap.containsKey(id)
                            || manager.subtaskMap.containsKey(id)) {
                        System.out.println(manager.getTaskById(id));
                    } else {
                        System.out.println("Неверный ID");
                    }
                    break;

                case 7:
                    System.out.println("Введите ID задачи, в которой хотите поменять статус:");
                    id = scanner.nextInt();

                    if (manager.epicMap.containsKey(id)) {
                        System.out.println("Нельзя менять статус Эпика!");
                        break;
                    } else if (manager.taskMap.containsKey(id) || manager.subtaskMap.containsKey(id)) {
                        System.out.println("Введите новый статус:");
                        System.out.println("1 -- NEW");
                        System.out.println("2 -- IN PROGRESS");
                        System.out.println("3 -- DONE");
                        int status = scanner.nextInt();
                        switch (status) {
                            case 1:
                                manager.status(manager.getTaskById(id), "NEW");
                                break;
                            case 2:
                                manager.status(manager.getTaskById(id), "IN PROGRESS");
                                break;
                            case 3:
                                manager.status(manager.getTaskById(id), "DONE");
                                break;
                        }
                    } else {
                        System.out.println("Неверный ID");
                    }
                    manager.changeEpicStatus();
                    break;

                case 8:
                    System.out.println("Введите ID задачи, которую хотите удалить:");
                    id = scanner.nextInt();

                    if (manager.taskMap.containsKey(id) || manager.epicMap.containsKey(id)
                            || manager.subtaskMap.containsKey(id)) {
                        manager.removeTask(id);
                    } else {
                        System.out.println("Неверный ID");
                    }
                    manager.changeEpicStatus();
                    break;

                case 9:
                    System.out.println("Введите ID эпика:");
                    manager.printEpics();
                    id = scanner.nextInt();
                    if (manager.epicMap.containsKey(id)) {
                        manager.printEpicWithSubtasks(id);
                    } else {
                        System.out.println("Неверный ID");
                    }
                    break;

                case 0:
                    System.out.println("Пока пока");
                    return;

                default:
                    System.out.println("Такой команды нет");
            }
        }
    }

    static int printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 -- создать новую задачу");
        System.out.println("2 -- создать новый эпик");
        System.out.println("3 -- создать новую подзадачу");
        System.out.println("4 -- получить список всех задач");
        System.out.println("5 -- удалить все задачи");
        System.out.println("6 -- получить задачу по ID");
        System.out.println("7 -- смена статуса задачи");
        System.out.println("8 -- удаление задачи по ID");
        System.out.println("9 -- Получить список подзадач эпика");
        System.out.println("0 -- выход");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        return command;
    }
}

