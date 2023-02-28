package org.example;

import java.util.Date;

//animal
public abstract class Scheduler implements Alertable, ConvertAlertMessages {

    private int dataAdd;
    private int timeAdd;
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
//        int days = (int) (currentDate.getTime() - this.dateCreated.getTime()) / (60 * 60 * 24 * 1000);
        int seconds = (int) (currentDate.getTime() - this.dateCreated.getTime()) / (1000);

        return seconds;
    }


    public int getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(int dataAdd) {
        this.dataAdd = dataAdd;
    }

    public int getTimeAdd() {
        return timeAdd;
    }

    public void setTimeAdd(int timeAdd) {
        this.timeAdd = timeAdd;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

