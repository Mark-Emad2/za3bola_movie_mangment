import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        movieData_base database = new movieData_base();
        user currentUser = null;
        Map<String, user> users = new HashMap<>();

        // Add some sample movies to database
        addSampleMovies(database);

        while (true) {
            IO.println("\n=== Welcome to Za3bola movie management ===");
            IO.println("1. User Options");
            IO.println("2. Database Options");
            IO.println("3. Exit");
            IO.print("Choose an option: ");

            int choice = Integer.parseInt(IO.readln());

            switch (choice) {
                case 1:
                    currentUser = userMenu(users, currentUser);
                    break;
                case 2:
                    databaseMenu(database, currentUser);
                    break;
                case 3:
                    IO.println("Goodbye!");
                    return;
                default:
                    IO.println("Invalid option. Please try again.");
            }
        }
    }

    private static user userMenu(Map<String, user> users, user currentUser) {
        while (true) {
            IO.println("\n=== USER MENU ===");
            IO.println("1. Register");
            IO.println("2. Login");
            IO.println("3. View User Info");
            IO.println("4. Add to Watch List");
            IO.println("5. Remove from Watch List");
            IO.println("6. Rate a Movie");
            IO.println("7. View My Ratings");
            IO.println("8. Search in My Watch List");
            IO.println("9. Display All Users");
            IO.println("10. Back to Main Menu");
            IO.print("Choose an option: ");

            int choice = Integer.parseInt(IO.readln());

            switch (choice) {
                case 1:
                    IO.print("Enter username: ");
                    String regUser = IO.readln();
                    IO.print("Enter password (min 4 characters): ");
                    String regPass = IO.readln();

                    user newUser = new user("", "", "", 0, false, false, regUser, regPass);
                    if (newUser.register(regUser, regPass)) {
                        users.put(regUser, newUser);
                        currentUser = newUser;
                    }
                    break;

                case 2:
                    if (currentUser == null) {
                        IO.print("Enter username: ");
                        String loginUser = IO.readln();
                        IO.print("Enter password: ");
                        String loginPass = IO.readln();

                        user tempUser = new user("", "", "", 0, false, false, loginUser, loginPass);
                        if (tempUser.login(loginUser, loginPass)) {
                            currentUser = users.get(loginUser);
                        }
                    } else {
                        IO.println("Already logged in as: " + currentUser.getUserName());
                    }
                    break;

                case 3:
                    if (currentUser != null) {
                        currentUser.userInfo();
                    } else {
                        IO.println("Please login first.");
                    }
                    break;

                case 4:
                    if (currentUser != null) {
                        IO.print("Enter movie name to add: ");
                        String movieName = IO.readln();
                        movie newMovie = new movie(movieName, "Unknown", "Unknown", 0, false, true);
                        currentUser.addTo_watchList(newMovie);
                    } else {
                        IO.println("Please login first.");
                    }
                    break;

                case 5:
                    if (currentUser != null) {
                        IO.print("Enter movie name to remove: ");
                        String movieName = IO.readln();
                        movie removeMovie = new movie(movieName, "", "", 0, false, false);
                        currentUser.removeFrom_watchList(removeMovie);
                    } else {
                        IO.println("Please login first.");
                    }
                    break;

                case 6:
                    if (currentUser != null) {
                        IO.print("Enter movie name to rate: ");
                        String movieName = IO.readln();
                        IO.print("Enter rating (0-10): ");
                        double rating = Double.parseDouble(IO.readln());
                        movie rateMovie = new movie(movieName, "", "", 0, false, false);
                        currentUser.setUser_rating(rateMovie, rating);
                    } else {
                        IO.println("Please login first.");
                    }
                    break;

                case 7:
                    if (currentUser != null) {
                        Map<String, Double> ratings = currentUser.getAllRatings();
                        if (ratings.isEmpty()) {
                            IO.println("No ratings yet.");
                        } else {
                            IO.println("Your ratings:");
                            for (Map.Entry<String, Double> entry : ratings.entrySet()) {
                                IO.println(entry.getKey() + ": " + entry.getValue() + "/10");
                            }
                        }
                    } else {
                        IO.println("Please login first.");
                    }
                    break;

                case 8:
                    if (currentUser != null) {
                        IO.print("Enter search keyword: ");
                        String keyword = IO.readln();
                        List<movie> results = currentUser.searchIn_movie(keyword);
                        if (results.isEmpty()) {
                            IO.println("No movies found.");
                        } else {
                            IO.println("Found movies:");
                            for (movie m : results) {
                                IO.println("- " + m.getMovie_name());
                            }
                        }
                    } else {
                        IO.println("Please login first.");
                    }
                    break;

                case 9:
                    if (currentUser != null) {
                        List<String> userList = currentUser.displayUsers();
                        IO.println("Registered users: " + userList);
                    } else {
                        IO.println("Please login first.");
                    }
                    break;

                case 10:
                    return currentUser;

                default:
                    IO.println("Invalid option.");
            }
        }
    }

    private static void databaseMenu(movieData_base database, user currentUser) {
        while (true) {
            IO.println("\n=== DATABASE MENU ===");
            IO.println("1. Add Movie to Database");
            IO.println("2. Remove Movie from Database");
            IO.println("3. Search by Name");
            IO.println("4. Search by Type");
            IO.println("5. Search by Rating");
            IO.println("6. Display All Movies");
            IO.println("7. Movie Count");
            IO.println("8. Back to Main Menu");
            IO.print("Choose an option: ");

            int choice = Integer.parseInt(IO.readln());

            switch (choice) {
                case 1:
                    IO.print("Enter movie name: ");
                    String name = IO.readln();
                    IO.print("Enter movie type: ");
                    String type = IO.readln();
                    IO.print("Enter director: ");
                    String director = IO.readln();
                    IO.print("Enter rating (0-10): ");
                    int rating = Integer.parseInt(IO.readln());

                    movie newMovie = new movie(name, type, director, rating, false, false);
                    database.addMovie(newMovie);
                    break;

                case 2:
                    IO.print("Enter movie name to remove: ");
                    String removeName = IO.readln();
                    movie removeMovie = new movie(removeName, "", "", 0, false, false);
                    database.removeMovie(removeMovie);
                    break;

                case 3:
                    IO.print("Enter movie name to search: ");
                    String searchName = IO.readln();
                    List<movie> nameResults = database.searchBy_name(searchName);
                    displayMovieList(nameResults);
                    break;

                case 4:
                    IO.print("Enter movie type to search: ");
                    String searchType = IO.readln();
                    List<movie> typeResults = database.searchBy_type(searchType);
                    displayMovieList(typeResults);
                    break;

                case 5:
                    IO.print("Enter minimum rating (0-10): ");
                    double searchRating = Double.parseDouble(IO.readln());
                    List<movie> ratingResults = database.searchBy_rating(searchRating);
                    displayMovieList(ratingResults);
                    break;

                case 6:
                    database.displayMovies();
                    break;

                case 7:
                    IO.println("Total movies in database: " + database.movesCount());
                    break;

                case 8:
                    return;

                default:
                    IO.println("Invalid option.");
            }
        }
    }

    private static void displayMovieList(List<movie> movies) {
        if (movies.isEmpty()) {
            IO.println("No movies found.");
        } else {
            for (movie m : movies) {
                IO.println("Name: " + m.getMovie_name() +
                        ", Type: " + m.getType() +
                        ", Director: " + m.getDirector() +
                        ", Rating: " + m.getRating() + "/10");
            }
        }
    }

    private static void addSampleMovies(movieData_base database) {
        database.addMovie(new movie("The Shawshank Redemption", "Drama", "Frank Darabont", 9, false, false));
        database.addMovie(new movie("The Godfather", "Crime", "Francis Ford Coppola", 9, false, false));
        database.addMovie(new movie("Pulp Fiction", "Crime", "Quentin Tarantino", 8, false, false));
        database.addMovie(new movie("The Dark Knight", "Action", "Christopher Nolan", 9, false, false));
        database.addMovie(new movie("Forrest Gump", "Drama", "Robert Zemeckis", 8, false, false));
    }
}