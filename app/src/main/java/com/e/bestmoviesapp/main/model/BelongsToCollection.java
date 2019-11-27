package com.e.bestmoviesapp.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class BelongsToCollection implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    public final static Parcelable.Creator<BelongsToCollection> CREATOR = new Creator<BelongsToCollection>() {


        public BelongsToCollection createFromParcel(Parcel in) {
            return new BelongsToCollection(in);
        }

        public BelongsToCollection[] newArray(int size) {
            return (new BelongsToCollection[size]);
        }

    };

    protected BelongsToCollection(Parcel in) {
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public BelongsToCollection() {
    }

    /**
     *
     * @param name
     * @param id
     * @param backdropPath
     * @param posterPath
     */
    public BelongsToCollection(Long id, String name, String posterPath, String backdropPath) {
        super();
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BelongsToCollection withId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BelongsToCollection withName(String name) {
        this.name = name;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public BelongsToCollection withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    @Override
    public String toString() {
        return "BelongsToCollection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelongsToCollection that = (BelongsToCollection) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(posterPath, that.posterPath) &&
                Objects.equals(backdropPath, that.backdropPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, posterPath, backdropPath);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(posterPath);
        dest.writeValue(backdropPath);
    }

    public int describeContents() {
        return 0;
    }

}
