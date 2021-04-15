package com.chetan.system_alert_window_x2.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.Map;

import com.chetan.system_alert_window_x2.R;
import com.chetan.system_alert_window_x2.SystemAlertWindowX2Plugin;
import com.chetan.system_alert_window_x2.models.Decoration;
import com.chetan.system_alert_window_x2.models.Padding;
import com.chetan.system_alert_window_x2.utils.UiBuilder;

import static com.chetan.system_alert_window_x2.utils.Constants.*;

public class FooterView {

    private static final String TAG = "FooterView";

    private Map<String, Object> footerMap;
    private Context context;

    public FooterView(Context context, Map<String, Object> footerMap) {
        this.context = context;
        this.footerMap = footerMap;
        Log.d(TAG, "getView:  ====== CALLED map :" + footerMap);
    }

    /**
     * {
     * padding={right=16, top=0, left=16, bottom=12},
     * isShowFooter=true,
     * buttons=[
     * {padding={right=10, top=10, left=10, bottom=10},
     * margin=null,
     * width=0,
     * text={padding=null, fontSize=12.0, text=Simple button, textColor=4294609761, fontWeight=normal},
     * tag=simple_button,
     * decoration={borderColor=4294967295, borderRadius=0.0, endColor=4294967295, borderWidth=0, startColor=4294967295},
     * height=-2},
     * {padding={right=10, top=10, left=10, bottom=10},
     * margin=null,
     * width=0,
     * text={padding=null, fontSize=12.0, text=Focus button, textColor=4294967295, fontWeight=normal},
     * tag=focus_button,
     * decoration={borderColor=4294967295, borderRadius=30.0, endColor=4294384728, borderWidth=0, startColor=4294609761},
     * height=-2}
     * ],
     * text=null,
     * decoration={borderColor=4294967295, borderRadius=0.0, endColor=null, borderWidth=0, startColor=4294967295}, buttonsPosition=center}
     *
     * @return
     */

    public LinearLayout getView2() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Padding footerPadding = UiBuilder.getPadding(context, footerMap.get(KEY_PADDING));
        linearLayout.setPadding(footerPadding.getLeft(), footerPadding.getTop(), footerPadding.getRight(), footerPadding.getBottom());
        linearLayout.setLayoutParams(params);
        Decoration decoration = UiBuilder.getDecoration(context, footerMap.get(KEY_DECORATION));
        if (decoration != null) {
            GradientDrawable gd = UiBuilder.getGradientDrawable(decoration);
            linearLayout.setBackground(gd);
        }
        if ((boolean) footerMap.get(KEY_IS_SHOW_FOOTER)) {

            View view = LayoutInflater.from(context).inflate(R.layout.buttons_layout, linearLayout, false);

            Button button5 = view.findViewById(R.id.button_5);
            Button button10 = view.findViewById(R.id.button_10);
            Button button15 = view.findViewById(R.id.button_15);
            Button button20 = view.findViewById(R.id.button_20);
            Button button25 = view.findViewById(R.id.button_25);
            Button button30 = view.findViewById(R.id.button_30);
            Button button35 = view.findViewById(R.id.button_35);
            Button button40 = view.findViewById(R.id.button_40);
            Button button45 = view.findViewById(R.id.button_45);
            Button button50 = view.findViewById(R.id.button_50);
            Button button55 = view.findViewById(R.id.button_55);
            Button button60 = view.findViewById(R.id.button_60);
            Button button60Plus = view.findViewById(R.id.button_60_plus);

            button5.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "05min"));
            button10.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "10min"));
            button15.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "15min"));
            button20.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "20min"));
            button25.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "25min"));
            button30.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "30min"));
            button35.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "35min"));
            button40.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "40min"));
            button45.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "45min"));
            button50.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "50min"));
            button55.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "55min"));
            button60.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "60min"));
            button60Plus.setOnClickListener(v -> SystemAlertWindowX2Plugin.invokeCallBack(context, "onClick", "65Pmin"));

            linearLayout.addView(view);

        }
        return linearLayout;
    }

    public LinearLayout getView() {
        return getView2();
//        LinearLayout linearLayout = new LinearLayout(context);
//        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        Padding footerPadding = UiBuilder.getPadding(context, footerMap.get(KEY_PADDING));
//        linearLayout.setPadding(footerPadding.getLeft(), footerPadding.getTop(), footerPadding.getRight(), footerPadding.getBottom());
//        linearLayout.setLayoutParams(params);
//        Decoration decoration = UiBuilder.getDecoration(context, footerMap.get(KEY_DECORATION));
//        if (decoration != null) {
//            GradientDrawable gd = UiBuilder.getGradientDrawable(decoration);
//            linearLayout.setBackground(gd);
//        }
//        if ((boolean) footerMap.get(KEY_IS_SHOW_FOOTER)) {
//            Map<String, Object> textMap = Commons.getMapFromObject(footerMap, KEY_TEXT);
//
//
//            List<Map<String, Object>> buttonsMap = Commons.getMapListFromObject(footerMap, KEY_BUTTONS_LIST);
//            TextView textView = UiBuilder.getTextView(context, textMap);
//            List<Button> buttonsView = new ArrayList<>();
//            for (Map<String, Object> buttonMap : buttonsMap) {
//                buttonsView.add(UiBuilder.getButtonView(context, buttonMap));
//            }
//            String buttonsPosition = (String) footerMap.get(KEY_BUTTONS_LIST_POSITION);
//            if (textView != null) {
//                if (buttonsView.size() > 0) {
//                    if ("leading".equals(buttonsPosition)) {
//                        for (Button buttonView : buttonsView) {
//                            linearLayout.addView(buttonView);
//                        }
//                        linearLayout.addView(textView);
//                    } else {
//                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
//                                ViewGroup.LayoutParams.WRAP_CONTENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT,
//                                1.0f
//                        );
//                        textView.setLayoutParams(param);
//                        linearLayout.addView(textView);
//                        for (Button buttonView : buttonsView) {
//                            linearLayout.addView(buttonView);
//                        }
//                    }
//                } else {
//                    linearLayout.addView(textView);
//                }
//            } else {
//                for (Button buttonView : buttonsView) {
//                    linearLayout.addView(buttonView);
//                }
//                linearLayout.setGravity(Commons.getGravity(buttonsPosition, Gravity.FILL));
//            }
//        }
//        return linearLayout;
    }
}
