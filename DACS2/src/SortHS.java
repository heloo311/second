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

public class SortHS extends JFrame implements ActionListener {

    Connection conn;
    Statement stm;
    ResultSet rst;
    // tao bang de truy van thong tin tu co so du lieu
    Vector vData = new Vector();
    Vector vTitle = new Vector();
    JScrollPane tableResult;
    DefaultTableModel model;
    JTable tb = new JTable();
    // cac nut thao tac voi csdl
    JButton edit, delete, insert, search,quit,sort ,back;
    int selectedrow = 0;

    public SortHS(String s) {
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
        edit = new JButton("Edit");
        edit.addActionListener(this);

        delete = new JButton("Delete");
        delete.addActionListener(this);

        insert = new JButton("Insert");
        insert.addActionListener(this);

        search = new JButton("Search");
        search.addActionListener(this);

        quit=new JButton("Quit");
        quit.addActionListener(this);

        sort = new JButton("Sort by DTB");
        sort.addActionListener(this);

        back = new JButton("Back");
        back.addActionListener(this);
        //dat nut vao giao dien
        p.add(edit);
        p.add(delete);
        p.add(insert);
        p.add(search);
        p.add(quit);
        p.add(sort);
        p.add(back);
        // dat nut xuong duoi dao dien
        this.add(p, "South");
        // nap du lieu vao 2 vecto vtitel len cot , vdata hang du lieu
        reload1();
        //tao bang hien thi thng tin len
        model = new DefaultTableModel(vData, vTitle);
        tb = new JTable(model);
        tableResult = new JScrollPane(tb);
        this.getContentPane().add(tableResult, "North");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocation(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void reload1()
    {
        try {
            // xoa du lieu cu
            vTitle.clear();
            vData.clear();
            ResultSet rst = stm.executeQuery(" select * from SINHVIEN order by DTB");

            // tao dt ley thong tin
            ResultSetMetaData rstmeta = rst.getMetaData();

            int num_column = rstmeta.getColumnCount();
            // cb du lieu tao bang
            for (int i = 1; i <= num_column; i++) {
                vTitle.add(rstmeta.getColumnLabel(i));
            }
            while (rst.next()) {

                Vector row = new Vector(num_column);
                for (int i = 1; i <= num_column; i++) {
                    row.add(rst.getString(i));
                }
                vData.add(row);
            }

            rst.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Delete")) {

        }

        if (e.getActionCommand().equals("Insert")) {


        }

        if (e.getActionCommand().equals("Edit"))
        {

        }

        if(e.getActionCommand().equals("Quit")){
            int x=JOptionPane.showConfirmDialog(this,"Do you want to continue?");
            if (x==JOptionPane.YES_OPTION) {
                this.dispose();
            }
            else if (x==JOptionPane.NO_OPTION){
                setVisible(true);
            }
        }

        if(e.getActionCommand().equals("Sort by DTB")){

        }

        if(e.getActionCommand().equals("Search")){

        }
        if(e.getActionCommand().equals("Back")){

            setVisible(false);
        }
    }
}

