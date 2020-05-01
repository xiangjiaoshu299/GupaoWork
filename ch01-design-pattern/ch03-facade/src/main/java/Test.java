import model.*;

public class Test {

    //使用装饰器模式实现一个可根据权限动态扩展功能的导航条。
    public static void main(String[] args) {

        NavicationBar navigationBar = new GeneralNavigationBar();

        System.out.print("普通用户菜单： ");
        navigationBar.show();
        System.out.println();

        navigationBar = new HomeworkDecorator(navigationBar);
        navigationBar = new QuestionBankDecorator(navigationBar);
        navigationBar = new GrowWallDecorator(navigationBar);
        System.out.print("登陆用户菜单： ");
        navigationBar.show();
        System.out.println();
    }
}
