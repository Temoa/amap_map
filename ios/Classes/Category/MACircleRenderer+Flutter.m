//
//  MACircleRenderer+Flutter.m
//  amap_map
//
//  Created by Temoa on 2024/6/28.
//

#import "MACircleRenderer+Flutter.h"
#import "AMapCircle.h"

@implementation MACircleRenderer (Flutter)

- (void)updateRenderWithCircle:(AMapCircle *)circle {
    self.lineWidth = circle.strokeWidth;
    self.strokeColor  = circle.strokeColor;
    self.fillColor = circle.fillColor;
    if (circle.visible) {
        self.alpha = 1.0;
    } else {
        self.alpha = 0;
    }
}


@end
