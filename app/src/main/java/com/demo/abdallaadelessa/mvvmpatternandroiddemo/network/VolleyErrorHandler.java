package com.demo.abdallaadelessa.mvvmpatternandroiddemo.network;

import android.content.Context;

import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;

import org.json.JSONObject;

/**
 * Created by abdalla on 29/07/15.
 */
public class VolleyErrorHandler {

    public static String getErrorMessage(VolleyError error) {
        String errorMsg = VolleySingleton.ERROR_UNKNOWN_ERROR;
        if(error instanceof TimeoutError) {
            errorMsg = VolleySingleton.ERROR_TIME_OUT;
        }
        else if(isNetworkProblem(error)) {
            errorMsg = VolleySingleton.ERROR_INTERNET_CONNECTION;
        }
        else if(isServerProblem(error)) {
            errorMsg = VolleySingleton.ERROR_SERVER_ERROR;
            if(error.networkResponse != null) {
                try {
                    final String errorResponseTag = VolleySingleton.ERROR_RESPONSE_TAG;
                    JSONObject jsonObject = new JSONObject(volleyErrorToString(error));
                    if(jsonObject != null && jsonObject.has(errorResponseTag)) {
                        errorMsg = jsonObject.getString(errorResponseTag);
                    }
                }
                catch(Exception e) {
                    VolleySingleton.logError(e);
                }
            }
        }
        return errorMsg;
    }

    public static void handleError(Context context, VolleyError error) {
        VolleySingleton.showToast(context, getErrorMessage(error));
    }

    public static String volleyErrorToString(VolleyError error) {
        String errorInString = null;
        try {
            errorInString = new String(error.networkResponse.data);
        }
        catch(Exception e) {
            VolleySingleton.logError(e);
        }
        return errorInString;
    }

    // --------------------------------------------

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    protected static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    /**
     * Determines whether the error is related to server
     *
     * @param error
     * @return
     */
    protected static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

}
