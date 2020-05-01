public interface IPasswordParser {

    //密码加密
    String encoding(String originPwd);

    //比较密码是否相等
    boolean isPwdEqual(String savePwd, String input);
}
