/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import java.util.UUID;
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
public class StudentMainControl {
    
     public class SubjectInfo {
        private Subject subject;
        private Enrollment enrollment;

        public SubjectInfo(Subject subject, Enrollment enrollment) {
            this.subject = subject;
            this.enrollment = enrollment;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Enrollment getEnrollment() {
            return enrollment;
        }

        public void setEnrollment(Enrollment enrollment) {
            this.enrollment = enrollment;
        }
    }
    
    public class EvaluationInfo {
        private Evaluation evaluation;
        private EvaluationRegistry evaReg;

        public EvaluationInfo(Evaluation evaluation, EvaluationRegistry evaReg) {
            this.evaluation = evaluation;
            this.evaReg = evaReg;
        }

        public Evaluation getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(Evaluation evaluation) {
            this.evaluation = evaluation;
        }

        public EvaluationRegistry getEvaReg() {
            return evaReg;
        }

        public void setEvaReg(EvaluationRegistry evaReg) {
            this.evaReg = evaReg;
        }
    }
    
    private Student currentStudent;
    private ArrayList<SubjectInfo> subjectsInfo;
    private ArrayList<EvaluationInfo> evaluationsInfo;
    private ArrayList<Evaluation> evaluationsPosibleToRegister;
    private Evaluation selectedEvaluation;

    public StudentMainControl() {
    }
    
    public StudentMainControl(Student currentStudent) {
        this.currentStudent = currentStudent;
        setValues();
    }
    
    public void setValues() {
        // set variables
        this.subjectsInfo = new ArrayList<>();
        this.evaluationsInfo = new ArrayList<>();
        this.evaluationsPosibleToRegister = new ArrayList<>();
        // set subjectsInfo
        currentStudent.getEnrollments().forEach(enrollment -> {
            subjectsInfo.add(
                    new SubjectInfo(
                            FilesManager.getSubjectById(enrollment.getSubjectId()),
                            enrollment
                    )
            );
        });
        // set evaluationsInfo
        currentStudent.getEvaluationsRegistrys().forEach(registry -> {
            evaluationsInfo.add(
                    new EvaluationInfo(
                            FilesManager.getEvaluationById(registry.getEvaluationId()), 
                            registry
                    )
            );
        });
        // set evaluationsPosibleToRegister
        subjectsInfo.forEach(element -> {
            element.getSubject().getEvaluations().forEach(evaluation -> {
                if(evaluation.getIsActive() && !currentStudentIsEnrolledInEvaluation(evaluation)) {
                    evaluationsPosibleToRegister.add(evaluation);
                }
            });
        });
    }
    
    public boolean currentStudentIsEnrolledInEvaluation(Evaluation evaluation) {
        boolean aux = false;
        for(EvaluationRegistry evaReg : currentStudent.getEvaluationsRegistrys()) {
            if(evaReg.getEvaluationId().equals(evaluation.getId())) {
                aux = true;
            }
        }
        return aux;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public ArrayList<SubjectInfo> getSubjectsInfo() {
        return subjectsInfo;
    }

    public void setSubjectsInfo(ArrayList<SubjectInfo> subjectsInfo) {
        this.subjectsInfo = subjectsInfo;
    }

    public ArrayList<EvaluationInfo> getEvaluationsInfo() {
        return evaluationsInfo;
    }

    public void setEvaluationsInfo(ArrayList<EvaluationInfo> evaluationsInfo) {
        this.evaluationsInfo = evaluationsInfo;
    }

    public ArrayList<Evaluation> getEvaluationsPosibleToRegister() {
        return evaluationsPosibleToRegister;
    }

    public void setEvaluationsPosibleToRegister(ArrayList<Evaluation> evaluationsPosibleToRegister) {
        this.evaluationsPosibleToRegister = evaluationsPosibleToRegister;
    }

    public Evaluation getSelectedEvaluation() {
        return selectedEvaluation;
    }

    public void setSelectedEvaluation(Evaluation selectedEvaluation) {
        this.selectedEvaluation = selectedEvaluation;
    }
    
    public void setSelectedProssibleToEnrollEvaluationByIndex(int index) {
        this.selectedEvaluation = evaluationsPosibleToRegister.get(index);
    }
    
    public void setSelectedEvaluationByIndex(int index) {
        this.selectedEvaluation = evaluationsInfo.get(index).getEvaluation();
    }
    
    public EvaluationRegistry getEvaluationRegistryForEvaluation(Evaluation evaluation) {
        for(EvaluationRegistry element : currentStudent.getEvaluationsRegistrys()) {
            if(element.getEvaluationId().equals(evaluation.getId())) {
                return element;
            }
        }
        return null;
    }
    
    public void createEvaluationRegistry() {
        // create the registry
        EvaluationRegistry newRegistry = new EvaluationRegistry(
                UUID.randomUUID().toString(), 
                selectedEvaluation.getId(), 
                currentStudent.getId(), 
                0, 
                0, 
                false, 
                false
        );
        // get all the evaluationRegistries
        ArrayList<EvaluationRegistry> allRegistries = FilesManager.getEvaluationRegistries();
        // add the new 
        allRegistries.add(newRegistry);
        // save to the file
        FilesManager.writeListToFile(allRegistries, "evaluationRegistries");
        // add the registry to the student
        currentStudent.getEvaluationsRegistrys().add(newRegistry);
        // update the users file
        ArrayList<User> allUsers = FilesManager.getUsers();
        allUsers.removeIf(user -> user.getId().equals(currentStudent.getId()));
        allUsers.add(currentStudent);
        FilesManager.writeListToFile(allUsers, "users");
        // get the subjects and add the registry to the subject
        ArrayList<Evaluation> evaluations = FilesManager.getEvaluations();
        // add the registry to the evaluation
        for(Evaluation evaluation : evaluations) {
            if(evaluation.getId().equals(selectedEvaluation.getId())) {
                evaluation.getResults().add(newRegistry);
            }
        }
        // save the evaluations to the file
        FilesManager.writeListToFile(evaluations, "evaluations");
        // set the new values to the variables
        this.setValues();
        selectedEvaluation = null;
    }
    
    public void deleteEvaluationRegistry() {
        // get the current evaluations registered by the student
        ArrayList<EvaluationRegistry> currentRegistries = currentStudent.getEvaluationsRegistrys();
        // remove the selected registry
        currentRegistries.removeIf(element -> element.getEvaluationId().equals(selectedEvaluation.getId()));
        // set the current evaluations registered
        currentStudent.setEvaluationsRegistrys(currentRegistries);
        // get all the evaluationRegistries
        ArrayList<EvaluationRegistry> allRegistries = FilesManager.getEvaluationRegistries();
        // delete the selected one
        allRegistries.removeIf(registry -> registry.getEvaluationId().equals(selectedEvaluation.getId()));
        // save to the file
        FilesManager.writeListToFile(allRegistries, "evaluationRegistries");
        // update the users file
        ArrayList<User> allUsers = FilesManager.getUsers();
        allUsers.removeIf(user -> user.getId().equals(currentStudent.getId()));
        allUsers.add(currentStudent);
        FilesManager.writeListToFile(allUsers, "users");
        // set the new values to the variables
        this.setValues();
        selectedEvaluation = null;
    }
    
    public void presentEvaluation() {
        
    }
}
