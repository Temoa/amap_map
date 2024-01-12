package com.amap.flutter.map;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.flutter.map.core.AMapOptionsSink;
import com.amap.flutter.map.utils.LogUtil;

import java.util.List;

import io.flutter.plugin.common.BinaryMessenger;

/**
 * @author whm
 * @date 2020/10/29 10:13 AM
 * @mail hongming.whm@alibaba-inc.com
 * @since
 */
class AMapOptionsBuilder implements AMapOptionsSink {
    private static final String CLASS_NAME = "AMapOptionsBuilder";
    private final AMapOptions options = new AMapOptions();
    protected MyLocationStyle myLocationStyle;

    protected float minZoomLevel = 3;
    protected float maxZoomLevel = 20;
    protected LatLngBounds latLngBounds;
    protected boolean trafficEnabled = true;
    protected boolean buildingsEnabled = true;
    protected boolean labelsEnabled = true;

    protected Object initialMarkers;

    protected Object initialPolylines;

    protected Object initialPolygons;

    AMapPlatformView build(int id,
                           Context context,
                           BinaryMessenger binaryMessenger,
                           LifecycleOwner lifecycleProvider) {
        try {
            final AMapPlatformView aMapPlatformView = new AMapPlatformView(id, context, binaryMessenger, lifecycleProvider, options);
            aMapPlatformView.bind(this);

            return aMapPlatformView;
        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "build", e);
        }
        return null;
    }

    @Override
    public void setCamera(CameraPosition camera) {
        options.camera(camera);
    }

    @Override
    public void setMapType(int mapType) {
        options.mapType(mapType);
    }


    @Override
    public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        this.myLocationStyle = myLocationStyle;
    }


    @Override
    public void setMinZoomLevel(float minZoomLevel) {
        this.minZoomLevel = minZoomLevel;
    }

    @Override
    public void setMaxZoomLevel(float maxZoomLevel) {
        this.maxZoomLevel = maxZoomLevel;
    }

    @Override
    public void setLatLngBounds(LatLngBounds latLngBounds) {
        this.latLngBounds = latLngBounds;
    }

    @Override
    public void setTrafficEnabled(boolean trafficEnabled) {
        this.trafficEnabled = trafficEnabled;
    }

    @Override
    public void setBuildingsEnabled(boolean buildingsEnabled) {
        this.buildingsEnabled = buildingsEnabled;
    }

    @Override
    public void setLabelsEnabled(boolean labelsEnabled) {
        this.labelsEnabled = labelsEnabled;
    }


    @Override
    public void setZoomGesturesEnabled(boolean zoomGesturesEnabled) {
        options.zoomGesturesEnabled(zoomGesturesEnabled);
    }

    @Override
    public void setScrollGesturesEnabled(boolean scrollGesturesEnabled) {
        options.scrollGesturesEnabled(scrollGesturesEnabled);
    }

    @Override
    public void setRotateGesturesEnabled(boolean rotateGesturesEnabled) {
        options.rotateGesturesEnabled(rotateGesturesEnabled);
    }

    @Override
    public void setTiltGesturesEnabled(boolean tiltGesturesEnabled) {
        options.tiltGesturesEnabled(tiltGesturesEnabled);
    }



    @Override
    public void setInitialMarkers(Object markersObject) {
        this.initialMarkers = markersObject;
    }

    @Override
    public void setInitialPolylines(Object polylinesObject) {
        this.initialPolylines = polylinesObject;
    }

    @Override
    public void setInitialPolygons(Object polygonsObject) {
        this.initialPolygons = polygonsObject;
    }


}
