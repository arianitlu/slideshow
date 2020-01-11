package arianit.com.slideshow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

public class IO {

    public static void writeToFile(String path, List<Photo> solution) throws IOException {
        File fileOut = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        bufferedWriter.write(String.valueOf(solution.size()));
        bufferedWriter.newLine();

        for (int i = 0; i < solution.size(); i++) {
            bufferedWriter.write(String.valueOf(solution.get(i).id));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    public static Scanner createScaner(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        return sc;
    }
}
