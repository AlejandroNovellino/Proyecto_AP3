/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.manyToManyRelations;

import java.io.Serializable;

/**
 *
 * @author Alejandro
 */
public class Enrollment implements Serializable{
    private String id;
    private String subjectId;
    private String studentId;
    private Integer note;
    private Boolean passed; // se vio la materia y se paso

    public Enrollment() {
    }

    public Enrollment(String id, String subjectId, String studentId, Integer note, Boolean passed) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.note = note;
        this.passed = passed;
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

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    @Override
    public String toString() {
        return "Enrollment{" + "id=" + id + ", subjectId=" + subjectId + ", studentId=" + studentId + ", note=" + note + '}';
    }
}
