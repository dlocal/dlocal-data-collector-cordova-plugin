import DLDataCollectorSDK
@objc(DLCollector) class DLCollector : CDVPlugin {
    @objc(setUp:)
    func setUp(command: CDVInvokedUrlCommand) {
        let arguments = command.argument(at: 0) as? Dictionary<String, Any>
        
        let env = arguments?["env"] as? Int
        var environment: DLEnvironment = .production
        var pluginResult: CDVPluginResult
        
        if let env = env, let dlEnv = DLEnvironment(rawValue: env) {
            environment = dlEnv
        }
        
        guard let apiKey: String = arguments?["apiKey"] as? String else {
            pluginResult = CDVPluginResult (status: CDVCommandStatus_ERROR, messageAs: "Missing API Key");
            self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
            return
        }
        
        
        DLDataCollectorSDK.DLCollector.shared.setUp(DLSettings(apiKey: apiKey,
                                                               env: environment,
                                                               logLevel: .silent))
        
        
        pluginResult = CDVPluginResult(status: CDVCommandStatus_OK)
        
        self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
    }
    
    @objc(startSession:)
    func startSession(command: CDVInvokedUrlCommand) {
        try? DLDataCollectorSDK.DLCollector.shared.startSession()
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK);
        self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
    }
    
    @objc(getSessionId:)
    func getSessionId(command: CDVInvokedUrlCommand) {
        let sessionId = DLDataCollectorSDK.DLCollector.shared.getSessionId()
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: sessionId ?? "")
        self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
    }
}
