/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.converter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 张奇(Sirius Zhang)
 *         <p>
 *         Date : 2011-2-19
 */
@SuppressWarnings("all")
public abstract class AbstractConverter {

    private static Converter converter = new Converter();

    /**
     * 根据指定位数，生成对应的字符串格式
     *
     * @param length 位数
     * @return
     */
    public abstract String formatString(int length, long num);

    /**
     * 强制转换对象为String类型
     *
     * @param obj 对象
     * @return
     */
    public abstract String formatForceToString(Object obj);

    /**
     * 根据*适配字符串
     *
     * @param source 适配字符串
     * @param model  模板字符串
     * @return
     */
    public abstract boolean stringStarModel(String source, String model);

    /**
     * 获取随机UUID
     *
     * @return
     */
    public abstract String getUUID();

    /**
     * 根据指定时间格式将时间字符串转换为时间类型
     *
     * @param dateString 字符串
     * @param format     格式
     * @return
     */
    public abstract Date formatDate(String dateString, String format);

    /**
     * 根据指定日期格式强制转换对象为String
     *
     * @param obj    对象
     * @param format 格式
     * @return
     */
    public abstract String formatForceToString(Object obj, String format);

    /**
     * 时间段内小时数
     *
     * @param begin
     * @param end
     * @return
     */
    public abstract int instanceHour(Date begin, Date end);

    /**
     * 时间段内分钟数
     *
     * @param begin
     * @param end
     * @return
     */
    public abstract int instanceMinutes(Date begin, Date end);

    /**
     * 时间段内秒数
     *
     * @param begin
     * @param end
     * @return
     */
    public abstract int instanceSeconds(Date begin, Date end);

    /**
     * 获取本月第一天
     *
     * @return
     */
    public abstract String getNowMonthFirstDay();

    /**
     * 获取星期几
     *
     * @return
     */
    public abstract String getWeekNow();

    /**
     * 分页
     *
     * @param source 全部信息列表
     * @param start  开始条目
     * @param limit  每页条目
     * @return
     */
    public abstract List forPage(List source, int start, int limit);

    /**
     * 获取指定字符串在原字符串内出现的次数
     *
     * @param chr    指定字符串
     * @param string 原字符串
     * @return
     */

    public abstract int stringMatchNumber(String chr, String string);

    /**
     * 获取唯一的随机数序列
     *
     * @return
     */
    public abstract String uniqueRandom();

    /**
     * 转换List到Map
     *
     * @param list
     * @param key
     * @return
     */
    public abstract Map convertListToMap(List list, String key);


    /**
     * 清除html代码
     *
     * @param source
     * @return
     */
    public abstract String clearHtmlCode(String source);


    /**
     * 转换html代码
     *
     * @param source
     * @return
     */
    public abstract String doHtmlCode(String source);

    /**
     * 获取当前的系统时间yyyy-MM-dd
     */
    public abstract String getNowDateS();

    /**
     * 获取当前的系统时间yyyy-MM-dd HH:mm:ss
     */
    public abstract String getNowDateL();

    /**
     * 清理多余字段
     * @param source
     * @param field
     * @return
     */
    public abstract List clearField(List source, String field);

    /**
     * 清理多余字段
     * @param source
     * @param field
     * @return
     */
    public abstract Map clearField(Map source, String field);
}
