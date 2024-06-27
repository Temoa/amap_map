//
//  MACircle+Flutter.h
//  amap_map
//
//  Created by Temoa on 2024/6/28.
//

#import <MAMapKit/MAMapKit.h>

NS_ASSUME_NONNULL_BEGIN

/// 该拓展类型主要用于对地图原MACircle添加一个唯一id,
/// 便于在地图回调代理中，通过id快速找到对应的AMapCircle对象，
/// 以此来构建对应的circleRender

@interface MACircle (Flutter)

@property (nonatomic,copy,readonly) NSString *circleId;

/// 使用circleId初始化对应的MACircle
/// @param circleId circle的唯一标识
- (instancetype)initWithCircleId:(NSString *)circleId;

@end

NS_ASSUME_NONNULL_END
