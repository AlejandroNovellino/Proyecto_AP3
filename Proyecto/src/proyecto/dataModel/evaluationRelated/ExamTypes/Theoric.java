/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.evaluationRelated.ExamTypes;

import java.util.Date;
import proyecto.dataModel.enums.evaluationType;
import proyecto.dataModel.evaluationRelated.Exam;

/**
 *
 * @author Alejandro
 */
public class Theoric extends Exam{

    public Theoric() {
    }

    public Theoric(String id, evaluationType type, float weighing, Date initDate, Date closeDate, boolean active, int tries) {
        super(id, type, weighing, initDate, closeDate, active, tries);
    }
}
