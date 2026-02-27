import java.util.*;

public class movieData_base {
    private List<movie> allMovies;

    public movieData_base() {
        this.allMovies = new ArrayList<>();
    }

    public void addMovie(movie film) {
        if (!allMovies.contains(film)) {
            allMovies.add(film);
            IO.println("movie added successfully to data base");
        } else {
            IO.println("movie already added to data base");
        }
    }

    public void removeMovie(movie film) {
        if (allMovies.remove(film)) {
            IO.println(film.getMovie_name() + " removed from the data base successfully");
        } else {
            IO.println("movie not found in database");
        }
    }

    public List<movie> searchBy_name(String keyWord) {
        keyWord = keyWord.toLowerCase().trim();
        List<movie> film = new ArrayList<>();
        boolean found = false;
        for (movie f : allMovies) {
            if (f.getMovie_name().toLowerCase().trim().equals(keyWord)) {
                film.add(f);
                found = true;
            }
        }
        if (found) {
            IO.println("movie found successfully");
            return film;
        } else {
            IO.println("couldn't find the movie");
            return film;
        }
    }

    public List<movie> searchBy_type(String type) {
        type = type.toLowerCase().trim();
        List<movie> film = new ArrayList<>();
        boolean found = false;
        for (movie f : allMovies) {
            if (f.getType().toLowerCase().trim().equals(type)) {
                film.add(f);
                found = true;
            }
        }
        if (found) {
            IO.println("movie found successfully");
            return film;
        } else {
            IO.println("couldn't find the movie");
            return film;
        }
    }

    public List<movie> searchBy_rating(double rating) {
        List<movie> film = new ArrayList<>();
        boolean found = false;
        for (movie f : allMovies) {
            if (f.getRating() >= rating) {
                film.add(f);
                found = true;
            }
        }
        if (found) {
            IO.println("movies found successfully");
            return film;
        } else {
            IO.println("couldn't find any movies");
            return film;
        }
    }

    public int movesCount() {
        return allMovies.size();
    }

    public void displayMovies() {
        if (allMovies.isEmpty()) {
            IO.println("no movies in the data base");
        } else {
            for (int i = 0; i < allMovies.size(); i++) {
                movie m = allMovies.get(i);
                IO.println((i + 1) + ". " + m.getMovie_name() +
                        " (" + m.getType() + ") - Director: " +
                        m.getDirector() + " - Rating: " + m.getRating() + "/10");
            }
        }
    }
}