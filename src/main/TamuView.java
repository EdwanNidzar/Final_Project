package main;

import inc.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class TamuView extends javax.swing.JInternalFrame {

    public TamuView() {
        initComponents();
        clear();
        tampil();
    }
    
    public final Connection conn = new Koneksi().getConnetion();
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel tabMode;
    
    public void tampil(){
        Object [] baris = {"NO", "ID Tamu", "Nama", "Alamat", "Nomor Telephone"};
        tabMode = new DefaultTableModel(null, baris);
        tableTamu.setModel(tabMode);
        try {
                String sql = "SELECT * FROM tamu";
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                int no = 0;
                while (rs.next()){
                        no++;
                        String id = rs.getString("id_tamu");
                        String nm = rs.getString("nama");
                        String al = rs.getString("alamat");
                        String nt = rs.getString("no_telp");

                        Object [] data = {no,id,nm,al,nt};
                        tabMode.addRow(data);
                }
        } catch (Exception e){
                System.out.println(e.toString());
        }
    }
    
    public void clear(){
        tID.setText(kodeOtomatis());
        tNama.setText(null);
        tAlamat.setText(null);
        tTelp.setText(null);
    }
    
    public String kodeOtomatis(){
        String kode = "";
        try {
            int kodeLama;
            pst = conn.prepareStatement("SELECT id_tamu FROM tamu ORDER BY id_tamu DESC");
            rs = pst.executeQuery();
            if (!rs.next()){
                kode = "TAMU-0001";
            } else {
                kodeLama=Integer.parseInt(rs.getString(1).substring(5))+1;
                if(kodeLama<10){
                    kode = "TAMU-000"+kodeLama;
                }
                else if(kodeLama >= 10 && kodeLama<100){
                    kode = "TAMU-00"+kodeLama;
                }
                else if(kodeLama >= 100 && kodeLama<1000){
                    kode = "TAMU-0"+kodeLama;
                }
                else{
                    kode = "TAMU-"+kodeLama;
                }
            }
        } catch (Exception e) {
            System.out.println("error kode otomasis : " +e.toString());
        }
        return kode;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        tTelp = new javax.swing.JTextField();
        bKeluar = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        bCetak = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTamu = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data"));

        jLabel1.setText("ID Tamu");

        jLabel2.setText("Nama");

        jLabel3.setText("Alamat");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane1.setViewportView(tAlamat);

        jLabel4.setText("No. Telephone");

        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        bClear.setText("Clear");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
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

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bCetak.setText("Cetak");
        bCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tTelp))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(tID)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(bSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bUbah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCetak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bKeluar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bKeluar)
                    .addComponent(bClear)
                    .addComponent(bHapus)
                    .addComponent(bUbah)
                    .addComponent(bSimpan)
                    .addComponent(bCetak))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Tamu"));

        tableTamu.setModel(new javax.swing.table.DefaultTableModel(
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
        tableTamu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTamuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTamu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if (tID.getText().equals("") || tNama.getText().equals("") || tAlamat.getText().equals("") || tTelp.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Field Tidak Boleh Kosong");
        } else {
            try {
               int s;
               String sql = "INSERT INTO tamu VALUES ('"+tID.getText()+"', '"+tNama.getText()+"', '"+tAlamat.getText()+"', '"+tTelp.getText()+"')";
               st = conn.createStatement();
               s = st.executeUpdate(sql);
               if (s == 1){
                JOptionPane.showMessageDialog(null, "Sukses");
                clear();
                tampil();
               }
           } catch (Exception e){
                System.out.println(e.toString());
           }    
        }
        
    }//GEN-LAST:event_bSimpanActionPerformed

    private void tableTamuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTamuMouseClicked
        // TODO add your handling code here:
        int baris = tableTamu.getSelectedRow();
	tID.setText(tableTamu.getValueAt(baris, 1).toString());
	tNama.setText(tableTamu.getValueAt(baris, 2).toString());
	tAlamat.setText(tableTamu.getValueAt(baris, 3).toString());
        tTelp.setText(tableTamu.getValueAt(baris, 4).toString());
    }//GEN-LAST:event_tableTamuMouseClicked

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_bClearActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        int c = JOptionPane.showConfirmDialog(null, "Ingin Mengubah Data "+tID.getText()+" ?" ,"Informasi", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION){
            if (tID.getText().equals("") || tNama.getText().equals("") || tAlamat.getText().equals("") || tTelp.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Field Tidak Boleh Kosong");
            } else {
                try {
                   int s;
                   String sql = "UPDATE tamu SET nama = '"+tNama.getText()+"', alamat = '"+tAlamat.getText()+"', no_telp = '"+tTelp.getText()+"' WHERE id_tamu = '"+tID.getText()+"' ";
                   st = conn.createStatement();
                   s = st.executeUpdate(sql);
                   if (s == 1){
                    JOptionPane.showMessageDialog(null, "Sukses");
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
        int c = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data "+tID.getText()+" ?","Informasi", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION){
            try {
                int s;
                String sql = "DELETE FROM tamu WHERE id_tamu = '"+tID.getText()+"' ";
                st = conn.createStatement();
                s = st.executeUpdate(sql);
                if (s == 1){
                    JOptionPane.showMessageDialog(null, "Sukses");
                    clear();
                    tampil();
                }
            } catch (Exception e){
                    System.out.println(e.toString());
            } 
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        // TODO add your handling code here:
        int c = JOptionPane.showConfirmDialog(null, "Yakin Ingin Keluar?", null, JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION){
            dispose();
        }
    }//GEN-LAST:event_bKeluarActionPerformed

    private void bCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCetakActionPerformed
        // TODO add your handling code here:
        JasperReport reports;

        String path=".\\src\\report\\reportTamu.jasper";
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
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JTextField tID;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tTelp;
    private javax.swing.JTable tableTamu;
    // End of variables declaration//GEN-END:variables
}
