/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.evaluationRelated;

import java.io.Serializable;

/**
 *
 * @author Alejandro
 */
public class Option implements Serializable{
    private String id;
    private String value; //valor - lo que indica la opcion
    private boolean isAnswer;

    public Option() {
    }

    public Option(String id, String value, boolean isAnswer) {
        this.id = id;
        this.value = value;
        this.isAnswer = isAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", value=" + value + ", isAnswer=" + isAnswer + '}';
    }
}
