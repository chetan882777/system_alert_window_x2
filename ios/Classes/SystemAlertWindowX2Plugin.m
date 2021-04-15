#import "SystemAlertWindowX2Plugin.h"
#if __has_include(<system_alert_window_x2/system_alert_window_x2-Swift.h>)
#import <system_alert_window_x2/system_alert_window_x2-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "system_alert_window_x2-Swift.h"
#endif

@implementation SystemAlertWindowX2Plugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSystemAlertWindowX2Plugin registerWithRegistrar:registrar];
}
@end
