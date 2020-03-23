package myclasses;

import java.util.Comparator;

public class Timetable {
    public String weekday;
    public Integer numOfPair;
    public String nameOfLesson;
    public int numOfClass;
    public String group;

    public Timetable(String weekday, int numOfPair, String nameOfLesson, int numOfClass, String group) {
        this.weekday = weekday;
        this.numOfPair = numOfPair;
        this.nameOfLesson = nameOfLesson;
        this.numOfClass = numOfClass;
        this.group = group;
    }

    public Integer weekdayInInt(String weekday) {
        if (weekday.equals("ПОНЕДЕЛЬНИК")) return 6;
        if (weekday.equals("ВТОРНИК")) return 5;
        if (weekday.equals("СРЕДА")) return 4;
        if (weekday.equals("ЧЕТВЕРГ")) return 3;
        if (weekday.equals("ПЯТНИЦА")) return 2;
        if (weekday.equals("СУББОТА")) return 1;
        return 0;
    }


    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(weekday + " " + numOfPair + " " + nameOfLesson + " " + numOfClass + " " + group);
        return out.toString();
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public void setNumOfPair(Integer numOfPair) {
        this.numOfPair = numOfPair;
    }

    public void setNameOfLesson(String nameOfLesson) {
        this.nameOfLesson = nameOfLesson;
    }

    public void setNumOfClass(int numOfClass) {
        this.numOfClass = numOfClass;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
