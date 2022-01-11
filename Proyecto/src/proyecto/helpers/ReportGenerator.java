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
import proyecto.dataModel.users.Student;
/**
 *
 * @author Alejandro
 */
public class ReportGenerator {
    
    public static void generateEvaluationReport(String fileName, InfoEvaluationControl control) throws IOException {
        try {
            // delete the file if it exist
            File auxFileToDelete = new File("src/proyecto/reports/"+fileName+".pdf");
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
            // set the values to the file
            content.beginText();
            content.setFont(PDType1Font.TIMES_ROMAN, 12);
            content.setLeading(14.5f);
            
            content.newLineAtOffset(25, 700);
            String[] evaluationInfo = {"Reporte de la evaluacion "+control.getEvaluation().getId(),
                    "Datos de la evaluacion:",
                    "Ponderacion: " +control.getEvaluation().getWeighing(),
                    "Numero de intentos: " +control.getEvaluation().getTries(),
                    "Estado: " +control.getEvaluation().getIsActive(),
                    "Fecha de inicio: " +control.getEvaluation().getInitDate().toString(),
                    "Fecha de cierre: " +control.getEvaluation().getCloseDate().toString()
            };
            // write all the lines
            for(String line : evaluationInfo) {
                content.showText(line);
                content.newLine();
            }
            content.newLine();
            content.newLine();
            
            content.showText("Informacion alumnos:");
            content.newLine();
            content.newLine();
            
            for(Student student : control.getStudents()) {
                String informacionAlumnos = "Nombre: "+student.getNames()+" "
                        + "Apellido: "+student.getLastNames()+" "
                        + "Cedula: "+student.getCi()+" "
                        + "Nota: "+control.getStudentNote(student);
                // escribe la linea en el archivo
                content.showText(informacionAlumnos);
                content.newLine();
            }
            // ultima linea
            content.newLine();
            // cerrar el archivo
            content.endText();
            // close the streamer
            content.close();
            // save the file to reports package
            doc.save("src/proyecto/reports/"+fileName+".pdf");
        } catch(IOException e) {
        }
    }
}
