import javax.swing.*;

public class DialogCall extends JDialog {
    String text;

    public DialogCall(String text){
        this.text = text;
        JOptionPane.showMessageDialog(null, text, "Message", JOptionPane.ERROR_MESSAGE);
    }
}
