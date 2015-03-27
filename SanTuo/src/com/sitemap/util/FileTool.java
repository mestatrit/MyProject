package com.sitemap.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileTool {

	private static String getPath() throws Exception {

		String path = "";
		path = FileTool.class.getClassLoader().getResource("").toString();
		path = path.substring(path.indexOf("/") + 1).split("WEB-INF")[0] + "sitefiles/";
		return path;
	}

	/** 
	* 移动文件 
	* @param srcFileName 	源文件完整路径
	* @param destDirName 	目的目录完整路径
	* @return 文件移动成功返回true，否则返回false 
	*/  
	public static boolean moveFile(String srcFileName, String destDirName) {
		
		File srcFile = new File(srcFileName);
		if(!srcFile.exists() || !srcFile.isFile()) 
		    return false;
		String path=destDirName.substring(0,destDirName.lastIndexOf("\\"));
		System.out.println("path: "+path);
		File destDir = new File(path);
		if (!destDir.exists())
			destDir.mkdirs();
		return srcFile.renameTo(new File(destDirName));
	}
	
	public static String getPublicPath(String tempPath) {

		String path = FileTool.class.getClassLoader().getResource("").toString();
		path = path.substring(path.indexOf("/") + 1).split("webapps")[0] + "CGS/";
		if (path.indexOf(":") == -1)
			path = "/" + path;
		return path;
	}

	public static String getPublicPathDouble(Class<?> cs, String fileName) throws Exception {

		String classPath = cs.getName();
		classPath = classPath.replace(".", "/");
		String path = cs.getClassLoader().getResource("").toString();
		path = path.substring(path.indexOf("/") + 1);
		classPath = classPath.substring(0, classPath.lastIndexOf("/"));
		path = path + classPath + "/" + fileName;
		return "/" + path;
	}

	public static List<String> getFileInfos(String filename) {
		System.out.println(filename);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		List<String> list = new ArrayList<String>();
		try {
			File file = new File(filename);
			File[] files = file.listFiles();
			String tname = "", time = "";
			long size = 0;
			for (int i = 0; i < files.length; i++) {
				tname = files[i].getName();
				time = sdf.format(new Date(files[i].lastModified()));
				if (files[i].isFile()) {
					size = files[i].length();
					if (size == 0) {
						tname = "1;" + tname + ";0G;" + time;
					} else {
						tname = "1;" + tname + ";" + FormetFileSize(size) + ";" + time;
					}
				} else {
					tname = "2;" + tname + ";--;" + time;
				}
				list.add(tname);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
	
	public static List<String> getFiles(String filename) {
		System.out.println(filename);
		List<String> list = new ArrayList<String>();
		try {
			File file = new File(filename);
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if(files[i].length()>0)
				list.add(files[i].getName());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}

	public static String FormetFileSize(float value) {

		String fileSizeString = "";
		if (value < 1024) {
			fileSizeString = value + "B";
		} else if (value < 1048576) {
			if (value % 1024 == 0)
				fileSizeString = (long) value / 1024 + "KB";
			else
				fileSizeString = new BigDecimal(value / 1024).setScale(2, 4) + "KB";
		} else if (value < 1073741824) {
			if (value % 1048576 == 0)
				fileSizeString = (long) value / 1048576 + "MB";
			else
				fileSizeString = new BigDecimal(value / 1048576).setScale(2, 4) + "MB";
		} else {
			if (value % 1073741824 == 0)
				fileSizeString = (long) value / 1073741824 + "G";
			else
				fileSizeString = new BigDecimal(value / 1073741824).setScale(2, 4) + "G";
		}
		return fileSizeString;
	}

	public static int addfile(String path) {

		try {
			File file = new File(getPath() + path);
			if (!file.exists()) {
				file.mkdirs();
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int deletefile(String path, int flag) {

		try {
			String[] paths = path.split(";");
			for (int a = 0; a < paths.length; a++) {
				String tpath = paths[a];
				if (flag == 1) {
					tpath = getPath() + tpath;
				}
				File file = new File(tpath);
				if (file.exists()) {
					File[] ff = file.listFiles();
					for (int i = 0; i < ff.length; i++) {
						if (ff[i].isFile()) {
							ff[i].delete();
						} else if (ff[i].isDirectory()) {
							deletefile(ff[i].getAbsolutePath().replace(".lnk", ""), 2);
						}
						ff[i].delete();
					}
					file.delete();
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int mingalert(String path, String filename) {

		try {
			File oldFile = new File(getPath() + path + "/" + filename.split(";")[0]);
			if (oldFile.exists()) {
				String rootPath = oldFile.getParent();
				File newFile = new File(rootPath + File.separator + filename.split(";")[1]);
				if (oldFile.renameTo(newFile)) {
					return 1;
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static long getFileSize(File f)// 取得文件夹大小
	{

		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}



	public static void main(String[] args) {
		File file=new File("D://a闲杂/a.rtf");
		file.delete();
			System.out.println(FileTool.getFiles("D://a闲杂"));
	}
}
