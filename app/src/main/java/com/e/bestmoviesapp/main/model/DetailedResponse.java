package com.e.bestmoviesapp.main.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailedResponse implements Parcelable {

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    private BelongsToCollection belongsToCollection;
    @SerializedName("budget")
    @Expose
    private Long budget;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("runtime")
    @Expose
    private Long runtime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Long voteCount;
    @SerializedName("credits")
    @Expose
    private Credits credits;
    public final static Parcelable.Creator<DetailedResponse> CREATOR = new Creator<DetailedResponse>() {

        public DetailedResponse createFromParcel(Parcel in) {
            return new DetailedResponse(in);
        }

        public DetailedResponse[] newArray(int size) {
            return (new DetailedResponse[size]);
        }

    }
            ;

    protected DetailedResponse(Parcel in) {
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        this.belongsToCollection = ((BelongsToCollection) in.readValue((BelongsToCollection.class.getClassLoader())));
        this.budget = ((Long) in.readValue((Long.class.getClassLoader())));
        in.readList(this.genres, (com.e.bestmoviesapp.main.model.Genre.class.getClassLoader()));
        this.homepage = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
        this.runtime = ((Long) in.readValue((Long.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.tagline = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.voteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
        this.voteCount = ((Long) in.readValue((Long.class.getClassLoader())));
        this.credits = ((Credits) in.readValue((Credits.class.getClassLoader())));

    }

    /**
     * No args constructor for use in serialization
     *
     */
    public DetailedResponse() {
    }


    public DetailedResponse(Boolean adult, String backdropPath, BelongsToCollection belongsToCollection, Long budget, List<Genre> genres, String homepage, Long id, String imdbId, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, String releaseDate, Long revenue, Long runtime, String status, String tagline, String title, Boolean video, Double voteAverage, Long voteCount, Credits credits) {
        super();
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.credits = credits;

    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public DetailedResponse withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public DetailedResponse withBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
        return this;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public DetailedResponse withBudget(Long budget) {
        this.budget = budget;
        return this;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public DetailedResponse withGenres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public DetailedResponse withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetailedResponse withId(Long id) {
        this.id = id;
        return this;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public DetailedResponse withOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public DetailedResponse withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public DetailedResponse withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public DetailedResponse withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }


    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public DetailedResponse withRuntime(Long runtime) {
        this.runtime = runtime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DetailedResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public DetailedResponse withTagline(String tagline) {
        this.tagline = tagline;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DetailedResponse withTitle(String title) {
        this.title = title;
        return this;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public DetailedResponse withVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public DetailedResponse withVoteCount(Long voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public DetailedResponse withCredits(Credits credits) {
        this.credits = credits;
        return this;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(backdropPath);
        dest.writeValue(belongsToCollection);
        dest.writeValue(budget);
        dest.writeList(genres);
        dest.writeValue(homepage);
        dest.writeValue(id);
        dest.writeValue(originalTitle);
        dest.writeValue(overview);
        dest.writeValue(posterPath);
        dest.writeValue(releaseDate);
        dest.writeValue(runtime);
        dest.writeValue(status);
        dest.writeValue(tagline);
        dest.writeValue(title);
        dest.writeValue(voteAverage);
        dest.writeValue(voteCount);
        dest.writeValue(credits);
    }

    public int describeContents() {
        return 0;
    }

}
