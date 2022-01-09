public class NoLectureSelectedException extends Exception{
    public void viewDialog(){
        new DialogCall("선택된 항목이 없습니다.");
    }
}
