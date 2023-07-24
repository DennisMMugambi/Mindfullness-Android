package com.example.mindfullnessapp.Common

import java.util.concurrent.TimeUnit;



public class Constants {

    val BASE_PACKAGE_NAME = "com.example.mindfullnessapp";
    /**
     *
     */
    val CALLING_ACTIVITY = "calling-activity";
    /**
     *
     */
    val ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";//"yyyyMMdd'T'HHmmsszz";//";

    // Shared
    val  CONNECTION_TIME_OUT_MS = TimeUnit.SECONDS.toMillis(5);

    val KEY_COMM_TYPE = "communicationType";

    val  KEY_PAYLOAD = "payload";

    // Requests
    val  COMM_TYPE_REQUEST_PROMPT_PERMISSION = 1;

    val  COMM_TYPE_REQUEST_DATA = 2;

    // Responses
    val  COMM_TYPE_RESPONSE_PERMISSION_REQUIRED = 1001;
    val  COMM_TYPE_RESPONSE_USER_APPROVED_PERMISSION = 1002;
    val  COMM_TYPE_RESPONSE_USER_DENIED_PERMISSION = 1003;
    val  COMM_TYPE_RESPONSE_DATA = 1004;

    // Phone
    val CAPABILITY_PHONE_APP = "phone_app_runtime_permissions";
    val MESSAGE_PATH_PHONE = "/phone_message_path";

    // Wear
    val CAPABILITY_WEAR_APP = "wear_app_runtime_permissions";
    val  MESSAGE_PATH_WEAR = "/wear_message_path";

    companion object {
        var USERNAME: String? = ""
    }

}
