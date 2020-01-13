package arianit.com.slideshow;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static arianit.com.slideshow.Settings.PATH_B;
import static arianit.com.slideshow.Settings.PATH_B_OUTPUT;
import static arianit.com.slideshow.Settings.PATH_C;
import static arianit.com.slideshow.Settings.PATH_C_OUTPUT;
import static arianit.com.slideshow.Settings.PATH_D;
import static arianit.com.slideshow.Settings.PATH_D_OUTPUT;
import static arianit.com.slideshow.Settings.PATH_E;
import static arianit.com.slideshow.Settings.PATH_E_OUTPUT;

public class MainProgram {

    public static void main(String[] args) throws IOException {


        System.out.println("StartTime: " + time());
        SlideShow slideShow1 = new SlideShow(PATH_D);
        System.out.println("----------------------------");
        System.out.println("InitialSolutionTime: " + time());
        System.out.println("StartScore: " + slideShow1.Score);
        slideShow1.TabuSearchAlgorithm(15000000);
        System.out.println("----------------------------");
        System.out.println("EndTime: " + time());
        System.out.println("BestScore: " + slideShow1.Score);
        System.out.println("----------------------------");

        Output(slideShow1);

    }

    public static void Output(SlideShow slideShow){
        try {
            IO.writeToFile(PATH_D_OUTPUT, slideShow.PhotoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Output file successfully created");
        System.out.println("Best fitness: " + slideShow.Score);
    }

    public static String time(){
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        Date date = new Date();
        String time=dateFormat.format(date);
        return time;
    }


}


