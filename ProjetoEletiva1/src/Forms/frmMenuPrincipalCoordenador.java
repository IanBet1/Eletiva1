/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Planoaula;
import Beans.Usuario;
import Controller.PlanoaulaJpaController;
import Controller.UsuarioJpaController;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Terminal
 */
public class frmMenuPrincipalCoordenador extends javax.swing.JFrame {

     public Usuario user;
    public Usuario prof;
    public Planoaula plano;
    private final PlanoaulaJpaController planoDAO;
    private final UsuarioJpaController usuarioDAO;

    public frmMenuPrincipalCoordenador(Usuario user2) {
        initComponents();
        this.user = user2;
        planoDAO = new PlanoaulaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        usuarioDAO = new UsuarioJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        mudaLabel(user2.getNome());
    }

    frmMenuPrincipalCoordenador() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private void carregaTabelaProfessor(List<Planoaula> lista){
        if (lista.size() >= 0) {
            DefaultTableModel tabelaProfessor = (DefaultTableModel) tblProfessor.getModel();
            tabelaProfessor.setNumRows(0);
            for (Planoaula p : lista) {
                Object[] obj = new Object[]{                    
                    p.getUsuarioLogin().getLogin(),
                    p.getUsuarioLogin()
                };
                tabelaProfessor.addRow(obj);
            }
        }
    }
    
    private void carregaTabelaPlanoAula(List<Planoaula> lista){
        if (lista.size() >= 0) {
            DefaultTableModel tabelaPlanoAula = (DefaultTableModel) tblPlanoAula.getModel();
            tabelaPlanoAula.setNumRows(0);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            for (Planoaula p : lista) {
                if(p.getStatus().equals("Em Aprovação")){
                Object[] obj = new Object[]{
                
                    p,
                    formato.format(p.getDatainicio()),
                    formato.format(p.getDatafim())
                };
                tabelaPlanoAula.addRow(obj);
            }
            }
        }
    }
    public void mudaLabel(String nome) {
        jLabel2.setText("Bem Vindo(a), Coordenador(a) " + nome+".");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProfessor = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlanoAula = new javax.swing.JTable();
        btnInicio = new javax.swing.JButton();
        btnCadastroAluno = new javax.swing.JButton();
        btnAprovacaoAvaliacaoBimestral = new javax.swing.JButton();
        btnCadastroClasse = new javax.swing.JButton();
        btnAprovacaoAvaliacaoMensal = new javax.swing.JButton();
        btnAprovacaoAulaSemanal2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCalendar2 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário Principal Coordenador");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Menu Principal Coordenador");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 340, 31);
        jLabel1.getAccessibleContext().setAccessibleName("Menu Principal \nCoordenador");

        jLabel2.setText("Bem Vindo(a), Coordenador(a):");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 60, 390, 14);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(1104, 620));

        tblProfessor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Professor"
            }
        ));
        tblProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProfessorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProfessor);

        tblPlanoAula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data Início", "Data Fim"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(400, 10, 780, 620);

        btnInicio.setText("Início");
        btnInicio.setPreferredSize(new java.awt.Dimension(197, 23));
        getContentPane().add(btnInicio);
        btnInicio.setBounds(190, 170, 211, 23);

        btnCadastroAluno.setText("Cadastro de Aluno");
        btnCadastroAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastroAluno);
        btnCadastroAluno.setBounds(190, 200, 211, 23);

        btnAprovacaoAvaliacaoBimestral.setText("Aprovação de Avaliação Bimestrall");
        getContentPane().add(btnAprovacaoAvaliacaoBimestral);
        btnAprovacaoAvaliacaoBimestral.setBounds(190, 320, 211, 23);

        btnCadastroClasse.setText("Cadastro de Classe");
        btnCadastroClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroClasseActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastroClasse);
        btnCadastroClasse.setBounds(190, 230, 211, 23);

        btnAprovacaoAvaliacaoMensal.setText("Aprovação de Avaliação Mensal");
        getContentPane().add(btnAprovacaoAvaliacaoMensal);
        btnAprovacaoAvaliacaoMensal.setBounds(190, 290, 211, 23);

        btnAprovacaoAulaSemanal2.setText("Aprovação de Plano de Aula Semanal");
        getContentPane().add(btnAprovacaoAulaSemanal2);
        btnAprovacaoAulaSemanal2.setBounds(190, 260, 211, 23);

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

        setSize(new java.awt.Dimension(1202, 681));
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

    private void btnCadastroClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroClasseActionPerformed
        // TODO add your handling code here:
        FrmCadastroClasse classe = new FrmCadastroClasse();
        classe.setVisible(true);
        //this.dispose();
    }//GEN-LAST:event_btnCadastroClasseActionPerformed

    private void btnCadastroAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroAlunoActionPerformed
        // TODO add your handling code here:
        FrmCadastroAluno aluno = new FrmCadastroAluno();
        aluno.setVisible(true);
        //this.dispose();
    }//GEN-LAST:event_btnCadastroAlunoActionPerformed

    private void tblProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProfessorMouseClicked
        // TODO add your handling code here:
        carregaTabelaPlanoAula(planoDAO.findPlanoaulaEntities());
        int linhaselecionada = tblProfessor.getSelectedRow();
        if(linhaselecionada != -1 )
        {
            prof = (Usuario)tblProfessor.getValueAt(linhaselecionada, 1);
        }
    }//GEN-LAST:event_tblProfessorMouseClicked

    private void tblPlanoAulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoAulaMouseClicked
        // TODO add your handling code here:
        int linhaselecionada = tblPlanoAula.getSelectedRow();
        if(linhaselecionada != -1 )
        {
            plano = (Planoaula)tblPlanoAula.getValueAt(linhaselecionada, 0);
        }
        if(evt.getClickCount() > 1){
            int row = this.tblPlanoAula.rowAtPoint(evt.getPoint());
            // Abre um diálogo pra editar os dados
            FrmAprovarPlanoAula fapa = new FrmAprovarPlanoAula(user, plano);
            fapa.setVisible(true);
        }
    }//GEN-LAST:event_tblPlanoAulaMouseClicked

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
            java.util.logging.Logger.getLogger(frmMenuPrincipalCoordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipalCoordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipalCoordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipalCoordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frmMenuPrincipalCoordenador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprovacaoAulaSemanal2;
    private javax.swing.JButton btnAprovacaoAvaliacaoBimestral;
    private javax.swing.JButton btnAprovacaoAvaliacaoMensal;
    private javax.swing.JButton btnCadastroAluno;
    private javax.swing.JButton btnCadastroClasse;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPlanoAula;
    private javax.swing.JTable tblProfessor;
    // End of variables declaration//GEN-END:variables
}
