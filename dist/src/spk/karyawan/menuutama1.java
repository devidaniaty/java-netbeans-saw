/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.karyawan;



import LoginAdmin.login1;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author Asus
 */
public class menuutama1 extends javax.swing.JFrame {
    private DefaultTableModel model;
    DefaultTableModel tabelKaryawan;
    DefaultTableModel tabelbobot;
    DefaultTableModel tabelDataKriteria;
    DefaultTableModel tabelNormalisasi;
    DefaultTableModel tabelPeringkat;

    Color hoverButton;
  
     koneksi conn= new koneksi();
    private DefaultTableModel tabmode;
    /**
     * Creates new form menuutama1
     */
     public menuutama1() {
     initComponents();
     setVisible(true);
     setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
     setLocationRelativeTo(this);
     tabelData();
     tabelModelKriteria();
     
  
        this.hoverButton=home.getBackground();
        Pane.removeAll();
        Pane.repaint();
        Pane.revalidate();
                //add panel, add panel
        Pane.add(open);
        Pane.repaint();
        Pane.revalidate();
    }
     public void tabelData(){
        tabelKaryawan = new DefaultTableModel();
        tabelKaryawan.addColumn("ID");
        tabelKaryawan.addColumn("Nama");
        tabelKaryawan.addColumn("JK");
        tabelKaryawan.addColumn("Pendidikan ");
        tabelKaryawan.addColumn("Nomer HP");
        tabelKaryawan.addColumn("Alamat");
        tblkaryawan2.setModel(tabelKaryawan);
        
        kode1();
        
        try{
            ResultSet rs =conn.ambilData("SELECT * FROM tbl_karyawan");
            while (rs.next()){
                tabelKaryawan.addRow(new Object[]{rs.getString(1),rs.getString(2)
                ,rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
            }
            tblkaryawan2.setModel(tabelKaryawan);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this, ex);
        }  
         }
     private void tampilkan1() {
        int row = tblkaryawan2.getRowCount();
        for(int a = 0; a<row;a++){
            tabelKaryawan.removeRow(0);
        }
        try {
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
        ResultSet nama = conn.createStatement().executeQuery("SELECT * FROM tbl_karyawan");
        while(nama.next()) {
                String data [] = {nama.getString(1),nama.getString(2),
                nama.getString(3),nama.getString(4),nama.getString(5),nama.getString(6)};
            tabelKaryawan.addRow(data);
        }
        } catch (SQLException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
     private void tampilkan2() {
        int row = tblbobot.getRowCount();
        for(int a = 0; a<row;a++){
            tabelbobot.removeRow(0);
        }
        try {
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
        ResultSet nama = conn.createStatement().executeQuery("SELECT * FROM tbl_bobot");
        while(nama.next()) {
                String data [] = {nama.getString(1),nama.getString(2),
                nama.getString(3),nama.getString(4)};
            tabelbobot.addRow(data);
        }
        } catch (SQLException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
     private void reset1() {
            txt_id.setText("");
            txt_nama.setText("");
            btnG.clearSelection();
            cmb_pendidikan.setSelectedItem("");
            txt_nohp.setText("");
            txt_alamat.setText("");
          
     }
     private void reset2() {
            idkaryawan.setText("");
            txtnama.setText("");
            c1.setText("");
            c2.setText("");
            c3.setText("");
            c4.setText("");
     }
     private void insertdatakaryawan(){
         String sql ="INSERT INTO tbl_karyawan VALUES(?,?,?,?,?,?)";
         try{
             java.sql.Connection conn=(java.sql.Connection)koneksi.getconnetion();
             java.sql.PreparedStatement stat =conn.prepareStatement(sql);
             stat.setString(1,txt_id.getText());
             stat.setString(2,txt_nama.getText());
             String jk="";
             if(rbLaki.isSelected()){
         jk=rbLaki.getText();
         }else {jk=rbPerempuan.getText();}
         stat.setString(3, jk);
         stat.setString(4,cmb_pendidikan.getSelectedItem().toString());
         stat.setString(5,txt_nohp.getText());
         stat.setString(6,txt_alamat.getText());
         stat.executeUpdate();
         JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
         reset1();
         txt_id.requestFocus();
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null,"DATA Gagal Disimpan"+e);          
         }
     }
     public void tabelbobot(){
        tabelbobot = new DefaultTableModel();
        tabelbobot.addColumn("W1");
        tabelbobot.addColumn("W2");
        tabelbobot.addColumn("W3");
        tabelbobot.addColumn("W4");
        tblbobot.setModel(tabelbobot);
        try{
          ResultSet rs =conn.ambilData("SELECT * FROM tbl_bobot");
            while (rs.next()){
                tabelbobot.addRow(new Object[]{rs.getString(1),rs.getString(2)
                ,rs.getString(3),rs.getString(4)});  
        }
            tblbobot.setModel(tabelbobot);
     }catch(SQLException ex) {
           JOptionPane.showMessageDialog(this, ex);
        }  
         }
     private void kode1() {
        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
            Statement st = conn.createStatement();

            String sql = "SELECT * FROM tbl_karyawan ORDER by id desc";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                String kd1 = rs.getString("id").substring(1);
                String KW = "" + (Integer.parseInt(kd1) + 1);
                String Nol = "";

                if (KW.length() == 1) {
                    Nol = "0";
                } 

                txt_id.setText("A" + Nol + KW);
            } else {
                txt_id.setText("A01");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     
    }
  
     public final void loadDatakriteria() {
        java.sql.Connection kon = koneksi.getconnetion();
        Object header[]={"Nomer ID","Nama Karyawan","C1","C2","C3","C4"};
        DefaultTableModel data= new DefaultTableModel(null,header);
        tblkriteria1.setModel(data);
        String sql="SELECT * FROM tbl_kriteria";
        try
        {
            java.sql.Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {

                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                String k6=rs.getString(6);   
                          
                String k[]={k1,k2,k3,k4,k5,k6};
                data.addRow(k);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void loadDatakaryawan() {
       txt_id.setEnabled(true);
        tabelKaryawan.getDataVector().removeAllElements();
        tabelKaryawan.fireTableDataChanged();
        
        try {
            String sql = "SELECT * FROM tbl_karyawan";
            java.sql.Connection conn=(java.sql.Connection)koneksi.getconnetion();
            java.sql.PreparedStatement s =conn.prepareStatement(sql);
            
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("id");
                o[1] = r.getString("nama");
                o[2] = r.getString("jk");
                o[3] = r.getString("pendidikan");
                o[4] = r.getString("email");
                o[5] = r.getString("nohp");
                o[6] = r.getString("alamat");             
                tabelKaryawan.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }
     public void editDataKaryawan(){
        try{
        String sql ="UPDATE tbl_karyawan set nama=?, jk=?, pendidikan=?, nohp=?, alamat=? WHERE id=?";
        java.sql.Connection conn=(java.sql.Connection)koneksi.getconnetion();
        PreparedStatement stat =conn.prepareStatement(sql);
         stat.setString(1,txt_nama.getText());
             String jk="";
             if(rbLaki.isSelected()){
         jk=rbLaki.getText();
         }else {jk=rbPerempuan.getText();}
         stat.setString(2, jk);
         stat.setString(3,cmb_pendidikan.getSelectedItem().toString());
         stat.setString(4,txt_nohp.getText());
         stat.setString(5,txt_alamat.getText());
         stat.setString(6,txt_id.getText());
         stat.executeUpdate();
         JOptionPane.showMessageDialog(null,"Data Berhasil Diubah");
         reset1();
         txt_id.requestFocus();
   }catch (SQLException e){
       JOptionPane.showMessageDialog(null,"Data Gagal Diubah" +e);
       
   }
        
     }
     public void editDataKriteria(){
   try {
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
            conn.createStatement().executeUpdate("UPDATE tbl_kriteria SET nama = '"+txtnama.getText()+"',c1 = '"+c1.getText()+"',"
                + "c2 = '"+c2.getText()+"',c3 = '"+c3.getText()+"',c4 = '"+c4.getText()+"' WHERE id ='"+idkaryawan.getText()+"'");
            JOptionPane.showMessageDialog(this, "Data Berhasil Di ubah");
            loadDatakriteria();
            reset2();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }
     public void tabelModelKriteria(){
       tabelDataKriteria = new DefaultTableModel();
       tabelDataKriteria.addColumn("Nomer ID");
       tabelDataKriteria.addColumn("Nama");
       tabelDataKriteria.addColumn("Kedisplinan");
       tabelDataKriteria.addColumn("Kerjasama Tim");
       tabelDataKriteria.addColumn("Kepribadian");
       tabelDataKriteria.addColumn("Skill");
       tblkriteria1.setModel(tabelDataKriteria);
        
        try{
            ResultSet rs =conn.ambilData("SELECT * FROM tbl_kriteria");
            while (rs.next()){
                tabelDataKriteria.addRow(new Object[]{rs.getString(1),rs.getString(2)
                ,rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
            }
            tblkriteria1.setModel(tabelDataKriteria);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this, ex);
        }  
     }
     
     public void tabelModelNormalisasi(){
       tabelNormalisasi = new DefaultTableModel();
       tabelNormalisasi.addColumn("Nomer ID");
       tabelNormalisasi.addColumn("Nama");
       tabelNormalisasi.addColumn("C1");
       tabelNormalisasi.addColumn("C2");
       tabelNormalisasi.addColumn("C3");
       tabelNormalisasi.addColumn("C4");
       tnormalisasi.setModel(tabelNormalisasi);
    }
     public void tabelmodelPeringkat(){
        try {
            tabelPeringkat = new DefaultTableModel();
            tabelPeringkat.addColumn("Nomer ID");
            tabelPeringkat.addColumn("Nama");
            tabelPeringkat.addColumn("Nilai");
            
            tperingkat.setModel(tabelPeringkat);
            ResultSet res = conn.ambilData("SELECT * FROM tbl_peringkat ORDER BY nilai desc");
             while (res.next()){
                         tabelPeringkat.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3)}); 
                     }
        } catch (SQLException ex) {
        }
    }
     public  void normalisasi(){
        LinkedList max = new LinkedList();
        LinkedList mm = new LinkedList();
        
        try{
            ResultSet res = conn.ambilData("select max(c1),"+ 
                "max(c2), "+
                "max(c3), "+
                "max(c4)"+
                "from "+
                "tbl_kriteria ");
            
            while (res.next()){
                max.add(res.getString(1));
                max.add(res.getString(2));
                max.add(res.getString(3));
                max.add(res.getString(4));
            }
            ResultSet res2 = conn.ambilData("select * from tbl_kriteria ");
            tabelModelNormalisasi();
            
            while (res2.next()){
                tabelNormalisasi.addRow(new Object[]{res2.getString(1),res2.getString(2),
                    (res2.getFloat(3)/Float.valueOf(max.get(0).toString())),
                    (res2.getFloat(4)/Float.valueOf(max.get(1).toString())),
                    (res2.getFloat(5)/Float.valueOf(max.get(2).toString())),
                    (res2.getFloat(6)/Float.valueOf(max.get(3).toString())),
                    (Float.valueOf(max.get(3).toString())/res2.getFloat(6))});
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }
     public boolean isCellEditable(int row, int column){
        return false;//This causes all cells to be not editable
    }
     
     public void cariperingkat(){
        try{
            LinkedList mm = new LinkedList();
            ResultSet res3 = conn.ambilData("SELECT * FROM tbl_bobot");
            tabelmodelPeringkat();
            while (res3.next()){
                 mm.add(res3.getString(1));
                 mm.add(res3.getString(2));
                 mm.add(res3.getString(3));
                 mm.add(res3.getString(4));
            }
            for (int t = 0; t < tnormalisasi.getRowCount(); t++) {
                String sql = "DELETE FROM tbl_peringkat WHERE nama= nama";
                conn.simpanData(sql);
            }
            for (int x = 0; x < tnormalisasi.getRowCount(); x++){
                double r1;
                double r2;
                double r3;
                double r4;
                double w;
                
                r1=(Float.valueOf(tnormalisasi.getValueAt(x, 2).toString())*Float.valueOf(mm.get(0).toString()));
                r2=(Float.valueOf(tnormalisasi.getValueAt(x, 3).toString())*Float.valueOf(mm.get(1).toString()));
                r3=(Float.valueOf(tnormalisasi.getValueAt(x, 4).toString())*Float.valueOf(mm.get(2).toString()));
                r4=(Float.valueOf(tnormalisasi.getValueAt(x, 5).toString())*Float.valueOf(mm.get(3).toString()));
                w=r1+r2+r3+r4;
                
                String sql = "insert into tbl_peringkat values"+"('"+tnormalisasi.getValueAt(x,0).toString()+"','"+tnormalisasi.getValueAt(x,1).toString()+"',"+"'"+w+"')";
                conn.simpanData(sql);
            }
            tabelModelNormalisasi();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
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

        btnG = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        menuutama = new javax.swing.JPanel();
        btn_LogOut = new javax.swing.JButton();
        btn_DataKaryawan = new javax.swing.JButton();
        btn_DataKriteria = new javax.swing.JButton();
        btn_Peringkat = new javax.swing.JButton();
        Laporan_DataKaryawan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Laporan_DataBobot = new javax.swing.JButton();
        Laporan_DataKriteria = new javax.swing.JButton();
        Laporan_DataHasil = new javax.swing.JButton();
        btn_Bobot = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Pane = new javax.swing.JPanel();
        DataKriteria = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cm1 = new javax.swing.JComboBox<>();
        cm2 = new javax.swing.JComboBox<>();
        cm3 = new javax.swing.JComboBox<>();
        cm4 = new javax.swing.JComboBox<>();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        c4 = new javax.swing.JTextField();
        c1 = new javax.swing.JTextField();
        c2 = new javax.swing.JTextField();
        c3 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        idkaryawan = new java.awt.TextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkriteria1 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        Peringkat = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tnormalisasi = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tperingkat = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        CetakLaporan = new javax.swing.JPanel();
        DialogTambahData = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblkaryawan2 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        PanelKandidat = new javax.swing.JTabbedPane();
        repaint = new javax.swing.JPanel();
        repaint1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_nohp = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        cmb_pendidikan = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        txt_nama = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tampilkaryawan1 = new javax.swing.JButton();
        btn_simpan1 = new javax.swing.JButton();
        btn_edit1 = new javax.swing.JButton();
        btn_hapus1 = new javax.swing.JButton();
        home = new javax.swing.JPanel();
        open = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        DataBobot = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblbobot = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        menuutama.setBackground(new java.awt.Color(0, 102, 204));

        btn_LogOut.setBackground(new java.awt.Color(153, 153, 153));
        btn_LogOut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_LogOut.setText("Log Out");
        btn_LogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LogOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_LogOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_LogOutMouseExited(evt);
            }
        });
        btn_LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogOutActionPerformed(evt);
            }
        });

        btn_DataKaryawan.setBackground(new java.awt.Color(0, 102, 204));
        btn_DataKaryawan.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_DataKaryawan.setText("Data Karyawan");
        btn_DataKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DataKaryawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DataKaryawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DataKaryawanMouseExited(evt);
            }
        });
        btn_DataKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DataKaryawanActionPerformed(evt);
            }
        });

        btn_DataKriteria.setBackground(new java.awt.Color(0, 102, 204));
        btn_DataKriteria.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_DataKriteria.setText("Data Kriteria");
        btn_DataKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DataKriteriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DataKriteriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DataKriteriaMouseExited(evt);
            }
        });
        btn_DataKriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DataKriteriaActionPerformed(evt);
            }
        });

        btn_Peringkat.setBackground(new java.awt.Color(0, 102, 204));
        btn_Peringkat.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_Peringkat.setText("Proses Data");
        btn_Peringkat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_PeringkatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_PeringkatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_PeringkatMouseExited(evt);
            }
        });
        btn_Peringkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PeringkatActionPerformed(evt);
            }
        });

        Laporan_DataKaryawan.setBackground(new java.awt.Color(0, 102, 204));
        Laporan_DataKaryawan.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        Laporan_DataKaryawan.setText("Laporan Data Karyawan");
        Laporan_DataKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laporan_DataKaryawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Laporan_DataKaryawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Laporan_DataKaryawanMouseExited(evt);
            }
        });
        Laporan_DataKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Laporan_DataKaryawanActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("         Cetak Laporan");

        Laporan_DataBobot.setBackground(new java.awt.Color(0, 102, 204));
        Laporan_DataBobot.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        Laporan_DataBobot.setText("Laporan Data Bobot");
        Laporan_DataBobot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laporan_DataBobotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Laporan_DataBobotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Laporan_DataBobotMouseExited(evt);
            }
        });
        Laporan_DataBobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Laporan_DataBobotActionPerformed(evt);
            }
        });

        Laporan_DataKriteria.setBackground(new java.awt.Color(0, 102, 204));
        Laporan_DataKriteria.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        Laporan_DataKriteria.setText("Laporan Data Kriteria");
        Laporan_DataKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laporan_DataKriteriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Laporan_DataKriteriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Laporan_DataKriteriaMouseExited(evt);
            }
        });
        Laporan_DataKriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Laporan_DataKriteriaActionPerformed(evt);
            }
        });

        Laporan_DataHasil.setBackground(new java.awt.Color(0, 102, 204));
        Laporan_DataHasil.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        Laporan_DataHasil.setText("Laporan Hasil Seleksi");
        Laporan_DataHasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laporan_DataHasilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Laporan_DataHasilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Laporan_DataHasilMouseExited(evt);
            }
        });
        Laporan_DataHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Laporan_DataHasilActionPerformed(evt);
            }
        });

        btn_Bobot.setBackground(new java.awt.Color(0, 102, 204));
        btn_Bobot.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_Bobot.setText("Data Bobot");
        btn_Bobot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_BobotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_BobotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_BobotMouseExited(evt);
            }
        });
        btn_Bobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BobotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuutamaLayout = new javax.swing.GroupLayout(menuutama);
        menuutama.setLayout(menuutamaLayout);
        menuutamaLayout.setHorizontalGroup(
            menuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuutamaLayout.createSequentialGroup()
                .addGroup(menuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_LogOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuutamaLayout.createSequentialGroup()
                        .addGroup(menuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Laporan_DataKriteria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Laporan_DataBobot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Laporan_DataKaryawan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Laporan_DataHasil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Peringkat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_DataKaryawan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_DataKriteria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Bobot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        menuutamaLayout.setVerticalGroup(
            menuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuutamaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btn_DataKaryawan)
                .addGap(18, 18, 18)
                .addComponent(btn_Bobot)
                .addGap(18, 18, 18)
                .addComponent(btn_DataKriteria)
                .addGap(18, 18, 18)
                .addComponent(btn_Peringkat)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(Laporan_DataKaryawan)
                .addGap(18, 18, 18)
                .addComponent(Laporan_DataBobot)
                .addGap(18, 18, 18)
                .addComponent(Laporan_DataKriteria)
                .addGap(18, 18, 18)
                .addComponent(Laporan_DataHasil)
                .addGap(33, 33, 33)
                .addComponent(btn_LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        header.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Asus\\OneDrive\\Documents\\NetBeansProjects\\SPK-KARYAWAN\\KDM LOGO.png")); // NOI18N

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 23)); // NOI18N
        jLabel2.setText("Sistem Pendukung Keputusan Untuk Evaluasi Kinerja Karyawan Tetap Metode SAW");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Pane.setBackground(new java.awt.Color(255, 255, 255));

        DataKriteria.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pengaturan Bobot Kepentingan Kriteria");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Kerjasama Tim");

        jLabel12.setText("Kedisplinan");

        jLabel13.setText("Kepribadian");

        jLabel14.setText("Skill");

        cm1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cm1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sangat Tinggi", "Tinggi", "Cukup", "Rendah" }));
        cm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cm1ActionPerformed(evt);
            }
        });

        cm2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cm2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sangat Tinggi", "Tinggi", "Cukup", "Rendah" }));
        cm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cm2ActionPerformed(evt);
            }
        });

        cm3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cm3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sangat Tinggi", "Tinggi", "Cukup", "Rendah" }));
        cm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cm3ActionPerformed(evt);
            }
        });

        cm4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cm4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sangat Tinggi", "Tinggi", "Cukup", "Rendah" }));
        cm4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cm4ActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(0, 102, 255));
        btn_simpan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanMouseClicked(evt);
            }
        });
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(204, 0, 0));
        btn_hapus.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(0, 102, 255));
        btn_edit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        c4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4ActionPerformed(evt);
            }
        });

        c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1ActionPerformed(evt);
            }
        });

        c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2ActionPerformed(evt);
            }
        });

        c3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3ActionPerformed(evt);
            }
        });

        jLabel31.setText("C1");

        jLabel32.setText("C2");

        jLabel35.setText("C3");

        jLabel37.setText("C4");

        jLabel39.setText("Masukkan ID");

        jLabel40.setText("Nama");

        txtnama.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        idkaryawan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        idkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idkaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cm3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel35))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cm1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cm4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37)))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cm2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32))
                            .addComponent(txtnama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(idkaryawan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31)))
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cm3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cm4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(59, 59, 59)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan)
                    .addComponent(btn_edit))
                .addGap(42, 42, 42)
                .addComponent(btn_hapus)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tblkriteria1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblkriteria1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkriteria1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblkriteria1);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel15.setText("Catatan : Edit, klik data pada tabel terlebih dahulu");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout DataKriteriaLayout = new javax.swing.GroupLayout(DataKriteria);
        DataKriteria.setLayout(DataKriteriaLayout);
        DataKriteriaLayout.setHorizontalGroup(
            DataKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DataKriteriaLayout.setVerticalGroup(
            DataKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataKriteriaLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Peringkat.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(102, 102, 102));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Data Peringkat Karyawan");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel43.setText("Tabel Normalisasi");

        tnormalisasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tnormalisasi);

        jLabel44.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel44.setText("Tabel Peringkat");

        tperingkat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(tperingkat);

        jButton3.setText("HITUNG");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("TENTUKAN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(30, 30, 30)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(784, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout PeringkatLayout = new javax.swing.GroupLayout(Peringkat);
        Peringkat.setLayout(PeringkatLayout);
        PeringkatLayout.setHorizontalGroup(
            PeringkatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PeringkatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PeringkatLayout.setVerticalGroup(
            PeringkatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PeringkatLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CetakLaporanLayout = new javax.swing.GroupLayout(CetakLaporan);
        CetakLaporan.setLayout(CetakLaporanLayout);
        CetakLaporanLayout.setHorizontalGroup(
            CetakLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1059, Short.MAX_VALUE)
        );
        CetakLaporanLayout.setVerticalGroup(
            CetakLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        tblkaryawan2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblkaryawan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkaryawan2MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblkaryawan2);

        jTabbedPane2.addTab("Tabel Calon Karyawan Tetap", jScrollPane11);

        jPanel17.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" Data Karyawan");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        repaint1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("No. ID");

        jLabel6.setText("Nama");

        btn.setText("Jenis Kelamin");

        jLabel20.setText("Pendidikan Terakhir");

        txt_nohp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpActionPerformed(evt);
            }
        });

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        rbLaki.setBackground(new java.awt.Color(255, 255, 255));
        rbLaki.setText("Laki - Laki");
        rbLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiActionPerformed(evt);
            }
        });

        rbPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPerempuanActionPerformed(evt);
            }
        });

        cmb_pendidikan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak Sekolah", "SD", "SMP", "SMA", "S1" }));

        jLabel21.setText("Alamat");

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane3.setViewportView(txt_alamat);

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        jLabel22.setText("Nomer HP");

        javax.swing.GroupLayout repaint1Layout = new javax.swing.GroupLayout(repaint1);
        repaint1.setLayout(repaint1Layout);
        repaint1Layout.setHorizontalGroup(
            repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repaint1Layout.createSequentialGroup()
                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, repaint1Layout.createSequentialGroup()
                        .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbPerempuan))
                    .addGroup(repaint1Layout.createSequentialGroup()
                        .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(repaint1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(repaint1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_id))
                                    .addGroup(repaint1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(repaint1Layout.createSequentialGroup()
                                                .addComponent(rbLaki)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txt_nama)))))
                            .addGroup(repaint1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(repaint1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_pendidikan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(2, 2, 2)))
                .addGap(7, 7, 7))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, repaint1Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(txt_nohp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, repaint1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        repaint1Layout.setVerticalGroup(
            repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repaint1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan))
                .addGap(19, 19, 19)
                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cmb_pendidikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(repaint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tampilkaryawan1.setText("Tampilkan Data ");
        tampilkaryawan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tampilkaryawan1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tampilkaryawan1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tampilkaryawan1MouseExited(evt);
            }
        });
        tampilkaryawan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampilkaryawan1ActionPerformed(evt);
            }
        });

        btn_simpan1.setText("Simpan");
        btn_simpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_simpan1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_simpan1MouseExited(evt);
            }
        });
        btn_simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan1ActionPerformed(evt);
            }
        });

        btn_edit1.setText("Edit");
        btn_edit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_edit1MouseExited(evt);
            }
        });
        btn_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit1ActionPerformed(evt);
            }
        });

        btn_hapus1.setText("Hapus");
        btn_hapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_hapus1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_hapus1MouseExited(evt);
            }
        });
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout repaintLayout = new javax.swing.GroupLayout(repaint);
        repaint.setLayout(repaintLayout);
        repaintLayout.setHorizontalGroup(
            repaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repaintLayout.createSequentialGroup()
                .addComponent(repaint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(repaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tampilkaryawan1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_edit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_hapus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        repaintLayout.setVerticalGroup(
            repaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repaintLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(repaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(repaint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(repaintLayout.createSequentialGroup()
                        .addComponent(tampilkaryawan1)
                        .addGap(18, 18, 18)
                        .addComponent(btn_simpan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_edit1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_hapus1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        PanelKandidat.addTab("Biodata Calon Karyawan Tetap", repaint);

        javax.swing.GroupLayout DialogTambahDataLayout = new javax.swing.GroupLayout(DialogTambahData);
        DialogTambahData.setLayout(DialogTambahDataLayout);
        DialogTambahDataLayout.setHorizontalGroup(
            DialogTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DialogTambahDataLayout.createSequentialGroup()
                .addComponent(PanelKandidat, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );
        DialogTambahDataLayout.setVerticalGroup(
            DialogTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogTambahDataLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelKandidat, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        home.setBackground(new java.awt.Color(0, 102, 204));

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        open.setBackground(new java.awt.Color(204, 204, 204));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel33.setText("Selamat Datang, di Aplikasi ");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel34.setText("Sistem Pendukung Keputusan Evaluasi Kinerja Karyawan atau Penetapan Karyawan Tetap");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel36.setText(" Menggunakan Metode Simple Additive Weigthing (SAW)");

        javax.swing.GroupLayout openLayout = new javax.swing.GroupLayout(open);
        open.setLayout(openLayout);
        openLayout.setHorizontalGroup(
            openLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(openLayout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, openLayout.createSequentialGroup()
                .addGap(0, 74, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, openLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addGap(195, 195, 195))
        );
        openLayout.setVerticalGroup(
            openLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(openLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jLabel33)
                .addGap(44, 44, 44)
                .addComponent(jLabel34)
                .addGap(47, 47, 47)
                .addComponent(jLabel36)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(102, 102, 102));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText(" Data Bobot");

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel42.setText("DATA BOBOT");

        tblbobot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tblbobot);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton1.setText("Tampilkan");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel41.setText("Catatan : Tabel didapat dari inputan sebelumnya");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel41)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(421, 421, 421)
                        .addComponent(jButton1))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jLabel42)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(16, 16, 16)
                .addComponent(jLabel41)
                .addGap(14, 14, 14)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout DataBobotLayout = new javax.swing.GroupLayout(DataBobot);
        DataBobot.setLayout(DataBobotLayout);
        DataBobotLayout.setHorizontalGroup(
            DataBobotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DataBobotLayout.setVerticalGroup(
            DataBobotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataBobotLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneLayout = new javax.swing.GroupLayout(Pane);
        Pane.setLayout(PaneLayout);
        PaneLayout.setHorizontalGroup(
            PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(DataKriteria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Peringkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(CetakLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(DialogTambahData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneLayout.createSequentialGroup()
                    .addContainerGap(56, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(494, Short.MAX_VALUE)))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(open, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(52, Short.MAX_VALUE)))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(DataBobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(80, Short.MAX_VALUE)))
        );
        PaneLayout.setVerticalGroup(
            PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(DataKriteria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addComponent(Peringkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 67, Short.MAX_VALUE)))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(CetakLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneLayout.createSequentialGroup()
                    .addComponent(DialogTambahData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(62, Short.MAX_VALUE)))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(open, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(94, Short.MAX_VALUE)))
            .addGroup(PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(DataBobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(109, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(menuutama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuutama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1292, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 742, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogOutMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new login1().setVisible(true);
    }//GEN-LAST:event_btn_LogOutMouseClicked

    private void btn_LogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogOutMouseEntered
        // TODO add your handling code here:
        btn_LogOut.setBackground(new Color(51,51,51));
        btn_LogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_LogOutMouseEntered

    private void btn_LogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogOutMouseExited
        // TODO add your handling code here:
        btn_LogOut.setBackground(Color.BLACK);
    }//GEN-LAST:event_btn_LogOutMouseExited

    private void btn_LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LogOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LogOutActionPerformed

    private void btn_DataKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DataKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DataKaryawanActionPerformed

    private void btn_DataKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataKriteriaMouseClicked
        // TODO add your handling code here:
        Pane.removeAll();
        Pane.repaint();
        Pane.revalidate();

        //add panel, add panel
        Pane.add(DataKriteria);
        Pane.repaint();
        Pane.revalidate();
    }//GEN-LAST:event_btn_DataKriteriaMouseClicked

    private void btn_DataKriteriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataKriteriaMouseEntered
        // TODO add your handling code here:
        btn_DataKriteria.setBackground(new Color(51,51,51));
        btn_DataKriteria.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_DataKriteriaMouseEntered

    private void btn_DataKriteriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataKriteriaMouseExited
        // TODO add your handling code here:
         btn_DataKriteria.setBackground(hoverButton);
    }//GEN-LAST:event_btn_DataKriteriaMouseExited

    private void btn_DataKriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DataKriteriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DataKriteriaActionPerformed

    private void btn_PeringkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PeringkatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_PeringkatActionPerformed

    private void Laporan_DataKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataKaryawanMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Laporan_DataKaryawanMouseClicked

    private void Laporan_DataKaryawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataKaryawanMouseEntered
        // TODO add your handling code here:
        Laporan_DataKaryawan.setBackground(new Color(51,51,51));
        Laporan_DataKaryawan.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_Laporan_DataKaryawanMouseEntered

    private void Laporan_DataKaryawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataKaryawanMouseExited
        // TODO add your handling code here:
        Laporan_DataKaryawan.setBackground(hoverButton);
    }//GEN-LAST:event_Laporan_DataKaryawanMouseExited

    private void Laporan_DataKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Laporan_DataKaryawanActionPerformed
        // TODO add your handling code here:
        JasperReport reports;
        
        String path = ".\\src\\laporan\\datakaryawan.jasper";
        try {
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
            System.out.println("Masuk try");
            
        } catch (JRException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Laporan_DataKaryawanActionPerformed

    private void Laporan_DataBobotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataBobotMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_Laporan_DataBobotMouseClicked

    private void Laporan_DataBobotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataBobotMouseEntered
        // TODO add your handling code here:
        Laporan_DataBobot.setBackground(new Color(51,51,51));
        Laporan_DataBobot.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_Laporan_DataBobotMouseEntered

    private void Laporan_DataBobotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataBobotMouseExited
        // TODO add your handling code here:
        Laporan_DataBobot.setBackground(hoverButton);
    }//GEN-LAST:event_Laporan_DataBobotMouseExited

    private void Laporan_DataBobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Laporan_DataBobotActionPerformed
        // TODO add your handling code here:
        JasperReport reports;
        
        String path = ".\\src\\laporan\\Data Bobot.jasper";
        try {
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
            System.out.println("Masuk try");
            
        } catch (JRException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Laporan_DataBobotActionPerformed

    private void Laporan_DataKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataKriteriaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_Laporan_DataKriteriaMouseClicked

    private void Laporan_DataKriteriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataKriteriaMouseEntered
        // TODO add your handling code here:
        Laporan_DataKriteria.setBackground(new Color(51,51,51));
        Laporan_DataKriteria.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_Laporan_DataKriteriaMouseEntered

    private void Laporan_DataKriteriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataKriteriaMouseExited
        // TODO add your handling code here:
        Laporan_DataKriteria.setBackground(hoverButton);
    }//GEN-LAST:event_Laporan_DataKriteriaMouseExited

    private void Laporan_DataKriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Laporan_DataKriteriaActionPerformed
        // TODO add your handling code here:
        JasperReport reports;
        
            String path = ".\\src\\laporan\\Data Kriteria.jasper";
        try {
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
            System.out.println("Masuk try");
            
        } catch (JRException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Laporan_DataKriteriaActionPerformed
 
    private void Laporan_DataHasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataHasilMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_Laporan_DataHasilMouseClicked

    private void Laporan_DataHasilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataHasilMouseEntered
        // TODO add your handling code here:
        Laporan_DataHasil.setBackground(new Color(51,51,51));
        Laporan_DataHasil.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_Laporan_DataHasilMouseEntered

    private void Laporan_DataHasilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_DataHasilMouseExited
        // TODO add your handling code here:
        Laporan_DataHasil.setBackground(hoverButton);
    }//GEN-LAST:event_Laporan_DataHasilMouseExited

    private void Laporan_DataHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Laporan_DataHasilActionPerformed
        // TODO add your handling code here:
        JasperReport reports;
        
        String path = ".\\src\\laporan\\Data Keputusan1.jasper";
        try {
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_spk_kinerja","root","");
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
            System.out.println("Masuk try");
            
        } catch (JRException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(menuutama1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Laporan_DataHasilActionPerformed

    private void cm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cm2ActionPerformed
        // TODO add your handling code here:
        switch(cm2.getSelectedIndex()){
            case 0:
            c2.setText("5");
            break;
            case 1:
            c2.setText("4");
            break;
            case 2:
            c2.setText("3");
            break;
            case 3:
            c2.setText("2");
            break;
        }
    }//GEN-LAST:event_cm2ActionPerformed

    private void cm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cm3ActionPerformed
        // TODO add your handling code here:
        switch(cm3.getSelectedIndex()){
            case 0:
            c3.setText("5");
            break;
            case 1:
            c3.setText("4");
            break;
            case 2:
            c3.setText("3");
            break;
            case 3:
            c3.setText("2");
            break;
        }
    }//GEN-LAST:event_cm3ActionPerformed

    private void cm4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cm4ActionPerformed
        // TODO add your handling code here:
        switch(cm4.getSelectedIndex()){
            case 0:
            c4.setText("5");
            break;
            case 1:
            c4.setText("4");
            break;
            case 2:
            c4.setText("3");
            break;
            case 3:
            c4.setText("2");
            break;
        }
    }//GEN-LAST:event_cm4ActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
       // TODO add your handling code here:
        if (idkaryawan.getText().equals("") || txtnama.getText().equals("") || c1.getText().equals("") || c2.getText().equals("") || c3.getText().equals("") || c4.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !","", JOptionPane.INFORMATION_MESSAGE);

        } else {
            String id = idkaryawan.getText();
            String nama = txtnama.getText();
            String cr = c1.getText();
            String ck = c2.getText();
            String cn = c3.getText();
            String cm = c4.getText();
  
            try {
                String sql = "INSERT INTO tbl_kriteria VALUES (?, ?, ?, ?, ?, ?)";
                java.sql.Connection conn=(java.sql.Connection)koneksi.getconnetion();

                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1,id);
                p.setString(2, nama);
                p.setString(3, cr);
                p.setString(4, ck);
                p.setString(5, cn);
                p.setString(6, cm);
                p.executeUpdate();
                p.close();
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                loadDatakriteria();
                idkaryawan.setText("");
                txtnama.setText("");
                c1.setText("");
                c2.setText("");
                c3.setText("");
                c4.setText("");
         
                JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        if (idkaryawan.getText().equals("") ||txtnama.getText().equals("") ||c1.getText().equals("") || 
                c2.getText().equals("") || c3.getText().equals("")||c4.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "gagal!","", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                String sql ="delete from tbl_kriteria where id='"+idkaryawan.getText()+"'";
                java.sql.Connection conn=(Connection)koneksi.getconnetion();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "berhasil di hapus");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            loadDatakriteria();
            reset2();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        editDataKriteria();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_DataKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataKaryawanMouseClicked
        // TODO add your handling code here:
        Pane.removeAll();
        Pane.repaint();
        Pane.revalidate();

        //add panel, add panel
        Pane.add(DialogTambahData);
        Pane.repaint();
        Pane.revalidate();
        
    }//GEN-LAST:event_btn_DataKaryawanMouseClicked

    private void btn_DataKaryawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataKaryawanMouseEntered
        // TODO add your handling code here:
        btn_DataKaryawan.setBackground(new Color(51,51,51));
        btn_DataKaryawan.setCursor(new Cursor(Cursor.HAND_CURSOR));       

    }//GEN-LAST:event_btn_DataKaryawanMouseEntered

    private void btn_DataKaryawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataKaryawanMouseExited
        // TODO add your handling code here:
        btn_DataKaryawan.setBackground(hoverButton);
    }//GEN-LAST:event_btn_DataKaryawanMouseExited

    private void btn_PeringkatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PeringkatMouseExited
        // TODO add your handling code here:
        btn_Peringkat.setBackground(hoverButton);
    }//GEN-LAST:event_btn_PeringkatMouseExited

    private void btn_PeringkatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PeringkatMouseEntered
        // TODO add your handling code here:
        btn_Peringkat.setBackground(new Color(51,51,51));
        btn_Peringkat.setCursor(new Cursor(Cursor.HAND_CURSOR));       

    }//GEN-LAST:event_btn_PeringkatMouseEntered

    private void btn_PeringkatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PeringkatMouseClicked
        // TODO add your handling code here:
        Pane.removeAll();
        Pane.repaint();
        Pane.revalidate();
        
        //add panel, add panel
        Pane.add(Peringkat);
        Pane.repaint();
        Pane.revalidate();

    }//GEN-LAST:event_btn_PeringkatMouseClicked

    private void c4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c4ActionPerformed

    private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c1ActionPerformed

    private void c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c2ActionPerformed

    private void c3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c3ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        tabelbobot();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btn_BobotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BobotMouseClicked
        // TODO add your handling code here:
        Pane.removeAll();
        Pane.repaint();
        Pane.revalidate();

        //add panel, add panel
        Pane.add(DataBobot);
        Pane.repaint();
        Pane.revalidate();
    }//GEN-LAST:event_btn_BobotMouseClicked

    private void btn_BobotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BobotMouseEntered
        // TODO add your handling code here:
        btn_Bobot.setBackground(new Color(51,51,51));
        btn_Bobot.setCursor(new Cursor(Cursor.HAND_CURSOR) );
    
    }//GEN-LAST:event_btn_BobotMouseEntered

    private void btn_BobotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BobotMouseExited
        // TODO add your handling code here:
        btn_Bobot.setBackground(hoverButton);
    }//GEN-LAST:event_btn_BobotMouseExited

    private void btn_BobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BobotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_BobotActionPerformed

    private void cm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cm1ActionPerformed
        // TODO add your handling code here:
        switch(cm1.getSelectedIndex()){
            case 0:
            c1.setText("5");
            break;
            case 1:
            c1.setText("4");
            break;
            case 2:
            c1.setText("3");
            break;
            case 3:
            c1.setText("2");
            break;
       
        }
    }//GEN-LAST:event_cm1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void idkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idkaryawanActionPerformed
        // TODO add your handling code here:
    try {
        String sql = "SELECT * FROM tbl_karyawan where id ='" + idkaryawan.getText() + "'";
        java.sql.Connection conn = koneksi.getconnetion();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            txtnama.setText(rs.getString("nama"));
        }
    } catch (Exception e){
            System.out.println("Error | " + e.getMessage());
    }                                         
    
    }//GEN-LAST:event_idkaryawanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       normalisasi();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    cariperingkat();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblkriteria1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkriteria1MouseClicked
        // TODO add your handling code here:                                      
        btn_simpan.setEnabled(true);
        btn_edit.setEnabled(true);
        btn_hapus.setEnabled(true);
        int i = tblkriteria1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) tblkriteria1.getValueAt(i, 0);
        idkaryawan.setText(id);

        String nama = (String) tblkriteria1.getValueAt (i, 1);
        txtnama.setText(nama);

        String ca = (String) tblkriteria1.getValueAt (i, 2);
        c1.setText(ca);

        String cb = (String) tblkriteria1.getValueAt (i, 3);
        c2.setText(cb);

        String cc = (String) tblkriteria1.getValueAt (i, 4);
        c3.setText(cc);

        String cd = (String) tblkriteria1.getValueAt (i, 5);
        c4.setText(cd);  
    }//GEN-LAST:event_tblkriteria1MouseClicked

    private void tampilkaryawan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tampilkaryawan1MouseEntered
        // TODO add your handling code here:
        tampilkaryawan1.setBackground(new Color(51,51,51));
        tampilkaryawan1.setCursor(new Cursor(Cursor.HAND_CURSOR) );
    }//GEN-LAST:event_tampilkaryawan1MouseEntered

    private void tampilkaryawan1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tampilkaryawan1MouseExited
        // TODO add your handling code here:
        tampilkaryawan1.setBackground(hoverButton);
    }//GEN-LAST:event_tampilkaryawan1MouseExited

    private void tampilkaryawan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tampilkaryawan1MouseClicked
        // TODO add your handling code here:
        tampilkan1();
    }//GEN-LAST:event_tampilkaryawan1MouseClicked

    private void btn_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit1ActionPerformed
        // TODO add your handling code here:
        editDataKaryawan();
        
    }//GEN-LAST:event_btn_edit1ActionPerformed

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed
        // TODO add your handling code here:
        if(txt_id.getText().equals("") ||txt_nama.getText().equals("") || rbLaki.getText().equals("")|| 
                cmb_pendidikan.getSelectedItem().equals("")|| txt_nohp.getText().equals("")|| txt_alamat.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "elekronik berkah", JOptionPane.INFORMATION_MESSAGE);
        }else{
        try {
            String sql ="delete from tbl_karyawan where id='"+txt_id.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getconnetion();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadDatakaryawan();
        txt_id.setText("");
        txt_nama.setText("");
        reset1();
        }
        
    }//GEN-LAST:event_btn_hapus1ActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void rbPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPerempuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPerempuanActionPerformed

    private void rbLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbLakiActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_nohpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nohpActionPerformed

    private void btn_simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan1ActionPerformed
        // TODO add your handling code here:
        insertdatakaryawan();
    }//GEN-LAST:event_btn_simpan1ActionPerformed

    private void btn_simpan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan1MouseEntered
        // TODO add your handling code here:
        btn_simpan1.setBackground(new Color(51,51,51));
        btn_simpan1.setCursor(new Cursor(Cursor.HAND_CURSOR) );
    }//GEN-LAST:event_btn_simpan1MouseEntered

    private void btn_simpan1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan1MouseExited
        // TODO add your handling code here:
       btn_simpan1.setBackground(hoverButton);
    }//GEN-LAST:event_btn_simpan1MouseExited

    private void btn_edit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit1MouseEntered
        // TODO add your handling code here:
        btn_edit1.setBackground(new Color(51,51,51));
        btn_edit1.setCursor(new Cursor(Cursor.HAND_CURSOR) );

    }//GEN-LAST:event_btn_edit1MouseEntered

    private void btn_edit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit1MouseExited
        // TODO add your handling code here:
        btn_edit1.setBackground(hoverButton);
    }//GEN-LAST:event_btn_edit1MouseExited

    private void btn_hapus1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus1MouseEntered
        // TODO add your handling code here:
        btn_hapus1.setBackground(new Color(51,51,51));
        btn_hapus1.setCursor(new Cursor(Cursor.HAND_CURSOR) );

    }//GEN-LAST:event_btn_hapus1MouseEntered

    private void btn_hapus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus1MouseExited
        // TODO add your handling code here:
        btn_hapus1.setBackground(hoverButton);
    }//GEN-LAST:event_btn_hapus1MouseExited

    private void tampilkaryawan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampilkaryawan1ActionPerformed
        // TODO add your handling code here:
        tampilkan1();
    }//GEN-LAST:event_tampilkaryawan1ActionPerformed

    private void tblkaryawan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkaryawan2MouseClicked
        // TODO add your handling code here:
        btn_simpan1.setEnabled(true);
        btn_edit1.setEnabled(true);
        btn_hapus1.setEnabled(true);
        int i = tblkaryawan2.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) tblkaryawan2.getValueAt(i, 0);
        txt_id.setText(id);

        String nama = (String) tblkaryawan2.getValueAt (i, 1);
        txt_nama.setText(nama);

         String jk = (String) tblkaryawan2.getValueAt(i, 2);
        String l ="Laki-Laki";
        if(jk.equals(l)){
            rbLaki.setSelected(true);
        }else{
            rbPerempuan.setSelected(true);     
                    }

        String pendidikan = (String) tblkaryawan2.getValueAt (i, 3);
        cmb_pendidikan.setSelectedItem(pendidikan);

        String hp = (String) tblkaryawan2.getValueAt (i, 4);
        txt_nohp.setText(hp);

        String alamat = (String) tblkaryawan2.getValueAt (i, 5);
        txt_alamat.setText(alamat);  
                
    }//GEN-LAST:event_tblkaryawan2MouseClicked

    private void btn_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseClicked
        // TODO add your handling code here:
 
    }//GEN-LAST:event_btn_simpanMouseClicked

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
            java.util.logging.Logger.getLogger(menuutama1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuutama1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuutama1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuutama1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuutama1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CetakLaporan;
    private javax.swing.JPanel DataBobot;
    private javax.swing.JPanel DataKriteria;
    private javax.swing.JPanel DialogTambahData;
    private javax.swing.JButton Laporan_DataBobot;
    private javax.swing.JButton Laporan_DataHasil;
    private javax.swing.JButton Laporan_DataKaryawan;
    private javax.swing.JButton Laporan_DataKriteria;
    private javax.swing.JPanel Pane;
    private javax.swing.JTabbedPane PanelKandidat;
    private javax.swing.JPanel Peringkat;
    private javax.swing.JLabel btn;
    private javax.swing.ButtonGroup btnG;
    private javax.swing.JButton btn_Bobot;
    private javax.swing.JButton btn_DataKaryawan;
    private javax.swing.JButton btn_DataKriteria;
    private javax.swing.JButton btn_LogOut;
    private javax.swing.JButton btn_Peringkat;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_edit1;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_simpan1;
    private javax.swing.JTextField c1;
    private javax.swing.JTextField c2;
    private javax.swing.JTextField c3;
    private javax.swing.JTextField c4;
    private javax.swing.JComboBox<String> cm1;
    private javax.swing.JComboBox<String> cm2;
    private javax.swing.JComboBox<String> cm3;
    private javax.swing.JComboBox<String> cm4;
    private javax.swing.JComboBox<String> cmb_pendidikan;
    private javax.swing.JPanel header;
    private javax.swing.JPanel home;
    private java.awt.TextField idkaryawan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel menuutama;
    private javax.swing.JPanel open;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JPanel repaint;
    private javax.swing.JPanel repaint1;
    private javax.swing.JButton tampilkaryawan1;
    private javax.swing.JTable tblbobot;
    private javax.swing.JTable tblkaryawan2;
    private javax.swing.JTable tblkriteria1;
    private javax.swing.JTable tnormalisasi;
    private javax.swing.JTable tperingkat;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nohp;
    private javax.swing.JTextField txtnama;
    // End of variables declaration//GEN-END:variables
}