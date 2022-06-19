import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class PFrame extends JFrame implements ActionListener {

    //submit button
    JButton submit;
    //text fields to get from
    JTextField atf;
    JTextField wtf;
    JTextField htf;

    Patient pat = new Patient();
    //Female, Male
    JRadioButton male;
    JRadioButton female;

    Connection con;

    public PFrame(Connection con) {

        //images

        ImageIcon xlogo = new ImageIcon("lxn.png");

        ImageIcon logo = new ImageIcon("lim.png");

        ImageIcon coffee = new ImageIcon("coffee.png");

        //Welcome Label

        JLabel wText = new JLabel();
        wText.setText("Welcome to CrossReact!");
        wText.setBounds(50, 50, 500, 30);
        wText.setVerticalTextPosition(JLabel.CENTER);
        wText.setHorizontalTextPosition(JLabel.CENTER);
        wText.setFont(new Font("Bahnschrift", Font.PLAIN, 30));

        //app logo

        JLabel il = new JLabel();
        il.setIcon(xlogo);
        il.setBounds(0, 660, 300, 150);

        //company logo

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
        subb.setText("Aboneaza-te");
        subb.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        subb.setFocusable(false);
        subb.setBackground(Color.LIGHT_GRAY);
        subb.setForeground(new Color(252, 231, 253 ));

        JLabel bg = new JLabel();
        bg.setOpaque(true);
        bg.setBackground(new Color(231, 245, 251));
        bg.setBounds(0, 0, 1600, 900);

        JLabel aget = new JLabel();
        aget.setText("Introduceti varsta");
        aget.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        aget.setBounds(400, 250, 150, 20);

        JLabel wt = new JLabel();
        wt.setText("Introduceti greutatea");
        wt.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        wt.setBounds(400, 310, 180, 20);

        JLabel ht = new JLabel();
        ht.setText("Introduceti inaltimea");
        ht.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        ht.setBounds(400, 370, 180, 20);

        JLabel gt = new JLabel();
        gt.setText("Alegeti genul");
        gt.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        gt.setBounds(400, 430, 180, 20);

        JPanel sub = new JPanel();
        sub.setBackground(new Color(252, 231, 253 ));
        sub.setBounds(1200, 580, 400, 300);
        sub.setLayout(new FlowLayout(FlowLayout.LEADING));
        sub.add(cfl);
        sub.add(subb);


        atf = new JTextField();
        atf.setBounds(400, 280, 150, 30);
//        atf.setVisible(true);

        wtf = new JTextField();
        wtf.setBounds(400, 340, 150, 30);

        htf = new JTextField();
        htf.setBounds(400, 400, 150, 30);

        submit = new JButton();
        submit.addActionListener(this);
        submit.setBounds(600, 320, 100, 40);
        submit.setText("Submit");
        submit.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        submit.setFocusable(false);
        submit.setBackground(Color.LIGHT_GRAY);

        male = new JRadioButton("Masculin");
        male.setBounds(400, 450, 75, 30);

        female = new JRadioButton("Feminin");
        female.setBounds(500, 450, 75, 30);

        ButtonGroup rgroup = new ButtonGroup();
        rgroup.add(male);
        rgroup.add(female);

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, 1600, 900);
        lp.add(bg);
        lp.add(sub, Integer.valueOf(1));
        lp.add(cil, Integer.valueOf(1));
        lp.add(wText, Integer.valueOf(1));
        lp.add(il, Integer.valueOf(1));
        lp.add(aget, Integer.valueOf(1));
        lp.add(atf, Integer.valueOf(2));
        lp.add(wt, Integer.valueOf(1));
        lp.add(wtf, Integer.valueOf(2));
        lp.add(ht, Integer.valueOf(1));
        lp.add(htf, Integer.valueOf(2));
        lp.add(submit, Integer.valueOf(3));
        lp.add(male, Integer.valueOf(2));
        lp.add(female, Integer.valueOf(2));
        lp.add(gt, Integer.valueOf(2));
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

            pat.age = Integer.parseInt(atf.getText());
            pat.weight = Double.parseDouble(wtf.getText());
            pat.height = Double.parseDouble(htf.getText());

            DB_connection.insertPat(con, pat);

            JOptionPane.showOptionDialog(
                    null,
                    "Te-ai inregistrat cu succes!",
                    null,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    0);
        }
        if(e.getSource() == male)
            pat.gen = 1;
        if(e.getSource() == female)
            pat.gen = 0;

    }
}
