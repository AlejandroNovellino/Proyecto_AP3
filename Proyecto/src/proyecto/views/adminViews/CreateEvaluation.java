/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.adminViews;

import java.util.Date;
import javax.swing.DefaultListModel;
import proyecto.controls.CreateEvaluationControl;
import proyecto.dataModel.enums.evaluationType;
import proyecto.helpers.DataCheck;
import proyecto.helpers.JFramesHelper;

/**
 *
 * @author Alejandro
 */
public class CreateEvaluation extends javax.swing.JFrame {
    private static CreateEvaluation uniqueInstance = null;
    private CreateEvaluationControl control;
    /**
     * Creates new form CreateEvaluation
     */
    private CreateEvaluation() {
        initComponents();
        control = new CreateEvaluationControl();
        alertMessagePanel.setVisible(false);
    }
    
    public static CreateEvaluation getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new CreateEvaluation();
        }
        return uniqueInstance;
    }
    
    public CreateEvaluationControl getControl() {
        return control;
    }
    
    private void killInstance() {
        uniqueInstance = null;
    }
    
    private evaluationType getTypeSelected() {
        switch(type.getSelectedItem().toString()) {
            case "Quiz":
                return evaluationType.Quiz;
            case "Parcial Practico":
                return evaluationType.Practico;
            case "Parcial Prueba":
                return evaluationType.Prueba;
            case "Parcial Teorico":
                return evaluationType.Teorico;
            default:
                return null;       
        }
    }
    
    private boolean getStatusSelected() {
        switch(status.getSelectedItem().toString()) {
            case "Activa":
                return true;
            case "Inactiva":
                return false;
            default:
                return false;       
        }
    }
    
    private boolean allDataCorrect() {
        Date auxDate = new Date();
        auxDate.setHours(0);
        auxDate.setMinutes(0);
        auxDate.setSeconds(0);
        if(!DataCheck.validInt(weigh.getValue().toString()) || (Integer)weigh.getValue()<1) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Ponderacion no valida");
            return false;
        } else if(!DataCheck.validInt(tries.getValue().toString()) || (Integer)tries.getValue()<1) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Numero de intentos invalido");
            return false;
        } else if(control.getQuestions().size()<1) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Tienen que existir preguntas");
            return false;
        } else if(control.getTotalWeighInQuestions()<20) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "No se llega al total de 20 puntos en las pregutnas");
            return false;
        } else if(control.getTotalWeighInQuestions()>20) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se sobrepaso el total de 20 puntos en las pregutnas");
            return false;
        } else if(initDate.getDate() == null) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Fecha de inicio nula");
            return false;
        } else if(closeDate.getDate() == null) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Fecha de cierre nula");
            return false;
        } else if(initDate.getDate().after(closeDate.getDate())) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Fecha de cierre menor a la de inicio");
            return false;
        } else if(initDate.getDate().before(auxDate)) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Fecha de inicio menor a la actual");
            return false;
        } else if(closeDate.getDate().before(auxDate)) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Fecha de cierre menor a la actual");
            return false;
        }
        
        JFramesHelper.setMessage(alertMessagePanel, alertMessage, false, "");
        return true;
    }
    
    private void createEvaluation() {
        if(getTypeSelected() == evaluationType.Prueba) {
            tries.setValue(10000);
        }
        if(allDataCorrect()) {
            int auxTries = Integer.parseInt(tries.getValue().toString());
            control.createEvaluation(
                    getTypeSelected(), 
                    Float.parseFloat(weigh.getValue().toString()), 
                    initDate.getDate(), 
                    closeDate.getDate(), 
                    getStatusSelected(), 
                    auxTries
            );
            //modal.setVisible(true);
            AdminMain.getInstance().setVisible(true);
            uniqueInstance.setVisible(false);
            killInstance();
        }
    }
    
    public void updateList() {
        DefaultListModel<String> aux = new DefaultListModel<>();
        control.getQuestions().forEach(element -> aux.addElement(element.toString()));
        questionsJList.setModel(aux);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        initDate = new com.toedter.calendar.JDateChooser();
        closeDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tries = new javax.swing.JSpinner();
        weigh = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        create = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        questionsJList = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        createQuestion = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        alertMessagePanel = new javax.swing.JPanel();
        alertMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(65, 10, 97));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Datos evaluacion");

        jPanel2.setBackground(new java.awt.Color(103, 69, 128));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tipo");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 24, -1, -1));

        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quiz", "Parcial Practico", "Parcial Prueba", "Parcial Teorico" }));
        type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                typeMousePressed(evt);
            }
        });
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        jPanel2.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 19, 91, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ponderacion");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 21, -1, -1));
        jPanel2.add(initDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 57, 145, -1));
        jPanel2.add(closeDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 57, 177, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Estado");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 21, -1, -1));

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activa", "Inactiva" }));
        jPanel2.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 19, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Intentos");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 21, -1, -1));
        jPanel2.add(tries, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 19, 46, -1));
        jPanel2.add(weigh, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 19, 46, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha inicio");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha cierre");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 57, -1, -1));

        create.setBackground(new java.awt.Color(65, 10, 97));
        create.setText("Crear");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        jPanel2.add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 359, -1, -1));

        cancel.setBackground(new java.awt.Color(65, 10, 97));
        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel2.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 359, -1, -1));

        questionsJList.setSelectionBackground(new java.awt.Color(199, 147, 230));
        questionsJList.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(questionsJList);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 196, 535, 145));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Preguntas");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        createQuestion.setText("Crear pregunta");
        createQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createQuestionActionPerformed(evt);
            }
        });
        jPanel2.add(createQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 167, -1, -1));

        jButton2.setText("Borrar Pregunta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 167, -1, -1));

        alertMessagePanel.setBackground(new java.awt.Color(217, 171, 251));

        alertMessage.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertMessage.setText("jLabel9");

        javax.swing.GroupLayout alertMessagePanelLayout = new javax.swing.GroupLayout(alertMessagePanel);
        alertMessagePanel.setLayout(alertMessagePanelLayout);
        alertMessagePanelLayout.setHorizontalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(alertMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        alertMessagePanelLayout.setVerticalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(alertMessage)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.add(alertMessagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 535, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        AdminMain.getInstance().setVisible(true);
        uniqueInstance.setVisible(false);
        killInstance();
    }//GEN-LAST:event_cancelActionPerformed

    private void createQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createQuestionActionPerformed
        CreateQuestion.getInstance(control).setVisible(true);
        uniqueInstance.setVisible(false);
    }//GEN-LAST:event_createQuestionActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        createEvaluation();
    }//GEN-LAST:event_createActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            int index = questionsJList.getSelectedIndex();
            control.removeFromQuestionsByIndex(index);
            updateList();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void typeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeMousePressed
        
    }//GEN-LAST:event_typeMousePressed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        if(getTypeSelected() == evaluationType.Prueba){
            jLabel5.setVisible(false);
            tries.setVisible(false);
        } else {
            jLabel5.setVisible(true);
            tries.setVisible(true);
        }
    }//GEN-LAST:event_typeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertMessage;
    private javax.swing.JPanel alertMessagePanel;
    private javax.swing.JButton cancel;
    private com.toedter.calendar.JDateChooser closeDate;
    private javax.swing.JButton create;
    private javax.swing.JButton createQuestion;
    private com.toedter.calendar.JDateChooser initDate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> questionsJList;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JSpinner tries;
    private javax.swing.JComboBox<String> type;
    private javax.swing.JSpinner weigh;
    // End of variables declaration//GEN-END:variables
}
