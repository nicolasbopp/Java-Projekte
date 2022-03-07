package gui;

import logic.FileHandler;
import logic.Grade;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu semesterMenu;
    JMenuItem addSemester;
    JMenuItem editSemester;
    JMenuItem deleteSemester;

    JMenu gradeMenu;
    JMenuItem addGrade;
    JMenuItem editGrade;
    JMenuItem deleteGrade;

    JMenu exportMenu;
    JMenuItem excelExport;
    JMenuItem csvExport;

    JMenu helpMenu;
    JMenuItem informationHelp;
    JMenuItem exitHelp;

    JSplitPane mainPanel;
    JTabbedPane topPanel;

    JPanel bottomPanel;

    public MainWindow() throws Exception {
        this.setTitle("Notenverwaltung");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(920,640);

        ImageIcon image = new ImageIcon("logo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(255, 255, 255));

        WindowLayout();
        MenuBar();
        this.setVisible(true);
    }

    void WindowLayout() throws Exception {

        mainPanel = new JSplitPane();
        mainPanel.setDividerSize(0);
        mainPanel.setDividerLocation(400);
        mainPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);

        topPanel = new JTabbedPane();

        mainPanel.setTopComponent(topPanel);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.CYAN);
        mainPanel.setBottomComponent(bottomPanel);

        TabPanel();
        this.add(mainPanel);

    }

    void TabPanel() throws Exception {
        FileHandler fileHandler = new FileHandler();
        ArrayList<String> semesterList = fileHandler.getSemester();
        ArrayList<JPanel> panelList = new ArrayList<JPanel>();

        String[] columnNames = {"Modul","Dozierende","Note","ECT"};

        int counter = 0;
        for (String i : semesterList){
            JPanel semester = new JPanel();
            ArrayList<Grade> dataList = fileHandler.getGrade(i);

            String[][] dataArray = new String[dataList.size()][4];

            counter = 0;
            for (Grade grade : dataList){
                dataArray[counter][0] = grade.getSubject();
                dataArray[counter][1] = grade.getLecturer();
                dataArray[counter][2] = String.valueOf(grade.getMark());
                dataArray[counter][3] = String.valueOf(grade.getEct());
                counter++;
            }

            semester.setLayout(new BorderLayout());

            JTable table = new JTable(dataArray, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            JScrollPane tableScroll = new JScrollPane();
            tableScroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            table.setEnabled(false);
            table.setRowHeight(25);
            setJTableColumnsWidth(table, 480, 40, 40, 10, 10);

            semester.add(tableScroll, BorderLayout.CENTER);
            semester.add(tableScroll);
            panelList.add(semester);
        }

        Random randomGenerator = new Random();
        counter = 0;
        for (JPanel panel : panelList){

            ImageIcon icon;
            if (counter == 0){
                icon = new ImageIcon("src/graphics/home.png");
            }else{
                icon = new ImageIcon("src/graphics/semester.png");
            }
            topPanel.addTab(semesterList.get(counter),icon,panel);
            counter++;
        }
    }

    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)
                    (tablePreferredWidth * (percentages[i] / total)));
        }
    }

    void MenuBar(){
        menuBar = new JMenuBar();

        semesterMenu = new JMenu("Semester");
        gradeMenu = new JMenu("Noten");
        exportMenu = new JMenu("Exportieren");
        helpMenu = new JMenu("Hilfe");

        addSemester = new JMenuItem("Semester hinzufügen");
        editSemester = new JMenuItem("Semester bearbeiten");
        deleteSemester = new JMenuItem("Semester löschen");

        addGrade = new JMenuItem("Note hinzufügen");
        editGrade = new JMenuItem("Note bearbeiten");
        deleteGrade = new JMenuItem("Note löschen");

        csvExport = new JMenuItem("CSV exportieren");
        excelExport = new JMenuItem("Excel exportieren");

        informationHelp = new JMenuItem("Information");
        exitHelp = new JMenuItem("Beenden");

        semesterMenu.add(addSemester);
        semesterMenu.add(editSemester);
        semesterMenu.add(deleteSemester);

        gradeMenu.add(addGrade);
        gradeMenu.add(editGrade);
        gradeMenu.add(deleteGrade);

        exportMenu.add(csvExport);
        exportMenu.add(excelExport);

        helpMenu.add(informationHelp);
        helpMenu.add(exitHelp);

        addSemester.addActionListener(this);
        editSemester.addActionListener(this);
        deleteSemester.addActionListener(this);

        addGrade.addActionListener(this);
        editGrade.addActionListener(this);
        deleteGrade.addActionListener(this);

        csvExport.addActionListener(this);
        excelExport.addActionListener(this);

        informationHelp.addActionListener(this);
        exitHelp.addActionListener(this);

        menuBar.add(semesterMenu);
        menuBar.add(gradeMenu);
        menuBar.add(exportMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
