/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.evaluationRelated;

import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import proyecto.dataModel.enums.evaluationType;

/**
 *
 * @author Alejandro
 */
public abstract class  Evaluation implements Serializable{
    protected String id;
    protected evaluationType type; //tipo de evaluacion
    protected float weighing; //ponderacion
    protected Date initDate;
    protected Date closeDate;
    protected boolean active;
    protected int tries; //intentos
    protected ArrayList<EvaluationRegistry> results;
    protected ArrayList<Question> questions;

    public Evaluation() {
    }

    public Evaluation(String id, evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        this.id = id;
        this.type = type;
        this.weighing = weighing;
        this.initDate = initDate;
        this.closeDate = closeDate;
        this.active = active;
        this.tries = tries;
        this.results = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public evaluationType getType() {
        return type;
    }

    public void setType(evaluationType type) {
        this.type = type;
    }

    public float getWeighing() {
        return weighing;
    }

    public void setWeighing(float weighing) {
        this.weighing = weighing;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public ArrayList<EvaluationRegistry> getResults() {
        return results;
    }

    public void setResults(ArrayList<EvaluationRegistry> results) {
        this.results = results;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", type=" + type + ", weighing=" + weighing + ", initDate=" + initDate + ", closeDate=" + closeDate + ", active=" + active + ", tries=" + tries + '}';
    }
}
