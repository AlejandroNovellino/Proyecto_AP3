/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.studentViews;

import java.awt.Color;
import java.io.IOException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyecto.controls.StudentMainControl;
import proyecto.dataModel.enums.evaluationType;
import proyecto.dataModel.users.Student;
import proyecto.helpers.JFramesHelper;
import proyecto.helpers.ReportGenerator;
import proyecto.views.Login;

/**
 *
 * @author Alejandro
 */
public class StudentMain extends javax.swing.JFrame {
    private static StudentMain uniqueInstance = null;
    private final int[] standarColorButtons = {103,69,128};
    private final int[] hoverColorButtons = {78, 36, 102};
    private StudentMainControl control = null;
    private PresentEvaluation presentEvaluation = null;
    
    /**
     * Creates new form StudentMain
     */
    private StudentMain(Student student) {
        initComponents();
        // set the jframe icon
        JFramesHelper.setJFrameIcon(this);
        // set the modals icon
        // big modal
        JFramesHelper.setJDialogIcon(confirmPresentation);
        JFramesHelper.setBigModalSize(confirmPresentation);
        JFramesHelper.setJDialogIcon(confirmDownloadActivity);
        JFramesHelper.setModalSize(confirmDownloadActivity);
        JFramesHelper.setJDialogIcon(confirmDownloadCertifiedNotes);
        JFramesHelper.setModalSize(confirmDownloadCertifiedNotes);
        // set the other values
        this.control = control;
        alertMessagePanel.setVisible(false);
        control = new StudentMainControl(student);
        // set the align place to the tables
        JFramesHelper.alignTextJTableCetner(registeredSubjects, JFramesHelper.swingConstantToCenter());
        JFramesHelper.alignTextJTableCetner(registeredEvaluations, JFramesHelper.swingConstantToCenter());
        JFramesHelper.alignTextJTableCetner(passedSubjects, JFramesHelper.swingConstantToCenter());
        JFramesHelper.alignTextJTableCetner(posibleToRegisterEvaluations, JFramesHelper.swingConstantToCenter());
        setTablesValues();
    }
    
    public static StudentMain getInstance() {
        return uniqueInstance;
    }
    
    public static StudentMain getInstance(Student student) {
        if(uniqueInstance == null) {
            uniqueInstance = new StudentMain(student);
        }
        return uniqueInstance;
    }
    
    public void killInstance() {
        uniqueInstance = null;
    }
    
    public void killPresentEvaluationInstace() {
        presentEvaluation = null;
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
    
    public void setTablesValues() {
        // clear all the tables
        clearTableValues(registeredSubjects);
        clearTableValues(registeredEvaluations);
        clearTableValues(passedSubjects);
        clearTableValues(posibleToRegisterEvaluations);
        // set subjects table
        DefaultTableModel subjectTable = (DefaultTableModel) registeredSubjects.getModel();
        control.getSubjectsInfo().forEach((element) -> {
            if(!element.getEnrollment().getPassed()) {
                subjectTable.addRow(new Object[]{
                    element.getSubject().getName(),
                    element.getSubject().getCode(),
                    element.getEnrollment().getNote()
                });
            }
        });
        // set passed subjects table
        DefaultTableModel passedSubjectTable = (DefaultTableModel) passedSubjects.getModel();
        control.getSubjectsInfo().forEach((element) -> {
            if(element.getEnrollment().getPassed()) {
                passedSubjectTable.addRow(new Object[]{
                    element.getSubject().getName(),
                    element.getSubject().getCode(),
                    element.getEnrollment().getNote()
                });
            }
        });
        // set posible to register evaluations table
        DefaultTableModel posibleToRegisterEvaluationsTable = (DefaultTableModel) posibleToRegisterEvaluations.getModel();
        control.getEvaluationsPosibleToRegister().forEach(element -> {
            String numTries;
            if(element.getType() == evaluationType.Prueba) {
                numTries = "Elimitados";
            } else {
                numTries = Integer.toString(element.getTries());
            }
            posibleToRegisterEvaluationsTable.addRow(new Object[]{
                element.getType().toString(),
                control.getSubjectByRegistryId(
                        element.getId()
                ).getName(),
                element.getWeighing(),
                element.getInitDate().toString(),
                element.getCloseDate().toString(),
                numTries
            });
        });
        // set registered evaluations table
        DefaultTableModel evaluationTable = (DefaultTableModel) registeredEvaluations.getModel();
        control.getEvaluationsInfo().forEach((element) -> {
            String numTries;
            if(element.getEvaluation().getType() == evaluationType.Prueba) {
                numTries = "Elimitados";
            } else {
                numTries = Integer.toString(element.getEvaluation().getTries());
            }
            evaluationTable.addRow(new Object[]{
                element.getEvaluation().getType().toString(),
                control.getSubjectByRegistryId(
                        element.getEvaluation().getId()
                ).getName(),
                element.getEvaluation().getWeighing(),
                element.getEvaluation().getInitDate().toString(),
                element.getEvaluation().getCloseDate().toString(),
                numTries,
                element.getEvaReg().getNumTries(),
                element.getEvaReg().getNote()
            });
        });
    }
    
    private void registerEvaluation() {
        try {
            int index = posibleToRegisterEvaluations.getSelectedRow();
            control.setSelectedProssibleToEnrollEvaluationByIndex(index);
            control.createEvaluationRegistry();
            setTablesValues();
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, false, "");
        } catch (Exception e) {
            System.out.println(e);
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una evaluacion");
        }
    }
    
    private void deleteRegistryEvaluation() {
        try {
            int index = registeredEvaluations.getSelectedRow();
            control.setSelectedEvaluationByIndex(index);
            if(control.getEvaluationRegistryForEvaluation(control.getSelectedEvaluation()).getPresented()) {
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Evaluacion ya presentada");
            } else {
                control.deleteEvaluationRegistry();
                setTablesValues();
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, false, "");
            }
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una evaluacion");
        }
    }
    
    private boolean checkIfEvaluationCanBePresented() {
        try {
            int index = registeredEvaluations.getSelectedRow();
            control.setSelectedEvaluationByIndex(index);
            if(control.getEvaluationsInfo().get(index).getEvaluation().getTries() == control.getEvaluationsInfo().get(index).getEvaReg().getNumTries()) {
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Evaluacion ya se presento el limite de veces");
                return false;
            } else if(new Date().after(control.getEvaluationsInfo().get(index).getEvaluation().getCloseDate())) {
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "La evaluacion ha culminado");
                return false;
            } else if(new Date().before(control.getEvaluationsInfo().get(index).getEvaluation().getInitDate())) {
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "La evaluacion no ha comenzado");
                return false;
            }
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una evaluacion registrada");
            return false;
        }
        return true;
    }
    
    private void presentEvaluation() {
        try {
            if(checkIfEvaluationCanBePresented()) { 
                confirmPresentation.setVisible(true);
            }
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una evaluacion inscrita");
        }
    }
    
    private void generateActivityReport() {
        try {
            ReportGenerator.generateActivityReport(control);
            confirmDownloadActivity.setVisible(true);
        } catch (IOException e) {
            System.out.println("No se pudo generar el reporte");
        }
    }
    
    private void generateCertifiesNotesReport() {
        try {
            ReportGenerator.generateCertifiedNotesReport(control);
            confirmDownloadActivity.setVisible(true);
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

        confirmPresentation = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cancel = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        confirmDownloadActivity = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        btnConfirm1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        confirmDownloadCertifiedNotes = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        btnConfirm2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        leftSidePanel = new javax.swing.JPanel();
        btnRegisterEvaluation = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDeleteEvaluationRegister = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnPresentEvaluation = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnCertifieNotes = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnReporteActividad = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        topPanel = new javax.swing.JPanel();
        exit = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        alertMessagePanel = new javax.swing.JPanel();
        alertMessage = new javax.swing.JLabel();
        mainTabbedPane = new javax.swing.JTabbedPane();
        subjectsPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        registeredSubjects = new javax.swing.JTable();
        subjectsPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        passedSubjects = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        posibleToRegisterEvaluations = new javax.swing.JTable();
        evaluationsPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        registeredEvaluations = new javax.swing.JTable();

        confirmPresentation.setTitle("ATENCIÓN ");
        confirmPresentation.setAlwaysOnTop(true);
        confirmPresentation.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        confirmPresentation.setModal(true);
        confirmPresentation.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(65, 10, 97));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ATENCIÓN ");

        jPanel4.setBackground(new java.awt.Color(103, 69, 128));

        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        confirm.setText("Aceptar");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(103, 69, 128));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Una vez se haga click en aceptar se dará inicio a la evaluación. \n\nEsto eliminara uno de sus intentos. \n\nEn ningún momento se puede cancelar la presentación, es decir, se debe finalizar la evaluación en su totalidad o parcial, ya que se puede finalizar la presentación en cualquier momento y se evaluara en base a lo respondido. \n\nLas preguntas se evaluaran una a la vez y una vez respondida no se puede volver a la misma.\n\nUna vez finalizada la presentación se dará su nota.\n\n¡Suerte!\n");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jTextArea1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(cancel)
                .addGap(101, 101, 101)
                .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm)
                    .addComponent(cancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout confirmPresentationLayout = new javax.swing.GroupLayout(confirmPresentation.getContentPane());
        confirmPresentation.getContentPane().setLayout(confirmPresentationLayout);
        confirmPresentationLayout.setHorizontalGroup(
            confirmPresentationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmPresentationLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        confirmPresentationLayout.setVerticalGroup(
            confirmPresentationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmPresentationLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        confirmDownloadActivity.setTitle("Confirmar");
        confirmDownloadActivity.setModal(true);
        confirmDownloadActivity.setResizable(false);

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
        jTextArea2.setText("El reporte se encuentra en paquete/carpeta proyecto.reporte bajo el nombre de \"reporte_estudiante_NombreApellido\".");
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setSelectionColor(new java.awt.Color(217, 171, 251));
        jScrollPane6.setViewportView(jTextArea2);

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
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout confirmDownloadActivityLayout = new javax.swing.GroupLayout(confirmDownloadActivity.getContentPane());
        confirmDownloadActivity.getContentPane().setLayout(confirmDownloadActivityLayout);
        confirmDownloadActivityLayout.setHorizontalGroup(
            confirmDownloadActivityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        confirmDownloadActivityLayout.setVerticalGroup(
            confirmDownloadActivityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        confirmDownloadCertifiedNotes.setTitle("Confirmar");
        confirmDownloadCertifiedNotes.setModal(true);
        confirmDownloadCertifiedNotes.setResizable(false);

        jPanel7.setBackground(new java.awt.Color(65, 10, 97));

        jPanel8.setBackground(new java.awt.Color(103, 69, 128));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Reporte generado:");

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(103, 69, 128));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTextArea3.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText("El reporte se encuentra en paquete/carpeta proyecto.reporte bajo el nombre de \"notas_certificadas_NombreApellido\".");
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextArea3.setSelectionColor(new java.awt.Color(217, 171, 251));
        jScrollPane7.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnConfirm2.setBackground(new java.awt.Color(103, 69, 128));
        btnConfirm2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirm2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirm2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnConfirm2MousePressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-next-page-24.png"))); // NOI18N
        jLabel17.setText("Continuar");

        javax.swing.GroupLayout btnConfirm2Layout = new javax.swing.GroupLayout(btnConfirm2);
        btnConfirm2.setLayout(btnConfirm2Layout);
        btnConfirm2Layout.setHorizontalGroup(
            btnConfirm2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConfirm2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel17)
                .addGap(50, 50, 50))
        );
        btnConfirm2Layout.setVerticalGroup(
            btnConfirm2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(btnConfirm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout confirmDownloadCertifiedNotesLayout = new javax.swing.GroupLayout(confirmDownloadCertifiedNotes.getContentPane());
        confirmDownloadCertifiedNotes.getContentPane().setLayout(confirmDownloadCertifiedNotesLayout);
        confirmDownloadCertifiedNotesLayout.setHorizontalGroup(
            confirmDownloadCertifiedNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        confirmDownloadCertifiedNotesLayout.setVerticalGroup(
            confirmDownloadCertifiedNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio Estudiante");
        setBackground(new java.awt.Color(65, 10, 97));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(65, 10, 97));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leftSidePanel.setBackground(new java.awt.Color(65, 10, 97));
        leftSidePanel.setRequestFocusEnabled(false);
        leftSidePanel.setVerifyInputWhenFocusTarget(false);
        leftSidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegisterEvaluation.setBackground(new java.awt.Color(103, 69, 128));
        btnRegisterEvaluation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegisterEvaluationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegisterEvaluationMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegisterEvaluationMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-subscription-24.png"))); // NOI18N
        jLabel1.setText("Registrar Evaluación");

        javax.swing.GroupLayout btnRegisterEvaluationLayout = new javax.swing.GroupLayout(btnRegisterEvaluation);
        btnRegisterEvaluation.setLayout(btnRegisterEvaluationLayout);
        btnRegisterEvaluationLayout.setHorizontalGroup(
            btnRegisterEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRegisterEvaluationLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        btnRegisterEvaluationLayout.setVerticalGroup(
            btnRegisterEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnRegisterEvaluation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 40));

        btnDeleteEvaluationRegister.setBackground(new java.awt.Color(103, 69, 128));
        btnDeleteEvaluationRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteEvaluationRegisterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteEvaluationRegisterMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteEvaluationRegisterMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-delete-24.png"))); // NOI18N
        jLabel2.setText("Eliminar Registro");

        javax.swing.GroupLayout btnDeleteEvaluationRegisterLayout = new javax.swing.GroupLayout(btnDeleteEvaluationRegister);
        btnDeleteEvaluationRegister.setLayout(btnDeleteEvaluationRegisterLayout);
        btnDeleteEvaluationRegisterLayout.setHorizontalGroup(
            btnDeleteEvaluationRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDeleteEvaluationRegisterLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(30, 30, 30))
        );
        btnDeleteEvaluationRegisterLayout.setVerticalGroup(
            btnDeleteEvaluationRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnDeleteEvaluationRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 40));

        btnPresentEvaluation.setBackground(new java.awt.Color(103, 69, 128));
        btnPresentEvaluation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPresentEvaluationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPresentEvaluationMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPresentEvaluationMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-present-to-all-24.png"))); // NOI18N
        jLabel4.setText("Presentar Evaluación");

        javax.swing.GroupLayout btnPresentEvaluationLayout = new javax.swing.GroupLayout(btnPresentEvaluation);
        btnPresentEvaluation.setLayout(btnPresentEvaluationLayout);
        btnPresentEvaluationLayout.setHorizontalGroup(
            btnPresentEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPresentEvaluationLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        btnPresentEvaluationLayout.setVerticalGroup(
            btnPresentEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnPresentEvaluation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 210, 40));

        jLabel12.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-tableau-software-48.png"))); // NOI18N
        jLabel12.setText("AP Análisis ");
        leftSidePanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        btnCertifieNotes.setBackground(new java.awt.Color(103, 69, 128));
        btnCertifieNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCertifieNotesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCertifieNotesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCertifieNotesMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-download-graph-report-24.png"))); // NOI18N
        jLabel5.setText("Notas Certificadas");

        javax.swing.GroupLayout btnCertifieNotesLayout = new javax.swing.GroupLayout(btnCertifieNotes);
        btnCertifieNotes.setLayout(btnCertifieNotesLayout);
        btnCertifieNotesLayout.setHorizontalGroup(
            btnCertifieNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnCertifieNotesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        btnCertifieNotesLayout.setVerticalGroup(
            btnCertifieNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnCertifieNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 40));

        btnReporteActividad.setBackground(new java.awt.Color(103, 69, 128));
        btnReporteActividad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReporteActividadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporteActividadMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReporteActividadMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-download-graph-report-24.png"))); // NOI18N
        jLabel6.setText("Reporte Actividad");

        javax.swing.GroupLayout btnReporteActividadLayout = new javax.swing.GroupLayout(btnReporteActividad);
        btnReporteActividad.setLayout(btnReporteActividadLayout);
        btnReporteActividadLayout.setHorizontalGroup(
            btnReporteActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnReporteActividadLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addContainerGap())
        );
        btnReporteActividadLayout.setVerticalGroup(
            btnReporteActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnReporteActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 210, 40));

        jPanel1.add(leftSidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 530));

        topPanel.setBackground(new java.awt.Color(65, 10, 97));

        exit.setBackground(new java.awt.Color(103, 69, 128));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-logout-24.png"))); // NOI18N
        jLabel13.setText("Salir");

        javax.swing.GroupLayout exitLayout = new javax.swing.GroupLayout(exit);
        exit.setLayout(exitLayout);
        exitLayout.setHorizontalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel13)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        exitLayout.setVerticalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        alertMessagePanel.setBackground(new java.awt.Color(217, 171, 251));

        alertMessage.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertMessage.setText("EXAMPLE TEXT");

        javax.swing.GroupLayout alertMessagePanelLayout = new javax.swing.GroupLayout(alertMessagePanel);
        alertMessagePanel.setLayout(alertMessagePanelLayout);
        alertMessagePanelLayout.setHorizontalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap(456, Short.MAX_VALUE))
        );
        alertMessagePanelLayout.setVerticalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addComponent(alertMessagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alertMessagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 820, 60));

        mainTabbedPane.setBackground(new java.awt.Color(65, 10, 97));
        mainTabbedPane.setToolTipText("");

        subjectsPanel.setBackground(new java.awt.Color(91, 47, 122));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lista de materias registradas");

        registeredSubjects.setAutoCreateRowSorter(true);
        registeredSubjects.setBackground(new java.awt.Color(78, 36, 102));
        registeredSubjects.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        registeredSubjects.setForeground(new java.awt.Color(255, 255, 255));
        registeredSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Código", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        registeredSubjects.setGridColor(new java.awt.Color(217, 171, 251));
        registeredSubjects.setSelectionBackground(new java.awt.Color(199, 147, 230));
        registeredSubjects.setSelectionForeground(new java.awt.Color(0, 0, 0));
        registeredSubjects.setShowVerticalLines(false);
        jScrollPane2.setViewportView(registeredSubjects);

        javax.swing.GroupLayout subjectsPanelLayout = new javax.swing.GroupLayout(subjectsPanel);
        subjectsPanel.setLayout(subjectsPanelLayout);
        subjectsPanelLayout.setHorizontalGroup(
            subjectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subjectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(subjectsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 547, Short.MAX_VALUE)))
                .addContainerGap())
        );
        subjectsPanelLayout.setVerticalGroup(
            subjectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Materias Cursando", subjectsPanel);

        subjectsPanel1.setBackground(new java.awt.Color(91, 47, 122));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lista de materias registradas");

        passedSubjects.setAutoCreateRowSorter(true);
        passedSubjects.setBackground(new java.awt.Color(78, 36, 102));
        passedSubjects.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        passedSubjects.setForeground(new java.awt.Color(255, 255, 255));
        passedSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Código", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        passedSubjects.setGridColor(new java.awt.Color(217, 171, 251));
        passedSubjects.setSelectionBackground(new java.awt.Color(199, 147, 230));
        passedSubjects.setSelectionForeground(new java.awt.Color(0, 0, 0));
        passedSubjects.setShowVerticalLines(false);
        jScrollPane4.setViewportView(passedSubjects);

        javax.swing.GroupLayout subjectsPanel1Layout = new javax.swing.GroupLayout(subjectsPanel1);
        subjectsPanel1.setLayout(subjectsPanel1Layout);
        subjectsPanel1Layout.setHorizontalGroup(
            subjectsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subjectsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(subjectsPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 547, Short.MAX_VALUE)))
                .addContainerGap())
        );
        subjectsPanel1Layout.setVerticalGroup(
            subjectsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Materias Vistas", subjectsPanel1);

        jPanel2.setBackground(new java.awt.Color(91, 47, 122));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Lista de evaluaciones posibles para registrar");

        jScrollPane5.setBackground(new java.awt.Color(1, 1, 1));

        posibleToRegisterEvaluations.setAutoCreateRowSorter(true);
        posibleToRegisterEvaluations.setBackground(new java.awt.Color(78, 36, 102));
        posibleToRegisterEvaluations.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        posibleToRegisterEvaluations.setForeground(new java.awt.Color(255, 255, 255));
        posibleToRegisterEvaluations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Materia", "Ponderación", "Fecha inicio", "Fecha cierre", "Num. Intentos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        posibleToRegisterEvaluations.setGridColor(new java.awt.Color(217, 171, 251));
        posibleToRegisterEvaluations.setSelectionBackground(new java.awt.Color(199, 147, 230));
        posibleToRegisterEvaluations.setSelectionForeground(new java.awt.Color(0, 0, 0));
        posibleToRegisterEvaluations.setShowVerticalLines(false);
        jScrollPane5.setViewportView(posibleToRegisterEvaluations);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Evaluaciones a Registrar", jPanel2);

        evaluationsPanel.setBackground(new java.awt.Color(91, 47, 122));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Lista de evaluaciones registradas");

        registeredEvaluations.setAutoCreateRowSorter(true);
        registeredEvaluations.setBackground(new java.awt.Color(78, 36, 102));
        registeredEvaluations.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        registeredEvaluations.setForeground(new java.awt.Color(255, 255, 255));
        registeredEvaluations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Materia", "Ponderación", "Fecha inicio", "Fecha cierre", "Num. Intentos", "Num. Realizada", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
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
        registeredEvaluations.setGridColor(new java.awt.Color(217, 171, 251));
        registeredEvaluations.setSelectionBackground(new java.awt.Color(199, 147, 230));
        registeredEvaluations.setSelectionForeground(new java.awt.Color(0, 0, 0));
        registeredEvaluations.setShowVerticalLines(false);
        jScrollPane3.setViewportView(registeredEvaluations);

        javax.swing.GroupLayout evaluationsPanelLayout = new javax.swing.GroupLayout(evaluationsPanel);
        evaluationsPanel.setLayout(evaluationsPanelLayout);
        evaluationsPanelLayout.setHorizontalGroup(
            evaluationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evaluationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(evaluationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(evaluationsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 513, Short.MAX_VALUE)))
                .addContainerGap())
        );
        evaluationsPanelLayout.setVerticalGroup(
            evaluationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evaluationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Evaluaciones Registradas", evaluationsPanel);

        jPanel1.add(mainTabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 810, 470));

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

    private void btnRegisterEvaluationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterEvaluationMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnRegisterEvaluationMouseEntered

    private void btnRegisterEvaluationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterEvaluationMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnRegisterEvaluationMouseExited

    private void btnRegisterEvaluationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterEvaluationMousePressed
        registerEvaluation();
    }//GEN-LAST:event_btnRegisterEvaluationMousePressed

    private void btnDeleteEvaluationRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteEvaluationRegisterMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnDeleteEvaluationRegisterMouseEntered

    private void btnDeleteEvaluationRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteEvaluationRegisterMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnDeleteEvaluationRegisterMouseExited

    private void btnDeleteEvaluationRegisterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteEvaluationRegisterMousePressed
        deleteRegistryEvaluation();
    }//GEN-LAST:event_btnDeleteEvaluationRegisterMousePressed

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_exitMouseExited

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        Login.getInstance().setVisible(true);
        uniqueInstance.setVisible(false);
        killInstance();
    }//GEN-LAST:event_exitMousePressed

    private void btnPresentEvaluationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPresentEvaluationMousePressed
        presentEvaluation();
    }//GEN-LAST:event_btnPresentEvaluationMousePressed

    private void btnPresentEvaluationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPresentEvaluationMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnPresentEvaluationMouseExited

    private void btnPresentEvaluationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPresentEvaluationMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnPresentEvaluationMouseEntered

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        confirmPresentation.setVisible(false);
    }//GEN-LAST:event_cancelActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        presentEvaluation = new PresentEvaluation(
                control.getCurrentStudent(), 
                control.getSelectedEvaluation(), 
                control.getEvaluationRegistryForEvaluation(control.getSelectedEvaluation())
        );
        confirmPresentation.setVisible(false);
        uniqueInstance.setVisible(false);
        JFramesHelper.setMessage(alertMessagePanel, alertMessage, false, "");
    }//GEN-LAST:event_confirmActionPerformed

    private void btnCertifieNotesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCertifieNotesMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnCertifieNotesMouseEntered

    private void btnCertifieNotesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCertifieNotesMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnCertifieNotesMouseExited

    private void btnCertifieNotesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCertifieNotesMousePressed
        generateCertifiesNotesReport();
    }//GEN-LAST:event_btnCertifieNotesMousePressed

    private void btnConfirm1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm1MouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnConfirm1MouseEntered

    private void btnConfirm1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm1MouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnConfirm1MouseExited

    private void btnConfirm1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm1MousePressed
        confirmDownloadActivity.setVisible(false);
    }//GEN-LAST:event_btnConfirm1MousePressed

    private void btnReporteActividadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteActividadMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnReporteActividadMouseEntered

    private void btnReporteActividadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteActividadMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnReporteActividadMouseExited

    private void btnReporteActividadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteActividadMousePressed
        generateActivityReport();
    }//GEN-LAST:event_btnReporteActividadMousePressed

    private void btnConfirm2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm2MouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnConfirm2MouseEntered

    private void btnConfirm2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm2MouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnConfirm2MouseExited

    private void btnConfirm2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirm2MousePressed
        confirmDownloadCertifiedNotes.setVisible(false);
    }//GEN-LAST:event_btnConfirm2MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertMessage;
    private javax.swing.JPanel alertMessagePanel;
    private javax.swing.JPanel btnCertifieNotes;
    private javax.swing.JPanel btnConfirm1;
    private javax.swing.JPanel btnConfirm2;
    private javax.swing.JPanel btnDeleteEvaluationRegister;
    private javax.swing.JPanel btnPresentEvaluation;
    private javax.swing.JPanel btnRegisterEvaluation;
    private javax.swing.JPanel btnReporteActividad;
    private javax.swing.JButton cancel;
    private javax.swing.JButton confirm;
    private javax.swing.JDialog confirmDownloadActivity;
    private javax.swing.JDialog confirmDownloadCertifiedNotes;
    private javax.swing.JDialog confirmPresentation;
    private javax.swing.JPanel evaluationsPanel;
    private javax.swing.JPanel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JPanel leftSidePanel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JTable passedSubjects;
    private javax.swing.JTable posibleToRegisterEvaluations;
    private javax.swing.JTable registeredEvaluations;
    private javax.swing.JTable registeredSubjects;
    private javax.swing.JPanel subjectsPanel;
    private javax.swing.JPanel subjectsPanel1;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
