package com.clone.demo.shyp;

import java.util.ArrayList;
import java.util.List;

public class WeatherResponse  {

    private int cod;
    private String base;
    private Coord coord;

    private ArrayList<Weather> weather;

    public WeatherResponse(ArrayList<Weather> Weather, String base, int cod) {
        this.weather = Weather;
        this.base = base;
        this.cod = cod;
    }

    public int getCod(){
        return this.cod;
    }

    public void setCod(int cod){
        this.cod = cod;
    }

    public String getBase(){
        return base;
    }

    public void setBase(String base){
        this.base = base;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Coord getCoord() {
        return coord;
    }
    public void setCoord(Coord coord) {
        this.coord = coord;
    }



}

class Coord {

    private double lon;
    private double lat;

    public Coord(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon(){
        return this.lon;
    }

    public void setLon(int lon){
        this.lon = lon;
    }

    public double getLat(){
        return this.lat;
    }

    public void setLat(int lat){
        this.lat = lat;
    }


}
