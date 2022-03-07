package logic;

public class Grade {
    private String subject;
    private String lecturer;
    private double mark;
    private int ect;

    public Grade(String subject, String lecturer, double mark, int ect) {
        this.subject = subject;
        this.lecturer = lecturer;
        this.mark = mark;
        this.ect = ect;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public int getEct() {
        return ect;
    }

    public void setEct(int ect) {
        this.ect = ect;
    }
}