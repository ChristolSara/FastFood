package fr.greta.fastfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Menu implements Parcelable{
    private String name;
    private Float price;
    private String url;
    private int totalInCards;

    public Menu(String name, Float price, String url, int totalInCards) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.totalInCards = totalInCards;
    }

    protected Menu(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readFloat();
        }
        url = in.readString();
        totalInCards = in.readInt();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotalInCards() {
        return totalInCards;
    }

    public void setTotalInCards(int totalInCards) {
        this.totalInCards = totalInCards;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(price);
        }
        parcel.writeString(url);
        parcel.writeInt(totalInCards);
    }
}
