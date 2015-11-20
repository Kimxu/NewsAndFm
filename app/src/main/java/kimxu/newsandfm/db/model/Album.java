package kimxu.newsandfm.db.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Album extends DataSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String name;

    private float price;

    private List<Song> songs = new ArrayList<Song>();

    // generated getters and setters.
    //...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}