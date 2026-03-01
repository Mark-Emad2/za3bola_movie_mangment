public class movie {
    protected String movie_name;
    protected String type;
    protected String director;
    protected double rating;
    protected boolean watched;
    protected boolean wantTo_watch;

    public movie() {
    }

    public movie(String movie_name, String type, String director, int rating, boolean watched, boolean wantTo_watch) {
        this.movie_name = movie_name;
        this.type = type;
        this.director = director;
        this.rating = rating;
        this.watched = watched;
        this.wantTo_watch = wantTo_watch;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getType() {
        return type;
    }

    public String getDirector() {
        return director;
    }

    public double getRating() {
        return rating;
    }

    public boolean isWatched() {
        return watched;
    }

    public boolean isWantTo_watch() {
        return wantTo_watch;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public void setWantTo_watch(boolean wantTo_watch) {
        this.wantTo_watch = wantTo_watch;
    }
}
