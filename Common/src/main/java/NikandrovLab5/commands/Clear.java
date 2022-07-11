package NikandrovLab5.commands;

import NikandrovLab5.utility.Collection;
import NikandrovLab5.utility.TextFormatting;

public class Clear {
    public void clear(Collection collection) {
        collection.collection.clear();
        System.out.println(TextFormatting.getGreenText("The collection has been cleared"));
        System.out.println();
    }
}
