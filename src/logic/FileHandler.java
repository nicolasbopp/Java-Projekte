package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public ArrayList<String> getSemester() throws Exception {

        ArrayList<String> semesterList = new ArrayList<String>();
        semesterList.add("Gesamtübersicht");
        Scanner sc = new Scanner(new File("src/data/semester/Semester.csv"));
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            semesterList.add(sc.next());
        }
        sc.close();
        return semesterList;
    }

    public ArrayList<Grade> getGrade(String pathParameter) throws Exception {
        String path = "src/data/grade/" + pathParameter + ".csv";
        ArrayList<Grade> gradeList = new ArrayList<Grade>();
        Path pathToFile = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                Grade grade = createGrade(attributes);
                gradeList.add(grade);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(gradeList);
        return gradeList;
    }

    private static Grade createGrade(String[] metadata) {
        System.out.println(metadata[0]);
        System.out.println(metadata[1]);
        System.out.println(metadata[2]);
        System.out.println(metadata[3]);

        String subject = metadata[0];
        String lecturer = metadata[1];
        double grade = Double.parseDouble(metadata[2]);
        int ect = Integer.parseInt(metadata[3]);

        return new Grade(subject, lecturer, grade, ect);
    }
}




