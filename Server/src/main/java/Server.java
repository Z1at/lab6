import NikandrovLab5.utility.Collection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramChannel serverChannel = DatagramChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 777));
        Collection collection = new Collection();
        ServerReceiver serverReceiver = new ServerReceiver(serverChannel);
        ServerSender serverSender = new ServerSender(serverChannel);
        ServerManager serverManager = new ServerManager(serverReceiver, serverSender);
        serverManager.run(collection);
    }
}
