package NikandrovLab5.commands;

import NikandrovLab5.utility.Collection;
import NikandrovLab5.utility.TextFormatting;

public class Show {
    public void show(Collection collection) {
        if (collection.collection.size() == 0) {
            System.out.println(TextFormatting.getGreenText("Collection is empty"));
            System.out.println();
        } else {
            System.out.println(TextFormatting.getGreenText("All elements of the collection:"));
            for (String key : collection.collection.keySet()) {
                System.out.println(TextFormatting.getYellowText(key + ":"));
                System.out.println(TextFormatting.getBlueText(collection.collection.get(key).toString()));
            }
        }
    }
}
