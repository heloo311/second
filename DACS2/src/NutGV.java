import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// tao ham cua so nhap moi
public class NutGV extends JFrame implements ActionListener {
    // cac tp giao dien

    JLabel Idlb;
    JTextField Id;

    JLabel Tenlb;
    JTextField Ten;

    JLabel GioiTinhlb;
    JTextField GioiTinh;

    JLabel NgaySinhlb;
    JTextField NgaySinh;

    JLabel Quelb;
    JTextField Que;



    // bao loi khii nhap sai
    JLabel errorlb;
    JLabel errordetails;


    JButton ok;
    JButton cancle;
    // nhap doi tuong cua gai dien chinh truyen
    GiangVien mst;
    // nhap malk cua bnag khi hiue chinh
    String i1;

    public NutGV(String s, GiangVien st, String i, String ten, String Gioi, String Ngay, String que) {

        super(s);
        // nhap doi tuong cua cua so du lieu chinh
        mst = st;
        //tao giao dien
        Container cont = this.getContentPane();
        cont.setLayout(new GridLayout(8, 4));

        Idlb = new JLabel("Id");
        Id = new JTextField(i);
        cont.add(Idlb);
        cont.add(Id);

        Tenlb = new JLabel("Ten");
        Ten = new JTextField(ten);
        cont.add(Tenlb);
        cont.add(Ten);

        GioiTinhlb = new JLabel("GioiTinh");
        GioiTinh = new JTextField(Gioi);
        cont.add(GioiTinhlb);
        cont.add(GioiTinh);

        NgaySinhlb = new JLabel("NgaySinh");
        NgaySinh = new JTextField(Ngay);
        cont.add(NgaySinhlb);
        cont.add(NgaySinh);

        Quelb = new JLabel("Que");
        Que = new JTextField(que);
        cont.add(Quelb);
        cont.add(Que);

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
                NgaySinh.getText().equals("") || Que.getText().equals("")) {
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
                String b = GioiTinh.getText();
                String c = NgaySinh.getText();
                String d = Que.getText();
                String sql = "";
                if (this.getTitle().equals("Insert form"))
                    sql = "Insert into Giangvien(id,Ten , GioiTinh , NgaySinh ,Que) values('" + id + "','" + a + "','" + b + "','" + c + "','" + d + "')";
                else
                    sql = "Update Giangvien set id='" + id + "',Ten='" + a + "',GioiTinh='" + b + "',NgaySinh='" + c + "',Que='" + d + "'where id=" + i1 + "";
                //cap nhat csdl
                mst.stm.executeUpdate(sql);
                mst.reload();
                mst.model.fireTableDataChanged();
                this.dispose();
                JOptionPane.showMessageDialog(rootPane, "Continue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}