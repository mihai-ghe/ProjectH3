import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class DFrame extends JFrame implements ActionListener {

    Connection con;
    JTextField idtf, dtf;
    JTextArea mtf;
    JButton submit;

    String medts;
    int id;
    String dname;

    public DFrame(Connection con) {

        ImageIcon xlogo = new ImageIcon("lxn.png");

        ImageIcon logo = new ImageIcon("lim.png");

        ImageIcon coffee = new ImageIcon("coffee.png");

        JLabel il = new JLabel();
        il.setIcon(xlogo);
        il.setBounds(0, 660, 300, 150);

        JLabel cil = new JLabel();
        cil.setIcon(logo);
        cil.setBounds(1340, 0, 300, 150);

        JLabel cfl = new JLabel();
        cfl.setIcon(coffee);
        cfl.setHorizontalTextPosition(JLabel.CENTER);
        cfl.setVerticalTextPosition(JLabel.BOTTOM);
        cfl.setText("Fa-ne cinste cu o cafea!");
        cfl.setFont(new Font("Bahnschrift", Font.PLAIN, 14));

        JButton subb = new JButton();
        subb.setPreferredSize(new Dimension(110, 60));
        subb.setText("Doneaza");
        subb.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        subb.setFocusable(false);
        subb.setBackground(Color.LIGHT_GRAY);
        subb.setForeground(new Color(252, 231, 253 ));

        JLabel bg = new JLabel();
        bg.setOpaque(true);
        bg.setBackground(new Color(231, 245, 251));
        bg.setBounds(0, 0, 1600, 900);

        JLabel idget = new JLabel();
        idget.setText("Introduceti ID-ul pacientului");
        idget.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        idget.setBounds(50, 100, 320, 30);

        JLabel dget = new JLabel();
        dget.setText("Introduceti diagnosticul");
        dget.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        dget.setBounds(50, 200, 250, 30);

        JPanel sub = new JPanel();
        sub.setBackground(new Color(252, 231, 253 ));
        sub.setBounds(1200, 580, 400, 300);
        sub.setLayout(new FlowLayout(FlowLayout.LEADING));
        sub.add(cfl);
        sub.add(subb);

        idtf = new JTextField();
        idtf.setBounds(50, 150, 180, 30);
//        atf.setVisible(true);

        dtf = new JTextField();
        dtf.setBounds(50, 250, 180, 30);

        submit = new JButton();
        submit.addActionListener(this);
        submit.setBounds(400, 170, 120, 60);
        submit.setText("Submit");
        submit.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        submit.setFocusable(false);
        submit.setBackground(Color.LIGHT_GRAY);



        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, 1600, 900);
        lp.add(bg);
        lp.add(sub, Integer.valueOf(1));
        lp.add(cil, Integer.valueOf(1));
        lp.add(il, Integer.valueOf(1));
        lp.add(idget, Integer.valueOf(1));
        lp.add(idtf, Integer.valueOf(2));
        lp.add(dget, Integer.valueOf(1));
        lp.add(dtf, Integer.valueOf(2));
        lp.add(submit, Integer.valueOf(3));
        lp.setBackground(new Color(146, 220, 239));
        lp.setVisible(true);

        this.add(lp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(1600, 900);
        this.setLayout(null);
        this.setVisible(true);

        this.con = con;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {

            id = Integer.parseInt(idtf.getText());
            dname = dtf.getText();

            MedsFrame frame = new MedsFrame(con, id, dname);
        }
    }
}
