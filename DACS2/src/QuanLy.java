import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLy extends JFrame implements ActionListener {
    JButton Hs , gv;

    public QuanLy(String  s){
        super(s);
        Container container = this.getContentPane();
        JPanel jPanel = new JPanel();
        Hs = new JButton("Hoc sinh");
        Hs.addActionListener(this);
        gv = new JButton("Giang vien");
        gv.addActionListener(this);
        jPanel.add(Hs);
        jPanel.add(gv);
        this.add(jPanel,"Center");
        this.setSize(500,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Hoc sinh")){
            new  HocSinh("Hoc Sinh");
        }
        if(e.getActionCommand().equals("Giang vien")){
            new GiangVien("Giang Vien");
        }
    }
}