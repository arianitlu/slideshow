package arianit.com.slideshow;

import java.util.ArrayList;
import java.util.List;

import static arianit.com.slideshow.Settings.TABU_LIST_SIZE;

public class TabuClass {

    public static List<String> TabuList = new ArrayList<String>();

    public static void insertElementInTabuList(Photo photo){
        if(TabuList.size() >= TABU_LIST_SIZE){
            TabuList.remove(0);
        }
        TabuList.add(photo.id);
    }

    public static boolean isElementInTabuList(Photo photo){
        if (TabuList.contains(photo.id))
            return true;
        return false;
    }

}
