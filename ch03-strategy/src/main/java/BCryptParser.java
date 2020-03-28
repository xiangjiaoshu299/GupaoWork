import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptParser implements IPasswordParser {

    public String encoding(String originPwd) {
        String hashpw = BCrypt.hashpw(originPwd, BCrypt.gensalt());
        return hashpw;
    }

    public boolean isPwdEqual(String savePwd, String input) {
        BCrypt.checkpw(input, savePwd);
        return false;
    }
}
