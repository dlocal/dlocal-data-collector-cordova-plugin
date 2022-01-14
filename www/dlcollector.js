var argscheck = require('cordova/argscheck');
var channel = require('cordova/channel');
var exec = require('cordova/exec');
var cordova = require('cordova');

module.exports = {
    ENV_PROD: 0,
    ENV_SANDBOX: 1,

    setUp: function (apiKey, env, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'DLCollector', 'setUp', [apiKey, env]);
    },

    startSession: function (successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'DLCollector', 'startSession', []);
    },

    getSessionId: function () {
        return new Promise((resolve, reject) => {
            exec((result) => resolve(result), () => reject(), 'DLCollector', 'getSessionId', []);
        });
    }
};