import com.google.common.eventbus.EventBus;

public class Test {

    public static void main(String[] args) {

        TeacherService teacherService = new TeacherService();

        EventBus eventBus = new EventBus();
        eventBus.register(teacherService);
        Question question = new Question("今天天气好吗?", "今天阴天，天气好吗？");
        eventBus.post(question);
    }
}
