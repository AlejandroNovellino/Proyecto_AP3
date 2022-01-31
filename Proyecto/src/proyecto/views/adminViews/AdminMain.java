/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.adminViews;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyecto.controls.AdminMainControl;
import proyecto.dataModel.enums.filesNames;
import proyecto.helpers.JFramesHelper;
import proyecto.views.Login;

/**
 *
 * @author Alejandro
 */
public class AdminMain extends javax.swing.JFrame {
    private static AdminMain uniqueInstance = null;
    private final int[] standarColorButtons = {103,69,128};
    private final int[] hoverColorButtons = {78, 36, 102};
    private AdminMainControl control;
    // auxiliary views
    InfoEvaluation infoEvaluation = null;
    InfoSubject infoSubject = null;
    /**
     * Creates new form AdminMain
     */
    private AdminMain() {
        initComponents();
        // set the jframe icon
        JFramesHelper.setJFrameIcon(this);
        alertMessagePanel.setVisible(false);
        control = new AdminMainControl();
        // set the align place to the tables
        JFramesHelper.alignTextJTableCetner(studentsTable, JFramesHelper.swingConstantToCenter());
        JFramesHelper.alignTextJTableCetner(subjectsTable, JFramesHelper.swingConstantToCenter());
        JFramesHelper.alignTextJTableCetner(evaluationsTable, JFramesHelper.swingConstantToCenter());
        // set the table values
        setTablesValues();
    }
    
    public static AdminMain getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new AdminMain();
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
        clearTableValues(studentsTable);
        clearTableValues(subjectsTable);
        clearTableValues(evaluationsTable);
        // set students table
        DefaultTableModel studentTable = (DefaultTableModel) studentsTable.getModel();
        control.getAllStudents().forEach((element) -> {
            studentTable.addRow(new Object[]{
                element.getNames(),
                element.getLastNames(), 
                element.getCi(),
                element.getGender().toString(),
                (element.getStatus()) ? "Activo" : "Inactivo"  
            });
        });
        // set subjects table
        DefaultTableModel subjectTable = (DefaultTableModel) subjectsTable.getModel();
        control.getAllSubjects().forEach((element) -> {
            subjectTable.addRow(new Object[]{
                element.getName(),
                element.getCode()
            });
        });
        // set evaluations table
        DefaultTableModel evaluationTable = (DefaultTableModel) evaluationsTable.getModel();
        control.getAllEvaluations().forEach((element) -> {
            evaluationTable.addRow(new Object[]{
                element.getType().toString(),
                element.getWeighing(),
                element.getCloseDate().toString()
            });
        });
    }
    
    private void deleteFromList(JTable table, ArrayList<?> list, String message, filesNames fileName) {
        try {
            int index = table.getSelectedRow();
            control.deleteFromList(list, index, fileName);
            setTablesValues();
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, message);
        }
    }    
    
    private void editEstudent() {
        try {
            int index = studentsTable.getSelectedRow();
            CreateStudent.getInstance(control.getElementByIndex(control.getAllStudents(), index)).setVisible(true);
            JFramesHelper.setJFrameIcon(this);
            uniqueInstance.setVisible(false);
            killInstance();
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar un estudiante");
        }
    }
    
    private void showEvaluationDetails() {
        try {
            int index = evaluationsTable.getSelectedRow();
            infoEvaluation = new InfoEvaluation(control.getElementByIndex(control.getAllEvaluations(), index));
            infoEvaluation.setVisible(true);
            uniqueInstance.setVisible(false);
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una evaluacion");
        }
    }
    
    private void showSubjectDetails() {
        try {
            int index = subjectsTable.getSelectedRow();
            infoSubject = new InfoSubject(
                    control.getAllStudents(), 
                    control.getElementByIndex(control.getAllSubjects(), index), 
                    control.getAllEvaluations()
            );
            infoSubject.setVisible(true);
            uniqueInstance.setVisible(false);
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una materia");
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
        leftSidePanel = new javax.swing.JPanel();
        btnStudentAdd = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnStudentModify = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnSubjectDelete = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnStudentDelete = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnSubjectAdd = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnEvaluationAdd = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnEvaluationDelete = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnEvaluationInfo = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnSubjectInfo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        topPanel = new javax.swing.JPanel();
        alertMessagePanel = new javax.swing.JPanel();
        alertMessage = new javax.swing.JLabel();
        exit = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        mainTabbedPane = new javax.swing.JTabbedPane();
        studentsPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentsTable = new javax.swing.JTable();
        subjectsPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subjectsTable = new javax.swing.JTable();
        evaluationsPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        evaluationsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Admin View");
        setBackground(new java.awt.Color(65, 10, 97));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(65, 10, 97));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leftSidePanel.setBackground(new java.awt.Color(65, 10, 97));
        leftSidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnStudentAdd.setBackground(new java.awt.Color(103, 69, 128));
        btnStudentAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStudentAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStudentAddMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStudentAddMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-add-24.png"))); // NOI18N
        jLabel1.setText("Agregar Estudiante");

        javax.swing.GroupLayout btnStudentAddLayout = new javax.swing.GroupLayout(btnStudentAdd);
        btnStudentAdd.setLayout(btnStudentAddLayout);
        btnStudentAddLayout.setHorizontalGroup(
            btnStudentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentAddLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        btnStudentAddLayout.setVerticalGroup(
            btnStudentAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnStudentAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 220, 40));

        btnStudentModify.setBackground(new java.awt.Color(103, 69, 128));
        btnStudentModify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStudentModifyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStudentModifyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStudentModifyMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-edit-file-24.png"))); // NOI18N
        jLabel2.setText("Modificar Estudiante");

        javax.swing.GroupLayout btnStudentModifyLayout = new javax.swing.GroupLayout(btnStudentModify);
        btnStudentModify.setLayout(btnStudentModifyLayout);
        btnStudentModifyLayout.setHorizontalGroup(
            btnStudentModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentModifyLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        btnStudentModifyLayout.setVerticalGroup(
            btnStudentModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnStudentModify, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 220, 40));

        btnSubjectDelete.setBackground(new java.awt.Color(103, 69, 128));
        btnSubjectDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubjectDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubjectDeleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSubjectDeleteMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-delete-24.png"))); // NOI18N
        jLabel3.setText("Borrar Materia");

        javax.swing.GroupLayout btnSubjectDeleteLayout = new javax.swing.GroupLayout(btnSubjectDelete);
        btnSubjectDelete.setLayout(btnSubjectDeleteLayout);
        btnSubjectDeleteLayout.setHorizontalGroup(
            btnSubjectDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSubjectDeleteLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        btnSubjectDeleteLayout.setVerticalGroup(
            btnSubjectDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnSubjectDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 220, 40));

        btnStudentDelete.setBackground(new java.awt.Color(103, 69, 128));
        btnStudentDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStudentDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStudentDeleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStudentDeleteMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-delete-24.png"))); // NOI18N
        jLabel4.setText("Borrar Estudiante");

        javax.swing.GroupLayout btnStudentDeleteLayout = new javax.swing.GroupLayout(btnStudentDelete);
        btnStudentDelete.setLayout(btnStudentDeleteLayout);
        btnStudentDeleteLayout.setHorizontalGroup(
            btnStudentDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentDeleteLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        btnStudentDeleteLayout.setVerticalGroup(
            btnStudentDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnStudentDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 220, 40));

        btnSubjectAdd.setBackground(new java.awt.Color(103, 69, 128));
        btnSubjectAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnSubjectAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubjectAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubjectAddMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSubjectAddMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-add-24.png"))); // NOI18N
        jLabel5.setText("Agregar Materia");

        javax.swing.GroupLayout btnSubjectAddLayout = new javax.swing.GroupLayout(btnSubjectAdd);
        btnSubjectAdd.setLayout(btnSubjectAddLayout);
        btnSubjectAddLayout.setHorizontalGroup(
            btnSubjectAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSubjectAddLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        btnSubjectAddLayout.setVerticalGroup(
            btnSubjectAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnSubjectAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 220, 40));

        btnEvaluationAdd.setBackground(new java.awt.Color(103, 69, 128));
        btnEvaluationAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnEvaluationAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEvaluationAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEvaluationAddMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEvaluationAddMousePressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-add-24.png"))); // NOI18N
        jLabel10.setText("Agregar Evaluación");

        javax.swing.GroupLayout btnEvaluationAddLayout = new javax.swing.GroupLayout(btnEvaluationAdd);
        btnEvaluationAdd.setLayout(btnEvaluationAddLayout);
        btnEvaluationAddLayout.setHorizontalGroup(
            btnEvaluationAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEvaluationAddLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel10)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        btnEvaluationAddLayout.setVerticalGroup(
            btnEvaluationAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnEvaluationAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 220, 40));

        btnEvaluationDelete.setBackground(new java.awt.Color(103, 69, 128));
        btnEvaluationDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEvaluationDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEvaluationDeleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEvaluationDeleteMousePressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-delete-24.png"))); // NOI18N
        jLabel12.setText("Borrar Evaluación");

        javax.swing.GroupLayout btnEvaluationDeleteLayout = new javax.swing.GroupLayout(btnEvaluationDelete);
        btnEvaluationDelete.setLayout(btnEvaluationDeleteLayout);
        btnEvaluationDeleteLayout.setHorizontalGroup(
            btnEvaluationDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEvaluationDeleteLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        btnEvaluationDeleteLayout.setVerticalGroup(
            btnEvaluationDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnEvaluationDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 220, 40));

        btnEvaluationInfo.setBackground(new java.awt.Color(103, 69, 128));
        btnEvaluationInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEvaluationInfoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEvaluationInfoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEvaluationInfoMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-report-card-24.png"))); // NOI18N
        jLabel14.setText("Resultados Evaluación");

        javax.swing.GroupLayout btnEvaluationInfoLayout = new javax.swing.GroupLayout(btnEvaluationInfo);
        btnEvaluationInfo.setLayout(btnEvaluationInfoLayout);
        btnEvaluationInfoLayout.setHorizontalGroup(
            btnEvaluationInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEvaluationInfoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel14)
                .addGap(19, 19, 19))
        );
        btnEvaluationInfoLayout.setVerticalGroup(
            btnEvaluationInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnEvaluationInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 220, 40));

        btnSubjectInfo.setBackground(new java.awt.Color(103, 69, 128));
        btnSubjectInfo.setForeground(new java.awt.Color(255, 255, 255));
        btnSubjectInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubjectInfoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubjectInfoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSubjectInfoMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-report-card-24.png"))); // NOI18N
        jLabel6.setText("Resultados Materia");

        javax.swing.GroupLayout btnSubjectInfoLayout = new javax.swing.GroupLayout(btnSubjectInfo);
        btnSubjectInfo.setLayout(btnSubjectInfoLayout);
        btnSubjectInfoLayout.setHorizontalGroup(
            btnSubjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSubjectInfoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        btnSubjectInfoLayout.setVerticalGroup(
            btnSubjectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        leftSidePanel.add(btnSubjectInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 220, 40));

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/views/icons/icons8-tableau-software-48.png"))); // NOI18N
        jLabel11.setText("AP Análisis ");
        leftSidePanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jPanel1.add(leftSidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 530));

        topPanel.setBackground(new java.awt.Color(65, 10, 97));

        alertMessagePanel.setBackground(new java.awt.Color(217, 171, 251));

        alertMessage.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertMessage.setText("EXAMPLE TEXT");

        javax.swing.GroupLayout alertMessagePanelLayout = new javax.swing.GroupLayout(alertMessagePanel);
        alertMessagePanel.setLayout(alertMessagePanelLayout);
        alertMessagePanelLayout.setHorizontalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap(439, Short.MAX_VALUE))
        );
        alertMessagePanelLayout.setVerticalGroup(
            alertMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alertMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(19, 19, 19))
        );
        exitLayout.setVerticalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
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
                    .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel1.add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 820, 60));

        mainTabbedPane.setBackground(new java.awt.Color(65, 10, 97));
        mainTabbedPane.setToolTipText("");

        studentsPanel.setBackground(new java.awt.Color(91, 47, 122));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Lista de estudiantes");

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));

        studentsTable.setBackground(new java.awt.Color(78, 36, 102));
        studentsTable.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        studentsTable.setForeground(new java.awt.Color(255, 255, 255));
        studentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "CI", "Género", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
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
        studentsTable.setGridColor(new java.awt.Color(217, 171, 251));
        studentsTable.setSelectionBackground(new java.awt.Color(199, 147, 230));
        studentsTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        studentsTable.setShowVerticalLines(false);
        studentsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(studentsTable);

        javax.swing.GroupLayout studentsPanelLayout = new javax.swing.GroupLayout(studentsPanel);
        studentsPanel.setLayout(studentsPanelLayout);
        studentsPanelLayout.setHorizontalGroup(
            studentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(studentsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 622, Short.MAX_VALUE)))
                .addContainerGap())
        );
        studentsPanelLayout.setVerticalGroup(
            studentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Estudiante", studentsPanel);

        subjectsPanel.setBackground(new java.awt.Color(91, 47, 122));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lista de materias");

        subjectsTable.setBackground(new java.awt.Color(78, 36, 102));
        subjectsTable.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        subjectsTable.setForeground(new java.awt.Color(255, 255, 255));
        subjectsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Código"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subjectsTable.setGridColor(new java.awt.Color(217, 171, 251));
        subjectsTable.setSelectionBackground(new java.awt.Color(199, 147, 230));
        subjectsTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        subjectsTable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(subjectsTable);

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
                        .addGap(0, 644, Short.MAX_VALUE)))
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

        mainTabbedPane.addTab("Materias", subjectsPanel);

        evaluationsPanel.setBackground(new java.awt.Color(91, 47, 122));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Lista de evaluaciones");

        evaluationsTable.setBackground(new java.awt.Color(78, 36, 102));
        evaluationsTable.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        evaluationsTable.setForeground(new java.awt.Color(255, 255, 255));
        evaluationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Ponderación", "Fecha cierre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Object.class
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
        evaluationsTable.setGridColor(new java.awt.Color(217, 171, 251));
        evaluationsTable.setSelectionBackground(new java.awt.Color(199, 147, 230));
        evaluationsTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        evaluationsTable.setShowVerticalLines(false);
        jScrollPane3.setViewportView(evaluationsTable);

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
                        .addGap(0, 610, Short.MAX_VALUE)))
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

        mainTabbedPane.addTab("Evaluaciones", evaluationsPanel);

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

    private void btnStudentAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentAddMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnStudentAddMouseEntered

    private void btnStudentAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentAddMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnStudentAddMouseExited

    private void btnStudentAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentAddMousePressed
        CreateStudent.getInstance().setVisible(true);
        uniqueInstance.setVisible(false);
        killInstance();
    }//GEN-LAST:event_btnStudentAddMousePressed

    private void btnStudentModifyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentModifyMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnStudentModifyMouseEntered

    private void btnSubjectAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectAddMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnSubjectAddMouseEntered

    private void btnSubjectAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectAddMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnSubjectAddMouseExited

    private void btnSubjectAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectAddMousePressed
        CreateSubject.getInstance().setVisible(true);
        uniqueInstance.setVisible(false);
        killInstance();
    }//GEN-LAST:event_btnSubjectAddMousePressed

    private void btnEvaluationAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationAddMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnEvaluationAddMouseEntered

    private void btnEvaluationAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationAddMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnEvaluationAddMouseExited

    private void btnEvaluationAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationAddMousePressed
        try {
            // set the subject to add a evaluation
            int index = subjectsTable.getSelectedRow();
            int code = (int)subjectsTable.getValueAt(index, 1);
            CreateEvaluation.getInstance().setVisible(true);
            CreateEvaluation.getInstance().getControl().setSubject(code);
            uniqueInstance.setVisible(false);
            killInstance();
        } catch (Exception e) {
            JFramesHelper.setMessage(alertMessagePanel, alertMessage, true, "Se debe seleccionar una materia");
        }
    }//GEN-LAST:event_btnEvaluationAddMousePressed

    private void btnStudentDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentDeleteMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnStudentDeleteMouseEntered

    private void btnStudentDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentDeleteMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnStudentDeleteMouseExited

    private void btnStudentDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentDeleteMousePressed
        deleteFromList(studentsTable, control.getAllStudents(), "Debe seleccionar un estudiante", filesNames.users);
    }//GEN-LAST:event_btnStudentDeleteMousePressed

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

    private void btnSubjectDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectDeleteMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnSubjectDeleteMouseEntered

    private void btnSubjectDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectDeleteMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnSubjectDeleteMouseExited

    private void btnSubjectDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectDeleteMousePressed
        deleteFromList(subjectsTable, control.getAllSubjects(), "Debe seleccionar una materia", filesNames.subjects);
    }//GEN-LAST:event_btnSubjectDeleteMousePressed

    private void btnEvaluationDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationDeleteMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnEvaluationDeleteMouseEntered

    private void btnEvaluationDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationDeleteMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnEvaluationDeleteMouseExited

    private void btnEvaluationDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationDeleteMousePressed
        deleteFromList(evaluationsTable, control.getAllEvaluations(), "Debe seleccionar una evaluacion", filesNames.evaluations);
    }//GEN-LAST:event_btnEvaluationDeleteMousePressed

    private void btnStudentModifyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentModifyMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnStudentModifyMouseExited

    private void btnStudentModifyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentModifyMousePressed
        editEstudent();
    }//GEN-LAST:event_btnStudentModifyMousePressed

    private void btnEvaluationInfoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationInfoMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnEvaluationInfoMouseEntered

    private void btnEvaluationInfoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationInfoMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnEvaluationInfoMouseExited

    private void btnEvaluationInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluationInfoMousePressed
        showEvaluationDetails();
    }//GEN-LAST:event_btnEvaluationInfoMousePressed

    private void btnSubjectInfoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectInfoMouseEntered
        changeBackgroundColor(evt, hoverColorButtons[0], hoverColorButtons[1], hoverColorButtons[2]);
    }//GEN-LAST:event_btnSubjectInfoMouseEntered

    private void btnSubjectInfoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectInfoMouseExited
        changeBackgroundColor(evt, standarColorButtons[0], standarColorButtons[1], standarColorButtons[2]);
    }//GEN-LAST:event_btnSubjectInfoMouseExited

    private void btnSubjectInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectInfoMousePressed
        showSubjectDetails();
    }//GEN-LAST:event_btnSubjectInfoMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertMessage;
    private javax.swing.JPanel alertMessagePanel;
    private javax.swing.JPanel btnEvaluationAdd;
    private javax.swing.JPanel btnEvaluationDelete;
    private javax.swing.JPanel btnEvaluationInfo;
    private javax.swing.JPanel btnStudentAdd;
    private javax.swing.JPanel btnStudentDelete;
    private javax.swing.JPanel btnStudentModify;
    private javax.swing.JPanel btnSubjectAdd;
    private javax.swing.JPanel btnSubjectDelete;
    private javax.swing.JPanel btnSubjectInfo;
    private javax.swing.JPanel evaluationsPanel;
    private javax.swing.JTable evaluationsTable;
    private javax.swing.JPanel exit;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel leftSidePanel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JPanel studentsPanel;
    private javax.swing.JTable studentsTable;
    private javax.swing.JPanel subjectsPanel;
    private javax.swing.JTable subjectsTable;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
