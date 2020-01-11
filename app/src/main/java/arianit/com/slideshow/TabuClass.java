package arianit.com.slideshow;

import java.util.ArrayList;
import java.util.List;

public class TabuClass {
    public static List<String> TabuList = new ArrayList<String>();
    public static int Time = 0;

    public static void resetTabuList(){
        TabuList = new ArrayList<>();
    }

    public static void insertElementInTabuList(Photo photo){
        TabuList.add(photo.id);
        Time = Time + 1;
        if(Time > 10)
            resetTabuList();
    }

    public static boolean isElementInTabuList(Photo photo){
        if (TabuList.contains(photo.id))
            return true;
        return false;
    }

}
