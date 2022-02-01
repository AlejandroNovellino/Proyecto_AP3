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
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import proyecto.controls.InfoEvaluationControl;
import proyecto.controls.InfoSubjectControl;
import proyecto.controls.StudentMainControl;
import proyecto.controls.StudentMainControl.EvaluationInfo;
import proyecto.controls.StudentMainControl.SubjectInfo;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.Option;
import proyecto.dataModel.evaluationRelated.Question;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
import proyecto.dataModel.users.Student;
/**
 *
 * @author Alejandro
 */
public class ReportGenerator {
    
    public static void generateEvaluationReport(InfoEvaluationControl control) throws IOException {
        try {
            // delete the file if it exist
            File auxFileToDelete = new File("src/proyecto/reports/reporte_evaluacion_"+control.getEvaluation().getId()+".pdf");
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
            String[] evaluationInfo = {"Reporte de la evaluación "+control.getEvaluation().getId(),
                    "",
                    "Datos:",
                    "",
                    "   Tipo: " +control.getEvaluation().getType().toString(),
                    "   Ponderación: " +control.getEvaluation().getWeighing(),
                    "   Número de intentos: " +control.getEvaluation().getTries(),
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
            // for each student write a page
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
            doc.save("src/proyecto/reports/reporte_evaluacion_"+control.getEvaluation().getId()+".pdf");
        } catch(IOException e) {
        }
    }
    
    public static void generateSubjectReport(InfoSubjectControl control) throws IOException {
        try {
            // delete the file if it exist
            File auxFileToDelete = new File("src/proyecto/reports/reporte_materia_"+control.getSubject().getName()+".pdf");
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
            String[] evaluationInfo = {"Reporte de la materia "+control.getSubject().getName(),
                    "",
                    "Datos:",
                    "",
                    "   ID: " +control.getSubject().getId(),
                    "   Código: " +control.getSubject().getCode(),
                    "   Cantidad de evaluaciones: " +control.getSubjectInfo().getEvaluationsNumber(),
                    "   Cantidad de inscritos: " +control.getSubjectInfo().getStudentsNumber(),
                    "   Cantidad de estudiantes que aprobaron la materia: " +control.getSubjectInfo().getPassStudents(),
                    "   Cantidad de estudiantes que no han aprobaron la materia: " +control.getSubjectInfo().getNotPassStudents(),
                    "",
                    "   Evaluaciones:"
            };
            // write all the lines
            for(String line : evaluationInfo) {
                content.showText(line);
                content.newLine();
            }
            // write the evalautions info
            int count = 1;
            for(Evaluation element : control.getSubject().getEvaluations()){
                content.showText("      "+count+") "+element.getType()+", porcentaje: "+element.getWeighing());
                content.newLine();
                content.showText("        "+"fecha inicio: "+element.getInitDate()+", fecha cierre: "+element.getInitDate()+",");
                content.newLine();
                String state = (element.getIsActive()) ? "Activa" : "Inactiva";
                content.showText("        "+"número de intentos: "+element.getTries()+", estado: "+state+".");
                content.newLine();
                count++;
            }
            content.newLine();
            
            content.endText();
            content.close();
            // for each student write a page
            for(InfoSubjectControl.StudentSubjectInfo student : control.getStudentsInfo()) {
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
                            "       Nombre: "+student.getStudent().getNames(),
                            "       Apellido: "+student.getStudent().getLastNames(),
                            "       Cédula: "+student.getStudent().getCi(),
                            "       Género: "+student.getStudent().getGender().toString(),
                            "       Nota total: "+student.getTotalNote(),
                            "       Cantidad de evaluaciones presentadas: "+student.getNumEvaluationsPresented(),
                            "       Cantidad de evaluaciones no presentadas: "+student.getNumEvaluationsNotPresented(),
                            "       Cantidad de evaluaciones aprobadas: "+student.getNumEvaluationsPass(),
                            "       Cantidad de evaluaciones no aprobadas: "+student.getNumEvaluationsNotPass(),
                    };
                    
                    // write all the lines
                    for(String line : auxData) {
                        content.showText(line);
                        content.newLine();
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
            doc.save("src/proyecto/reports/reporte_materia_"+control.getSubject().getName()+".pdf");
        } catch(IOException e) {
        }
    }
    
    public static void generateActivityReport(StudentMainControl control) throws IOException {
        try {
            // delete the file if it exist
            File auxFileToDelete = new File("src/proyecto/reports/reporte_estudiante_"+control.getCurrentStudent().getNames()+""+control.getCurrentStudent().getLastNames()+".pdf");
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
            String[] evaluationInfo = {"Reporte actividad del alumno:",
                    "",
                    "Datos del estudiante:",
                    "",
                    "       Nombre: "+control.getCurrentStudent().getNames(),
                    "       Apellido: "+control.getCurrentStudent().getLastNames(),
                    "       Cédula: "+control.getCurrentStudent().getCi(),
                    "       Género: "+control.getCurrentStudent().getGender().toString(),
                    "       Nombre de usuario: "+control.getCurrentStudent().getUserName(),
                    "       Cantidad de materias inscritas: "+control.getCurrentStudent().getEnrollments().size(),
                    "       Cantidad de evaluaciones registradas: "+control.getCurrentStudent().getEvaluationsRegistrys().size()
            };
            
            // write all the lines
            for(String line : evaluationInfo) {
                content.showText(line);
                content.newLine();
            }
            content.newLine();
            
            content.endText();
            content.close();
            
            // create the new page
            // create one page and add it to the document
            PDPage newPage = new PDPage();
            // add the page to the file
            doc.addPage(newPage);
            content = new PDPageContentStream(doc, newPage);
            content.beginText();
            content.setFont(PDType1Font.TIMES_ROMAN, 12);
            content.setLeading(14.5f);
            content.newLineAtOffset(50, 700);
            
            // write the header
            content.showText("Informacion de materias inscritas:");
            content.newLine();
            
            // write the information about the subjects
            int count = 1;
            for(SubjectInfo element : control.getSubjectsInfo()) {
                String state = (element.getEnrollment().getPassed()) ? "Aprobada" : "No aprobada";
                String[] auxData = {
                        "   "+count+") Materia: "+element.getSubject().getName(),
                        "     Código: "+element.getSubject().getCode(),
                        "     Nota en la materia: "+element.getEnrollment().getNote(),
                        "     Estado de la materia: "+state
                };

                // write all the lines
                for(String line : auxData) {
                    content.showText(line);
                    content.newLine();
                }
                content.newLine();
                count++;
            }
            
            // end text
            content.endText();
            // close the stream
            content.close();
            
            // for each evaluation presented
            for(EvaluationInfo evaluation : control.getEvaluationsInfo()) {
                try {
                    // create one page and add it to the document
                    newPage = new PDPage();
                    // add the page to the file
                    doc.addPage(newPage);
                    content = new PDPageContentStream(doc, newPage);
                    content.beginText();
                    content.setFont(PDType1Font.TIMES_ROMAN, 12);
                    content.setLeading(14.5f);
                    content.newLineAtOffset(50, 700);
            
                    String[] auxData = {
                            "Resultados de las evaluaciones presentadas:",
                            "",
                            "       Nombre: "+evaluation.getEvaluation().getType(),
                            "       Ponderación: "+evaluation.getEvaluation().getWeighing(),
                            "       Nota: "+evaluation.getEvaReg().getNote(),
                            "       Cantidad de intentos utilizados: "+evaluation.getEvaReg().getNumTries(),
                            "",
                            "       Opciones seleccionadas:"
                    };
                    
                    // write all the lines
                    for(String line : auxData) {
                        content.showText(line);
                        content.newLine();
                    }
                    content.newLine();
                    // write the options selected
                    count = 1;
                    for(Option line : evaluation.getEvaReg().getAnswers()) {
                        content.showText("          "+count+") "+line.getValue());
                        content.newLine();
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
            doc.save("src/proyecto/reports/reporte_estudiante_"+control.getCurrentStudent().getNames()+""+control.getCurrentStudent().getLastNames()+".pdf");
        } catch(IOException e) {
        }
    }
    
    public static void generateCertifiedNotesReport(StudentMainControl control) throws IOException {
        try {
            // delete the file if it exist
            File auxFileToDelete = new File("src/proyecto/reports/notas_certificadas_"+control.getCurrentStudent().getNames()+""+control.getCurrentStudent().getLastNames()+".pdf");
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
            // institution icon
            PDImageXObject image = PDImageXObject.createFromFile("src/proyecto/views/icons/icons8-tableau-software-24.png", doc);
            // create the stream to set the text
            PDPageContentStream content = new PDPageContentStream(doc, myPage);
            // drawing the image
            content.drawImage(image, 50, 700);
            // init to write
            // set the values of the streamer
            content.beginText();
            content.setFont(PDType1Font.TIMES_ROMAN, 12);
            content.setLeading(14.5f);
            content.newLineAtOffset(50, 700);
            // write the title
            String titleOffset = "                                                                  ";
            content.showText(titleOffset+"Notas certificadas");
            // set the offset
            //content.newLineAtOffset(50, 700);
            content.newLine();
            content.newLine();
            content.newLine();
            content.newLine();
            // offset
            String textOffset = "                                               ";
            String textSeparator = " ------------------------------- ";
            // info to copy
            String[] info = {
                    textOffset+"Nombre del estudiante: "+control.getCurrentStudent().getNames()+" "+control.getCurrentStudent().getLastNames(),
                    textOffset+"Cédula: "+control.getCurrentStudent().getCi(),
                    "",
                    textOffset+"                    Materias vistas:",
                    "",
                    textOffset+"Materia                                            Nota"
            };
            
            // write all the lines
            for(String line : info) {
                content.showText(line);
                content.newLine();
            }
            content.newLine();
            // write all the subjects
            for(SubjectInfo subjectInfo : control.getSubjectsInfo()) {
                content.showText(
                        textOffset+
                        subjectInfo.getSubject().getName()+
                        textSeparator+
                        subjectInfo.getEnrollment().getNote()
                );
                content.newLine();
            }
            // end text and close
            content.endText();
            content.close();
            
            // save the file to reports package
            doc.save("src/proyecto/reports/notas_certificadas_"+control.getCurrentStudent().getNames()+""+control.getCurrentStudent().getLastNames()+".pdf");
        } catch(IOException e) {
        }
    }
}
