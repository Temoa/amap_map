//
//  AMapCircle.m
//  amap_map
//
//  Created by Temoa on 2024/6/28.
//

#import "AMapCircle.h"
#import "AMapConvertUtil.h"
#import "MACircle+Flutter.h"

@interface AMapCircle ()

@property (nonatomic, strong, readwrite) MACircle *circle;

@end


@implementation AMapCircle

- (instancetype)init {
    self = [super init];
    if (self) {
        _visible = YES;
    }
    return self;
}

- (MACircle *)circle {
    if (_circle == nil) {
        _circle = [[MACircle alloc] initWithCircleId:self.id_];
        [_circle setCircleWithCenterCoordinate:_center radius:_radius];
    }
    return _circle;
}

//更新circle
- (void)updateCircle:(AMapCircle *)circle {
    NSAssert((circle != nil && [self.id_ isEqualToString:circle.id_]), @"更新AMapCircle数据异常");
    if ([self checkCoordsEqualWithCircle:circle] == NO) {//circle更新了center和radius
        _center = circle->_center;
        _radius = circle->_radius;
    }
    self.strokeWidth = circle.strokeWidth;
    self.strokeColor = circle.strokeColor;
    self.fillColor = circle.fillColor;
    self.visible = circle.visible;
    self.zIndex = circle.zIndex;
    if (_circle) {
        [_circle setCircleWithCenterCoordinate:_center radius:_radius];
    }
}

- (BOOL)checkCoordsEqualWithCircle:(AMapCircle *)newCircle {
    if (_center.latitude != newCircle->_center.latitude) {//center不同，则直接更新
        return NO;
    }
    if (_center.longitude != newCircle->_center.longitude) {//center不同，则直接更新
        return NO;
    }
    if(_radius != newCircle->_radius) {//radius不同，则直接更新
        return NO;
    }
    return YES;
}


@end
