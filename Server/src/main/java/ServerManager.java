import NikandrovLab5.commands.GetInfo;
import NikandrovLab5.commands.Help;
import NikandrovLab5.commands.Operations;
import NikandrovLab5.data.*;
import NikandrovLab5.utility.Collection;
import NikandrovLab5.utility.Transformation;
import src.ClientMessage;
import src.ServerMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ServerManager {
    ServerSender serverSender;
    ServerReceiver serverReceiver;

    public ServerManager(ServerReceiver serverReceiver, ServerSender serverSender){
        this.serverReceiver = serverReceiver;
        this.serverSender = serverSender;
    }

    public void run(Collection collection) throws IOException, ClassNotFoundException {
        while (true) {
            String answer = "";
            ByteBuffer byteBuffer = serverReceiver.receive(serverSender);
            ClientMessage clientMessage = (ClientMessage) Transformation.Deserialization(byteBuffer);
            if (clientMessage.command != null) {
                if (clientMessage.command.equals("insert")) {
                    if (clientMessage.arg.contains(",")) {
                        answer = "There can be no commas in the key";
                    } else {
                        City city = (City) clientMessage.obj;
                        city.setId(collection.id++);
                        city.setCreationDate();
                        collection.collection.put(clientMessage.arg, city);
                        answer = "The object was successfully added";
                    }
                } else {
                    String key = null;
                    for (String now : collection.collection.keySet()) {
                        if (collection.collection.get(now).getId() == clientMessage.id) {
                            key = now;
                            break;
                        }
                    }
                    if (key == null) {
                        answer = "There is no item with this id in the collection";
                    } else {
                        switch (clientMessage.arg) {
                            case "name" -> collection.collection.get(key).setName((String) clientMessage.obj);
                            case "coordinates" -> collection.collection.get(key).setCoordinates((Coordinates) clientMessage.obj);
                            case "area" -> collection.collection.get(key).setArea((Double) clientMessage.obj);
                            case "population" -> collection.collection.get(key).setPopulation((Long) clientMessage.obj);
                            case "metersabvovesealevel" -> collection.collection.get(key).setMetersAboveSeaLevel((Integer) clientMessage.obj);
                            case "climate" -> collection.collection.get(key).setClimate((Climate) clientMessage.obj);
                            case "government" -> collection.collection.get(key).setGovernment((Government) clientMessage.obj);
                            case "standardofliving" -> collection.collection.get(key).setStandardOfLiving((StandardOfLiving) clientMessage.obj);
                            case "governor" -> collection.collection.get(key).setGovernor((Human) clientMessage.obj);
                        }
                        answer = "The element has been successfully replaced";
                    }
                }
            } else {
                Operations operations = new Operations();
                int result = operations.run(clientMessage.commands, collection, operations, null);
                if(result == 0){
                    answer = "The command was executed successfully";
                }
                else if(result == 1){
                    answer = "Client Shutdown";
                }
                else if(result == 2){
                    answer = "Looping occurs when executing commands from files";
                }
                else if(result == 3){
                    answer = "While executing commands from a file, an error occurred in a file";
                }
                else if(result == 4){
                    answer = "An unknown command was entered, enter again:";
                }
                else if(result == 5){
                    Help help = new Help();
                    Help.create(Help.vocabulary.size());
                    answer = help.toString();
                }
                else if(result == 6){
                    GetInfo info = new GetInfo();
                    answer = info.getInfo(collection, collection.initTime);
                }
                else{
                    if(collection.collection.size() == 0){
                        answer = "Collection is empty";
                    }
                    else {
                        for (String key : collection.collection.keySet()) {
                            answer += key + '\n' + collection.collection.get(key).toString() + "\n";
                        }
                    }
                }
            }
            ServerMessage serverMessage = new ServerMessage(answer);
            ByteBuffer buffer = Transformation.Serialization(serverMessage);
            serverSender.send(buffer);
        }
    }
}
