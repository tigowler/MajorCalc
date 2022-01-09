import java.util.HashMap;

public class CalcRef {
    private HashMap<String, Double> score = new HashMap<>();

    public CalcRef(){
        score.put("A+", 4.3);
        score.put("A0", 4.0);
        score.put("A-", 3.7);
        score.put("B+", 3.3);
        score.put("B0", 3.0);
        score.put("B-", 2.7);
        score.put("C+", 2.3);
        score.put("C0", 2.0);
        score.put("C-", 1.7);
        score.put("D+", 1.3);
        score.put("D0", 1.0);
        score.put("D-", 0.7);
        score.put("F", 0.0);
        score.put("P", 0.0);
    }

    public double getScore(String s){
        return score.get(s);
    }

    public int getRequiredCourse(String grade, String major){
        switch (major){
            case "심화전공":
            case "복수전공":
                return 15;
            case "부전공":
                switch (Integer.parseInt(grade)){
                    case 18:
                    case 19:
                        return 30;
                    case 20:
                    case 21:
                        return 21;
                }
                break;
            default:
                return 0;
        }
        return 0;
    }

    public int getSumCourses(String grade, String major){
        switch (major){
            case "심화전공":
                switch (Integer.parseInt(grade)){
                    case 18:
                    case 19:
                    case 20:
                        return 60;
                    case 21:
                        return 72;
                }
                break;
            case "복수전공":
                switch (Integer.parseInt(grade)){
                    case 18:
                    case 19:
                    case 20:
                        return 42;
                    case 21:
                        return 54;
                }
                break;
            case "부전공":
                switch (Integer.parseInt(grade)){
                    case 18:
                    case 19:
                        return 30;
                    case 20:
                    case 21:
                        return 21;
                }
                break;
            default:
                return 0;
        }
        return 0;
    }
}
