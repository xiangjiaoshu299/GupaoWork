import lombok.Data;

@Data
public class Question {
    private String title;
    private String content;

    public Question(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
