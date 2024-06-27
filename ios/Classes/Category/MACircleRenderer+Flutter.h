//
//  MACircleRenderer+Flutter.h
//  amap_map
//
//  Created by Temoa on 2024/6/28.
//

#import <MAMapKit/MAMapKit.h>

NS_ASSUME_NONNULL_BEGIN

@class AMapCircle;

@interface MACircleRenderer (Flutter)

- (void)updateRenderWithCircle:(AMapCircle *)circle;

@end

NS_ASSUME_NONNULL_END
