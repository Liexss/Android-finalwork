package com.example.finalfunction;

public class weather2 {
    private  String date;
    private  String high;
    private  String fengli;

    private  String low;
    private  String fengxiang;

    private  String type;

    public weather2() {
    }

    public weather2(String date, String fengli, String high, String low, String fengxiang, String type) {
        this.date = date;
        this.high = high;
        this.low = low;
        this.type = type;
        this.fengli=fengli;
        this.fengxiang=fengxiang;
    }
    public  String getDate(){
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public  String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }
    public  String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }
    public  String getFengli(){
        return fengli;
    }
    public void setFengli(String fengli){
        this.fengli=fengli;
    }
    public  String getFengxiang(){
        return fengxiang;
    }
    public void setFengxiang(String fengxiang){
        this.fengxiang=fengxiang;
    }
    public  String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", high='" + high + '\'' +
                ", fengli='"+fengli+'\''+
                ", low='" + low + '\'' +
                ", fengxiang='" + fengxiang + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
