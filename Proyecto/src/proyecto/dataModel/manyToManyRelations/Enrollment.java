/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.manyToManyRelations;

/**
 *
 * @author Alejandro
 */
public class Enrollment {
    private String id;
    private String subjectId;
    private String studentId;
    private Integer note;

    public Enrollment() {
    }

    public Enrollment(String id, String subjectId, String studentId, Integer note) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }
    
    
}
