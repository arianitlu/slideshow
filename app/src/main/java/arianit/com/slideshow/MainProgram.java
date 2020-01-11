package arianit.com.slideshow;

import java.io.IOException;

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

        SlideShow slideShow = new SlideShow(PATH_D,6);

        System.out.println("StartScore: " + slideShow.Score);

        slideShow.TabuSearchAlgorithm(20000);

        System.out.println("BestScore: " + slideShow.Score);
        //asdasd
        Output(slideShow);

    }

    public static void Output(SlideShow slideShow){
        try {
            IO.writeToFile(PATH_D_OUTPUT, slideShow.PhotoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Output file successfully created");
        System.out.println("Initial fitness: " + slideShow.Score);
    }


}


