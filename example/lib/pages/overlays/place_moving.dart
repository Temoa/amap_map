import 'package:amap_map/amap_map.dart';
import 'package:flutter/material.dart';
import 'package:x_amap_base/x_amap_base.dart';

class MovingDemoPage extends StatefulWidget {
  const MovingDemoPage();

  @override
  State<StatefulWidget> createState() => _State();
}

class _State extends State<MovingDemoPage> {
  _State();

  AMapController? _controller;

  Set<Polyline> _polylines = <Polyline>{};

  void _onMapCreated(AMapController controller) {
    _controller = controller;
  }

  LatLng _createLatLng(double lat, double lng) {
    return LatLng(lat, lng);
  }

  void _start() {
    final points = [
      _createLatLng(39.828809, 116.360364),
      _createLatLng(39.828809, 116.350364),
      _createLatLng(39.848809, 116.362364),
      _createLatLng(39.858809, 116.360364),
    ];
    setState(() {
      _polylines.clear();
      final Polyline polyline = Polyline(
        color: Colors.red,
        width: 5,
        points: points,
      );
      _polylines.add(polyline);
    });
    _controller?.startSmoothMove(
      Moving(
        points: points,
        duration: 10,
        visible: true,
        icon: BitmapDescriptor.fromIconPath("assets/location_marker.png"),
      ),
    );
  }

  void _stop() {
    _controller?.stopSmoothMove();
  }

  void _destroy() {
    _controller?.destroySmoothMove();
    setState(() {
      _polylines.clear();
    });
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    _destroy();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final AMapWidget map = AMapWidget(
      initialCameraPosition: CameraPosition(target: LatLng(39.828809, 116.360364), zoom: 13),
      onMapCreated: _onMapCreated,
      polylines: _polylines,
    );
    return Container(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Container(
            height: MediaQuery.of(context).size.height * 0.6,
            width: MediaQuery.of(context).size.width,
            child: map,
          ),
          Expanded(
            child: SingleChildScrollView(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Row(
                    children: <Widget>[
                      Column(
                        children: <Widget>[
                          TextButton(
                            child: const Text('开始'),
                            onPressed: _start,
                          ),
                          TextButton(
                            child: const Text('停止'),
                            onPressed: _stop,
                          ),
                          TextButton(
                            child: const Text('销毁'),
                            onPressed: _destroy,
                          ),
                        ],
                      ),
                    ],
                  )
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
