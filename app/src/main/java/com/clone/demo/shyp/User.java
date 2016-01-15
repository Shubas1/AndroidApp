package com.clone.demo.shyp;

public class User {

    private String email;
    private String password;
    private int statusCode;
    private String errorMessage;
    private String mobileNumber;

    private ResponseData responseData;

    /**
     * @param constructor
     * */

     public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * @param email
     * */

     public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    /**
     * @param password
     * */

     public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    /**
     * @param statusCode
     * */

     public int getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }

    /**
     * @param errorMessage
     * */

     public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * @param responseData
     * */

    public ResponseData getResponseData(){
        return this.responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

}

class ResponseData {

    private int id;
    private String name;
    private String email;

    public ResponseData() {}

    public int getId(){
        return id;
    }

    // @SETTER INT ID
    public void setId(int id){
        this.id = id;
    }

    // @GETTER INT ID
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }


}