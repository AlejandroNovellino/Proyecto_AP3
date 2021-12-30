/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.subjectRelated.Prelation;
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
        this.posibleToEnrollSubjects = FilesManager.getCurrentPosibleToEnrollSubjectsForStudent(student);
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
    
    public boolean checkPrelations(Subject notViewedSubject, ArrayList<Integer> codes) {
        
        boolean aux = true;
        try {
            for(Integer code : notViewedSubject.getPrelation().getSubjectsCodes()) {
                if(!codes.contains(code)) {
                    aux = false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return aux;
    }
    
    public void updateLists() {
        ArrayList<Integer> codesFromViewedSubjects = new ArrayList<>();
        viewedSubjects.forEach(subject -> codesFromViewedSubjects.add(subject.getCode()));
        for(Subject subject : notViewedSubjects) {
            if(checkPrelations(subject, codesFromViewedSubjects) && !posibleToEnrollSubjects.contains(subject)) {
                posibleToEnrollSubjects.add(subject);
            }
        }
        for(Subject subject : viewedSubjects) {
            if(posibleToEnrollSubjects.contains(subject)) {
                posibleToEnrollSubjects.remove(subject);
            }
        }
    }
}
