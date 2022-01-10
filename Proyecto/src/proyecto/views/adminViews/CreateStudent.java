/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.adminViews;

import javax.swing.ComboBoxModel;
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
    private CreateStudentControl control;
    /**
     * Creates new form createStudent
     */
    private CreateStudent() {
        initComponents();
        // set the jframe icon
        JFramesHelper.setJFrameIcon(this);
        alertMessagePanel.setVisible(false);
        control = new CreateStudentControl(null);
        JFramesHelper.setModalSize(modal);
        updateLists();
    }
    
    private CreateStudent(Student student) {
        initComponents();
        alertMessagePanel.setVisible(false);
        // set all the elements with the ones from the student
        names.setText(student.getNames());
        lastNames.setText(student.getLastNames());
        ci.setText(Integer.toString(student.getCi()));
        setGenderSelected(student.getGender());
        setStatusSelected(student.getStatus());
        // set the other elements
        createBtn.setText("Modificar");
        control = new CreateStudentControl(student);
        JFramesHelper.setModalSize(modal);
        updateLists();
    }
    
    public static CreateStudent getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new CreateStudent();
        }
        return uniqueInstance;
    }
    
    public static CreateStudent getInstance(Student student) {
        if(uniqueInstance == null) {
            uniqueInstance = new CreateStudent(student);
        }
        return uniqueInstance;
    }
    
    public void killInstance() {
        uniqueInstance = null;
    }
    
    private void updateLists() {
        // update subjectsToSelect
        DefaultListModel<Subject> aux1 = new DefaultListModel<>();
        control.getNotViewedSubjects().forEach(element -> aux1.addElement(element));
        subjectsToSelect.setModel(aux1);
        
        // update subjectsSaw
        DefaultListModel<Subject> aux2 = new DefaultListModel<>();
        control.getViewedSubjects().forEach(element -> aux2.addElement(element));
        subjectsSaw.setModel(aux2);
        
        // update subjectCanBeView
        DefaultListModel<Subject> aux3 = new DefaultListModel<>();
        control.getNotViewedSubjects().forEach(element -> aux3.addElement(element));
        subjectCanBeView.setModel(aux3);
        
        // update subjectsToEnroll
        DefaultListModel<Subject> aux4 = new DefaultListModel<>();
        control.getEnrolledSubjects().forEach(element -> aux4.addElement(element));
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
    
    private void setGenderSelected(gender gender) {
        ComboBoxModel<String> aux = genderSelection.getModel();
        aux.setSelectedItem(gender.toString());
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
    
    private void setStatusSelected(boolean status) {
        ComboBoxModel<String> aux = statusSelected.getModel();
        if(status) {
            aux.setSelectedItem("Activo");
        } else {
            aux.setSelectedItem("Inactivo");
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
            if(control.getCurrentStudent()==null) {
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Cedula no es unica");
                return false;
            } else {
                if(control.getCurrentStudent().getCi() == Integer.parseInt(ci.getText())) {
                    
                } else {
                    JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Cedula no es unica");
                    return false;
                }
            }
            
        }
            
        JFramesHelper.setMessage(alertMessagePanel, alertMessage, false, "");
        return true;
    }
    
    private void createStudent() {
        if(allDataCorrect()) {
            try {
                control.createStudent(
                    names.getText(), 
                    lastNames.getText(), 
                    Integer.parseInt(ci.getText()), 
                    getGenderSelected(), 
                    getStatusSelected()
                );
                modal.setVisible(true);
            } catch (Exception e) {
            }
        }
    }
    
    private void updateStudent() {
        if(allDataCorrect()) {
            try {
                control.updateStudent(
                    names.getText(), 
                    lastNames.getText(), 
                    Integer.parseInt(ci.getText()), 
                    getGenderSelected(), 
                    getStatusSelected()
                );
                jLabel14.setVisible(false);
                jLabel12.setVisible(false);
                jLabel13.setVisible(false);
                jLabel11.setText("Estudiante modificado exitosamente");
                modal.setVisible(true);
            } catch (Exception e) {
            }
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

        modal = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
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
        createBtn = new javax.swing.JButton();
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

        modal.setTitle("Operacion Realizada");
        modal.setModal(true);
        modal.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(65, 10, 97));

        jPanel4.setBackground(new java.awt.Color(91, 47, 122));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Se ha creado el usuario exitosamente");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("El password es la CI ingresada.");

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("El usuario es el nombre y apellido que se ingreso juntos.");

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Por ejemplo. nombreApellido: PedroCasa");

        jButton2.setText("Continuar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 40, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(54, 54, 54))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout modalLayout = new javax.swing.GroupLayout(modal.getContentPane());
        modal.getContentPane().setLayout(modalLayout);
        modalLayout.setHorizontalGroup(
            modalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modalLayout.setVerticalGroup(
            modalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Datos Estudiante");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(65, 10, 97));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(65, 10, 97));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Datos Estudiante");

        jPanel2.setBackground(new java.awt.Color(91, 47, 122));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 19, -1, -1));

        names.setBackground(new java.awt.Color(245, 222, 255));
        names.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jPanel2.add(names, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 17, 300, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 58, -1, -1));

        lastNames.setBackground(new java.awt.Color(245, 222, 255));
        lastNames.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jPanel2.add(lastNames, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 55, 300, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cedula");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Genero");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 59, -1, -1));

        ci.setBackground(new java.awt.Color(245, 222, 255));
        ci.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jPanel2.add(ci, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 17, 300, -1));

        genderSelection.setBackground(new java.awt.Color(245, 222, 255));
        genderSelection.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        genderSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino", "Otro" }));
        jPanel2.add(genderSelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 56, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Status");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 59, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Materias a inscribir");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 264, -1, -1));

        subjectsToSelect.setBackground(new java.awt.Color(245, 222, 255));
        subjectsToSelect.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        subjectsToSelect.setSelectionBackground(new java.awt.Color(65, 10, 97));
        subjectsToSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subjectsToSelectMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(subjectsToSelect);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 116, 360, -1));

        statusSelected.setBackground(new java.awt.Color(245, 222, 255));
        statusSelected.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        statusSelected.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jPanel2.add(statusSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(644, 56, 136, -1));

        alertMessagePanel.setBackground(new java.awt.Color(217, 171, 251));
        alertMessagePanel.setToolTipText("");
        alertMessagePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alertMessage.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        alertMessage.setText("EXAMPLE TEXT");
        alertMessagePanel.add(alertMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 20));

        jPanel2.add(alertMessagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 438, 750, 30));

        createBtn.setBackground(new java.awt.Color(65, 10, 97));
        createBtn.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        createBtn.setText("Crear");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });
        jPanel2.add(createBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 486, 171, -1));

        cancel.setBackground(new java.awt.Color(65, 10, 97));
        cancel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel2.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 486, 164, -1));

        subjectCanBeView.setBackground(new java.awt.Color(245, 222, 255));
        subjectCanBeView.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        subjectCanBeView.setSelectionBackground(new java.awt.Color(65, 10, 97));
        subjectCanBeView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subjectCanBeViewMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(subjectCanBeView);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 285, 356, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Materias que ya ha visto el estudiante");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 95, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Elecciones");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 95, -1, -1));

        subjectsSaw.setBackground(new java.awt.Color(245, 222, 255));
        subjectsSaw.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        subjectsSaw.setSelectionBackground(new java.awt.Color(65, 10, 97));
        subjectsSaw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subjectsSawMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(subjectsSaw);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 116, 356, -1));

        subjectsToEnroll.setBackground(new java.awt.Color(245, 222, 255));
        subjectsToEnroll.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        subjectsToEnroll.setSelectionBackground(new java.awt.Color(65, 10, 97));
        subjectsToEnroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subjectsToEnrollMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(subjectsToEnroll);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 290, 356, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Elecciones");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 264, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
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
        uniqueInstance = null;
    }//GEN-LAST:event_cancelActionPerformed

    private void subjectsToSelectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectsToSelectMousePressed
        try {
            control.addToViewedSubjects(subjectsToSelect.getSelectedValue());
            control.removeFromNotViewedSubjects(subjectsToSelect.getSelectedValue().getId());
            updateLists();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_subjectsToSelectMousePressed

    private void subjectsSawMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectsSawMousePressed
        try {
            control.addToNotViewedSubjects(subjectsSaw.getSelectedValue());
            control.removeFromViewedSubjects(subjectsSaw.getSelectedValue().getId());
            updateLists();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_subjectsSawMousePressed

    private void subjectCanBeViewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectCanBeViewMousePressed
        try {
            control.addToEnrolledSubjects(subjectCanBeView.getSelectedValue());
            control.removeFromNotViewedSubjects(subjectCanBeView.getSelectedValue().getId());
            updateLists();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_subjectCanBeViewMousePressed

    private void subjectsToEnrollMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectsToEnrollMousePressed
        try {
            control.addToNotViewedSubjects(subjectsToEnroll.getSelectedValue());
            control.removeFromEnrolledSubjects(subjectsToEnroll.getSelectedValue().getId());
            updateLists();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_subjectsToEnrollMousePressed

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        if(control.getCurrentStudent() == null) {
            createStudent();
        } else {
            updateStudent();
        } 
    }//GEN-LAST:event_createBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdminMain.getInstance().setVisible(true);
        modal.setVisible(false);
        uniqueInstance.setVisible(false);
        killInstance();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertMessage;
    private javax.swing.JPanel alertMessagePanel;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField ci;
    private javax.swing.JButton createBtn;
    private javax.swing.JComboBox<String> genderSelection;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField lastNames;
    private javax.swing.JDialog modal;
    private javax.swing.JTextField names;
    private javax.swing.JComboBox<String> statusSelected;
    private javax.swing.JList<Subject> subjectCanBeView;
    private javax.swing.JList<Subject> subjectsSaw;
    private javax.swing.JList<Subject> subjectsToEnroll;
    private javax.swing.JList<Subject> subjectsToSelect;
    // End of variables declaration//GEN-END:variables
}
