import command.BeginConn;
import command.Download;
import command.EndConn;
import command.ICommand;
import model.CommandLine;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine();
        commandLine.addCommand("开始连接", new BeginConn());
        commandLine.addCommand("断开连接", new EndConn());
        commandLine.addCommand("下载", new Download());

        commandLine.input("下载");

        commandLine.reomve("下载");
        commandLine.input("下载");

    }
}
