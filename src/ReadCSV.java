import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {
    BufferedReader br;
    ArrayList<LectureDTO> list = new ArrayList<LectureDTO>();
    int[] index = {1, 3, 4, 5, 6};
    public ReadCSV(){
        try{
            br = Files.newBufferedReader(Paths.get("C:\\Users\\user\\Desktop\\2021_WINTER\\ProjectTest\\EXPORT.csv"));
            String line = "";

            while((line = br.readLine()) != null){
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                tmpList = Arrays.asList(array);
                makeLectureDTO(tmpList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (br!=null){
                    br.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void makeLectureDTO(List<String> tmpList) {
        LectureDTO tmpDTO = new LectureDTO();
        tmpDTO.setName(tmpList.get(index[0]));
        tmpDTO.setMandatory(tmpList.get(index[1]));
        tmpDTO.setGrade(tmpList.get(index[2]));
        tmpDTO.setTime(tmpList.get(index[3]));
        if (tmpList.get(index[4]).equals("X")){
            tmpDTO.setPf("✔");
        }
        list.add(tmpDTO);
    }
}
