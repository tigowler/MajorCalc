import javax.swing.table.AbstractTableModel;

public class GradeTableModel extends AbstractTableModel {
    Object[][] data = {};
    String[] columnName = {"성적", "과목명(국문)", "인정교과구분", "이수단계", "학점/이론/실습", "p/f"};

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public String getColumnName(int col){
        return columnName[col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col){
        if (col==0){
            return true;
        }
        return false;
    }
}
