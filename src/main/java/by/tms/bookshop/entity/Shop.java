package by.tms.bookshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shopName")
    private String shopName;
    @Column(name = "Location")
    private String location;
    @Column(name = "information")
    private String information;

    public Shop() {
    }

    public Shop(String shopName, String location, String information) {
        this.shopName = shopName;
        this.location = location;
        this.information = information;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", location='" + location + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
