/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.DiasemanaHasEstrategia;
import Beans.Planoaula;
import Beans.Usuario;
import Controller.DiasemanaHasEstrategiaJpaController;
import Controller.PlanoaulaJpaController;
import Controller.UsuarioJpaController;
import java.util.ArrayList;
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
public class FrmAprovarPlanoAula extends javax.swing.JFrame {

    public Planoaula plano;
    public Usuario prof;
    private final UsuarioJpaController usuarioDAO;
    private final PlanoaulaJpaController planoDAO;
    private final DiasemanaHasEstrategiaJpaController cruzamentoDAO;
    Planoaula pa;

    public FrmAprovarPlanoAula(Usuario user, Planoaula planoaula2) {
        initComponents();
        planoDAO = new PlanoaulaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        usuarioDAO = new UsuarioJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        cruzamentoDAO = new DiasemanaHasEstrategiaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        prof = user;
        plano = planoaula2;
        if (recuperarSegunda() == false) {
            recuperarTerca();
            recuperarQuarta();
            recuperarQuinta();
            recuperarSexta();
        }
        desabilitarCampos();
    }

    private FrmAprovarPlanoAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       private void desabilitarCampos(){
    txtDataInicio.setEnabled(false);
    txtDataFinal.setEnabled(false);
    txtProfessr.setEnabled(false);
    txtClasse.setEnabled(false);
    txtClasse.setEnabled(false);
    txtPrincipalObjetivo.setEnabled(false);
    txtPrincipalObjetivo1.setEnabled(false);
    txtPrincipalObjetivo2.setEnabled(false);
    txtPrincipalObjetivo3.setEnabled(false);
    txtPrincipalObjetivo4.setEnabled(false);
    txtAcolhidaAlunos.setEnabled(false);
    txtAcolhidaAlunos1.setEnabled(false);
    txtAcolhidaAlunos2.setEnabled(false);
    txtAcolhidaAlunos3.setEnabled(false);
    txtAcolhidaAlunos4.setEnabled(false);
    txtAnexo.setEditable(false);
    txtAnexo1.setEditable(false);
    txtAnexo2.setEditable(false);
    txtAnexo3.setEditable(false);
    txtAnexo4.setEditable(false);
    txtObservacoes.setEnabled(false);
    txtObservacoes1.setEnabled(false);
    txtObservacoes2.setEnabled(false);
    txtObservacoes3.setEnabled(false);
    txtObservacoes4.setEnabled(false);
    txtTurma.setEnabled(false);
}
    private Boolean recuperarSegunda() {
        Boolean vazio = false;
        int stratadd = 0;
        //Buscar Planoaula em Diasemana_has_estrategia
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.plano);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblEstrategia.getModel();
        if (listasemanal.isEmpty()) {
            vazio = true;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Segunda-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    if (stratadd == 0) {
                        txtDataInicio.setDate(ds.getPlanoaula().getDatainicio());
                        txtDataFinal.setDate(ds.getPlanoaula().getDatafim());
                        txtProfessr.setText(ds.getPlanoaula().getUsuarioLogin().getNome());
                        txtClasse.setText(ds.getPlanoaula().getClasseIdclasse().getIdclasse());
                        txtTurma.setText(ds.getPlanoaula().getClasseIdclasse().getTurma());
                        txtPrincipalObjetivo.setText(ds.getDiasemana().getPrincipalObj());
                        txtAcolhidaAlunos.setText(ds.getDiasemana().getAcolhida());
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                        txtAnexo.setText(ds.getDiasemana().getAnexos());

                        txtObservacoes.setText(ds.getDiasemana().getObservacoes());
                        stratadd++;
                    } else {
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                    }
                }

            }
        }
        return vazio;
    }

    private Boolean recuperarTerca() {
        Boolean vazio = false;
        int stratadd = 0;
        //Buscar Planoaula em Diasemana_has_estrategia
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.plano);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblEstrategia2.getModel();
        if (listasemanal.isEmpty()) {
            vazio = true;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Terça-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    if (stratadd == 0) {
                        txtPrincipalObjetivo2.setText(ds.getDiasemana().getPrincipalObj());
                        txtAcolhidaAlunos2.setText(ds.getDiasemana().getAcolhida());
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                        txtAnexo2.setText(ds.getDiasemana().getAnexos());

                        txtObservacoes2.setText(ds.getDiasemana().getObservacoes());
                        stratadd++;
                    } else {
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                    }
                }

            }
        }
        return vazio;
    }

    private Boolean recuperarQuarta() {
        Boolean vazio = false;
        int stratadd = 0;
        //Buscar Planoaula em Diasemana_has_estrategia
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.plano);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblEstrategia3.getModel();
        if (listasemanal.isEmpty()) {
            vazio = true;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Quarta-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    if (stratadd == 0) {
                        txtPrincipalObjetivo3.setText(ds.getDiasemana().getPrincipalObj());
                        txtAcolhidaAlunos3.setText(ds.getDiasemana().getAcolhida());
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                        txtAnexo3.setText(ds.getDiasemana().getAnexos());

                        txtObservacoes3.setText(ds.getDiasemana().getObservacoes());
                        stratadd++;
                    } else {
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                    }
                }

            }
        }
        return vazio;
    }

    private Boolean recuperarQuinta() {
        Boolean vazio = false;
        int stratadd = 0;
        //Buscar Planoaula em Diasemana_has_estrategia
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.plano);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblEstrategia4.getModel();
        if (listasemanal.isEmpty()) {
            vazio = true;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Quinta-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    if (stratadd == 0) {
                        txtPrincipalObjetivo4.setText(ds.getDiasemana().getPrincipalObj());
                        txtAcolhidaAlunos4.setText(ds.getDiasemana().getAcolhida());
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                        txtAnexo4.setText(ds.getDiasemana().getAnexos());

                        txtObservacoes4.setText(ds.getDiasemana().getObservacoes());
                        stratadd++;
                    } else {
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                    }
                }

            }
        }
        return vazio;
    }

    private Boolean recuperarSexta() {
        Boolean vazio = false;
        int stratadd = 0;
        //Buscar Planoaula em Diasemana_has_estrategia
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.plano);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblEstrategia1.getModel();
        if (listasemanal.isEmpty()) {
            vazio = true;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Sexta-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    if (stratadd == 0) {
                        txtPrincipalObjetivo1.setText(ds.getDiasemana().getPrincipalObj());
                        txtAcolhidaAlunos1.setText(ds.getDiasemana().getAcolhida());
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                        txtAnexo1.setText(ds.getDiasemana().getAnexos());

                        txtObservacoes1.setText(ds.getDiasemana().getObservacoes());
                        stratadd++;
                    } else {
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                    }
                }

            }
        }
        return vazio;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCorTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlCorFormulario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDataInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtDataFinal = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtProfessr = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtClasse = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTurma = new javax.swing.JTextField();
        tbpGuias = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtPrincipalObjetivo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAcolhidaAlunos = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstrategia = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtAnexo = new javax.swing.JTextField();
        btnVisualizarAnexo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtPrincipalObjetivo2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtAcolhidaAlunos2 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEstrategia2 = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        txtAnexo2 = new javax.swing.JTextField();
        btnVisualizarAnexo2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtObservacoes2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtPrincipalObjetivo3 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtAcolhidaAlunos3 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblEstrategia3 = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        txtAnexo3 = new javax.swing.JTextField();
        btnVisualizarAnexo3 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtObservacoes3 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtPrincipalObjetivo4 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtAcolhidaAlunos4 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblEstrategia4 = new javax.swing.JTable();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        txtAnexo4 = new javax.swing.JTextField();
        btnVisualizarAnexo4 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtObservacoes4 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtPrincipalObjetivo1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtAcolhidaAlunos1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEstrategia1 = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtAnexo1 = new javax.swing.JTextField();
        btnVisualizarAnexo1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtObservacoes1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObsrvacoesPlano = new javax.swing.JTextArea();
        btnReprovar = new javax.swing.JButton();
        btnAprovar = new javax.swing.JButton();
        btnPdf = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(999, 780));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlCorTitulo.setBackground(new java.awt.Color(16, 37, 63));
        pnlCorTitulo.setForeground(new java.awt.Color(16, 37, 63));

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Aprovação Plano de Aula Semanal");

        javax.swing.GroupLayout pnlCorTituloLayout = new javax.swing.GroupLayout(pnlCorTitulo);
        pnlCorTitulo.setLayout(pnlCorTituloLayout);
        pnlCorTituloLayout.setHorizontalGroup(
            pnlCorTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCorTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCorTituloLayout.setVerticalGroup(
            pnlCorTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCorTituloLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCorFormulario.setBackground(new java.awt.Color(232, 244, 248));

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel2.setText("Bem Vindo(a) Diretor(a):");

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel3.setText("Data Inicio:");

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel4.setText("Data Final:");

        jLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel5.setText("Professor:");

        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel6.setText("Classe:");

        jLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel7.setText("Turma:");

        tbpGuias.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel8.setText("Principal Objetivo do Dia:");

        jLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel9.setText("Acolhida dos Alunos:");

        tblEstrategia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estrátegia, Recursos e Atividades Complementares"
            }
        ));
        jScrollPane1.setViewportView(tblEstrategia);

        jLabel10.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel10.setText("Matrizes (Anexos):");

        btnVisualizarAnexo.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnVisualizarAnexo.setText("Visualizar Anexo");

        jLabel11.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel11.setText("Lição de Casa:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel12.setText("Observações e Reflexões:");

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane2.setViewportView(txtObservacoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAcolhidaAlunos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrincipalObjetivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVisualizarAnexo))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPrincipalObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAcolhidaAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizarAnexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Segunda - Feira", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel19.setText("Principal Objetivo do Dia:");

        jLabel20.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel20.setText("Acolhida dos Alunos:");

        tblEstrategia2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estrátegia, Recursos e Atividades Complementares"
            }
        ));
        jScrollPane6.setViewportView(tblEstrategia2);

        jLabel21.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel21.setText("Matrizes (Anexos):");

        btnVisualizarAnexo2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnVisualizarAnexo2.setText("Visualizar Anexo");

        jLabel22.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel22.setText("Lição de Casa:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel23.setText("Observações e Reflexões:");

        txtObservacoes2.setColumns(20);
        txtObservacoes2.setRows(5);
        jScrollPane7.setViewportView(txtObservacoes2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addComponent(jSeparator5)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAcolhidaAlunos2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrincipalObjetivo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(txtAnexo2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVisualizarAnexo2))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 129, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtPrincipalObjetivo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtAcolhidaAlunos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtAnexo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizarAnexo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Terça - Feira", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel24.setText("Principal Objetivo do Dia:");

        jLabel25.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel25.setText("Acolhida dos Alunos:");

        tblEstrategia3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estrátegia, Recursos e Atividades Complementares"
            }
        ));
        jScrollPane8.setViewportView(tblEstrategia3);

        jLabel26.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel26.setText("Matrizes (Anexos):");

        btnVisualizarAnexo3.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnVisualizarAnexo3.setText("Visualizar Anexo");

        jLabel27.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel27.setText("Lição de Casa:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel28.setText("Observações e Reflexões:");

        txtObservacoes3.setColumns(20);
        txtObservacoes3.setRows(5);
        jScrollPane9.setViewportView(txtObservacoes3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addComponent(jSeparator7)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAcolhidaAlunos3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrincipalObjetivo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(txtAnexo3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVisualizarAnexo3))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 129, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPrincipalObjetivo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtAcolhidaAlunos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtAnexo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizarAnexo3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Quarta - Feira", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel29.setText("Principal Objetivo do Dia:");

        jLabel30.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel30.setText("Acolhida dos Alunos:");

        tblEstrategia4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estrátegia, Recursos e Atividades Complementares"
            }
        ));
        jScrollPane10.setViewportView(tblEstrategia4);

        jLabel31.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel31.setText("Matrizes (Anexos):");

        btnVisualizarAnexo4.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnVisualizarAnexo4.setText("Visualizar Anexo");

        jLabel32.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel32.setText("Lição de Casa:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel33.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel33.setText("Observações e Reflexões:");

        txtObservacoes4.setColumns(20);
        txtObservacoes4.setRows(5);
        jScrollPane11.setViewportView(txtObservacoes4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator10)
            .addComponent(jSeparator9)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAcolhidaAlunos4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrincipalObjetivo4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(txtAnexo4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVisualizarAnexo4))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 129, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtPrincipalObjetivo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtAcolhidaAlunos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtAnexo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizarAnexo4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Quinta - Feira", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel14.setText("Principal Objetivo do Dia:");

        jLabel15.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel15.setText("Acolhida dos Alunos:");

        tblEstrategia1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estrátegia, Recursos e Atividades Complementares"
            }
        ));
        jScrollPane4.setViewportView(tblEstrategia1);

        jLabel16.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel16.setText("Matrizes (Anexos):");

        btnVisualizarAnexo1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnVisualizarAnexo1.setText("Visualizar Anexo");

        jLabel17.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel17.setText("Lição de Casa:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel18.setText("Observações e Reflexões:");

        txtObservacoes1.setColumns(20);
        txtObservacoes1.setRows(5);
        jScrollPane5.setViewportView(txtObservacoes1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addComponent(jSeparator3)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAcolhidaAlunos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrincipalObjetivo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(txtAnexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVisualizarAnexo1))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 129, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPrincipalObjetivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtAcolhidaAlunos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtAnexo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizarAnexo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Sexta - Feira", jPanel5);

        jLabel13.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        jLabel13.setText("Observações:");

        txtObsrvacoesPlano.setColumns(20);
        txtObsrvacoesPlano.setRows(5);
        jScrollPane3.setViewportView(txtObsrvacoesPlano);

        btnReprovar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnReprovar.setText("Reprovar Plano de Aula Semanal");
        btnReprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReprovarActionPerformed(evt);
            }
        });

        btnAprovar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnAprovar.setText("Aprovar Plano de Aula Semanal");
        btnAprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprovarActionPerformed(evt);
            }
        });

        btnPdf.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnPdf.setText("Visualizar PDF");

        btnVoltar.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCorFormularioLayout = new javax.swing.GroupLayout(pnlCorFormulario);
        pnlCorFormulario.setLayout(pnlCorFormularioLayout);
        pnlCorFormularioLayout.setHorizontalGroup(
            pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCorFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tbpGuias, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCorFormularioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(pnlCorFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtProfessr, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18))
            .addGroup(pnlCorFormularioLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCorFormularioLayout.createSequentialGroup()
                        .addComponent(btnReprovar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAprovar)
                        .addGap(18, 18, 18)
                        .addComponent(btnPdf)
                        .addGap(18, 18, 18)
                        .addComponent(btnVoltar)
                        .addGap(119, 119, 119)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCorFormularioLayout.setVerticalGroup(
            pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCorFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCorFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jLabel4)
                    .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProfessr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(tbpGuias, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCorFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReprovar)
                    .addComponent(btnAprovar)
                    .addComponent(btnPdf)
                    .addComponent(btnVoltar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCorTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCorFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCorTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCorFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        setSize(new java.awt.Dimension(1115, 781));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReprovarActionPerformed
        // TODO add your handling code here:
        Planoaula edit = new Planoaula();
            int dialogResult;
            dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja Reprovar?", "Aviso!", 1);
            if (dialogResult == JOptionPane.YES_OPTION) {
                if (plano.getStatus().equals("Em Aprovação")) {
                    try {
                        edit = planoDAO.findPlanoaula(plano.getIdplanoaula());
                        edit.setObservacao(txtObsrvacoesPlano.getText());
                        edit.setStatus("Reprovado");
                        planoDAO.edit(edit);
                        this.dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(FrmAprovarPlanoAula.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Plano Reprovado!");
                }
            }
    }//GEN-LAST:event_btnReprovarActionPerformed

    private void btnAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprovarActionPerformed
        // TODO add your handling code here:
        Planoaula edit = new Planoaula();
            int dialogResult;
            dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja Aprovar?", "Aviso!", 1);
            if (dialogResult == JOptionPane.YES_OPTION) {
                if (plano.getStatus().equals("Em Aprovação")) {
                    try {
                        edit = planoDAO.findPlanoaula(plano.getIdplanoaula());
                        edit.setStatus("Aprovado");
                        planoDAO.edit(edit);
                        this.dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(FrmAprovarPlanoAula.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Plano Aprovado!");
                }
            }
    }//GEN-LAST:event_btnAprovarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FrmAprovarPlanoAula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAprovarPlanoAula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAprovarPlanoAula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAprovarPlanoAula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAprovarPlanoAula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprovar;
    private javax.swing.JButton btnPdf;
    private javax.swing.JButton btnReprovar;
    private javax.swing.JButton btnVisualizarAnexo;
    private javax.swing.JButton btnVisualizarAnexo1;
    private javax.swing.JButton btnVisualizarAnexo2;
    private javax.swing.JButton btnVisualizarAnexo3;
    private javax.swing.JButton btnVisualizarAnexo4;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel pnlCorFormulario;
    private javax.swing.JPanel pnlCorTitulo;
    private javax.swing.JTable tblEstrategia;
    private javax.swing.JTable tblEstrategia1;
    private javax.swing.JTable tblEstrategia2;
    private javax.swing.JTable tblEstrategia3;
    private javax.swing.JTable tblEstrategia4;
    private javax.swing.JTabbedPane tbpGuias;
    private javax.swing.JTextField txtAcolhidaAlunos;
    private javax.swing.JTextField txtAcolhidaAlunos1;
    private javax.swing.JTextField txtAcolhidaAlunos2;
    private javax.swing.JTextField txtAcolhidaAlunos3;
    private javax.swing.JTextField txtAcolhidaAlunos4;
    private javax.swing.JTextField txtAnexo;
    private javax.swing.JTextField txtAnexo1;
    private javax.swing.JTextField txtAnexo2;
    private javax.swing.JTextField txtAnexo3;
    private javax.swing.JTextField txtAnexo4;
    private javax.swing.JTextField txtClasse;
    private com.toedter.calendar.JDateChooser txtDataFinal;
    private com.toedter.calendar.JDateChooser txtDataInicio;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextArea txtObservacoes1;
    private javax.swing.JTextArea txtObservacoes2;
    private javax.swing.JTextArea txtObservacoes3;
    private javax.swing.JTextArea txtObservacoes4;
    private javax.swing.JTextArea txtObsrvacoesPlano;
    private javax.swing.JTextField txtPrincipalObjetivo;
    private javax.swing.JTextField txtPrincipalObjetivo1;
    private javax.swing.JTextField txtPrincipalObjetivo2;
    private javax.swing.JTextField txtPrincipalObjetivo3;
    private javax.swing.JTextField txtPrincipalObjetivo4;
    private javax.swing.JTextField txtProfessr;
    private javax.swing.JTextField txtTurma;
    // End of variables declaration//GEN-END:variables
}
