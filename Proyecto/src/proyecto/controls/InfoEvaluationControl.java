/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.Option;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
import proyecto.dataModel.users.Student;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class InfoEvaluationControl {
    Evaluation evaluation; 
    ArrayList<Student> students;

    public InfoEvaluationControl(Evaluation evaluation) {
        this.evaluation = evaluation;
        // set the students
        students = new ArrayList<>();
        ArrayList<Student> aux = FilesManager.getStudents();
        aux.forEach(student -> {
            student.getEvaluationsRegistrys().forEach(evaReg -> {
                if(evaReg.getEvaluationId().equals(this.evaluation.getId())) {
                    students.add(student);
                }
            });
        });
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    
    public Float getStudentNote(Student student) {
       for(EvaluationRegistry evaReg : student.getEvaluationsRegistrys()) {
            if(evaReg.getEvaluationId().equals(evaluation.getId())) {
                return evaReg.getNote();
            }
        }
       return null;
    }
    
    public ArrayList<Option> getStudentOptions(int ci) {
        for(Student student : students) {
            if(student.getCi() == ci) {
                for(EvaluationRegistry evaReg : student.getEvaluationsRegistrys()) {
                    if(evaReg.getEvaluationId().equals(evaluation.getId())) {
                        return evaReg.getAnswers();
                    }
                }
            }
        }
        return null;
    }
    
    public int amountPassed() {
        int aux = 0;
        for(Student student : students) {
            for(EvaluationRegistry evaReg : student.getEvaluationsRegistrys()) {
                if(evaReg.getEvaluationId().equals(evaluation.getId())) {
                    if(Math.round(evaReg.getNote())>=10) {
                        aux++;
                    }
                }
            }
        }
        return aux;
    }
}
