package com.e.bestmoviesapp.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        public Movie createFromParcel(Parcel in){
            return new Movie(in);
        }


        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @SerializedName("id")
    private int mId;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("vote_average")
    private double mVoteAverage;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    public Movie(int movieId, String originalTitle, String title, String posterPath, String overview,
                 double voteAverage, String releaseDate, String backdropPath) {
        mId = movieId;
        mOriginalTitle = originalTitle;
        mTitle = title;
        mPosterPath = posterPath;
        mOverview = overview;
        mVoteAverage = voteAverage;
        mReleaseDate =releaseDate;
        mBackdropPath = backdropPath;
    }


    public int getId() {
        return mId;
    }


    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getPosterPath() {
        return mPosterPath;
    }


    public String getOverview() {
        return mOverview;
    }


    public double getVoteAverage() {
        return mVoteAverage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }


    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public void setmVoteAverage(double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    private Movie(Parcel in) {
        mId = in.readInt();
        mOriginalTitle = in.readString();
        mTitle = in.readString();
        mPosterPath = in.readString();
        mOverview = in.readString();
        mVoteAverage = in.readDouble();
        mReleaseDate = in.readString();
        mBackdropPath = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mOriginalTitle);
        dest.writeString(mTitle);
        dest.writeString(mPosterPath);
        dest.writeString(mOverview);
        dest.writeDouble(mVoteAverage);
        dest.writeString(mReleaseDate);
        dest.writeString(mBackdropPath);
    }
}
