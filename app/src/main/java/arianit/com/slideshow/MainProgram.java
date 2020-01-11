package arianit.com.slideshow;

import java.io.IOException;
import java.sql.Timestamp;

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

        SlideShow slideShow = new SlideShow(PATH_C,8);

        System.out.println("Start time:" + printTime());
        System.out.println("StartScore: " + slideShow.Score);

        slideShow.TabuSearchAlgorithm(2000000);

        System.out.println("BestScore: " + slideShow.Score);
        System.out.println("End time:" + printTime());
        Output(slideShow);

    }

    public static void Output(SlideShow slideShow){
        try {
            IO.writeToFile(PATH_C_OUTPUT, slideShow.PhotoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Output file successfully created");
        System.out.println("Initial fitness: " + slideShow.Score);
    }

    public static Timestamp printTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;

    }


}


