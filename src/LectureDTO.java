public class LectureDTO {
    private String name; //과목 이름
    private String mandatory; //전공 필수-선택
    private String grade; //이수 학년
    private String time; //학점
    private String pf; //pf이면 true

    public LectureDTO(){

    }

    public LectureDTO(String name, String mandatory, String grade, String time, String pf){
        this.name = name;
        this.mandatory = mandatory;
        this.grade = grade;
        this.time = time;
        this.pf = pf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }
}
