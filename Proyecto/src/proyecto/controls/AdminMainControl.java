/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class AdminMainControl {
    ArrayList<Student> allStudents = null;
    ArrayList<Subject> allSubjects = null;
    ArrayList<Evaluation> allEvaluations = null;

    public AdminMainControl() {
        allStudents = FilesManager.getStudents();
        allSubjects = FilesManager.getSubjects();
        allEvaluations = FilesManager.getEvaluations();
    }

    public ArrayList<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(ArrayList<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public ArrayList<Subject> getAllSubjects() {
        return allSubjects;
    }

    public void setAllSubjects(ArrayList<Subject> allSubjects) {
        this.allSubjects = allSubjects;
    }

    public ArrayList<Evaluation> getAllEvaluations() {
        return allEvaluations;
    }

    public void setAllEvaluations(ArrayList<Evaluation> allEvaluations) {
        this.allEvaluations = allEvaluations;
    }
    
    public <E> E getElementByIndex(ArrayList<E> list, int index) {
        return list.get(index);
    }
    
    public void deleteFromList(ArrayList<?> list, int index, String fileName) {
        list.remove(index);
        if(fileName.equals("users")) {
            ArrayList<User> newList = new ArrayList<>();
            FilesManager.getAdmins().forEach(element -> newList.add(element));
            list.forEach(element -> newList.add((Student)element));
            FilesManager.writeListToFile(newList, fileName);
        } else if(fileName.equals("evaluations")){
            // REVISAR
            FilesManager.writeListToFile(list, fileName);
            // all the enrollments to the evaluation must be deleted
        } else {
            FilesManager.writeListToFile(list, fileName);
        }
    }
}
