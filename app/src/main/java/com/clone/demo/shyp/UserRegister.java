package com.clone.demo.shyp;

public class UserRegister {

    private String name;
    private String email;
    private String password;
    private String password_confirmation;
    private String mobile_number;
    private String zip_code;
    private String referral_code;

    private int statusCode;
    private String errorMessage;

    private UserRegisterResponseData responseData;

    /**
     * @param
     * */

    public UserRegister(String name, String email, String password
            , String password_confirmation, String mobile_number, String zip_code ,String referral_code) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.mobile_number = mobile_number;
        this.zip_code = zip_code;
        this.referral_code = referral_code;

    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }



    /**
     * @param
     * */

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    /**
     * @param
     * */

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    /**
     * @param
     * */

    public int getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }

    /**
     * @param
     * */

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * @param
     * */

    public UserRegisterResponseData getResponseData(){
        return this.responseData;
    }

    public void setResponseData(UserRegisterResponseData responseData) {
        this.responseData = responseData;
    }

}

class UserRegisterResponseData {

    private int id;
    private String name;
    private String email;

    public UserRegisterResponseData() {}

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