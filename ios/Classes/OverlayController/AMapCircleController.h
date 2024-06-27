//
//  AMapCircleController.h
//  amap_map
//
//  Created by Temoa on 2024/6/28.
//

#import <Foundation/Foundation.h>
#import <Flutter/Flutter.h>
#import <MAMapKit/MAMapKit.h>

NS_ASSUME_NONNULL_BEGIN

@class AMapCircle;

@interface AMapCircleController : NSObject

- (instancetype)init:(FlutterMethodChannel*)methodChannel
             mapView:(MAMapView*)mapView
           registrar:(NSObject<FlutterPluginRegistrar>*)registrar;

- (nullable AMapCircle *)circleForId:(NSString *)circleId;

- (void)addCircles:(NSArray*)circlesToAdd;

- (void)changeCircles:(NSArray*)circlesToChange;

- (void)removeCircleIds:(NSArray*)circleIdsToRemove;

@end

NS_ASSUME_NONNULL_END
