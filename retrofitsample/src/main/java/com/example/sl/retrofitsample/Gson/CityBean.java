package com.example.sl.retrofitsample.Gson;

import java.util.List;

public class CityBean {
    /**
     * rows : [{"adcode":"341100","people_count_2010":3937868,"lat":32.303627,"lng":118.316264,"name":"滁州市","level":"city","parent":"安徽省"},{"adcode":"341700","people_count_2010":1402518,"lat":30.656037,"lng":117.489157,"name":"池州市","level":"city","parent":"安徽省"},{"adcode":"341300","people_count_2010":6974366,"lat":33.633891,"lng":116.984084,"name":"宿州市","level":"city","parent":"安徽省"},{"adcode":"340500","people_count_2010":1366302,"lat":31.689362,"lng":118.507906,"name":"马鞍山市","level":"city","parent":"安徽省"},{"adcode":"341600","people_count_2010":4900000,"lat":33.869338,"lng":115.782939,"name":"亳州市","level":"city","parent":"安徽省"},{"adcode":"341200","people_count_2010":7630903,"lat":32.896969,"lng":115.819729,"name":"阜阳市","level":"city","parent":"安徽省"},{"adcode":"340200","people_count_2010":2263123,"lat":31.326319,"lng":118.376451,"name":"芜湖市","level":"city","parent":"安徽省"},{"adcode":"340700","people_count_2010":651657,"lat":30.929935,"lng":117.816576,"name":"铜陵市","level":"city","parent":"安徽省"},{"adcode":"340600","people_count_2010":2113321,"lat":33.971707,"lng":116.794664,"name":"淮北市","level":"city","parent":"安徽省"},{"adcode":"340300","people_count_2010":3136401,"lat":32.939667,"lng":117.363228,"name":"蚌埠市","level":"city","parent":"安徽省"},{"adcode":"340400","people_count_2010":2333896,"lat":32.647574,"lng":117.018329,"name":"淮南市","level":"city","parent":"安徽省"},{"adcode":"340800","people_count_2010":5311379,"lat":30.50883,"lng":117.043551,"name":"安庆市","level":"city","parent":"安徽省"},{"adcode":"341800","people_count_2010":2532938,"lat":30.945667,"lng":118.757995,"name":"宣城市","level":"city","parent":"安徽省"},{"adcode":"340100","people_count_2010":5680145,"lat":31.86119,"lng":117.283042,"name":"合肥市","level":"city","parent":"安徽省"},{"adcode":"341500","people_count_2010":5623217,"lat":31.752889,"lng":116.507676,"name":"六安市","level":"city","parent":"安徽省"},{"adcode":"341000","people_count_2010":1358980,"lat":29.709239,"lng":118.317325,"name":"黄山市","level":"city","parent":"安徽省"}]
     * total : 16
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * adcode : 341100
         * people_count_2010 : 3937868
         * lat : 32.303627
         * lng : 118.316264
         * name : 滁州市
         * level : city
         * parent : 安徽省
         */

        private String adcode;
        private int people_count_2010;
        private double lat;
        private double lng;
        private String name;
        private String level;
        private String parent;

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public int getPeople_count_2010() {
            return people_count_2010;
        }

        public void setPeople_count_2010(int people_count_2010) {
            this.people_count_2010 = people_count_2010;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }
    }
}
