package org.example;

public class User extends Scheduler {
    private int id;


    public User(String fullName, int deadline, String text) {
        super(fullName, deadline, text);
        if (fullName.equals("")) {
            this.alertWarning(0);
        }
//        if (text.equals("")) {
//            this.alertWarning(2);
//        }
    }


    boolean addNote(int id, String fullName) {
        //...
        return true;
    }

    int dataAdd(int dataAdd) {
        //...
        return 22;
    }

    int timeAdd(int timeAdd) {
        //...
        return 22;
    }

    int deadline(int deadline) {
        //...
        return 55;
    }


}

