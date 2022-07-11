package NikandrovLab5.commands;

import NikandrovLab5.data.Government;
import NikandrovLab5.utility.Collection;
import NikandrovLab5.utility.TextFormatting;

public class CountGreaterThanGovernment {
    public void countGreaterThanGovernment(Government government, Collection collection) {
        int counter = 0;
        for (String key : collection.collection.keySet()) {
            if (collection.collection.get(key).getGovernment().compareTo(government) > 0) {
                counter++;
            }
        }
        System.out.println(TextFormatting.getGreenText(String.valueOf(counter)));
        System.out.println();
    }
}
