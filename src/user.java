import java.util.*;

public class user {
    private String userName;
    private String password;
    private List<movie> watchList;
    private Map<String, Double> userRatings;
    private Map<String, String> users;

    public user() {
        this("", "", "", 0, false, false, "", "");
    }

    public user(String movie_name, String type, String director, int rating, boolean watched, boolean wantTo_watch, String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.users = new HashMap<>();
        this.watchList = new ArrayList<>();
        this.userRatings = new HashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (!userName.trim().isEmpty()) {
            this.userName = userName;
        } else {
            IO.println("please enter your user name");
        }
    }

    public List<movie> getWatchList() {
        return new ArrayList<>(watchList);
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public Map<String, Double> getUserRatings() {
        return new HashMap<>(userRatings);
    }

    public void addTo_watchList(movie film) {
        if (!watchList.contains(film)) {
            watchList.add(film);
            IO.println("movie " + film.getMovie_name() + " added successfully");
        } else {
            IO.println("movie already in watch list");
        }
    }

    public void removeFrom_watchList(movie film) {
        if (watchList.remove(film)) {
            IO.println("movie " + film.getMovie_name() + " removed successfully");
        } else {
            IO.println("movie not found in watch list");
        }
    }

    public void setUser_rating(movie film, double rating) {
        if (rating < 0 || rating > 10) {
            IO.println("rating must be between 0 and 10 please reset the rating");
            return;
        }
        if (userRatings.containsKey(film.getMovie_name())) {
            IO.println("movie is already rated");
            IO.println("if you want to rate again enter 1");
            IO.println("if you want to keep the rating enter 0");
            IO.println("if you want to remove the rating enter 2");
            String user_choice = IO.readln();

            if (user_choice.equals("1")) {
                userRatings.put(film.getMovie_name(), rating);
                IO.println(film.getMovie_name() + " rating is " + rating + "/10");
            } else if (user_choice.equals("0")) {
                return;
            } else if (user_choice.equals("2")) {
                userRatings.remove(film.getMovie_name());
                System.out.println("Removed rating for '" + film.getMovie_name() + "'!");
            } else {
                IO.println("please enter 0 or 1 or 2");
            }
        } else {
            userRatings.put(film.getMovie_name(), rating);
            IO.println(film.getMovie_name() + " rating is " + rating + "/10");
        }
    }

    public Double getMovieRating(movie film) {
        return userRatings.getOrDefault(film.getMovie_name(), null);
    }

    public Map<String, Double> getAllRatings() {
        return new HashMap<>(userRatings);
    }

    public List<movie> searchIn_movie(String keyWord) {
        List<movie> result = new ArrayList<>();
        keyWord = keyWord.toLowerCase();
        for (movie f : watchList) {
            if (f.getMovie_name().toLowerCase().contains(keyWord)) {
                result.add(f);
            }
        }
        return result;
    }

    public void userInfo() {
        IO.println("User: " + userName);
        IO.println("Movies in watch List: " + watchList.size() + " movies");
    }

    public boolean login(String userName, String password) {
        if (users.containsKey(userName)) {
            if (users.get(userName).equals(password)) {
                IO.println("welcome back " + userName);
                return true;
            } else {
                IO.println("password is incorrect try again");
                return false;
            }
        } else {
            IO.println("account does not exist");
            return false;
        }
    }

    public boolean register(String userName, String password) {
        if (users.containsKey(userName)) {
            IO.println("user already have an account");
            return false;
        } else {
            if (!userName.isEmpty() && password.length() >= 4 && userName.length() >= 4) {
                users.put(userName, password);
                this.userName = userName;
                this.password = password;
                IO.println("account registered successfully");
                return true;
            } else {
                IO.println("please enter a password and a user name greater than 3 characters");
                return false;
            }
        }
    }

    public List<String> displayUsers() {
        return new ArrayList<>(users.keySet());
    }

    public int numUsers() {
        return users.size();
    }
}