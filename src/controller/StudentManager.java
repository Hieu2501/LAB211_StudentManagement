package controller;

import common.StudentManagementSystem;
import common.Library;
import java.util.List;
import model.Student;
import view.Menu;

public class StudentManager extends Menu<String> {

    static String[] mc = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};

    protected Library library;
    protected StudentManagementSystem studentManSys;

    public StudentManager() {
        super("PROGRAMMING", mc);
        library = new Library();
        studentManSys = new StudentManagementSystem();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                if (studentManSys.size() > 10) {
                    String choice = library.getString("Do you want to continue (Y/N)?: ");
                    if (choice.equalsIgnoreCase("Y")) {
                        studentManSys.create();
                    } else if (choice.equalsIgnoreCase("N")) {
                        break;
                    }
                } else {
                    studentManSys.create();
                }
                break;
            case 2:
                String studentNameSearch = library.getString("Input name of student to search: ");
                studentManSys.display(studentManSys.sort(studentManSys.searchByName(studentNameSearch)));
                break;
            case 3:
                String studentIdSearch = library.getString("Input ID of student to search: ");
                if (studentManSys.searchById(studentIdSearch) != null) {
                    String choiceDU = library.getString("Do you want to update (U) or delete (D) student: ");
                    if(choiceDU.equalsIgnoreCase("D")) {
                        studentManSys.detele(studentIdSearch);
                    } else if(choiceDU.equalsIgnoreCase("U")) {
                        studentManSys.update(studentIdSearch);
                    } else {
                        System.out.println("Your choice invalid!");
                    }
                }
                break;
            case 4:
                studentManSys.report();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Your choice invalid! Pls input another choice");
        }
    }
}
