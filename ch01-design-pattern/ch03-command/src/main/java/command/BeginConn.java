package command;

import model.FtpServer;

public class BeginConn implements ICommand {
    private FtpServer ftpServer;

    public void execute() {
        ftpServer.beginConn();
    }
}
