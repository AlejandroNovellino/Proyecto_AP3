/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import proyecto.controls.InfoEvaluationControl;
import proyecto.dataModel.evaluationRelated.Option;
import proyecto.dataModel.evaluationRelated.Question;
import proyecto.dataModel.users.Student;
/**
 *
 * @author Alejandro
 */
public class ReportGenerator {
    
    public static void generateEvaluationReport(InfoEvaluationControl control) throws IOException {
        try {
            // delete the file if it exist
            File auxFileToDelete = new File("src/proyecto/reports/reporte_"+control.getEvaluation().getId()+".pdf");
            auxFileToDelete.delete();
        } catch (Exception e) {
            System.out.println("File can not be deleted");
        }
        
        // create the file
        try {
            // create the document
            PDDocument doc = new PDDocument();
            // create one page
            PDPage myPage = new PDPage();
            // add the page to the file
            doc.addPage(myPage);
            // create the stream to set the text
            PDPageContentStream content = new PDPageContentStream(doc, myPage);
            // set the values of the streamer
            content.beginText();
            content.setFont(PDType1Font.TIMES_ROMAN, 12);
            content.setLeading(14.5f);
            content.newLineAtOffset(50, 700);
            // info to copy
            String[] evaluationInfo = {"Reporte de la evaluacion "+control.getEvaluation().getId(),
                    "Datos:",
                    "",
                    "   Tipo: " +control.getEvaluation().getType().toString(),
                    "   Ponderación: " +control.getEvaluation().getWeighing(),
                    "   Numero de intentos: " +control.getEvaluation().getTries(),
                    "   Estado: " +control.getEvaluation().getIsActive(),
                    "   Fecha de inicio: " +control.getEvaluation().getInitDate().toString(),
                    "   Fecha de cierre: " +control.getEvaluation().getCloseDate().toString(),
                    "   Número de preguntas: " +control.getEvaluation().getQuestions().size(),
                    "",
                    "   Resultados:",
                    "       Cantidad de alumnos que presentaron: "+control.getStudents().size(),
                    "       Cantidad de alumnos que pasaron: "+control.amountPassed()+" ("+(((float)control.amountPassed()/(float)control.getStudents().size())*100)+"%)",
                    "       Cantidad de alumnos que rasparon: "+(control.getStudents().size()-control.amountPassed())+" ("+((((float)control.getStudents().size()-(float)control.amountPassed())/(float)control.getStudents().size())*100)+"%)",
                    "",
                    "   Preguntas:"
            };
            // write all the lines
            for(String line : evaluationInfo) {
                content.showText(line);
                content.newLine();
            }
            int count = 1;
            for(Question element : control.getEvaluation().getQuestions()){
                content.showText("      "+count+") "+element.getStatement()+", puntaje: "+element.getScore());
                content.newLine();
                count++;
            }
            content.newLine();
            
            content.endText();
            content.close();
            // ahora para cada estudiante se escribira una pagina
            for(Student student : control.getStudents()) {
                try {
                    // create one page and add it to the document
                    PDPage newPage = new PDPage();
                    // add the page to the file
                    doc.addPage(newPage);
                    content = new PDPageContentStream(doc, newPage);
                    content.beginText();
                    content.setFont(PDType1Font.TIMES_ROMAN, 12);
                    content.setLeading(14.5f);
                    content.newLineAtOffset(50, 700);
            
                    String[] auxData = {
                            "Datos del estudiante:",
                            "",
                            "       Nombre: "+student.getNames(),
                            "       Apellido: "+student.getLastNames(),
                            "       Cédula: "+student.getCi(),
                            "       Género: "+student.getGender().toString(),
                            "       Nota: "+control.getStudentNote(student),
                            "       Opciones seleccionadas por el alumno:"
                    };
                    
                    // write all the lines
                    for(String line : auxData) {
                        content.showText(line);
                        content.newLine();
                    }
                    count = 1;
                    for(Option element : control.getStudentOptions(student.getCi())){
                        content.showText("              Pregunta "+count+") "+element.toString());
                        content.newLine();
                        count++;
                    }
                    
                    content.newLine();
                    
                    // end text
                    content.endText();
                    // close the stream
                    content.close();
                } catch (Exception e) {
                }
            }
            
            // save the file to reports package
            doc.save("src/proyecto/reports/reporte_"+control.getEvaluation().getId()+".pdf");
        } catch(IOException e) {
        }
    }
}
