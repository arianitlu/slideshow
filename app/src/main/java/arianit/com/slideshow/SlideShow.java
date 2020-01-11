package arianit.com.slideshow;

import android.content.SyncStatusObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SlideShow {
    public List<Photo> PhotoList = new ArrayList<Photo>();
    public String Path = "";
    public int Score = 0;

    public SlideShow(){

    }
    public SlideShow(String _path, int maxScore) throws IOException{
        this.Path = _path;
        this.InitiateSolutionAdvanced(maxScore);
        //this.InitiateSolution();
    }
//
//    private void InitiateSolution() throws IOException {
//        Scanner scanner = IO.createScaner(this.Path);
//        List<Photo> photos = Helper.createPhotos(scanner);
//
//        // Seperate photos whether are horizontal or vertical
//        ArrayList<Photo> listOfHorizontalPhotos = new ArrayList<>();
//        ArrayList<Photo> listOfVerticalPhotos = new ArrayList<>();
//
//        for (Photo photo : photos) {
//            if(photo.type == 'H')
//                listOfHorizontalPhotos.add(photo);
//        }
//
//        for (Photo photo : photos) {
//            if(photo.type == 'V')
//                listOfVerticalPhotos.add(photo);
//        }
//
//        // Combine all horizontal with vertical
//        listOfHorizontalPhotos.addAll(Helper.combineVerticalPhotos(listOfVerticalPhotos));
//        Collections.shuffle(listOfHorizontalPhotos);
//
//
//        Photo current = photos.get(0);
//        current.gone = true;
//        this.PhotoList.add(current);
//
//        while (this.PhotoList.size() < listOfHorizontalPhotos.size()) {
//            for (Photo photo : listOfHorizontalPhotos) {
//                if (!photo.gone) {
//                    this.PhotoList.add(photo);
//                    photo.gone = true;
//                    break;
//                }
//            }
//        }
//        this.Score = this.CalculateScore(this.PhotoList);
//    }

    private void InitiateSolutionAdvanced(int _maxScore) throws IOException {
        Scanner scanner = IO.createScaner(this.Path);
        List<Photo> photos = Helper.createPhotos(scanner);

        // Seperate photos whether are horizontal or vertical
        ArrayList<Photo> listOfHorizontalPhotos = new ArrayList<>();
        ArrayList<Photo> listOfVerticalPhotos = new ArrayList<>();

        for (Photo photo : photos) {
            if(photo.type == 'H')
                listOfHorizontalPhotos.add(photo);
        }
        Collections.sort(listOfHorizontalPhotos,new CustomComperator());
        for (Photo photo : photos) {
            if(photo.type == 'V')
                listOfVerticalPhotos.add(photo);
        }
        Collections.sort(listOfVerticalPhotos,new CustomComperator());

        // Combine all horizontal with vertical
        listOfHorizontalPhotos.addAll(Helper.combineVerticalPhotos(listOfVerticalPhotos));
        Collections.shuffle(listOfHorizontalPhotos);
        Photo current = photos.get(0);
        current.gone = true;
        this.PhotoList.add(current);

        int maxScore = _maxScore;

        while (this.PhotoList.size() < listOfHorizontalPhotos.size()) {
            boolean found = false;
            for (Photo photo : listOfHorizontalPhotos) {
                if (!photo.gone) {
                    int score = Helper.calculateScoring(current, photo);
                    if (score >= maxScore) {
                        this.PhotoList.add(photo);
                        current = photo;
                        found = true;
                        photo.gone = true;
                        break;
                    }
                }
            }
            if (!found) {
                maxScore--;
            }
        }
        this.PhotoList.remove(0);
        this.Score = this.CalculateScore(this.PhotoList);
    }

    public int SwapOperator(SlideShow ActualSolution,int x, int y){
        SlideShow newSolution = new SlideShow();
        newSolution.Copy(ActualSolution);
        return this.CalculateScoreOfSwaps(x,y,newSolution.PhotoList);
    }

    public SlideShow MoveOperator(SlideShow ActualSolution,int x, int y){
        SlideShow newSolution = new SlideShow();
        newSolution.Copy(ActualSolution);

        Photo photo = newSolution.PhotoList.get(x);
        newSolution.PhotoList.remove(x);
        newSolution.PhotoList.add(y,photo);

        newSolution.Score = this.CalculateScore(newSolution.PhotoList);

        return newSolution;
    }

    public SlideShow RemoveOperator(SlideShow ActualSolution,int x){
        SlideShow newSolution = new SlideShow();
        newSolution.Copy(ActualSolution);
        newSolution.PhotoList.remove(x);
        newSolution.Score = this.CalculateScore(newSolution.PhotoList);

        return newSolution;
    }

    public void Copy(SlideShow ActualSolution){
        this.PhotoList = ActualSolution.PhotoList;
        this.Score = ActualSolution.Score;
        this.Path = ActualSolution.Path;
    }

    public int CalculateScore(List<Photo> Solution){
        int sc = 0;
        for(int i = 0;i<Solution.size()-1;i++){
            int score = Helper.calculateScoring(Solution.get(i),Solution.get(i+1));
            sc += score;
        }
        return sc;
    }

    public int CalculateScoreOfSwaps(int x, int y, List<Photo> photoList){
        int sc = 0;
        Photo photox_0;
        if(x==0){
            photox_0 = null;

        }else{
            photox_0 = photoList.get(x-1);
        }
        Photo photox_1 = photoList.get(x);
        Photo photox_2;
        if(x>photoList.size()-1){
            photox_2 = null;

        }else{
            photox_2 = photoList.get(x+1);
        }


        Photo photoy_0;
        if(y==0){
            photoy_0 = null;

        }else{
            photoy_0 = photoList.get(y-1);
        }

        Photo photoy_1 = photoList.get(y);

        Photo photoy_2;
        if(y>photoList.size()-1){
            photoy_2 = null;

        }else{
            photoy_2 = photoList.get(y+1);
        }

        sc = Helper.calculateScoring(photox_0,photoy_1) + Helper.calculateScoring(photoy_1,photox_2)
                - Helper.calculateScoring(photox_0,photox_1) - Helper.calculateScoring(photox_1,photox_2)
                + Helper.calculateScoring(photoy_0,photox_1) + Helper.calculateScoring(photox_1,photoy_2)
                - Helper.calculateScoring(photoy_0,photoy_1) - Helper.calculateScoring(photoy_1,photoy_2);

        return sc;
    }

    public int CalculateScoreOfMove(int x, int y, List<Photo> photoList,int paraPas){
        int sc = 0;
        int sc2 = 0;

        Photo photox_0 = photoList.get(x-1);
        Photo photox_1 = photoList.get(x);
        Photo photox_2 = photoList.get(x+1);

        Photo photoy_0 = photoList.get(y-1);
        Photo photoy_1 = photoList.get(y);
        Photo photoy_2 = photoList.get(y+1);

        if (paraPas == 0){
            sc = Helper.calculateScoring(photox_0,photox_1) + Helper.calculateScoring(photox_1,photox_2)
                    + Helper.calculateScoring(photoy_1,photoy_0);
            return sc;
        }else{
            sc2 = Helper.calculateScoring(photox_2,photox_0) + Helper.calculateScoring(photoy_0,photoy_1) +
                    Helper.calculateScoring(photoy_1,photoy_2);
            return sc2;
        }

    }

    public void TabuSearchAlgorithm(int iteration){

        for(int i=0;i<iteration;i++){
            Random random = new Random();

            int x = random.nextInt(this.PhotoList.size()-1);
            int y = random.nextInt(this.PhotoList.size()-1);

//            if (i % 2 == 0){
                int tmpSolutionScore = this.SwapOperator(this,x,y);
                if(!TabuClass.isElementInTabuList(this.PhotoList.get(x))){
                    if(tmpSolutionScore >= 0){
                        this.Score = this.Score + tmpSolutionScore;
                        Photo tmpPhotox = new Photo();
                        tmpPhotox.Set(this.PhotoList.get(x));
                        this.PhotoList.get(x).Set(this.PhotoList.get(y));
                        this.PhotoList.get(y).Set(tmpPhotox);
                        int toGetTabu = random.nextInt(10);
                        TabuClass.insertElementInTabuList(this.PhotoList.get(x));
                    }
                }else{
                    int n = random.nextInt(10);
                    if(n == 1){
                        if(tmpSolutionScore >= 0){
                            this.Score = this.Score + tmpSolutionScore;
                            Photo tmpPhotox = new Photo();
                            tmpPhotox.Set(this.PhotoList.get(x));
                            this.PhotoList.get(x).Set(this.PhotoList.get(y));
                            this.PhotoList.get(y).Set(tmpPhotox);
                            int toGetTabu = random.nextInt(10);
                            TabuClass.insertElementInTabuList(this.PhotoList.get(x));
                        }
                    }
                }

//            }

//            else if (i % 100 == 1){
//                SlideShow tmpSolution3 = new SlideShow();
//                tmpSolution3 = this.RemoveOperator(this, x);
//                if (tmpSolution3.Score >= this.Score) {
//                    System.out.println("Score: " + tmpSolution3.Score);
//                    if (!TabuClass.isElementInTabuList(this.PhotoList.get(x))) {
//                        this.SetActualSolution(tmpSolution3);
//                        TabuClass.insertElementInTabuList(this.PhotoList.get(x));
//                    }
//                }
//            }

//            else {
//                SlideShow tmpSolution2 = new SlideShow();
//                tmpSolution2 = this.MoveOperator(this, x, y);
//                if (tmpSolution2.Score >= this.Score) {
//                    System.out.println("Score: " + tmpSolution2.Score);
//                    if (!TabuClass.isElementInTabuList(this.PhotoList.get(x))) {
//                        this.SetActualSolution(tmpSolution2);
//                        TabuClass.insertElementInTabuList(this.PhotoList.get(x));
//                    }
//                }
//            }
        }

    }

    public void SetActualSolution(SlideShow newActualSolution){
        this.PhotoList = newActualSolution.PhotoList;
        this.Score = newActualSolution.Score;
        this.Path = newActualSolution.Path;
    }
}
