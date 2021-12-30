/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.adminViews;

import javax.swing.DefaultListModel;
import proyecto.controls.CreateStudentControl;
import proyecto.dataModel.enums.gender;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.dataModel.users.Student;
import proyecto.helpers.DataCheck;
import proyecto.helpers.JFramesHelper;

/**
 *
 * @author Alejandro
 */
public class CreateStudent extends javax.swing.JFrame {
    private static CreateStudent uniqueInstance = null;
    private boolean modifyStudent = false;  
    /**
     * Creates new form createStudent
     */
    public CreateStudent() {
        initComponents();
        alertMessagePanel.setVisible(false);
        if(modifyStudent) {
            CreateStudentControl.getInstance(null);
        } else {
            CreateStudentControl.getInstance(null);
        }
        updateLists();
    }
    
    public static CreateStudent getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new CreateStudent();
        }
        return uniqueInstance;
    }
    
    private void updateLists() {
        // update subjectsToSelect
        DefaultListModel<Subject> aux1 = new DefaultListModel<>();
        CreateStudentControl.getInstance().getNotViewedSubjects().forEach(element -> aux1.addElement(element));
        subjectsToSelect.setModel(aux1);
        
        // update subjectsSaw
        DefaultListModel<Subject> aux2 = new DefaultListModel<>();
        CreateStudentControl.getInstance().getViewedSubjects().forEach(element -> aux2.addElement(element));
        subjectsSaw.setModel(aux2);
        
        // update subjectCanBeView
        DefaultListModel<Subject> aux3 = new DefaultListModel<>();
        CreateStudentControl.getInstance().getPosibleToEnrollSubjects().forEach(element -> aux3.addElement(element));
        subjectCanBeView.setModel(aux3);
        
        // update subjectsToEnroll
        DefaultListModel<Subject> aux4 = new DefaultListModel<>();
        CreateStudentControl.getInstance().getEnrolledSubjects().forEach(element -> aux4.addElement(element));
        subjectsToEnroll.setModel(aux4);
    }
    
    private gender getGenderSelected() {
        switch(genderSelection.getSelectedItem().toString()) {
            case "Femenino":
                return gender.Femenino;
            case "Masculino":
                return gender.Masculino;
            case "Otro":
                return gender.Otro;
            default:
                return null;       
        }
    }
    
    private boolean getStatusSelected() {
        switch(statusSelected.getSelectedItem().toString()) {
            case "Activo":
                return true;
            case "Inactivo":
                return false;
            default:
                return false;       
        }
    }
    
    private boolean allDataCorrect() {
        if(!DataCheck.validString(names.getText())) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Nombre no puede contener numeros ni caracteres especiales");
            return false;
        } else if (!DataCheck.validString(lastNames.getText())){
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Apellidos no puede contener numeros ni caracteres especiales");
            return false;
        } else if(!DataCheck.validInt(ci.getText())) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Cedula solo puede ser digitos");
            return false;
        } else if(!DataCheck.uniqueCi(Integer.parseInt(ci.getText()))) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Cedula no es unica");
            return false;
        }
            
        JFramesHelper.setMessage(alertMessagePanel, alertMessage, false, "");
        return true;
    }
    
    private void createStudent() {
        if(allDataCorrect()) {
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        names = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastNames = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ci = new javax.swing.JTextField();
        genderSelection = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        subjectsToSelect = new javax.swing.JList<>();
        statusSelected = new javax.swing.JComboBox<>();
        alertMessagePanel = new javax.swing.JPanel();
        alertMessage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        subjectCanBeView = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        subjectsSaw = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        subjectsToEnroll = new javax.swing.JList<>();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Datos Estudiante");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(65, 10, 97));

        jPanel1.setBackground(new java.awt.Color(65, 10, 97));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Datos Estudiante");

        jPanel2.setBackground(new java.awt.Color(91, 47, 122));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres");

        names.setBackground(new java.awt.Color(245, 222, 255));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos");

        lastNames.setBackground(new java.awt.Color(245, 222, 255));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cedula");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Genero");

        ci.setBackground(new java.awt.Color(245, 222, 255));

        genderSelection.setBackground(new java.awt.Color(245, 222, 255));
        genderSelection.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        genderSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino", "Otro" }));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Status");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Materias a inscribir (se muestran las que cumplen las prelaciones)");

        subjectsToSelect.setBackground(new java.awt.Color(245, 222, 255));
        subjectsToSelect.setSelectionBackground(new java.awt.Color(65, 10, 97));
        subjectsToSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subjectsToSelectMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(subjectsToSelect);

        statusSelected.setBackground(new java.awt.Color(245, 222, 255));
        statusSelected.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        statusSelected.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        alertMessagePanel.setBackground(new java.awt.Color(217, 171, 251));
        alertMessagePanel.setToolTipText("");
        alertMessagePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alertMessage.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        alertMessage.setText("ALERT");
        alertMessagePanel.add(alertMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jButton1.setBackground(new java.awt.Color(217, 171, 251));
        jButton1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton1.setText("Crear");

        cancel.setBackground(new java.awt.Color(217, 171, 251));
        cancel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        subjectCanBeView.setBackground(new java.awt.Color(245, 222, 255));
        subjectCanBeView.setSelectionBackground(new java.awt.Color(65, 10, 97));
        jScrollPane2.setViewportView(subjectCanBeView);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Materias que ya ha visto el estudiante");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Elecciones");

        subjectsSaw.setBackground(new java.awt.Color(245, 222, 255));
        subjectsSaw.setSelectionBackground(new java.awt.Color(65, 10, 97));
        subjectsSaw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subjectsSawMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(subjectsSaw);

        subjectsToEnroll.setBackground(new java.awt.Color(245, 222, 255));
        subjectsToEnroll.setSelectionBackground(new java.awt.Color(65, 10, 97));
        jScrollPane4.setViewportView(subjectsToEnroll);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Elecciones");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alertMessagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lastNames, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    .addComponent(names)))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(genderSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(statusSelected, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(ci, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(names, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genderSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(statusSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(alertMessagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(cancel))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        AdminMain.getInstance().setVisible(true);
        uniqueInstance.setVisible(false);
        uniqueInstance = null;
        CreateStudentControl.killUniqueInstance();
    }//GEN-LAST:event_cancelActionPerformed

    private void subjectsToSelectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectsToSelectMousePressed
        try {
            CreateStudentControl.getInstance().addToViewedSubjects(subjectsToSelect.getSelectedValue());
            CreateStudentControl.getInstance().removeFromNotViewedSubjects(subjectsToSelect.getSelectedValue().getId());
            CreateStudentControl.getInstance().updateLists();
            updateLists();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_subjectsToSelectMousePressed

    private void subjectsSawMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectsSawMousePressed
        try {
            CreateStudentControl.getInstance().addToNotViewedSubjects(subjectsSaw.getSelectedValue());
            CreateStudentControl.getInstance().removeFromViewedSubjects(subjectsSaw.getSelectedValue().getId());
            CreateStudentControl.getInstance().updateLists();
            updateLists();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_subjectsSawMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertMessage;
    private javax.swing.JPanel alertMessagePanel;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField ci;
    private javax.swing.JComboBox<String> genderSelection;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField lastNames;
    private javax.swing.JTextField names;
    private javax.swing.JComboBox<String> statusSelected;
    private javax.swing.JList<Subject> subjectCanBeView;
    private javax.swing.JList<Subject> subjectsSaw;
    private javax.swing.JList<Subject> subjectsToEnroll;
    private javax.swing.JList<Subject> subjectsToSelect;
    // End of variables declaration//GEN-END:variables
}