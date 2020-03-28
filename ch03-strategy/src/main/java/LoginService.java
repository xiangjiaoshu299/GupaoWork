import java.util.HashMap;
import java.util.Map;

public class LoginService {


    private Map<String, IPasswordParser> parserMap = new HashMap();

    public LoginService(){
        parserMap.put("md5", new Md5PwdParser());
        parserMap.put("bcrypt", new BCryptParser());
    }

    public void register(String username, String pwdInput, String arithmetic){
        String password = getPassword(pwdInput, arithmetic);
        System.out.println("注册用户：" + username + "， 录入到数据库的密码：" + password);
    }

    public String getPassword(String password, String arithmetic){
        return parserMap.get(arithmetic).encoding(password);
    }

}
