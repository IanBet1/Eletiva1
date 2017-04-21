/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

/**
 *
 * @author Terminal
 */
public class FrmPlanoAulaSemanal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPlanoAulaSemanal
     */
    public FrmPlanoAulaSemanal() {
        initComponents();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDataInicio = new com.toedter.calendar.JDateChooser();
        txtDataFinal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlSegunda = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAcolhidaAlunos = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        cmbAreaConhecimento = new javax.swing.JComboBox<>();
        btnMais = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEstrRecuAtivi = new javax.swing.JTextArea();
        btnAdicionar = new javax.swing.JButton();
        btnRecuperar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlanoAula = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtAnexo = new javax.swing.JTextField();
        btnAnexar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        pnlterca = new javax.swing.JPanel();
        pnlQuarta = new javax.swing.JPanel();
        pnlQuinta = new javax.swing.JPanel();
        pnlSexta = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Plano de Aula Semanal");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Data de Início:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Data Final:");

        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Principal Objetivo do Dia:");

        txtPrincipalObjetivoDia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrincipalObjetivoDiaKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel5.setText("Acolhida dos Alunos:");

        txtAcolhidaAlunos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcolhidaAlunosKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setText("Área de Conhecimento:");

        cmbAreaConhecimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnMais.setText("+");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setText("Estratégia, Recursos, Atividades Complementares ");

        txtEstrRecuAtivi.setColumns(20);
        txtEstrRecuAtivi.setRows(5);
        txtEstrRecuAtivi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstrRecuAtiviKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtEstrRecuAtivi);

        btnAdicionar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAdicionar.setText(">>");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRecuperar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnRecuperar.setText("<<");

        tblPlanoAula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estratégia, Recursos e Atividades Complementares "
            }
        ));
        jScrollPane2.setViewportView(tblPlanoAula);

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setText("Plano de Aula");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setText("Matriz (Anexar):");

        btnAnexar.setText("Anexar");

        jLabel10.setText("jLabel10");

        jTextField2.setText("jTextField2");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel11.setText("Observações e Reflexões:");

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        txtObservacoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacoesKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(txtObservacoes);

        javax.swing.GroupLayout pnlSegundaLayout = new javax.swing.GroupLayout(pnlSegunda);
        pnlSegunda.setLayout(pnlSegundaLayout);
        pnlSegundaLayout.setHorizontalGroup(
            pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSegundaLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtPrincipalObjetivoDia, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSegundaLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(43, 43, 43)
                            .addComponent(txtAcolhidaAlunos)))
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar)
                                    .addComponent(btnRecuperar)))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbAreaConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnMais, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(jLabel8))))))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAnexo)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAnexar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSegundaLayout.setVerticalGroup(
            pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4))
                    .addComponent(txtPrincipalObjetivoDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5))
                    .addComponent(txtAcolhidaAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnAdicionar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRecuperar))
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel6))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbAreaConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMais))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Segunda - Feira", pnlSegunda);

        javax.swing.GroupLayout pnltercaLayout = new javax.swing.GroupLayout(pnlterca);
        pnlterca.setLayout(pnltercaLayout);
        pnltercaLayout.setHorizontalGroup(
            pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        pnltercaLayout.setVerticalGroup(
            pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Terça - Feira", pnlterca);

        javax.swing.GroupLayout pnlQuartaLayout = new javax.swing.GroupLayout(pnlQuarta);
        pnlQuarta.setLayout(pnlQuartaLayout);
        pnlQuartaLayout.setHorizontalGroup(
            pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        pnlQuartaLayout.setVerticalGroup(
            pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quarta - Feira", pnlQuarta);

        javax.swing.GroupLayout pnlQuintaLayout = new javax.swing.GroupLayout(pnlQuinta);
        pnlQuinta.setLayout(pnlQuintaLayout);
        pnlQuintaLayout.setHorizontalGroup(
            pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        pnlQuintaLayout.setVerticalGroup(
            pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quinta - Feira", pnlQuinta);

        javax.swing.GroupLayout pnlSextaLayout = new javax.swing.GroupLayout(pnlSexta);
        pnlSexta.setLayout(pnlSextaLayout);
        pnlSextaLayout.setHorizontalGroup(
            pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        pnlSextaLayout.setVerticalGroup(
            pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sexta - Feira", pnlSexta);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton1.setText("Salvar Plano de Aula");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("abaSegunda");

        setSize(new java.awt.Dimension(1059, 752));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrincipalObjetivoDiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrincipalObjetivoDiaKeyPressed
        // TODO add your handling code here:
        String principalObjetivo = txtPrincipalObjetivoDia.getText();
        int quantosCaracteres = principalObjetivo.length();
        if (quantosCaracteres > 99) {
            principalObjetivo = principalObjetivo.substring(0, principalObjetivo.length() - 1);
            txtPrincipalObjetivoDia.setText(principalObjetivo);
        }
    }//GEN-LAST:event_txtPrincipalObjetivoDiaKeyPressed

    private void txtAcolhidaAlunosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcolhidaAlunosKeyPressed
        // TODO add your handling code here:
        String acolhidaAlunos = txtAcolhidaAlunos.getText();
        int quantosCaracteres = acolhidaAlunos.length();
        if (quantosCaracteres > 99) {
            acolhidaAlunos = acolhidaAlunos.substring(0, acolhidaAlunos.length() - 1);
            txtAcolhidaAlunos.setText(acolhidaAlunos);
        }
    }//GEN-LAST:event_txtAcolhidaAlunosKeyPressed

    private void txtEstrRecuAtiviKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstrRecuAtiviKeyPressed
        // TODO add your handling code here:
        String estrRecuAtivi = txtEstrRecuAtivi.getText();
        int quantosCaracteres = estrRecuAtivi.length();
        if (quantosCaracteres > 499) {
            estrRecuAtivi = estrRecuAtivi.substring(0, estrRecuAtivi.length() - 1);
            txtEstrRecuAtivi.setText(estrRecuAtivi);
        }
    }//GEN-LAST:event_txtEstrRecuAtiviKeyPressed

    private void txtObservacoesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacoesKeyPressed
        // TODO add your handling code here:
        String observacao = txtObservacoes.getText();
        int quantosCaracteres = observacao.length();
        if (quantosCaracteres > 499) {
            observacao = observacao.substring(0, observacao.length() - 1);
            txtObservacoes.setText(observacao);
        }
    }//GEN-LAST:event_txtObservacoesKeyPressed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPlanoAulaSemanal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAnexar;
    private javax.swing.JButton btnMais;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JComboBox<String> cmbAreaConhecimento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel pnlQuarta;
    private javax.swing.JPanel pnlQuinta;
    private javax.swing.JPanel pnlSegunda;
    private javax.swing.JPanel pnlSexta;
    private javax.swing.JPanel pnlterca;
    private javax.swing.JTable tblPlanoAula;
    private javax.swing.JTextField txtAcolhidaAlunos;
    private javax.swing.JTextField txtAnexo;
    private com.toedter.calendar.JDateChooser txtDataFinal;
    private com.toedter.calendar.JDateChooser txtDataInicio;
    private javax.swing.JTextArea txtEstrRecuAtivi;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextField txtPrincipalObjetivoDia;
    // End of variables declaration//GEN-END:variables
}
