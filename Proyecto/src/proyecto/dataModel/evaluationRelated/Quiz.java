/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.evaluationRelated;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import proyecto.dataModel.enums.evaluationType;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "quiz")
public class Quiz extends Evaluation{

    public Quiz() {
    }

    public Quiz(String id, evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        super(id, type, weighing, initDate, closeDate, active, tries);
    }
    
}
