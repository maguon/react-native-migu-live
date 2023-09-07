package com.migulive;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.util.RNLog;

public class MiguLiveViewManager extends SimpleViewManager<TextView> {
    public static final String REACT_CLASS = "RCTLiveView";
    ReactApplicationContext mCallerContext;

    public MiguLiveViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public TextView createViewInstance(ThemedReactContext context) {
        final TextView textView = new TextView(context);
        RNLog.e("TextView init");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritableMap event = Arguments.createMap();
                event.putString("message", "MyMessage哈哈哈--自定义");
                ReactContext reactContext = (ReactContext)textView.getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        textView.getId(),
                        "topChange",
                        event);
            }
        });
        textView.setText("我是原声的View");
        textView.setTextSize(20);
        return textView;
    }

    @ReactProp(name="text")
    public void setText(TextView textView,String text){
        textView.setText(text);
    }

    @ReactProp(name="textSize")
    public void setTextSize(TextView view,float fontSize){
        view.setTextSize(fontSize);
    }

    @ReactProp(name = "textColor",defaultInt = Color.BLACK)
    public void setTextColor(TextView view,int textColor){
        view.setTextColor(textColor);
    }
}
