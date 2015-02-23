package org.hospital.core.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

/**
 * Created by thiago on 2/22/15.
 */
public class Hospital {

    @Id
    private ObjectId id;

    private String name;
    private String address;

    @GeoSpatialIndexed
    private Point location;

    private String city;
    private String state;
    private String country;

    public Hospital() {}

    public Hospital(ObjectId id, String name, String address, Point location, String city, String state, String country) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.location = location;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
