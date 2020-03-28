package command;

import model.FtpServer;

public class EndConn implements ICommand {
    private FtpServer ftpServer;

    public void execute() {
        ftpServer.download();
    }
}
