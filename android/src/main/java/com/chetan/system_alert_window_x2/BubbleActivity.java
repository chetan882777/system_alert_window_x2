package com.chetan.system_alert_window_x2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import com.chetan.system_alert_window_x2.utils.Commons;
import com.chetan.system_alert_window_x2.views.BodyView;
import com.chetan.system_alert_window_x2.views.FooterView;
import com.chetan.system_alert_window_x2.views.HeaderView;

import io.flutter.embedding.android.FlutterActivity;

import static com.chetan.system_alert_window_x2.utils.Constants.INTENT_EXTRA_PARAMS_MAP;
import static com.chetan.system_alert_window_x2.utils.Constants.KEY_BODY;
import static com.chetan.system_alert_window_x2.utils.Constants.KEY_FOOTER;
import static com.chetan.system_alert_window_x2.utils.Constants.KEY_HEADER;

public class BubbleActivity extends FlutterActivity {

    private  LinearLayout bubbleLayout;
    private HashMap<String, Object> paramsMap;

    private Context mContext;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        mContext = this;
        bubbleLayout = findViewById(R.id.bubbleLayout);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            paramsMap = (HashMap<String, Object>) intent.getSerializableExtra(INTENT_EXTRA_PARAMS_MAP);
            configureUI();
        }
    }

    void configureUI(){
        Map<String, Object> headersMap = Commons.getMapFromObject(paramsMap, KEY_HEADER);
        Map<String, Object> bodyMap = Commons.getMapFromObject(paramsMap, KEY_BODY);
        Map<String, Object> footerMap = Commons.getMapFromObject(paramsMap, KEY_FOOTER);
        LinearLayout headerView = new HeaderView(mContext, headersMap).getView();
        LinearLayout bodyView = new BodyView(mContext, bodyMap).getView();
        LinearLayout footerView = new FooterView(mContext, footerMap).getView();

        bubbleLayout.setBackgroundColor(Color.WHITE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        bubbleLayout.setLayoutParams(params);
        bubbleLayout.addView(headerView);
        bubbleLayout.addView(bodyView);
        bubbleLayout.addView(footerView);
    }
}
