package com.hp.util;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpUtil {

	private static final String CHARSET = HTTP.UTF_8;

	public static HttpClient getClinet() {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpProtocolParams.setUserAgent(params,
				"Mozilla/5.0(Linux;U;Android 2.3.5)");
		// 设置网络超时
		ConnManagerParams.setTimeout(params, 5000);
		HttpConnectionParams.setConnectionTimeout(params, 5000);
		HttpConnectionParams.setSoTimeout(params, 5000);
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));

		ClientConnectionManager conmgr = new ThreadSafeClientConnManager(
				params, schReg);
		
		return new DefaultHttpClient(conmgr, params);

	}

	public static HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url);
		return request;
	}

	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		return request;
	}

	public static HttpResponse getHttpResponse(HttpGet request)
			throws ClientProtocolException, IOException {
		HttpResponse response = HttpUtil.getClinet().execute(request);
		return response;
	}

	public static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {

		HttpResponse response = HttpUtil.getClinet().execute(request);
		return response;
	}

	public static String queryStringForPost(String url) {
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);

			// request.get
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
				return result;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "网络请求异常";
			return result;
		}
		return null;
	}

	public static String queryStringForGet(String url) {

		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			int resultCode = response.getStatusLine().getStatusCode();
			// System.out.println("resultCode-->"+resultCode);
			if (resultCode == 200) {
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return null;
	}

	public static byte[] getImage(String url) {

		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = HttpUtil.getClinet();
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				return entity == null ? null : EntityUtils.toByteArray(entity);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String queryStringForPost(HttpPost post) {
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(post);
			if (response.getStatusLine().getStatusCode() == 200) {

				result = EntityUtils.toString(response.getEntity());
				return result;
				// return result;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "网络异常";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "网络异常";
		}
		return result;
	}

	public static String queryStringForGet(HttpGet request) {
		// TODO Auto-generated method stub
		String result = null;
		try {

			HttpResponse response = HttpUtil.getHttpResponse(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
				return result;	
			}
			result = EntityUtils.toString(response.getEntity());
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
