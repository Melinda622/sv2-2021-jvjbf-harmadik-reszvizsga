package streams;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Song {

    private String title;
    private int length;
    private List<String> performers;
    private LocalDate release;

    public Song(String title, int length, List<String> artists, LocalDate release) {
        this.title = title;
        this.length = length;
        this.performers = artists;
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public List<String> getPerformers() {
        return performers;
    }

    public LocalDate getRelease() {
        return release;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return length == song.length && title.equals(song.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length);
    }
}
