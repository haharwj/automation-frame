/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.builder;

import cn.com.frame.common.center.factory.AbstractManagersFactory;
import cn.com.frame.common.center.factory.AbstractToolsFactory;

import java.lang.reflect.Constructor;


/**
 * @author 张奇(Sirius Zhang)
 * 
 * Factory生成类
 * 
 * Date : 2010-11-30
 */
@SuppressWarnings( { "all" })
public class SystemBuilder extends AbstractBuilder{

    /**
     * 封装模式
     */
    SystemBuilder(){
    	try{
			//ToolsFactory创建子
			Constructor cts = Class.forName("cn.com.frame.common.center.factory.ToolsFactory").getDeclaredConstructor(null);
			cts.setAccessible(true);
			Object factoryInstance = cts.newInstance(null);
			toolsFactory = (AbstractToolsFactory)factoryInstance;
			//ManagersFactory创建子
			cts = Class.forName("cn.com.frame.common.center.factory.ManagersFactory").getDeclaredConstructor(null);
			cts.setAccessible(true);
			factoryInstance = cts.newInstance(null);
			managersFactory = (AbstractManagersFactory)factoryInstance;
			
		}catch(Exception e){
			e.printStackTrace();
		}
    	
    }
    /* (non-Javadoc)
     * @see com.j2ee.tools.center.builder.AbstractBuilder#getBuilder()
     */
    public static AbstractBuilder getBuilder() {
        // TODO Auto-generated method stub
        return builder;
    }

    /* (non-Javadoc)
     * @see com.j2ee.tools.center.builder.AbstractBuilder#getToolsFactroy()
     */
    @Override
    public AbstractToolsFactory getToolsFactory() {
        // TODO Auto-generated method stub
        return toolsFactory;
    }
	@Override
	public AbstractManagersFactory getManagersFactory() {
		// TODO Auto-generated method stub
		return managersFactory;
	}
    
    /**
     * */

    public static void main(String args[]){
    	Object obj = SystemBuilder.getBuilder().getToolsFactory().getXMLTools();
    	System.out.println(obj.getClass().getName());
    	obj = SystemBuilder.getBuilder().getManagersFactory().getUtilManager();
    	System.out.println(obj.getClass().getName());
    }     

}
