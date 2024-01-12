package com.amap.flutter.map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.AMapWrapper;
import com.amap.flutter.map.core.MapController;
import com.amap.flutter.map.overlays.marker.MarkersController;
import com.amap.flutter.map.overlays.polygon.PolygonsController;
import com.amap.flutter.map.overlays.polyline.PolylinesController;
import com.amap.flutter.map.utils.LogUtil;
import com.amap.flutter.map.wrapper.MAWebViewWrapper;
import com.amap.flutter.map.wrapper.MyWebView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;


/**
 * @author kuloud
 * @author whm
 * @date 2020/10/27 5:49 PM
 * @mail hongming.whm@alibaba-inc.com
 * @since
 */
public class AMapPlatformView
        implements
        DefaultLifecycleObserver,
        ActivityPluginBinding.OnSaveInstanceStateListener,
        MethodChannel.MethodCallHandler,
        PlatformView {
    private static final String CLASS_NAME = "AMapPlatformView";
    private final MethodChannel methodChannel;
    private final Map<String, MyMethodCallHandler> myMethodCallHandlerMap;
    private MapController mapController;
    private MarkersController markersController;
    private PolylinesController polylinesController;
    private PolygonsController polygonsController;
    private AMapWrapper mapView;

    private MAWebViewWrapper webViewWrapper;

    private boolean disposed = false;

    private AMapOptionsBuilder aMapOptionsBuilder;

    AMapPlatformView(int id,
                     Context context,
                     BinaryMessenger binaryMessenger,
                     LifecycleOwner lifecycleProvider,
                     AMapOptions options) {

        methodChannel = new MethodChannel(binaryMessenger, "amap_map_lite_" + id);
        methodChannel.setMethodCallHandler(this);
        myMethodCallHandlerMap = new HashMap<>(8);

        try {
            MyWebView myWebView = new MyWebView(context);
            webViewWrapper = new MAWebViewWrapper(myWebView);
            mapView = new AMapWrapper(context, webViewWrapper);
            mapController = new MapController(methodChannel, mapView);
            
            lifecycleProvider.getLifecycle().addObserver(this);

        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "<init>", e);
        }
    }

    private void initMyMethodCallHandlerMap() {
        String[] methodIdArray = mapController.getRegisterMethodIdArray();
        if (null != methodIdArray) {
            for (String methodId : methodIdArray) {
                myMethodCallHandlerMap.put(methodId, mapController);
            }
        }

        methodIdArray = markersController.getRegisterMethodIdArray();
        if (null != methodIdArray) {
            for (String methodId : methodIdArray) {
                myMethodCallHandlerMap.put(methodId, markersController);
            }
        }

        methodIdArray = polylinesController.getRegisterMethodIdArray();
        if (null != methodIdArray) {
            for (String methodId : methodIdArray) {
                myMethodCallHandlerMap.put(methodId, polylinesController);
            }
        }

        methodIdArray = polygonsController.getRegisterMethodIdArray();
        if (null != methodIdArray) {
            for (String methodId : methodIdArray) {
                myMethodCallHandlerMap.put(methodId, polygonsController);
            }
        }
    }


    public MapController getMapController() {
        return mapController;
    }

    public MarkersController getMarkersController() {
        return markersController;
    }

    public PolylinesController getPolylinesController() {
        return polylinesController;
    }

    public PolygonsController getPolygonsController() {
        return polygonsController;
    }


    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        LogUtil.i(CLASS_NAME, "onMethodCall==>" + call.method + ", arguments==> " + call.arguments);
        String methodId = call.method;
        if (myMethodCallHandlerMap.containsKey(methodId)) {
            Objects.requireNonNull(myMethodCallHandlerMap.get(methodId)).doMethodCall(call, result);
        } else {
            LogUtil.w(CLASS_NAME, "onMethodCall, the methodId: " + call.method + ", not implemented");
            result.notImplemented();
        }
    }


    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        LogUtil.i(CLASS_NAME, "onCreate==>");
        try {
            if (disposed) {
                return;
            }
            if (null != mapView) {
                mapView.onCreate();
                mapView.getMapAsyn((amap) -> {
                    markersController = new MarkersController(methodChannel, amap);
                    polylinesController = new PolylinesController(methodChannel, amap);
                    polygonsController = new PolygonsController(methodChannel, amap);

                    initMyMethodCallHandlerMap();

                    getMapController().onMapReady(amap);

                    if (null != aMapOptionsBuilder.myLocationStyle) {
                        getMapController().setMyLocationStyle(aMapOptionsBuilder.myLocationStyle);
                    }

                    getMapController().setMinZoomLevel(aMapOptionsBuilder.minZoomLevel);
                    getMapController().setMaxZoomLevel(aMapOptionsBuilder.maxZoomLevel);

                    if (null != aMapOptionsBuilder.latLngBounds) {
                        getMapController().setLatLngBounds(aMapOptionsBuilder.latLngBounds);
                    }

                    getMapController().setTrafficEnabled(aMapOptionsBuilder.trafficEnabled);
                    getMapController().setBuildingsEnabled(aMapOptionsBuilder.buildingsEnabled);
                    getMapController().setLabelsEnabled(aMapOptionsBuilder.labelsEnabled);


                    if (null != aMapOptionsBuilder.initialMarkers) {
                        List<Object> markerList = (List<Object>) aMapOptionsBuilder.initialMarkers;
                        getMarkersController().addByList(markerList);
                    }

                    if (null != aMapOptionsBuilder.initialPolylines) {
                        List<Object> markerList = (List<Object>) aMapOptionsBuilder.initialPolylines;
                        getPolylinesController().addByList(markerList);
                    }

                    if (null != aMapOptionsBuilder.initialPolygons) {
                        List<Object> polygonList = (List<Object>) aMapOptionsBuilder.initialPolygons;
                        getPolygonsController().addByList(polygonList);
                    }

                });
            }

        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "onCreate", e);
        }
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        LogUtil.i(CLASS_NAME, "onStart==>");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        LogUtil.i(CLASS_NAME, "onResume==>");
        try {
            if (disposed) {
                return;
            }
            if (null != mapView) {
                mapView.onResume();
            }
        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "onResume", e);
        }
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        LogUtil.i(CLASS_NAME, "onPause==>");
        try {
            if (disposed) {
                return;
            }
            mapView.onPause();
        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "onPause", e);
        }
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        LogUtil.i(CLASS_NAME, "onStop==>");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        LogUtil.i(CLASS_NAME, "onDestroy==>");
        try {
            if (disposed) {
                return;
            }
            destroyMapViewIfNecessary();
        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "onDestroy", e);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        LogUtil.i(CLASS_NAME, "onDestroy==>");
        try {
            if (disposed) {
                return;
            }
            mapView.onSaveInstanceState(bundle);
        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "onSaveInstanceState", e);
        }
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle bundle) {
        LogUtil.i(CLASS_NAME, "onDestroy==>");
        try {
            if (disposed) {
                return;
            }
        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "onRestoreInstanceState", e);
        }
    }


    @Override
    public View getView() {
        LogUtil.i(CLASS_NAME, "getView==>");
        return webViewWrapper.getView();
    }

    @Override
    public void dispose() {
        LogUtil.i(CLASS_NAME, "dispose==>");
        try {
            if (disposed) {
                return;
            }
            methodChannel.setMethodCallHandler(null);
            destroyMapViewIfNecessary();
            disposed = true;
        } catch (Throwable e) {
            LogUtil.e(CLASS_NAME, "dispose", e);
        }
    }

    private void destroyMapViewIfNecessary() {
        if (mapView == null) {
            return;
        }
        mapView.onDestroy();
    }


    public void bind(AMapOptionsBuilder aMapOptionsBuilder) {
        this.aMapOptionsBuilder = aMapOptionsBuilder;
    }
}
