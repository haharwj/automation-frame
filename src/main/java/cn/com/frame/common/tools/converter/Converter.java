/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.converter;

import cn.com.frame.common.center.builder.SystemBuilder;
import cn.com.frame.common.tools.reflect.CommonReflect;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张奇(Sirius Zhang)
 *         <p>
 *         Date : 2011-2-19
 */
public class Converter extends AbstractConverter {

    @Override
    public String formatString(int length, long num) {
        // TODO Auto-generated method stub
        if (length == 0)
            return null;
        String format = "";
        for (int i = 0; i < length; i++) {
            format += "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(num);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#formatForceToString
     * (java.lang.Object)
     */
    @Override
    public String formatForceToString(Object obj) {
        // TODO Auto-generated method stub
        if (obj == null)
            return null;
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Date) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format((Date) obj);
        }
        if (obj instanceof Long) {
            return ((Long) obj).toString();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).toString();
        }
        if (obj instanceof Double) {
            return ((Double) obj).toString();
        }
        return obj.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#stringStarModel(java
     * .lang.String, java.lang.String)
     */
    @Override
    public boolean stringStarModel(String source, String model) {
        // TODO Auto-generated method stub
        int starIndex = model.indexOf("*");
        if (starIndex == -1)
            return source.equals(model);
        else {

            String head = model.substring(0, starIndex);
            String tail = model.substring(starIndex + 1);
            int headLength = head.length();
            int tailLength = tail.length();
            if (source.length() < headLength || source.length() < tailLength) {
                return false;
            }
            String sourceHead = source.substring(0, headLength);
            String sourceTail = source.substring(source.length() - tailLength,
                    source.length());
            if (head.equals(sourceHead) && tail.equals(sourceTail))
                return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.system.common.tools.converter.AbstractConverter#getUUID()
     */
    @Override
    public String getUUID() {
        // TODO Auto-generated method stub
        return UUID.randomUUID().toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#formatDate(java.lang
     * .String)
     */
    @Override
    public Date formatDate(String dateString, String format) {
        // TODO Auto-generated method stub
        try {
            if (format == null) {
                format = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateString);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#instanceHour(java
     * .util.Date, java.util.Date)
     */
    @Override
    public int instanceHour(Date begin, Date end) {
        // TODO Auto-generated method stub
        if (begin == null && end == null)
            return -1;
        Long l1 = begin.getTime();
        Long l2 = end.getTime();
        return (int) ((l1 - l2) / 3600000);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#instanceMinutes(java
     * .util.Date, java.util.Date)
     */
    @Override
    public int instanceMinutes(Date begin, Date end) {
        // TODO Auto-generated method stub
        if (begin == null && end == null)
            return -1;
        Long l1 = begin.getTime();
        Long l2 = end.getTime();
        int hours = (int) ((l1 - l2) / 3600000);
        return (int) (((l1 - l2) / 1000 - hours * 3600) / 60);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#instanceSeconds(java
     * .util.Date, java.util.Date)
     */
    @Override
    public int instanceSeconds(Date begin, Date end) {
        // TODO Auto-generated method stub
        if (begin == null && end == null)
            return -1;
        Long l1 = begin.getTime();
        Long l2 = end.getTime();
        int hours = (int) ((l1 - l2) / 3600000);
        int minutes = (int) (((l1 - l2) / 1000 - hours * 3600) / 60);
        return (int) ((l1 - l2) / 1000 - hours * 3600 - minutes * 60);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#formatForceToString
     * (java.lang.Object, java.lang.String)
     */
    @Override
    public String formatForceToString(Object obj, String format) {
        // TODO Auto-generated method stub
        if (format == null)
            format = "yyyy-MM-dd HH:MM:ss";
        if (obj == null)
            return null;
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Date) {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format((Date) obj);
        }
        if (obj instanceof Long) {
            return ((Long) obj).toString();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).toString();
        }
        if (obj instanceof Double) {
            return ((Double) obj).toString();
        }
        return obj.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#getNowMonthFirstDay()
     */
    @Override
    public String getNowMonthFirstDay() {
        // TODO Auto-generated method stub
        String nowString = SystemBuilder.getBuilder().getToolsFactory()
                .getConverter().formatForceToString(new Date());
        String[] split = nowString.split("-");
        StringBuffer dateString = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            if (i == 2) {
                String[] timeSplit = split[i].split(" ");
                dateString.append("01 " + timeSplit[1]);
            } else {
                dateString.append(split[i]);
            }
        }
        return dateString.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.system.common.tools.converter.AbstractConverter#getWeekNow()
     */
    @Override
    public String getWeekNow() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String args[]) {
        Converter con = new Converter();
        int length = con.stringMatchNumber("a", "abcasdfa");
        System.out.println(length);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.system.common.tools.converter.AbstractConverter#forPage(int,
     * int)
     */
    @Override
    public List forPage(List source, int start, int limit) {
        // TODO Auto-generated method stub
        if (source == null || limit == 0) {
            return source;
        }

        List result = new ArrayList();
        for (int i = start; i < source.size() && i < start + limit; i++) {
            result.add(source.get(i));
        }
        return result;
    }

    @Override
    public int stringMatchNumber(String chr, String string) {
        int count = 0;
        int m = string.indexOf(chr);
        while (m != -1) {
            m = string.indexOf(chr, m + 1);
            count++;
        }
        return count;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.system.common.tools.converter.AbstractConverter#uniqueRandom()
     */
    @Override
    public String uniqueRandom() {
        // TODO Auto-generated method stub
        String d = new Double(Math.random()).toString();
        return new SimpleDateFormat("yyyyMMddhhmmsssss").format(new Date())
                + (d.length() > 9 ? d.substring(2, 9) : d.substring(2,
                d.length()));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.system.common.tools.converter.AbstractConverter#convertListToMap(
     * java.util.List, java.lang.String)
     */
    @Override
    public Map convertListToMap(List list, String key) {
        // TODO Auto-generated method stub
        if (list.size() <= 0) {
            return new HashMap();
        }
        CommonReflect reflect = new CommonReflect();
        reflect.setTableName(list.get(0));
        LinkedHashMap newMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Object temp = it.next();
            Object keytemp = reflect.invokeGetMethod(temp, key);
            if (keytemp == null) {
                continue;
            }
            if (!(keytemp instanceof String)) {
                keytemp = keytemp.toString();
            }
            newMap.put(keytemp, temp);
        }
        return newMap;
    }

    @Override
    public String clearHtmlCode(String htmlStr) {
        // TODO Auto-generated method stub
        Pattern p_script = Pattern
                .compile("<script[^>]*?>[\\s\\S]*?<\\/script>",
                        Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>",
                Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile("\\s*|\t|\r|\n",
                Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        // 过滤特殊字符
        htmlStr = htmlStr.replaceAll("&gt;", ">");
        htmlStr = htmlStr.replaceAll("&lt;", "<");
        htmlStr = htmlStr.replaceAll("&nbsp;", " ");
        htmlStr = htmlStr.replaceAll(" &nbsp;", " ");
        htmlStr = htmlStr.replaceAll("&quot;", "\"");
        htmlStr = htmlStr.replaceAll("&#39;", "\'");
        htmlStr = htmlStr.replaceAll("<br/> ", "\n");
        return htmlStr.trim(); // 返回文本字符�?
    }

    @Override
    public String doHtmlCode(String source) {
        // TODO Auto-generated method stub
        return source.replaceAll("\n", "<br/>").replace("/r/n", "<br/>")
                .replace("/n/r", "<br/>");

    }

    public String getNowDateS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public String getNowDateL() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    @Override
    public List clearField(List source, String field) {
        List result = new ArrayList();
        for (Iterator it = source.iterator(); it.hasNext(); ) {
            Map temp = (Map) it.next();
            temp = this.clearField(temp, field);
            result.add(temp);
        }
        return result;
    }

    @Override
    public Map clearField(Map source, String field) {
        Map result = new HashMap();
        String[] split = field.split(",");
        for (int i = 0; i < split.length; i++) {
            Object temp = source.get(split[i]);
            if (temp != null) {
                result.put(split[i], temp);
            }
        }
        return result;
    }
}
