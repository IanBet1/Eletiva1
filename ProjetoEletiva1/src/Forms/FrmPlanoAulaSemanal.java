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
import Controller.exceptions.NonexistentEntityException;
import conexao.Conexao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import static org.eclipse.persistence.jpa.rs.util.JPARSLogger.exception;
import static org.xhtmlrenderer.util.IOUtil.copyFile;

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
    String d;
    Planoaula pa;
    Diasemana ds;
    Estrategia es;
    DiasemanaHasEstrategia cruzamento;
    boolean editandoplano = false;
    boolean editandoplano1 = false;
    boolean editandoplano2 = false;
    boolean editandoplano3 = false;
    boolean editandoplano4 = false;
    int numBotaos = 0;
    String caminho;
    private Conexao con;

    String SegAnexo = "", SegLic = "", TercAnexo = "", TerLic = "", QuarAnexo = "", QuarLic = "", QuinAnexo = "", QuinLic = "", SexAnexo = "", SexLic = "";

    /**
     * Creates new form FrmPlanoAulaSemanal
     *
     * @param login
     * @param planoaula
     * @throws java.text.ParseException
     */
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
        cruzamento = new DiasemanaHasEstrategia();
        user = login;
        preencherCmbConhecimento();

        lblAnexo.setText("");
        lblAnexo1.setText("");
        lblAnexo2.setText("");
        lblAnexo3.setText("");
        lblAnexo4.setText("");

        lblAnexoLicao.setText("");
        lblAnexoLicao1.setText("");
        lblAnexoLicao2.setText("");
        lblAnexoLicao3.setText("");
        lblAnexoLicao4.setText("");

        txtAnexoLicao.setEnabled(false);
        txtAnexoLicao1.setEnabled(false);
        txtAnexoLicao2.setEnabled(false);
        txtAnexoLicao4.setEnabled(false);
        txtAnexoLicao3.setEnabled(false);
        if (planoaula == null) {
            desativarCampos();
            numBotaos = 5;
        } else {
            this.pa = planoaula;
            txtDataInicio.setDate(pa.getDatainicio());
            txtDataFinal.setDate(pa.getDatafim());
            txtDataInicio.setEnabled(false);
            txtDataFinal.setEnabled(false);
            if (recuperarSegunda() == false) {
                recuperarTerca();
                recuperarQuarta();
                recuperarQuinta();
                recuperarSexta();
            }
            btnInserirPlanoAula.setEnabled(false);
            chbLetivo.setEnabled(false);
            chbLetivo1.setEnabled(false);
            chbLetivo2.setEnabled(false);
            chbLetivo3.setEnabled(false);
            chbLetivo4.setEnabled(false);
            disponibilizaEnvio();
            if (this.pa.getStatus().equals("Aprovado")) {
                travaCampos0(false);
                travaCampos0b(false);
                travaCampos1(false);
                travaCampos1b(false);
                travaCampos2(false);
                travaCampos2b(false);
                travaCampos3(false);
                travaCampos3b(false);
                travaCampos4(false);
                travaCampos4b(false);
                desativarCampos();
            }
        }
    }

    private void disponibilizaEnvio() {
        if (btnSalvarPlanoAula.isEnabled()) {
            numBotaos = numBotaos + 1;
        }
        if (btnSalvarPlanoAula1.isEnabled()) {
            numBotaos = numBotaos + 1;
        }
        if (btnSalvarPlanoAula2.isEnabled()) {
            numBotaos = numBotaos + 1;
        }
        if (btnSalvarPlanoAula3.isEnabled()) {
            numBotaos = numBotaos + 1;
        }
        if (btnSalvarPlanoAula4.isEnabled()) {
            numBotaos = numBotaos + 1;
        }
    }

    private void desativarCampos() {
        btnEnviarPlano.setEnabled(false);

        btnSalvarPlanoAula.setEnabled(false);
        btnSalvarPlanoAula1.setEnabled(false);
        btnSalvarPlanoAula2.setEnabled(false);
        btnSalvarPlanoAula3.setEnabled(false);
        btnSalvarPlanoAula4.setEnabled(false);
        txtDataFinal.setEnabled(false);

        //pnlSegunda.enable(false);
        txtAcolhidaAlunos.setEnabled(false);
        txtPrincipalObjetivoDia.setEnabled(false);
        cmbAreaConhecimento.setEnabled(false);
        txtEstrRecuAtivi.setEnabled(false);
        btnAdicionar.setEnabled(false);
        btnRecuperar.setEnabled(false);
        txtObservacoes.setEnabled(false);
        txtAnexo.setEnabled(false);
        btnAnexar.setEnabled(false);
        btnAnexarLicao.setEnabled(false);
        chbLetivo.setEnabled(false);
        btnEditar.setEnabled(false);

        //pnlterca.enable(false);
        txtAcolhidaAlunos1.setEnabled(false);
        txtPrincipalObjetivoDia1.setEnabled(false);
        cmbAreaConhecimento1.setEnabled(false);
        txtEstrRecuAtivi1.setEnabled(false);
        btnAdicionar1.setEnabled(false);
        btnRecuperar1.setEnabled(false);
        txtObservacoes1.setEnabled(false);
        txtAnexo1.setEnabled(false);
        btnAnexarLicao1.setEnabled(false);
        btnAnexar1.setEnabled(false);
        chbLetivo1.setEnabled(false);
        btnEditar1.setEnabled(false);

        //pnlQuarta.enable(false);
        txtAcolhidaAlunos2.setEnabled(false);
        txtPrincipalObjetivoDia2.setEnabled(false);
        cmbAreaConhecimento2.setEnabled(false);
        txtEstrRecuAtivi2.setEnabled(false);
        btnAdicionar2.setEnabled(false);
        btnRecuperar2.setEnabled(false);
        txtObservacoes2.setEnabled(false);
        txtAnexo2.setEnabled(false);
        btnAnexarLicao2.setEnabled(false);
        btnAnexar2.setEnabled(false);
        chbLetivo2.setEnabled(false);
        btnEditar2.setEnabled(false);

        //pnlQuinta.enable(false);
        txtAcolhidaAlunos3.setEnabled(false);
        txtPrincipalObjetivoDia3.setEnabled(false);
        cmbAreaConhecimento3.setEnabled(false);
        txtEstrRecuAtivi3.setEnabled(false);
        btnAdicionar3.setEnabled(false);
        btnRecuperar3.setEnabled(false);
        txtObservacoes3.setEnabled(false);
        txtAnexo3.setEnabled(false);
        btnAnexarLicao3.setEnabled(false);
        btnAnexar3.setEnabled(false);
        chbLetivo3.setEnabled(false);
        btnEditar3.setEnabled(false);

        //pnlSexta.enable(false);
        txtAcolhidaAlunos4.setEnabled(false);
        txtPrincipalObjetivoDia4.setEnabled(false);
        cmbAreaConhecimento4.setEnabled(false);
        txtEstrRecuAtivi4.setEnabled(false);
        btnAdicionar4.setEnabled(false);
        btnRecuperar4.setEnabled(false);
        txtObservacoes4.setEnabled(false);
        txtAnexo4.setEnabled(false);
        btnAnexarLicao4.setEnabled(false);
        btnAnexar4.setEnabled(false);
        chbLetivo4.setEnabled(false);
        btnEditar4.setEnabled(false);
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
        cmbAreaConhecimento.setEnabled(true);
        txtEstrRecuAtivi.setEnabled(true);
        btnAdicionar.setEnabled(true);
        btnRecuperar.setEnabled(true);
        txtObservacoes.setEnabled(true);
        //  txtAnexo.setEnabled(true);
        btnAnexar.setEnabled(true);
        btnAnexarLicao.setEnabled(true);
        chbLetivo.setEnabled(true);

        //pnlterca.enable(true);
        txtAcolhidaAlunos1.setEnabled(true);
        txtPrincipalObjetivoDia1.setEnabled(true);
        cmbAreaConhecimento1.setEnabled(true);
        txtEstrRecuAtivi1.setEnabled(true);
        btnAdicionar1.setEnabled(true);
        btnRecuperar1.setEnabled(true);
        txtObservacoes1.setEnabled(true);
        //txtAnexo1.setEnabled(true);
        btnAnexar1.setEnabled(true);
        btnAnexarLicao1.setEnabled(true);
        chbLetivo1.setEnabled(true);

        //pnlQuarta.enable(true);
        txtAcolhidaAlunos2.setEnabled(true);
        txtPrincipalObjetivoDia2.setEnabled(true);
        cmbAreaConhecimento2.setEnabled(true);
        txtEstrRecuAtivi2.setEnabled(true);
        btnAdicionar2.setEnabled(true);
        btnRecuperar2.setEnabled(true);
        txtObservacoes2.setEnabled(true);
        //txtAnexo2.setEnabled(true);
        btnAnexar2.setEnabled(true);
        btnAnexarLicao2.setEnabled(true);
        chbLetivo2.setEnabled(true);

        //pnlQuinta.enable(true);
        txtAcolhidaAlunos3.setEnabled(true);
        txtPrincipalObjetivoDia3.setEnabled(true);
        cmbAreaConhecimento3.setEnabled(true);
        txtEstrRecuAtivi3.setEnabled(true);
        btnAdicionar3.setEnabled(true);
        btnRecuperar3.setEnabled(true);
        txtObservacoes3.setEnabled(true);
        //txtAnexo3.setEnabled(true);
        btnAnexar3.setEnabled(true);
        btnAnexarLicao3.setEnabled(true);
        chbLetivo3.setEnabled(true);

        //pnlSexta.enable(true);
        txtAcolhidaAlunos4.setEnabled(true);
        txtPrincipalObjetivoDia4.setEnabled(true);
        cmbAreaConhecimento4.setEnabled(true);
        txtEstrRecuAtivi4.setEnabled(true);
        btnAdicionar4.setEnabled(true);
        btnRecuperar4.setEnabled(true);
        txtObservacoes4.setEnabled(true);
        //txtAnexo4.setEnabled(true);
        btnAnexar4.setEnabled(true);
        btnAnexarLicao4.setEnabled(true);
        chbLetivo4.setEnabled(true);
    }

    private void somadia() throws ParseException {

        java.util.Date pega = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.d = formato.format(pega);
        LocalDate data = LocalDate.parse(d, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        data = data.plusDays(4);
        //txtData.setText(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtDataFinal.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

    }

    private boolean adicionarPlanoAula() {
        Planoaula p = new Planoaula();
        boolean teste = false;
        try {
            p = instanciaPlanoaula();
            List<Planoaula> pl = new ArrayList();
            pl = planoaulaDAO.getPlano1(p);
            if (pl.isEmpty()) {
                planoaulaDAO.create(p);
                this.pa = planoaulaDAO.getPlano(p);
                JOptionPane.showMessageDialog(null, "Plano Salvo");
                teste = true;
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecione outra data!\n Já existe um plano nesta data");
                txtDataInicio.setDate(null);
                txtDataFinal.setDate(null);

            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            return teste;
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

    private void editarDiaSemana() {
        Diasemana d = new Diasemana();
        try {
            d = diasemanaDAO.getDia(this.ds);
            d = editaDiaSemana(d);
            diasemanaDAO.edit(d);
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

    private void editarEstrategia() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDataInicio = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtDataFinal = new com.toedter.calendar.JDateChooser();
        btnInserirPlanoAula = new javax.swing.JButton();
        tbpGuias = new javax.swing.JTabbedPane();
        pnlSegunda = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAcolhidaAlunos = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        cmbAreaConhecimento = new javax.swing.JComboBox();
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
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        btnSalvarPlanoAula = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        chbLetivo = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        txtAnexoLicao = new javax.swing.JTextField();
        btnAnexarLicao = new javax.swing.JButton();
        lblAnexo = new javax.swing.JLabel();
        lblAnexoLicao = new javax.swing.JLabel();
        pnlterca = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAcolhidaAlunos1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
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
        jLabel19 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtObservacoes1 = new javax.swing.JTextArea();
        btnSalvarPlanoAula1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        chbLetivo1 = new javax.swing.JCheckBox();
        jLabel44 = new javax.swing.JLabel();
        txtAnexoLicao1 = new javax.swing.JTextField();
        btnAnexarLicao1 = new javax.swing.JButton();
        lblAnexoLicao1 = new javax.swing.JLabel();
        lblAnexo1 = new javax.swing.JLabel();
        pnlQuarta = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtAcolhidaAlunos2 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
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
        jLabel27 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtObservacoes2 = new javax.swing.JTextArea();
        btnSalvarPlanoAula2 = new javax.swing.JButton();
        btnEditar2 = new javax.swing.JButton();
        chbLetivo2 = new javax.swing.JCheckBox();
        jLabel45 = new javax.swing.JLabel();
        txtAnexoLicao2 = new javax.swing.JTextField();
        btnAnexarLicao2 = new javax.swing.JButton();
        lblAnexoLicao2 = new javax.swing.JLabel();
        lblAnexo2 = new javax.swing.JLabel();
        pnlQuinta = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtAcolhidaAlunos3 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
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
        jLabel35 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtObservacoes3 = new javax.swing.JTextArea();
        btnSalvarPlanoAula3 = new javax.swing.JButton();
        btnEditar3 = new javax.swing.JButton();
        chbLetivo3 = new javax.swing.JCheckBox();
        jLabel47 = new javax.swing.JLabel();
        txtAnexoLicao3 = new javax.swing.JTextField();
        btnAnexarLicao3 = new javax.swing.JButton();
        lblAnexoLicao3 = new javax.swing.JLabel();
        lblAnexo3 = new javax.swing.JLabel();
        pnlSexta = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtPrincipalObjetivoDia4 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtAcolhidaAlunos4 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
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
        jLabel43 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtObservacoes4 = new javax.swing.JTextArea();
        btnSalvarPlanoAula4 = new javax.swing.JButton();
        btnEditar4 = new javax.swing.JButton();
        chbLetivo4 = new javax.swing.JCheckBox();
        jLabel46 = new javax.swing.JLabel();
        txtAnexoLicao4 = new javax.swing.JTextField();
        btnAnexarLicao4 = new javax.swing.JButton();
        lblAnexoLicao4 = new javax.swing.JLabel();
        lblAnexo4 = new javax.swing.JLabel();
        btnEnviarPlano = new javax.swing.JButton();
        btnVisualizarPdf = new javax.swing.JButton();
        btnVolta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Plano Aula");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(16, 37, 63));

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Plano de Aula Semanal");

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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(232, 244, 248));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Data de Início:");

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

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Data Final:");

        btnInserirPlanoAula.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnInserirPlanoAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Criar Plano de Aula.png"))); // NOI18N
        btnInserirPlanoAula.setText("Criar Novo Plano de Aula");
        btnInserirPlanoAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirPlanoAulaActionPerformed(evt);
            }
        });

        tbpGuias.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        tbpGuias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbpGuiasKeyPressed(evt);
            }
        });

        pnlSegunda.setBackground(new java.awt.Color(255, 255, 255));

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
        btnAnexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarActionPerformed(evt);
            }
        });

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

        btnSalvarPlanoAula.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSalvarPlanoAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Salvar.png"))); // NOI18N
        btnSalvarPlanoAula.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAulaActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        chbLetivo.setText("Dia não Letivo");
        chbLetivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbLetivoItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setText("Lição de Casa:");

        btnAnexarLicao.setText("Anexar");
        btnAnexarLicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarLicaoActionPerformed(evt);
            }
        });

        lblAnexo.setText("jLabel18");

        lblAnexoLicao.setText("jLabel18");

        javax.swing.GroupLayout pnlSegundaLayout = new javax.swing.GroupLayout(pnlSegunda);
        pnlSegunda.setLayout(pnlSegundaLayout);
        pnlSegundaLayout.setHorizontalGroup(
            pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSegundaLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrincipalObjetivoDia, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(43, 43, 43)
                                .addComponent(txtAcolhidaAlunos)))
                        .addGap(18, 18, 18)
                        .addComponent(chbLetivo))
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAreaConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62)
                                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAdicionar)
                                    .addComponent(btnRecuperar))))
                        .addGap(46, 46, 46)
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(jLabel8))))))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar))
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAnexoLicao)
                    .addComponent(txtAnexo, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addComponent(btnAnexarLicao, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAnexoLicao))
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addComponent(btnAnexar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAnexo))))
        );
        pnlSegundaLayout.setVerticalGroup(
            pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSegundaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4))
                    .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrincipalObjetivoDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chbLetivo)))
                .addGap(16, 16, 16)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5))
                    .addComponent(txtAcolhidaAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmbAreaConhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addGap(6, 6, 6)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSegundaLayout.createSequentialGroup()
                                .addComponent(btnAdicionar)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecuperar))))
                    .addGroup(pnlSegundaLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar)
                    .addComponent(lblAnexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtAnexoLicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexarLicao)
                    .addComponent(lblAnexoLicao))
                .addGap(32, 32, 32)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSegundaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPlanoAula)
                    .addComponent(btnEditar))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Segunda - Feira", pnlSegunda);
        pnlSegunda.getAccessibleContext().setAccessibleName("pnllSegundaFeira");

        pnlterca.setBackground(new java.awt.Color(255, 255, 255));

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
        btnAnexar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexar1ActionPerformed(evt);
            }
        });

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
        btnSalvarPlanoAula1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Salvar.png"))); // NOI18N
        btnSalvarPlanoAula1.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula1ActionPerformed(evt);
            }
        });

        btnEditar1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Editar.png"))); // NOI18N
        btnEditar1.setText("Editar");
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        chbLetivo1.setText("Dia não Letivo");
        chbLetivo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbLetivo1ItemStateChanged(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel44.setText("Lição de Casa:");

        btnAnexarLicao1.setText("Anexar");
        btnAnexarLicao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarLicao1ActionPerformed(evt);
            }
        });

        lblAnexoLicao1.setText("jLabel18");

        lblAnexo1.setText("jLabel18");

        javax.swing.GroupLayout pnltercaLayout = new javax.swing.GroupLayout(pnlterca);
        pnlterca.setLayout(pnltercaLayout);
        pnltercaLayout.setHorizontalGroup(
            pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnltercaLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrincipalObjetivoDia1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(43, 43, 43)
                                .addComponent(txtAcolhidaAlunos1)))
                        .addGap(18, 18, 18)
                        .addComponent(chbLetivo1))
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAreaConhecimento1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar1)
                                    .addComponent(btnRecuperar1))))
                        .addGap(45, 45, 45)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel15))))))
            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar1))
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel44))
                .addGap(18, 18, 18)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtAnexoLicao1)
                    .addComponent(txtAnexo1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnexarLicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnexoLicao1)
                    .addComponent(lblAnexo1)))
        );
        pnltercaLayout.setVerticalGroup(
            pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnltercaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel12))
                    .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrincipalObjetivoDia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chbLetivo1)))
                .addGap(16, 16, 16)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13))
                    .addComponent(txtAcolhidaAlunos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cmbAreaConhecimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel16)
                        .addGap(6, 6, 6)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnltercaLayout.createSequentialGroup()
                                .addComponent(btnAdicionar1)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecuperar1))))
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtAnexo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexar1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(txtAnexoLicao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexarLicao1)
                            .addComponent(lblAnexoLicao1)))
                    .addGroup(pnltercaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblAnexo1)))
                .addGap(32, 32, 32)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnltercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPlanoAula1)
                    .addComponent(btnEditar1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Terça - Feira", pnlterca);
        pnlterca.getAccessibleContext().setAccessibleName("pnlTercaFeira");

        pnlQuarta.setBackground(new java.awt.Color(255, 255, 255));

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
        btnAnexar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexar2ActionPerformed(evt);
            }
        });

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
        btnSalvarPlanoAula2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Salvar.png"))); // NOI18N
        btnSalvarPlanoAula2.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula2ActionPerformed(evt);
            }
        });

        btnEditar2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Editar.png"))); // NOI18N
        btnEditar2.setText("Editar");
        btnEditar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar2ActionPerformed(evt);
            }
        });

        chbLetivo2.setText("Dia não Letivo");
        chbLetivo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbLetivo2ItemStateChanged(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel45.setText("Lição de Casa:");

        btnAnexarLicao2.setText("Anexar");
        btnAnexarLicao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarLicao2ActionPerformed(evt);
            }
        });

        lblAnexoLicao2.setText("jLabel18");

        lblAnexo2.setText("jLabel18");

        javax.swing.GroupLayout pnlQuartaLayout = new javax.swing.GroupLayout(pnlQuarta);
        pnlQuarta.setLayout(pnlQuartaLayout);
        pnlQuartaLayout.setHorizontalGroup(
            pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuartaLayout.createSequentialGroup()
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAnexoLicao2)
                            .addComponent(txtAnexo2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAnexarLicao2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexar2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnexoLicao2)
                            .addComponent(lblAnexo2)))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuartaLayout.createSequentialGroup()
                                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuartaLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPrincipalObjetivoDia2, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(43, 43, 43)
                                        .addComponent(txtAcolhidaAlunos2)))
                                .addGap(18, 18, 18)
                                .addComponent(chbLetivo2))
                            .addGroup(pnlQuartaLayout.createSequentialGroup()
                                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAreaConhecimento2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(55, 55, 55)
                                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAdicionar2)
                                            .addComponent(btnRecuperar2))))
                                .addGap(59, 59, 59)
                                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                                        .addGap(154, 154, 154)
                                        .addComponent(jLabel23))
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSalvarPlanoAula2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlQuartaLayout.setVerticalGroup(
            pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuartaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel20))
                    .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrincipalObjetivoDia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chbLetivo2)))
                .addGap(16, 16, 16)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel21))
                    .addComponent(txtAcolhidaAlunos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cmbAreaConhecimento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)))
                .addGap(19, 19, 19)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuartaLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel24)
                                .addGap(6, 6, 6)
                                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlQuartaLayout.createSequentialGroup()
                                        .addComponent(btnAdicionar2)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnRecuperar2))))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtAnexo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexar2)))
                    .addComponent(lblAnexo2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(txtAnexoLicao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnexarLicao2))
                    .addComponent(lblAnexoLicao2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(32, 32, 32)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQuartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPlanoAula2)
                    .addComponent(btnEditar2))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Quarta - Feira", pnlQuarta);
        pnlQuarta.getAccessibleContext().setAccessibleName("pnlQuartaFeira");

        pnlQuinta.setBackground(new java.awt.Color(255, 255, 255));

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
        btnAnexar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexar3ActionPerformed(evt);
            }
        });

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
        btnSalvarPlanoAula3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Salvar.png"))); // NOI18N
        btnSalvarPlanoAula3.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula3ActionPerformed(evt);
            }
        });

        btnEditar3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Editar.png"))); // NOI18N
        btnEditar3.setText("Editar");
        btnEditar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar3ActionPerformed(evt);
            }
        });

        chbLetivo3.setText("Dia não Letivo");
        chbLetivo3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbLetivo3ItemStateChanged(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel47.setText("Lição de Casa:");

        btnAnexarLicao3.setText("Anexar");
        btnAnexarLicao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarLicao3ActionPerformed(evt);
            }
        });

        lblAnexoLicao3.setText("jLabel18");

        lblAnexo3.setText("jLabel18");

        javax.swing.GroupLayout pnlQuintaLayout = new javax.swing.GroupLayout(pnlQuinta);
        pnlQuinta.setLayout(pnlQuintaLayout);
        pnlQuintaLayout.setHorizontalGroup(
            pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuintaLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrincipalObjetivoDia3, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                            .addGroup(pnlQuintaLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(43, 43, 43)
                                .addComponent(txtAcolhidaAlunos3)))
                        .addGap(18, 18, 18)
                        .addComponent(chbLetivo3))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAreaConhecimento3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(63, 63, 63)
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuintaLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel31))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar3))
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuintaLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18))
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(15, 15, 15)))
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtAnexoLicao3)
                    .addComponent(txtAnexo3, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnexarLicao3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnexoLicao3)
                    .addComponent(lblAnexo3)))
        );
        pnlQuintaLayout.setVerticalGroup(
            pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuintaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel28))
                    .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrincipalObjetivoDia3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chbLetivo3)))
                .addGap(16, 16, 16)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel29))
                    .addComponent(txtAcolhidaAlunos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(cmbAreaConhecimento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)))
                .addGap(6, 6, 6)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlQuintaLayout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtAnexo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexar3)
                            .addComponent(lblAnexo3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(txtAnexoLicao3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexarLicao3)))
                    .addComponent(lblAnexoLicao3))
                .addGap(32, 32, 32)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQuintaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPlanoAula3)
                    .addComponent(btnEditar3))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Quinta - Feira", pnlQuinta);
        pnlQuinta.getAccessibleContext().setAccessibleName("pnlQuintaFeira");
        pnlQuinta.getAccessibleContext().setAccessibleDescription("");

        pnlSexta.setBackground(new java.awt.Color(255, 255, 255));

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
        btnAnexar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexar4ActionPerformed(evt);
            }
        });

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
        btnSalvarPlanoAula4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Salvar.png"))); // NOI18N
        btnSalvarPlanoAula4.setText("Salvar Rascunho Temporariamente");
        btnSalvarPlanoAula4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPlanoAula4ActionPerformed(evt);
            }
        });

        btnEditar4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Editar.png"))); // NOI18N
        btnEditar4.setText("Editar");
        btnEditar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar4ActionPerformed(evt);
            }
        });

        chbLetivo4.setText("Dia não Letivo");
        chbLetivo4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbLetivo4ItemStateChanged(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel46.setText("Lição de Casa:");

        btnAnexarLicao4.setText("Anexar");
        btnAnexarLicao4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarLicao4ActionPerformed(evt);
            }
        });

        lblAnexoLicao4.setText("jLabel18");

        lblAnexo4.setText("jLabel18");

        javax.swing.GroupLayout pnlSextaLayout = new javax.swing.GroupLayout(pnlSexta);
        pnlSexta.setLayout(pnlSextaLayout);
        pnlSextaLayout.setHorizontalGroup(
            pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSextaLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrincipalObjetivoDia4, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                            .addGroup(pnlSextaLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(43, 43, 43)
                                .addComponent(txtAcolhidaAlunos4)))
                        .addGap(18, 18, 18)
                        .addComponent(chbLetivo4))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAreaConhecimento4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSextaLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel39))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvarPlanoAula4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar4))
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtAnexoLicao4)
                    .addComponent(txtAnexo4, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnexarLicao4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnexar4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnexoLicao4)
                    .addComponent(lblAnexo4)))
        );
        pnlSextaLayout.setVerticalGroup(
            pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSextaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel36))
                    .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrincipalObjetivoDia4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chbLetivo4)))
                .addGap(16, 16, 16)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel37))
                    .addComponent(txtAcolhidaAlunos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(cmbAreaConhecimento4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39)))
                .addGap(6, 6, 6)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSextaLayout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtAnexo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexar4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(txtAnexoLicao4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnexarLicao4)))
                    .addGroup(pnlSextaLayout.createSequentialGroup()
                        .addComponent(lblAnexo4)
                        .addGap(29, 29, 29))
                    .addComponent(lblAnexoLicao4))
                .addGap(32, 32, 32)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSextaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPlanoAula4)
                    .addComponent(btnEditar4))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tbpGuias.addTab("Sexta - Feira", pnlSexta);
        pnlSexta.getAccessibleContext().setAccessibleName("pnlSextaFeira");

        btnEnviarPlano.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEnviarPlano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Enviar Plano de Aula Semanal.png"))); // NOI18N
        btnEnviarPlano.setText("Enviar Plano  de Aula Semanal");
        btnEnviarPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarPlanoActionPerformed(evt);
            }
        });

        btnVisualizarPdf.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnVisualizarPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/PDF.png"))); // NOI18N
        btnVisualizarPdf.setText("Visualizar arquivo em PDF");
        btnVisualizarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarPdfActionPerformed(evt);
            }
        });

        btnVolta.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ícones/Voltar.png"))); // NOI18N
        btnVolta.setText("Voltar");
        btnVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(btnEnviarPlano)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizarPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVolta)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(btnInserirPlanoAula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpGuias, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviarPlano)
                    .addComponent(btnVisualizarPdf)
                    .addComponent(btnVolta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpGuias.getAccessibleContext().setAccessibleName("tblPainel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1015, 765));
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

    private String validacaoTodosDias() {
        String mensagem = "Favor preencher o(s) seguinte(s) campo(s):\n";

        if ("".equals(txtPrincipalObjetivoDia.getText())) {
            mensagem = mensagem + " Objetivo de Segunda-feira;\n";
        }
        if ("".equals(txtAcolhidaAlunos.getText())) {
            mensagem = mensagem + " Acolhida de Segunda-feira;\n";
        }
        if ("".equals(txtPrincipalObjetivoDia1.getText())) {
            mensagem = mensagem + " Objetivo de Terça-feira;\n";
        }
        if ("".equals(txtAcolhidaAlunos1.getText())) {
            mensagem = mensagem + " Acolhida de Terça-feira;\n";
        }
        if ("".equals(txtPrincipalObjetivoDia2.getText())) {
            mensagem = mensagem + " Objetivo de Quarta-feira;\n";
        }
        if ("".equals(txtAcolhidaAlunos2.getText())) {
            mensagem = mensagem + " Acolhida de Quarta-feira;\n";
        }
        if ("".equals(txtPrincipalObjetivoDia3.getText())) {
            mensagem = mensagem + " Objetivo de Quinta-feira;\n";
        }
        if ("".equals(txtAcolhidaAlunos3.getText())) {
            mensagem = mensagem + " Acolhida de Quinta-feira;\n";
        }
        if ("".equals(txtPrincipalObjetivoDia4.getText())) {
            mensagem = mensagem + " Objetivo de Sexta-feira;\n";
        }
        if ("".equals(txtAcolhidaAlunos3.getText())) {
            mensagem = mensagem + " Acolhida de Sexta-feira;\n";
        }
        return mensagem;
    }

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

    public void instanciaEstrategia() throws Exception {
        int obj = tbpGuias.getSelectedIndex();
        Estrategia e = new Estrategia();
        DefaultTableModel tabelaPlanoAula0 = (DefaultTableModel) tblPlanoAula.getModel();
        DefaultTableModel tabelaPlanoAula1 = (DefaultTableModel) tblPlanoAula1.getModel();
        DefaultTableModel tabelaPlanoAula2 = (DefaultTableModel) tblPlanoAula2.getModel();
        DefaultTableModel tabelaPlanoAula3 = (DefaultTableModel) tblPlanoAula3.getModel();
        DefaultTableModel tabelaPlanoAula4 = (DefaultTableModel) tblPlanoAula4.getModel();
        switch (obj) {
            case 0:
                excluirEstrategiaSegunda();
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
                excluirEstrategiaTerca();
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
                excluirEstrategiaQuarta();
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
                excluirEstrategiaQuinta();
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
                excluirEstrategiaSexta();
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
    }

    public Diasemana editaDiaSemana(Diasemana d) {
        if (tbpGuias.getSelectedIndex() == 0) {
            d.setPrincipalObj(txtPrincipalObjetivoDia.getText());
            d.setAcolhida(txtAcolhidaAlunos.getText());
            d.setAnexos(txtAnexo.getText());
            d.setLicaodecasa(txtAnexoLicao.getText());
            d.setObservacoes(txtObservacoes.getText());
        }
        if (tbpGuias.getSelectedIndex() == 1) {
            d.setPrincipalObj(txtPrincipalObjetivoDia1.getText());
            d.setAcolhida(txtAcolhidaAlunos1.getText());
            d.setAnexos(txtAnexo1.getText());
            d.setLicaodecasa(txtAnexoLicao1.getText());
            d.setObservacoes(txtObservacoes1.getText());
        }
        if (tbpGuias.getSelectedIndex() == 2) {
            d.setPrincipalObj(txtPrincipalObjetivoDia2.getText());
            d.setAcolhida(txtAcolhidaAlunos2.getText());
            d.setAnexos(txtAnexo2.getText());
            d.setLicaodecasa(txtAnexoLicao2.getText());
            d.setObservacoes(txtObservacoes2.getText());
        }
        if (tbpGuias.getSelectedIndex() == 3) {
            d.setPrincipalObj(txtPrincipalObjetivoDia3.getText());
            d.setAcolhida(txtAcolhidaAlunos3.getText());
            d.setAnexos(txtAnexo3.getText());
            d.setLicaodecasa(txtAnexoLicao4.getText());
            d.setObservacoes(txtObservacoes3.getText());
        }
        if (tbpGuias.getSelectedIndex() == 4) {
            d.setPrincipalObj(txtPrincipalObjetivoDia4.getText());
            d.setAcolhida(txtAcolhidaAlunos4.getText());
            d.setAnexos(txtAnexo4.getText());
            d.setLicaodecasa(txtAnexoLicao3.getText());
            d.setObservacoes(txtObservacoes4.getText());
        }
        return d;
    }

    public Diasemana instanciaDiaSemana() {
        Diasemana d = new Diasemana();
        if (tbpGuias.getSelectedIndex() == 0) {
            d.setDia("Segunda-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia.getText());
            d.setAcolhida(txtAcolhidaAlunos.getText());
            d.setAnexos(caminho + "Segunda-Feira\\" + lblAnexo.getText());
            d.setLicaodecasa(caminho + "Segunda-Feira\\" + lblAnexoLicao.getText());
            d.setObservacoes(txtObservacoes.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 1) {
            d.setDia("Terça-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia1.getText());
            d.setAcolhida(txtAcolhidaAlunos1.getText());
            d.setAnexos(caminho + "Terça-Feira\\" + lblAnexo1.getText());
            d.setLicaodecasa(caminho + "Terça-Feira\\" + lblAnexoLicao1.getText());
            d.setObservacoes(txtObservacoes1.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 2) {
            d.setDia("Quarta-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia2.getText());
            d.setAcolhida(txtAcolhidaAlunos2.getText());
            d.setAnexos(caminho + "Quarta-Feira\\" + lblAnexo2.getText());
            d.setLicaodecasa(caminho + "Quarta-Feira\\" + lblAnexoLicao2.getText());
            d.setObservacoes(txtObservacoes2.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 3) {
            d.setDia("Quinta-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia3.getText());
            d.setAcolhida(txtAcolhidaAlunos3.getText());
            d.setAnexos(caminho + "Quinta-Feira\\" + lblAnexo3.getText());
            d.setLicaodecasa(caminho + "Quinta-Feira\\" + lblAnexoLicao3.getText());
            d.setObservacoes(txtObservacoes3.getText());
            d.setDatadiasemana(new Date());
        }
        if (tbpGuias.getSelectedIndex() == 4) {
            d.setDia("Sexta-Feira");
            d.setPrincipalObj(txtPrincipalObjetivoDia4.getText());
            d.setAcolhida(txtAcolhidaAlunos4.getText());
            d.setAnexos(caminho + "Sexta-Feira\\" + lblAnexo4.getText());
            d.setLicaodecasa(caminho + "Sexta-Feira\\" + lblAnexoLicao4.getText());
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
            e.setEstrategia(txtEstrRecuAtivi.getText());
            tabelaEstrategia.setValueAt(e, linhaSelecionada, 1);
            tabelaEstrategia.setValueAt(cmbAreaConhecimento.getSelectedItem(), linhaSelecionada, 0);
            txtEstrRecuAtivi.setText("");
            cmbAreaConhecimento.setSelectedIndex(0);
            editando = false;
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarPlanoAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAulaActionPerformed
        if (editandoplano == false) {
            if (salvaPlano()) {
                btnEditar.setEnabled(true);
                if (chbLetivo.isSelected() == false) {
                    File diretorio = new File(caminho + "Segunda-Feira\\");
                    diretorio.mkdir();
                    File destinoMatriz, destinoLicao, origemMatriz, origemLicao;
                    destinoMatriz = new File(caminho + "Segunda-Feira\\");
                    destinoLicao = new File(caminho + "Segunda-Feira\\");
                    origemMatriz = new File(txtAnexo.getText());
                    origemLicao = new File(txtAnexoLicao.getText());

                    try {
                        if (!txtAnexo.getText().equals("")) {
                            copyFile(origemMatriz, destinoMatriz);
                            //SegAnexo = destinoMatriz.toString();
                        }
                        if (!txtAnexoLicao.getText().equals("")) {
                            copyFile(origemLicao, destinoLicao);
                            //SegLic = destinoLicao.toString();
                        }

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString() + "\nVerifique a conexão de rede de sua máquina e do servidor!");
                        Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            if (numBotaos == 0) {
                btnEnviarPlano.setEnabled(true);
            }
        } else {
            try {
                editarPlano();
                btnEditar.setEnabled(true);
                numBotaos = numBotaos - 1;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarPlanoAulaActionPerformed

    private boolean salvaPlano() {
        boolean teste = false;
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
            numBotaos = numBotaos - 1;
            teste = true;
            switch (tbpGuias.getSelectedIndex()) {
                case 0:
                    travaCampos0(false);
                    break;
                case 1:
                    travaCampos1(false);
                    break;
                case 2:
                    travaCampos2(false);
                    break;
                case 3:
                    travaCampos3(false);
                    break;
                case 4:
                    travaCampos4(false);
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, mensagem);

        }
        return teste;
    }

    private void editarPlano() throws NonexistentEntityException {
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
            editarDiaSemana();
            switch (tbpGuias.getSelectedIndex()) {
                case 0:
                    excluirEstrategiaSegunda();
                    break;
                case 1:
                    excluirEstrategiaTerca();
                    break;
                case 2:
                    excluirEstrategiaQuarta();
                    break;
                case 3:
                    excluirEstrategiaQuinta();
                    break;
                case 4:
                    excluirEstrategiaSexta();
                    break;
                default:
                    break;
            }
            editarEstrategia();
            mensagem = "";
            switch (tbpGuias.getSelectedIndex()) {
                case 0:
                    travaCampos0(false);
                    editandoplano = false;
                    break;
                case 1:
                    travaCampos1(false);
                    editandoplano1 = false;
                    break;
                case 2:
                    travaCampos2(false);
                    editandoplano2 = false;
                    break;
                case 3:
                    travaCampos3(false);
                    editandoplano3 = false;
                    break;
                case 4:
                    travaCampos4(false);
                    editandoplano4 = false;
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, mensagem);
        }
    }

    private void travaCampos0(boolean statuscampos) {
        txtPrincipalObjetivoDia.setEnabled(statuscampos);
        txtAcolhidaAlunos.setEnabled(statuscampos);
        cmbAreaConhecimento.setEnabled(statuscampos);
        txtEstrRecuAtivi.setEnabled(statuscampos);
        btnAdicionar.setEnabled(statuscampos);
        btnRecuperar.setEnabled(statuscampos);
        txtAnexo.setEnabled(statuscampos);
        btnAnexar.setEnabled(statuscampos);
        txtObservacoes.setEnabled(statuscampos);
        tblPlanoAula.setEnabled(statuscampos);
        btnSalvarPlanoAula.setEnabled(statuscampos);
        chbLetivo.setEnabled(statuscampos);
        txtAnexoLicao.setEnabled(statuscampos);
    }

    private void travaCampos0b(boolean statuscampos) {
        txtPrincipalObjetivoDia.setEnabled(statuscampos);
        txtAcolhidaAlunos.setEnabled(statuscampos);
        cmbAreaConhecimento.setEnabled(statuscampos);
        txtEstrRecuAtivi.setEnabled(statuscampos);
        btnAdicionar.setEnabled(statuscampos);
        btnRecuperar.setEnabled(statuscampos);
        txtAnexo.setEnabled(statuscampos);
        btnAnexar.setEnabled(statuscampos);
        txtObservacoes.setEnabled(statuscampos);
        tblPlanoAula.setEnabled(statuscampos);
        chbLetivo.setEnabled(statuscampos);
        txtAnexoLicao.setEnabled(statuscampos);
    }

    private void travaCampos1(boolean statuscampos) {
        txtPrincipalObjetivoDia1.setEnabled(statuscampos);
        txtAcolhidaAlunos1.setEnabled(statuscampos);
        cmbAreaConhecimento1.setEnabled(statuscampos);
        txtEstrRecuAtivi1.setEnabled(statuscampos);
        btnAdicionar1.setEnabled(statuscampos);
        btnRecuperar1.setEnabled(statuscampos);
        txtAnexo1.setEnabled(statuscampos);
        btnAnexar1.setEnabled(statuscampos);
        txtObservacoes1.setEnabled(statuscampos);
        tblPlanoAula1.setEnabled(statuscampos);
        btnSalvarPlanoAula1.setEnabled(statuscampos);
        chbLetivo1.setEnabled(statuscampos);
        txtAnexoLicao1.setEnabled(statuscampos);
    }

    private void travaCampos1b(boolean statuscampos) {
        txtPrincipalObjetivoDia1.setEnabled(statuscampos);
        txtAcolhidaAlunos1.setEnabled(statuscampos);
        cmbAreaConhecimento1.setEnabled(statuscampos);
        txtEstrRecuAtivi1.setEnabled(statuscampos);
        btnAdicionar1.setEnabled(statuscampos);
        btnRecuperar1.setEnabled(statuscampos);
        txtAnexo1.setEnabled(statuscampos);
        btnAnexar1.setEnabled(statuscampos);
        txtObservacoes1.setEnabled(statuscampos);
        tblPlanoAula1.setEnabled(statuscampos);
        chbLetivo1.setEnabled(statuscampos);
        txtAnexoLicao1.setEnabled(statuscampos);
    }

    private void travaCampos2(boolean statuscampos) {
        txtPrincipalObjetivoDia2.setEnabled(statuscampos);
        txtAcolhidaAlunos2.setEnabled(statuscampos);
        cmbAreaConhecimento2.setEnabled(statuscampos);
        txtEstrRecuAtivi2.setEnabled(statuscampos);
        btnAdicionar2.setEnabled(statuscampos);
        btnRecuperar2.setEnabled(statuscampos);
        txtAnexo2.setEnabled(statuscampos);
        btnAnexar2.setEnabled(statuscampos);
        txtObservacoes2.setEnabled(statuscampos);
        tblPlanoAula2.setEnabled(statuscampos);
        btnSalvarPlanoAula2.setEnabled(statuscampos);
        chbLetivo2.setEnabled(statuscampos);
        txtAnexoLicao2.setEnabled(statuscampos);
    }

    private void travaCampos2b(boolean statuscampos) {
        txtPrincipalObjetivoDia2.setEnabled(statuscampos);
        txtAcolhidaAlunos2.setEnabled(statuscampos);
        cmbAreaConhecimento2.setEnabled(statuscampos);
        txtEstrRecuAtivi2.setEnabled(statuscampos);
        btnAdicionar2.setEnabled(statuscampos);
        btnRecuperar2.setEnabled(statuscampos);
        txtAnexo2.setEnabled(statuscampos);
        btnAnexar2.setEnabled(statuscampos);
        txtObservacoes2.setEnabled(statuscampos);
        tblPlanoAula2.setEnabled(statuscampos);
        chbLetivo2.setEnabled(statuscampos);
        txtAnexoLicao2.setEnabled(statuscampos);
    }

    private void travaCampos3(boolean statuscampos) {
        txtPrincipalObjetivoDia3.setEnabled(statuscampos);
        txtAcolhidaAlunos3.setEnabled(statuscampos);
        cmbAreaConhecimento3.setEnabled(statuscampos);
        txtEstrRecuAtivi3.setEnabled(statuscampos);
        btnAdicionar3.setEnabled(statuscampos);
        btnRecuperar3.setEnabled(statuscampos);
        txtAnexo3.setEnabled(statuscampos);
        btnAnexar3.setEnabled(statuscampos);
        txtObservacoes3.setEnabled(statuscampos);
        tblPlanoAula3.setEnabled(statuscampos);
        btnSalvarPlanoAula3.setEnabled(statuscampos);
        chbLetivo3.setEnabled(statuscampos);
        txtAnexoLicao4.setEnabled(statuscampos);
    }

    private void travaCampos3b(boolean statuscampos) {
        txtPrincipalObjetivoDia3.setEnabled(statuscampos);
        txtAcolhidaAlunos3.setEnabled(statuscampos);
        cmbAreaConhecimento3.setEnabled(statuscampos);
        txtEstrRecuAtivi3.setEnabled(statuscampos);
        btnAdicionar3.setEnabled(statuscampos);
        btnRecuperar3.setEnabled(statuscampos);
        txtAnexo3.setEnabled(statuscampos);
        btnAnexar3.setEnabled(statuscampos);
        txtObservacoes3.setEnabled(statuscampos);
        tblPlanoAula3.setEnabled(statuscampos);
        chbLetivo3.setEnabled(statuscampos);
        txtAnexoLicao4.setEnabled(statuscampos);
    }

    private void travaCampos4(boolean statuscampos) {
        txtPrincipalObjetivoDia4.setEnabled(statuscampos);
        txtAcolhidaAlunos4.setEnabled(statuscampos);
        cmbAreaConhecimento4.setEnabled(statuscampos);
        txtEstrRecuAtivi4.setEnabled(statuscampos);
        btnAdicionar4.setEnabled(statuscampos);
        btnRecuperar4.setEnabled(statuscampos);
        txtAnexo4.setEnabled(statuscampos);
        btnAnexar4.setEnabled(statuscampos);
        txtObservacoes4.setEnabled(statuscampos);
        tblPlanoAula4.setEnabled(statuscampos);
        btnSalvarPlanoAula4.setEnabled(statuscampos);
        chbLetivo4.setEnabled(statuscampos);
        txtAnexoLicao3.setEnabled(statuscampos);
    }

    private void travaCampos4b(boolean statuscampos) {
        txtPrincipalObjetivoDia4.setEnabled(statuscampos);
        txtAcolhidaAlunos4.setEnabled(statuscampos);
        cmbAreaConhecimento4.setEnabled(statuscampos);
        txtEstrRecuAtivi4.setEnabled(statuscampos);
        btnAdicionar4.setEnabled(statuscampos);
        btnRecuperar4.setEnabled(statuscampos);
        txtAnexo4.setEnabled(statuscampos);
        btnAnexar4.setEnabled(statuscampos);
        txtObservacoes4.setEnabled(statuscampos);
        tblPlanoAula4.setEnabled(statuscampos);
        chbLetivo4.setEnabled(statuscampos);
        txtAnexoLicao3.setEnabled(statuscampos);
    }


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
            e.setEstrategia(txtEstrRecuAtivi1.getText());
            tabelaEstrategia.setValueAt(e, linhaSelecionada, 1);
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
            e.setEstrategia(txtEstrRecuAtivi2.getText());
            tabelaEstrategia.setValueAt(e, linhaSelecionada, 1);
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
            e.setEstrategia(txtEstrRecuAtivi3.getText());
            tabelaEstrategia.setValueAt(e, linhaSelecionada, 1);
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
            e.setEstrategia(txtEstrRecuAtivi4.getText());
            tabelaEstrategia.setValueAt(e, linhaSelecionada, 1);
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
        if (txtDataInicio.getDate() != null) {
            String data = txtDataInicio.getDate().toString();
            data = data.substring(0, 3);
            if (data.equals("Mon")) {
                if (adicionarPlanoAula() == true) {
                    ativarCampos();
                    txtDataInicio.setEnabled(false);
                    btnInserirPlanoAula.setEnabled(false);
                    String professor = user.getLogin();
                    Classe c = new Classe();
                    c = classeDAO.getClasseByProf(professor);
                    String classe = c.getIdclasse();
                    String turma = c.getTurma();
                    int ano = c.getAnoclasse();
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                    String dataInicio = formato.format(txtDataInicio.getDate());
                    SimpleDateFormat formato2 = new SimpleDateFormat("dd-MM-yyyy");
                    String dataFim = formato2.format(txtDataFinal.getDate());
                    caminho = ("W:\\" + professor + "\\" + classe + "_" + turma + " - " + ano + "\\" + dataInicio + " a " + dataFim + "\\");
                    File diretorio = new File(caminho);
                    diretorio.mkdir();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar uma Segunda-Feira");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Favor selecionar uma data de início!");
        }
    }//GEN-LAST:event_btnInserirPlanoAulaActionPerformed

    private void btnVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltaActionPerformed
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja voltar?", "Aviso!", 1);
        if (dialogResult == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btnVoltaActionPerformed

    private void btnEnviarPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarPlanoActionPerformed
        if (numBotaos == 0) {
            String mensagem = validacaoTodosDias();
            if ("Favor preencher o(s) seguinte(s) campo(s):\n".equals(mensagem)) {
                int dialogResult;
                dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que enviar o plano de aula para aprovação?", "Aviso!", 1);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    this.pa.setStatus("Em Aprovação");
                    /*          this.cruzamento.setPlanoaula(this.pa);
                DiasemanaHasEstrategiaPK pk = cruzamento.getDiasemanaHasEstrategiaPK();
                pk.setPlanoaulaIdplanoaula(this.pa.getIdplanoaula());
                     */ try {
                        //          cruzamentoDAO.edit(this.cruzamento);
                        planoaulaDAO.edit(this.pa);
                        JOptionPane.showMessageDialog(null, "Plano enviado para aprovação do diretor.");
                        this.dispose();
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, mensagem);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Favor Salvar todos os dias da semana!");
        }

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

            //btnInserirPlanoAula.setEnabled(true);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //DEFINE FORMATO DE DATA  
            Date datainic = txtDataInicio.getDate();
            Calendar c = Calendar.getInstance();
            c.setTime(datainic);
            c.add(Calendar.DATE, 4);
            String d = formato.format(c.getTime()); //CONVERTE PRA STRING
            try {
                txtDataFinal.setDate(formato.parse(d));
            } catch (ParseException ex) {
                Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtDataInicioPropertyChange

    private void btnSalvarPlanoAula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula1ActionPerformed
        if (editandoplano1 == false) {
            if (salvaPlano()) {
                btnEditar1.setEnabled(true);
                if (chbLetivo1.isSelected() == false) {
                    File diretorio = new File(caminho + "Terça-Feira\\");
                    diretorio.mkdir();
                    File destinoMatriz, destinoLicao, origemMatriz, origemLicao;
                    destinoMatriz = new File(caminho + "Terça-Feira\\");
                    destinoLicao = new File(caminho + "Terça-Feira\\");
                    origemMatriz = new File(txtAnexo1.getText());
                    origemLicao = new File(txtAnexoLicao1.getText());
                    try {
                        if (!txtAnexo1.getText().equals("")) {
                            copyFile(origemMatriz, destinoMatriz);
                            TercAnexo = destinoMatriz.toString();
                        }
                        if (!txtAnexoLicao1.getText().equals("")) {
                            copyFile(origemLicao, destinoLicao);
                            TerLic = destinoLicao.toString();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString() + "\nVerifique a conexão de rede de sua máquina e do servidor!");
                        Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            if (numBotaos == 0) {
                btnEnviarPlano.setEnabled(true);
            }
        } else {
            try {
                editarPlano();
                btnEditar1.setEnabled(true);
                numBotaos = numBotaos - 1;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarPlanoAula1ActionPerformed

    private void btnSalvarPlanoAula4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula4ActionPerformed
        if (editandoplano4 == false) {
            if (salvaPlano()) {
                btnEditar4.setEnabled(true);
                if (chbLetivo4.isSelected() == false) {
                    File diretorio = new File(caminho + "Sexta-Feira\\");
                    diretorio.mkdir();
                    File destinoMatriz, destinoLicao, origemMatriz, origemLicao;
                    destinoMatriz = new File(caminho + "Sexta-Feira\\");
                    destinoLicao = new File(caminho + "Sexta-Feira\\");
                    origemMatriz = new File(txtAnexo4.getText());
                    origemLicao = new File(txtAnexoLicao3.getText());
                    try {
                        if (!txtAnexo4.getText().equals("")) {
                            copyFile(origemMatriz, destinoMatriz);
                            SexAnexo = destinoMatriz.toString();
                        }
                        if (!txtAnexoLicao4.getText().equals("")) {
                            copyFile(origemLicao, destinoLicao);
                            SexLic = destinoLicao.toString();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString() + "\nVerifique a conexão de rede de sua máquina e do servidor!");
                        Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
            if (numBotaos == 0) {
                btnEnviarPlano.setEnabled(true);
            }
        } else {
            try {
                editarPlano();
                btnEditar4.setEnabled(true);
                numBotaos = numBotaos - 1;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarPlanoAula4ActionPerformed

    private void btnSalvarPlanoAula2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula2ActionPerformed
        if (editandoplano2 == false) {
            if (salvaPlano()) {
                btnEditar2.setEnabled(true);
                if (chbLetivo2.isSelected() == false) {
                    File diretorio = new File(caminho + "Quarta-Feira\\");
                    diretorio.mkdir();
                    File destinoMatriz, destinoLicao, origemMatriz, origemLicao;
                    destinoMatriz = new File(caminho + "Quarta-Feira\\");
                    destinoLicao = new File(caminho + "Quarta-Feira\\");
                    origemMatriz = new File(txtAnexo2.getText());
                    origemLicao = new File(txtAnexoLicao2.getText());
                    try {
                        if (!txtAnexo2.getText().equals("")) {
                            copyFile(origemMatriz, destinoMatriz);
                            QuarAnexo = destinoMatriz.toString();
                        }
                        if (!txtAnexoLicao2.getText().equals("")) {
                            copyFile(origemLicao, destinoLicao);
                            QuarLic = destinoLicao.toString();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString() + "\nVerifique a conexão de rede de sua máquina e do servidor!");
                        Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            if (numBotaos == 0) {
                btnEnviarPlano.setEnabled(true);
            }
        } else {
            try {
                editarPlano();
                btnEditar2.setEnabled(true);
                numBotaos = numBotaos - 1;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarPlanoAula2ActionPerformed

    private void btnSalvarPlanoAula3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPlanoAula3ActionPerformed
        if (editandoplano3 == false) {
            if (salvaPlano()) {
                btnEditar3.setEnabled(true);
                if (chbLetivo3.isSelected() == false) {
                    File diretorio = new File(caminho + "Quinta-Feira\\");
                    diretorio.mkdir();
                    File destinoMatriz, destinoLicao, origemMatriz, origemLicao;
                    destinoMatriz = new File(caminho + "Quinta-Feira\\");
                    destinoLicao = new File(caminho + "Quinta-Feira\\");
                    origemMatriz = new File(txtAnexo3.getText());
                    origemLicao = new File(txtAnexoLicao4.getText());
                    try {
                        if (!txtAnexo3.getText().equals("")) {
                            copyFile(origemMatriz, destinoMatriz);
                            QuinAnexo = destinoMatriz.toString();
                        }
                        if (!txtAnexoLicao3.getText().equals("")) {
                            copyFile(origemLicao, destinoLicao);
                            QuinLic = destinoLicao.toString();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString() + "\nVerifique a conexão de rede de sua máquina e do servidor!");
                        Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            if (numBotaos == 0) {
                btnEnviarPlano.setEnabled(true);
            }
        } else {
            try {
                editarPlano();
                btnEditar3.setEnabled(true);
                numBotaos = numBotaos - 1;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FrmPlanoAulaSemanal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarPlanoAula3ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        travaCampos0(true);
        editandoplano = true;
        this.ds = instanciaDiaSemana();
        numBotaos = numBotaos + 1;
        btnEditar.setEnabled(false);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        travaCampos1(true);
        editandoplano1 = true;
        this.ds = instanciaDiaSemana();
        numBotaos = numBotaos + 1;
        btnEditar1.setEnabled(false);
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnEditar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar2ActionPerformed
        travaCampos2(true);
        editandoplano2 = true;
        this.ds = instanciaDiaSemana();
        numBotaos = numBotaos + 1;
        btnEditar2.setEnabled(false);
    }//GEN-LAST:event_btnEditar2ActionPerformed

    private void btnEditar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar3ActionPerformed
        travaCampos3(true);
        editandoplano3 = true;
        this.ds = instanciaDiaSemana();
        numBotaos = numBotaos + 1;
        btnEditar3.setEnabled(false);
    }//GEN-LAST:event_btnEditar3ActionPerformed

    private void btnEditar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar4ActionPerformed
        travaCampos4(true);
        editandoplano4 = true;
        this.ds = instanciaDiaSemana();
        numBotaos = numBotaos + 1;
        btnEditar4.setEnabled(false);
    }//GEN-LAST:event_btnEditar4ActionPerformed

    private void chbLetivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbLetivoItemStateChanged
        // TODO add your handling code here:
        if (chbLetivo.isSelected()) {
            txtPrincipalObjetivoDia.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Segunda");
            txtAcolhidaAlunos.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Segunda");
            Estrategia e = new Estrategia();
            e.setEstrategia("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Segunda");
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento.getSelectedItem());
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula.getModel();
            Object[] obj = new Object[]{
                e.getAreaconhecimentoIdconhecimento(),
                e,};
            tabelaConhecimento.addRow(obj);
            travaCampos0b(false);
        } else {
            travaCampos0b(true);
            txtPrincipalObjetivoDia.setText("");
            txtAcolhidaAlunos.setText("");
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula.getModel();
            tabelaConhecimento.setRowCount(0);
        }
    }//GEN-LAST:event_chbLetivoItemStateChanged

    private void chbLetivo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbLetivo1ItemStateChanged
        // TODO add your handling code here:
        if (chbLetivo1.isSelected()) {
            txtPrincipalObjetivoDia1.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Terça");
            txtAcolhidaAlunos1.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Terça");
            Estrategia e = new Estrategia();
            e.setEstrategia("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Terça");
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento1.getSelectedItem());
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula1.getModel();
            Object[] obj = new Object[]{
                e.getAreaconhecimentoIdconhecimento(),
                e,};
            tabelaConhecimento.addRow(obj);
            travaCampos1b(false);
        } else {
            travaCampos1b(true);
            txtPrincipalObjetivoDia1.setText("");
            txtAcolhidaAlunos1.setText("");
            DefaultTableModel tabelaConhecimento1 = (DefaultTableModel) tblPlanoAula1.getModel();
            tabelaConhecimento1.setRowCount(0);
        }
    }//GEN-LAST:event_chbLetivo1ItemStateChanged

    private void chbLetivo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbLetivo2ItemStateChanged
        // TODO add your handling code here:
        if (chbLetivo2.isSelected()) {
            txtPrincipalObjetivoDia2.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Quarta");
            txtAcolhidaAlunos2.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Quarta");
            Estrategia e = new Estrategia();
            e.setEstrategia("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Quarta");
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento2.getSelectedItem());
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula2.getModel();
            Object[] obj = new Object[]{
                e.getAreaconhecimentoIdconhecimento(),
                e,};
            tabelaConhecimento.addRow(obj);
            travaCampos2b(false);
        } else {
            travaCampos2b(true);
            txtPrincipalObjetivoDia2.setText("");
            txtAcolhidaAlunos2.setText("");
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula2.getModel();
            tabelaConhecimento.setRowCount(0);
        }
    }//GEN-LAST:event_chbLetivo2ItemStateChanged

    private void chbLetivo4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbLetivo4ItemStateChanged
        // TODO add your handling code here:
        if (chbLetivo4.isSelected()) {
            txtPrincipalObjetivoDia4.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Sexta");
            txtAcolhidaAlunos4.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Sexta");
            Estrategia e = new Estrategia();
            e.setEstrategia("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Sexta");
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento4.getSelectedItem());
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula4.getModel();
            Object[] obj = new Object[]{
                e.getAreaconhecimentoIdconhecimento(),
                e,};
            tabelaConhecimento.addRow(obj);
            travaCampos4b(false);
        } else {
            travaCampos4b(true);
            txtPrincipalObjetivoDia4.setText("");
            txtAcolhidaAlunos4.setText("");
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula4.getModel();
            tabelaConhecimento.setRowCount(0);
        }
    }//GEN-LAST:event_chbLetivo4ItemStateChanged

    private void chbLetivo3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbLetivo3ItemStateChanged
        // TODO add your handling code here:
        if (chbLetivo3.isSelected()) {
            txtPrincipalObjetivoDia3.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Quinta");
            txtAcolhidaAlunos3.setText("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Quinta");
            Estrategia e = new Estrategia();
            e.setEstrategia("N/A - " + user.getLogin() + " - " + pa.getDatainicio() + " - Quinta");
            e.setAreaconhecimentoIdconhecimento((Areaconhecimento) cmbAreaConhecimento3.getSelectedItem());
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula3.getModel();
            Object[] obj = new Object[]{
                e.getAreaconhecimentoIdconhecimento(),
                e,};
            tabelaConhecimento.addRow(obj);
            travaCampos3b(false);
        } else {
            travaCampos3b(true);
            txtPrincipalObjetivoDia3.setText("");
            txtAcolhidaAlunos3.setText("");
            DefaultTableModel tabelaConhecimento = (DefaultTableModel) tblPlanoAula3.getModel();
            tabelaConhecimento.setRowCount(0);
        }
    }//GEN-LAST:event_chbLetivo3ItemStateChanged

    private void btnVisualizarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarPdfActionPerformed
        // TODO add your handling code here:
        try {
            HashMap filtro = new HashMap();
            JasperPrint print = JasperFillManager.fillReport("c:/Relatorio/PlanoAula.jasper", filtro, con.getConnection());
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar Relatório" + e);
        }
    }//GEN-LAST:event_btnVisualizarPdfActionPerformed

    private void btnAnexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarActionPerformed
        // TODO add your handling code here:
        //Metodo pra pegar o path do arquivo
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexo.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexo.setText(arquivo.getPath());
            lblAnexo.setText(arquivo.getName());
            txtAnexo.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexarActionPerformed

    private void btnAnexarLicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarLicaoActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexoLicao.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexoLicao.setText(arquivo.getPath());
            lblAnexoLicao.setText(arquivo.getName());
            txtAnexoLicao.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexarLicaoActionPerformed

    private void btnAnexarLicao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarLicao1ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexoLicao1.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexoLicao1.setText(arquivo.getPath());
            lblAnexoLicao1.setText(arquivo.getName());
            txtAnexoLicao1.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexarLicao1ActionPerformed

    private void btnAnexarLicao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarLicao2ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexoLicao2.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexoLicao2.setText(arquivo.getPath());
            lblAnexoLicao2.setText(arquivo.getName());
            txtAnexoLicao2.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexarLicao2ActionPerformed

    private void btnAnexarLicao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarLicao4ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexoLicao4.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexoLicao4.setText(arquivo.getPath());
            lblAnexoLicao4.setText(arquivo.getName());
            txtAnexoLicao4.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexarLicao4ActionPerformed

    private void btnAnexarLicao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexarLicao3ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexoLicao3.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexoLicao3.setText(arquivo.getPath());
            lblAnexoLicao3.setText(arquivo.getName());
            txtAnexoLicao3.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexarLicao3ActionPerformed

    private void btnAnexar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexar1ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexo1.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexo1.setText(arquivo.getPath());
            lblAnexo1.setText(arquivo.getName());
            txtAnexo1.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexar1ActionPerformed

    private void btnAnexar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexar2ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexo2.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexo2.setText(arquivo.getPath());
            lblAnexo2.setText(arquivo.getName());
            txtAnexo2.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexar2ActionPerformed

    private void btnAnexar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexar3ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexo3.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexo3.setText(arquivo.getPath());
            lblAnexo3.setText(arquivo.getName());
            txtAnexo3.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexar3ActionPerformed

    private void btnAnexar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexar4ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            txtAnexo4.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            txtAnexo4.setText(arquivo.getPath());
            lblAnexo4.setText(arquivo.getName());
            txtAnexo4.setEnabled(false);
        }
    }//GEN-LAST:event_btnAnexar4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja voltar?", "Aviso!", 1);
        if (dialogResult == JOptionPane.YES_OPTION) {
            this.dispose();
        }
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
    private javax.swing.JButton btnAnexarLicao;
    private javax.swing.JButton btnAnexarLicao1;
    private javax.swing.JButton btnAnexarLicao2;
    private javax.swing.JButton btnAnexarLicao3;
    private javax.swing.JButton btnAnexarLicao4;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnEditar2;
    private javax.swing.JButton btnEditar3;
    private javax.swing.JButton btnEditar4;
    private javax.swing.JButton btnEnviarPlano;
    private javax.swing.JButton btnInserirPlanoAula;
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
    private javax.swing.JCheckBox chbLetivo;
    private javax.swing.JCheckBox chbLetivo1;
    private javax.swing.JCheckBox chbLetivo2;
    private javax.swing.JCheckBox chbLetivo3;
    private javax.swing.JCheckBox chbLetivo4;
    private javax.swing.JComboBox cmbAreaConhecimento;
    private javax.swing.JComboBox cmbAreaConhecimento1;
    private javax.swing.JComboBox cmbAreaConhecimento2;
    private javax.swing.JComboBox cmbAreaConhecimento3;
    private javax.swing.JComboBox cmbAreaConhecimento4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JLabel lblAnexo;
    private javax.swing.JLabel lblAnexo1;
    private javax.swing.JLabel lblAnexo2;
    private javax.swing.JLabel lblAnexo3;
    private javax.swing.JLabel lblAnexo4;
    private javax.swing.JLabel lblAnexoLicao;
    private javax.swing.JLabel lblAnexoLicao1;
    private javax.swing.JLabel lblAnexoLicao2;
    private javax.swing.JLabel lblAnexoLicao3;
    private javax.swing.JLabel lblAnexoLicao4;
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
    private javax.swing.JTextField txtAnexoLicao;
    private javax.swing.JTextField txtAnexoLicao1;
    private javax.swing.JTextField txtAnexoLicao2;
    private javax.swing.JTextField txtAnexoLicao3;
    private javax.swing.JTextField txtAnexoLicao4;
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

    private Boolean recuperarSegunda() {
        Boolean vazio = false;
        int stratadd = 0;
        //Buscar Planoaula em Diasemana_has_estrategia
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula.getModel();
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
                        txtPrincipalObjetivoDia.setText(ds.getDiasemana().getPrincipalObj());
                        String teste = txtPrincipalObjetivoDia.getText();
                        if (teste.startsWith("N/A")) {
                            chbLetivo.setSelected(true);
                        }
                        txtAcolhidaAlunos.setText(ds.getDiasemana().getAcolhida());
                        Object[] obj = new Object[]{
                            ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                            ds.getEstrategia()
                        };
                        tabelaEstrategia.addRow(obj);
                        txtAnexo.setText(ds.getDiasemana().getAnexos());
                        txtAnexoLicao.setText(ds.getDiasemana().getLicaodecasa());
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
                travaCampos0(false);
            }
        }
        return vazio;
    }

    private void recuperarTerca() {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        List<DiasemanaHasEstrategia> listaterca = new ArrayList<DiasemanaHasEstrategia>();
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula1.getModel();
        int stratadd = 0;

        for (DiasemanaHasEstrategia de : listasemanal) {
            if (de.getDiasemana().getDia().equals("Terça-Feira")) {
                listaterca.add(de);
            }
        }
        if (!listaterca.isEmpty()) {
            for (DiasemanaHasEstrategia ds : listaterca) {
                if (stratadd == 0) {
                    txtPrincipalObjetivoDia1.setText(ds.getDiasemana().getPrincipalObj());
                    String teste = txtPrincipalObjetivoDia1.getText();
                    if (teste.startsWith("N/A")) {
                        chbLetivo1.setSelected(true);
                    }
                    txtAcolhidaAlunos1.setText(ds.getDiasemana().getAcolhida());
                    Object[] obj = new Object[]{
                        ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                        ds.getEstrategia()
                    };
                    tabelaEstrategia.addRow(obj);
                    txtAnexo1.setText(ds.getDiasemana().getAnexos());
                    txtAnexoLicao1.setText(ds.getDiasemana().getLicaodecasa());
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
            travaCampos1(false);
        }

    }

    private void recuperarQuarta() {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        List<DiasemanaHasEstrategia> listaquarta = new ArrayList<DiasemanaHasEstrategia>();
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula2.getModel();
        int stratadd = 0;

        for (DiasemanaHasEstrategia de : listasemanal) {
            if (de.getDiasemana().getDia().equals("Quarta-Feira")) {
                listaquarta.add(de);
            }
        }
        if (!listaquarta.isEmpty()) {
            for (DiasemanaHasEstrategia ds : listaquarta) {
                if (stratadd == 0) {
                    txtPrincipalObjetivoDia2.setText(ds.getDiasemana().getPrincipalObj());
                    String teste = txtPrincipalObjetivoDia2.getText();
                    if (teste.startsWith("N/A")) {
                        chbLetivo2.setSelected(true);
                    }
                    txtAcolhidaAlunos2.setText(ds.getDiasemana().getAcolhida());
                    Object[] obj = new Object[]{
                        ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                        ds.getEstrategia()
                    };
                    tabelaEstrategia.addRow(obj);
                    txtAnexo2.setText(ds.getDiasemana().getAnexos());
                    txtAnexoLicao2.setText(ds.getDiasemana().getLicaodecasa());
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
            travaCampos2(false);
        }
    }

    private void recuperarQuinta() {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        List<DiasemanaHasEstrategia> listaquinta = new ArrayList<DiasemanaHasEstrategia>();
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula3.getModel();
        int stratadd = 0;

        for (DiasemanaHasEstrategia de : listasemanal) {
            if (de.getDiasemana().getDia().equals("Quinta-Feira")) {
                listaquinta.add(de);
            }
        }
        if (!listaquinta.isEmpty()) {
            for (DiasemanaHasEstrategia ds : listaquinta) {
                if (stratadd == 0) {
                    txtPrincipalObjetivoDia3.setText(ds.getDiasemana().getPrincipalObj());
                    String teste = txtPrincipalObjetivoDia3.getText();
                    if (teste.startsWith("N/A")) {
                        chbLetivo3.setSelected(true);
                    }
                    txtAcolhidaAlunos3.setText(ds.getDiasemana().getAcolhida());
                    Object[] obj = new Object[]{
                        ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                        ds.getEstrategia()
                    };
                    tabelaEstrategia.addRow(obj);
                    txtAnexo3.setText(ds.getDiasemana().getAnexos());
                    txtAnexoLicao4.setText(ds.getDiasemana().getLicaodecasa());
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
            travaCampos3(false);
        }
    }

    private void recuperarSexta() {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        List<DiasemanaHasEstrategia> listasexta = new ArrayList<DiasemanaHasEstrategia>();
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula4.getModel();
        int stratadd = 0;

        for (DiasemanaHasEstrategia de : listasemanal) {
            if (de.getDiasemana().getDia().equals("Sexta-Feira")) {
                listasexta.add(de);
            }
        }
        if (!listasexta.isEmpty()) {
            for (DiasemanaHasEstrategia ds : listasexta) {
                if (stratadd == 0) {
                    txtPrincipalObjetivoDia4.setText(ds.getDiasemana().getPrincipalObj());
                    String teste = txtPrincipalObjetivoDia4.getText();
                    if (teste.startsWith("N/A")) {
                        chbLetivo4.setSelected(true);
                    }
                    txtAcolhidaAlunos4.setText(ds.getDiasemana().getAcolhida());
                    Object[] obj = new Object[]{
                        ds.getEstrategia().getAreaconhecimentoIdconhecimento(),
                        ds.getEstrategia()
                    };
                    tabelaEstrategia.addRow(obj);
                    txtAnexo4.setText(ds.getDiasemana().getAnexos());
                    txtAnexoLicao3.setText(ds.getDiasemana().getLicaodecasa());
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
            travaCampos4(false);
        }
    }

    private void excluirEstrategiaSegunda() throws NonexistentEntityException {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula.getModel();
        if (listasemanal.isEmpty()) {
            return;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Segunda-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    this.ds = ds.getDiasemana();
                    this.pa = ds.getPlanoaula();
                    this.es = ds.getEstrategia();
                    cruzamentoDAO.destroy(ds.getDiasemanaHasEstrategiaPK());
                    estrategiaDAO.destroy(this.es.getIdestrategia());
                }
            }
        }
    }

    private void excluirEstrategiaTerca() throws NonexistentEntityException {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula1.getModel();
        if (listasemanal.isEmpty()) {
            return;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Terça-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    this.ds = ds.getDiasemana();
                    this.pa = ds.getPlanoaula();
                    this.es = ds.getEstrategia();
                    cruzamentoDAO.destroy(ds.getDiasemanaHasEstrategiaPK());
                    estrategiaDAO.destroy(this.es.getIdestrategia());
                }
            }
        }
    }

    private void excluirEstrategiaQuarta() throws NonexistentEntityException {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula2.getModel();
        if (listasemanal.isEmpty()) {
            return;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Quarta-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    this.ds = ds.getDiasemana();
                    this.pa = ds.getPlanoaula();
                    this.es = ds.getEstrategia();
                    cruzamentoDAO.destroy(ds.getDiasemanaHasEstrategiaPK());
                    estrategiaDAO.destroy(this.es.getIdestrategia());
                }
            }
        }
    }

    private void excluirEstrategiaQuinta() throws NonexistentEntityException {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula3.getModel();
        if (listasemanal.isEmpty()) {
            return;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Quinta-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    this.ds = ds.getDiasemana();
                    this.pa = ds.getPlanoaula();
                    this.es = ds.getEstrategia();
                    cruzamentoDAO.destroy(ds.getDiasemanaHasEstrategiaPK());
                    estrategiaDAO.destroy(this.es.getIdestrategia());
                }
            }
        }
    }

    private void excluirEstrategiaSexta() throws NonexistentEntityException {
        List<DiasemanaHasEstrategia> listasemanal = cruzamentoDAO.getPlanoAula(this.pa);
        DefaultTableModel tabelaEstrategia = (DefaultTableModel) tblPlanoAula4.getModel();
        if (listasemanal.isEmpty()) {
            return;
        } else {
            List<DiasemanaHasEstrategia> listasegunda = new ArrayList<DiasemanaHasEstrategia>();

            for (DiasemanaHasEstrategia de : listasemanal) {
                if (de.getDiasemana().getDia().equals("Sexta-Feira")) {
                    listasegunda.add(de);
                }
            }

            if (!listasegunda.isEmpty()) {
                for (DiasemanaHasEstrategia ds : listasegunda) {
                    this.ds = ds.getDiasemana();
                    this.pa = ds.getPlanoaula();
                    this.es = ds.getEstrategia();
                    cruzamentoDAO.destroy(ds.getDiasemanaHasEstrategiaPK());
                    estrategiaDAO.destroy(this.es.getIdestrategia());
                }
            }
        }
    }
}
