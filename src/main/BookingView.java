package main;

import inc.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class BookingView extends javax.swing.JInternalFrame {

    public BookingView() {
        initComponents();
        clear();
        setKamar();
        setTamu();
        tampil();
    }
    
    public final Connection conn = new Koneksi().getConnetion();
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel tabMode;
    
    public void setKamar(){
        try {
            String sql = "SELECT * FROM kamar";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                cKamar.addItem(rs.getString("id_kamar"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void setTamu(){
        try {
            String sql = "SELECT * FROM tamu";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                cTamu.addItem(rs.getString("id_tamu"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void tampil(){
        Object [] baris = {"NO", "ID Booking", "ID Tamu", "Nama", "Alamat", "Nomor Telp", "ID Kamar", "Nomor Kamar", "Type Kamar", "Tanggal Masuk", "Tanggal Keluar","Harga", "Uang Masuk", "Kembalian"};
        tabMode = new DefaultTableModel(null, baris);
        tableBooking.setModel(tabMode);
        try {
                String sql = "SELECT booking.id_booking, booking.id_tamu, tamu.nama, tamu.alamat, tamu.no_telp, booking.id_kamar, kamar.nomor, kamar.type, booking.tanggal_masuk, booking.tanggal_keluar, booking.harga, booking.uang_masuk, booking.kembalian FROM booking, tamu, kamar WHERE booking.id_tamu = tamu.id_tamu AND booking.id_kamar = kamar.id_kamar";
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                int no = 0;
                while (rs.next()){
                        no++;
                        String id = rs.getString("id_booking");
                        String tamu = rs.getString("id_tamu");
                        String nama = rs.getString("nama");
                        String alamat = rs.getString("alamat");
                        String telp = rs.getString("no_telp");
                        String kamar = rs.getString("id_kamar");
                        String nomor = rs.getString("nomor");
                        String type = rs.getString("type");
                        String masuk  = rs.getString("tanggal_masuk");
                        String keluar  = rs.getString("tanggal_keluar");
                        String harga = rs.getString("harga");
                        String uang = rs.getString("uang_masuk");
                        String kembalian = rs.getString("kembalian");

                        Object [] data = {no,id,tamu,nama,alamat,telp,kamar,nomor,type,masuk,keluar,harga,uang,kembalian};
                        tabMode.addRow(data);
                }
        } catch (Exception e){
                System.out.println(e.toString());
        }
    }
    
    public String kodeOtomatis(){
        String kode = "";
        try {
            int kodeLama;
            pst = conn.prepareStatement("SELECT id_booking FROM booking ORDER BY id_booking DESC");
            rs = pst.executeQuery();
            if (!rs.next()){
                kode = "PSN-0001";
            } else {
                kodeLama=Integer.parseInt(rs.getString(1).substring(4))+1;
                if(kodeLama<10){
                    kode = "PSN-000"+kodeLama;
                }
                else if(kodeLama >= 10 && kodeLama<100){
                    kode = "PSN-00"+kodeLama;
                }
                else if(kodeLama >= 100 && kodeLama<1000){
                    kode = "PSN-0"+kodeLama;
                }
                else{
                    kode = "PSN-"+kodeLama;
                }
            }
        } catch (Exception e) {
            System.out.println("error kode otomasis : " +e.toString());
        }
        return kode;
    }
    
    public void clear(){
        tIdBooking.setText(kodeOtomatis());
        
        cTamu.setSelectedItem(null);
        tNamaTamu.setText(null);
        tAlamat.setText(null);
        tTelp.setText(null);
        
        cKamar.setSelectedItem(null);
        tNomorKamar.setText(null);
        tHarga.setText(null);
        tType.setText(null);
        
        tgl_masuk.setDate(null);
        tgl_keluar.setDate(null);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cKamar = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tNomorKamar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tType = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tHarga = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tIdBooking = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tgl_masuk = new com.toedter.calendar.JDateChooser();
        tgl_keluar = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cTamu = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        tNamaTamu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tAlamat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tTelp = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        bSimpan = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        bKeluar = new javax.swing.JButton();
        bCetak = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBooking = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tUangMasuk = new javax.swing.JTextField();
        bHitung = new javax.swing.JButton();
        tKembalian = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Booking");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kamar"));

        jLabel2.setText("ID Kamar");

        cKamar.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cKamarPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setText("Nomor Kamar");

        tNomorKamar.setEditable(false);

        jLabel4.setText("Type");

        tType.setEditable(false);

        jLabel5.setText("Harga");

        tHarga.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cKamar, 0, 196, Short.MAX_VALUE)
                    .addComponent(tHarga)
                    .addComponent(tNomorKamar)
                    .addComponent(tType))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tNomorKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Booking"));

        jLabel1.setText("ID Booking");

        jLabel10.setText("Tanggal Masuk");

        jLabel11.setText("Tanggal Keluar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgl_keluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tIdBooking, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(tgl_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tIdBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(tgl_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(tgl_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tamu"));

        jLabel6.setText("ID Tamu");

        cTamu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cTamuPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel7.setText("Nama Tamu");

        tNamaTamu.setEditable(false);

        jLabel8.setText("Alamat");

        tAlamat.setEditable(false);

        jLabel9.setText("No Telp");

        tTelp.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cTamu, 0, 196, Short.MAX_VALUE)
                    .addComponent(tTelp)
                    .addComponent(tNamaTamu)
                    .addComponent(tAlamat))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cTamu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tNamaTamu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Button Grup"));

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bHapus.setText("Hapus");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        bUbah.setText("Ubah");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        bClear.setText("Clear");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        bCetak.setText("Cetak");
        bCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(bSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bUbah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bCetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bKeluar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSimpan)
                    .addComponent(bHapus)
                    .addComponent(bUbah)
                    .addComponent(bClear)
                    .addComponent(bKeluar)
                    .addComponent(bCetak))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Booking"));

        tableBooking.setModel(new javax.swing.table.DefaultTableModel(
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
        tableBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBookingMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBooking);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pembayaran"));

        jLabel12.setText("Uang Masuk");

        jLabel13.setText("Kembalian");

        bHitung.setText("Hitung");
        bHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHitungActionPerformed(evt);
            }
        });

        tKembalian.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bHitung))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tUangMasuk)
                            .addComponent(tKembalian, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tUangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(bHitung)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addComponent(tKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cTamuPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cTamuPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM tamu WHERE id_tamu = '"+cTamu.getSelectedItem()+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                tNamaTamu.setText(rs.getString("nama"));
                tAlamat.setText(rs.getString("alamat"));
                tTelp.setText(rs.getString("no_telp"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_cTamuPopupMenuWillBecomeInvisible

    private void cKamarPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cKamarPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM kamar WHERE id_kamar = '"+cKamar.getSelectedItem()+"' ";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                tNomorKamar.setText(rs.getString("nomor"));
                tType.setText(rs.getString("type"));
                tHarga.setText(rs.getString("harga"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_cKamarPopupMenuWillBecomeInvisible

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        String id_tamu = cTamu.getSelectedItem().toString();
        String id_kamar = cKamar.getSelectedItem().toString();
        
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String masuk = String.valueOf(dateformat.format(tgl_masuk.getDate()));
        String keluar = String.valueOf(dateformat.format(tgl_keluar.getDate()));
        
        if (tIdBooking.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ID Booking Tidak Boleh Kosong");
            tIdBooking.requestFocus();
        } else if (tgl_masuk.getDate().equals(null)){
            JOptionPane.showMessageDialog(null, "Tanggal Masuk Tidak Boleh Kosong");
        } else if (tgl_keluar.getDate().equals(null)){
            JOptionPane.showMessageDialog(null, "Tanggal Keluar Tidak Boleh Kosong");
        } else if (cTamu.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Tamu");
        } else if (cKamar.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Kamar");
        } else {
            try {
               int s;
               String sql = "INSERT INTO booking VALUES ('"+tIdBooking.getText()+"', '"+id_tamu+"', '"+id_kamar+"'  , '"+masuk+"', '"+keluar+"', '"+tHarga.getText()+"', '"+tUangMasuk.getText()+"', '"+tKembalian.getText()+"' )";
               st = conn.createStatement();
               s = st.executeUpdate(sql);
               if (s == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                clear();
                tampil();
               }
           } catch (Exception e){
                System.out.println(e.toString());
           }    
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void tableBookingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBookingMouseClicked
        // TODO add your handling code here:
        try {
            int baris = tableBooking.getSelectedRow();
            tIdBooking.setText(tableBooking.getValueAt(baris, 1).toString());
            cTamu.setSelectedItem(tableBooking.getValueAt(baris, 2).toString());
            tNamaTamu.setText(tableBooking.getValueAt(baris,3).toString());
            tAlamat.setText(tableBooking.getValueAt(baris,4).toString());
            tTelp.setText(tableBooking.getValueAt(baris,5).toString());
            
            cKamar.setSelectedItem(tableBooking.getValueAt(baris, 6).toString());
            tNomorKamar.setText(tableBooking.getValueAt(baris,7).toString());
            tType.setText(tableBooking.getValueAt(baris,8).toString());
            
            String masuk = tableBooking.getValueAt(baris, 9).toString();
            Date stanggal = new SimpleDateFormat("yyyy-MM-dd").parse(masuk);
            tgl_masuk.setDate(stanggal);
            
            String keluar = tableBooking.getValueAt(baris, 10).toString();
            Date tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(keluar);
            tgl_keluar.setDate(tanggal);
            
            tHarga.setText(tableBooking.getValueAt(baris,11).toString());
            tUangMasuk.setText(tableBooking.getValueAt(baris,12).toString());
            tKembalian.setText(tableBooking.getValueAt(baris,13).toString());
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_tableBookingMouseClicked

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        // TODO add your handling code here:
        int c = JOptionPane.showConfirmDialog(null, "Yakin Ingin Keluar?", null, JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION){
            dispose();
        }
    }//GEN-LAST:event_bKeluarActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_bClearActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String masuk = String.valueOf(dateformat.format(tgl_masuk.getDate()));
        String keluar = String.valueOf(dateformat.format(tgl_keluar.getDate()));
        
        String id_tamu = cTamu.getSelectedItem().toString();
        String id_kamar = cKamar.getSelectedItem().toString();
        
        int c = JOptionPane.showConfirmDialog(null, "Ingin Mengubah Data "+tIdBooking.getText()+" ?" ,"Informasi", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION){
            if (tIdBooking.getText().equals("")){
                JOptionPane.showMessageDialog(null, "ID Booking Tidak Boleh Kosong");
                tIdBooking.requestFocus();
            } else if (tgl_masuk.getDate().equals(null)){
                JOptionPane.showMessageDialog(null, "Tanggal Masuk Tidak Boleh Kosong");
            } else if (tgl_keluar.getDate().equals(null)){
                JOptionPane.showMessageDialog(null, "Tanggal Keluar Tidak Boleh Kosong");
            } else if (cTamu.getSelectedItem().equals(null)){
                JOptionPane.showMessageDialog(null, "Silahkan Pilih Tamu");
            } else if (cKamar.getSelectedItem().equals(null)){
                JOptionPane.showMessageDialog(null, "Silahkan Pilih Kamar");
            } else {
                    try {
                       int s;
                       String sql = "UPDATE booking id_tamu = '"+id_tamu+"', id_kamar = '"+id_kamar+"'  , tanggal_masuk = '"+masuk+"', tanggal_keluar = '"+keluar+"', harga = '"+tHarga.getText()+"', uang_masuk = '"+tUangMasuk.getText()+"', kembalian = '"+tKembalian.getText()+"' WHERE id_booking = '"+tIdBooking.getText()+"' ";
                       st = conn.createStatement();
                       s = st.executeUpdate(sql);
                       if (s == 1){
                        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                        clear();
                        tampil();
                       }
                    } catch (Exception e){
                        System.out.println(e.toString());
                    }    
            }
        }  
    }//GEN-LAST:event_bUbahActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        int c = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data "+tIdBooking.getText()+" ?","Informasi", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION){
            try {
                int s;
                String sql = "DELETE FROM booking WHERE id_booking = '"+tIdBooking.getText()+"' ";
                st = conn.createStatement();
                s = st.executeUpdate(sql);
                if (s == 1){
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    clear();
                    tampil();
                }
            } catch (Exception e){
                    System.out.println(e.toString());
            } 
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHitungActionPerformed
        // TODO add your handling code here:
        int harga = Integer.parseInt(tHarga.getText());
        int uang = Integer.parseInt(tUangMasuk.getText());
        int kembalian = uang - harga;
        tKembalian.setText(String.valueOf(kembalian));
    }//GEN-LAST:event_bHitungActionPerformed

    private void bCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCetakActionPerformed
        // TODO add your handling code here:
        JasperReport reports;

        String path=".\\src\\report\\reportBooking.jasper";
        try {
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_bCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCetak;
    private javax.swing.JButton bClear;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bHitung;
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JComboBox<String> cKamar;
    private javax.swing.JComboBox<String> cTamu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tAlamat;
    private javax.swing.JTextField tHarga;
    private javax.swing.JTextField tIdBooking;
    private javax.swing.JTextField tKembalian;
    private javax.swing.JTextField tNamaTamu;
    private javax.swing.JTextField tNomorKamar;
    private javax.swing.JTextField tTelp;
    private javax.swing.JTextField tType;
    private javax.swing.JTextField tUangMasuk;
    private javax.swing.JTable tableBooking;
    private com.toedter.calendar.JDateChooser tgl_keluar;
    private com.toedter.calendar.JDateChooser tgl_masuk;
    // End of variables declaration//GEN-END:variables
}
