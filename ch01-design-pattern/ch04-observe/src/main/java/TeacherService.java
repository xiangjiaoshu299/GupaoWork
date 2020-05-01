import com.google.common.eventbus.Subscribe;

public class TeacherService {

    @Subscribe
    public void update(Question question){

        System.out.println("收到提问：" + question);
    }
}
