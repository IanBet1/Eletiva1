/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Beans.Areaconhecimento;
import Beans.Classe;
import Beans.Diasemana;
import Beans.DiasemanaHasEstrategia;
import Beans.DiasemanaHasEstrategiaPK;
import Beans.Estrategia;
import Beans.Planoaula;
import Beans.Usuario;
import Controller.AreaconhecimentoJpaController;
import Controller.ClasseJpaController;
import Controller.DiasemanaHasEstrategiaJpaController;
import Controller.DiasemanaJpaController;
import Controller.EstrategiaJpaController;
import Controller.PlanoaulaJpaController;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static org.eclipse.persistence.jpa.rs.util.JPARSLogger.exception;

/**
 *
 * @author Terminal
 */
public class FrmPlanoAulaSemanal extends javax.swing.JFrame {

    private final AreaconhecimentoJpaController areaConhecimentoDAO;
    private final DiasemanaHasEstrategiaJpaController cruzamentoDAO;
    private final EstrategiaJpaController estrategiaDAO;
    private final DiasemanaJpaController diasemanaDAO;
    private final PlanoaulaJpaController planoaulaDAO;
    private final ClasseJpaController classeDAO;
    boolean editando = false;
    boolean status = true;
    int linhaSelecionada;
    public Usuario user;

    /**
     * Creates new form FrmPlanoAulaSemanal
     *
     * @param login
     */
    String d;
    Planoaula pa;
    Diasemana ds;
    Estrategia es;
    DiasemanaHasEstrategia cruzamento;

    public FrmPlanoAulaSemanal(Usuario login, Planoaula planoaula) throws ParseException {
        initComponents();
        areaConhecimentoDAO = new AreaconhecimentoJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        estrategiaDAO = new EstrategiaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        diasemanaDAO = new DiasemanaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        planoaulaDAO = new PlanoaulaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        classeDAO = new ClasseJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        cruzamentoDAO = new DiasemanaHasEstrategiaJpaController(Persistence.createEntityManagerFactory("ProjetoEletiva1PU"));
        txtDataInicio.setDateFormatString("dd/MM/yyyy");
        txtDataFinal.setDateFormatString("dd/MM/yyyy");
        user = login;
        preencherCmbConhecimento();
        desativarCampos();
        this.cruzamento = new DiasemanaHasEstrategia();
    }

    private void desativarCampos() {
        btnSalvarPlanoAula.setEnabled(false);
        btnSalvarPlanoAula1.setEnabled(false);
        btnSalvarPlanoAula2.setEnabled(false);
        btnSalvarPlanoAula3.setEnabled(false);
        btnSalvarPlanoAula4.setEnabled(false);
        btnEnviarPlano.setEnabled(false);
        txtDataFinal.setEnabled(false);

        //pnlSegunda.enable(false);
        txtAcolhidaAlunos.setEnabled(false);
        txtPrincipalObjetivoDia.setEnabled(false);
        txtConhecimento.setEnabled(false);
        btnMais.setEnabled(false);
        cmbAreaConhecimento.setEnabled(false);
        txtEstrRecuAtivi.setEnabled(false);
        btnAdicionar.setEnabled(false);
        btnRecuperar.setEnabled(false);
        txtObservacoes.setEnabled(false);
        txtAnexo.setEnabled(false);
        btnAnexar.setEnabled(false);

        //pnlterca.enable(false);
        txtAcolhidaAlunos1.setEnabled(false);
        txtPrincipalObjetivoDia1.setEnabled(false);
        txtConhecimento1.setEnabled(false);
        btnMais1.setEnabled(false);
        cmbAreaConhecimento1.setEnabled(false);
        txtEstrRecuAtivi1.setEnabled(false);
        btnAdicionar1.setEnabled(false);
        btnRecuperar1.setEnabled(false);
        txtObservacoes1.setEnabled(false);
        txtAnexo1.setEnabled(false);
        btnAnexar1.setEnabled(false);

        //pnlQuarta.enable(false);
        txtAcolhidaAlunos2.setEnabled(false);
        txtPrincipalObjetivoDia2.setEnabled(false);
        txtConhecimento2.setEnabled(false);
        btnMais2.setEnabled(false);
        cmbAreaConhecimento2.setEnabled(false);
        txtEstrRecuAtivi2.setEnabled(false);
        btnAdicionar2.setEnabled(false);
        btnRecuperar2.setEnabled(false);
        txtObservacoes2.setEnabled(false);
        txtAnexo2.setEnabled(false);
        btnAnexar2.setEnabled(false);

        //pnlQuinta.enable(false);
        txtAcolhidaAlunos3.setEnabled(false);
        txtPrincipalObjetivoDia3.setEnabled(false);
        txtConhecimento3.setEnabled(false);
        btnMais3.setEnabled(false);
        cmbAreaConhecimento3.setEnabled(false);
        txtEstrRecuAtivi3.setEnabled(false);
        btnAdicionar3.setEnabled(false);
        btnRecuperar3.setEnabled(false);
        txtObservacoes3.setEnabled(false);
        txtAnexo3.setEnabled(false);
        btnAnexar3.setEnabled(false);

        //pnlSexta.enable(false);
        txtAcolhidaAlunos4.setEnabled(false);
        txtPrincipalObjetivoDia4.setEnabled(false);
        txtConhecimento4.setEnabled(false);
        btnMais4.setEnabled(false);
        cmbAreaConhecimento4.setEnabled(false);
        txtEstrRecuAtivi4.setEnabled(false);
        btnAdicionar4.setEnabled(false);
        btnRecuperar4.setEnabled(false);
        txtObservacoes4.setEnabled(false);
        txtAnexo4.setEnabled(false);
        btnAnexar4.setEnabled(false);
    }

    private void ativarCampos() {
        btnSalvarPlanoAula.setEnabled(true);
        btnSalvarPlanoAula1.setEnabled(true);
        btnSalvarPlanoAula2.setEnabled(true);
        btnSalvarPlanoAula3.setEnabled(true);
        btnSalvarPlanoAula4.setEnabled(true);

        //pnlSegunda.enable(true);
        txtAcolhidaAlunos.setEnabled(true);
        txtPrincipalObjetivoDia.setEnabled(true);
        txtConhecimento.setEnabled(true);
        btnMais.setEnabled(true);
        cmbAreaConhecimento.setEnabled(true);
        txtEstrRecuAtivi.setEnabled(true);
        btnAdicionar.setEnabled(true);
        btnRecuperar.setEnabled(true);
        txtObservacoes.setEnabled(true);
        txtAnexo.setEnabled(true);
        btnAnexar.setEnabled(true);

        //pnlterca.enable(true);
        txtAcolhidaAlunos1.setEnabled(true);
        txtPrincipalObjetivoDia1.setEnabled(true);
        txtConhecimento1.setEnabled(true);
        btnMais1.setEnabled(true);
        cmbAreaConhecimento1.setEnabled(true);
        txtEstrRecuAtivi1.setEnabled(true);
        btnAdicionar1.setEnabled(true);
        btnRecuperar1.setEnabled(true);
        txtObservacoes1.setEnabled(true);
        txtAnexo1.setEnabled(true);
        btnAnexar1.setEnabled(true);

        //pnlQuarta.enable(true);
        txtAcolhidaAlunos2.setEnabled(true);
        txtPrincipalObjetivoDia2.setEnabled(true);
        txtConhecimento2.setEnabled(true);
        btnMais2.setEnabled(true);
        cmbAreaConhecimento2.setEnabled(true);
        txtEstrRecuAtivi2.setEnabled(true);
        btnAdicionar2.setEnabled(true);
        btnRecuperar2.setEnabled(true);
        txtObservacoes2.setEnabled(true);
        txtAnexo2.setEnabled(true);
        btnAnexar2.setEnabled(true);

        //pnlQuinta.enable(true);
        txtAcolhidaAlunos3.setEnabled(true);
        txtPrincipalObjetivoDia3.setEnabled(true);
        txtConhecimento3.setEnabled(true);
        btnMais3.setEnabled(true);
        cmbAreaConhecimento3.setEnabled(true);
        txtEstrRecuAtivi3.setEnabled(true);
        btnAdicionar3.setEnabled(true);
        btnRecuperar3.setEnabled(true);
        txtObservacoes3.setEnabled(true);
        txtAnexo3.setEnabled(true);
        btnAnexar3.setEnabled(true);

        //pnlSexta.enable(true);
        txtAcolhidaAlunos4.setEnabled(true);
        txtPrincipalObjetivoDia4.setEnabled(true);
        txtConhecimento4.setEnabled(true);
        btnMais4.setEnabled(true);
        cmbAreaConhecimento4.setEnabled(true);
        txtEstrRecuAtivi4.setEnabled(true);
        btnAdicionar4.setEnabled(true);
        btnRecuperar4.setEnabled(true);
        txtObservacoes4.setEnabled(true);
        txtAnexo4.setEnabled(true);
        btnAnexar4.setEnabled(true);
    }

    private void somadia() throws ParseException {

        java.util.Date pega = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.d = formato.format(pega);
        LocalDate data = LocalDate.parse(d, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        data = data.plusDays(5);
        //txtData.setText(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtDataFinal.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

    }

    private void adicionarPlanoAula() {
        Planoaula p = new Planoaula();
        try {
            p = instanciaPlanoaula();
            planoaulaDAO.create(p);
            this.pa = planoaulaDAO.getPlano(p);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            JOptionPane.showMessageDialog(null, "Plano Salvo");
        }
    }

    private void adicionarDiaSemana() {
        Diasemana d = new Diasemana();
        try {
            d = instanciaDiaSemana();
            diasemanaDAO.create(d);
            this.ds = diasemanaDAO.getDia(d);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {

            JOptionPane.showMessageDialog(null, "Dias da Semana Salvo");

        }
    }

    private void adicionarEstrategia() {
        try {
            instanciaEstrategia();
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {

            JOptionPane.showMessageDialog(null, "Estrategia Salva");

        }

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
        tbpGuias = new javax.swing.JTabbedPane();
        pnlSegunda = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAcolhidaAlunos = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        cmbAreaConhecimento = new javax.swing.JComboBox();
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
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        txtConhecimento = new javax.swing.JTextField();
        cmbLicaoCasa = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        btnSalvarPlanoAula = new javax.swing.JButton();
        pnlterca = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAcolhidaAlunos1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtConhecimento1 = new javax.swing.JTextField();
        btnMais1 = new javax.swing.JButton();
        cmbAreaConhecimento1 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPlanoAula1 = new javax.swing.JTable();
        btnAdicionar1 = new javax.swing.JButton();
        btnRecuperar1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtEstrRecuAtivi1 = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        txtAnexo1 = new javax.swing.JTextField();
        btnAnexar1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtObservacoes1 = new javax.swing.JTextArea();
        btnSalvarPlanoAula1 = new javax.swing.JButton();
        pnlQuarta = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtAcolhidaAlunos2 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        txtConhecimento2 = new javax.swing.JTextField();
        btnMais2 = new javax.swing.JButton();
        cmbAreaConhecimento2 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPlanoAula2 = new javax.swing.JTable();
        btnAdicionar2 = new javax.swing.JButton();
        btnRecuperar2 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtEstrRecuAtivi2 = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        txtAnexo2 = new javax.swing.JTextField();
        btnAnexar2 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtObservacoes2 = new javax.swing.JTextArea();
        btnSalvarPlanoAula2 = new javax.swing.JButton();
        pnlQuinta = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtAcolhidaAlunos3 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        txtConhecimento3 = new javax.swing.JTextField();
        btnMais3 = new javax.swing.JButton();
        cmbAreaConhecimento3 = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblPlanoAula3 = new javax.swing.JTable();
        btnAdicionar3 = new javax.swing.JButton();
        btnRecuperar3 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtEstrRecuAtivi3 = new javax.swing.JTextArea();
        jLabel32 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        txtAnexo3 = new javax.swing.JTextField();
        btnAnexar3 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtObservacoes3 = new javax.swing.JTextArea();
        btnSalvarPlanoAula3 = new javax.swing.JButton();
        pnlSexta = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia4 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtAcolhidaAlunos4 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        txtConhecimento4 = new javax.swing.JTextField();
        btnMais4 = new javax.swing.JButton();
        cmbAreaConhecimento4 = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblPlanoAula4 = new javax.swing.JTable();
        btnAdicionar4 = new javax.swing.JButton();
        btnRecuperar4 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtEstrRecuAtivi4 = new javax.swing.JTextArea();
        jLabel40 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        txtAnexo4 = new javax.swing.JTextField();
        btnAnexar4 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtObservacoes4 = new javax.swing.JTextArea();
        btnSalvarPlanoAula4 = new javax.swing.JButton();
        btnInserirPlanoAula = new javax.swing.JButton();
        btnEnviarPlano = new javax.swing.JButton();
        btnVisualizarPdf = new javax.swing.JButton();
        btnVolta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Plano de Aula Semanal");

        txtDataInicio.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtDataInicioCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        txtDataInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDataInicioPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Data de Início:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Data Final:");

        tbpGuias.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        tbpGuias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbpGuiasKeyPressed(evt);
            }
        });

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

        btnMais.setText("+");
        btnMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaisActionPerformed(evt);
            }
        });

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
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });

        tblPlanoAula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estratégia, Recursos e Atividades Complementares "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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
        if (tblPlanoAula.getColumnModel().getColumnCount() > 0) {
            tblPlanoAula.getColumnModel().getColumn(0).setResizable(false);
            tblPlanoAula.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setText("Plano de Aula");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setText("Matriz (Anexar):");

        btnAnexar.setText("Anexar");

        jLabel10.setText("Not_used:");

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

        cmbLicaoCasa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSalvarPlanoAula.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvarPlanoAula.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAulaActionPerformed(evt);
            }
        });

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
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAdicionar)
                                    .addComponent(btnRecuperar)))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMais, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbAreaConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel8))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAnexo, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(cmbLicaoCasa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula))
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
                                .addGap(20, 20, 20)
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnMais)
                                        .addComponent(txtConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbAreaConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6)))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)))
                        .addGap(3, 3, 3)
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbLicaoCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnSalvarPlanoAula)
                .addContainerGap())
        );

        tbpGuias.addTab("Segunda - Feira", pnlSegunda);
        pnlSegunda.getAccessibleContext().setAccessibleName("pnllSegundaFeira");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel12.setText("Principal Objetivo do Dia:");

        txtPrincipalObjetivoDia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrincipalObjetivoDia1KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel13.setText("Acolhida dos Alunos:");

        txtAcolhidaAlunos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcolhidaAlunos1KeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel14.setText("Área de Conhecimento:");

        btnMais1.setText("+");
        btnMais1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMais1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setText("Plano de Aula");

        tblPlanoAula1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estratégia, Recursos e Atividades Complementares "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlanoAula1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanoAula1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPlanoAula1);
        if (tblPlanoAula1.getColumnModel().getColumnCount() > 0) {
            tblPlanoAula1.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAdicionar1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAdicionar1.setText(">>");
        btnAdicionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar1ActionPerformed(evt);
            }
        });

        btnRecuperar1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnRecuperar1.setText("<<");
        btnRecuperar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperar1ActionPerformed(evt);
            }
        });

        txtEstrRecuAtivi1.setColumns(20);
        txtEstrRecuAtivi1.setRows(5);
        txtEstrRecuAtivi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstrRecuAtivi1KeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(txtEstrRecuAtivi1);

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setText("Estratégia, Recursos, Atividades Complementares ");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setText("Matriz (Anexar):");

        btnAnexar1.setText("Anexar");

        jLabel18.setText("Not_used:");

        jTextField3.setEditable(false);
        jTextField3.setText("Not_used");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setText("Observações e Reflexões:");

        txtObservacoes1.setColumns(20);
        txtObservacoes1.setRows(5);
        txtObservacoes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacoes1KeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(txtObservacoes1);

        btnSalvarPlanoAula1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvarPlanoAula1.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnltercaLayout = new javax.swing.GroupLayout(pnlterca);
        pnlterca.setLayout(pnltercaLayout);
        pnltercaLayout.setHorizontalGroup(
            pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnltercaLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(18, 18, 18)
                            .addComponent(txtPrincipalObjetivoDia1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnltercaLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(43, 43, 43)
                            .addComponent(txtAcolhidaAlunos1)))
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar1)
                                    .addComponent(btnRecuperar1)))
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtConhecimento1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMais1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbAreaConhecimento1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel15))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAnexo1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAnexar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula1))
        );
        pnltercaLayout.setVerticalGroup(
            pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel12))
                    .addComponent(txtPrincipalObjetivoDia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13))
                    .addComponent(txtAcolhidaAlunos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMais1)
                                .addComponent(txtConhecimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbAreaConhecimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)))
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)))
                .addGap(3, 3, 3)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(6, 6, 6)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addComponent(btnAdicionar1)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecuperar1))))
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtAnexo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar1))
                .addGap(18, 18, 18)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvarPlanoAula1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Terça - Feira", pnlterca);
        pnlterca.getAccessibleContext().setAccessibleName("pnlTercaFeira");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setText("Principal Objetivo do Dia:");

        txtPrincipalObjetivoDia2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrincipalObjetivoDia2KeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setText("Acolhida dos Alunos:");

        txtAcolhidaAlunos2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcolhidaAlunos2KeyPressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel22.setText("Área de Conhecimento:");

        btnMais2.setText("+");
        btnMais2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMais2ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setText("Plano de Aula");

        tblPlanoAula2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estratégia, Recursos e Atividades Complementares "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlanoAula2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanoAula2MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblPlanoAula2);
        if (tblPlanoAula2.getColumnModel().getColumnCount() > 0) {
            tblPlanoAula2.getColumnModel().getColumn(0).setResizable(false);
            tblPlanoAula2.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAdicionar2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAdicionar2.setText(">>");
        btnAdicionar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar2ActionPerformed(evt);
            }
        });

        btnRecuperar2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnRecuperar2.setText("<<");
        btnRecuperar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperar2ActionPerformed(evt);
            }
        });

        txtEstrRecuAtivi2.setColumns(20);
        txtEstrRecuAtivi2.setRows(5);
        txtEstrRecuAtivi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstrRecuAtivi2KeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(txtEstrRecuAtivi2);

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setText("Estratégia, Recursos, Atividades Complementares ");

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel25.setText("Matriz (Anexar):");

        btnAnexar2.setText("Anexar");

        jLabel26.setText("Not_used:");

        jTextField4.setEditable(false);
        jTextField4.setText("Not_used");

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel27.setText("Observações e Reflexões:");

        txtObservacoes2.setColumns(20);
        txtObservacoes2.setRows(5);
        txtObservacoes2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacoes2KeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(txtObservacoes2);

        btnSalvarPlanoAula2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvarPlanoAula2.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlQuartaLayout = new javax.swing.GroupLayout(pnlQuarta);
        pnlQuarta.setLayout(pnlQuartaLayout);
        pnlQuartaLayout.setHorizontalGroup(
            pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlQuartaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuartaLayout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(18, 18, 18)
                            .addComponent(txtPrincipalObjetivoDia2, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuartaLayout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addGap(43, 43, 43)
                            .addComponent(txtAcolhidaAlunos2)))
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuartaLayout.createSequentialGroup()
                                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar2)
                                    .addComponent(btnRecuperar2)))
                            .addGroup(pnlQuartaLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(txtConhecimento2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMais2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbAreaConhecimento2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuartaLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel23))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlQuartaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAnexo2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAnexar2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlQuartaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlQuartaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula2))
        );
        pnlQuartaLayout.setVerticalGroup(
            pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuartaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel20))
                    .addComponent(txtPrincipalObjetivoDia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel21))
                    .addComponent(txtAcolhidaAlunos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMais2)
                                .addComponent(txtConhecimento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbAreaConhecimento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22)))
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)))
                .addGap(3, 3, 3)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(6, 6, 6)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlQuartaLayout.createSequentialGroup()
                                .addComponent(btnAdicionar2)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecuperar2))))
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtAnexo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar2))
                .addGap(18, 18, 18)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvarPlanoAula2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Quarta - Feira", pnlQuarta);
        pnlQuarta.getAccessibleContext().setAccessibleName("pnlQuartaFeira");

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel28.setText("Principal Objetivo do Dia:");

        txtPrincipalObjetivoDia3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrincipalObjetivoDia3KeyPressed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel29.setText("Acolhida dos Alunos:");

        txtAcolhidaAlunos3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcolhidaAlunos3KeyPressed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel30.setText("Área de Conhecimento:");

        btnMais3.setText("+");
        btnMais3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMais3ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel31.setText("Plano de Aula");

        tblPlanoAula3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estratégia, Recursos e Atividades Complementares "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlanoAula3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanoAula3MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblPlanoAula3);
        if (tblPlanoAula3.getColumnModel().getColumnCount() > 0) {
            tblPlanoAula3.getColumnModel().getColumn(0).setResizable(false);
            tblPlanoAula3.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAdicionar3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAdicionar3.setText(">>");
        btnAdicionar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar3ActionPerformed(evt);
            }
        });

        btnRecuperar3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnRecuperar3.setText("<<");
        btnRecuperar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperar3ActionPerformed(evt);
            }
        });

        txtEstrRecuAtivi3.setColumns(20);
        txtEstrRecuAtivi3.setRows(5);
        txtEstrRecuAtivi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstrRecuAtivi3KeyPressed(evt);
            }
        });
        jScrollPane11.setViewportView(txtEstrRecuAtivi3);

        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel32.setText("Estratégia, Recursos, Atividades Complementares ");

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel33.setText("Matriz (Anexar):");

        btnAnexar3.setText("Anexar");

        jLabel34.setText("Not_used:");

        jTextField5.setEditable(false);
        jTextField5.setText("Not_used");

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel35.setText("Observações e Reflexões:");

        txtObservacoes3.setColumns(20);
        txtObservacoes3.setRows(5);
        txtObservacoes3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacoes3KeyPressed(evt);
            }
        });
        jScrollPane12.setViewportView(txtObservacoes3);

        btnSalvarPlanoAula3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvarPlanoAula3.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlQuintaLayout = new javax.swing.GroupLayout(pnlQuinta);
        pnlQuinta.setLayout(pnlQuintaLayout);
        pnlQuintaLayout.setHorizontalGroup(
            pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuintaLayout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addGap(18, 18, 18)
                            .addComponent(txtPrincipalObjetivoDia3, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuintaLayout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addGap(43, 43, 43)
                            .addComponent(txtAcolhidaAlunos3)))
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuintaLayout.createSequentialGroup()
                                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar3)
                                    .addComponent(btnRecuperar3)))
                            .addGroup(pnlQuintaLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(txtConhecimento3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMais3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbAreaConhecimento3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuintaLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel31))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAnexo3)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAnexar3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula3))
        );
        pnlQuintaLayout.setVerticalGroup(
            pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel28))
                    .addComponent(txtPrincipalObjetivoDia3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel29))
                    .addComponent(txtAcolhidaAlunos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMais3)
                                .addComponent(txtConhecimento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbAreaConhecimento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30)))
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)))
                .addGap(3, 3, 3)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32)
                        .addGap(6, 6, 6)
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlQuintaLayout.createSequentialGroup()
                                .addComponent(btnAdicionar3)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecuperar3))))
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtAnexo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar3))
                .addGap(18, 18, 18)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvarPlanoAula3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Quinta - Feira", pnlQuinta);
        pnlQuinta.getAccessibleContext().setAccessibleName("pnlQuintaFeira");
        pnlQuinta.getAccessibleContext().setAccessibleDescription("");

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel36.setText("Principal Objetivo do Dia:");

        txtPrincipalObjetivoDia4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrincipalObjetivoDia4KeyPressed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel37.setText("Acolhida dos Alunos:");

        txtAcolhidaAlunos4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcolhidaAlunos4KeyPressed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel38.setText("Área de Conhecimento:");

        btnMais4.setText("+");
        btnMais4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMais4ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel39.setText("Plano de Aula");

        tblPlanoAula4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área de Conhecimento", "Estratégia, Recursos e Atividades Complementares "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlanoAula4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanoAula4MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblPlanoAula4);
        if (tblPlanoAula4.getColumnModel().getColumnCount() > 0) {
            tblPlanoAula4.getColumnModel().getColumn(0).setResizable(false);
            tblPlanoAula4.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAdicionar4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAdicionar4.setText(">>");
        btnAdicionar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar4ActionPerformed(evt);
            }
        });

        btnRecuperar4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnRecuperar4.setText("<<");
        btnRecuperar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperar4ActionPerformed(evt);
            }
        });

        txtEstrRecuAtivi4.setColumns(20);
        txtEstrRecuAtivi4.setRows(5);
        txtEstrRecuAtivi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstrRecuAtivi4KeyPressed(evt);
            }
        });
        jScrollPane14.setViewportView(txtEstrRecuAtivi4);

        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel40.setText("Estratégia, Recursos, Atividades Complementares ");

        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel41.setText("Matriz (Anexar):");

        btnAnexar4.setText("Anexar");

        jLabel42.setText("Not_used:");

        jTextField6.setEditable(false);
        jTextField6.setText("Not_used");

        jLabel43.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel43.setText("Observações e Reflexões:");

        txtObservacoes4.setColumns(20);
        txtObservacoes4.setRows(5);
        txtObservacoes4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacoes4KeyPressed(evt);
            }
        });
        jScrollPane15.setViewportView(txtObservacoes4);

        btnSalvarPlanoAula4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvarPlanoAula4.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSextaLayout = new javax.swing.GroupLayout(pnlSexta);
        pnlSexta.setLayout(pnlSextaLayout);
        pnlSextaLayout.setHorizontalGroup(
            pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSextaLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addGap(18, 18, 18)
                            .addComponent(txtPrincipalObjetivoDia4, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSextaLayout.createSequentialGroup()
                            .addComponent(jLabel37)
                            .addGap(43, 43, 43)
                            .addComponent(txtAcolhidaAlunos4)))
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSextaLayout.createSequentialGroup()
                                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar4)
                                    .addComponent(btnRecuperar4)))
                            .addGroup(pnlSextaLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(txtConhecimento4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMais4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbAreaConhecimento4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSextaLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel39))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAnexo4)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAnexar4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula4))
        );
        pnlSextaLayout.setVerticalGroup(
            pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel36))
                    .addComponent(txtPrincipalObjetivoDia4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel37))
                    .addComponent(txtAcolhidaAlunos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMais4)
                                .addComponent(txtConhecimento4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbAreaConhecimento4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel38)))
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39)))
                .addGap(3, 3, 3)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40)
                        .addGap(6, 6, 6)
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSextaLayout.createSequentialGroup()
                                .addComponent(btnAdicionar4)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecuperar4))))
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtAnexo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar4))
                .addGap(18, 18, 18)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnSalvarPlanoAula4)
                .addContainerGap())
        );

        tbpGuias.addTab("Sexta - Feira", pnlSexta);
        pnlSexta.getAccessibleContext().setAccessibleName("pnlSextaFeira");

        btnInserirPlanoAula.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnInserirPlanoAula.setText("Criar Novo Plano de Aula");
        btnInserirPlanoAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirPlanoAulaActionPerformed(evt);
            }
        });

        btnEnviarPlano.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEnviarPlano.setText("Enviar Plano  de Aula Semanal");
        btnEnviarPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarPlanoActionPerformed(evt);
            }
        });

        btnVisualizarPdf.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnVisualizarPdf.setText("Visualizar arquivo em PDF");

        btnVolta.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnVolta.setText("Voltar");
        btnVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltaActionPerformed(evt);
            }
        });

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
                                .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnInserirPlanoAula))
                            .addComponent(tbpGuias, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(btnEnviarPlano)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizarPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVolta)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(tbpGuias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnInserirPlanoAula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviarPlano)
                    .addComponent(btnVisualizarPdf)
                    .addComponent(btnVolta))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        tbpGuias.getAccessibleContext().setAccessibleName("tblPainel");

        setSize(new java.awt.Dimension(1015, 818));
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

    private String validacaoCamposSegunda() {
        String objetivo1 = txtPrincipalObjetivoDia.getText();
        String acolhida1 = txtAcolhidaAlunos.getText();

        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";

        if ("".equals(objetivo1)) {
            mensagem = mensagem + " Objetivo de Segunda-feira;\n";
        }
        if ("".equals(acolhida1)) {
            mensagem = mensagem + " Acolhida de Segunda-feira;\n";
        }

        return mensagem;
    }

    private String validacaoCamposTerca() {
        String objetivo2 = txtPrincipalObjetivoDia1.getText();
        String acolhida2 = txtAcolhidaAlunos1.getText();

        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";

        if ("".equals(objetivo2)) {
            mensagem = mensagem + " Objetivo de Terça-feira;\n";
        }
        if ("".equals(acolhida2)) {
            mensagem = mensagem + " Acolhida de Terça-feira;\n";
        }

        return mensagem;
    }

    private String validacaoCamposQuarta() {
        String objetivo3 = txtPrincipalObjetivoDia2.getText();
        String acolhida3 = txtAcolhidaAlunos2.getText();

        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";

        if ("".equals(objetivo3)) {
            mensagem = mensagem + " Objetivo de Quarta-feira;\n";
        }
        if ("".equals(acolhida3)) {
            mensagem = mensagem + " Acolhida de Quarta-feira;\n";
        }

        return mensagem;
    }

    private String validacaoCamposQuinta() {
        String objetivo4 = txtPrincipalObjetivoDia3.getText();
        String acolhida4 = txtAcolhidaAlunos3.getText();

        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";
        if ("".equals(objetivo4)) {
            mensagem = mensagem + " Objetivo de Quinta-feira;\n";
        }
        if ("".equals(acolhida4)) {
            mensagem = mensagem + " Acolhida de Quinta-feira;\n";
        }

        return mensagem;
    }

    private String validacaoCamposSexta() {
        String objetivo5 = txtPrincipalObjetivoDia4.getText();
        String acolhida5 = txtAcolhidaAlunos4.getText();

        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";
        if ("".equals(objetivo5)) {
            mensagem = mensagem + " Objetivo de Sexta-feira;\n";
        }
        if ("".equals(acolhida5)) {
            mensagem = mensagem + " Acolhida de Sexta-feira;\n";
        }

        return mensagem;
    }

    private void preencherCmbConhecimento() {
        List<Areaconhecimento> lista = areaConhecimentoDAO.findAreaconhecimentoEntities();
        if (lista.size() > 0) {
            cmbAreaConhecimento.removeAllItems();
            cmbAreaConhecimento1.removeAllItems();
            cmbAreaConhecimento2.removeAllItems();
            cmbAreaConhecimento3.removeAllItems();
            cmbAreaConhecimento4.removeAllItems();
            for (Areaconhecimento a : lista) {
                cmbAreaConhecimento.addItem(a);
                cmbAreaConhecimento1.addItem(a);
                cmbAreaConhecimento2.addItem(a);
                cmbAreaConhecimento3.addItem(a);
                cmbAreaConhecimento4.addItem(a);
            }
        }
    }

    public Areaconhecimento instanciaAreaConhecimento() {
        Areaconhecimento ac = new Areaconhecimento();
        if (tbpGuias.getSelectedIndex() == 0) {
            ac.setAreaconhecimento(txtConhecimento.getText());
        }
        if (tbpGuias.getSelectedIndex() == 1) {
            ac.setAreaconhecimento(txtConhecimento1.getText());
        }
        if (tbpGuias.getSelectedIndex() == 2) {
            ac.setAreaconhecimento(txtConhecimento2.getText());
        }
        if (tbpGuias.getSelectedIndex() == 3) {
            ac.setAreaconhecimento(txtConhecimento3.getText());
        }
        if (tbpGuias.getSelectedIndex() == 4) {
            ac.setAreaconhecimento(txtConhecimento4.getText());
        }
        return ac;
    }

    public Estrategia instanciaEstrategia() throws Exception {
        int obj = tbpGuias.getSelectedIndex();
        Estrategia e = new Estrategia();
        DefaultTableModel tabelaPlanoAula0 = (DefaultTableModel) tblPlanoAula.getModel();
        DefaultTableModel tabelaPlanoAula1 = (DefaultTableModel) tblPlanoAula1.getModel();
        DefaultTableModel tabelaPlanoAula2 = (DefaultTableModel) tblPlanoAula2.getModel();
        DefaultTableModel tabelaPlanoAula3 = (DefaultTableModel) tblPlanoAula3.getModel();
        DefaultTableModel tabelaPlanoAula4 = (DefaultTableModel) tblPlanoAula4.getModel();
        switch (obj) {
            case 0:
                for (int i = 0; i < tblPlanoAula.getRowCount(); i++) {
                    e = (Estrategia) tabelaPlanoAula0.getValueAt(i, 1);
                    estrategiaDAO.create(e);
                    this.es = estrategiaDAO.getEstrategia(e);
                    cruzamento.setDiasemana(this.ds);
                    cruzamento.setEstrategia(this.es);
                    cruzamento.setPlanoaula(this.pa);
                    cruzamentoDAO.create(cruzamento);
                    e = null;
                }
                break;
            case 1:
                for (int i = 0; i < tblPlanoAula1.getRowCount(); i++) {
                    e = (Estrategia) tabelaPlanoAula1.getValueAt(i, 1);
                    estrategiaDAO.create(e);
                    this.es = estrategiaDAO.getEstrategia(e);
                    cruzamento.setDiasemana(this.ds);
                    cruzamento.setEstrategia(this.es);
                    cruzamento.setPlanoaula(this.pa);
                    cruzamentoDAO.create(cruzamento);
                    e = null;
                }
                break;
            case 2:
                for (int i = 0; i < tblPlanoAula2.getRowCount(); i++) {
                    e = (Estrategia) tabelaPlanoAula2.getValueAt(i, 1);
                    estrategiaDAO.create(e);
                    this.es = estrategiaDAO.getEstrategia(e);
                    cruzamento.setDiasemana(this.ds);
                    cruzamento.setEstrategia(this.es);
                    cruzamento.setPlanoaula(this.pa);
                    cruzamentoDAO.create(cruzamento);
                    e = null;
                }
                break;
            case 3:
                for (int i = 0; i < tblPlanoAula3.getRowCount(); i++) {
                    e = (Estrategia) tabelaPlanoAula3.getValueAt(i, 1);
                    estrategiaDAO.create(e);
                    this.es = estrategiaDAO.getEstrategia(e);
                    cruzamento.setDiasemana(this.ds);
                    cruzamento.setEstrategia(this.es);
                    cruzamento.setPlanoaula(this.pa);
                    cruzamentoDAO.create(cruzamento);
                    e = null;
                }
                break;
            case 4:
                for (int i = 0; i < tblPlanoAula4.getRowCount(); i++) {
                    e = (Estrategia) tabelaPlanoAula4.getValueAt(i, 1);
                    estrategiaDAO.create(e);
                    this.es = estrategiaDAO.getEstrategia(e);
                    cruzamento.setDiasemana(this.ds);
                    cruzamento.setEstrategia(this.es);
                    cruzamento.setPlanoaula(this.pa);
                    cruzamentoDAO.create(cruzamento);
                    e = null;
                }
                break;
        }
        return e;
    }

    public Diasemana instanciaDiaSemana() {
        Diasemana d = new Diasemana();
        if (tbpGuias.getSelectedIndex() == 0) {
            d.setDia("Segunda-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia.getText());
            d.setAcolhida(txtAcolhidaAlunos.getText());
            d.setAnexos(txtAnexo.getText());
            d.setLicaodecasa(jTextField2.getText());
            d.setObservacoes(txtObservacoes.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 1) {
            d.setDia("Terça-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia1.getText());
            d.setAcolhida(txtAcolhidaAlunos1.getText());
            d.setAnexos(txtAnexo1.getText());
            d.setLicaodecasa(jTextField2.getText());
            d.setObservacoes(txtObservacoes1.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 2) {
            d.setDia("Quarta-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia2.getText());
            d.setAcolhida(txtAcolhidaAlunos2.getText());
            d.setAnexos(txtAnexo2.getText());
            d.setLicaodecasa(jTextField2.getText());
            d.setObservacoes(txtObservacoes2.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 3) {
            d.setDia("Quinta-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia3.getText());
            d.setAcolhida(txtAcolhidaAlunos3.getText());
            d.setAnexos(txtAnexo3.getText());
            d.setLicaodecasa(jTextField2.getText());
            d.setObservacoes(txtObservacoes3.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 4) {
            d.setDia("Sexta-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia4.getText());
            d.setAcolhida(txtAcolhidaAlunos4.getText());
            d.setAnexos(txtAnexo4.getText());
            d.setLicaodecasa(jTextField2.getText());
            d.setObservacoes(txtObservacoes4.getText());
            d.setDatadiasemana(new Date());
        }
        return d;
    }

    public Planoaula instanciaPlanoaula() {
        Planoaula p = new Planoaula();
        Usuario u = new Usuario();
        Classe c = new Classe();

        c = classeDAO.getClasseByProf(user.getLogin());
        p.setDatainicio(txtDataInicio.getDate());
        p.setDatafim(txtDataFinal.getDate());
        p.setUsuarioLogin(user);
        p.setClasseIdclasse(c);
        p.setStatus("Incompleto");

        return p;
    }

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

    private void preencheTabela(List<Estrategia> lista) {
        if (lista.size() >= 0) {
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula.getModel();
            //tabelaConhecimento.setNumRows(0);
            for (Estrategia e : lista) {
                Object[] obj = new Object[]{
                    e.getAreaconhecimentoIdconhecimento().toString(),
                    e.getEstrategia()
                };
                tabelaConhecimento.addRow(obj);
            }
        }
    }

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (editando == false) {
            Estrategia e = new Estrategia();
            Areaconhecimento a = new Areaconhecimento();
            e.setEstrategia(txtEstrRecuAtivi.getText());
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento.getSelectedItem());
            a = (Areaconhecimento) cmbAreaConhecimento.getSelectedItem();
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula.getModel();
            Object[] obj = new Object[]{
                a,
                e,};
            tabelaConhecimento.addRow(obj);

            txtEstrRecuAtivi.setText("");
            cmbAreaConhecimento.setSelectedIndex(0);

        } else {
            DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula.getModel();
            Estrategia e = (Estrategia) tabelaEstrategia.getValueAt(linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(txtEstrRecuAtivi.getText(), linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(cmbAreaConhecimento.getSelectedItem(), linhaSelecionada, 0);
            txtEstrRecuAtivi.setText("");
            cmbAreaConhecimento.setSelectedIndex(0);
            editando = false;
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarPlanoAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAulaActionPerformed
        salvaPlano();
    }//GEN-LAST:event_btnSalvarPlanoAulaActionPerformed

    private void salvaPlano() {
        String mensagem = "";
        switch (tbpGuias.getSelectedIndex()) {
            case 0:
                mensagem = validacaoCamposSegunda();
                break;
            case 1:
                mensagem = validacaoCamposTerca();
                break;
            case 2:
                mensagem = validacaoCamposQuarta();
                break;
            case 3:
                mensagem = validacaoCamposQuinta();
                break;
            case 4:
                mensagem = validacaoCamposSexta();
                break;
            default:
                break;
        }
        if (mensagem.equals("Favor preencher o(s) seguinte(s) campo(s):\n")) {
            adicionarDiaSemana();
            adicionarEstrategia();
            mensagem = "";
            switch (tbpGuias.getSelectedIndex()) {
                case 0:
                    travaCampos0();
                    break;
                case 1:
                    travaCampos1();
                    break;
                case 2:
                    travaCampos2();
                    break;
                case 3:
                    travaCampos3();
                    break;
                case 4:
                    travaCampos4();
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, mensagem);
        }
    }

    private void travaCampos0() {
        boolean statuscampos;
        statuscampos = false;
        txtPrincipalObjetivoDia.setEnabled(statuscampos);
        txtAcolhidaAlunos.setEnabled(statuscampos);
        txtConhecimento.setEnabled(statuscampos);
        btnMais.setEnabled(statuscampos);
        cmbAreaConhecimento.setEnabled(statuscampos);
        txtEstrRecuAtivi.setEnabled(statuscampos);
        btnAdicionar.setEnabled(statuscampos);
        btnRecuperar.setEnabled(statuscampos);
        txtAnexo.setEnabled(statuscampos);
        btnAnexar.setEnabled(statuscampos);
        txtObservacoes.setEnabled(statuscampos);
        tblPlanoAula.setEnabled(statuscampos);
        btnSalvarPlanoAula.setEnabled(statuscampos);
    }

    ;
    private void travaCampos1() {
        boolean statuscampos;
        statuscampos = false;
        txtPrincipalObjetivoDia1.setEnabled(statuscampos);
        txtAcolhidaAlunos1.setEnabled(statuscampos);
        txtConhecimento1.setEnabled(statuscampos);
        btnMais1.setEnabled(statuscampos);
        cmbAreaConhecimento1.setEnabled(statuscampos);
        txtEstrRecuAtivi1.setEnabled(statuscampos);
        btnAdicionar1.setEnabled(statuscampos);
        btnRecuperar1.setEnabled(statuscampos);
        txtAnexo1.setEnabled(statuscampos);
        btnAnexar1.setEnabled(statuscampos);
        txtObservacoes1.setEnabled(statuscampos);
        tblPlanoAula1.setEnabled(statuscampos);
        btnSalvarPlanoAula1.setEnabled(statuscampos);
    }

    ;
    private void travaCampos2() {
        boolean statuscampos;
        statuscampos = false;
        txtPrincipalObjetivoDia2.setEnabled(statuscampos);
        txtAcolhidaAlunos2.setEnabled(statuscampos);
        txtConhecimento2.setEnabled(statuscampos);
        btnMais2.setEnabled(statuscampos);
        cmbAreaConhecimento2.setEnabled(statuscampos);
        txtEstrRecuAtivi2.setEnabled(statuscampos);
        btnAdicionar2.setEnabled(statuscampos);
        btnRecuperar2.setEnabled(statuscampos);
        txtAnexo2.setEnabled(statuscampos);
        btnAnexar2.setEnabled(statuscampos);
        txtObservacoes2.setEnabled(statuscampos);
        tblPlanoAula2.setEnabled(statuscampos);
        btnSalvarPlanoAula2.setEnabled(statuscampos);
    }

    ;
    private void travaCampos3() {
        boolean statuscampos;
        statuscampos = false;
        txtPrincipalObjetivoDia3.setEnabled(statuscampos);
        txtAcolhidaAlunos3.setEnabled(statuscampos);
        txtConhecimento3.setEnabled(statuscampos);
        btnMais3.setEnabled(statuscampos);
        cmbAreaConhecimento3.setEnabled(statuscampos);
        txtEstrRecuAtivi3.setEnabled(statuscampos);
        btnAdicionar3.setEnabled(statuscampos);
        btnRecuperar3.setEnabled(statuscampos);
        txtAnexo3.setEnabled(statuscampos);
        btnAnexar3.setEnabled(statuscampos);
        txtObservacoes3.setEnabled(statuscampos);
        tblPlanoAula3.setEnabled(statuscampos);
        btnSalvarPlanoAula3.setEnabled(statuscampos);
    }

    ;
    private void travaCampos4() {
        boolean statuscampos;
        statuscampos = false;
        txtPrincipalObjetivoDia4.setEnabled(statuscampos);
        txtAcolhidaAlunos4.setEnabled(statuscampos);
        txtConhecimento4.setEnabled(statuscampos);
        btnMais4.setEnabled(statuscampos);
        cmbAreaConhecimento4.setEnabled(statuscampos);
        txtEstrRecuAtivi4.setEnabled(statuscampos);
        btnAdicionar4.setEnabled(statuscampos);
        btnRecuperar4.setEnabled(statuscampos);
        txtAnexo4.setEnabled(statuscampos);
        btnAnexar4.setEnabled(statuscampos);
        txtObservacoes4.setEnabled(statuscampos);
        tblPlanoAula4.setEnabled(statuscampos);
        btnSalvarPlanoAula4.setEnabled(statuscampos);
    }

    ;
    
    private void btnMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaisActionPerformed
        String exception = "";

        try {

            areaConhecimentoDAO.create(instanciaAreaConhecimento());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (exception.equals("")) {
                JOptionPane.showMessageDialog(null, "Área cadastrado com sucesso!");
                txtConhecimento.setText("");
                preencherCmbConhecimento();
            } else {
                //txtConhecimento.setText("");
                JOptionPane.showMessageDialog(null, "Deu ruim");
            }

        }
    }//GEN-LAST:event_btnMaisActionPerformed

    private void tblPlanoAulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoAulaMouseClicked

    }//GEN-LAST:event_tblPlanoAulaMouseClicked

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
        linhaSelecionada = tblPlanoAula.getSelectedRow();
        Estrategia e = (Estrategia) tblPlanoAula.getModel().getValueAt(linhaSelecionada, 1);
        txtEstrRecuAtivi.setText(e.getEstrategia());
        cmbAreaConhecimento.setSelectedItem((Object) tblPlanoAula.getModel().getValueAt(linhaSelecionada, 0));
        editando = true;
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void txtPrincipalObjetivoDia1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrincipalObjetivoDia1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrincipalObjetivoDia1KeyPressed

    private void txtAcolhidaAlunos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcolhidaAlunos1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcolhidaAlunos1KeyPressed

    private void btnMais1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMais1ActionPerformed
        // TODO add your handling code here:
        String exception = "";
        preencherCmbConhecimento();
        try {
            areaConhecimentoDAO.create(instanciaAreaConhecimento());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (exception.equals("")) {
                JOptionPane.showMessageDialog(null, "Área cadastrado com sucesso!");
                txtConhecimento1.setText("");
                preencherCmbConhecimento();
            } else {
                //txtConhecimento.setText("");
                JOptionPane.showMessageDialog(null, "Deu ruim");
            }

        }
    }//GEN-LAST:event_btnMais1ActionPerformed

    private void tblPlanoAula1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoAula1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPlanoAula1MouseClicked

    private void btnAdicionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar1ActionPerformed
        if (editando == false) {
            Estrategia e = new Estrategia();
            Areaconhecimento a = new Areaconhecimento();
            e.setEstrategia(txtEstrRecuAtivi1.getText());
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento1.getSelectedItem());
            a = (Areaconhecimento) cmbAreaConhecimento1.getSelectedItem();
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula1.getModel();
            Object[] obj = new Object[]{
                a,
                e,};
            tabelaConhecimento.addRow(obj);

            txtEstrRecuAtivi1.setText("");
            cmbAreaConhecimento1.setSelectedIndex(0);

        } else {
            DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula1.getModel();
            Estrategia e = (Estrategia) tabelaEstrategia.getValueAt(linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(txtEstrRecuAtivi1.getText(), linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(cmbAreaConhecimento1.getSelectedItem(), linhaSelecionada, 0);
            txtEstrRecuAtivi1.setText("");
            cmbAreaConhecimento1.setSelectedIndex(0);
            editando = false;
        }
    }//GEN-LAST:event_btnAdicionar1ActionPerformed

    private void btnRecuperar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperar1ActionPerformed
        linhaSelecionada = tblPlanoAula1.getSelectedRow();
        Estrategia e = (Estrategia) tblPlanoAula1.getModel().getValueAt(linhaSelecionada, 1);
        txtEstrRecuAtivi1.setText(e.getEstrategia());
        cmbAreaConhecimento1.setSelectedItem((Object) tblPlanoAula1.getModel().getValueAt(linhaSelecionada, 0));
        editando = true;
    }//GEN-LAST:event_btnRecuperar1ActionPerformed

    private void txtEstrRecuAtivi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstrRecuAtivi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstrRecuAtivi1KeyPressed

    private void txtObservacoes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacoes1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacoes1KeyPressed

    private void txtPrincipalObjetivoDia2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrincipalObjetivoDia2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrincipalObjetivoDia2KeyPressed

    private void txtAcolhidaAlunos2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcolhidaAlunos2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcolhidaAlunos2KeyPressed

    private void btnMais2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMais2ActionPerformed
        // TODO add your handling code here:
        String exception = "";

        try {

            areaConhecimentoDAO.create(instanciaAreaConhecimento());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (exception.equals("")) {
                JOptionPane.showMessageDialog(null, "Área cadastrado com sucesso!");
                txtConhecimento2.setText("");
                preencherCmbConhecimento();
            } else {
                //txtConhecimento.setText("");
                JOptionPane.showMessageDialog(null, "Deu ruim");
            }

        }
    }//GEN-LAST:event_btnMais2ActionPerformed

    private void tblPlanoAula2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoAula2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPlanoAula2MouseClicked

    private void btnAdicionar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar2ActionPerformed
        if (editando == false) {
            Estrategia e = new Estrategia();
            Areaconhecimento a = new Areaconhecimento();
            e.setEstrategia(txtEstrRecuAtivi2.getText());
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento2.getSelectedItem());
            a = (Areaconhecimento) cmbAreaConhecimento2.getSelectedItem();
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula2.getModel();
            Object[] obj = new Object[]{
                a,
                e,};
            tabelaConhecimento.addRow(obj);

            txtEstrRecuAtivi2.setText("");
            cmbAreaConhecimento2.setSelectedIndex(0);

        } else {
            DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula2.getModel();
            Estrategia e = (Estrategia) tabelaEstrategia.getValueAt(linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(txtEstrRecuAtivi2.getText(), linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(cmbAreaConhecimento2.getSelectedItem(), linhaSelecionada, 0);
            txtEstrRecuAtivi2.setText("");
            cmbAreaConhecimento2.setSelectedIndex(0);
            editando = false;
        }
    }//GEN-LAST:event_btnAdicionar2ActionPerformed

    private void btnRecuperar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperar2ActionPerformed
        linhaSelecionada = tblPlanoAula2.getSelectedRow();
        Estrategia e = (Estrategia) tblPlanoAula2.getModel().getValueAt(linhaSelecionada, 1);
        txtEstrRecuAtivi2.setText(e.getEstrategia());
        cmbAreaConhecimento2.setSelectedItem((Object) tblPlanoAula2.getModel().getValueAt(linhaSelecionada, 0));
        editando = true;
    }//GEN-LAST:event_btnRecuperar2ActionPerformed

    private void txtEstrRecuAtivi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstrRecuAtivi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstrRecuAtivi2KeyPressed

    private void txtObservacoes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacoes2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacoes2KeyPressed

    private void txtPrincipalObjetivoDia3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrincipalObjetivoDia3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrincipalObjetivoDia3KeyPressed

    private void txtAcolhidaAlunos3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcolhidaAlunos3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcolhidaAlunos3KeyPressed

    private void btnMais3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMais3ActionPerformed
        // TODO add your handling code here:
        String exception = "";

        try {

            areaConhecimentoDAO.create(instanciaAreaConhecimento());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (exception.equals("")) {
                JOptionPane.showMessageDialog(null, "Área cadastrado com sucesso!");
                txtConhecimento3.setText("");
                preencherCmbConhecimento();
            } else {
                //txtConhecimento.setText("");
                JOptionPane.showMessageDialog(null, "Deu ruim");
            }

        }
    }//GEN-LAST:event_btnMais3ActionPerformed

    private void tblPlanoAula3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoAula3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPlanoAula3MouseClicked

    private void btnAdicionar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar3ActionPerformed
        if (editando == false) {
            Estrategia e = new Estrategia();
            Areaconhecimento a = new Areaconhecimento();
            e.setEstrategia(txtEstrRecuAtivi3.getText());
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento3.getSelectedItem());
            a = (Areaconhecimento) cmbAreaConhecimento3.getSelectedItem();
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula3.getModel();
            Object[] obj = new Object[]{
                a,
                e,};
            tabelaConhecimento.addRow(obj);

            txtEstrRecuAtivi3.setText("");
            cmbAreaConhecimento3.setSelectedIndex(0);

        } else {
            DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula3.getModel();
            Estrategia e = (Estrategia) tabelaEstrategia.getValueAt(linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(txtEstrRecuAtivi3.getText(), linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(cmbAreaConhecimento3.getSelectedItem(), linhaSelecionada, 0);
            txtEstrRecuAtivi3.setText("");
            cmbAreaConhecimento3.setSelectedIndex(0);
            editando = false;
        }
    }//GEN-LAST:event_btnAdicionar3ActionPerformed

    private void btnRecuperar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperar3ActionPerformed
        linhaSelecionada = tblPlanoAula3.getSelectedRow();
        Estrategia e = (Estrategia) tblPlanoAula3.getModel().getValueAt(linhaSelecionada, 1);
        txtEstrRecuAtivi3.setText(e.getEstrategia());
        cmbAreaConhecimento3.setSelectedItem((Object) tblPlanoAula3.getModel().getValueAt(linhaSelecionada, 0));
        editando = true;
    }//GEN-LAST:event_btnRecuperar3ActionPerformed

    private void txtEstrRecuAtivi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstrRecuAtivi3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstrRecuAtivi3KeyPressed

    private void txtObservacoes3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacoes3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacoes3KeyPressed

    private void txtPrincipalObjetivoDia4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrincipalObjetivoDia4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrincipalObjetivoDia4KeyPressed

    private void txtAcolhidaAlunos4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcolhidaAlunos4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcolhidaAlunos4KeyPressed

    private void btnMais4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMais4ActionPerformed
        // TODO add your handling code here:
        String exception = "";

        try {

            areaConhecimentoDAO.create(instanciaAreaConhecimento());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (exception.equals("")) {
                JOptionPane.showMessageDialog(null, "Área cadastrado com sucesso!");
                txtConhecimento4.setText("");
                preencherCmbConhecimento();
            } else {
                //txtConhecimento.setText("");
                JOptionPane.showMessageDialog(null, "Deu ruim");
            }

        }
    }//GEN-LAST:event_btnMais4ActionPerformed

    private void tblPlanoAula4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanoAula4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPlanoAula4MouseClicked

    private void btnAdicionar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar4ActionPerformed
        if (editando == false) {
            Estrategia e = new Estrategia();
            Areaconhecimento a = new Areaconhecimento();
            e.setEstrategia(txtEstrRecuAtivi4.getText());
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento4.getSelectedItem());
            a = (Areaconhecimento) cmbAreaConhecimento4.getSelectedItem();
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula4.getModel();
            Object[] obj = new Object[]{
                a,
                e,};
            tabelaConhecimento.addRow(obj);

            txtEstrRecuAtivi4.setText("");
            cmbAreaConhecimento4.setSelectedIndex(0);

        } else {
            DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula4.getModel();
            Estrategia e = (Estrategia) tabelaEstrategia.getValueAt(linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(txtEstrRecuAtivi4.getText(), linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(cmbAreaConhecimento4.getSelectedItem(), linhaSelecionada, 0);
            txtEstrRecuAtivi4.setText("");
            cmbAreaConhecimento4.setSelectedIndex(0);
            editando = false;
        }
    }//GEN-LAST:event_btnAdicionar4ActionPerformed

    private void btnRecuperar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperar4ActionPerformed
        linhaSelecionada = tblPlanoAula4.getSelectedRow();
        Estrategia e = (Estrategia) tblPlanoAula4.getModel().getValueAt(linhaSelecionada, 1);
        txtEstrRecuAtivi4.setText(e.getEstrategia());
        cmbAreaConhecimento4.setSelectedItem((Object) tblPlanoAula4.getModel().getValueAt(linhaSelecionada, 0));
        editando = true;
    }//GEN-LAST:event_btnRecuperar4ActionPerformed

    private void txtEstrRecuAtivi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstrRecuAtivi4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstrRecuAtivi4KeyPressed

    private void txtObservacoes4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacoes4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacoes4KeyPressed

    private void tbpGuiasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpGuiasKeyPressed
        // TODO add your handling code here:        
        preencherCmbConhecimento();
    }//GEN-LAST:event_tbpGuiasKeyPressed

    private void btnInserirPlanoAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirPlanoAulaActionPerformed
        // TODO add your handling code here:
        ativarCampos();
        adicionarPlanoAula();
        txtDataInicio.setEnabled(false);
    }//GEN-LAST:event_btnInserirPlanoAulaActionPerformed

    private void btnVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVoltaActionPerformed

    private void btnEnviarPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarPlanoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarPlanoActionPerformed

    private void txtDataInicioCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtDataInicioCaretPositionChanged
        try {
            somadia();
        } catch (ParseException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtDataInicioCaretPositionChanged

    private void txtDataInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDataInicioPropertyChange
        // TODO add your handling code here:
        if (txtDataInicio.getDate() != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //DEFINE FORMATO DE DATA  
            Date datainic = txtDataInicio.getDate();
            Calendar c = Calendar.getInstance();
            c.setTime(datainic);
            c.add(Calendar.DATE, 5);
            String d = formato.format(c.getTime()); //CONVERTE PRA STRING
            try {
                txtDataFinal.setDate(formato.parse(d));
            } catch (ParseException ex) {
                Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtDataInicioPropertyChange

    private void btnSalvarPlanoAula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula1ActionPerformed
        salvaPlano();
    }//GEN-LAST:event_btnSalvarPlanoAula1ActionPerformed

    private void btnSalvarPlanoAula4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula4ActionPerformed
        salvaPlano();
    }//GEN-LAST:event_btnSalvarPlanoAula4ActionPerformed

    private void btnSalvarPlanoAula2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula2ActionPerformed
        salvaPlano();
    }//GEN-LAST:event_btnSalvarPlanoAula2ActionPerformed

    private void btnSalvarPlanoAula3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula3ActionPerformed
        salvaPlano();
    }//GEN-LAST:event_btnSalvarPlanoAula3ActionPerformed

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
                try {
                    new FrmPlanoAulaSemanal(null, null).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAdicionar1;
    private javax.swing.JButton btnAdicionar2;
    private javax.swing.JButton btnAdicionar3;
    private javax.swing.JButton btnAdicionar4;
    private javax.swing.JButton btnAnexar;
    private javax.swing.JButton btnAnexar1;
    private javax.swing.JButton btnAnexar2;
    private javax.swing.JButton btnAnexar3;
    private javax.swing.JButton btnAnexar4;
    private javax.swing.JButton btnEnviarPlano;
    private javax.swing.JButton btnInserirPlanoAula;
    private javax.swing.JButton btnMais;
    private javax.swing.JButton btnMais1;
    private javax.swing.JButton btnMais2;
    private javax.swing.JButton btnMais3;
    private javax.swing.JButton btnMais4;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JButton btnRecuperar1;
    private javax.swing.JButton btnRecuperar2;
    private javax.swing.JButton btnRecuperar3;
    private javax.swing.JButton btnRecuperar4;
    private javax.swing.JButton btnSalvarPlanoAula;
    private javax.swing.JButton btnSalvarPlanoAula1;
    private javax.swing.JButton btnSalvarPlanoAula2;
    private javax.swing.JButton btnSalvarPlanoAula3;
    private javax.swing.JButton btnSalvarPlanoAula4;
    private javax.swing.JButton btnVisualizarPdf;
    private javax.swing.JButton btnVolta;
    private javax.swing.JComboBox cmbAreaConhecimento;
    private javax.swing.JComboBox cmbAreaConhecimento1;
    private javax.swing.JComboBox cmbAreaConhecimento2;
    private javax.swing.JComboBox cmbAreaConhecimento3;
    private javax.swing.JComboBox cmbAreaConhecimento4;
    private javax.swing.JComboBox<String> cmbLicaoCasa;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
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
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPanel pnlQuarta;
    private javax.swing.JPanel pnlQuinta;
    private javax.swing.JPanel pnlSegunda;
    private javax.swing.JPanel pnlSexta;
    private javax.swing.JPanel pnlterca;
    private javax.swing.JTable tblPlanoAula;
    private javax.swing.JTable tblPlanoAula1;
    private javax.swing.JTable tblPlanoAula2;
    private javax.swing.JTable tblPlanoAula3;
    private javax.swing.JTable tblPlanoAula4;
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
    private javax.swing.JTextField txtConhecimento;
    private javax.swing.JTextField txtConhecimento1;
    private javax.swing.JTextField txtConhecimento2;
    private javax.swing.JTextField txtConhecimento3;
    private javax.swing.JTextField txtConhecimento4;
    private com.toedter.calendar.JDateChooser txtDataFinal;
    private com.toedter.calendar.JDateChooser txtDataInicio;
    private javax.swing.JTextArea txtEstrRecuAtivi;
    private javax.swing.JTextArea txtEstrRecuAtivi1;
    private javax.swing.JTextArea txtEstrRecuAtivi2;
    private javax.swing.JTextArea txtEstrRecuAtivi3;
    private javax.swing.JTextArea txtEstrRecuAtivi4;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextArea txtObservacoes1;
    private javax.swing.JTextArea txtObservacoes2;
    private javax.swing.JTextArea txtObservacoes3;
    private javax.swing.JTextArea txtObservacoes4;
    private javax.swing.JTextField txtPrincipalObjetivoDia;
    private javax.swing.JTextField txtPrincipalObjetivoDia1;
    private javax.swing.JTextField txtPrincipalObjetivoDia2;
    private javax.swing.JTextField txtPrincipalObjetivoDia3;
    private javax.swing.JTextField txtPrincipalObjetivoDia4;
    // End of variables declaration//GEN-END:variables
}
