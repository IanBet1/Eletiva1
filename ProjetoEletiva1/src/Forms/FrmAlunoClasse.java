/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Aluno;
import Beans.Classe;
import Beans.ClasseHasAluno;
import Beans.ClasseHasAlunoPK;
import Controller.AlunoJpaController;
import Controller.ClasseHasAlunoJpaController;
import Controller.ClasseJpaController;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.eclipse.persistence.internal.expressions.SQLStatement;

/**
 *
 * @author Terminal
 */
public class FrmAlunoClasse extends javax.swing.JFrame {

    private final ClasseHasAlunoJpaController classealunoDAO;
    private final ClasseJpaController classeDAO;
    private final AlunoJpaController alunoDAO;
    private Object alunoSelecionado;
    private int model;
    private int filtro;
    private List<Aluno> al;
    private List<Aluno> al1;
    private int fil;

    public FrmAlunoClasse() {
        initComponents();
        alunoDAO = new AlunoJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        classeDAO = new ClasseJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        classealunoDAO = new ClasseHasAlunoJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        preencherLstAlunos(alunoDAO.getAlunosAtivos());
        preencherCmbClasse();
        preencherLstAlunosClasse(alunoDAO.getAlunosNessaClasse((Classe) cmbClasse.getSelectedItem()));
        alunoSelecionado = null;
        model = 0;
        filtro = 0;
        al = new ArrayList<Aluno>();
        al1 = new ArrayList<Aluno>();
        fil = 0;
    }

    private void preencherCmbClasse() {
        List<Classe> lista = classeDAO.getClasseAtiva();
        if (lista.size() > 0) {
            for (Classe c : lista) {
                cmbClasse.addItem(c);
            }
        }
    }

    private void preencherLstAlunos(List<Aluno> lista) {
        if (lista.size() >= 0) {
            DefaultListModel listaAlunos = new DefaultListModel();
            DefaultListModel listaAlunos1 = new DefaultListModel();
            lstAlunos.setModel(listaAlunos);
            for (Aluno a : lista) {
                listaAlunos.addElement(a);
            }
            if (filtro == 0) {
                lstAlunoClasse.setModel(listaAlunos1);
            }
        }
    }

    private void adicionarAlunoClasse(Object alunoativo) {
        if (model == 0) {
            DefaultListModel listaAlunos = (DefaultListModel) lstAlunoClasse.getModel();
            if (alunoativo != null) {
                listaAlunos.addElement(alunoativo);
                listaAlunos = (DefaultListModel) lstAlunos.getModel();
                listaAlunos.removeElement(alunoativo);
                alunoSelecionado = null;
            }
        }
        cmbClasse.setEnabled(false);
    }

    private void adicionarAlunos(Object alunoativo) {
        if (model == 1) {
            if (alunoativo != null) {
                DefaultListModel listaAlunos = (DefaultListModel) lstAlunos.getModel();
                listaAlunos.addElement(alunoativo);

                listaAlunos = (DefaultListModel) lstAlunoClasse.getModel();
                listaAlunos.removeElement(alunoativo);
                alunoSelecionado = null;
            }
        }
        cmbClasse.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstAlunos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAlunoClasse = new javax.swing.JList<>();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNomeAluno = new javax.swing.JTextField();
        txtNomeAlunoClasse = new javax.swing.JTextField();
        btnAluno = new javax.swing.JButton();
        btnPesquisarAlunoClasse = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        cmbClasse = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 400));
        setMinimumSize(new java.awt.Dimension(800, 400));
        setPreferredSize(new java.awt.Dimension(759, 395));
        setResizable(false);
        getContentPane().setLayout(null);

        lstAlunos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstAlunos.setToolTipText("");
        lstAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAlunosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstAlunos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 120, 266, 130);

        lstAlunoClasse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAlunoClasseMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstAlunoClasse);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(439, 120, 284, 130);

        btnAdicionar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAdicionar.setText(">>");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(341, 143, 47, 34);

        btnRemover.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnRemover.setText("<<");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(341, 183, 47, 34);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Associar Aluno a Classe");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 11, 282, 31);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Nome do Aluno:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(29, 269, 100, 16);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Nome do Aluno:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(439, 269, 95, 16);
        getContentPane().add(txtNomeAluno);
        txtNomeAluno.setBounds(133, 268, 163, 20);
        getContentPane().add(txtNomeAlunoClasse);
        txtNomeAlunoClasse.setBounds(538, 268, 185, 20);

        btnAluno.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAluno.setText("Pesquisar");
        btnAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAluno);
        btnAluno.setBounds(133, 299, 163, 25);

        btnPesquisarAlunoClasse.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnPesquisarAlunoClasse.setText("Pesquisar");
        btnPesquisarAlunoClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarAlunoClasseActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisarAlunoClasse);
        btnPesquisarAlunoClasse.setBounds(538, 299, 163, 25);

        btnSalvar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(262, 342, 69, 25);

        btnSair.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair);
        btnSair.setBounds(398, 342, 69, 25);

        cmbClasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbClasseItemStateChanged(evt);
            }
        });
        getContentPane().add(cmbClasse);
        cmbClasse.setBounds(79, 60, 160, 20);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Classe:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 63, 41, 16);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void lstAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAlunosMouseClicked
        alunoSelecionado = lstAlunos.getSelectedValue();
        model = 0;
    }//GEN-LAST:event_lstAlunosMouseClicked

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        adicionarAlunoClasse(alunoSelecionado);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        adicionarAlunos(alunoSelecionado);
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void lstAlunoClasseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAlunoClasseMouseClicked
        alunoSelecionado = lstAlunoClasse.getSelectedValue();
        model = 1;
    }//GEN-LAST:event_lstAlunoClasseMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Classe classe = (Classe) cmbClasse.getSelectedItem();
        List<Aluno> alunoBanco = alunoDAO.getAlunoAtivosClasse(classe);
        List<Aluno> removidos = new ArrayList<Aluno>();
        DefaultListModel listaAlunoClasse = (DefaultListModel) lstAlunoClasse.getModel();
        int qtdAlunos = listaAlunoClasse.getSize();
        int removido = 0;
        if (qtdAlunos == 0) {
            JOptionPane.showMessageDialog(null, "Impossível salvar com nenhum aluno selecionado!");
        } else {
            List<Aluno> lista = new ArrayList<Aluno>();
            for (int i = 0; i < qtdAlunos; i++) {
                lista.add((Aluno) listaAlunoClasse.get(i));
            }

            for (int i = 0; i < alunoBanco.size(); i++) {
                removido = 0;
                if (lista.size() > 0) {
                    for (int j = 0; j < lista.size(); j++) {
                        Aluno a = alunoBanco.get(i);
                        Aluno b = lista.get(j);
                        if (a.getMatricula().equals(b.getMatricula())) {
                            lista.remove(b);
                            removido = 0;
                            j = 0;
                            break;
                        } else {
                            removido++;
                        }
                    }
                } else {
                    removido++;
                }
                if (removido != 0) {
                    removidos.add(alunoBanco.get(i));
                }
            }

            if (!removidos.isEmpty()) {
                try {
                    atualizarAlunoClasse(removidos, classe);
                } catch (Exception ex) {
                    Logger.getLogger(FrmAlunoClasse.class.getName()).log(Level.SEVERE, null, ex);
                }
            };

            if (!lista.isEmpty()) {
                try {
                    adicionarCA(lista, classe);
                } catch (Exception ex) {
                    Logger.getLogger(FrmAlunoClasse.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            JOptionPane.showMessageDialog(null, "Aluno(s) da classe " + classe.getIdclasse() + " - " + classe.getTurma() + " com sucesso!");
            cmbClasse.setEnabled(true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void cmbClasseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbClasseItemStateChanged
        preencherLstAlunosClasse(alunoDAO.getAlunosNessaClasse((Classe) cmbClasse.getSelectedItem()));
        if (fil != 0 && !al1.isEmpty()) {
            for (Aluno a : al1) {
                this.al1.remove(a);
            }
        }
        fil = 1;
    }//GEN-LAST:event_cmbClasseItemStateChanged

    private void btnAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlunoActionPerformed
        filtrarAlunoSemClasse(txtNomeAluno.getText());
    }//GEN-LAST:event_btnAlunoActionPerformed

    private void btnPesquisarAlunoClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarAlunoClasseActionPerformed
        filtrarAlunoComClasse(txtNomeAlunoClasse.getText());
    }//GEN-LAST:event_btnPesquisarAlunoClasseActionPerformed

    private void filtrarAlunoSemClasse(String filtro) {
        DefaultListModel dm = (DefaultListModel) lstAlunos.getModel();
        if (this.al.isEmpty()) {
            for (int i = 0; i < dm.size(); i++) {
                Aluno a = (Aluno) dm.get(i);
                try {
                    al.add(a);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
        for (Aluno a : al) {
            if (!a.getNomealuno().contains(filtro)) {
                if (dm.contains(a)) {
                    dm.removeElement((Aluno) a);
                }
            } else {
                if (!dm.contains(a)) {
                    dm.addElement((Aluno) a);
                }
            }
        }
    }

    private void filtrarAlunoComClasse(String filtro) {
        DefaultListModel dm = (DefaultListModel) lstAlunoClasse.getModel();
        if (this.al1.isEmpty()) {
            for (int i = 0; i < dm.size(); i++) {
                Aluno a = (Aluno) dm.get(i);
                try {
                    al1.add(a);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
        for (Aluno a : al1) {
            if (!a.getNomealuno().contains(filtro)) {
                if (dm.contains(a)) {
                    dm.removeElement((Aluno) a);
                }
            } else {
                if (!dm.contains(a)) {
                    dm.addElement((Aluno) a);
                }
            }
        }
    }

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
            java.util.logging.Logger.getLogger(FrmAlunoClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAlunoClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAlunoClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAlunoClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAlunoClasse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAluno;
    private javax.swing.JButton btnPesquisarAlunoClasse;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cmbClasse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstAlunoClasse;
    private javax.swing.JList lstAlunos;
    private javax.swing.JTextField txtNomeAluno;
    private javax.swing.JTextField txtNomeAlunoClasse;
    // End of variables declaration//GEN-END:variables

    private void adicionarCA(List<Aluno> lista, Classe classe) throws Exception {
        for (Aluno a : lista) {
            Aluno a1 = new Aluno();
            try {
                a1 = alunoDAO.getAlunoDesativosClasse(classe, a);
            } catch (Exception Ex) {
                a1 = null;
            }
            if (a1 == null) {
                ClasseHasAluno ca = new ClasseHasAluno();
                ca.setAluno(a);
                ca.setClasse(classe);
                ca.setStatus(true);
                try {
                    classealunoDAO.create(ca);
                } catch (Exception ex) {
                    Logger.getLogger(FrmAlunoClasse.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ClasseHasAlunoPK id = new ClasseHasAlunoPK();
                id.setAlunoMatricula(a.getMatricula());
                id.setClasseIdclasse(classe.getIdclasse());
                ClasseHasAluno ca = classealunoDAO.findClasseHasAluno(id);
                ca.setStatus(true);
                classealunoDAO.edit(ca);
            }
        }
    }

    private void atualizarAlunoClasse(List<Aluno> removidos, Classe classe) throws Exception {
        for (Aluno a : removidos) {
            ClasseHasAlunoPK id = new ClasseHasAlunoPK();
            id.setAlunoMatricula(a.getMatricula());
            id.setClasseIdclasse(classe.getIdclasse());
            ClasseHasAluno ca = classealunoDAO.findClasseHasAluno(id);
            ca.setStatus(false);
            classealunoDAO.edit(ca);
        }
    }

    private void preencherLstAlunosClasse(List<Aluno> lista) {
        if (lista.size() >= 0) {
            DefaultListModel listaAlunos = (DefaultListModel) lstAlunoClasse.getModel();
            listaAlunos.clear();
            for (Aluno a : lista) {
                listaAlunos.addElement(a);
            }
        }
    }
}
