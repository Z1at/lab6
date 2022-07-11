package NikandrovLab5.commands;

import NikandrovLab5.data.Human;
import NikandrovLab5.utility.Collection;
import NikandrovLab5.utility.TextFormatting;

import java.util.Objects;

public class RemoveAnyByGovernor {
    public void removeAnyByGovernor(Human governor, Collection collection) {
        String temporary = "";
        for (String key : collection.collection.keySet()) {
            if (collection.collection.get(key).getGovernor().getName().equals(governor.getName()) &
                    Objects.equals(collection.collection.get(key).getGovernor().getHeight(), governor.getHeight()) &
                    Objects.equals(collection.collection.get(key).getGovernor().getBirthday(), governor.getBirthday())) {
                temporary = key;
                break;
            }
        }
        if (temporary.equals("")) {
            System.out.println(TextFormatting.getRedText("There is no such governor in the collection"));
        } else {
            collection.collection.remove(temporary);
            System.out.println(TextFormatting.getGreenText("Element was delete"));
        }
        System.out.println();
    }
}
