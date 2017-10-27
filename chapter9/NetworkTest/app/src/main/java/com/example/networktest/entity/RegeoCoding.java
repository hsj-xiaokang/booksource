package com.example.networktest.entity;

import com.example.networktest.entity.addr;

import java.util.List;

/**
 * @author hsj
 * @date 2017/10/27.
 * http://gc.ditu.aliyun.com/regeocoding?l=39.938133,116.395739&type=001
 * json 数据对象
 *
 *    {
     queryLocation: [
     39.938133,
     116.395739
        ],
    addrList: [
            {
            type: "doorPlate",
            status: 0,
            name: "",
            admCode: "",
            admName: "",
            nearestPoint: [ ],
            distance: -1
          }
        ]
    }
 */

public class RegeoCoding {
    private List<Double> queryLocation;
    private List<addr> addrList;

    public RegeoCoding() {
    }

    public RegeoCoding(List<Double> queryLocation, List<addr> addrList) {
        this.queryLocation = queryLocation;
        this.addrList = addrList;
    }

    @Override
    public String toString() {
        return "RegeoCoding{" +
                "queryLocation=" + queryLocation +
                ", addrList=" + addrList +
                '}';
    }

    public List<Double> getQueryLocation() {
        return queryLocation;
    }

    public List<addr> getAddrList() {
        return addrList;
    }

    public void setQueryLocation(List<Double> queryLocation) {
        this.queryLocation = queryLocation;
    }

    public void setAddrList(List<addr> addrList) {
        this.addrList = addrList;
    }
}
