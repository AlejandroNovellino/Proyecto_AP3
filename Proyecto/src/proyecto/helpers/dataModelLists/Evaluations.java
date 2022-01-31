/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers.dataModelLists;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.ExamTypes.Practical;
import proyecto.dataModel.evaluationRelated.ExamTypes.Test;
import proyecto.dataModel.evaluationRelated.ExamTypes.Theoric;
import proyecto.dataModel.evaluationRelated.Quiz;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "evaluations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Evaluations {
    // quiz list
    @XmlElementWrapper(name="quizes")
    @XmlElement(name="quiz")
    private ArrayList<Quiz> quizes;
    // practical exam list
    @XmlElementWrapper(name="practicalExams")
    @XmlElement(name="practical")
    private ArrayList<Practical> practicalExams;
    // test exam list
    @XmlElementWrapper(name="testExams")
    @XmlElement(name="test")
    private ArrayList<Test> testExams;
    // theoric exam list
    @XmlElementWrapper(name="theoricExams")
    @XmlElement(name="theoric")
    private ArrayList<Theoric> theoricExams;

    public Evaluations() {
    }

    public Evaluations(ArrayList<Evaluation> evaluations) {
        this.quizes = new ArrayList<>();
        this.practicalExams = new ArrayList<>();
        this.testExams = new ArrayList<>();
        this.theoricExams = new ArrayList<>();
        
        // get all the diferents evaluations
        evaluations.forEach(element -> {
            switch(element.getType().toString()) {
                case "Quiz":
                    quizes.add((Quiz)element);
                    break;
                case "Teorico":
                    theoricExams.add((Theoric)element);
                    break;
                case "Prueba":
                    testExams.add((Test)element);
                    break;
                case "Practico":
                    practicalExams.add((Practical)element);
                    break;
                default:
                    break;
            }
        });
    }
    
    public ArrayList<Evaluation> getEvaluations() {
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        // add all the quizes
        quizes.forEach(element -> {
            evaluations.add(element);
        });
        // add all the practical exams
        practicalExams.forEach(element -> {
            evaluations.add(element);
        });
        // add all the test exams
        testExams.forEach(element -> {
            evaluations.add(element);
        });
        // add all the theoric exmans
        theoricExams.forEach(element -> {
            evaluations.add(element);
        });
        
        return evaluations;
    }
}
