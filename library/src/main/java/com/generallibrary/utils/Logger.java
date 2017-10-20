package com.generallibrary.utils;

import android.text.TextUtils;
import android.util.Log;

import com.generallibrary.base.DifDefine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Li DaChang on 16/7/21.
 * ..-..---.-.--..---.-...-..-....-.
 * 最新Log工具类!功能更贴心!
 * 20170119:现在添加了合并对象串的方法,更强大啦
 */
public class Logger {
    private static final String DEFAULT_TAG = "+--+";
    private static final String SDK_LOG_FILE = "sdkLog.txt";
    private static final String SSSS_TAG = "ssss";//贤哥的定制tag,对应编号0
    private static final String DC_TAG = "----";//达畅的定制tag,对应编号1
    private static final String DC_TAG_2 = "-++-";//达畅的定制tag2,编号32
    private static final String ZHONG_XIA_TAG = "feng";//中霞的定制tag,对应编号2
    private static final String MAGINA = "magina";//高飞的定制tag,对应编号4
    private static final String DDDDD_TAG = "dddd";
    private static final String ZZL_TAG = "cscs"; //宗志龙的Tag,对应编号6

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 2;


//    public Logger() {
//        mIsDebug = ApplicationBase.getInstance().getIsDebug();
//        mIsLogToFile = ApplicationBase.getInstance().getIsLogToFile();
//    }

    /**
     * 原始打印方法
     *
     * @param tag flag
     * @param msg 信息
     */
    private static void takeLogI(String tag, String className, String msg) {
        if (DifDefine.mIsDebug) {
            Log.i(tag + "--", className + msg);
        }
    }

    private static void takeLogD(String tag, String className, String msg) {
        if (DifDefine.mIsDebug) {
            Log.d(tag + "--", className + msg);
        }
    }

    private static void takeLogW(String tag, String className, String msg) {
        if (DifDefine.mIsDebug) {
            Log.w(tag + "--", className + msg);
        }
    }

    private static void takeLogE(String tag, String className, String msg) {
        if (DifDefine.mIsDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * 用于拼接各种串
     *
     * @param value 对象串
     * @return String
     */
    private static String convertValues(Object... value) {
        if (value == null) {
            return null;
        }
        if (value.length == 1) {
            return String.valueOf(value[0]);
        }
        String conVal = "";
        if (value.length == 2) {
            conVal = conVal + value[0] + " : " + value[1];
        } else {
            for (Object o : value) {
                conVal = TextUtils.concat(conVal, String.valueOf(o), " -- ").toString();
            }
        }
        return conVal;
    }

    public static void d(String msg) {
        takeLogD(DEFAULT_TAG, getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("d", DEFAULT_TAG, msg);
        }
    }

    public static void d(int tag, String msg) {
        takeLogD(getTag(tag), getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("d", getTag(tag), msg);
        }
    }

    public static void d(String tag, String msg) {
        takeLogD(tag, getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("d", tag, msg);
        }
    }

    public static void d(int flag, Object... value) {
        takeLogD(getTag(flag), getClassName(), convertValues(value));
    }

    public static void d(String tag, Object... value) {
        takeLogD(tag, getClassName(), convertValues(value));
    }

    public static void i(String msg) {
        takeLogI(DEFAULT_TAG, getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("i", DEFAULT_TAG, msg);
        }
    }

    public static void i(int flag, String msg) {
        takeLogI(getTag(flag), getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("i", getTag(flag), msg);
        }
    }

    public static void i(String tag, String msg) {
        takeLogI(tag, getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("i", tag, msg);
        }
    }

    public static void i(int flag, Object... value) {
        takeLogI(getTag(flag), getClassName(), convertValues(value));
    }

    public static void i(String tag, Object... value) {
        takeLogI(tag, getClassName(), convertValues(value));
    }


    /**
     * 分步debug用的
     *
     * @param num 数字
     */
    public static void i(int num) {
        takeLogI(DEFAULT_TAG, getClassName(), "-------------------" + num);
    }

    /**
     * 根据传入的flag序号返回对应的定制tag
     *
     * @param flag 序号  0:SSSS_TAG,贤哥的   1:DC_TAG,达畅的  2:ZHONG_XIA_TAG,中霞的  （～达畅好偏心，我也要我也要～）(都说了可以随意添加啊,已经替卢骁加上了) 其他人可以任意添加
     *             20160721更新:添加双位tag,以十位数区分每个人,以个位区分每人不同的tag,如:11:贤哥的常用tag,12:贤哥的另一个tag
     *             10~19:贤哥的;20~29:中霞的;30~39:达畅的;40~49:高飞的;50~59:土燊
     *             具体的tag请自行添加
     * @return 对应的tag
     */
    private static String getTag(int flag) {
        switch (flag) {
            case 0:
                return SSSS_TAG;//贤哥的
            case 1:
                return DC_TAG;//达畅的
            case 2:
                return ZHONG_XIA_TAG;//中霞的
            case 4:
                return MAGINA;//高飞的
            case 6:
                return ZZL_TAG;
            case 11:
                return DDDDD_TAG;//贤哥的另一个
            case 21:
                return ZHONG_XIA_TAG;
            case 22:
                return ZHONG_XIA_TAG;
            case 33:
                return DC_TAG;
            case 32:
                return DC_TAG_2;
            case 41:
                return MAGINA;
            case 44:
                return MAGINA;
            default:
                return DEFAULT_TAG;//默认的
        }
    }

    public static void w(String msg) {
        takeLogW(DEFAULT_TAG, getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("w", DEFAULT_TAG, msg);
        }
    }

    public static void w(int flag, String msg) {
        takeLogW(getTag(flag), getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("w", getTag(flag), msg);
        }
    }

    public static void w(String tag, String msg) {
        takeLogW(tag, getClassName(), msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("w", tag, msg);
        }
    }

    public static void w(int flag, Object... value) {
        takeLogW(getTag(flag), getClassName(), convertValues(value));
    }

    public static void w(String tag, Object... value) {
        takeLogW(tag, getClassName(), convertValues(value));
    }

    public static void e(String msg) {
        takeLogE(DEFAULT_TAG, getClassName(), getMethodName() + msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("e", DEFAULT_TAG, msg);
        }
    }

    public static void e(int flag, String msg) {
        takeLogE(getTag(flag), getClassName(), getMethodName() + ":" + msg);
        if (DifDefine.mIsLogToFile) {
            writeToFile("e", DEFAULT_TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DifDefine.mIsDebug) {
            takeLogE(tag, getClassName(), getMethodName() + msg);
            if (DifDefine.mIsLogToFile) {
                writeToFile("e", tag, getClassName() + msg);
            }
        }
    }

    public static void e(int flag, Object... value) {
        takeLogE(getTag(flag), getClassName(), convertValues(value));
    }

    public static void e(String tag, Object... value) {
        takeLogE(tag, getClassName(), convertValues(value));
    }

    public static void json(int tagFlag, String jsonStr) {
        if (DifDefine.mIsDebug) {
            while (jsonStr.length() > 3950) {
                int subStringIndex = jsonStr.lastIndexOf(",", 3950);
                if (subStringIndex == -1) {
                    subStringIndex = 3950;
                    takeLogI(getTag(tagFlag), getClassName(), jsonStr.substring(0, subStringIndex));
                    jsonStr = jsonStr.substring(subStringIndex).trim();
                }
                takeLogI(getTag(tagFlag), getClassName(), jsonStr);
            }
        }
    }

    /**
     * 打印分割线,主要用于程序启动或销毁时在log中做标记
     *
     * @param msg1 信息
     * @param time 当前时间
     */
    public static void line(String msg1, String time) {
        if (DifDefine.mIsDebug)
            Log.i(DEFAULT_TAG,
                    "---====================================---\n" +
                            "\t我是" + msg1 + "的分割线-" + time +
                            "\n---====================================---");
    }

    public static void logMatch(String msg) {
        logMatch(DEFAULT_TAG, msg);
    }

    public static void logMatch(String tag, String msg) {
        if (DifDefine.mIsDebug) {
            Log.i(tag, msg);
            if (DifDefine.mIsLogToFile) {
                writeToFile("Match", tag, msg);
            }
        }
    }

    public static void writeToFile(final String level, final String flag, final String msg1) {
        DifWorker.getInstance().executeTask(new Runnable() {
            @Override
            public void run() {
                if (DifCommonUtils.initPath(DifDefine.mPathLog)) {
                    String msg = DifDateUtils.formatTime(System.currentTimeMillis()) + "  " + level + "   " + flag + "    " + msg1 + "\n";
                    File log = new File(DifDefine.mPathLog, SDK_LOG_FILE);
                    FileWriter fw = null;
                    try {
                        fw = new FileWriter(log, true);
                        fw.write(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fw != null) {
                            try {
                                fw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * 打印的时候带上类名什么的最爽了
     *
     * @return 类名前缀
     */
    public static String getClassName() {
        if (DifDefine.mIsDebug) {
            StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
            String className = traceElement.getFileName();
            //去除文件名中的后缀
            if (className != null) {
                if (className.contains(".java")) {
                    className = className.substring(0, className.length() - 5);
                }

                if (className.length() > 18) {
                    className = className.substring(0, 17) + "...";
                }
            }

            className = String.format("%-20s", className);//补位,不足20位的用空格填充
            className = className + ": ";
            return className;
        } else {
            return null;
        }
    }

    public static String getMethodName() {
        if (DifDefine.mIsDebug) {

            StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
            String className = traceElement.getFileName();
            if (className.contains(".java")) {
                className = className.substring(0, className.length() - 5);
            }
            String methodName = traceElement.getMethodName();
            return className + "." + methodName;
        } else {
            return null;
        }
    }

    public static String getLineMethod() {
        if (DifDefine.mIsDebug) {
            StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
            String className = traceElement.getFileName();
            if (className.contains(".java")) {
                className = className.substring(0, className.length() - 5);
            }
            return className + "." + traceElement.getMethodName() + "-" + traceElement.getLineNumber();
        } else {
            return null;
        }
    }
}
