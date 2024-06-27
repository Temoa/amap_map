package com.amap.flutter.map.overlays.circle;

import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;

/**
 * @author Temoa
 * @date 2024/6/27 5:01 PM
 * @mail temoa_mic@hotmail.com
 * @since
 */
class CircleOptionsBuilder implements CircleOptionsSink {
    final CircleOptions circleOptions;

    CircleOptionsBuilder() {
        circleOptions = new CircleOptions();
        circleOptions.usePolylineStroke(true);
    }

    public CircleOptions build() {
        return circleOptions;
    }


    @Override
    public void setStrokeColor(int strokeColor) {
        circleOptions.strokeColor(strokeColor);
    }

    @Override
    public void setFillColor(int fillColor) {
        circleOptions.fillColor(fillColor);
    }

    @Override
    public void setCenter(LatLng center) {
        circleOptions.center(center);
    }

    @Override
    public void setRadius(double radius) {
        circleOptions.radius(radius);
    }

    @Override
    public void setVisible(boolean visible) {
        circleOptions.visible(visible);
    }

    @Override
    public void setStrokeWidth(float strokeWidth) {
        circleOptions.strokeWidth(strokeWidth);
    }

    @Override
    public void setZIndex(float zIndex) {
        circleOptions.zIndex(zIndex);
    }
}
