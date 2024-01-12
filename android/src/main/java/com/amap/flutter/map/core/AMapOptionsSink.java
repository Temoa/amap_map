package com.amap.flutter.map.core;

import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MyLocationStyle;

/**
 * @author kuloud
 * @author whm
 * @date 2020/10/29 9:56 AM
 * @mail hongming.whm@alibaba-inc.com
 * @since
 */
public interface AMapOptionsSink {

    void setCamera(CameraPosition camera);

    void setMapType(int mapType);

     void setMyLocationStyle(MyLocationStyle myLocationStyle);

     void setMinZoomLevel(float minZoomLevel);

     void setMaxZoomLevel(float maxZoomLevel);

     void setLatLngBounds(LatLngBounds latLngBounds);

     void setTrafficEnabled(boolean trafficEnabled);


     void setBuildingsEnabled(boolean buildingsEnabled);

     void setLabelsEnabled(boolean labelsEnabled);



     void setZoomGesturesEnabled(boolean zoomGesturesEnabled);

     void setScrollGesturesEnabled(boolean scrollGesturesEnabled);

     void setRotateGesturesEnabled(boolean rotateGesturesEnabled);

     void setTiltGesturesEnabled(boolean tiltGesturesEnabled);

     void setInitialMarkers(Object initialMarkers);

     void setInitialPolylines(Object initialPolylines);

     void setInitialPolygons(Object initialPolygons);
}
