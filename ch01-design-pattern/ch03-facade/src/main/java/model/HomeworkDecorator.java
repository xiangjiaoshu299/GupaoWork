package model;

public class HomeworkDecorator implements NavicationBar {
    private NavicationBar navicationBar;

    public HomeworkDecorator(NavicationBar navicationBar) {
        this.navicationBar = navicationBar;
    }
    public void show() {
        navicationBar.show();
        System.out.print("作业 ");
    }
}
