import 'package:amap_map/amap_map.dart';
import 'package:amap_map_example/widgets/amap_switch_button.dart';
import 'package:flutter/material.dart';
import 'package:x_amap_base/x_amap_base.dart';

class CircleDemoPage extends StatefulWidget {
  const CircleDemoPage();

  @override
  State<StatefulWidget> createState() => _State();
}

class _State extends State<CircleDemoPage> {
  _State();

// Values when toggling Polygon color
  int colorsIndex = 0;
  List<Color> colors = <Color>[
    Colors.purple,
    Colors.red,
    Colors.green,
    Colors.pink,
  ];

  Map<String, Circle> _circles = <String, Circle>{};
  String? selectedCircleId;

  void _onMapCreated(AMapController controller) {}

  LatLng _createLatLng(double lat, double lng) {
    return LatLng(lat, lng);
  }

  void _add() {
    final Circle polygon = Circle(
      strokeColor: colors[++colorsIndex % colors.length],
      fillColor: colors[++colorsIndex % colors.length],
      strokeWidth: 15,
      center: _createLatLng(39.828809, 116.360364),
      radius: 1000,
    );
    setState(() {
      selectedCircleId = polygon.id;
      _circles[polygon.id] = polygon;
    });
  }

  void _remove() {
    if (selectedCircleId != null) {
      //有选中的Marker
      setState(() {
        _circles.remove(selectedCircleId);
      });
    }
  }

  void _changeStrokeWidth() {
    final Circle? selectedCircle = _circles[selectedCircleId];
    if (selectedCircle != null) {
      double currentWidth = selectedCircle.strokeWidth;
      if (currentWidth < 50) {
        currentWidth += 10;
      } else {
        currentWidth = 5;
      }
      //有选中的Polygon
      setState(() {
        _circles[selectedCircleId!] = selectedCircle.copyWith(strokeWidthParam: currentWidth);
      });
    } else {
      print('无选中的Polygon，无法修改宽度');
    }
  }

  void _changeColors() {
    final Circle circle = _circles[selectedCircleId]!;
    setState(() {
      _circles[selectedCircleId!] = circle.copyWith(
        strokeColorParam: colors[++colorsIndex % colors.length],
        fillColorParam: colors[(colorsIndex + 1) % colors.length],
      );
    });
  }

  Future<void> _toggleVisible(value) async {
    final Circle circle = _circles[selectedCircleId]!;
    setState(() {
      _circles[selectedCircleId!] = circle.copyWith(
        visibleParam: value,
      );
    });
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final AMapWidget map = AMapWidget(
      initialCameraPosition: CameraPosition(target: LatLng(39.828809, 116.360364), zoom: 13),
      onMapCreated: _onMapCreated,
      circles: Set<Circle>.of(_circles.values),
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
                            child: const Text('添加'),
                            onPressed: _add,
                          ),
                          TextButton(
                            child: const Text('删除'),
                            onPressed: (selectedCircleId == null) ? null : _remove,
                          ),
                          TextButton(
                            child: const Text('修改边框宽度'),
                            onPressed: (selectedCircleId == null) ? null : _changeStrokeWidth,
                          ),
                        ],
                      ),
                      Column(
                        children: <Widget>[
                          TextButton(
                            child: const Text('修改边框和填充色'),
                            onPressed: (selectedCircleId == null) ? null : _changeColors,
                          ),
                          AMapSwitchButton(
                            label: Text('显示'),
                            onSwitchChanged: (selectedCircleId == null) ? null : _toggleVisible,
                            defaultValue: true,
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
