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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmbClasse = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAlunoClasse = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstAlunos = new javax.swing.JList();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNomeAluno = new javax.swing.JTextField();
        btnAluno = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNomeAlunoClasse = new javax.swing.JTextField();
        btnPesquisarAlunoClasse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 400));
        setMinimumSize(new java.awt.Dimension(800, 400));
        setPreferredSize(new java.awt.Dimension(759, 395));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(16, 37, 64));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Associar Aluno a Classe");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel3.setBackground(new java.awt.Color(232, 244, 248));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Classe:");

        cmbClasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbClasseItemStateChanged(evt);
            }
        });

        lstAlunoClasse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAlunoClasseMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstAlunoClasse);

        lstAlunos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstAlunos.setToolTipText("");
        lstAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAlunosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstAlunos);

        btnAdicionar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAdicionar.setText(">>");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnRemover.setText("<<");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Nome do Aluno:");

        btnAluno.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAluno.setText("Pesquisar");
        btnAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlunoActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSair.setText("Voltar");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Nome do Aluno:");

        btnPesquisarAlunoClasse.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnPesquisarAlunoClasse.setText("Pesquisar");
        btnPesquisarAlunoClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarAlunoClasseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(cmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionar)
                            .addComponent(btnRemover))
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(txtNomeAlunoClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btnAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(247, 247, 247)
                        .addComponent(btnPesquisarAlunoClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(btnSalvar)
                        .addGap(10, 10, 10)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(cmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeAlunoClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAluno)
                    .addComponent(btnPesquisarAlunoClasse))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar)
                    .addComponent(btnSair))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Aviso!", 1);
        if (dialogResult == JOptionPane.YES_OPTION) {
            super.dispose();
        }
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
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja salvar estes alunos nesta classe?", "Aviso!", 1);
        if (dialogResult == JOptionPane.YES_OPTION) {
            Classe classe = (Classe) cmbClasse.getSelectedItem();
            List<Aluno> alunoBanco = alunoDAO.getAlunoAtivosClasse(classe);
            List<Aluno> removidos = new ArrayList<Aluno>();
            DefaultListModel listaAlunoClasse = (DefaultListModel) lstAlunoClasse.getModel();
            salvarClasseAlunos(classe, alunoBanco, removidos, listaAlunoClasse);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void salvarClasseAlunos(Classe classe, List<Aluno> alunoBanco, List<Aluno> removidos, DefaultListModel listaAlunoClasse) {
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
    }

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
            } else if (!dm.contains(a)) {
                dm.addElement((Aluno) a);
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
            } else if (!dm.contains(a)) {
                dm.addElement((Aluno) a);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
