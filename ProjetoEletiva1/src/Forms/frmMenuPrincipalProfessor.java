/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Avaliacao;
import Beans.Categoria;
import Beans.Classe;
import Beans.Planoaula;
import Beans.Usuario;
import Controller.AvaliacaoJpaController;
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
    public Avaliacao avaliacao;
    private final PlanoaulaJpaController planoDAO;
    private final ClasseJpaController classeDAO;
    private final CategoriaJpaController categoriaDAO;
    private final AvaliacaoJpaController avaliacaoDAO;

    public frmMenuPrincipalProfessor(Usuario user2) {
        initComponents();
        this.user = user2;
        mudaLabel(user2.getNome());
        planoDAO = new PlanoaulaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        classeDAO = new ClasseJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        categoriaDAO = new CategoriaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        avaliacaoDAO = new AvaliacaoJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        carregaTabelaPlanoAula(planoDAO.findPlanoaulaEntities());
        carregaTabelaAvaliacao(avaliacaoDAO.findAvaliacaoEntities());
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
                    if (!p.getStatus().equals("Em Aprovação") && !p.getStatus().equals("Aprovado")) {
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
    
    private void carregaTabelaAvaliacao(List<Avaliacao> lista) {
        if (lista.size() >= 0) {
            DefaultTableModel tabelaAvaliacao = (DefaultTableModel) tblAvaliacao.getModel();
            tabelaAvaliacao.setNumRows(0);
            for (Avaliacao a : lista) {
                if (a.getUsuarioLogin().getLogin().equals(user.getLogin())) {
                    if (!a.getStatus().equals("Em Aprovação") && !a.getStatus().equals("Aprovado")) {
                                  }
                        Object[] obj = new Object[]{
                            a,
                            a.getTipo(),
                            a.getAreaconhecimentoIdareaconhecimento().getAreaconhecimento()                            
                        };
                        tabelaAvaliacao.addRow(obj);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlanoAula = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAvaliacaoAtividadesContinuos = new javax.swing.JButton();
        btnAvaliacao = new javax.swing.JButton();
        btnPlanoAulaSemanal = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacaoAvaliacao = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAvaliacao = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário do Professor");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(16, 37, 63));

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Menu Principal  Professor");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(232, 244, 248));

        jLabel2.setText("Bem Vindo(a), Professor(a):");

        btnAvaliacaoAtividadesContinuos.setText("Avaliação de Atividades Contínuos");

        btnAvaliacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Criar Plano de Aula.png"))); // NOI18N
        btnAvaliacao.setText("Avaliação");
        btnAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvaliacaoActionPerformed(evt);
            }
        });

        btnPlanoAulaSemanal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Criar Plano de Aula.png"))); // NOI18N
        btnPlanoAulaSemanal.setText("Plano de Aula Semanal");
        btnPlanoAulaSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanoAulaSemanalActionPerformed(evt);
            }
        });

        btnInicio.setText("Início");
        btnInicio.setPreferredSize(new java.awt.Dimension(197, 23));

        jCalendar2.setWeekOfYearVisible(false);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(169, 169, 169)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPlanoAulaSemanal, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAvaliacaoAtividadesContinuos, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jCalendar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnPlanoAulaSemanal)
                .addGap(7, 7, 7)
                .addComponent(btnAvaliacao)
                .addGap(7, 7, 7)
                .addComponent(btnAvaliacaoAtividadesContinuos)
                .addGap(37, 37, 37)
                .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setText("Observações enviadas.");

        txtObservacaoAvaliacao.setColumns(20);
        txtObservacaoAvaliacao.setRows(5);
        jScrollPane3.setViewportView(txtObservacaoAvaliacao);

        tblAvaliacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Avaliacao", "Tipo", "Área Conhecimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAvaliacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAvaliacaoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblAvaliacao);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

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
            fav = new FrmAvaliacao(user, null);
            fav.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        


    }//GEN-LAST:event_btnAvaliacaoActionPerformed

    private void tblAvaliacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAvaliacaoMouseClicked
        // TODO add your handling code here:
        int linhaselecionada = tblAvaliacao.getSelectedRow();
        if (linhaselecionada != -1) {
            avaliacao = (Avaliacao) tblAvaliacao.getValueAt(linhaselecionada, 0);
            txtObservacaoAvaliacao.setText(avaliacao.getObservacao());
        }
        if (evt.getClickCount() > 1) {
            int row = this.tblAvaliacao.rowAtPoint(evt.getPoint());
            FrmAvaliacao fa;
            try {
                fa = new FrmAvaliacao(user, avaliacao);
                fa.setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(frmMenuPrincipalProfessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_tblAvaliacaoMouseClicked

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblAvaliacao;
    private javax.swing.JTable tblPlanoAula;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextArea txtObservacaoAvaliacao;
    // End of variables declaration//GEN-END:variables
}
