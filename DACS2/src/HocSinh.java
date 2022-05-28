import javax.swing.*;
import java.awt.*;
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

public class HocSinh extends JFrame implements ActionListener, MouseListener {

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

    public HocSinh(String s) {
        super(s);
        try {
            //ket noi den csdl va tao doi tuong stament
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-POGVG9P5:1433;databaseName =DACS2", "sa", "123");
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


        //dat nut vao giao dien
        p.add(edit);
        p.add(delete);
        p.add(insert);
        p.add(search);
        p.add(quit);
        p.add(sort);
        // dat nut xuong duoi dao dien
        this.add(p, "South");
        // nap du lieu vao 2 vecto vtitel len cot , vdata hang du lieu
        reload();
        //tao bang hien thi thng tin len
        model = new DefaultTableModel(vData, vTitle);
        tb = new JTable(model);
        tb.addMouseListener(this);
        tableResult = new JScrollPane(tb);
        this.getContentPane().add(tableResult, "North");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocation(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // doc du lieu vao nap vao 2 vecto
    public void reload()
    {
        try {
            // xoa du lieu cu
            vTitle.clear();
            vData.clear();
            ResultSet rst = stm.executeQuery("select * from SINHVIEN");
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
    // ham delete
    public void delete() {
        try {
            // lay nd hang da chon
            Vector st = (Vector)vData.elementAt(selectedrow);
            String sql = "Delete from SINHVIEN where id ='" + st.elementAt(0) + "'";
            stm.executeUpdate(sql);
            // xooa nd tuong ung
            vData.remove(selectedrow);
            // cap nhat lai bnag
            model.fireTableDataChanged();
            JOptionPane.showMessageDialog(rootPane, "Deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Delete")) {
            delete();
        }

        if (e.getActionCommand().equals("Insert")) {
            new NutHS("Insert form",this,"","","","","","","");

        }

        if (e.getActionCommand().equals("Edit"))
        {
            Vector st = (Vector) vData.elementAt(selectedrow);
            // tao cua so hiue chinh hgang da chon
            new NutHS("Edit form",this,(String) st.elementAt(0),(String) st.elementAt(1),(String) st.elementAt(2),(String) st.elementAt(3),(String) st.elementAt(4)
                    ,(String) st.elementAt(5),(String) st.elementAt(6));
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
            new SortHS("Sort Students By Name");
            setVisible(false);
        }

        if(e.getActionCommand().equals("Search")){
            new TkHS("Hoc Sinh");
        }
    }
    public void mouseClicked(MouseEvent e) {
        selectedrow = tb.getSelectedRow();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}








