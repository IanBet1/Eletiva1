/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Areaconhecimento;
import Beans.Areaconhecimento_;
import Beans.Avaliacao;
import Beans.Usuario;
import Controller.AreaconhecimentoJpaController;
import Controller.AvaliacaoJpaController;
import Controller.UsuarioJpaController;
import com.sun.glass.events.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static org.eclipse.persistence.jpa.rs.util.JPARSLogger.exception;
import static org.xhtmlrenderer.util.IOUtil.copyFile;

/**
 *
 * @author Terminal
 */
public class FrmAvaliacao extends javax.swing.JFrame {

    private final AreaconhecimentoJpaController areaConhecimentoDAO;
    private final AvaliacaoJpaController avaliacaoDAO;
    private final UsuarioJpaController usuarioDAO;
    Areaconhecimento a;
    public Usuario user;
    public Avaliacao avaliacao;

    /**
     * Creates new form FrmAvaliacao
     */
    public FrmAvaliacao(Usuario user, Avaliacao avaliacao) throws ParseException {
        initComponents();
        areaConhecimentoDAO = new AreaconhecimentoJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        avaliacaoDAO = new AvaliacaoJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        usuarioDAO = new UsuarioJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        this.user = user;
        this.avaliacao = avaliacao;
        lblArquivo.setText("");
        preencherCmbConhecimento();
        if (avaliacao != null) {
            recuperar();           
        }
        carregaTabela(avaliacaoDAO.findAvaliacaoEntities());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAnexo = new javax.swing.JTextField();
        btnAnexar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtFiltrar = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAvaliacao = new javax.swing.JTable();
        btnEnviar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        cmbAreaConhecimento = new javax.swing.JComboBox();
        lblArquivo = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formulário Avaliação");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(16, 37, 67));

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Avaliação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(232, 244, 248));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel2.setText("Tipo:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 14, -1, -1));

        cmbTipo.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mensal", "Bimestral" }));
        jPanel2.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 11, -1, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel3.setText("Área de Conhecimento:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 52, -1, -1));

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel4.setText("Arquivo:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 92, -1, -1));

        txtAnexo.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        txtAnexo.setEnabled(false);
        txtAnexo.setMaximumSize(new java.awt.Dimension(6, 20));
        jPanel2.add(txtAnexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 90, 219, -1));

        btnAnexar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnAnexar.setText("Anexar");
        btnAnexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnexar, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 88, -1, -1));

        jLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel5.setText("Filtrar:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 221, -1, -1));

        txtFiltrar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jPanel2.add(txtFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 219, 219, -1));

        btnFiltrar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Filtrar.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        jPanel2.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 217, -1, -1));

        tblAvaliacao.setAutoCreateRowSorter(true);
        tblAvaliacao.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        tblAvaliacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área Conhecimento", "Aprovação"
            }
        ));
        jScrollPane1.setViewportView(tblAvaliacao);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 251, -1, 135));

        btnEnviar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Enviar Plano de Aula Semanal.png"))); // NOI18N
        btnEnviar.setText("Enviar");
        btnEnviar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEnviar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEnviar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 404, -1, -1));

        btnVoltar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Voltar.png"))); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVoltar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnVoltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        jPanel2.add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 404, -1, -1));

        cmbAreaConhecimento.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jPanel2.add(cmbAreaConhecimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 50, 121, -1));

        lblArquivo.setText("jLabel7");
        jPanel2.add(lblArquivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 117, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(478, 550));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  private void preencherCmbConhecimento() throws ParseException {
        List<Areaconhecimento> lista = areaConhecimentoDAO.findAreaconhecimentoEntities();
        if (lista.size() > 0) {
            cmbAreaConhecimento.removeAllItems();
            for (Areaconhecimento a : lista) {
                cmbAreaConhecimento.addItem(a);
            }
        }
    }

    private void carregaTabela(List<Avaliacao> lista) {
        if (lista.size() >= 0) {
            DefaultTableModel tabelaAvaliacao = (DefaultTableModel) tblAvaliacao.getModel();
            tabelaAvaliacao.setNumRows(0);
            for (Avaliacao av : lista) {
                Object[] obj = new Object[]{
                    av.getAreaconhecimentoIdareaconhecimento(),
                    av.getStatus()
                };
                tabelaAvaliacao.addRow(obj);
            }
        }
    }

    public Avaliacao instanciaAvaliacao() throws Exception {
        Avaliacao av = new Avaliacao();
        //Areaconhecimento ac = new Areaconhecimento();
        av.setTipo(cmbTipo.getSelectedItem().toString());
        av.setArquivo("W:\\"+user.getLogin()+"\\Avaliações\\"+lblArquivo.getText());
        Areaconhecimento ac = (Areaconhecimento) cmbAreaConhecimento.getSelectedItem();
        av.setAreaconhecimentoIdareaconhecimento(ac);
        av.setStatus("Em Aprovação");
        av.setUsuarioLogin(user);
        return av;
    }

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:
        String mensagem = "";
        if ("".equals(txtAnexo.getText())) {
           JOptionPane.showMessageDialog(null, "Por Gentileza escolha um anexo");
        }
        else{
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que enviar avaliação para aprovação?", "Aviso!", 1);
        if (dialogResult == JOptionPane.YES_OPTION) {
            try {
               if (!txtAnexo.getText().equals("")) {
                avaliacaoDAO.create(instanciaAvaliacao());
        
                    File destino, origem;
                    destino = new File("W:\\"+user.getLogin()+"\\Avaliações\\");                    
                    origem = new File(txtAnexo.getText());             

                    
                        copyFile(origem, destino);
                        
                    }
            } catch (Exception ex) {
                Logger.getLogger(FrmAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                JOptionPane.showMessageDialog(null, "Avaliação enviada com sucesso!");
                carregaTabela(avaliacaoDAO.findAvaliacaoEntities());
                super.dispose();
                try {
                    FrmAvaliacao fa = new FrmAvaliacao(user, avaliacao);
                    fa.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(FrmAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        }
         
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnAnexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexo.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexo.setText(arquivo.getPath());
            lblArquivo.setText(arquivo.getName().toString());
            txtAnexo.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Deseja Realmente Voltar", "Aviso", 1);
        if(dialogResult == JOptionPane.YES_OPTION){
            frmMenuPrincipalProfessor fmpp = new frmMenuPrincipalProfessor(user);
            fmpp.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Deseja Realmente voltar?", "Aviso", 1);
        if(dialogResult == JOptionPane.YES_OPTION){
            frmMenuPrincipalProfessor fmpp = new frmMenuPrincipalProfessor(user);
            fmpp.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing
    private Boolean recuperar() {
        Boolean vazio = false;
        int stratadd = 0;
        Avaliacao listasav = avaliacaoDAO.getAvaliacao(this.avaliacao);
        if (listasav == null) {
            vazio = true;
        } else if (stratadd == 0) {
            cmbTipo.setSelectedItem(listasav.getTipo());
            cmbAreaConhecimento.setSelectedItem(listasav.getAreaconhecimentoIdareaconhecimento().getAreaconhecimento());
            txtAnexo.setText(listasav.getArquivo());
        }
        return vazio;
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
            java.util.logging.Logger.getLogger(FrmAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmAvaliacao(null, null).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(FrmAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnexar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox cmbAreaConhecimento;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArquivo;
    private javax.swing.JTable tblAvaliacao;
    private javax.swing.JTextField txtAnexo;
    private javax.swing.JTextField txtFiltrar;
    // End of variables declaration//GEN-END:variables
}
