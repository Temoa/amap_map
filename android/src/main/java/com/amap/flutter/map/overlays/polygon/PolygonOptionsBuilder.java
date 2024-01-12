package com.amap.flutter.map.overlays.polygon;

import com.amap.api.maps.model.AMapPara;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolygonOptions;

import java.util.List;

/**
 * @author whm
 * @date 2020/11/12 9:51 AM
 * @mail hongming.whm@alibaba-inc.com
 * @since
 */
class PolygonOptionsBuilder implements PolygonOptionsSink {
    final PolygonOptions polygonOptions;

    PolygonOptionsBuilder() {
        polygonOptions = new PolygonOptions();
    }

    public PolygonOptions build() {
        return polygonOptions;
    }


    @Override
    public void setPoints(List<LatLng> points) {
        polygonOptions.setPoints(points);
    }

    @Override
    public void setStrokeWidth(float strokeWidth) {
        polygonOptions.strokeWidth(strokeWidth);
    }

    @Override
    public void setStrokeColor(int color) {
        polygonOptions.strokeColor(color);
    }

    @Override
    public void setFillColor(int color) {
        polygonOptions.fillColor(color);
    }

    @Override
    public void setVisible(boolean visible) {
        polygonOptions.visible(visible);
    }

}
