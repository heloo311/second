import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TkHS extends JFrame implements ActionListener {

    Connection conn;
    Statement stm;
    ResultSet rst;
    // tao bang de truy van thong tin tu co so du lieu
    Vector vData1 = new Vector();
    Vector vTitle1 = new Vector();
    JScrollPane tableResult1;
    DefaultTableModel model;
    JTable tb1 = new JTable();
    // cac nut thao tac voi csdl
    JTextField ID;
    JButton ok,back  ;
    int selectedrow = 0;

    public TkHS(String s) {
        super(s);
        try {

            //ket noi den csdl va tao doi tuong stament
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-POGVG9P5:1433;databaseName =DACS2",
                    "sa", "123");
            stm = conn.createStatement();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //tao p de nhom cac nut vao dao dien chinh
        JPanel p = new JPanel();
        // tao nut va gan tai nghe

        ID=new JTextField("                ");
        ok=new JButton("OK");
        ok.addActionListener(this);
        back=new JButton("Back");
        back.addActionListener(this);
        //dat nut vao giao dien
        p.add(ID);
        p.add(ok);
        p.add(back);
        // dat nut xuong duoi dao dien
        this.add(p, "South");
        // nap du lieu vao 2 vecto vtitel len cot , vdata hang du lieu
        reload1();
        //tao bang hien thi thng tin len
        model = new DefaultTableModel(vData1, vTitle1);
        tb1 = new JTable(model);
//        tb1.addMouseListener(this);
        tableResult1 = new JScrollPane(tb1);
        this.getContentPane().add(tableResult1, "North");

        this.setSize(1000, 200);
        this.setLocation(210, 250);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void reload1()
    {
        try {
            // xoa du lieu cu
            String s =ID.getText();
            vTitle1.clear();
            vData1.clear();
            ResultSet rst = stm.executeQuery("select * from SINHVIEN where id='"+s+"'");

            // tao dt ley thong tin
            ResultSetMetaData rstmeta = rst.getMetaData();

            int num_column = rstmeta.getColumnCount();
            // cb du lieu tao bang
            for (int i = 1; i <= num_column; i++) {
                vTitle1.add(rstmeta.getColumnLabel(i));
            }
            while (rst.next()) {

                Vector row = new Vector(num_column);
                for (int i = 1; i <= num_column; i++) {
                    row.add(rst.getString(i));
                }
                vData1.add(row);
            }

            rst.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void actionPerformed(ActionEvent e){
        if(ID.getText().equals("                         ")){
            JOptionPane.showMessageDialog(rootPane,"No data entered");
        }
        if (e.getActionCommand().equals("OK")) {

            reload1();
            model.fireTableDataChanged();


        }
        else if (e.getActionCommand().equals("Back")) {

            setVisible(false);
        }

    }
}
