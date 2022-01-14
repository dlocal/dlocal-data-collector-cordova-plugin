package com.dlocal.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.dlocal.datacollector.DLCollector;
import com.dlocal.datacollector.api.DLSettings;
import com.dlocal.datacollector.api.DLEnvironment;

public class DLCollectorPlugin extends CordovaPlugin {
    public DLEnvironment getEnv(Integer env) {
        switch (env) {
            case 0: return DLEnvironment.PRODUCTION;
            case 1: return DLEnvironment.SANDBOX;
        }
        return DLEnvironment.PRODUCTION;
    }

    @Override
    public boolean execute(String action, JSONArray args,
      final CallbackContext callbackContext) {
        PluginResult pluginResult;
        switch (action) {
            case "setUp":
                String apiKey;
                Integer env;
                try {
                    JSONObject options = args.getJSONObject(0);
                    apiKey = options.getString("apiKey");
                    env = options.getInt("env");
                } catch (JSONException e) {
                    callbackContext.error("Error: " + e.getMessage());
                    return false;
                }
                DLEnvironment environment = env != null ? getEnv(env) : DLEnvironment.PRODUCTION;

                if (apiKey == null || apiKey.isEmpty()) {
                    callbackContext.error("Error: api key can't be null or empty");
                    return false;
                }
                DLCollector.setUp(this.cordova.getActivity().getApplicationContext(), new DLSettings(apiKey, environment));

                pluginResult = new PluginResult(PluginResult.Status.OK);
                callbackContext.sendPluginResult(pluginResult);
                return true;
            case "startSession":
                DLCollector.startSession();

                pluginResult = new PluginResult(PluginResult.Status.OK);
                callbackContext.sendPluginResult(pluginResult);
                return true;
            case "getSessionId":

                pluginResult = new PluginResult(PluginResult.Status.OK, DLCollector.getSessionId());
                callbackContext.sendPluginResult(pluginResult);
                return true;
            default:
                callbackContext.error("Unknown method");
                return false; 
        }
      }
}