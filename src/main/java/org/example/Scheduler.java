package org.example;

import java.util.Date;

public abstract class Scheduler implements Alertable, ConvertAlertMessages {

    private int deadline;
    private String fullName;
    private String text;

    private Date dateCreated;

    public Scheduler(String fullName, int deadline, String text) {
        this.fullName = fullName;
        this.deadline = deadline;
        this.text = text;
        this.dateCreated = new Date();              //без параметров берет время с системы
    }

    public final int created() {
        Date currentDate = new Date();
        int seconds = (int) (currentDate.getTime() - this.dateCreated.getTime()) / (1000);

        return seconds;
    }




    public int getDeadline() {
        return deadline;
    }


    public String getFullName() {
        return fullName;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public void alertError(int num) {
        System.out.println("Произошла ошибка: " + this.convertAlertErrorMessage(num));
    }

    @Override
    public void alertWarning(int num) {
        System.out.println("Возможно ошибка: " + this.convertAlertWarningMessage(num));
    }

    @Override
    public String convertAlertErrorMessage(int num) {
        return ListErrorsWarnings.listErrors[num];
    }

    @Override
    public String convertAlertWarningMessage(int num) {
        return ListErrorsWarnings.listWarnings[num];
    }
}

