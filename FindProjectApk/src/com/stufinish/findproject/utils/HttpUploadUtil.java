package com.stufinish.findproject.utils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

//ͨ��HttpЭ�鷢�ʹ��ļ��򲻴��ļ�������Ĺ�����
public class HttpUploadUtil 
{
		//�����ļ��������ͷ���
	// //actionUrl��ʾ�����URL��Map<String, String> params��ʾ����Ĳ�������
	public static String postWithoutFile(String actionUrl,Map<String, String> params)
	{		
		//String result="";
		//1����HttpClient����
		HttpClient httpclient = new DefaultHttpClient();
		//2����ָ����URL������HTTPPOST����
		HttpPost httppost = new HttpPost(actionUrl);
		//��Ҫ���ݵĲ������浽LIST������
		
		try { 
		   List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(params.size()); 
		   //ѭ����ȡ�������ϣ���������nameValuePairs��
		   for (Map.Entry<String, String> entry : params.entrySet()) 
		   {//�������ֶ�����  
	            nameValuePairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue())); 
	       }  
		   
		   //���ñ��뷽ʽ
		   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8")); 
		   //����HttpResponse����
		   HttpResponse response; 
		   //ִ��HttpClient����
		   response=httpclient.execute(httppost); 
//		   //��ȡ���
//		   if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
//		   {
//			   result+=EntityUtils.toString(response.getEntity());
//			   
//		   }else
//		   {
//			   
//			   result="����ʧ��!";
//		   }
//		   return result;
		   
		  InputStream in=response.getEntity().getContent();
		
		   //�����ֽ����������
		   ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   //������������ȡ���������
		   int ch=0;
		    while((ch=in.read())!=-1)
		    {
		      	baos.write(ch);
		    }   
		    //�������������ȫ�����浽�ֽ�����data��.
		    byte[] data=baos.toByteArray();
		    baos.close();
		    //�������ַ�����������ݣ���
		    return MyConverter.unescape(new String(data).trim());
		  
		  } catch (Exception e) 
		  { 
		   e.printStackTrace(); 
		   return "error";
		  }
	}
}
