# dl-data-collector-cordova-plugin

DLocal's Data Collector SDK cordova plugin for ionic

This plugin is a wrapper of the native SDKs which can be found here:

* [iOS](https://bitbucket.org/dlocal-public/data-collector-sdk-ios/src/master/)
* [Android](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/)

More info about the functionallity of the SDK on each platform can be found on those repositories.

## Install
```ash
cordova plugin add https://bitbucket.org/dlocal-public/dlocal-data-collector-cordova-plugin.git
```

## Usage
### Setup on app start
Add this to your app startup, 
```javascript
    DLCollector.setUp({
      apiKey: "TEST",
      env: DLCollector.ENV_SANDBOX // or DLCollector.ENV_PROD
    });

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

## Publishing
If your app is distributed through the Apple App Store or Google Play Store, you must comply with the data policies and rules for each store. When using this SDK in your app you must ensure the following: 

### Apple App Store
* Add a disclaimer in the app that says that device data is collected and sent to a third party (dlocal). 
* If your app uses location, please add to the info.plist NSLocatioUsageeDescription keys a disclaimer that the location data will also be used in fraud prevention.

The SDK will only use location data if the app is using it already, so it is not necessary to add a location disclaimer if location is not used.

For more information please refer to [apple's documentation](https://developer.apple.com/documentation/uikit/protecting_the_user_s_privacy).

### Google Play Store
* Add a disclaimer in the app that says that device data is collected and sent to a third party (dlocal). 

In the Data Safety section in the Play Console of your app, ensure you declare these data types: 
* Location: Approximate Location
* App activity: Installed apps
* Device or others identifiers: Device or other identifiers

For more information please refer to [Google Play's Data safety documentation](https://support.google.com/googleplay/android-developer/answer/10787469?hl=en#types&zippy=%2Cdata-types).

