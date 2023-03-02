package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager {
    public static final String fileJSON = "fileManagerJSON.json";

    public static Scanner in;

    public static void addNewText(int i, User[] users) {
        int deadline;
        String fullName, text;

        System.out.println("Введите ФИО");
        fullName = in.nextLine();
        try {
            System.out.println("Введите время на выполнение");
            deadline = in.nextInt();
            in.nextLine();
            if (deadline < 0) {
                throw new NegativeDeadlineException(deadline);
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: InputMismatchException ");
            deadline = 0;
        } catch (NegativeDeadlineException e) {
            e.printStackTrace();
            System.out.println("Ошибка отрицательное число");
            deadline = 0;
        }
        System.out.println("Введите текст");
        text = in.nextLine();
        users[i] = new User(fullName, deadline, text);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("fullName", fullName);
        jsonObj.put("deadline", deadline);


        File file = new File(fileJSON);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JSONArray managerList;
        Object obj;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileJSON)) {
            if (file.length() > 2) {
                obj = jsonParser.parse(reader);
                managerList = (JSONArray) obj;
            } else {
                managerList = new JSONArray();
            }
            managerList.add(jsonObj);

            try (FileWriter writer = new FileWriter(fileJSON)) {
                writer.write(managerList.toJSONString());


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAllUser(int size, User[] users) {

        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ") " + users[i].getFullName() + " " + users[i].getDeadline() + " " + users[i].getText());
            System.out.println((i + 1) + "количество дней(seconds) " + users[i].created());

        }
    }

    public static void editUserFields(int count, User[] users) {
        int i = 0;
        System.out.println("Введите ID записи: ");
        i = in.nextInt();
        in.nextLine();
        if (i >= 0 && i < count) {
            System.out.println("Старая информация: " + (i + 1) + ") " + users[i].getFullName() + " " + users[i].getDeadline() + " " + users[i].getText());
            String text;

            System.out.println("Введите текст");
            text = in.nextLine();
            users[i].setText(text);
            System.out.println("Запись добавлена");

        } else {
            System.out.println("Ошибка. Введен неверный номер!");
        }
    }


    public static int readJSONFromFile(User[] users) {

        int count = 0;
        File file = new File(fileJSON);
        if (!file.exists() || file.length() < 2) {
            return 0;
        }
        JSONArray managerList;
        Object obj;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileJSON)) {
            obj = jsonParser.parse(reader);
            managerList = (JSONArray) obj;

            count = managerList.size();
            if (count >= 100) {
                count = 100;
            }


            for (int i = 0; i < managerList.size(); i++) {
                JSONObject objJSON = (JSONObject) managerList.get(i);
                users[i] = new User((String) objJSON.get("fullName"), (int) (long) objJSON.get("deadline"), (String) objJSON.get("text"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Task Manager");
        System.out.println("Выберите действие добавить (add), посмотреть (list), редактирование (edit) и выйти(exit)");

        in = new Scanner(System.in);
        String command;

        User[] users = new User[100];
        int count = 0;

        count = readJSONFromFile(users);


        //считыввем команды от пользоавтеля
        while (true) {
            System.out.println("Введите команду add, list, edit или exit");
            command = in.nextLine();
            switch (command) {
                case "add": {
                    System.out.println("Добавить запись");
                    if (count < 100) {
                        addNewText(count, users);
                        count++;
                        System.out.println("Запись добавлена");
                    } else {
                        System.out.println("Больше нельзя добавлять");
                    }
                    break;
                }
                case "list": {
                    System.out.println("Показать записи");
                    if (count > 0) {
                        showAllUser(count, users);
                    } else {
                        System.out.println("Записей нет");
                    }
                    break;
                }
                case "edit": {
                    System.out.println("Редактирование записи");
                    if (count > 0) {
                        editUserFields(count, users);
                        count++;
                    } else {
                        System.out.println("Записей нет");
                    }
                    break;
                }
                case "exit": {
                    in.close();
                    return;
                }
                default:
                    System.out.println("Неизвестная команда");
            }
        }

    }
}



