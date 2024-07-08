// Copyright 2019 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
import 'package:amap_map/amap_map.dart';
import 'package:flutter/foundation.dart';
import 'package:x_amap_base/x_amap_base.dart';

class Moving {
  Moving({
    required this.points,
    required this.duration,
    required this.visible,
    required this.icon,
  }) : super();

  final List<LatLng> points;
  final int duration;
  final bool visible;
  final BitmapDescriptor icon;

  Moving copyWith({
    List<LatLng>? points,
    int? duration,
    bool? visible,
    BitmapDescriptor? icon,
  }) {
    Moving copyMoving = Moving(
      points: points ?? this.points,
      duration: duration ?? this.duration,
      visible: visible ?? this.visible,
      icon: icon ?? this.icon,
    );
    return copyMoving;
  }

  Moving clone() => copyWith();

  Map<String, dynamic> toMap() {
    final Map<String, dynamic> json = <String, dynamic>{};

    void addIfPresent(String fieldName, dynamic value) {
      if (value != null) {
        json[fieldName] = value;
      }
    }

    json['points'] = _pointsToJson();
    addIfPresent('duration', duration);
    addIfPresent('visible', visible);
    addIfPresent('icon', icon.toMap());
    return json;
  }

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;
    if (other.runtimeType != runtimeType) return false;
    if (other is! Moving) return false;
    final Moving typedOther = other;
    return listEquals(points, typedOther.points) && //
        duration == typedOther.duration &&
        visible == typedOther.visible &&
        icon == typedOther.icon;
  }

  @override
  int get hashCode => super.hashCode;

  dynamic _pointsToJson() {
    final List<dynamic> result = <dynamic>[];
    for (final LatLng point in points) {
      result.add(point.toJson());
    }
    return result;
  }
}
