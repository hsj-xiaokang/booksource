package com.example.networktest.entity;

import java.util.List;

/**
 * Created by hsj on 2017/10/27.
 */

public class addr{
    private String type;
    private Integer status;
    private String name;
    private String admCode;
    private String admName;
    private List<Double> nearestPoint;
    private Integer distance;

    public addr() {
    }

    public addr(String type, Integer distance, List<Double> nearestPoint, String admName, String admCode, String name, Integer status) {
        this.type = type;
        this.distance = distance;
        this.nearestPoint = nearestPoint;
        this.admName = admName;
        this.admCode = admCode;
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "addr{" +
                "type='" + type + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", admCode='" + admCode + '\'' +
                ", admName='" + admName + '\'' +
                ", nearestPoint=" + nearestPoint +
                ", distance=" + distance +
                '}';
    }

    public String getType() {
        return type;
    }

    public Integer getDistance() {
        return distance;
    }

    public List<Double> getNearestPoint() {
        return nearestPoint;
    }

    public String getAdmName() {
        return admName;
    }

    public String getAdmCode() {
        return admCode;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setNearestPoint(List<Double> nearestPoint) {
        this.nearestPoint = nearestPoint;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public void setAdmCode(String admCode) {
        this.admCode = admCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
