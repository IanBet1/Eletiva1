/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package easyreport;

import java.sql.ResultSet;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rafael
 */
public class EasyReport {
    
    private JasperPrint jp;
    private String erro;
    
    public EasyReport(String reportFile, ResultSet rs){
        this(reportFile, rs, new HashMap());
    }
    
    public EasyReport(String reportFile, ResultSet rs, HashMap hm){
        JRResultSetDataSource jrResultSet = new JRResultSetDataSource(rs);
        try{
            this.jp = JasperFillManager.fillReport(reportFile, hm, jrResultSet);
        }
        catch(JRException e){
            this.erro = e.getMessage();
        }
    }

    public String getErro() {
        return erro;
    }
    
    public void displayReport(){
        JasperViewer jv = new JasperViewer(this.jp, false);
        jv.setVisible(true);
    }
    
    public void exportToPdf(String local){
        try{            
            JasperExportManager.exportReportToPdfFile(this.jp, local);
        }
        catch(JRException e){
            this.erro = e.getMessage();
        }
    }    
}
