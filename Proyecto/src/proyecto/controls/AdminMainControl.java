/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.manyToManyRelations.Enrollment;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
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
        switch (fileName) {
            case "users":
                // get the user
                Student user = (Student) list.get(index);
                // remove the user from the list
                list.remove(index);
                // get all the users and delete the student
                ArrayList<User> users = FilesManager.getUsers();
                users.removeIf(element -> element.getId().equals(user.getId()));
                // save the changes to the file 
                FilesManager.writeListToFile(users, fileName);
                // now the enrollments must be deleted and the evaluations registries
                // delete the enrollments from the enrollments file and the subjects file
                // deleting the enrollments in the subjects file
                ArrayList<Subject> subjects = FilesManager.getSubjects();
                user.getEnrollments().forEach(enrollment -> {
                    subjects.forEach(subject -> {
                       subject.getEnrrolments().removeIf(element -> element.getId().equals(enrollment.getId()));
                    });
                });
                // deleting the enrollments in the enrolmments file
                ArrayList<Enrollment> enrollments = FilesManager.getEnrollments();
                user.getEnrollments().forEach(enrollment -> {
                    enrollments.removeIf(element -> element.getId().equals(enrollment.getId()));
                });
                // save the changes to the subjects file
                FilesManager.writeListToFile(subjects, "subjects");
                // save the changes to the enrollments file
                FilesManager.writeListToFile(enrollments, "enrollments");
                ///////////////////////////////////////////////////////////////// NO SE SI SE DEBERIA ELIMINAR
                // delete the registries from the registries file and the evaluations file
                // deleting the registries in the evaluations file
                ArrayList<Evaluation> evaluations = FilesManager.getEvaluations();
                user.getEvaluationsRegistrys().forEach(registry -> {
                    evaluations.forEach(evaluation -> {
                       evaluation.getResults().removeIf(element -> element.getId().equals(registry.getId()));
                    });
                });
                // deleting the registries in the evaluationRegistries file
                ArrayList<EvaluationRegistry> evaluationRegistries = FilesManager.getEvaluationRegistries();
                user.getEvaluationsRegistrys().forEach(registry -> {
                    evaluationRegistries.removeIf(element -> element.getId().equals(registry.getId()));
                });
                // save the changes to the evaluations file
                FilesManager.writeListToFile(evaluations, "evaluations");
                // save the changes to the evaluationRegistries file
                FilesManager.writeListToFile(evaluationRegistries, "evaluationRegistries");
                break;
            case "evaluations":
                // REVISAR
                FilesManager.writeListToFile(list, fileName);
                // all the enrollments to the evaluation must be deleted
                break;
            case "subjects":
                // REVISAR
                FilesManager.writeListToFile(list, fileName);
            
                break;
            default:
                break;
        }
    }
}
