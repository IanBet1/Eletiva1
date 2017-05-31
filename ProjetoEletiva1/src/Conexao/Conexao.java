/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author IFSP
 */
public class Conexao {
    
    public static Connection getConnection()
    {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/eletiva1", "root", "");
           // return DriverManager.getConnection("jdbc:mysql://192.168.0.109:3306/atj2", "root", "mariano");
            //return DriverManager.getConnection("jdbc:mysql://169.254.34.221:3306/atj2", "root", "mariano");
        }
        catch(Exception e)
        {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
    
}
