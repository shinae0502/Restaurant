package com.study.restaurant.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class LOG {

    public static void d(Object str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];
            Log.d("exceptiontag", currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + str);
        }

    }

    public static void i(Object str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];
            Log.i("exception", currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + str);
        }

    }

    public static void t(Context c, Object str) {
        {
            Toast.makeText(c, "" + str, Toast.LENGTH_LONG).show();
        }
    }

    public static void api(String str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];

            StringBuffer buffer = new StringBuffer();
            str = str.replace("\"", "")
                    .replace(",", "\n")
                    .replace("{", "")
                    .replace("}", "")
                    .replace("\\u003d", "=");

            buffer.append(str + "\n" + "//info");

            Log.d("exceptiontag", currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + buffer.toString());
        }
    }
}
