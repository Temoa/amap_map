// Copyright 2019 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
import 'package:flutter/material.dart' show Color;
import 'package:x_amap_base/x_amap_base.dart';

import 'base_overlay.dart';

class Circle extends BaseOverlay {
  Circle({
    required this.center,
    required this.radius,
    double strokeWidth = 10,
    this.strokeColor = const Color(0xCC00BFFF),
    this.fillColor = const Color(0xC487CEFA),
    this.visible = true,
    this.zIndex = 0,
  })  : this.strokeWidth = (strokeWidth <= 0 ? 10 : strokeWidth),
        super();

  final Color strokeColor;
  final Color fillColor;
  final LatLng center;
  final double radius;
  final bool visible;
  final double strokeWidth;
  final double zIndex;

  Circle copyWith({
    LatLng? centerParam,
    double? radiusParam,
    double? strokeWidthParam,
    Color? strokeColorParam,
    Color? fillColorParam,
    bool? visibleParam,
    double? zIndexParam,
  }) {
    Circle copyCircle = Circle(
      center: centerParam ?? center,
      radius: radiusParam ?? radius,
      strokeWidth: strokeWidthParam ?? strokeWidth,
      strokeColor: strokeColorParam ?? strokeColor,
      fillColor: fillColorParam ?? fillColor,
      visible: visibleParam ?? visible,
      zIndex: zIndexParam ?? zIndex,
    );
    copyCircle.setIdForCopy(id);
    return copyCircle;
  }

  Circle clone() => copyWith();

  @override
  Map<String, dynamic> toMap() {
    final Map<String, dynamic> json = <String, dynamic>{};

    void addIfPresent(String fieldName, dynamic value) {
      if (value != null) {
        json[fieldName] = value;
      }
    }

    addIfPresent('id', id);
    json['center'] = _centerToJson();
    addIfPresent('radius', radius);
    addIfPresent('strokeWidth', strokeWidth);
    addIfPresent('strokeColor', strokeColor.value);
    addIfPresent('fillColor', fillColor.value);
    addIfPresent('visible', visible);
    addIfPresent('zIndex', zIndex);
    return json;
  }

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;
    if (other.runtimeType != runtimeType) return false;
    if (other is! Circle) return false;
    final Circle typedOther = other;
    return id == typedOther.id && center == typedOther.center && strokeWidth == typedOther.strokeWidth && strokeColor == typedOther.strokeColor && fillColor == typedOther.fillColor && visible == typedOther.visible && zIndex == typedOther.zIndex;
  }

  @override
  int get hashCode => super.hashCode;

  dynamic _centerToJson() {
    return center.toJson();
  }
}

Map<String, Circle> keyByCircleId(Iterable<Circle> circles) {
  // ignore: unnecessary_null_comparison
  if (circles == null) {
    return <String, Circle>{};
  }
  return Map<String, Circle>.fromEntries(circles.map((Circle circle) => MapEntry<String, Circle>(circle.id, circle.clone())));
}
