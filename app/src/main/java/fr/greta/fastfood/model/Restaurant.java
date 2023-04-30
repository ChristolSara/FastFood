package fr.greta.fastfood.model;



import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Restaurant implements Parcelable {
    private String name;
    private String address;
    private Integer delivery_charge;
    private String image;
    private Hours hours;
    private List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(String name, String address, Integer delivery_charge, String image, Hours hours, List<Menu> menus) {
        this.name = name;
        this.address = address;
        this.delivery_charge = delivery_charge;
        this.image = image;
        this.hours = hours;
        this.menus = menus;
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        if (in.readByte() == 0) {
            delivery_charge = null;
        } else {
            delivery_charge = in.readInt();
        }
        image = in.readString();
        menus = in.createTypedArrayList(Menu.CREATOR);
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(Integer delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        if (delivery_charge == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(delivery_charge);
        }
        parcel.writeString(image);
        parcel.writeTypedList(menus);
    }
}
