/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import proyecto.dataModel.enums.evaluationType;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.ExamTypes.Practical;
import proyecto.dataModel.evaluationRelated.ExamTypes.Test;
import proyecto.dataModel.evaluationRelated.ExamTypes.Theoric;
import proyecto.dataModel.evaluationRelated.Option;
import proyecto.dataModel.evaluationRelated.Question;
import proyecto.dataModel.evaluationRelated.Quiz;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class CreateEvaluationControl {
    private ArrayList<Question> questions;
    private ArrayList<Option> currentOptions;
    private Subject subject;

    public CreateEvaluationControl() {
        this.questions = new ArrayList<>();
        this.currentOptions = new ArrayList<>();
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Option> getCurrentOptions() {
        return currentOptions;
    }

    public void setCurrentOptions(ArrayList<Option> currentOptions) {
        this.currentOptions = currentOptions;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public void setSubject(int code) {
        this.subject = FilesManager.getSubjectByCode(code);
    }
    
    public void clearCurrentOptions() {
        currentOptions.clear();
    }
    
    public float getTotalWeighInQuestions() {
        int total = 0;
        for(Question element : questions) {
            total += element.getScore();
        }
        return total;
    }
    
    public void addQuestion(String statement, float score) {
        questions.add(
                new Question(
                        UUID.randomUUID().toString(),
                        statement,
                        score,
                        false,
                        new ArrayList<>(currentOptions)
                )
        );
        
        currentOptions.clear();
    }
    
    public void addOptionToCurrentsOptions(String value, boolean isAnswer) {
        currentOptions.add(
                new Option(
                        UUID.randomUUID().toString(),
                        value,
                        isAnswer
                )
        );
    }
    
    public void removeFromCurrentOptionsByIndex(int index) {
        currentOptions.remove(index);
    }
    
    public void removeFromQuestionsByIndex(int index) {
        questions.remove(index);
    }
    
    public boolean verifyHaveAnOption() {
        boolean aux = false;
        for(Option option : currentOptions) {
            if(option.getIsAnswer()) {
                aux = true;
            }
        }
        
        return aux;
    }
    
    public boolean verifyMoreThanOneAnswer() {
        // return true if the options only have one answer
        int count = 0;
        for(Option option : currentOptions) {
            if(option.getIsAnswer()) {
                count++;
            }
        }
        
        return (count == 1);
    }
    
    public Quiz createQuiz(evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        return new Quiz(
                UUID.randomUUID().toString(),
                type,
                weighing,
                initDate, 
                closeDate, 
                active, 
                tries
        );
    }
    
    public Practical createPractical(evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        return new Practical(
                UUID.randomUUID().toString(),
                type,
                weighing,
                initDate, 
                closeDate, 
                active, 
                tries
        );
    }
    
    public Test createTest(evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        return new Test(
                UUID.randomUUID().toString(),
                type,
                weighing,
                initDate, 
                closeDate, 
                active, 
                tries
        );
    }
    
    public Theoric createTheoric(evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        return new Theoric(
                UUID.randomUUID().toString(),
                type,
                weighing,
                initDate, 
                closeDate, 
                active, 
                tries
        );
    }
    
    public void createEvaluation(evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        Evaluation newEvaluation = null;
        
        switch(type.toString()) {
            case "Quiz":
                newEvaluation = createQuiz(type, weighing, initDate, closeDate, active, tries);
                break;
            case "Teorico":
                newEvaluation = createTheoric(type, weighing, initDate, closeDate, active, tries);
                break;
            case "Prueba":
                newEvaluation = createTest(type, weighing, initDate, closeDate, active, tries);
                break;
            case "Practico":
                newEvaluation = createPractical(type, weighing, initDate, closeDate, active, tries);
                break;
            default:
                break;
        }
        // set the questions to the evaluation
        newEvaluation.setQuestions(questions);
        // get all the evaluations and add the new one
        ArrayList<Evaluation> allEvaluations = FilesManager.getEvaluations();
        allEvaluations.add(newEvaluation);
        // save to file
        FilesManager.writeListToFile(allEvaluations, "evaluations");
        // add to the subject the evaluation
        subject.getEvaluations().add(newEvaluation);
        // save the new change to the object
        ArrayList<Subject> allSubjects = FilesManager.getSubjects();
        allSubjects.removeIf(element -> element.getCode() == subject.getCode());
        allSubjects.add(subject);
        // save the change to file
        FilesManager.writeListToFile(allSubjects, "subjects");
    }
}
