/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.dataModel.users.Student;

/**
 *
 * @author Alejandro
 */
public class InfoSubjectControl {
    // helper class
    public class InfoSubject {
        private int studentsNumber;
        private int evaluationsNumber;
        private float evaluationsPercentage;
        private int passStudents;
        private int notPassStudents;

        public InfoSubject(int studentsNumber, int evaluationsNumber, float evaluationsPercentage, int passStudents, int notPassStudents) {
            this.studentsNumber = studentsNumber;
            this.evaluationsNumber = evaluationsNumber;
            this.evaluationsPercentage = evaluationsPercentage;
            this.passStudents = passStudents;
            this.notPassStudents = notPassStudents;
        }

        public int getStudentsNumber() {
            return studentsNumber;
        }

        public void setStudentsNumber(int studentsNumber) {
            this.studentsNumber = studentsNumber;
        }

        public int getEvaluationsNumber() {
            return evaluationsNumber;
        }

        public void setEvaluationsNumber(int evaluationsNumber) {
            this.evaluationsNumber = evaluationsNumber;
        }

        public float getEvaluationsPorcentaje() {
            return evaluationsPercentage;
        }

        public void setEvaluationsPorcentaje(float evaluationsPercentage) {
            this.evaluationsPercentage = evaluationsPercentage;
        }

        public int getPassStudents() {
            return passStudents;
        }

        public void setPassStudents(int passStudents) {
            this.passStudents = passStudents;
        }

        public int getNotPassStudents() {
            return notPassStudents;
        }

        public void setNotPassStudents(int notPassStudents) {
            this.notPassStudents = notPassStudents;
        }
    }
    
    // helper class
    public class StudentSubjectInfo {
        private Student student;
        private int numEvaluationsPresented;
        private int numEvaluationsNotPresented;
        private int numEvaluationsPass;
        private int numEvaluationsNotPass;
        private float totalNote;
        private boolean subjectPassed;

        public StudentSubjectInfo(Student student, int numEvaluationsPresented, int numEvaluationsNotPresented, int numEvaluationsPass, int numEvaluationsNotPass, float totalNote, boolean subjectPassed) {
            this.student = student;
            this.numEvaluationsPresented = numEvaluationsPresented;
            this.numEvaluationsNotPresented = numEvaluationsNotPresented;
            this.numEvaluationsPass = numEvaluationsPass;
            this.numEvaluationsNotPass = numEvaluationsNotPass;
            this.totalNote = totalNote;
            this.subjectPassed = subjectPassed;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public int getNumEvaluationsPresented() {
            return numEvaluationsPresented;
        }

        public void setNumEvaluationsPresented(int numEvaluationsPresented) {
            this.numEvaluationsPresented = numEvaluationsPresented;
        }

        public int getNumEvaluationsNotPresented() {
            return numEvaluationsNotPresented;
        }

        public void setNumEvaluationsNotPresented(int numEvaluationsNotPresented) {
            this.numEvaluationsNotPresented = numEvaluationsNotPresented;
        }

        public int getNumEvaluationsPass() {
            return numEvaluationsPass;
        }

        public void setNumEvaluationsPass(int numEvaluationsPass) {
            this.numEvaluationsPass = numEvaluationsPass;
        }

        public int getNumEvaluationsNotPass() {
            return numEvaluationsNotPass;
        }

        public void setNumEvaluationsNotPass(int numEvaluationsNotPass) {
            this.numEvaluationsNotPass = numEvaluationsNotPass;
        }

        public float getTotalNote() {
            return totalNote;
        }

        public void setTotalNote(float totalNote) {
            this.totalNote = totalNote;
        }

        public boolean isSubjectPassed() {
            return subjectPassed;
        }

        public void setSubjectPassed(boolean subjectPassed) {
            this.subjectPassed = subjectPassed;
        }
    }
    
    //##########################################################################
    private Subject subject;
    private InfoSubject subjectInfo;
    private ArrayList<StudentSubjectInfo> studentsInfo;

    public InfoSubjectControl(ArrayList<Student> students, Subject subject, ArrayList<Evaluation> evaluations) {
        // set the subject
        this.subject = subject;
        // auxiliaries variables
        ArrayList<Student> enrolledStudents = new ArrayList<>();
        // get the enrolled subjects
        students.forEach(student -> {
            student.getEnrollments().forEach(enrollment -> {
                if(enrollment.getSubjectId().equals(subject.getId())) {
                    enrolledStudents.add(student);
                }
            });
        });
        // get the evaluation percentage
        float evaluationPercentage = 0;
        for(Evaluation evaluation : subject.getEvaluations()) {
            evaluationPercentage += evaluation.getWeighing();
        }
        // create the students info 
        this.studentsInfo = new ArrayList<>();
        // get the information for each student
        int passedStudents = 0;
        int notPassedStudents = 0;
        for(Student student : enrolledStudents) {
            float totalNote = 0;
            int numEvaluationsPresented = 0;
            int numEvaluationsNotPresented = 0;
            int numEvaluationsPass = 0;
            int numEvaluationsNotPass = 0;
            boolean subjectPassed = false;
            for(Evaluation eva : subject.getEvaluations()) {
                for(EvaluationRegistry evaReg : student.getEvaluationsRegistrys()) {
                    if(evaReg.getEvaluationId().equals(eva.getId()) && evaReg.getPresented()) {
                        totalNote += (evaReg.getNote()*(eva.getWeighing()/5))/20;
                        numEvaluationsPresented++;
                        if(Math.round(evaReg.getNote())>=10) {
                            numEvaluationsPass++;
                        } else {
                            numEvaluationsNotPass++;
                        }
                    } else if(evaReg.getEvaluationId().equals(eva.getId()) && !evaReg.getPresented()) {
                        numEvaluationsNotPresented++;
                    }
                }
            }
            if(Math.round(totalNote)>=10) {
                passedStudents++;
                subjectPassed = true;
            } else {
                notPassedStudents++;
            }
            // add the information to the list
            studentsInfo.add(
                    new StudentSubjectInfo(
                            student, 
                            numEvaluationsPresented, 
                            numEvaluationsNotPresented, 
                            numEvaluationsPass, 
                            numEvaluationsNotPass, 
                            totalNote,
                            subjectPassed
                    )
            );
        } 
        // create the subject info
        this.subjectInfo = new InfoSubject(
                enrolledStudents.size(), 
                subject.getEvaluations().size(), 
                evaluationPercentage, 
                passedStudents, 
                notPassedStudents
        );
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public InfoSubject getSubjectInfo() {
        return subjectInfo;
    }

    public void setSubjectInfo(InfoSubject subjectInfo) {
        this.subjectInfo = subjectInfo;
    }

    public ArrayList<StudentSubjectInfo> getStudentsInfo() {
        return studentsInfo;
    }

    public void setStudentsInfo(ArrayList<StudentSubjectInfo> studentsInfo) {
        this.studentsInfo = studentsInfo;
    }
}
