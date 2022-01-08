import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MainPanel{
    SelectPanel selectPanel;
    GradePanel gradePanel;
    ResultPanel resultPanel;

    public MainPanel(){

        selectPanel = new SelectPanel();
        gradePanel = new GradePanel();
        resultPanel = new ResultPanel();


    }

    public void addRowToGradeTable(){
        int[] rows = selectPanel.mainTable.selectedRows;
        for (int row : rows){
            System.out.print(row+" ");
        }
        System.out.println();
    }

}
