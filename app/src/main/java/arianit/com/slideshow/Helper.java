package arianit.com.slideshow;

import android.annotation.SuppressLint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Helper {

    public static List<Photo> createPhotos(Scanner scanner) {

        int numberOfPhotos;

        String nphotos = scanner.nextLine();
        numberOfPhotos = Integer.valueOf(nphotos);

        List<Photo> photos = new ArrayList<Photo>();

        for (int i = 0; i < numberOfPhotos; i++) {
            String photo = scanner.nextLine();
            String[] split = photo.split(" ");
            char typeOfPhoto = split[0].charAt(0); // Get type of photos V or H

            int numberOfTags = Integer.valueOf(split[1]); // Get number of tags

            List<String> tags = new ArrayList<>();

            for (int j = 0; j < numberOfTags; j++) {
                tags.add(split[j + 2]);
            }

            photos.add(new Photo(String.valueOf(i), typeOfPhoto, numberOfTags, tags));

        }

        return photos;
    }

    @SuppressLint("NewApi")
    public static int calculateScoring(Photo photo1, Photo photo2) {
        if(photo1 == null || photo2 == null){
            return 0;
        }
        List<String> tagsOfPhoto1 = photo1.tags;
        List<String> tagsOfPhoto2 = photo2.tags;
        List<String> commonTags = new ArrayList<String>();

        for (int i = 0; i < tagsOfPhoto1.size(); i++) {
            if (tagsOfPhoto2.contains(tagsOfPhoto1.get(i))) {
                commonTags.add(tagsOfPhoto1.get(i));
            }
        }
        return Integer.min(Integer.min(tagsOfPhoto1.size() - commonTags.size(), tagsOfPhoto2.size() - commonTags.size()),
                commonTags.size());
    }

    public static Timestamp printTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }



    public static Photo combineTwoPhotos(Photo one, Photo two) {

        List<String> newTags = new ArrayList<>();
        newTags.addAll(two.tags);

        for (String tag: one.tags) {
            if (!two.tags.contains(tag)) {
                newTags.add(tag);
            }
        }

        return new Photo(one.id + " " + two.id, 'H', newTags.size(), newTags);
    }

    public static List<Photo> combineVerticalPhotos(ArrayList<Photo> vertical) {

        //bashkimi i fotove vertikale

        if(vertical.size() == 0) {
            return new ArrayList<>();
        }


        List<Photo> listOfPhotos = new ArrayList<>();
        int ActualScore = 0;
        int Tmp = 99;
        int BestPosition = 0;
        for(int i = 0; i<vertical.size()-1;i++){
            if(vertical.get(i).gone)
                continue;
            for(int j = vertical.size()-1; j>i;j--){
                if(vertical.get(j).gone)
                    continue;

                ActualScore = calculateScoring(vertical.get(i),vertical.get(j));
                if(ActualScore == 0){
                    vertical.get(i).gone = true;
                    vertical.get(j).gone = true;
                    listOfPhotos.add(combineTwoPhotos( vertical.get(i), vertical.get(j)));
                    break;
                }else{
                    if(ActualScore <= Tmp){
                        Tmp = ActualScore;
                        BestPosition = j;
                    }
                }
                if(j == i-1){
                    vertical.get(i).gone = true;
                    vertical.get(j).gone = true;
                    listOfPhotos.add(combineTwoPhotos( vertical.get(i), vertical.get(BestPosition)));
                }
            }
        }

       /* Photo current = vertical.get(0);

        for(int i=1; i<vertical.size(); i=i+2) {
            listOfPhotos.add(combineTwoPhotos(current, vertical.get(i)));

            if(i+1 <= vertical.size()-2) {
                current = vertical.get(i+1);
            } else {
                break;
            }
        }*/

        return listOfPhotos;
    }

}

class CustomComperator implements Comparator<Photo>{
        @Override
        public int compare(Photo photo, Photo t1) {
            return photo.numberOfTags - t1.numberOfTags;
        }
}