
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNMiguLiveSpec.h"

@interface MiguLive : NSObject <NativeMiguLiveSpec>
#else
#import <React/RCTBridgeModule.h>

@interface MiguLive : NSObject <RCTBridgeModule>
#endif

@end
