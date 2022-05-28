import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// tao ham cua so nhap moi
public class NutHS extends JFrame implements ActionListener {
    // cac tp giao dien

    JLabel Idlb;
    JTextField Id;

    JLabel Tenlb;
    JTextField Ten;

    JLabel GioiTinhlb;
    JTextField GioiTinh;

    JLabel NgaySinhlb;
    JTextField NgaySinh;

    JLabel MaLoplb;
    JTextField MaLop;

    JLabel MaTinhlb;
    JTextField MaTinh;

    JLabel DTBlb;
    JTextField DTB;



    // bao loi khii nhap sai
    JLabel errorlb;
    JLabel errordetails;

    JButton ok;
    JButton cancle;
    // nhap doi tuong cua gai dien chinh truyen
    HocSinh mst;
    // nhap malk cua bnag khi hiue chinh
    String i1;

    public NutHS(String s, HocSinh st, String i, String ten, String Ngay, String Gioi, String Lop , String Tinh , String DB) {

        super(s);
        // nhap doi tuong cua cua so du lieu chinh
        mst = st;
        //tao giao dien
        Container cont = this.getContentPane();
        cont.setLayout(new GridLayout(11, 6));

        Idlb = new JLabel("Id");
        Id = new JTextField(i);
        cont.add(Idlb);
        cont.add(Id);

        Tenlb = new JLabel("Ten");
        Ten = new JTextField(ten);
        cont.add(Tenlb);
        cont.add(Ten);

        GioiTinhlb = new JLabel("Gioi Tinh");
        GioiTinh = new JTextField(Gioi);
        cont.add(GioiTinhlb);
        cont.add(GioiTinh);

        NgaySinhlb = new JLabel("Ngay Sinh");
        NgaySinh = new JTextField(Ngay);
        cont.add(NgaySinhlb);
        cont.add(NgaySinh);

        MaLoplb = new JLabel("Ma Lop");
        MaLop = new JTextField(Lop);
        cont.add(MaLoplb);
        cont.add(MaLop);

        MaTinhlb = new JLabel("Ma Tinh");
        MaTinh = new JTextField(Tinh);
        cont.add(MaTinhlb);
        cont.add(MaTinh);

        DTBlb = new JLabel("DTB");
        DTB = new JTextField(DB);
        cont.add(DTBlb);
        cont.add(DTB);

        errorlb = new JLabel("");
        errordetails = new JLabel("");

        errorlb.setVisible(false);
        errordetails.setVisible(false);
        cont.add(errorlb);
        cont.add(errordetails);

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        cont.add(ok);
        cont.add(cancel);
        ok.addActionListener(this);
        cancel.addActionListener(this);

        this.setSize(300, 300);
        this.setLocation(300, 400);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        i1 = i;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            insertDB();
        } else this.dispose();

    }

    public void insertDB() {
        // ko nhap la bao loi
        if (Id.getText().equals("")||Ten.getText().equals("") || GioiTinh.getText().equals("") ||
                NgaySinh.getText().equals("") || MaLop.getText().equals("") || MaTinh.getText().equals("")
                || DTB.getText().equals("")) {
            errorlb.setText("Error !!!!!!!!!!");
            errordetails.setText("empty value !!!!!!!!!!!");
            errorlb.setForeground(Color.RED);
            errordetails.setForeground(Color.RED);
            errorlb.setVisible(true);
            errordetails.setVisible(true);
        } else {
            try {
                // lay noi dung go o giao dien
                String id=Id.getText();
                String a = Ten.getText();
                String c = GioiTinh.getText();
                String b = NgaySinh.getText();
                String d = MaLop.getText();
                String e = MaTinh.getText();
                String f = DTB.getText();
                String sql = "";
                if (this.getTitle().equals("Insert form"))
                    sql = "Insert into SINHVIEN(id,HoTen , NgaySinh , GioiTinh , MaLop, MaTinh, DTB) values('" + id + "','" + a + "','" + b + "','" + c + "','" + d + "' , '" + e + "' , '" + f + "')";
                else
                    sql = "Update SINHVIEN set id='" + id + "',HoTen='" + a + "',NgaySinh='" + b + "',GioiTinh='" + c + "',MaLop='" + d + "',MaTinh='" + e + "',DTB='" + f + "'where id=" + i1 + "";
                //cap nhat csdl
                mst.stm.executeUpdate(sql);
                mst.reload();
                mst.model.fireTableDataChanged();
                this.dispose();
                JOptionPane.showMessageDialog(rootPane, "Success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}