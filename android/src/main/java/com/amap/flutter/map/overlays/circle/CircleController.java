package com.amap.flutter.map.overlays.circle;

import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.LatLng;

/**
 * @author Temoa
 * @date 2024/6/27 5:01 PM
 * @mail temoa_mic@hotmail.com
 * @since
 */
class CircleController implements CircleOptionsSink {

    private final Circle circle;
    private final String id;

    CircleController(Circle circle) {
        this.circle = circle;
        this.id = circle.getId();
    }

    public String getId() {
        return id;
    }

    public void remove() {
        circle.remove();
    }

    @Override
    public void setStrokeColor(int strokeColor) {
        circle.setStrokeColor(strokeColor);
    }

    @Override
    public void setFillColor(int fillColor) {
        circle.setFillColor(fillColor);
    }

    @Override
    public void setCenter(LatLng center) {
        circle.setCenter(center);
    }

    @Override
    public void setRadius(double radius) {
        circle.setRadius(radius);
    }

    @Override
    public void setVisible(boolean visible) {
        circle.setVisible(visible);
    }

    @Override
    public void setStrokeWidth(float strokeWidth) {
        circle.setStrokeWidth(strokeWidth);
    }

    @Override
    public void setZIndex(float zIndex) {
        circle.setZIndex(zIndex);
    }
}
