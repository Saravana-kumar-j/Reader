package adapter;

public class ListItem {
    private int imageResource;
    private String text;

    public ListItem(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }
}
