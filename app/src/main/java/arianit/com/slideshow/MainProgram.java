package arianit.com.slideshow;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static arianit.com.slideshow.Paths.PATH_B;
import static arianit.com.slideshow.Paths.PATH_B_OUTPUT;
import static arianit.com.slideshow.Paths.PATH_C;
import static arianit.com.slideshow.Paths.PATH_C_OUTPUT;
import static arianit.com.slideshow.Paths.PATH_D;
import static arianit.com.slideshow.Paths.PATH_D_OUTPUT;
import static arianit.com.slideshow.Paths.PATH_E;
import static arianit.com.slideshow.Paths.PATH_E_OUTPUT;

public class MainProgram {

    public static void main(String[] args) throws IOException {


        System.out.println("----------------------------");
        System.out.println("StartTime: " + time());

        SlideShow slideShow = new SlideShow(PATH_C);
        System.out.println("----------------------------");
        System.out.println("InitialSolutionTime: " + time());
        System.out.println("StartScore: " + slideShow.Score);
        slideShow.TabuSearchAlgorithm(300000);
        System.out.println("----------------------------");
        System.out.println("EndTime: " + time());
        System.out.println("BestScore: " + slideShow.Score);
        System.out.println("----------------------------");

        //Output(slideShow);

    }

    public static void Output(SlideShow slideShow){
        try {
            IO.writeToFile(PATH_E_OUTPUT, slideShow.PhotoList);
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


