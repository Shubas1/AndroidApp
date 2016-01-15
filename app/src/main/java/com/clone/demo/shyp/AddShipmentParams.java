package com.clone.demo.shyp;

import java.util.ArrayList;

/**
 * Created by admin on 13/06/15.
 */
public class AddShipmentParams {

    private int id;
    private int user_id;
    private String batch_code;
    private String recipient_name;
    private String country;
    private String address;
    private String more_address;
    private String city;
    private String state;
    private String zip;
    private int statusCode;
    private String errorMessage;

    private AddShipmentResponseData responseData;


    public AddShipmentParams(int user_id, String recipient_name
            , String country, String address, String more_address, String city, String state, String zip) {


        this.user_id = user_id;
        this.recipient_name = recipient_name;
        this.country = country;
        this.address = address;
        this.more_address = more_address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBatch_code() {
        return batch_code;
    }

    public void setBatch_code(String batch_code) {
        this.batch_code = batch_code;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMore_address() {
        return more_address;
    }

    public void setMore_address(String more_address) {
        this.more_address = more_address;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AddShipmentResponseData getShipmentsResponseData() {
        return responseData;
    }

    public void setShipmentsResponseData(AddShipmentResponseData shipmentsResponseData) {
        this.responseData = shipmentsResponseData;
    }


}// end main class shipments

class AddShipmentResponseData {

    private int id;
    private int user_id;
    private String batch_code;
    private String recipient_name;
    private String country;
    private String address;
    private String more_address;
    private String city;
    private String state;
    private String zip;

    public AddShipmentResponseData() { };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBatch_code() {
        return batch_code;
    }

    public void setBatch_code(String batch_code) {
        this.batch_code = batch_code;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMore_address() {
        return more_address;
    }

    public void setMore_address(String more_address) {
        this.more_address = more_address;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}//end ResponseData