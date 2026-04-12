package com.bartmilan.e_comm_api.dto;

import com.bartmilan.e_comm_api.model.DeliveryType;

public class PlaceOrderRequest {

    private Long userId;
    private DeliveryType deliveryType;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String parcelLockerId;

    public PlaceOrderRequest(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getParcelLockerId() {
        return parcelLockerId;
    }

    public void setParcelLockerId(String parcelLockerId) {
        this.parcelLockerId = parcelLockerId;
    }
}
