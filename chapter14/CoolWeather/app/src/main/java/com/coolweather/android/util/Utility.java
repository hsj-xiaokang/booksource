package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    /**
     * http://guolin.tech/api/china
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    //数据库保存
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * http://guolin.tech/api/china/27
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    //数据库保存
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * http://guolin.tech/api/china/27/251
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    //数据库保存
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * http://guolin.tech/api/weather?cityid=CN101290101&key=bc0418b57b2d4918819d3974ac1285d9
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
/**
 * http://guolin.tech/api/china
 * 全国
 * [
 {
 id: 1,
 name: "北京"
 },
 {
 id: 2,
 name: "上海"
 },
 {
 id: 3,
 name: "天津"
 },
 {
 id: 4,
 name: "重庆"
 },
 {
 id: 5,
 name: "香港"
 },
 {
 id: 6,
 name: "澳门"
 },
 {
 id: 7,
 name: "台湾"
 },
 {
 id: 8,
 name: "黑龙江"
 },
 {
 id: 9,
 name: "吉林"
 },
 {
 id: 10,
 name: "辽宁"
 },
 {
 id: 11,
 name: "内蒙古"
 },
 {
 id: 12,
 name: "河北"
 },
 {
 id: 13,
 name: "河南"
 },
 {
 id: 14,
 name: "山西"
 },
 {
 id: 15,
 name: "山东"
 },
 {
 id: 16,
 name: "江苏"
 },
 {
 id: 17,
 name: "浙江"
 },
 {
 id: 18,
 name: "福建"
 },
 {
 id: 19,
 name: "江西"
 },
 {
 id: 20,
 name: "安徽"
 },
 {
 id: 21,
 name: "湖北"
 },
 {
 id: 22,
 name: "湖南"
 },
 {
 id: 23,
 name: "广东"
 },
 {
 id: 24,
 name: "广西"
 },
 {
 id: 25,
 name: "海南"
 },
 {
 id: 26,
 name: "贵州"
 },
 {
 id: 27,
 name: "云南"
 },
 {
 id: 28,
 name: "四川"
 },
 {
 id: 29,
 name: "西藏"
 },
 {
 id: 30,
 name: "陕西"
 },
 {
 id: 31,
 name: "宁夏"
 },
 {
 id: 32,
 name: "甘肃"
 },
 {
 id: 33,
 name: "青海"
 },
 {
 id: 34,
 name: "新疆"
 }
 ]
 */


/**
 * http://guolin.tech/api/china/27
 * 云南
 * [
 {
 id: 251,
 name: "昆明"
 },
 {
 id: 252,
 name: "大理"
 },
 {
 id: 253,
 name: "红河"
 },
 {
 id: 254,
 name: "曲靖"
 },
 {
 id: 255,
 name: "保山"
 },
 {
 id: 256,
 name: "文山"
 },
 {
 id: 257,
 name: "玉溪"
 },
 {
 id: 258,
 name: "楚雄"
 },
 {
 id: 259,
 name: "普洱"
 },
 {
 id: 260,
 name: "昭通"
 },
 {
 id: 261,
 name: "临沧"
 },
 {
 id: 262,
 name: "怒江"
 },
 {
 id: 263,
 name: "香格里拉"
 },
 {
 id: 264,
 name: "丽江"
 },
 {
 id: 265,
 name: "德宏"
 },
 {
 id: 266,
 name: "景洪"
 }
 ]
 */

/**
 * http://guolin.tech/api/china/27/251
 * 昆明
 * [
 {
 id: 1837,
 name: "昆明",
 weather_id: "CN101290101"
 },
 {
 id: 1838,
 name: "东川",
 weather_id: "CN101290103"
 },
 {
 id: 1839,
 name: "寻甸",
 weather_id: "CN101290104"
 },
 {
 id: 1840,
 name: "晋宁",
 weather_id: "CN101290105"
 },
 {
 id: 1841,
 name: "宜良",
 weather_id: "CN101290106"
 },
 {
 id: 1842,
 name: "石林",
 weather_id: "CN101290107"
 },
 {
 id: 1843,
 name: "呈贡",
 weather_id: "CN101290108"
 },
 {
 id: 1844,
 name: "富民",
 weather_id: "CN101290109"
 },
 {
 id: 1845,
 name: "嵩明",
 weather_id: "CN101290110"
 },
 {
 id: 1846,
 name: "禄劝",
 weather_id: "CN101290111"
 },
 {
 id: 1847,
 name: "安宁",
 weather_id: "CN101290112"
 },
 {
 id: 1848,
 name: "太华",
 weather_id: "CN101290113"
 }
 ]
 */


/**
 {
 HeWeather: [
 {
 aqi: {
 city: {
 aqi: "51",
 co: "1",
 no2: "26",
 o3: "96",
 pm10: "52",
 pm25: "28",
 qlty: "良",
 so2: "11"
 }
 },
 basic: {
 city: "昆明",
 cnty: "中国",
 id: "CN101290101",
 lat: "25.04060936",
 lon: "102.71224976",
 update: {
 loc: "2017-10-31 14:49",
 utc: "2017-10-31 06:49"
 }
 },
 daily_forecast: [
 {
 astro: {
 mr: "16:01",
 ms: "03:04",
 sr: "07:17",
 ss: "18:30"
 },
 cond: {
 code_d: "100",
 code_n: "100",
 txt_d: "晴",
 txt_n: "晴"
 },
 date: "2017-10-31",
 hum: "59",
 pcpn: "0.0",
 pop: "0",
 pres: "1020",
 tmp: {
 max: "20",
 min: "5"
 },
 uv: "8",
 vis: "20",
 wind: {
 deg: "0",
 dir: "无持续风向",
 sc: "微风",
 spd: "3"
 }
 },
 {
 astro: {
 mr: "16:40",
 ms: "04:00",
 sr: "07:17",
 ss: "18:30"
 },
 cond: {
 code_d: "101",
 code_n: "101",
 txt_d: "多云",
 txt_n: "多云"
 },
 date: "2017-11-01",
 hum: "64",
 pcpn: "0.0",
 pop: "0",
 pres: "1018",
 tmp: {
 max: "19",
 min: "6"
 },
 uv: "8",
 vis: "20",
 wind: {
 deg: "0",
 dir: "无持续风向",
 sc: "微风",
 spd: "3"
 }
 },
 {
 astro: {
 mr: "17:21",
 ms: "04:59",
 sr: "07:18",
 ss: "18:29"
 },
 cond: {
 code_d: "305",
 code_n: "306",
 txt_d: "小雨",
 txt_n: "中雨"
 },
 date: "2017-11-02",
 hum: "70",
 pcpn: "14.7",
 pop: "79",
 pres: "1018",
 tmp: {
 max: "20",
 min: "9"
 },
 uv: "8",
 vis: "17",
 wind: {
 deg: "0",
 dir: "无持续风向",
 sc: "微风",
 spd: "5"
 }
 },
 {
 astro: {
 mr: "18:04",
 ms: "05:59",
 sr: "07:19",
 ss: "18:28"
 },
 cond: {
 code_d: "305",
 code_n: "104",
 txt_d: "小雨",
 txt_n: "阴"
 },
 date: "2017-11-03",
 hum: "77",
 pcpn: "17.7",
 pop: "75",
 pres: "1025",
 tmp: {
 max: "15",
 min: "6"
 },
 uv: "1",
 vis: "16",
 wind: {
 deg: "0",
 dir: "无持续风向",
 sc: "微风",
 spd: "3"
 }
 },
 {
 astro: {
 mr: "18:49",
 ms: "07:02",
 sr: "07:19",
 ss: "18:28"
 },
 cond: {
 code_d: "100",
 code_n: "100",
 txt_d: "晴",
 txt_n: "晴"
 },
 date: "2017-11-04",
 hum: "67",
 pcpn: "0.1",
 pop: "26",
 pres: "1024",
 tmp: {
 max: "17",
 min: "6"
 },
 uv: "8",
 vis: "19",
 wind: {
 deg: "0",
 dir: "无持续风向",
 sc: "微风",
 spd: "6"
 }
 },
 {
 astro: {
 mr: "19:39",
 ms: "08:07",
 sr: "07:20",
 ss: "18:27"
 },
 cond: {
 code_d: "100",
 code_n: "101",
 txt_d: "晴",
 txt_n: "多云"
 },
 date: "2017-11-05",
 hum: "59",
 pcpn: "0.0",
 pop: "0",
 pres: "1021",
 tmp: {
 max: "18",
 min: "9"
 },
 uv: "4",
 vis: "20",
 wind: {
 deg: "0",
 dir: "无持续风向",
 sc: "微风",
 spd: "4"
 }
 },
 {
 astro: {
 mr: "20:33",
 ms: "09:13",
 sr: "07:21",
 ss: "18:27"
 },
 cond: {
 code_d: "101",
 code_n: "305",
 txt_d: "多云",
 txt_n: "小雨"
 },
 date: "2017-11-06",
 hum: "58",
 pcpn: "0.0",
 pop: "4",
 pres: "1017",
 tmp: {
 max: "21",
 min: "10"
 },
 uv: "5",
 vis: "20",
 wind: {
 deg: "0",
 dir: "无持续风向",
 sc: "微风",
 spd: "3"
 }
 }
 ],
 hourly_forecast: [
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-10-31 16:00",
 hum: "33",
 pop: "0",
 pres: "1015",
 tmp: "19",
 wind: {
 deg: "208",
 dir: "西南风",
 sc: "微风",
 spd: "9"
 }
 },
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-10-31 19:00",
 hum: "47",
 pop: "1",
 pres: "1017",
 tmp: "14",
 wind: {
 deg: "195",
 dir: "西南风",
 sc: "微风",
 spd: "8"
 }
 },
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-10-31 22:00",
 hum: "57",
 pop: "1",
 pres: "1019",
 tmp: "12",
 wind: {
 deg: "189",
 dir: "南风",
 sc: "微风",
 spd: "6"
 }
 },
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-11-01 01:00",
 hum: "59",
 pop: "5",
 pres: "1020",
 tmp: "11",
 wind: {
 deg: "181",
 dir: "南风",
 sc: "微风",
 spd: "4"
 }
 },
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-11-01 04:00",
 hum: "61",
 pop: "5",
 pres: "1019",
 tmp: "10",
 wind: {
 deg: "184",
 dir: "南风",
 sc: "微风",
 spd: "4"
 }
 },
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-11-01 07:00",
 hum: "66",
 pop: "0",
 pres: "1020",
 tmp: "10",
 wind: {
 deg: "205",
 dir: "西南风",
 sc: "微风",
 spd: "4"
 }
 },
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-11-01 10:00",
 hum: "64",
 pop: "0",
 pres: "1020",
 tmp: "13",
 wind: {
 deg: "249",
 dir: "西南风",
 sc: "微风",
 spd: "6"
 }
 },
 {
 cond: {
 code: "103",
 txt: "晴间多云"
 },
 date: "2017-11-01 13:00",
 hum: "50",
 pop: "0",
 pres: "1017",
 tmp: "16",
 wind: {
 deg: "261",
 dir: "西风",
 sc: "微风",
 spd: "10"
 }
 }
 ],
 now: {
 cond: {
 code: "100",
 txt: "晴"
 },
 fl: "16",
 hum: "43",
 pcpn: "0",
 pres: "1025",
 tmp: "16",
 vis: "10",
 wind: {
 deg: "187",
 dir: "南风",
 sc: "微风",
 spd: "9"
 }
 },
 status: "ok",
 suggestion: {
 air: {
 brf: "中",
 txt: "气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"
 },
 comf: {
 brf: "舒适",
 txt: "白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"
 },
 cw: {
 brf: "不宜",
 txt: "不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"
 },
 drsg: {
 brf: "较舒适",
 txt: "建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"
 },
 flu: {
 brf: "较易发",
 txt: "天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。"
 },
 sport: {
 brf: "适宜",
 txt: "天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"
 },
 trav: {
 brf: "适宜",
 txt: "天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"
 },
 uv: {
 brf: "弱",
 txt: "紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"
 }
 }
 }
 ]
 }
 */