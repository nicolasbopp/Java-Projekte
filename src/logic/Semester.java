package logic;

import java.util.ArrayList;

public class Semester {

    private String name;
    private ArrayList<Grade> gradeList = new ArrayList<Grade>();

    public Semester(String name, ArrayList<Grade> gradeList) {
        this.name = name;
        this.gradeList = gradeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(ArrayList<Grade> gradeList) {
        this.gradeList = gradeList;
    }
}
