package com.generallibrary.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by feng on 2016/10/12.
 * 校验手机号和身份证号
 */
public class LibJudgePhone {

    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
　　 联通：130、131、132、152、155、156、185、186
　　 电信：133、153、180、189、（1349卫通）
     */
    public static boolean judgePhone(String phoneNum) {
//        String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
//        Pattern p = Pattern.compile(regExp);
//        Matcher m = p.matcher(phoneNum);
//        return m.matches();
        Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
        Matcher m = p.matcher(phoneNum);
        return m.find();
    }

    public static boolean checkPhone(String phone) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String phones = "[1][3456789]\\d{9}";
        return !TextUtils.isEmpty(phone) && phone.matches(phones);
    }

    public static void main(String[] args) {
        String s = "18310687925";
        System.out.println(judgePhone(s));
    }
}
