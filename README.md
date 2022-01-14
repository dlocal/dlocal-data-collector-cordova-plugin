# dl-data-collector-capacitor-plugin

DLocal's Data Collector SDK capacitor plugin for ionic

This plugin is a wrapper of the native SDKs which can be found here:

* [iOS](https://bitbucket.org/dlocal-public/data-collector-sdk-ios/src/master/)
* [Android](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/)

More info about the functionallity of the SDK on each platform can be found on those repositories.

## Install
'''bash
cordova plugin add https://nrostan_dl@bitbucket.org/dlocal-public/dlocal-data-collector-cordova-plugin.git
'''

## Usage
### Setup on app start
Add this to your app startup, 
```javascript
    DLCollector.setUp({
      apiKey: "TEST",
      env: DLCollector.ENV_SANDBOX // or DLCollector.ENV_PROD
    );

    DLCollector.startSession();
    
```
Replacing `apiKey` with your key.

### Link the session to the transaction
When the user starts the checkout transaction, gather the session id like so:

```javascript
    sessionId = await DLCollector.getSessionId();
```

 **NOTE: The session id will be undefined if setUp and startSession not called before** 

Submit this value in the payment request within the `additional_risk_data.device.event_uuid` parameter. The `sessionId` can be `undefined` if a session is not available or an error occurred.