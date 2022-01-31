package movietheaters;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MovieTheatreService {

    Map<String, List<Movie>> cinemas = new TreeMap<>();


    public void readFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp1;
                temp1 = line.split(";");

                String[] temp2;
                temp2=temp1[0].split("-");
                temp1[0]=temp2[1];


                if (cinemas.containsKey(temp2[0])) {
                    List<Movie> output = cinemas.get(temp2[0]);
                    output.add(new Movie(temp1[0], LocalTime.parse(temp1[1])));
                    cinemas.put(temp2[0], output);

                } else {
                    List<Movie> output = new ArrayList<>();
                    output.add(new Movie(temp1[0], LocalTime.parse(temp1[1])));
                    cinemas.put(temp2[0], output);
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }

    }

    public List<String> findMovie(String title) {

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Movie>> entry : cinemas.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
            if (entry.getValue().contains(title)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public Map<String, List<Movie>> getShows() {
        return cinemas;
    }

    public LocalTime findLatestShow(String title) {
        LocalTime latest = LocalTime.of(0, 0, 0);
        for (Map.Entry<String, List<Movie>> entry : cinemas.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
            if (entry.getValue().contains(title)) {
                for (Movie m : entry.getValue()) {
                    if (m.getTitle().equals(title)) {
                        if (m.getStartTime().isAfter(latest)) {
                            latest = m.getStartTime();
                        }
                    }
                }
            }else {
                throw new IllegalArgumentException("The movie is not played");
            }
        }return latest;
    }
}
