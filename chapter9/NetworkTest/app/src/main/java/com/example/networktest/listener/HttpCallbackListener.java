package com.example.networktest.listener;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}