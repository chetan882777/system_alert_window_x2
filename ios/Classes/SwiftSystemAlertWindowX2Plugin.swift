import Flutter
import UIKit

public class SwiftSystemAlertWindowX2Plugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "system_alert_window_x2", binaryMessenger: registrar.messenger())
    let instance = SwiftSystemAlertWindowX2Plugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
