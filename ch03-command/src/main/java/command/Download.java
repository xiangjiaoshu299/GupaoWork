package command;

import model.FtpServer;

public class Download implements ICommand{
    private FtpServer ftpServer = new FtpServer();

    public void execute() {
        ftpServer.download();
    }
}
