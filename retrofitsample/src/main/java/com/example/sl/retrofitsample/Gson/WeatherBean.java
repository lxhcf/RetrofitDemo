package com.example.sl.retrofitsample.Gson;

public class WeatherBean {

    /**
     * status : 0
     * city : 慈溪市
     * aqi :
     * pm25 : 64
     * temp : 36~24℃
     * weather : 晴
     * wind : 无持续风向微风
     * weatherimg :
     * tomorrow : {"temp":"36~24℃","weather":"晴","wind":"无持续风向微风","weatherimg":""}
     */

    private String status;
    private String city;
    private String aqi;
    private String pm25;
    private String temp;
    private String weather;
    private String wind;
    private String weatherimg;
    private TomorrowBean tomorrow;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeatherimg() {
        return weatherimg;
    }

    public void setWeatherimg(String weatherimg) {
        this.weatherimg = weatherimg;
    }

    public TomorrowBean getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(TomorrowBean tomorrow) {
        this.tomorrow = tomorrow;
    }

    public static class TomorrowBean {
        /**
         * temp : 36~24℃
         * weather : 晴
         * wind : 无持续风向微风
         * weatherimg :
         */

        private String temp;
        private String weather;
        private String wind;
        private String weatherimg;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWeatherimg() {
            return weatherimg;
        }

        public void setWeatherimg(String weatherimg) {
            this.weatherimg = weatherimg;
        }
    }
}
