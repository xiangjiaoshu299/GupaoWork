public class Test {

    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        loginService.register("刻度尺", "keduchi", "md5");
        loginService.register("刻度尺", "keduchi", "bcrypt");

    }
}
