package model;

public class GrowWallDecorator implements NavicationBar {
    private NavicationBar navicationBar;

    public GrowWallDecorator(NavicationBar navicationBar) {
        this.navicationBar = navicationBar;
    }

    public void show() {
        navicationBar.show();
        System.out.print("成长墙");
    }
}
