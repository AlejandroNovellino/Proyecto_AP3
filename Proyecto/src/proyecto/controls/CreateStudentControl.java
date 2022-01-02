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
import proyecto.dataModel.users.User;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class CreateStudentControl {
    private ArrayList<Subject> allSubjects = null; // all subjects
    private ArrayList<Subject> viewedSubjects = null; // viewed subjects
    private ArrayList<Subject> notViewedSubjects = null; // not viewed subjects
    private ArrayList<Subject> enrolledSubjects = null; // currently enrolled subjects
    private Student currentStudent = null;
    
    public CreateStudentControl() {
        this.allSubjects = FilesManager.getSubjects();
        this.viewedSubjects = new ArrayList<>();
        this.notViewedSubjects = new ArrayList<>();
        this.enrolledSubjects  = new ArrayList<>();
    }
    
    public CreateStudentControl(Student student) {
        // set all subjects
        this.allSubjects = FilesManager.getSubjects();
        // set viewed subjects
        this.viewedSubjects = FilesManager.getViewedSubjectsForStudent(student);
        // set enrolled subjects 
        this.enrolledSubjects = FilesManager.getCurrentEnrollSubjectsForStudent(student);
        // set not viewed subjects
        ArrayList<Subject> aux = FilesManager.getSubjects();
        viewedSubjects.forEach(viewedSubject -> {
            aux.removeIf(subject -> subject.getId().equals(viewedSubject.getId()));
        });
        enrolledSubjects.forEach(viewedSubject -> {
            aux.removeIf(subject -> subject.getId().equals(viewedSubject.getId()));
        });
        this.notViewedSubjects = aux;
        // set student 
        this.currentStudent = student;
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

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
    
    public ArrayList<String> getAllSubjectsNames() {
        ArrayList<String> aux = new ArrayList<>();
        allSubjects.forEach(subject -> aux.add(subject.getName()));
        return aux;
    }

    @Override
    public String toString() {
        return "CreateStudentControl{" + "allSubjects=" + allSubjects + ", viewedSubjects=" + viewedSubjects + ", notViewedSubjects=" + notViewedSubjects + ", enrolledSubjects=" + enrolledSubjects + '}';
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
        ArrayList<User> allUsers = FilesManager.getUsers();
        allUsers.add(newStudent);
        FilesManager.writeListToFile(allUsers, "users");
        // add the enrollments to the file
        ArrayList<Enrollment> allEnrollments = FilesManager.getEnrollments();
        enrollments.forEach(enrollment -> allEnrollments.add(enrollment));
        FilesManager.writeListToFile(allEnrollments, "enrollments");
    }
    
    public void updateStudent(String names, String lastNames, int ci, gender gender, boolean status) {
        // set the new values to the student
        currentStudent.setNames(names);
        currentStudent.setLastNames(lastNames);
        currentStudent.setCi(ci);
        currentStudent.setGender(gender);
        currentStudent.setStatus(status);
        // delete all the old enrollments
        ArrayList<Enrollment> oldEnrollments = FilesManager.getEnrollments();
        oldEnrollments.removeIf(element -> element.getStudentId().equals(currentStudent.getId()));
        FilesManager.writeListToFile(oldEnrollments, "enrollments");
        // create the enrollments, the passed ones
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        viewedSubjects.forEach((subject) -> {
            enrollments.add(
                    new Enrollment(
                            UUID.randomUUID().toString(),
                            subject.getId(),
                            currentStudent.getId(),
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
                            currentStudent.getId(),
                            null, 
                            false)
            );
        });
        // set the enrollments
        currentStudent.setEnrollments(enrollments);
        // update the users file
        ArrayList<User> allUsers = FilesManager.getUsers();
        allUsers.removeIf(element -> element.getId().equals(currentStudent.getId()));
        // add student to the file
        allUsers.add(currentStudent);
        FilesManager.writeListToFile(allUsers, "users");
        // add the enrollments to the file
        ArrayList<Enrollment> allEnrollments = FilesManager.getEnrollments();
        enrollments.forEach(enrollment -> allEnrollments.add(enrollment));
        FilesManager.writeListToFile(allEnrollments, "enrollments");
    }
}
