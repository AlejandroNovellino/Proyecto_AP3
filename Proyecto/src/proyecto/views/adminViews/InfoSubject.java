/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.adminViews;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import proyecto.controls.InfoSubjectControl;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.dataModel.users.Student;

/**
 *
 * @author Alejandro
 */
public class InfoSubject extends javax.swing.JFrame {
    private final int[] standarColorButtons = {103,69,128};
    private final int[] hoverColorButtons = {78, 36, 102};
    private InfoSubjectControl control = null;
    
    public InfoSubject(ArrayList<Student> students, Subject subject, ArrayList<Evaluation> evaluations) {
        initComponents();
        control = new InfoSubjectControl(students, subject, evaluations);
        setTableValues();
    }
    
    private void changeBackgroundColor(java.awt.event.MouseEvent evt, int R, int G, int B) {
        evt.getComponent().setBackground(new Color(R, G, B));
    }
    
    public void setTableValues() {
        // set subject table
        DefaultTableModel subjectInfoModel = (DefaultTableModel) subjectInfo.getModel();
        subjectInfoModel.addRow(new Object[]{
            control.getSubjectInfo().getStudentsNumber(),
            control.getSubjectInfo().getEvaluationsNumber(),
            control.getSubjectInfo().getEvaluationsPorcentaje(),
            control.getSubjectInfo().getPassStudents(),
            control.getSubjectInfo().getNotPassStudents()
        });
    
        // set students table
        DefaultTableModel studentsTableModel = (DefaultTableModel) studentsTable.getModel();
        control.getStudentsInfo().forEach((element) -> {
            studentsTableModel.addRow(new Object[]{
                element.getStudent().getNames(),
                element.getStudent().getLastNames(), 
                element.getStudent().getCi(),
                element.getNumEvaluationsPresented(),
                element.getNumEvaluationsNotPresented(),
                element.getNumEvaluationsPass(),
                element.getNumEvaluationsNotPass(),
                element.getTotalNote()
            });
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGenerateReport = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnExitToMainMenu = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subjectInfo = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detalles Materia");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(65, 10, 97));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGenerateReport.setBackground(new java.awt.Color(103, 69, 128));
        btnGenerateReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenerateReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenerateReportMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGenerateReportMousePressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Generar Reporte");

        javax.swing.GroupLayout btnGenerateReportLayout = new javax.swing.GroupLayout(btnGenerateReport);
        btnGenerateReport.setLayout(btnGenerateReportLayout);
        btnGenerateReportLayout.setHorizontalGroup(
            btnGenerateReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGenerateReportLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel13)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        btnGenerateReportLayout.setVerticalGroup(
            btnGenerateReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGenerateReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(btnGenerateReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, -1));

        btnExitToMainMenu.setBackground(new java.awt.Color(103, 69, 128));
        btnExitToMainMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitToMainMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitToMainMenuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExitToMainMenuMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Salir");

        javax.swing.GroupLayout btnExitToMainMenuLayout = new javax.swing.GroupLayout(btnExitToMainMenu);
        btnExitToMainMenu.setLayout(btnExitToMainMenuLayout);
        btnExitToMainMenuLayout.setHorizontalGroup(
            btnExitToMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExitToMainMenuLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel14)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        btnExitToMainMenuLayout.setVerticalGroup(
            btnExitToMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExitToMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(btnExitToMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 250, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EXAMPLE TEXT");

        studentsTable.setAutoCreateRowSorter(true);
        studentsTable.setBackground(new java.awt.Color(78, 36, 102));
        studentsTable.setForeground(new java.awt.Color(255, 255, 255));
        studentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Cedula", "Evaluaciones Presentadas", "Evaluaciones No Presentadas", "Evaluaciones Aprobadas", "Evaluaciones No Aprobadas", "Nota Final"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentsTable.setGridColor(new java.awt.Color(217, 171, 251));
        studentsTable.setSelectionBackground(new java.awt.Color(199, 147, 230));
        studentsTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        studentsTable.setShowVerticalLines(false);
        studentsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(studentsTable);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Estudiantes inscritos:");

        subjectInfo.setBackground(new java.awt.Color(78, 36, 102));
        subjectInfo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        subjectInfo.setForeground(new java.awt.Color(255, 255, 255));
        subjectInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. Inscritos", "Num. Evaluaciones", "Porcentaje en Evaluaciones", "Num. Aprobados", "Num. Reprobados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subjectInfo.setGridColor(new java.awt.Color(217, 171, 251));
        subjectInfo.setSelectionBackground(new java.awt.Color(199, 147, 230));
        subjectInfo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        subjectInfo.setShowVerticalLines(false);
        jScrollPane2.setViewportView(subjectInfo);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Detalles materia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(36, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(676, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerateReportMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnGenerateReportMouseEntered

    private void btnGenerateReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerateReportMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnGenerateReportMouseExited

    private void btnGenerateReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerateReportMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerateReportMousePressed

    private void btnExitToMainMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitToMainMenuMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnExitToMainMenuMouseEntered

    private void btnExitToMainMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitToMainMenuMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnExitToMainMenuMouseExited

    private void btnExitToMainMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitToMainMenuMousePressed
        AdminMain.getInstance().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnExitToMainMenuMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnExitToMainMenu;
    private javax.swing.JPanel btnGenerateReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable studentsTable;
    private javax.swing.JTable subjectInfo;
    // End of variables declaration//GEN-END:variables
}