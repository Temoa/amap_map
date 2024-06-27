//
//  AMapCircle.h
//  amap_map
//
//  Created by Temoa on 2024/6/28.
//

#import <Foundation/Foundation.h>
#import <MAMapKit/MAMapKit.h>
#import <CoreLocation/CoreLocation.h>

NS_ASSUME_NONNULL_BEGIN

@class AMapInfoWindow;

@interface AMapCircle : NSObject

@property (nonatomic, copy) NSString *id_;

/// 中心点坐标
@property (nonatomic, assign) CLLocationCoordinate2D center;

/// 半径
@property (nonatomic, assign) CLLocationDistance radius;

/// 边框宽度
@property (nonatomic, assign) CGFloat strokeWidth;

/// 边框颜色
@property (nonatomic, strong) UIColor *strokeColor;

/// 填充颜色
@property (nonatomic, strong) UIColor *fillColor;

/// 是否可见
@property (nonatomic, assign) bool visible;

/// zIndex
@property (nonatomic, assign) double zIndex;

/// 由以上数据生成的circle对象
@property (nonatomic, strong, readonly) MACircle *circle;

- (void)updateCircle:(AMapCircle *)circle;

@end

NS_ASSUME_NONNULL_END
