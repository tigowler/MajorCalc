public class NoScoreSelectedException extends Exception{
    int grade;
    public NoScoreSelectedException(int grade){
        this.grade = grade;
    }

    public void viewDialog(){
        System.out.println(grade+"학년에 성적이 입력되지 않은 강의가 있습니다.");
    }
}
