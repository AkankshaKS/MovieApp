package com.e.bestmoviesapp.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;


public class MovieDetails implements Parcelable {


    @SerializedName("genres")
    private List<Genre> mGenres = null;

    @SerializedName("results")
    private Movie mMovieResults = null;

    @SerializedName("cast")
    private List<Cast> mCast;

    @SerializedName("runtime")
    private int mRuntime;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("vote_count")
    private int mVoteCount;

    @SerializedName("credits")
    private Credits mCredits;

    private MovieDetails(Parcel in) {
        mRuntime = in.readInt();
        mStatus = in.readString();
        mVoteCount = in.readInt();
        mCredits = (Credits) in.readValue(Credits.class.getClassLoader());
    }

    @SuppressWarnings("unused")
    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRuntime);
        dest.writeString(mStatus);
        dest.writeInt(mVoteCount);
        dest.writeValue(mCredits);
    }

    public MovieDetails(List<Genre> mGenres, Movie mMovieResults, List<Cast> mCast, List<Review> mReviewResults, int mRuntime, long mRevenue, String mStatus, int mVoteCount, Credits mCredits) {
        this.mGenres = mGenres;
        this.mMovieResults = mMovieResults;
        this.mCast = mCast;
        this.mRuntime = mRuntime;
        this.mStatus = mStatus;
        this.mVoteCount = mVoteCount;
        this.mCredits = mCredits;
    }

    public List<Genre> getmGenres() {
        return mGenres;
    }

    public void setmGenres(List<Genre> mGenres) {
        this.mGenres = mGenres;
    }

    public Movie getmMovieResults() {
        return mMovieResults;
    }

    public void setmMovieResults(Movie mMovieResults) {
        this.mMovieResults = mMovieResults;
    }

    public List<Cast> getmCast() {
        return mCast;
    }

    public void setmCast(List<Cast> mCast) {
        this.mCast = mCast;
    }

    public int getmRuntime() {
        return mRuntime;
    }

    public void setmRuntime(int mRuntime) {
        this.mRuntime = mRuntime;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public int getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(int mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public Credits getmCredits() {
        return mCredits;
    }

    public void setmCredits(Credits mCredits) {
        this.mCredits = mCredits;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                ", mGenres=" + mGenres +
                ", mMovieResults=" + mMovieResults +
                ", mCast=" + mCast +
                ", mRuntime=" + mRuntime +
                ", mStatus='" + mStatus + '\'' +
                ", mVoteCount=" + mVoteCount +
                ", mCredits=" + mCredits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDetails that = (MovieDetails) o;
        return
                mRuntime == that.mRuntime &&
                mVoteCount == that.mVoteCount &&
                Objects.equals(mGenres, that.mGenres) &&
                Objects.equals(mMovieResults, that.mMovieResults) &&
                Objects.equals(mCast, that.mCast) &&
                Objects.equals(mStatus, that.mStatus) &&
                Objects.equals(mCredits, that.mCredits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mGenres, mMovieResults, mCast, mRuntime, mStatus, mVoteCount, mCredits);
    }
}


