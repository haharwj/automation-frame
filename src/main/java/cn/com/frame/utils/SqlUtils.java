package cn.com.frame.utils;

import cn.com.frame.common.tools.reflect.CommonReflect;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtils {

    /**
     * 转换javaboolean与mysql的int值
     *
     * @param intBoolean
     * @return
     */
    public static boolean toBoolean(int intBoolean) {
        return intBoolean == 1;
    }


    public static int toInt(boolean booleanValue) {
        return booleanValue ? 1 : 0;
    }

    /**
     * sql语句检测
     * 检查condition,去掉语句中数据库中不包含的字段
     *
     * @param condition,commonInstance
     * @return condition
     *
     */
    public String checkSql(String condition, Object commonInstance) {
        CommonReflect commonReflect = new CommonReflect();
        if (commonInstance == null)
            return "";
        commonReflect.setTableName(commonInstance);
        Map tableMap = (Map) commonReflect.findFieldMap();
        char[] sqlChar = condition.toCharArray();
        int left_bracket_num = 0;//左括号数
        int left_singlemark_num = 0;//单引号数
        Map value_map = new HashMap();
        String tempValue = "";
        int parms_num = 0;
        //检查语句
        for (char c : sqlChar) {
            //检测括号
            if (c == '(') {
                left_bracket_num++;
            } else if (left_bracket_num > 0 && c == ')') {
                left_bracket_num--;
            }
            //检测引号
            if (c == '\'') {
                left_singlemark_num++;
            }
            if (left_singlemark_num % 2 == 1) {
                tempValue += String.valueOf(c);
            } else {//将value转变为params
                if (!tempValue.equals("")) {
                    tempValue += "'";
                    value_map.put("params_[" + parms_num + "]", tempValue);
                    condition = condition.replace(tempValue, "params_[" + parms_num + "]");
                    parms_num++;
                }
                tempValue = "";
            }
        }
        if (left_bracket_num > 0) {
            System.out.println("检测到语句缺少括号！");
            return null;
        }
        if (left_singlemark_num % 2 != 0) {
            System.out.println("检测到语句缺少引号！");
            return null;
        }
        Pattern pattern = Pattern.compile("(and|or)+");
        String[] splitcondionArr = pattern.split(condition);
        for (int i = 0; i < splitcondionArr.length; i++) {
            String splitconditon = splitcondionArr[i];
            String fieldname = "";
            Pattern fieldpattern = Pattern.compile("([A-Za-z0-9_]+)[\\s|=|>|<]");
            Matcher m = fieldpattern.matcher(splitconditon);
            m.find();
            fieldname = m.group(1);
            if (tableMap.get(fieldname) == null) {
                System.out.println("数据库表中不含有字段:" + fieldname);
                //从condition中去掉
                int left_mark = 0;
                int right_mark = 0;
                for (char sc : splitconditon.toCharArray()) {
                    if (sc == '(') {
                        left_mark++;
                    } else if (sc == ')') {
                        right_mark++;
                    }
                }
                String replacestr = " ";
                if (left_mark > right_mark) {
                    for (int s = 0; s < left_mark - right_mark; s++) {
                        replacestr += "(";
                    }
                    splitconditon = splitconditon.replace("(", "\\(");
                    splitconditon = splitconditon.replace(")", "\\)");
                    splitconditon = splitconditon.replace("[", "\\[");
                    splitconditon = splitconditon.replace("]", "\\]");
                    condition = condition.replaceAll(splitconditon.trim() + "\\s*([and|or])*", replacestr).trim();
                } else {
                    for (int s = 0; s < right_mark - left_mark; s++) {
                        replacestr += ")";
                    }
                    splitconditon = splitconditon.replace("(", "\\(");
                    splitconditon = splitconditon.replace(")", "\\)");
                    splitconditon = splitconditon.replace("[", "\\[");
                    splitconditon = splitconditon.replace("]", "\\]");
                    condition = condition.replaceAll("([and|or])*\\s*" + splitconditon.trim(), replacestr).trim();
                }
            }
        }
        //整理sql语句
        //去掉仅是括号的表达式
        System.out.println(condition);
        String[] scconditionArr = pattern.split(condition);
        for (int i = 0; i < scconditionArr.length; i++) {
            String scc = scconditionArr[i];
            if (scc.replaceAll("\\(", "").replaceAll("\\)", "").trim().equals("")) {
                int left_mark = 0;
                int right_mark = 0;
                for (char c : scc.toCharArray()) {
                    if (c == '(') {
                        left_mark++;
                    } else if (c == ')') {
                        right_mark++;
                    }
                }
                String replacestr = " ";
                if (left_mark > right_mark) {
                    for (int s = 0; s < left_mark - right_mark; s++) {
                        replacestr += "(";
                    }
                    scc = scc.replace("(", "\\(");
                    scc = scc.replace(")", "\\)");
                    condition = condition.replaceAll(scc.trim() + "\\s*([and|or])*", replacestr).trim();
                } else {
                    for (int s = 0; s < right_mark - left_mark; s++) {
                        replacestr += ")";
                    }
                    scc = scc.replace("(", "\\(");
                    scc = scc.replace(")", "\\)");
                    System.out.println("([and|or])*\\s*" + scc.trim());
                    condition = condition.replaceAll("([and|or])*\\s*" + scc.trim(), replacestr).trim();
                }

            }
        }
        //去掉多余括号
        String[] sccondArr = pattern.split(condition);
        for (int i = 0; i < sccondArr.length; i++) {
            String scc = sccondArr[i];
            int left_mark = 0;
            int right_mark = 0;
            for (char c : scc.toCharArray()) {
                if (c == '(') {
                    left_mark++;
                } else if (c == ')') {
                    right_mark++;
                }
            }
            //是否有多重括号
            if (left_mark > 1 && right_mark > 1) {
                String replacestr = " ";
                String rs = scc.replace("(", "").replace(")", "").trim();
                if (left_mark > right_mark) {
                    for (int s = 0; s < left_mark - right_mark; s++) {
                        replacestr += "(";
                    }
                    rs = replacestr + rs;
                    condition = condition.replace(scc, rs).trim();
                } else {
                    for (int s = 0; s < right_mark - left_mark; s++) {
                        replacestr += ")";
                    }
                    rs = replacestr + rs;
                    condition = condition.replace(scc, rs).trim();
                }
            }
        }
        //变量赋值
        for (Iterator it = value_map.keySet().iterator(); it.hasNext(); ) {
            String paramName = (String) it.next();
            String paramValue = (String) value_map.get(paramName);
            condition = condition.replace(paramName, paramValue);
        }
        return condition;

    }
}
