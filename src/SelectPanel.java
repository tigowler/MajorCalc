import javax.swing.*;
import java.awt.*;

public class SelectPanel {
    JPanel selectIdsPanel, centerPanel, mainPanel;
    JComboBox idComboBox, majorComboBox;
    JLabel selectIdsLabel;
    String[] ids = {"18학번", "19학번", "20학번", "21학번"};
    String[] majors = {"심화전공", "복수전공", "부전공"};

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
        majorComboBox = new JComboBox(majors);
        selectIdsLabel = new JLabel("학번과 전공 형태를 선택하세요.");

        selectIdsPanel.add(selectIdsLabel);
        selectIdsPanel.add(idComboBox);
        selectIdsPanel.add(majorComboBox);

        con.gridx = 0;
        con.gridy = 0;
        centerPanel.add(selectIdsPanel, con);
        mainTable = new MainTablePanel();
        con.gridy = 1;
        centerPanel.add(mainTable.mainPanel, con);

        mainPanel.add(centerPanel);
    }

}
