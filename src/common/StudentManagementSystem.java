package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import model.Student;

public class StudentManagementSystem extends ArrayList<Student> {

    Library library = new Library();

    public void create() {
        String id = library.getString("Input student ID: ");
        String name = library.getString("Input student name: ");
        String semester = library.getString("Input semester: ");
        String course = library.getString("Input student course: ");

        this.add(new Student(id, name, semester, course));

    }

    public void detele(String studentId) {
        try {
            for (Student student : this) {
                if (student.getId().equalsIgnoreCase(studentId)) {
                    this.remove(student);
                }
            }
        } catch (Exception e) {
        }

    }

    public void update(String studentId) {
        for (Student student : this) {
            if (student.getId().equalsIgnoreCase(studentId)) {

                String name = library.getString("Input update name : ");
                student.setStudentName(name);
                String semester = library.getString("Input update semester: ");
                student.setSemester(semester);
                String course = library.getString("Input update course: ");
                student.setCourseName(course);

            } else {
                System.out.println("ID of student not found to update!");
            }
        }
    }

    public List<Student> sort(List<Student> list) {
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentName().compareTo(o2.getStudentName());
            }
        });
        return list;
    }

    public List<Student> searchByName(String studentName) {
//        return this.stream().filter(s -> s.getStudentName().contains(studentName)).collect(Collectors.toList());
        List<Student> result = new ArrayList<>();
        for (Student student : this) {
            if(student.getStudentName().equalsIgnoreCase(studentName)) {
                result.add(student);
            }
        }
        return result;
    }

    public Student searchById(String studentId) {
        for (Student student : this) {
            if (student.getId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void report() {
        
        String studentNamePrev = null;
        String coursePrev = null;
        int count = 0;
        sort(this);
        for (Student thi : this) {
            if(thi.getStudentName().equalsIgnoreCase(studentNamePrev) 
                    && thi.getCourseName().equalsIgnoreCase(coursePrev)) {
                count++;
                
            } else {
                count = 1;
            }
            studentNamePrev = thi.getStudentName();
            coursePrev = thi.getCourseName();
            System.out.println(thi.getStudentName() + " | " + thi.getCourseName() + " | " + count);
        }
        
    }

    public void display(List<Student> list) {
        for (Student student : list) {
            System.out.println(student);
        }
    }

}
