package com.buleocean_health.springboot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


public class MessageUtils {
	private static String applyName = "泰心康护用户端";
	private static String appKey = "7be49b8c736d408f980f2d14e96e1399";
	private static String appSecret = "MsDl6M67a13CF9";
	private static String url = "http://180.76.174.246:9601/api/sms/send";
   
	public static String sendMessage(String msg,String mobilePhone) {
	   long currentTimeMillis = System.currentTimeMillis()/1000;
		String str="appKey="+appKey+"&appSecret="+appSecret+"&applyName="+applyName+"&mobilePhone="+mobilePhone+"&timestamp="+currentTimeMillis;
		String sign = AES.GetMD5(str);
		sign = sign.toUpperCase();
//		System.out.println(sign);
		//String url ="http://180.76.174.246:9601/api/sms/send";
		//String param="applyName=药回收&appKey=B8B333DD5B064D7697B15836A88804F2&mobilePhone=18515644708&sign="+sign;
		 JSONObject  obj = new JSONObject();
		   try {
			obj.put("applyName", applyName);
			obj.put("appKey", appKey);
			obj.put("mobilePhone", mobilePhone);
			obj.put("sign", sign);
			obj.put("msg", msg);
			obj.put("timestamp", currentTimeMillis);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
//		   System.out.println(obj);
		String sendPost = MessageUtils.sendPost(url, obj.toString());
		//System.out.println("返回:::"+sendPost);
	   
	   
	return sendPost;
	
}
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {   	
        PrintWriter out = null;
        BufferedReader in = null;
        String result="";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("GS","UXDATA");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
//            System.out.println(conn.getOutputStream());
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            conn.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    public static void main(String[] args) {
    	System.out.println(sendMessage("", "13526731887"));
	}
}

