/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Aluno;
import Beans.Classe;
import Controller.AlunoJpaController;
import javax.persistence.Persistence;
import Controller.ClasseJpaController;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmCadastroAluno extends javax.swing.JFrame {

    /**
     * Creates new form FrmCadastroAluno
     */
    private final AlunoJpaController alunoDAO;
    private final ClasseJpaController classeDAO;

    /**
     * Creates new form FrmCadastroAluno
     */
    public FrmCadastroAluno() {
        initComponents();
        txtStatus.setEnabled(false);
        alunoDAO = new AlunoJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        classeDAO = new ClasseJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        preencherCmbClasse();
        txtDataNascimento.setDateFormatString("dd/MM/yyyy");
        preencheTabela(alunoDAO.findAlunoEntities());
    }

    public Aluno instanciaAluno(int n) {
        Aluno al = new Aluno();
        if (n == 1) {
            al.setMatricula(txtMatricula.getText());
            al.setNomealuno(txtNome.getText());
            al.setNascimento(txtDataNascimento.getDate());
            al.setMae(txtMae.getText());
            al.setPai(txtPai.getText());
            al.setEndereco(txtEndereco.getText());
            al.setBairro(txtBairro.getText());
            al.setNumero(txtNumero.getText());
            al.setCidade(txtCidade.getText());
            al.setUf(cmbUf.getSelectedItem().toString());
            al.setComplemento(txtComplemento.getText());
            al.setTelefone1(txtTelefone1.getText());
            al.setTelefone2(txtTelefone2.getText());
            al.setTelefone3(txtTelefone3.getText());
            al.setClasseIdclasse((Classe) cmbClasse.getSelectedItem());
            al.setStatus(true);
        } else {
            al.setMatricula(txtMatricula.getText());
            al.setNomealuno(txtNome.getText());
            al.setNascimento(txtDataNascimento.getDate());
            al.setMae(txtMae.getText());
            al.setPai(txtPai.getText());
            al.setEndereco(txtEndereco.getText());
            al.setBairro(txtBairro.getText());
            al.setNumero(txtNumero.getText());
            al.setCidade(txtCidade.getText());
            al.setUf(cmbUf.getSelectedItem().toString());
            al.setComplemento(txtComplemento.getText());
            al.setTelefone1(txtTelefone1.getText());
            al.setTelefone2(txtTelefone2.getText());
            al.setTelefone3(txtTelefone3.getText());
            al.setClasseIdclasse((Classe) cmbClasse.getSelectedItem());
            al.setStatus(false);
        }

        return al;
    }

    private String validacaoCampos() {
        String matricula = txtMatricula.getText();
        String nome = txtNome.getText();
        String data = txtDataNascimento.getDateFormatString();
        String mae = txtMae.getText();
        String pai = txtPai.getText();
        String endereco = txtEndereco.getText();
        String numero = txtNumero.getText();
        String bairro = txtBairro.getText();
        String cidade = txtCidade.getText();
        String telefone1 = txtTelefone1.getText();

        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";

        if ("".equals(nome)) {
            mensagem = mensagem + " Nome;\n";
        }
        if ("".equals(matricula)) {
            mensagem = mensagem + " Matricula;\n";
        }
        if ("".equals(data)) {
            mensagem = mensagem + " Data Nascimento;\n";
        }
        if ("".equals(mae)) {
            mensagem = mensagem + " Mãe;\n";
        }
        if ("".equals(pai)) {
            mensagem = mensagem + " Pai;\n";
        }
        if ("".equals(endereco)) {
            mensagem = mensagem + " Endereço;\n";
        }
        if ("".equals(bairro)) {
            mensagem = mensagem + " Bairro;\n";
        }
        if ("".equals(cidade)) {
            mensagem = mensagem + " Cidade;\n";
        }
        if ("".equals(numero)) {
            mensagem = mensagem + " Numero;\n";
        }
        if ("".equals(telefone1)) {
            mensagem = mensagem + " Telefone;\n";
        }
        return mensagem;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMae = new javax.swing.JTextField();
        txtPai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cmbUf = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtTelefone1 = new javax.swing.JTextField();
        txtTelefone2 = new javax.swing.JTextField();
        txtTelefone3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cmbClasse = new javax.swing.JComboBox();
        txtDataNascimento = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        txtNomeAluno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAluno = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesativar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Cadastro de Aluno");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Dados Pessoais do Aluno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Matrícula:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(15, 30, 58, 16);
        jPanel1.add(txtMatricula);
        txtMatricula.setBounds(120, 29, 138, 20);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Nome:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(15, 56, 39, 16);
        jPanel1.add(txtNome);
        txtNome.setBounds(120, 55, 381, 20);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Data Nasc.:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(511, 56, 66, 16);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Filiação");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(15, 86, 47, 16);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setText("Mãe:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(15, 112, 30, 16);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setText("Pai:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(15, 138, 21, 16);
        jPanel1.add(txtMae);
        txtMae.setBounds(120, 111, 391, 20);
        jPanel1.add(txtPai);
        txtPai.setBounds(120, 137, 391, 20);

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setText("Endereço:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(15, 181, 60, 16);
        jPanel1.add(txtEndereco);
        txtEndereco.setBounds(120, 180, 391, 20);

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setText("Número:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(537, 181, 50, 16);
        jPanel1.add(txtNumero);
        txtNumero.setBounds(605, 180, 51, 20);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setText("Bairro:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(15, 207, 37, 16);
        jPanel1.add(txtBairro);
        txtBairro.setBounds(120, 206, 173, 20);

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel11.setText("Cidade:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(338, 207, 47, 16);
        jPanel1.add(txtCidade);
        txtCidade.setBounds(403, 206, 135, 20);

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel12.setText("UF:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(570, 207, 17, 16);

        cmbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
        jPanel1.add(cmbUf);
        cmbUf.setBounds(605, 206, 51, 20);

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel13.setText("Telefones para Contato:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(15, 264, 139, 16);
        jPanel1.add(txtTelefone1);
        txtTelefone1.setBounds(172, 263, 189, 20);
        jPanel1.add(txtTelefone2);
        txtTelefone2.setBounds(172, 289, 189, 20);
        jPanel1.add(txtTelefone3);
        txtTelefone3.setBounds(172, 315, 189, 20);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel14.setText("Classe do Aluno:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(394, 264, 97, 16);

        jPanel1.add(cmbClasse);
        cmbClasse.setBounds(509, 263, 103, 20);
        jPanel1.add(txtDataNascimento);
        txtDataNascimento.setBounds(581, 55, 125, 20);

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setText("Status:");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(456, 306, 35, 16);
        jPanel1.add(txtStatus);
        txtStatus.setBounds(509, 305, 103, 20);

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setText("Complemento:");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(15, 238, 87, 16);
        jPanel1.add(txtComplemento);
        txtComplemento.setBounds(120, 237, 173, 20);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 48, 710, 380));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 710, 10));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setText("Nome do Aluno");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));
        getContentPane().add(txtNomeAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 481, -1));

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, -1, -1));

        tblAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Nome", "Classe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAluno);
        if (tblAluno.getColumnModel().getColumnCount() > 0) {
            tblAluno.getColumnModel().getColumn(0).setResizable(false);
            tblAluno.getColumnModel().getColumn(1).setResizable(false);
            tblAluno.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 650, 130));

        btnSalvar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 650, 89, 42));

        btnEditar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 650, 88, 42));

        btnDesativar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnDesativar.setText("Desativar");
        btnDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesativarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDesativar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 650, -1, 42));

        btnSair.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 650, 87, 42));

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
                alunoDAO.create(instanciaAluno(1));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FrmCadastroAluno.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                exception = ex.toString();
                String alunoexistente = "Controller.exceptions.PreexistingEntityException: Aluno Beans.Aluno[ matricula=" + txtMatricula.getText() + " ] already exists.";
                if (exception.equals(alunoexistente)) {
                    JOptionPane.showMessageDialog(null, "Um Aluno com esta matricula já existe!");
                }
            } finally {
                if (exception.equals("")) {
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
                    limpaCampos();
                    preencheTabela(alunoDAO.findAlunoEntities());
                } else {
                    limpaCampos();
                }
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void preencheTabela(List<Aluno> lista) {
        if (lista.size() >= 0) {
            DefaultTableModel tabelaAluno = (DefaultTableModel) tblAluno.getModel();
            tabelaAluno.setNumRows(0);
            for (Aluno a : lista) {
                Object[] obj = new Object[]{                    
                    a.getMatricula(),
                    a.getNomealuno(),
                    a.getClasseIdclasse()
                };
                tabelaAluno.addRow(obj);
            }
        }
    }
    
    private void preencherCmbClasse() {
        List<Classe> lista = classeDAO.findClasseEntities();
        if (lista.size() > 0) {
            for (Classe c : lista) {
                cmbClasse.addItem(c);
            }
        }
    }
    
    private void limpaCampos() {
        String clear = "";
        txtNome.setText(clear);
        txtMatricula.setText(clear);
        txtMae.setText(clear);
        txtPai.setText(clear);
        txtEndereco.setText(clear);
        txtBairro.setText(clear);
        txtCidade.setText(clear);
        txtNumero.setText(clear);
        txtTelefone1.setText(clear);
        txtTelefone2.setText(clear);
        txtTelefone3.setText(clear);
        cmbUf.setSelectedIndex(0);
        cmbClasse.setSelectedIndex(0);
        txtStatus.setText(clear);
        txtComplemento.setText(clear);
        txtDataNascimento.cleanup();
        txtDataNascimento.setDateFormatString("dd/MM/yyyy");
        
    }

    private void preencheCampos() {
        int linhaSelecionada = tblAluno.getSelectedRow();
        if (linhaSelecionada != -1) {
            int alunon = Integer.parseInt(tblAluno.getValueAt(linhaSelecionada, 0).toString());
            Aluno a = alunoDAO.findAluno(Integer.toString(alunon));
            txtNome.setText(a.getNomealuno());
            txtMatricula.setText(a.getMatricula());
            txtDataNascimento.setDate(a.getNascimento());
            txtMae.setText(a.getMae());
            txtPai.setText(a.getPai());
            txtEndereco.setText(a.getEndereco());
            txtNumero.setText(a.getNumero());
            txtBairro.setText(a.getBairro());
            txtCidade.setText(a.getCidade());
            txtTelefone1.setText(a.getTelefone1());
            txtTelefone2.setText(a.getTelefone2());
            txtTelefone3.setText(a.getTelefone3());
            txtComplemento.setText(a.getComplemento());
            if (a.getStatus() == true) {
                txtStatus.setText("Ativado");
                btnDesativar.setEnabled(true);
            } else {
                txtStatus.setText("Desativado");
                btnDesativar.setEnabled(false);
            }

            cmbClasse.setSelectedItem(a.getClasseIdclasse());
            cmbUf.setSelectedItem(a.getUf());
        }
    }


    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String mensagem = validacaoCampos();
        String exception = "";
        if (!"Favor preencher o(s) seguinte(s) campo(s):\n".equals(mensagem)) {
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            try {
                alunoDAO.edit(instanciaAluno(1));
            } catch (Exception ex) {
                exception = ex.toString();
                String matriculaExistente = "Beans.exceptions.PreexistingEntityException: Aluno Controller.Aluno[ matricula=" + txtMatricula.getText() + " ] already exists.";
                if (exception.equals(matriculaExistente)) {
                    JOptionPane.showMessageDialog(null, "Um aluno com esta matrícula já existe!");
                }
            } finally {
                if (exception.equals("")) {
                    JOptionPane.showMessageDialog(null, "Aluno editado com sucesso!");
                    limpaCampos();
                    preencheTabela(alunoDAO.findAlunoEntities());
                } else {
                    limpaCampos();
                }
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesativarActionPerformed
        if (txtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nenhuma matrícula selecionada para poder desativar!");
        } else {
            int dialogResult;
            dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja desativar este Aluno?", "Aviso!", 1);
            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    alunoDAO.edit(instanciaAluno(2));
                               
                } catch (Exception ex) {
                    Logger.getLogger(FrmCadastroAluno.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    JOptionPane.showMessageDialog(null, "Aluno desativado com sucesso!");
                    btnDesativar.setEnabled(false);
                    limpaCampos();
                    preencheTabela(alunoDAO.findAlunoEntities());
                }
            }
        }
    }//GEN-LAST:event_btnDesativarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        fecharJanela();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunoMouseClicked
        // TODO add your handling code here:
        preencheCampos();
    }//GEN-LAST:event_tblAlunoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<Aluno> lista = this.alunoDAO.getAlunoByNomeLike(txtNomeAluno.getText());
        preencheTabela(lista);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fecharJanela() {
        super.dispose();
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
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastroAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDesativar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cmbClasse;
    private javax.swing.JComboBox<String> cmbUf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblAluno;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private com.toedter.calendar.JDateChooser txtDataNascimento;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtMae;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeAluno;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPai;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTelefone1;
    private javax.swing.JTextField txtTelefone2;
    private javax.swing.JTextField txtTelefone3;
    // End of variables declaration//GEN-END:variables
}
