/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.adminViews;

import java.awt.Color;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyecto.controls.InfoEvaluationControl;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.helpers.JFramesHelper;
import proyecto.helpers.ReportGenerator;

/**
 *
 * @author Alejandro
 */
public class InfoEvaluation extends javax.swing.JFrame {
    private final int[] standarColorButtons = {103,69,128};
    private final int[] hoverColorButtons = {78, 36, 102};
    private InfoEvaluationControl control = null;
    
    public InfoEvaluation(Evaluation evaluation) {
        initComponents();
        // set the jframes icon
        JFramesHelper.setJFrameIcon(this);
        JFramesHelper.setJDialogIcon(confirm);
        JFramesHelper.setModalSize(confirm);
        // set the other values
        control = new InfoEvaluationControl(evaluation);
        presented.setText("Presentaron: "+control.getStudents().size());
        passed.setText("Pasaron: "+control.amountPassed());
        notPassed.setText("Rasparon: "+(control.getStudents().size()-control.amountPassed()));
        evaluationName.setText("Detalles Evaluacion: "+control.getEvaluation().getType().toString());
        // set the align place to the table
        JFramesHelper.alignTextJTableCetner(studentsTable, JFramesHelper.swingConstantToCenter());
        // set the table values
        setTableValues();
    }
    
    private void changeBackgroundColor(java.awt.event.MouseEvent evt, int R, int G, int B) {
        evt.getComponent().setBackground(new Color(R, G, B));
    }
    
    public void clearTableValues(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int total = model.getRowCount();
        for(int i=0; i<total; i++) {
            model.removeRow(0);
        }
    }
    
    public void setTableValues() {
        // clear all the tables
        clearTableValues(studentsTable);
        // set students table
        DefaultTableModel studentTable = (DefaultTableModel) studentsTable.getModel();
        control.getStudents().forEach((element) -> {
            studentTable.addRow(new Object[]{
                element.getNames(),
                element.getLastNames(), 
                element.getCi(),
                control.getStudentNote(element)
            });
        });
    }
    
    private void updateList(int ci) {
        DefaultListModel<String> aux1 = new DefaultListModel<>();
        control.getStudentOptions(ci).forEach(element -> aux1.addElement(element.toString()));
        studentAnswers.setModel(aux1);
    }
    
    private void generateReport() {
        try {
            ReportGenerator.generateEvaluationReport(control);
            confirm.setVisible(true);
        } catch (IOException e) {
            System.out.println("No se pudo generar el reporte");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirm = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        btnConfirm1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGenerateReport = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnExitToMainMenu = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        evaluationName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentAnswers = new javax.swing.JList<>();
        presented = new javax.swing.JLabel();
        notPassed = new javax.swing.JLabel();
        passed = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        confirm.setTitle("Confirmar");
        confirm.setModal(true);
        confirm.setResizable(false);

        jPanel5.setBackground(new java.awt.Color(65, 10, 97));

        jPanel6.setBackground(new java.awt.Color(103, 69, 128));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Reporte generado:");

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(103, 69, 128));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("El reporte se encuetnra en paquete/carpeta proyecto.reporte bajo el nombre de \"reporte_idDeLaEvaluacion\".");
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setSelectionColor(new java.awt.Color(217, 171, 251));
        jScrollPane4.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnConfirm1.setBackground(new java.awt.Color(103, 69, 128));
        btnConfirm1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirm1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirm1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnConfirm1MousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-next-page-24.png"))); // NOI18N
        jLabel16.setText("Continuar");

        javax.swing.GroupLayout btnConfirm1Layout = new javax.swing.GroupLayout(btnConfirm1);
        btnConfirm1.setLayout(btnConfirm1Layout);
        btnConfirm1Layout.setHorizontalGroup(
            btnConfirm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConfirm1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel16)
                .addGap(50, 50, 50))
        );
        btnConfirm1Layout.setVerticalGroup(
            btnConfirm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(btnConfirm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout confirmLayout = new javax.swing.GroupLayout(confirm.getContentPane());
        confirm.getContentPane().setLayout(confirmLayout);
        confirmLayout.setHorizontalGroup(
            confirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        confirmLayout.setVerticalGroup(
            confirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detalles Evaluacion");
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
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-download-graph-report-24.png"))); // NOI18N
        jLabel13.setText("Generar Reporte");

        javax.swing.GroupLayout btnGenerateReportLayout = new javax.swing.GroupLayout(btnGenerateReport);
        btnGenerateReport.setLayout(btnGenerateReportLayout);
        btnGenerateReportLayout.setHorizontalGroup(
            btnGenerateReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGenerateReportLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel13)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        btnGenerateReportLayout.setVerticalGroup(
            btnGenerateReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
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
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-logout-24.png"))); // NOI18N
        jLabel14.setText("Salir");

        javax.swing.GroupLayout btnExitToMainMenuLayout = new javax.swing.GroupLayout(btnExitToMainMenu);
        btnExitToMainMenu.setLayout(btnExitToMainMenuLayout);
        btnExitToMainMenuLayout.setHorizontalGroup(
            btnExitToMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExitToMainMenuLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel14)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        btnExitToMainMenuLayout.setVerticalGroup(
            btnExitToMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(btnExitToMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 250, -1));

        evaluationName.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        evaluationName.setForeground(new java.awt.Color(255, 255, 255));
        evaluationName.setText("EXAMPLE TEXT");

        studentsTable.setBackground(new java.awt.Color(78, 36, 102));
        studentsTable.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        studentsTable.setForeground(new java.awt.Color(255, 255, 255));
        studentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Cédula", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentsTable.setSelectionBackground(new java.awt.Color(199, 147, 230));
        studentsTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        studentsTable.setShowVerticalLines(false);
        studentsTable.getTableHeader().setReorderingAllowed(false);
        studentsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                studentsTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(studentsTable);

        studentAnswers.setBackground(new java.awt.Color(103, 69, 128));
        studentAnswers.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        studentAnswers.setForeground(new java.awt.Color(255, 255, 255));
        studentAnswers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        studentAnswers.setToolTipText("");
        studentAnswers.setSelectionBackground(new java.awt.Color(199, 147, 230));
        studentAnswers.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(studentAnswers);

        presented.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        presented.setForeground(new java.awt.Color(255, 255, 255));
        presented.setText("EXAMPLE TEXT");

        notPassed.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        notPassed.setForeground(new java.awt.Color(255, 255, 255));
        notPassed.setText("EXAMPLE TEXT");

        passed.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        passed.setForeground(new java.awt.Color(255, 255, 255));
        passed.setText("EXAMPLE TEXT");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Estudiantes inscritos:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(evaluationName)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(presented)
                                        .addGap(30, 30, 30)
                                        .addComponent(passed)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(notPassed)))
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(evaluationName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presented)
                            .addComponent(notPassed)
                            .addComponent(passed))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
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
        generateReport();
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

    private void studentsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentsTableMousePressed
        int rowSelected = studentsTable.getSelectedRow();
        updateList((Integer)studentsTable.getValueAt(rowSelected, 2));
    }//GEN-LAST:event_studentsTableMousePressed

    private void btnConfirm1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm1MouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnConfirm1MouseEntered

    private void btnConfirm1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm1MouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnConfirm1MouseExited

    private void btnConfirm1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm1MousePressed
        confirm.setVisible(false);
    }//GEN-LAST:event_btnConfirm1MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnConfirm1;
    private javax.swing.JPanel btnExitToMainMenu;
    private javax.swing.JPanel btnGenerateReport;
    private javax.swing.JDialog confirm;
    private javax.swing.JLabel evaluationName;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel notPassed;
    private javax.swing.JLabel passed;
    private javax.swing.JLabel presented;
    private javax.swing.JList<String> studentAnswers;
    private javax.swing.JTable studentsTable;
    // End of variables declaration//GEN-END:variables
}
