package ru.returnonintelligence.testask.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Антон on 28.03.2015.
 */
@Entity
@Table(name = "addresses")
@NamedQuery( name = "Address.getAll",
            query = "SELECT a FROM Address a")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", nullable = false)
    private Long id;

    @NotNull(message = "Address should have a zip")
    @Size(min = 5, max = 5, message = "Address's zip shoud have 5 characters")
    @Pattern(regexp = "^[0-9]+$", message = "Use only 0-9 characters")
    @Column(name = "zip", nullable = false)
    private String zip;

    @NotNull(message = "Address should have a country")
    @Size(min = 3, max = 40, message = "Address's country from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Use only a-zA-Z characters")
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull(message = "Address should have a city")
    @Size(min = 3, max = 40, message = "Address's city from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "Use only a-zA-Z space and \"-\" characters")
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull(message = "Address should have a district")
    @Size(min = 3, max = 40, message = "Address's district from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Use only a-zA-Z space  characters")
    @Column(name = "district", nullable = false)
    private String district;

    @NotNull(message = "Address should have a street")
    @Size(min = 3, max = 40, message = "Address's street from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z\\s\\d]+$", message = "Use only a-zA-Z0-9 space characters")
    @Column(name = "street", nullable = false)
    private String street;

    public Address() {
    }

    public Address(String zip, String country, String city, String district, String street) {
        this.zip = zip;
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
