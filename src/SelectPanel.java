import javax.swing.*;
import java.awt.*;

public class SelectPanel {
    JPanel selectIdsPanel, centerPanel, mainPanel;
    JComboBox idComboBox;
    JLabel selectIdsLabel;
    String[] ids = {"18학번", "19학번", "20학번", "21학번"};

    public MainTablePanel mainTable;
    GridBagConstraints con = new GridBagConstraints();

    public SelectPanel(){
        //초기화
        mainPanel = new JPanel();
        selectIdsPanel = new JPanel();
        centerPanel = new JPanel(new GridBagLayout());
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;
        con.weighty = 1;

        idComboBox = new JComboBox(ids);
        selectIdsLabel = new JLabel("학번을 선택하세요.");

        selectIdsPanel.add(selectIdsLabel);
        selectIdsPanel.add(idComboBox);

        con.gridx = 0;
        con.gridy = 0;
        centerPanel.add(selectIdsPanel, con);
        mainTable = new MainTablePanel();
        con.gridy = 1;
        centerPanel.add(mainTable.mainPanel, con);

        mainPanel.add(centerPanel);
    }

}
