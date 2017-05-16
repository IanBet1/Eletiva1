/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Categoria;
import Beans.Classe;
import Beans.Planoaula;
import Beans.Usuario;
import Controller.CategoriaJpaController;
import Controller.ClasseJpaController;
import Controller.PlanoaulaJpaController;
import static java.lang.String.format;
import static java.lang.String.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Terminal
 */
public class frmMenuPrincipalProfessor extends javax.swing.JFrame {

    public Usuario user;
    public Planoaula plano;
    private final PlanoaulaJpaController planoDAO;
    private final ClasseJpaController classeDAO;
    private final CategoriaJpaController categoriaDAO;

    public frmMenuPrincipalProfessor(Usuario user2) {
        initComponents();
        this.user = user2;
        mudaLabel(user2.getNome());
        planoDAO = new PlanoaulaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        classeDAO = new ClasseJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        categoriaDAO = new CategoriaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        carregaTabelaPlanoAula(planoDAO.findPlanoaulaEntities());
        txtObservacao.setEnabled(false);
    }

    private frmMenuPrincipalProfessor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void carregaTabelaPlanoAula(List<Planoaula> lista) {
        if (lista.size() >= 0) {
            DefaultTableModel tabelaPlanoAula = (DefaultTableModel) tblPlanoAula.getModel();
            tabelaPlanoAula.setNumRows(0);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            SimpleDateFormat sdf = new SimpleDateFormat("MM");

            for (Planoaula p : lista) {
                if (p.getUsuarioLogin().getLogin().equals(user.getLogin())) {
                    if (!p.getStatus().equals("Em Aprovação")) {
                        String mes = sdf.format(p.getDatainicio());
                        if (mes.equalsIgnoreCase("01")) {
                            mes = "Janeiro";
                        }
                        if (mes.equalsIgnoreCase("02")) {
                            mes = "Fevereiro";
                        }
                        if (mes.equalsIgnoreCase("03")) {
                            mes = "Março";
                        }
                        if (mes.equalsIgnoreCase("04")) {
                            mes = "Abril";
                        }
                        if (mes.equalsIgnoreCase("05")) {
                            mes = "Maio";
                        }
                        if (mes.equalsIgnoreCase("06")) {
                            mes = "Junho";
                        }
                        if (mes.equalsIgnoreCase("07")) {
                            mes = "Julho";
                        }
                        if (mes.equalsIgnoreCase("08")) {
                            mes = "Agosto";
                        }
                        if (mes.equalsIgnoreCase("09")) {
                            mes = "Setembro";
                        }
                        if (mes.equalsIgnoreCase("10")) {
                            mes = "Outubro";
                        }
                        if (mes.equalsIgnoreCase("11")) {
                            mes = "Novembro";
                        }
                        if (mes.equalsIgnoreCase("12")) {
                            mes = "Dezembro";
                        }
                        Object[] obj = new Object[]{
                            p,
                            mes,
                            formato.format(p.getDatainicio()),
                            formato.format(p.getDatafim())
                        };
                        tabelaPlanoAula.addRow(obj);
                    }
                }
            }
        }
    }

    /* frmMenuPrincipalProfessor() {
    }*/
    public void mudaLabel(String nome) {
        jLabel2.setText("Bem Vindo(a), Professor(a) " + nome + ".");
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
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlanoAula = new javax.swing.JTable();
        btnAvaliacaoAtividadesContinuos = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        btnPlanoAulaSemanal = new javax.swing.JButton();
        btnAvaliacao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCalendar2 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário do Professor");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Menu Principal  Professor");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 289, 31);

        jLabel2.setText("Bem Vindo(a), Professor(a):");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 60, 390, 20);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Observações enviadas.");

        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        jScrollPane1.setViewportView(txtObservacao);

        jLabel4.setText("Plano de Aula:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Plano", "Mês" }));

        jButton2.setText("Filtrar");

        tblPlanoAula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Plano Aula", "Mês", "Data Início", "Data Fim"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlanoAula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanoAulaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPlanoAula);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(400, 10, 780, 620);

        btnAvaliacaoAtividadesContinuos.setText("Avaliação de Atividades Contínuos");
        getContentPane().add(btnAvaliacaoAtividadesContinuos);
        btnAvaliacaoAtividadesContinuos.setBounds(190, 290, 211, 23);

        btnInicio.setText("Início");
        btnInicio.setPreferredSize(new java.awt.Dimension(197, 23));
        getContentPane().add(btnInicio);
        btnInicio.setBounds(190, 200, 211, 23);

        btnPlanoAulaSemanal.setText("Plano de Aula Semanal");
        btnPlanoAulaSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanoAulaSemanalActionPerformed(evt);
            }
        });
        getContentPane().add(btnPlanoAulaSemanal);
        btnPlanoAulaSemanal.setBounds(190, 230, 211, 23);

        btnAvaliacao.setText("Avaliação");
        btnAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvaliacaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAvaliacao);
        btnAvaliacao.setBounds(190, 260, 211, 23);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 610, 53, 25);

        jCalendar2.setWeekOfYearVisible(false);
        getContentPane().add(jCalendar2);
        jCalendar2.setBounds(10, 350, 380, 190);

        setSize(new java.awt.Dimension(1205, 672));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Aviso!", 1);
        if (dialogResult == JOptionPane.YES_OPTION) {
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Aviso!", 1);
        if (dialogResult == JOptionPane.YES_OPTION) {
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPlanoAulaSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanoAulaSemanalActionPerformed
        // TODO add your handling code here:

        List<Classe> verificaClasse = classeDAO.getClasseAtiva1(user.getLogin());
        if (verificaClasse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você não possui classe asssociada!");
        } else {
            FrmPlanoAulaSemanal fpas = null;
            try {
                fpas = new FrmPlanoAulaSemanal(user, null);
            } catch (ParseException ex) {
                Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            fpas.setVisible(true);
        }

    }//GEN-LAST:event_btnPlanoAulaSemanalActionPerformed

    private void tblPlanoAulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoAulaMouseClicked
        // TODO add your handling code here:
        int linhaselecionada = tblPlanoAula.getSelectedRow();
        if (linhaselecionada != -1) {
            plano = (Planoaula) tblPlanoAula.getValueAt(linhaselecionada, 0);
            txtObservacao.setText(plano.getObservacao());
        }
        if (evt.getClickCount() > 1) {
            try {
                int row = this.tblPlanoAula.rowAtPoint(evt.getPoint());
                // Abre um diálogo pra editar os dados
                FrmPlanoAulaSemanal fpas = new FrmPlanoAulaSemanal(user, plano);
                fpas.setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblPlanoAulaMouseClicked

    private void btnAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvaliacaoActionPerformed
        FrmAvaliacao fav;
        try {
            fav = new FrmAvaliacao(user);
            fav.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnAvaliacaoActionPerformed

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
            java.util.logging.Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuPrincipalProfessor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvaliacao;
    private javax.swing.JButton btnAvaliacaoAtividadesContinuos;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPlanoAulaSemanal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPlanoAula;
    private javax.swing.JTextArea txtObservacao;
    // End of variables declaration//GEN-END:variables
}
