// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ManagersFactory.java

package cn.com.frame.common.center.factory;

import cn.com.frame.common.util.AbstractUtilManager;
import cn.com.frame.common.util.UtilManager;

import java.lang.reflect.Constructor;

// Referenced classes of package cn.com.frame.center.factory:
//            AbstractManagersFactory

class ManagersFactory extends AbstractManagersFactory
{

    ManagersFactory()
    {
        try
        {
            Constructor cts = Class.forName("cn.com.frame.common.util.UtilManager").getDeclaredConstructor(null);
            cts.setAccessible(true);
            Object xmlManagersInstance = cts.newInstance(null);
            utilManager = (UtilManager)xmlManagersInstance;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public AbstractUtilManager getUtilManager()
    {
        return utilManager;
    }
}
