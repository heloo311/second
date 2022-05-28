import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        new HocSinh("Quản Lý Sinh Vien");
        Login frame = new Login();
        frame.setTitle("Login form");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10,10,370,600);
        frame.setLocationRelativeTo(null);
    }
}
