/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.views.studentViews;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.DefaultButtonModel;
import javax.swing.table.DefaultTableModel;
import proyecto.controls.PresentEvaluationControl;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.Option;
import proyecto.dataModel.evaluationRelated.Question;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
import proyecto.dataModel.users.Student;
import proyecto.helpers.JFramesHelper;

/**
 *
 * @author Alejandro
 */
public class PresentEvaluation extends javax.swing.JFrame {
    private PresentEvaluationControl control;
    
    public PresentEvaluation(Student student, Evaluation evaluation, EvaluationRegistry evaluationRegistry) {
        initComponents();
        // set the jframe icon
        JFramesHelper.setJFrameIcon(this);
        // set the modal icon
        JFramesHelper.setJDialogIcon(resultsDialog);
        JFramesHelper.setModalSize(resultsDialog);
        this.control = new PresentEvaluationControl(student, evaluation, evaluationRegistry);
        title.setText(control.getEvaluation().getType().toString());
        this.setVisible(true);
        // set the align place to the tables
        JFramesHelper.alignTextJTableCetner(resultsTable, JFramesHelper.swingConstantToCenter());
        setValues();
    }
    
    private void setValues() {
        // set to not visible and not selected all the radio buttons
        options.clearSelection();
        for(Enumeration<AbstractButton> buttonEnumeration = options.getElements(); buttonEnumeration.hasMoreElements();) {
            AbstractButton button = buttonEnumeration.nextElement();
            button.setVisible(false);
        }
        // get the evaluation
        Question questionAux = control.getCurrentQuestion();
        // set the question number
        int valueToDisplay = control.getQuestionIndex()+1;
        questionNumber.setText("Pregunta "+valueToDisplay+":");
        // set the question socre value
        questionScoreValue.setText("Puntaje: "+control.getCurrentQuestion().getScore());
        // set the question statement
        statement.setText(questionAux.getStatement());
        // set the values for the buttons and the visibility
        int counter = 0;
        for(Enumeration<AbstractButton> buttonEnumeration = options.getElements(); buttonEnumeration.hasMoreElements();) {
            if(counter == questionAux.getOptions().size()) {
                break;
            }
            AbstractButton button = buttonEnumeration.nextElement();
            button.setText(questionAux.getOptions().get(counter).getValue());
            button.setVisible(true);
            counter++;
        }
        // if it is the last question set the button to "Entregar"
        if(control.getQuestionIndex() == control.getEvaluation().getQuestions().size()-1) {
            continueBtn.setText("Entregar");
        }
    }
    
    private void setResultsTable() {
        // set subjects table
        DefaultTableModel resultsTableModel = (DefaultTableModel) resultsTable.getModel();
        for(int i=0; i<control.getEvaluation().getQuestions().size(); i++) {
            Question questionAux = control.getEvaluation().getQuestions().get(i);
            Option answerAux = null;
            try {
                answerAux = control.getAnswers().get(i);
            } catch (Exception e) {
                // No question was answered
            }
            if(answerAux != null) {
                resultsTableModel.addRow(new Object[]{
                    i+1,
                    questionAux.getStatement(),
                    questionAux.getScore(),
                    answerAux.getValue(),
                    (answerAux.getIsAnswer()) ? "Si":"No"
                });
            } else {
                resultsTableModel.addRow(new Object[]{
                    i+1,
                    questionAux.getStatement(),
                    questionAux.getScore(),
                    "NR",
                    "NR"
                });
            }
        }
    }
    
    private int getSelectedindex() {
        if(option1.isSelected()) {
            return 0;
        } else if(option2.isSelected()) {
            return 1;
        } else if(option3.isSelected()) {
            return 2;
        } else if(option4.isSelected()) {
            return 3;
        } else if(option5.isSelected()) {
            return 4;
        }
        return -1;
    }
    
    private void finishEvaluation() {
        // set the selected question
        control.addToSelectedAnswers(getSelectedindex());
        // save all the options and calculate the note
        control.setResultsOfExam();
        control.saveChangesToFile();
        // set the dialog visible and the form not visible
        this.setVisible(false);
        setResultsTable();
        noteLabel.setText("Nota obtenida: "+control.getCurrentNote());
        resultsDialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        options = new javax.swing.ButtonGroup();
        resultsDialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        modalContinue = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        noteLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        questionNumber = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        option1 = new javax.swing.JRadioButton();
        option2 = new javax.swing.JRadioButton();
        option3 = new javax.swing.JRadioButton();
        option4 = new javax.swing.JRadioButton();
        option5 = new javax.swing.JRadioButton();
        continueBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        statement = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        finishBtn = new javax.swing.JButton();
        questionScoreValue = new javax.swing.JLabel();

        resultsDialog.setTitle("Evaluacion Finalizada");
        resultsDialog.setAlwaysOnTop(true);
        resultsDialog.setModal(true);
        resultsDialog.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(65, 10, 97));

        jPanel5.setBackground(new java.awt.Color(103, 69, 128));

        resultsTable.setBackground(new java.awt.Color(78, 36, 102));
        resultsTable.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        resultsTable.setForeground(new java.awt.Color(255, 255, 255));
        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pregunta", "Enunciado", "Puntaje", "Opción seleccionada", "Correcta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        resultsTable.setGridColor(new java.awt.Color(217, 171, 251));
        resultsTable.setSelectionBackground(new java.awt.Color(199, 147, 230));
        resultsTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        resultsTable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(resultsTable);
        if (resultsTable.getColumnModel().getColumnCount() > 0) {
            resultsTable.getColumnModel().getColumn(0).setResizable(false);
            resultsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            resultsTable.getColumnModel().getColumn(2).setResizable(false);
        }

        modalContinue.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        modalContinue.setText("Continuar");
        modalContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modalContinueActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NR = Pregunta No Respondida");

        noteLabel.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        noteLabel.setForeground(new java.awt.Color(255, 255, 255));
        noteLabel.setText("TEXT EXAMPLE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noteLabel))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(modalContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(noteLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modalContinue)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Resultados");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout resultsDialogLayout = new javax.swing.GroupLayout(resultsDialog.getContentPane());
        resultsDialog.getContentPane().setLayout(resultsDialogLayout);
        resultsDialogLayout.setHorizontalGroup(
            resultsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        resultsDialogLayout.setVerticalGroup(
            resultsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultsDialogLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presentando Evaluacion");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(65, 10, 97));

        title.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("TEXT EXAMPLE");

        jPanel3.setBackground(new java.awt.Color(103, 69, 128));

        questionNumber.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        questionNumber.setForeground(new java.awt.Color(255, 255, 255));
        questionNumber.setText("TEXT EXAMPLE");

        jPanel2.setBackground(new java.awt.Color(103, 69, 128));

        option1.setBackground(new java.awt.Color(210, 210, 210));
        options.add(option1);
        option1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        option1.setText("jRadioButton1");

        option2.setBackground(new java.awt.Color(210, 210, 210));
        options.add(option2);
        option2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        option2.setText("jRadioButton2");

        option3.setBackground(new java.awt.Color(210, 210, 210));
        options.add(option3);
        option3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        option3.setText("jRadioButton3");

        option4.setBackground(new java.awt.Color(210, 210, 210));
        options.add(option4);
        option4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        option4.setText("jRadioButton4");

        option5.setBackground(new java.awt.Color(210, 210, 210));
        options.add(option5);
        option5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        option5.setText("jRadioButton5");

        continueBtn.setBackground(new java.awt.Color(65, 10, 97));
        continueBtn.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        continueBtn.setText("Continuar");
        continueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueBtnActionPerformed(evt);
            }
        });

        statement.setEditable(false);
        statement.setBackground(new java.awt.Color(245, 222, 255));
        statement.setColumns(20);
        statement.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        statement.setLineWrap(true);
        statement.setRows(5);
        statement.setWrapStyleWord(true);
        statement.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        statement.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(statement);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Opciones:");

        finishBtn.setBackground(new java.awt.Color(65, 10, 97));
        finishBtn.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        finishBtn.setText("Finalizar");
        finishBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(finishBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(continueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(option1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(option2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(option3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(option5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(option4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(option1)
                .addGap(30, 30, 30)
                .addComponent(option2)
                .addGap(30, 30, 30)
                .addComponent(option3)
                .addGap(30, 30, 30)
                .addComponent(option4)
                .addGap(30, 30, 30)
                .addComponent(option5)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finishBtn)
                    .addComponent(continueBtn))
                .addContainerGap())
        );

        questionScoreValue.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        questionScoreValue.setForeground(new java.awt.Color(255, 255, 255));
        questionScoreValue.setText("TEXT EXAMPLE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(questionNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(questionScoreValue)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionNumber)
                    .addComponent(questionScoreValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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

    private void continueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueBtnActionPerformed
        if(control.getQuestionIndex() == control.getEvaluation().getQuestions().size()-1) {
            finishEvaluation();
        } else {
            // set the selected question
            control.addToSelectedAnswers(getSelectedindex());
            // get the next question
            control.passQuestion();
            setValues(); 
        }
       
    }//GEN-LAST:event_continueBtnActionPerformed

    private void modalContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modalContinueActionPerformed
        // set the admin view visible
        StudentMain.getInstance().setTablesValues();
        StudentMain.getInstance().setVisible(true);
        resultsDialog.setVisible(false);
        StudentMain.getInstance().killPresentEvaluationInstace();
    }//GEN-LAST:event_modalContinueActionPerformed

    private void finishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishBtnActionPerformed
        finishEvaluation();
    }//GEN-LAST:event_finishBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continueBtn;
    private javax.swing.JButton finishBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton modalContinue;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JRadioButton option1;
    private javax.swing.JRadioButton option2;
    private javax.swing.JRadioButton option3;
    private javax.swing.JRadioButton option4;
    private javax.swing.JRadioButton option5;
    private javax.swing.ButtonGroup options;
    private javax.swing.JLabel questionNumber;
    private javax.swing.JLabel questionScoreValue;
    private javax.swing.JDialog resultsDialog;
    private javax.swing.JTable resultsTable;
    private javax.swing.JTextArea statement;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
