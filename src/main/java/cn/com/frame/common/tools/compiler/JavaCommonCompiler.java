/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.compiler;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.net.URI;
import java.util.Arrays;

/**
 * Java编译器操作
 * 
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-8-20
 */
public class JavaCommonCompiler extends AbstractJavaCompiler {
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.compiler.AbstractJavaCompiler#findClass(java.
     * lang.String)
     */
    @Override
    public Class<?> findClass(String str) throws ClassNotFoundException {
        // TODO Auto-generated method stub
        if(classMap.get(str) != null){
            return loadClass(str);
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 内存中的源代码保存在一个从JavaFileObject继承的类中
        JavaFileObject file = new JavaSourceFromString(str, str);
        Iterable compilationUnits = Arrays.asList(file);
        // 建立一个编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, null,
                null, null, compilationUnits);
        // 编译内存中的源程序
        boolean result = task.call();
        // 编译成功
        if (result) {
            // 加载内存中的class
            classMap.put(str, 1);
            return loadClass(str);
        }
        return null;
    }

    /**
     * 调用编译器编译源代码
     * 
     * @param str
     *            调用编译器
     * @return
     */
    public Class compilerSource(String str) {
        if (str == null || str == "") {
            return null;
        }
        // 调用加载器加载类文件
        try {
            Class tempclass = this.findClass(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

class JavaSourceFromString extends SimpleJavaFileObject {
    private String code;

    public JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/')
                + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}
