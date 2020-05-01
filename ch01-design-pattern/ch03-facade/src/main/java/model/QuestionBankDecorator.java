package model;

public class QuestionBankDecorator implements NavicationBar {

    private NavicationBar navicationBar;

    public QuestionBankDecorator(NavicationBar navicationBar) {
        this.navicationBar = navicationBar;
    }

    public void show() {
        navicationBar.show();
        System.out.print("题库 ");
    }
}
