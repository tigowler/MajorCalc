public class DuplicatedLectureException extends Exception{
    public void viewDialog(){
        new DialogCall("중복된 과목입니다.");
    }
}
