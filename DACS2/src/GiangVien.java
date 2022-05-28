import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Vector;

public class GiangVien extends JFrame implements ActionListener, MouseListener {
    Connection connection;
    Statement stm;
    ResultSet rst;

    Vector vData = new Vector();
    Vector vTitle = new Vector();
    JScrollPane tableResult;
    JTable tb = new JTable();
    DefaultTableModel model;

    JButton edit , delete , insert , quit , search , sort;

    int vitri = 0;

    public GiangVien(String  s){
        super(s);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-POGVG9P5:1433;databaseName =DACS2", "sa", "123");
            stm = connection.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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

        sort = new JButton("Sort by Id");
        sort.addActionListener(this);

        quit=new JButton("Quit");
        quit.addActionListener(this);
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
        this.setSize(1000, 700);
        this.setLocation(210, 250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void reload()
    {
        try {
            // xoa du lieu cu
            vTitle.clear();
            vData.clear();
            ResultSet rst = stm.executeQuery("select * from GiangVien");
            // tao dt ley thong tin
            ResultSetMetaData rstmeta = rst.getMetaData();
            int num_column = rstmeta.getColumnCount();
            // cb du lieu tao bang
            for (int i = 1; i <= num_column; i++) {
                vTitle.add(rstmeta.getColumnLabel(i));
            }
          //  ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY
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
    public void delete() {
        try {
            // lay nd hang da chon
            Vector st = (Vector)vData.elementAt(vitri);
            String sql = "Delete from GiangVien where id ='" + st.elementAt(0) + "'";
            stm.executeUpdate(sql);
            // xooa nd tuong ung
            vData.remove(vitri);
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
            new NutGV("Insert form",this,"","","","","");

        }

        if (e.getActionCommand().equals("Edit"))
        {
            Vector st = (Vector) vData.elementAt(vitri);
            // tao cua so hiue chinh hgang da chon
            new NutGV("Edit form",this,(String) st.elementAt(0),(String) st.elementAt(1),(String) st.elementAt(2),(String) st.elementAt(3),(String) st.elementAt(4));
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

        if(e.getActionCommand().equals("Search")){
            new TkGV("Giang Vien");
        }
        if(e.getActionCommand().equals("Sort by Id")){
            new SortGV("Sort Teachers By Id");
            setVisible(false);
        }

    }
    public void mouseClicked(MouseEvent e) {
        vitri = tb.getSelectedRow();
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
