import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.regex.Pattern;

public class MedsFrame extends JFrame implements ActionListener {

    Connection con;

    JButton confirm, cancel;

    JTextField prsp;

    Patient pat;

    Diagnostic dians;

    public MedsFrame(Connection con, int id, String dname) {

        //Determination of interacions

        pat = new Patient();
        dians = new Diagnostic();

        pat.id = id;
        dians.name = dname;

        DB_connection.fetchSpP(con, pat);

        DB_connection.fetchSpD(con, dians);

        Pattern pattern = Pattern.compile(", ");

        String[] patmeds = pattern.split(pat.meds);

        String[] patsup = pattern.split(pat.supp);

        String[] dmeds = pattern.split(dians.pt);

        String msg = "Interactiuni posibile cu medicamentele pacientului: ";

        Meds med = new Meds();

        for(String s : patmeds) {

            DB_connection.fetchMbyATC(con, s, med);

            for(String i : dmeds) {

                if((med.g0 != null) && (med.g0.contains(i)))
                    msg += "\n" + s + " - INTERACTIUNE DE GRAD 0 CU " + i;
                if((med.g1 != null) && (med.g1.contains(i)))
                    msg += "\n" + s + " - INTERACTIUNE DE GRAD 1 CU " + i;
                if((med.g2 != null) && (med.g2.contains(i)))
                    msg += "\n" + s + " - INTERACTIUNE DE GRAD 2 CU " + i;
                if((med.g3 != null) && (med.g3.contains(i)))
                    msg += "\n" + s + " - Interactiune De Grad 3 cu " + i;
                if((med.g4 != null) && (med.g4.contains(i)))
                    msg += "\n" + s + " - Interactiune De Grad 4 cu " + i;

            }
        }

        for(String s : patsup) {

            DB_connection.fetchMbyATC(con, s, med);

            for(String i : dmeds) {

                if((med.g0 != null) && (med.g0.contains(i)))
                    msg += "\n" + s + " - INTERACTIUNE DE GRAD 0 CU " + i;
                if((med.g1 != null) && (med.g1.contains(i)))
                    msg += "\n" + s + " - INTERACTIUNE DE GRAD 1 CU " + i;
                if((med.g2 != null) && (med.g2.contains(i)))
                    msg += "\n" + s + " - INTERACTIUNE DE GRAD 2 CU " + i;
                if((med.g3 != null) && (med.g3.contains(i)))
                    msg += "\n" + s + " - Interactiune De Grad 3 cu " + i;
                if((med.g4 != null) && (med.g4.contains(i)))
                    msg += "\n" + s + " - Interactiune De Grad 4 cu " + i;
            }
        }

        //GUI part

        ImageIcon xlogo = new ImageIcon("lxn.png");

        ImageIcon logo = new ImageIcon("lim.png");



        JLabel bg = new JLabel();
        bg.setOpaque(true);
        bg.setBackground(new Color(208, 203, 174));
        bg.setBounds(0, 0, 1600, 900);

        JLabel idget = new JLabel();
        idget.setText("Introduceti Prescriptia");
        idget.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        idget.setBounds(10, 460, 320, 30);


        prsp = new JTextField();
        prsp.setBounds(10, 510, 1400, 30);

        JTextArea wmsg = new JTextArea();
        wmsg.setBounds(10, 10, 1400, 400);
        wmsg.setLineWrap(true);
        wmsg.setFont( new Font("Roboto", Font.BOLD, 20));
        wmsg.setText(msg);

        confirm = new JButton();
        confirm.addActionListener(this);
        confirm.setBounds(600, 560, 120, 60);
        confirm.setText("Confirma");
        confirm.setFont(new Font("Bahnschrift", Font.PLAIN, 18));

        cancel = new JButton();
        cancel.addActionListener(this);
        cancel.setBounds(800, 560, 120, 60);
        cancel.setText("Anuleaza");
        cancel.setFont(new Font("Bahnschrift", Font.PLAIN, 18));



        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, 1600, 900);
        lp.add(bg);
        lp.add(idget, Integer.valueOf(1));
        lp.add(wmsg, Integer.valueOf(2));
        lp.add(prsp, Integer.valueOf(2));
        lp.add(confirm, Integer.valueOf(3));
        lp.add(cancel, Integer.valueOf(3));
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

        if(e.getSource() == confirm) {

            String meds = prsp.getText();

            if(pat.meds == null)
                pat.meds = meds;
            else
                pat.meds = pat.meds + ", " + meds;

            if(pat.dians == null)
                pat.dians = dians.name;
            else
                pat.dians = pat.dians + ", " + dians.name;

            DB_connection.updatePat(con, pat.id, pat.meds, pat.dians);

            JOptionPane.showOptionDialog(
                    null,
                    "Medicamentele si Diagnosticul au fost inregistrate!",
                    null,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    0);

            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
        if(e.getSource() == cancel)
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }


}
