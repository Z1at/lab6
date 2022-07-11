package NikandrovLab5.commands;

import NikandrovLab5.utility.TextFormatting;
import NikandrovLab5.utility.Collection;

public class RemoveKey {
    public void removeKey(String key, Collection collection) {
        int len = collection.collection.size();
        collection.collection.remove(key);
        if (len != collection.collection.size()) {
            System.out.println(TextFormatting.getGreenText("Element was delete"));
        } else {
            System.out.println(TextFormatting.getRedText("There is no such key"));
        }
        System.out.println();
    }
}
