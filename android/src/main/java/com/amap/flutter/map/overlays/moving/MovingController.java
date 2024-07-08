package com.amap.flutter.map.overlays.moving;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.amap.flutter.map.MyMethodCallHandler;
import com.amap.flutter.map.utils.Const;
import com.amap.flutter.map.utils.ConvertUtil;
import com.amap.flutter.map.utils.LogUtil;

import java.util.List;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MovingController implements MyMethodCallHandler {

    private static final String CLASS_NAME = "MovingPointOverlayController";

    private final MethodChannel methodChannel;
    private final AMap amap;

    private MovingPointOverlay smoothMarker;

    public MovingController(MethodChannel methodChannel, AMap map) {
        this.methodChannel = methodChannel;
        this.amap = map;
    }

    public void startSmoothMove(
            List<LatLng> points,
            int totalDuration,
            boolean visible,
            BitmapDescriptor icon
    ) {
        if (smoothMarker == null) {
            Marker marker = amap.addMarker(new MarkerOptions().icon(icon).anchor(0.5f, 0.5f));

            smoothMarker = new MovingPointOverlay(amap, marker);
            LatLng drivePoint = points.get(0);
            Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(points, drivePoint);
            List<LatLng> subList = points.subList(pair.first, points.size());
            smoothMarker.setPoints(subList);
            smoothMarker.setTotalDuration(totalDuration);
            smoothMarker.setVisible(visible);

            smoothMarker.setMoveListener(distance -> {
                // distance - 单位 米
                // 返回 距离终点的距离
                System.out.println(distance);
                System.out.println(getPosition());
            });
        }
        smoothMarker.startSmoothMove();
    }

    public void stopMove() {
        if (smoothMarker != null) {
            smoothMarker.stopMove();
        }
    }

    public void destroy() {
        if (smoothMarker != null) {
            smoothMarker.destroy();
        }
    }

    @Nullable
    LatLng getPosition() {
        if (smoothMarker != null) {
            return smoothMarker.getPosition();
        }
        return null;
    }

    void resetIndex() {
        if (smoothMarker != null) {
            smoothMarker.resetIndex();
        }
    }

    @Override
    public void doMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        String methodId = call.method;
        LogUtil.i(CLASS_NAME, "doMethodCall===>" + methodId);
        switch (methodId) {
            case Const.METHOD_MOVING_START:
                final Map<?, ?> data = ConvertUtil.toMap(call.arguments);
                final Object points = data.get("points");
                final Object totalDuration = data.get("duration");
                final Object visible = data.get("visible");
                final Object icon = data.get("icon");
                if (points == null || totalDuration == null || visible == null || icon == null) return;

                startSmoothMove(
                        ConvertUtil.toPoints(points),
                        ConvertUtil.toInt(totalDuration),
                        ConvertUtil.toBoolean(visible),
                        ConvertUtil.toBitmapDescriptor(icon)
                );
                break;

            case Const.METHOD_MOVING_STOP:
                stopMove();
                break;

            case Const.METHOD_MOVING_DESTROY:
                destroy();
                break;
        }
    }

    @Override
    public String[] getRegisterMethodIdArray() {
        return Const.METHOD_ID_LIST_FOR_MOVING;
    }
}
