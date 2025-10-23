package com.bidgely.library.model;

import java.util.Objects;

/**
 * Represents a book publisher.
 */
public class Publisher {
    private String publisherId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String country;
    private boolean active;

    public Publisher(String publisherId, String name, String country) {
        this.publisherId = publisherId;
        this.name = name;
        this.country = country;
        this.active = true;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(publisherId, publisher.publisherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherId);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId='" + publisherId + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", active=" + active +
                '}';
    }
}

