/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.Option;
import proyecto.dataModel.evaluationRelated.Question;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class PresentEvaluationControl {
    private Student student;
    private ArrayList<Option> answers;
    private Evaluation evaluation;
    private EvaluationRegistry evaluationRegistry;
    private Integer questionIndex = 0;
    private Float currentNote = 0f;

    public PresentEvaluationControl(Student student, Evaluation evaluation, EvaluationRegistry evaluationRegistry) {
        this.student = student;
        this.answers = new ArrayList<>(); 
        this.evaluation = evaluation;
        this.evaluationRegistry = evaluationRegistry;
        this.evaluationRegistry.setNumTries(this.evaluationRegistry.getNumTries()+1);
        this.evaluationRegistry.setPresented(true);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Option> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Option> answers) {
        this.answers = answers;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public EvaluationRegistry getEvaluationRegistry() {
        return evaluationRegistry;
    }

    public void setEvaluationRegistry(EvaluationRegistry evaluationRegistry) {
        this.evaluationRegistry = evaluationRegistry;
    }

    public Integer getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(Integer questionIndex) {
        this.questionIndex = questionIndex;
    }
    
    public void passQuestion() {
        questionIndex++;
    }
    
    public Question getCurrentQuestion() {
        return evaluation.getQuestions().get(questionIndex);
    }

    public Float getCurrentNote() {
        return currentNote;
    }

    public void setCurrentNote(Float currentNote) {
        this.currentNote = currentNote;
    }
    
    public void addToSelectedAnswers(int index) {
        if(index == -1) {
            answers.add(null);
        } else {
            answers.add(
                evaluation.getQuestions().get(questionIndex).getOptions().get(index)
            );
        }
    }
    
    public void setResultsOfExam() {
        // just loop until the index 
        //float note = 0;
        for(int i=0; i<=questionIndex; i++) {
            // if the question was not answered continue
            if(answers.get(i) == null) continue;
            // if it was answered set the note
            if(answers.get(i).getIsAnswer()) {
                currentNote += evaluation.getQuestions().get(i).getScore();
            }
        }
        // if it is the first time presenting the evaluation set the score
        if(evaluationRegistry.getNote() == null) {
            // set answers in the registry
            evaluationRegistry.setAnswers(answers);
            // set the score in the evaluation registry
            evaluationRegistry.setNote(currentNote);
        } else if(currentNote>evaluationRegistry.getNote()) { // if it is another try take the answers and the note only if the note is greater
            // set answers in the registry
            evaluationRegistry.setAnswers(answers);
            // set the score in the evaluation registry
            evaluationRegistry.setNote(currentNote);
        }
    }
    
    public void saveChangesToFile() {
        System.out.println(evaluationRegistry);
        System.out.println(student);
        System.out.println(evaluation);
//        // update the users file
//        ArrayList<User> allUsers = FilesManager.getUsers();
//        allUsers.removeIf(user -> user.getId().equals(student.getId()));
//        allUsers.add(student);
//        FilesManager.writeListToFile(allUsers, "users");
//        // update the evaluation file
//        // get the evaluations 
//        ArrayList<Evaluation> evaluations = FilesManager.getEvaluations();
//        // update the registry from the evaluation
//        for(Evaluation evaluationAux : evaluations) {
//            if(evaluationAux.getId().equals(evaluation.getId())) {
//                // remove the outdated
//                evaluationAux.getResults().removeIf(element -> element.getId().equals(evaluation.getId()));
//                // add the updated
//                evaluationAux.getResults().add(evaluationRegistry);
//            }
//        }
//        // save the evaluations to the file
//        FilesManager.writeListToFile(evaluations, "evaluations");
//        // update the evaluation registries file
    }
}
