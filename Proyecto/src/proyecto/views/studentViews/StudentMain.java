/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.studentViews;

import java.awt.Color;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyecto.controls.StudentMainControl;
import proyecto.dataModel.users.Student;
import proyecto.helpers.JFramesHelper;
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
    
    /**
     * Creates new form StudentMain
     */
    private StudentMain(Student student) {
        initComponents();
        alertMessagePanel.setVisible(false);
        control = new StudentMainControl(student); 
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
        // set evaluations table
        DefaultTableModel evaluationTable = (DefaultTableModel) registeredEvaluations.getModel();
        control.getEvaluationsInfo().forEach((element) -> {
            evaluationTable.addRow(new Object[]{
                element.getEvaluation().getType().toString(),
                element.getEvaluation().getWeighing(),
                element.getEvaluation().getInitDate().toString(),
                element.getEvaluation().getCloseDate().toString(),
                element.getEvaluation().getTries(),
                element.getEvaReg().getNumTries(),
                element.getEvaReg().getNote()
            });
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
            posibleToRegisterEvaluationsTable.addRow(new Object[]{
                element.getType().toString(),
                element.getWeighing(),
                element.getInitDate().toString(),
                element.getCloseDate().toString(),
                element.getTries()
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
    
    private void presentEvaluation() {
        try {
            int index = registeredEvaluations.getSelectedRow();
            control.setSelectedEvaluationByIndex(index);
            if(control.getEvaluationsInfo().get(index).getEvaluation().getTries() == control.getEvaluationsInfo().get(index).getEvaReg().getNumTries()) {
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Evaluacion ya se presento el limite de veces");
            } else if(new Date().after(control.getEvaluationsInfo().get(index).getEvaluation().getCloseDate())) {
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "La evaluacion ha culminado");
            } else {
                control.deleteEvaluationRegistry();
                setTablesValues();
                JFramesHelper.setMessage(alertMessagePanel, alertMessage, false, "");
            }
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una evaluacion");
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        leftSidePanel = new javax.swing.JPanel();
        btnRegisterEvaluation = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDeleteEvaluationRegister = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnPresentEvaluation = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
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

        jPanel3.setBackground(new java.awt.Color(65, 10, 97));

        jLabel7.setText("jLabel7");

        jButton1.setText("Cancelar");

        jButton2.setText("Aceptar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addGap(43, 43, 43)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(379, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(42, 42, 42)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout confirmPresentationLayout = new javax.swing.GroupLayout(confirmPresentation.getContentPane());
        confirmPresentation.getContentPane().setLayout(confirmPresentationLayout);
        confirmPresentationLayout.setHorizontalGroup(
            confirmPresentationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        confirmPresentationLayout.setVerticalGroup(
            confirmPresentationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jLabel1.setText("Registrar Evaluacion");

        javax.swing.GroupLayout btnRegisterEvaluationLayout = new javax.swing.GroupLayout(btnRegisterEvaluation);
        btnRegisterEvaluation.setLayout(btnRegisterEvaluationLayout);
        btnRegisterEvaluationLayout.setHorizontalGroup(
            btnRegisterEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRegisterEvaluationLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        btnRegisterEvaluationLayout.setVerticalGroup(
            btnRegisterEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRegisterEvaluationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        leftSidePanel.add(btnRegisterEvaluation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 190, 40));

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
        jLabel2.setText("Eliminar Registro");

        javax.swing.GroupLayout btnDeleteEvaluationRegisterLayout = new javax.swing.GroupLayout(btnDeleteEvaluationRegister);
        btnDeleteEvaluationRegister.setLayout(btnDeleteEvaluationRegisterLayout);
        btnDeleteEvaluationRegisterLayout.setHorizontalGroup(
            btnDeleteEvaluationRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDeleteEvaluationRegisterLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(30, 30, 30))
        );
        btnDeleteEvaluationRegisterLayout.setVerticalGroup(
            btnDeleteEvaluationRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDeleteEvaluationRegisterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        leftSidePanel.add(btnDeleteEvaluationRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 190, 40));

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
        jLabel4.setText("Presentar Evaluacion");

        javax.swing.GroupLayout btnPresentEvaluationLayout = new javax.swing.GroupLayout(btnPresentEvaluation);
        btnPresentEvaluation.setLayout(btnPresentEvaluationLayout);
        btnPresentEvaluationLayout.setHorizontalGroup(
            btnPresentEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPresentEvaluationLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        btnPresentEvaluationLayout.setVerticalGroup(
            btnPresentEvaluationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPresentEvaluationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        leftSidePanel.add(btnPresentEvaluation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 190, 40));

        jPanel1.add(leftSidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 450));

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

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Salir");

        javax.swing.GroupLayout exitLayout = new javax.swing.GroupLayout(exit);
        exit.setLayout(exitLayout);
        exitLayout.setHorizontalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        exitLayout.setVerticalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        alertMessagePanel.setBackground(new java.awt.Color(217, 171, 251));

        alertMessage.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertMessage.setText("jLabel14");

        javax.swing.GroupLayout alertMessagePanelLayout = new javax.swing.GroupLayout(alertMessagePanel);
        alertMessagePanel.setLayout(alertMessagePanelLayout);
        alertMessagePanelLayout.setHorizontalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addContainerGap(424, Short.MAX_VALUE))
        );
        alertMessagePanelLayout.setVerticalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertMessagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alertMessagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 580, 60));

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
                "Nombre", "Codigo", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
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
                        .addGap(0, 317, Short.MAX_VALUE)))
                .addContainerGap())
        );
        subjectsPanelLayout.setVerticalGroup(
            subjectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
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
                "Nombre", "Codigo", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
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
                        .addGap(0, 317, Short.MAX_VALUE)))
                .addContainerGap())
        );
        subjectsPanel1Layout.setVerticalGroup(
            subjectsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Materias Vistas", subjectsPanel1);

        jPanel2.setBackground(new java.awt.Color(91, 47, 122));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Lista de evaluaciones posibles para registrar");

        posibleToRegisterEvaluations.setAutoCreateRowSorter(true);
        posibleToRegisterEvaluations.setBackground(new java.awt.Color(78, 36, 102));
        posibleToRegisterEvaluations.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        posibleToRegisterEvaluations.setForeground(new java.awt.Color(255, 255, 255));
        posibleToRegisterEvaluations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Ponderacion", "Fecha Inicio", "Fecha Cierre", "Num. Intentos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
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
                "Tipo", "Ponderacion", "Fecha Inicio", "Fecha Cierre", "Num. Intentos", "Num. Realizada", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        if (registeredEvaluations.getColumnModel().getColumnCount() > 0) {
            registeredEvaluations.getColumnModel().getColumn(5).setHeaderValue("Num. Realizada");
            registeredEvaluations.getColumnModel().getColumn(6).setHeaderValue("Nota");
        }

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
                        .addGap(0, 283, Short.MAX_VALUE)))
                .addContainerGap())
        );
        evaluationsPanelLayout.setVerticalGroup(
            evaluationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evaluationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Evaluaciones Registradas", evaluationsPanel);

        jPanel1.add(mainTabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 580, 390));

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
        //deleteFromList(studentsTable, control.getAllStudents(), "Debe seleccionar un estudiante", "users");
        setTablesValues();
    }//GEN-LAST:event_btnPresentEvaluationMousePressed

    private void btnPresentEvaluationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPresentEvaluationMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnPresentEvaluationMouseExited

    private void btnPresentEvaluationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPresentEvaluationMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnPresentEvaluationMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertMessage;
    private javax.swing.JPanel alertMessagePanel;
    private javax.swing.JPanel btnDeleteEvaluationRegister;
    private javax.swing.JPanel btnPresentEvaluation;
    private javax.swing.JPanel btnRegisterEvaluation;
    private javax.swing.JDialog confirmPresentation;
    private javax.swing.JPanel evaluationsPanel;
    private javax.swing.JPanel exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
