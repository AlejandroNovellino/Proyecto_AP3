/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.enums.filesNames;
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
    
    private void deleteUser(ArrayList<?> list, int index) {
        // get the user
        Student user = (Student) list.get(index);
        // remove the user from the list
        list.remove(index);
        // get all the users and delete the student
        ArrayList<User> users = FilesManager.getUsers();
        users.removeIf(element -> element.getId().equals(user.getId()));
        // save the changes to the file 
        FilesManager.writeListToFile(users, filesNames.users);
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
        FilesManager.writeListToFile(subjects, filesNames.subjects);
        // save the changes to the enrollments file
        FilesManager.writeListToFile(enrollments, filesNames.enrollments);
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
        FilesManager.writeListToFile(evaluations, filesNames.evaluations);
        // save the changes to the evaluationRegistries file
        FilesManager.writeListToFile(evaluationRegistries, filesNames.evaluationRegistries);
    }
    
    private void deleteEvaluation(ArrayList<?> list, int index) {
        // get the evaluation
        Evaluation evaluation = (Evaluation) list.get(index);
        // get the id
        String evaId = evaluation.getId();
        // remove the evaluation from the list
        list.remove(index);
        // delete the evaluation from the file 
        ArrayList<Evaluation> evaluations = FilesManager.getEvaluations();
        // delete the evaluation
        evaluations.removeIf(element -> element.getId().equals(evaId));
        // save the changes to the file
        FilesManager.writeListToFile(evaluations, filesNames.evaluations);
        // all the registries in the students must be deleted
        // get the students
        ArrayList<Student> students = FilesManager.getStudents();
        // delete the registries
        students.forEach(student -> {
            student.getEvaluationsRegistrys().removeIf(element -> element.getEvaluationId().equals(evaId));
        });
        // get admins to merge with the students and save the changes
        ArrayList<User> users = new ArrayList<>(
                FilesManager.getAdmins()
        );
        students.forEach(student -> users.add(student));
        // save changes to file
        FilesManager.writeListToFile(users, filesNames.users);
        // delete the registries in the registries file
        // get the registries
        ArrayList<EvaluationRegistry> registries = FilesManager.getEvaluationRegistries();
        // delete the registrie
        registries.removeIf(reg -> reg.getEvaluationId().equals(evaId));
        // save changes to file
        FilesManager.writeListToFile(registries, filesNames.evaluationRegistries);
        // delete the evaluation from the subject
        // get the subjects
        ArrayList<Subject> subjects = FilesManager.getSubjects();
        // delete the evaluations in the subject
        subjects.forEach(subject -> {
            subject.getEvaluations().removeIf(ev -> ev.getId().equals(evaId));
        });
        // save the changes to the file
        FilesManager.writeListToFile(subjects, filesNames.subjects);
    }
    
    private void deleteEvaluation(Evaluation evaluation) {
        // get the id
        String evaId = evaluation.getId();
        // delete the evaluation from the file 
        ArrayList<Evaluation> evaluations = FilesManager.getEvaluations();
        // delete the evaluation
        evaluations.removeIf(element -> element.getId().equals(evaId));
        // save the changes to the file
        FilesManager.writeListToFile(evaluations, filesNames.evaluations);
        // all the registries in the students must be deleted
        // get the students
        ArrayList<Student> students = FilesManager.getStudents();
        // delete the registries
        students.forEach(student -> {
            student.getEvaluationsRegistrys().removeIf(element -> element.getEvaluationId().equals(evaId));
        });
        // get admins to merge with the students and save the changes
        ArrayList<User> users = new ArrayList<>(
                FilesManager.getAdmins()
        );
        students.forEach(student -> users.add(student));
        // save changes to file
        FilesManager.writeListToFile(users, filesNames.users);
        // delete the registries in the registries file
        // get the registries
        ArrayList<EvaluationRegistry> registries = FilesManager.getEvaluationRegistries();
        // delete the registrie
        registries.removeIf(reg -> reg.getEvaluationId().equals(evaId));
        // save changes to file
        FilesManager.writeListToFile(registries, filesNames.evaluationRegistries);
        // delete the evaluation from the subject
        // get the subjects
        ArrayList<Subject> subjects = FilesManager.getSubjects();
        // delete the evaluations in the subject
        subjects.forEach(subject -> {
            subject.getEvaluations().removeIf(ev -> ev.getId().equals(evaId));
        });
        // save the changes to the file
        FilesManager.writeListToFile(subjects, filesNames.subjects);
    }
    
    private void deleteSubject(ArrayList<?> list, int index) {
        // get the subject
        Subject subject = (Subject) list.get(index);
        // geth the subject id
        String subjectId = subject.getId();
        // delete all the evaluations of the subject
        subject.getEvaluations().forEach(evaluation -> {
            deleteEvaluation(evaluation);
        });
        // update the evaluation list
        this.allEvaluations = FilesManager.getEvaluations();
        // delete all the enrollments in all the students
        // get the students
        ArrayList<Student> students = FilesManager.getStudents();
        // delete the enrollment
        students.forEach(student -> {
            student.getEnrollments().removeIf(element -> element.getSubjectId().equals(subjectId));
        });
        // get admins to merge with the students and save the changes
        ArrayList<User> users = new ArrayList<>(
                FilesManager.getAdmins()
        );
        students.forEach(student -> users.add(student));
        // save changes to file
        FilesManager.writeListToFile(users, filesNames.users);
        // delete the enrollments from the enrollments file
        // get the enrollments
        ArrayList<Enrollment> enrollments = FilesManager.getEnrollments();
        // delete
        enrollments.removeIf(enrollment -> enrollment.getSubjectId().equals(subjectId));
        // save the cahnges to file
        FilesManager.writeListToFile(enrollments, filesNames.enrollments);
        // delete the subject
        allSubjects.removeIf(element -> element.getId().equals(subjectId));
        // update the subject file
        FilesManager.writeListToFile(allSubjects, filesNames.subjects);
    }
    
    public void deleteFromList(ArrayList<?> list, int index, filesNames fileName) {
        switch (fileName) {
            case users:
                deleteUser(list, index);
                break;
            case evaluations:
                deleteEvaluation(list, index);
                break;
            case subjects:
                deleteSubject(list, index);
                break;
            default:
                break;
        }
    }
}
