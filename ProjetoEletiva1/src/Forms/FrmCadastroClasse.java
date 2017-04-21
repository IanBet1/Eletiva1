/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Categoria;
import Beans.Classe;
import Beans.Usuario;
import Controller.CategoriaJpaController;
import Controller.ClasseJpaController;
import Controller.UsuarioJpaController;
import java.security.NoSuchAlgorithmException;
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
public class FrmCadastroClasse extends javax.swing.JFrame {

    private final UsuarioJpaController usuarioDAO;
    private final CategoriaJpaController categoriaDAO;
    private final ClasseJpaController classeDAO;
    private int filtro;

    public FrmCadastroClasse() {
        initComponents();
        categoriaDAO = new CategoriaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        usuarioDAO = new UsuarioJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        classeDAO = new ClasseJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        preencherCmbProfessor();
        preencheTabela(classeDAO.findClasseEntities());
        filtro = 0;
    }

    private void preencherCmbProfessor() {
        Categoria c = categoriaDAO.findCategoria(1);
        List<Usuario> lista = usuarioDAO.getProfessores(c);
        if (lista.size() > 0) {
            for (Usuario u : lista) {
                cmbProfessor.addItem(u);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbProfessor = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        txtIdentificadorClasse = new javax.swing.JFormattedTextField();
        cmbPeriodo = new javax.swing.JComboBox();
        txtTurma = new javax.swing.JFormattedTextField();
        txtAnoClasse = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        cmbClasse = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClasse = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesativar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        txtFiltroClasse = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Cadastrar Classe");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Dados da Classe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Identificador da Classe:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Ano da Classe:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Período:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel5.setText("Turma:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setText("Professor(a):");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setText("Status:");

        txtStatus.setEnabled(false);

        txtIdentificadorClasse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdentificadorClasseKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificadorClasseKeyTyped(evt);
            }
        });

        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manhã", "Tarde" }));

        txtTurma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTurmaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTurmaKeyTyped(evt);
            }
        });

        txtAnoClasse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAnoClasseKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnoClasseKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbProfessor, 0, 166, Short.MAX_VALUE)
                            .addComponent(cmbPeriodo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 166, Short.MAX_VALUE)
                            .addComponent(txtTurma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(69, 69, 69)
                            .addComponent(txtAnoClasse))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtIdentificadorClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdentificadorClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAnoClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel8.setText("Classe:");

        cmbClasse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ano da Classe", "Período", "Professor", "Todas" }));
        cmbClasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbClasseItemStateChanged(evt);
            }
        });
        cmbClasse.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbClasseCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        tblClasse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id da Classe", "Ano", "Período", "Turma", "Professor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClasse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClasseMouseClicked(evt);
            }
        });
        tblClasse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClasseKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblClasse);
        if (tblClasse.getColumnModel().getColumnCount() > 0) {
            tblClasse.getColumnModel().getColumn(0).setResizable(false);
            tblClasse.getColumnModel().getColumn(1).setResizable(false);
            tblClasse.getColumnModel().getColumn(2).setResizable(false);
            tblClasse.getColumnModel().getColumn(3).setResizable(false);
            tblClasse.getColumnModel().getColumn(4).setResizable(false);
        }

        btnSalvar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDesativar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnDesativar.setText("Ativar/Desativar");
        btnDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesativarActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        txtFiltroClasse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFiltroClasseKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroClasseKeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton1.setText("Associar Aluno Classe");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDesativar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFiltroClasse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFiltrar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar)
                    .addComponent(txtFiltroClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDesativar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String mensagem = validacaoCampos();
        String exception = "";
        if (!"Favor preencher o(s) seguinte(s) campo(s):\n".equals(mensagem)) {
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            try {
                classeDAO.create(instanciaClasse(1));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FrmCadastroClasse.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                exception = ex.toString();
                String loginexistente = exception;
                if (exception.equals(loginexistente)) {
                    JOptionPane.showMessageDialog(null, "Uma classe com este nome já existe!");
                }
            } finally {
                if (exception.equals("")) {
                    JOptionPane.showMessageDialog(null, "Classe cadastrada com sucesso!");
                    limpaCampos();
                    preencheTabela(classeDAO.findClasseEntities());
                } else {
                    limpaCampos();
                }
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private String validacaoCampos() {
        String idclasse = txtIdentificadorClasse.getText();
        String ano = txtAnoClasse.getText();
        String turma = txtTurma.getText();

        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";

        if ("".equals(idclasse)) {
            mensagem = mensagem + " Identificador de Classe;\n";
        }
        if ("".equals(ano)) {
            mensagem = mensagem + " Ano da Classe;\n";
        }
        if ("".equals(turma)) {
            mensagem = mensagem + " Turma;\n";
        }
        return mensagem;
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String mensagem = validacaoCampos();
        String exception = "";
        if (!"Favor preencher o(s) seguinte(s) campo(s):\n".equals(mensagem)) {
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            try {
                classeDAO.edit(instanciaClasse(2));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FrmCadastroClasse.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                exception = ex.toString();
                String loginexistente = "Controller.exceptions.PreexistingEntityException: Classe Beans.Classe[ idclasse=" + txtIdentificadorClasse.getText() + " ] already exists.";
                if (exception.equals(loginexistente)) {
                    JOptionPane.showMessageDialog(null, "Uma classe com este nome já existe!");
                }
            } finally {
                if (exception.equals("")) {
                    JOptionPane.showMessageDialog(null, "Classe editada com sucesso!");
                    limpaCampos();
                    preencheTabela(classeDAO.findClasseEntities());
                } else {
                    limpaCampos();
                }
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesativarActionPerformed
        if (txtIdentificadorClasse.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nenhuma classe selecionada para poder ativar/desativar!");
        } else {
            Classe edit = new Classe();
            int dialogResult;
            dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja ativar/desativar esta classe?", "Aviso!", 1);
            if (dialogResult == JOptionPane.YES_OPTION) {
                if (txtStatus.getText().equals("Ativado")) {
                    try {
                        edit = classeDAO.findClasse(txtIdentificadorClasse.getText());
                        edit.setStatus(false);
                        classeDAO.edit(edit);
                    } catch (Exception ex) {
                        Logger.getLogger(FrmCadastroGeral.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Classe desativada com sucesso!");
                    limpaCampos();
                    preencheTabela(classeDAO.findClasseEntities());
                } else {
                    try {
                        edit = classeDAO.findClasse(txtIdentificadorClasse.getText());
                        edit.setStatus(true);
                        classeDAO.edit(edit);
                    } catch (Exception ex) {
                        Logger.getLogger(FrmCadastroGeral.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Classe ativada com sucesso!");
                    limpaCampos();
                    preencheTabela(classeDAO.findClasseEntities());
                }
            }
        }
    }//GEN-LAST:event_btnDesativarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtIdentificadorClasseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorClasseKeyPressed
     String identificador = txtIdentificadorClasse.getText();
        int quantosCaracteres = identificador.length();
        if (quantosCaracteres > 14) {
            identificador = identificador.substring(0, identificador.length() - 1);
            txtIdentificadorClasse.setText(identificador);
        }
    }//GEN-LAST:event_txtIdentificadorClasseKeyPressed

    private void tblClasseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClasseMouseClicked
        preencheCampos();
    }//GEN-LAST:event_tblClasseMouseClicked

    private void tblClasseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClasseKeyReleased
        preencheCampos();
    }//GEN-LAST:event_tblClasseKeyReleased

    private void cmbClasseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbClasseItemStateChanged
        this.filtro = cmbClasse.getSelectedIndex();
        txtFiltroClasse.setText("");
    }//GEN-LAST:event_cmbClasseItemStateChanged

    private void cmbClasseCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbClasseCaretPositionChanged

    }//GEN-LAST:event_cmbClasseCaretPositionChanged

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        switch (filtro) {
            case 0:
                List<Classe> lista = this.classeDAO.getClasseByAno(Integer.parseInt(txtFiltroClasse.getText()));
                preencheTabela(lista);
                break;
            case 1:
                List<Classe> lista1 = this.classeDAO.getClasseByPeriodo(txtFiltroClasse.getText());
                preencheTabela(lista1);
                break;
            case 2:
                List<Classe> lista2 = this.classeDAO.getClasseByProfessor(txtFiltroClasse.getText());
                preencheTabela(lista2);
                break;
            case 3:
                preencheTabela(classeDAO.findClasseEntities());
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtAnoClasseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnoClasseKeyTyped
        // TODO add your handling code here:
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ;.,{}[]?!@#$%+_-\\|//&*()<>";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAnoClasseKeyTyped

    private void txtIdentificadorClasseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorClasseKeyTyped
        // TODO add your handling code here:
        String caracteres = "0987654321;.,{}[]?!@#$%+_-\\|//&*()<>";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdentificadorClasseKeyTyped

    private void txtTurmaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurmaKeyTyped
        // TODO add your handling code here:
        String caracteres = "0987654321;.,{}[]?!@#$%+_-\\|//&*()<>";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTurmaKeyTyped

    private void txtFiltroClasseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroClasseKeyTyped
        // TODO add your handling code here:
        String caracteres = "0987654321;.,{}[]?!@#$%+_-\\|//&*()<>";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFiltroClasseKeyTyped

    private void txtAnoClasseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnoClasseKeyPressed
        // TODO add your handling code here:
        String ano = txtAnoClasse.getText();
        int quantosCaracteres = ano.length();
        if (quantosCaracteres > 3) {
            ano = ano.substring(0, ano.length() - 1);
            txtAnoClasse.setText(ano);
        }
    }//GEN-LAST:event_txtAnoClasseKeyPressed

    private void txtTurmaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurmaKeyPressed
        // TODO add your handling code here:
        String turma = txtTurma.getText();
        int quantosCaracteres = turma.length();
        if (quantosCaracteres > 4) {
            turma = turma.substring(0, turma.length() - 1);
            txtTurma.setText(turma);
    }//GEN-LAST:event_txtTurmaKeyPressed
    }
    private void txtFiltroClasseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroClasseKeyPressed
        // TODO add your handling code here:
            String filtro = txtFiltroClasse.getText();
        int quantosCaracteres = filtro.length();
        if (quantosCaracteres > 49) {
            filtro = filtro.substring(0, filtro.length() - 1);
            txtFiltroClasse.setText(filtro);
    } 
    }//GEN-LAST:event_txtFiltroClasseKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FrmAlunoClasse fac = new FrmAlunoClasse();
        fac.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCadastroClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroClasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastroClasse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDesativar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbClasse;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbProfessor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblClasse;
    private javax.swing.JTextField txtAnoClasse;
    private javax.swing.JFormattedTextField txtFiltroClasse;
    private javax.swing.JFormattedTextField txtIdentificadorClasse;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JFormattedTextField txtTurma;
    // End of variables declaration//GEN-END:variables

    private void limpaCampos() {
        String clear = "";
        txtIdentificadorClasse.setText(clear);
        txtAnoClasse.setText(clear);
        txtTurma.setText(clear);
        txtStatus.setText(clear);
        cmbPeriodo.setSelectedIndex(0);
        cmbProfessor.setSelectedIndex(0);
    }

    private Classe instanciaClasse(int par) {
        Classe newclasse = new Classe();
        if (par == 1) {
            newclasse.setIdclasse(txtIdentificadorClasse.getText());
            newclasse.setAnoclasse(Integer.parseInt(txtAnoClasse.getText()));
            newclasse.setPeriodo(cmbPeriodo.getSelectedItem().toString());
            newclasse.setTurma(txtTurma.getText());
            newclasse.setProfessor((Usuario) cmbProfessor.getSelectedItem());
            newclasse.setStatus(true);
        } else {
            newclasse = classeDAO.findClasse(txtIdentificadorClasse.getText());
            newclasse.setAnoclasse(Integer.parseInt(txtAnoClasse.getText()));
            newclasse.setPeriodo(cmbPeriodo.getSelectedItem().toString());
            newclasse.setTurma(txtTurma.getText());
            newclasse.setProfessor((Usuario) cmbProfessor.getSelectedItem());
            if (txtStatus.getText().equals("Ativado")) {
                newclasse.setStatus(true);
            } else {
                newclasse.setStatus(false);
            }
        }
        return newclasse;
    }

    private void preencheTabela(List<Classe> lista) {
        if (lista.size() >= 0) {
            DefaultTableModel tabelaClasse = (DefaultTableModel) tblClasse.getModel();
            tabelaClasse.setNumRows(0);
            for (Classe c : lista) {
                Object[] obj = new Object[]{
                    c.getIdclasse(),
                    c.getAnoclasse(),
                    c.getPeriodo(),
                    c.getTurma(),
                    c.getProfessor()
                };
                tabelaClasse.addRow(obj);
            }
        }
    }

    private void preencheCampos() {
        int linhaSelecionada = tblClasse.getSelectedRow();
        if (linhaSelecionada != -1) {
            String idclasse = tblClasse.getValueAt(linhaSelecionada, 0).toString();
            Classe c = classeDAO.findClasse(idclasse);
            txtIdentificadorClasse.setText(c.getIdclasse());
            txtTurma.setText(c.getTurma());
            txtAnoClasse.setText(String.valueOf(c.getAnoclasse()));
            if (c.getStatus() == true) {
                txtStatus.setText("Ativado");
            } else {
                txtStatus.setText("Desativado");
            }
            cmbPeriodo.setSelectedItem(c.getPeriodo());
            cmbProfessor.setSelectedItem(c.getProfessor());
        }
    }
}
