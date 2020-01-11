package arianit.com.slideshow;

import java.util.ArrayList;
import java.util.List;

class Photo {
    public String id;
    public char type;
    public boolean gone;
    public int numberOfTags;
    public List<String> tags;

    public Photo() {
    }

    public Photo(String id, char type, int numberOfTags, List<String> tags) {
        this.id = id;
        this.type = type;
        this.numberOfTags = numberOfTags;
        this.tags = tags;
        this.gone = false;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", numberOfTags=" + numberOfTags +
                ", tags=" + tags +
                ", used=" + gone +
                '}';
    }

    public Photo Copy(){
        return this;
    }

    public void Set(Photo photo){
        this.id = photo.id;
        this.type = photo.type;
        this.gone = photo.gone;
        this.numberOfTags = photo.numberOfTags;
        this.tags = photo.tags;
    }
}
