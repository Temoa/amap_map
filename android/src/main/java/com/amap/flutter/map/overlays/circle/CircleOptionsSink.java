package com.amap.flutter.map.overlays.circle;

import com.amap.api.maps.model.LatLng;

/**
 * @author Temoa
 * @date 2024/6/27 5:01 PM
 * @mail temoa_mic@hotmail.com
 * @since
 */
interface CircleOptionsSink {
    void setStrokeColor(int strokeColor);

    void setFillColor(int fillColor);

    void setCenter(LatLng center);

    void setRadius(double radius);

    void setVisible(boolean visible);

    void setStrokeWidth(float strokeWidth);

    void setZIndex(float zIndex);
}
