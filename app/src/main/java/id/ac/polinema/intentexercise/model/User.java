package id.ac.polinema.intentexercise.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String fullname, nim, password, conpass, homepage, about;

    public User(String fullname, String nim, String password, String conpass, String homepage, String about) {
        this.fullname = fullname;
        this.nim = nim;
        this.password = password;
        this.conpass = conpass;
        this.homepage = homepage;
        this.about = about;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpass() {
        return conpass;
    }

    public void setConpass(String conpass) {
        this.conpass = conpass;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullname);
        dest.writeString(this.nim);
        dest.writeString(this.password);
        dest.writeString(this.conpass);
        dest.writeString(this.homepage);
        dest.writeString(this.about);
    }

    public void readFromParcel(Parcel source) {
        this.fullname = source.readString();
        this.nim = source.readString();
        this.password = source.readString();
        this.conpass = source.readString();
        this.homepage = source.readString();
        this.about = source.readString();
    }

    protected User(Parcel in) {
        this.fullname = in.readString();
        this.nim = in.readString();
        this.password = in.readString();
        this.conpass = in.readString();
        this.homepage = in.readString();
        this.about = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
