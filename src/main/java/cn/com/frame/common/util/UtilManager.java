package cn.com.frame.common.util;


/**
 * @author 张奇(Sirius Zhang)
 * 
 * Date : 2010-12-10
 */
public class UtilManager extends AbstractUtilManager{

	/**
	 * 不可创建对象
	 */
	UtilManager(){}
	
	@Override
	public void setParamUtilPageCount(int pageCount){
		if(pageCount == 0) pageCount = 1;
		ParamUtil.setPageCount(pageCount);
	}

}
