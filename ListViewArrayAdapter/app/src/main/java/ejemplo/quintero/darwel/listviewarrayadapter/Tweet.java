package ejemplo.quintero.darwel.listviewarrayadapter;

/**
 * Created by Darwel on 13/12/2016.
 */
public class Tweet {
    private int image;
    private String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tweet(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
