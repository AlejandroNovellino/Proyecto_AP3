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
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "evaluationRegistries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EvaluationRegistries {
    @XmlElementWrapper(name="evaluationRegistrys")
    @XmlElement(name="evaluationRegistry")
    private ArrayList<EvaluationRegistry> evaluationRegistries;

    public EvaluationRegistries() {
    }
    
    public EvaluationRegistries(ArrayList<EvaluationRegistry> evaluationRegistries) {
        this.evaluationRegistries = evaluationRegistries;
    }

    public ArrayList<EvaluationRegistry> getEvaluationRegistries() {
        return evaluationRegistries;
    }

    public void setEvaluationRegistries(ArrayList<EvaluationRegistry> evaluationRegistries) {
        this.evaluationRegistries = evaluationRegistries;
    }
}
