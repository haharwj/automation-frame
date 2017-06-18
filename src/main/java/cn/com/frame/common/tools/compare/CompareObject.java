package cn.com.frame.common.tools.compare;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings("all")
public class CompareObject implements Comparator {

    private Object obj;
    private String[] sortArray;
    private Map<String, Method> fm;
    private String type;
    private Map attributeMap;
    private List<String> modelSort;
    private Map privateMap;

    public CompareObject() {
    }

    public CompareObject(Object o, String[] sortFields) {
        obj = o;
        sortArray = sortFields;
        fm = new HashMap<String, Method>();
        String oname = o.getClass().getName();
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1);
            Method method = null;
            try {
                method = c.getDeclaredMethod(methodName, null);
            } catch (Exception e) {
                continue;
            }
            fm.put(fieldName, method);
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        // TODO Auto-generated method stub
        int flag = 0;
        try {
            // 根据特殊类型比较
            if (type != null && type.equals("文号") && attributeMap != null) {
                String head = (String) attributeMap.get("head");
                String content1 = (String) o1;
                String content2 = (String) o2;
                if (content1.indexOf(head) != -1
                        && content2.indexOf(head) != -1) {
                    String numberString1 = content1.substring(
                            content1.indexOf(head) + head.length(),
                            content1.length() - 1);
                    String numberString2 = content2.substring(
                            content2.indexOf(head) + head.length(),
                            content2.length() - 1);
                    try {
                        int numberconvert1 = Integer.parseInt(numberString1);
                        int numberconvert2 = Integer.parseInt(numberString2);
                        return numberconvert1 - numberconvert2;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (type != null && type.equals("模板")) {
                Object obj1 = fm.get(sortArray[0]).invoke(o1, null);
                Object obj2 = fm.get(sortArray[0]).invoke(o2, null);
                if ((obj1 instanceof String) && (obj1 instanceof String)) {
                    if (privateMap.get((String) obj1) != null
                            && privateMap.get((String) obj2) != null) {
                        return (Integer) privateMap.get((String) obj1)
                                - (Integer) privateMap.get((String) obj2);
                    } else {
                        return ((String) obj1).compareTo(((String) obj2));
                    }
                }
            }
            Object obj1 = fm.get(sortArray[0]).invoke(o1, null);
            if (obj1 instanceof String) {
                flag = ((String) obj1).compareTo((String) fm.get(sortArray[0])
                        .invoke(o2, null));
            }
            if (obj1 instanceof Integer) {
                flag = (Integer) obj1
                        - (Integer) fm.get(sortArray[0]).invoke(o2, null);
            }
            if (obj1 instanceof Long) {
                flag = Integer.parseInt(String.valueOf(((Long) obj1 - (Long) fm
                        .get(sortArray[0]).invoke(o2, null))));
            }
            if (obj1 instanceof java.util.Date) {
                flag = Integer.parseInt(String.valueOf(((java.util.Date) obj1)
                        .getTime()
                        - ((java.util.Date) fm.get(sortArray[0]).invoke(o2,
                                null)).getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 1; i < sortArray.length; i++) {
            if (flag == 0) {
                try {
                    Object obji1 = fm.get(sortArray[i]).invoke(o1, null);
                    if (obji1 instanceof String) {
                        flag = ((String) obji1).compareTo((String) fm.get(
                                sortArray[i]).invoke(o2, null));
                    }
                    if (obji1 instanceof Integer) {
                        flag = (Integer) obji1
                                - (Integer) fm.get(sortArray[i]).invoke(o2,
                                        null);
                    }
                    if (obji1 instanceof Long) {
                        flag = Integer.parseInt(String
                                .valueOf(((Long) obji1 - (Long) fm.get(
                                        sortArray[i]).invoke(o2, null))));
                    }
                    if (obji1 instanceof java.util.Date) {
                        flag = Integer.parseInt(String
                                .valueOf(((java.util.Date) obji1).getTime()
                                        - ((java.util.Date) fm
                                                .get(sortArray[i]).invoke(o2,
                                                        null)).getTime()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map attributeMap) {
        this.attributeMap = attributeMap;
    }

    public String[] getSortArray() {
        return sortArray;
    }

    public void setSortArray(String[] sortArray) {
        this.sortArray = sortArray;
    }

    public List<String> getModelSort() {
        return modelSort;
    }

    public void setModelSort(List<String> modelSort) {
        this.modelSort = modelSort;
        privateMap = new HashMap();
        int index = 0;
        for (Iterator<String> it = modelSort.iterator(); it.hasNext(); index++) {
            String temp = it.next();
            privateMap.put(temp, index);
        }
    }

}
