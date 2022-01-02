/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import java.util.UUID;
import proyecto.dataModel.enums.gender;
import proyecto.dataModel.enums.userType;
import proyecto.dataModel.manyToManyRelations.Enrollment;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.dataModel.users.Student;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class CreateStudentControl {
    private static CreateStudentControl uniqueInstance = null;
    private ArrayList<Subject> allSubjects = null;
    private ArrayList<Subject> viewedSubjects = null;
    private ArrayList<Subject> notViewedSubjects = null;
    private ArrayList<Subject> enrolledSubjects = null;
    private ArrayList<Subject> posibleToEnrollSubjects = null;
   
    
    public CreateStudentControl() {
        this.allSubjects = FilesManager.getSubjects();
        this.viewedSubjects = new ArrayList<>();
        this.notViewedSubjects = new ArrayList<>();
        this.enrolledSubjects  = new ArrayList<>();
        this.posibleToEnrollSubjects = new ArrayList<>();
    }
    
    public CreateStudentControl(Student student) {
        // set all subjects
        this.allSubjects = FilesManager.getSubjects();
        // set viewed subjects
        this.viewedSubjects = FilesManager.getViewedSubjectsForStudent(student);
        // set not viewed subjects
        this.notViewedSubjects = FilesManager.getNotViewedSubjectsForStudent(student);
        // set enrolled subjects 
        this.enrolledSubjects = FilesManager.getCurrentEnrollSubjectsForStudent(student);
        // set posible to enroll subjects
        //this.posibleToEnrollSubjects = FilesManager.getCurrentPosibleToEnrollSubjectsForStudent(student);
    }
    
    public static CreateStudentControl getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new CreateStudentControl();
        }
        return uniqueInstance;
    }   
    
    public static CreateStudentControl getInstance(Student student) {
        if(uniqueInstance == null) {
            uniqueInstance = new CreateStudentControl(student);
        }
        return uniqueInstance;
    } 
    
    public static void killUniqueInstance() {
        uniqueInstance = null;
    }

    public ArrayList<Subject> getAllSubjects() {
        return allSubjects;
    }

    public void setAllSubjects(ArrayList<Subject> allSubjects) {
        this.allSubjects = allSubjects;
    }

    public ArrayList<Subject> getViewedSubjects() {
        return viewedSubjects;
    }

    public void setViewedSubjects(ArrayList<Subject> viewedSubjects) {
        this.viewedSubjects = viewedSubjects;
    }

    public ArrayList<Subject> getNotViewedSubjects() {
        return notViewedSubjects;
    }

    public void setNotViewedSubjects(ArrayList<Subject> notViewedSubjects) {
        this.notViewedSubjects = notViewedSubjects;
    }

    public ArrayList<Subject> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    public void setEnrolledSubjects(ArrayList<Subject> enrolledSubjects) {
        this.enrolledSubjects = enrolledSubjects;
    }

    public ArrayList<Subject> getPosibleToEnrollSubjects() {
        return posibleToEnrollSubjects;
    }

    public void setPosibleToEnrollSubjects(ArrayList<Subject> posibleToEnrollSubjects) {
        this.posibleToEnrollSubjects = posibleToEnrollSubjects;
    }
    
    public ArrayList<String> getAllSubjectsNames() {
        ArrayList<String> aux = new ArrayList<>();
        allSubjects.forEach(subject -> aux.add(subject.getName()));
        return aux;
    }

    @Override
    public String toString() {
        return "CreateStudentControl{" + "allSubjects=" + allSubjects + ", viewedSubjects=" + viewedSubjects + ", notViewedSubjects=" + notViewedSubjects + ", enrolledSubjects=" + enrolledSubjects + ", posibleToEnrollSubjects=" + posibleToEnrollSubjects + '}';
    }
    
    public void addToViewedSubjects(Subject subject) {
        viewedSubjects.add(subject);
    }
    
    public void addToNotViewedSubjects(Subject subject) {
        notViewedSubjects.add(subject);
    }
    
    public void addToEnrolledSubjects(Subject subject) {
        enrolledSubjects.add(subject);
    }
    
    public void addToPosibleToEnrollSubjects(Subject subject) {
        posibleToEnrollSubjects.add(subject);
    }
    
    public void removeFromArrayList(ArrayList<Subject> list, String id) {
        list.removeIf(subject -> subject.getId().equals(id));
    }
    
    public void removeFromViewedSubjects(String id) {
        removeFromArrayList(viewedSubjects, id);
    }
    
    public void removeFromNotViewedSubjects(String id) {
        removeFromArrayList(notViewedSubjects, id);
    }
    
    public void removeFromEnrolledSubjects(String id) {
        removeFromArrayList(enrolledSubjects, id);
    }
     
    public void removeFromPosibleToEnrollSubjects(String id) {
        removeFromArrayList(posibleToEnrollSubjects, id);
    }
    
    public void updateLists() {
        ArrayList<Integer> codesFromViewedSubjects = new ArrayList<>();
        viewedSubjects.forEach(subject -> codesFromViewedSubjects.add(subject.getCode()));
        for(Subject subject : notViewedSubjects) {
            if(posibleToEnrollSubjects.contains(subject)) {
                posibleToEnrollSubjects.add(subject);
            }
        }
        for(Subject subject : viewedSubjects) {
            if(posibleToEnrollSubjects.contains(subject)) {
                posibleToEnrollSubjects.remove(subject);
            }
        }
        for(Subject subject : enrolledSubjects) {
            if(posibleToEnrollSubjects.contains(subject)) {
                posibleToEnrollSubjects.remove(subject);
                notViewedSubjects.remove(subject);
            }
        }
        codesFromViewedSubjects.clear();
        viewedSubjects.forEach(subject -> codesFromViewedSubjects.add(subject.getCode()));
        System.out.println(viewedSubjects);
        for(Subject subject : posibleToEnrollSubjects) {
            if(!notViewedSubjects.contains(subject)) {
                notViewedSubjects.add(subject);
            }
            if(!posibleToEnrollSubjects.contains(subject)) {
                posibleToEnrollSubjects.add(subject);
            }
        }
        for(Subject subject : notViewedSubjects) {
            if(posibleToEnrollSubjects.contains(subject)) {
                posibleToEnrollSubjects.remove(subject);
            }
        }
    }
    
    public void createStudent(String names, String lastNames, int ci, gender gender, boolean status) {
        //create the student
        String id = UUID.randomUUID().toString();
        String userName = names.concat(lastNames);
        String passWord = Integer.toString(ci);
        userType type = userType.STUDENT;
        Student newStudent = new Student(names, lastNames, ci, gender, status, id, userName, passWord, type);
        // create the enrollments, the passed ones
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        viewedSubjects.forEach((subject) -> {
            enrollments.add(
                    new Enrollment(
                            UUID.randomUUID().toString(),
                            subject.getId(),
                            id,
                            20, 
                            true)
            );
        });
        // create the enrollments, the current ones
        enrolledSubjects.forEach(subject -> {
            enrollments.add(
                    new Enrollment(
                            UUID.randomUUID().toString(),
                            subject.getId(),
                            id,
                            null, 
                            false)
            );
        });
        // set the enrollments
        newStudent.setEnrollments(enrollments);
        // add student to the file
        ArrayList<Student> allStudents = FilesManager.getStudents();
        allStudents.add(newStudent);
        FilesManager.writeListToFile(allStudents, "users");
        // add the enrollments to the file
        ArrayList<Enrollment> allEnrollments = FilesManager.getEnrollments();
        enrollments.forEach(enrollment -> allEnrollments.add(enrollment));
        FilesManager.writeListToFile(allEnrollments, "enrollments");
    }
}
