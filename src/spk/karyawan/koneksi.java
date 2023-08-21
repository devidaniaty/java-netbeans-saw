/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.karyawan;
import Koneksi1.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class koneksi {
    
    public static Connection koneksi;
    public Statement stat;
    private static  com.mysql.jdbc.Connection conn;
    
    public static Connection getconnetion(){
        if(koneksi==null){
            try{
                String server="jdbc:mysql://localhost:3306/db_spk_kinerja";
                String user="root";
                String password="";
                Class.forName("com.mysql.jdbc.Driver");
                koneksi=DriverManager.getConnection(server,user,password);
            }catch(Exception x){
                JOptionPane.showMessageDialog(null,"Koneksi Gagal, Pesan error \n"+x);
            }
        }
        return koneksi;
    }
    
    public void koneksi(){
        try{
            getconnetion();
            stat=koneksi.createStatement();
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,"Koneksi ambil Gagal, Pesan error \n"+x);
        }
    }
    
    public void tutupKoneksi(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
            stat.close();
            koneksi.close();
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,"Tutup Koneksi Gagal, Pesan error \n"+x);
        }
    } 
    
    public ResultSet ambilData(String sql){
         ResultSet rs=null;
         try{
            koneksi();
            rs=stat.executeQuery(sql);
         }catch(Exception x){
             JOptionPane.showMessageDialog(null,"Ambil Data Gagal, Pesan error : \n"+x);
         }
         return rs;
    }
    
    public void simpanData(String sql){
        try{
            koneksi();
            stat.executeUpdate(sql);
        }catch(Exception x){
            System.out.print(x);
        }
    }

    Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}