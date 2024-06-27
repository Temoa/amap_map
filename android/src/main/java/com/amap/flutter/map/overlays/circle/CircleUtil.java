package com.amap.flutter.map.overlays.circle;

import com.amap.flutter.map.utils.ConvertUtil;

import java.util.Map;

/**
 * @author Temoa
 * @date 2024/6/27 5:01 PM
 * @mail temoa_mic@hotmail.com
 * @since
 */
class CircleUtil {

    static String interpretOptions(Object o, CircleOptionsSink sink) {
        final Map<?, ?> data = ConvertUtil.toMap(o);
        final Object strokeColor = data.get("strokeColor");
        if (strokeColor != null) {
            sink.setStrokeColor(ConvertUtil.toInt(strokeColor));
        }

        final Object fillColor = data.get("fillColor");
        if (fillColor != null) {
            sink.setFillColor(ConvertUtil.toInt(fillColor));
        }

        final Object center = data.get("center");
        if (center != null) {
            sink.setCenter(ConvertUtil.toLatLng(center));
        }

        final Object radius = data.get("radius");
        if (radius != null) {
            sink.setRadius(ConvertUtil.toDouble(radius));
        }

        final Object visible = data.get("visible");
        if (visible != null) {
            sink.setVisible(ConvertUtil.toBoolean(visible));
        }

        final Object width = data.get("strokeWidth");
        if (width != null) {
            sink.setStrokeWidth(ConvertUtil.toFloatPixels(width));
        }

        final Object zIndex = data.get("zIndex");
        if (zIndex != null) {
            sink.setZIndex(ConvertUtil.toFloatPixels(zIndex));
        }

        final String polylineId = (String) data.get("id");
        if (polylineId == null) {
            throw new IllegalArgumentException("circle was null");
        } else {
            return polylineId;
        }
    }


}
