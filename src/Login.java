import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Login implements ItemListener {
    JFrame jf;
    JLabel login, ns, name, id;
    JTextField nameField, idField;
    JRadioButton[] btns = new JRadioButton[3];
    String[] btnNames = {"심화전공", "복수전공", "부전공"};
    ButtonGroup bg;
    JButton ok;
    ImageIcon nsImage;
    JPanel loginPanel, buttonPanel, nameFieldPanel, idFieldPanel, textFieldPanel, radioButtonPanel;
    GridBagConstraints con;

    String major;

    public Login(String msg){
        jf = new JFrame(msg);
        loginPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel();
        nameFieldPanel = new JPanel(new FlowLayout());
        idFieldPanel = new JPanel(new FlowLayout());
        textFieldPanel = new JPanel(new GridBagLayout());
        con = new GridBagConstraints();
        radioButtonPanel = new JPanel(new FlowLayout());
        bg = new ButtonGroup();

        login = new JLabel("Login");
        login.setHorizontalAlignment(JLabel.CENTER);
        nsImage = new ImageIcon("C:\\Users\\user\\Desktop\\2021_WINTER\\Day6\\character.gif");
        nsImage = changeImageSize(nsImage);
        ns = new JLabel(nsImage);
        name = new JLabel("이름");
        id = new JLabel("학번");
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (major == null){
                    //exception dialog
                    System.out.println("choose major");
                    return;
                }
                if (idField.getText().length()!=7){
                    //exception dialog
                    System.out.println("id length");
                    return;
                }
                if (Integer.parseInt(idField.getText().substring(0, 2)) < 18 || Integer.parseInt(idField.getText().substring(0, 2)) > 21){
                    //exception dialog
                    System.out.println("id nums");
                    return;
                }
                //setVisible
            }
        });

        nameField = new JTextField(15);
        idField = new JTextField(15);

        nameFieldPanel.add(name);
        nameFieldPanel.add(nameField);
        idFieldPanel.add(id);
        idFieldPanel.add(idField);
        con.gridx = 0;
        con.gridy = 0;
        textFieldPanel.add(nameFieldPanel, con);
        con.gridy = 1;
        textFieldPanel.add(idFieldPanel, con);

        con.gridx = 0;
        con.gridy = 0;
        loginPanel.add(login, con);
        con.gridy = 1;
        loginPanel.add(ns, con);
        con.gridy = 2;
        loginPanel.add(textFieldPanel, con);

        buttonPanel.add(ok);

        con.gridy = 3;
        for (int i=0; i<btns.length; i++){
            btns[i] = new JRadioButton(btnNames[i]);
            btns[i].addItemListener(this);
            bg.add(btns[i]);
            radioButtonPanel.add(btns[i]);
        }

        loginPanel.add(radioButtonPanel, con);

        jf.add(loginPanel, "Center");
        jf.add(buttonPanel, "South");

        jf.setSize(600, 400);
        jf.setVisible(true);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == btns[0]){
            major = "심화전공";
        }
        if (e.getSource() == btns[1]){
            major = "복수전공";
        }
        if (e.getSource() == btns[2]){
            major = "부전공";
        }
    }

    private ImageIcon changeImageSize(ImageIcon img) {
        Image sImg = img.getImage();
        Image eImg = sImg.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
        return new ImageIcon(eImg);
    }
}
