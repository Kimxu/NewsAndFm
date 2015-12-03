package kimxu.newsandfm.db.model;

import android.app.Activity;

import org.litepal.crud.DataSupport;

import java.util.List;

import kimxu.utils.L;
import kimxu.utils.Ts;

/**
 * db操作方法
 * Created by xuzhiguo on 15/11/19.
 */
public class Info {
    public void testDb(Activity mActivity) {
        Album album = new Album();
        album.setName("album");
        album.setPrice(10.99f);
        album.save();
        Song song1 = new Song();
        song1.setName("song1");
        song1.setDuration(320);
        song1.setAlbum(album);
        song1.save();
        Song song2 = new Song();
        song2.setName("song2");
        song2.setDuration(356);
        song2.setAlbum(album);
        song2.save();

        List<Song> allSongs = DataSupport.findAll(Song.class);
        for (Song songs: allSongs) {
            L.d(songs.getName() + "");
            Ts.showToast(mActivity, songs.getName() + "");
        }
        List<Album> allAlbums = DataSupport.findAll(Album.class);
        for (Album albums: allAlbums) {
            L.d(albums.getName() + "");
            Ts.showToast(mActivity, albums.getName() + "");
        }
    }
}

