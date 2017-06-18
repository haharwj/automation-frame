package cn.com.frame.common.tools.file;

import java.util.ArrayList;

public class ZipFileList {

	private ArrayList<String> path ;
	private ArrayList<String> fileName ;
	
	public ZipFileList(){
		path = new ArrayList<String>();
		fileName = new ArrayList<String>();
	}
	
	public int getSize(){
		return path.size();
	}
	
	public void addNoCheck(String pathString , String fileNameString){
		path.add(pathString);
		fileName.add(fileNameString);
	}
	
	public String[] getIndex(int index){
		if(index >= path.size()){
			return null;
		}
		String[] result = new String[2];
		result[0] = path.get(index);
		result[1] = fileName.get(index);
		return result;
	}
}
