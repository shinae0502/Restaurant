package com.study.restaurant.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 태그 설계
 * 1. 파일 쓰기 기능 날짜별로
 * 2. v, d, i, w, e 기능 정리
 * <p>
 * 로그의 우선순위
 * 우선순위는 다음 값 중 하나입니다.
 * V — Verbose (가장 낮은 우선순위)
 * D — Debug
 * I — Info
 * W — Warning
 * E — Error
 * A — Assert
 * <p>
 * Log level 메뉴에서 다음 값 중 하나를 선택합니다.
 * Verbose - 모든 로그 메시지를 표시합니다(기본 설정).
 * Debug - 개발 중에만 유용한 디버그 로그 메시지뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
 * Info - 일반적인 사용에 대해 예상할 수 있는 로그 메시지뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
 * Warn - 아직 오류는 아니지만 발생 가능한 문제뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
 * Error - 오류를 일으킨 문제뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
 * Assert - 개발자가 결코 발생해서는 안 된다고 생각하는 문제를 표시합니다.
 */
public class Logger {


    public static void writeFile(String data) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String filename = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            File folder = new File("/sdcard/bananaplate");
            folder.mkdirs();
            File file = new File("/sdcard/bananaplate/" + filename + ".txt");
            FileOutputStream fileinput = new FileOutputStream(file);
            PrintStream printstream = new PrintStream(fileinput);
            printstream.print(data + "\n");
            fileinput.close();
        } catch (Exception e) {
        }
    }

    /**
     * V — Verbose (가장 낮은 우선순위)
     * Verbose - 모든 로그 메시지를 표시합니다(기본 설정).
     *
     * @param str
     */
    public static void v(Object str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];
            String log = currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + str;
            Log.v(currentStack.getFileName(), log);
            writeFile(log);
        }
    }

    /**
     * D — Debug
     * Debug - 개발 중에만 유용한 디버그 로그 메시지뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
     *
     * @param str
     */
    public static void d(Object str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];
            String log = currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + str;
            Log.d(currentStack.getFileName(), log);
            writeFile(log);
        }
    }

    /**
     * I — Info
     * Info - 일반적인 사용에 대해 예상할 수 있는 로그 메시지뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
     */
    public static void i(Object str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];
            String log = currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + str;
            Log.i(currentStack.getFileName(), log);
            writeFile(log);
        }

    }

    /**
     * W — Warning
     * Warn - 아직 오류는 아니지만 발생 가능한 문제뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
     *
     * @param str
     */
    public static void w(Object str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];
            String log = currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + str;
            Log.w(currentStack.getFileName(), log);
            writeFile(log);
        }
    }

    /**
     * E — Error
     * Error - 오류를 일으킨 문제뿐 아니라 더 낮은 레벨의 메시지도 이 목록에 표시합니다.
     *
     * @param str
     */
    public static void e(Object str) {
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement currentStack = stack[1];
            String log = currentStack.getFileName() + "::" + currentStack.getMethodName() + "(" + currentStack.getLineNumber() + ")" + ": " + str;
            Log.e(currentStack.getFileName(), log);
            writeFile(log);
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
