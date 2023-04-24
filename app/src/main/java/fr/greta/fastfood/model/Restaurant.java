package fr.greta.fastfood.model;



import java.util.List;

public class Restaurant {
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
}
