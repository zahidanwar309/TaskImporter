/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crowdlab.taskimporter.utils;

import android.util.Log;
import com.crowdlab.taskimporter.activities.BuildConfig;

/**
 *
 * @author anwar
 * 
 * Wrapper Log class
 * 
 * You have the flexibility to print log on debug version of the app only
 * 
 * Or you can use any flag to print log
 * 
 * Easy to extend in future, if you would like to send exception on google/flurry analytics
 * 
 */

public class Logger {
    
    private static final boolean debug = BuildConfig.DEBUG;
    private static final String TAG = "taskimporter";
    
    public static void d(String msg) {
        
        if(debug)
            Log.d(TAG, msg);
    }
    
    public static void e(String msg) {
        
        if(debug)
            Log.e(TAG, msg);
    }
    
    public static void e(String msg, Exception e) {
        
        if(debug)
            Log.e(TAG, msg, e);
    }
    
    public static void w(String msg) {
        
        if(debug)
            Log.w(TAG, msg);
    }
    
    
    public static void i(String msg) {
        
        if(debug)
            Log.i(TAG, msg);
    }
}
