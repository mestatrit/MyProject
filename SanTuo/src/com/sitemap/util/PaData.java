package com.sitemap.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class PaData {
	
	public static String getData(String address){
		URL url;
        int responsecode;
        HttpURLConnection urlConnection;
        BufferedReader reader;
        StringBuffer html=new StringBuffer();
        String line;
        try{
            //生成一个URL对象
            url=new URL(address);
            //打开URL
            urlConnection = (HttpURLConnection)url.openConnection();
            //获取服务器响应代码
            responsecode=urlConnection.getResponseCode();
            if(responsecode==200){
                //得到输入流，即获得了网页的内容 
                reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
                while((line=reader.readLine())!=null){
                	//System.out.println(line);
                	html.append(line+"\n");
                }
            }
            else{
                System.out.println("获取不到网页的源码，服务器响应代码为："+responsecode);
                return "";
            }
        }
        catch(Exception e){
            System.out.println("获取不到网页的源码,出现异常："+e);
        }
		return html.toString();
	}
	
	public static String geturldata(String path,String code){
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Accept-Charset", code);
			connection.setRequestProperty("contentType", code);
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(15 * 1000);
			connection.setReadTimeout(30 * 1000);
			InputStream inputStream = connection.getInputStream();
			String encoding = connection.getContentEncoding();
			BufferedReader reader=null;
			if("gzip".equals(encoding)){
				reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream),code));
			}else{
				reader = new BufferedReader(new InputStreamReader(inputStream,code));
			}
			StringBuffer sb=new StringBuffer("");
			String line="";
			while((line=reader.readLine())!=null){
				sb.append(line+"\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	} 
	
	public static void main(String[] args) throws Exception {
		PaData paData=new PaData();
		System.out.println(PaData.geturldata("http://api.map.baidu.com/place/v2/detail?uid=8ee4560cf91d160e6cc02cd7&ak=mgF3rbgKMnOZv37GnF9UUyGp&output=json&scope=2","utf8"));
	}
}
