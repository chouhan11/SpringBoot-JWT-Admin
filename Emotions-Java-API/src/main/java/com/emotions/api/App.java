
package com.emotions.api;

import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Madhur Chouhan
 */

public class App {
	private String api_key = "xD2qIgWrrhPIkAMRE03YupkelZYqGGoqLgqmF2u7yw0";
	private String host = "https://apis.paralleldots.com/v4/";

	public App(String api_key) {
		this.api_key = api_key;
		try {
			setUpCert("apis.paralleldots.com");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public void setUpCert(String hostname) throws Exception {
		SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();

		SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443);
		try {
			socket.startHandshake();
			socket.close();
			return;
		} catch (SSLException e) {
			e.getMessage();
		}

		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] password = "changeit".toCharArray();
		ks.load(null, password);

		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ks);
		X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
		context.init(null, new TrustManager[] { tm }, null);
		factory = context.getSocketFactory();

		socket = (SSLSocket) factory.createSocket(hostname, 443);
		try {
			socket.startHandshake();
		} catch (SSLException e) {
			e.getMessage();
		}
		X509Certificate[] chain = tm.chain;
		if (chain == null) {
			System.out.println("Could not obtain server certificate chain");
			return;
		}

		X509Certificate cert = chain[0];
		String alias = hostname;
		ks.setCertificateEntry(alias, cert);
		System.out.println("saving file paralleldotscacerts to working dir");
		FileOutputStream fos = new FileOutputStream("paralleldotscacerts");
		ks.store(fos, password);
		fos.close();
	}

	private static class SavingTrustManager implements X509TrustManager {
		private final X509TrustManager tm;
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}
	}

	public String emotion(String text) throws Exception {
		if (this.api_key != null) {
			String url = host + "emotion";
			OkHttpClient client = new OkHttpClient();
			RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("api_key", this.api_key).addFormDataPart("text", text)
					.addFormDataPart("lang_code", "en").build();
			Request request = new Request.Builder().url(url).post(requestBody).addHeader("cache-control", "no-cache")
					.build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} else {
			return "{ \"Error\": \"API key does not exist\" }";
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws Exception {
		String key = null;
		System.out.println("ParallelDots API");
		App pd = new App("xD2qIgWrrhPIkAMRE03YupkelZYqGGoqLgqmF2u7yw0");
		// Excited="I recently got free voucher from your company. Thank yo for it."
		// Fear="Conflict is your company is definitely going to be the next big thing,
		// i can see it."
		String jsonStr = pd
				.emotion("Conflict is your company is definitely going to be the next big thing, i can see it.");

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);
		System.out.println(jsonObject);
		Object emotions = jsonObject.get("emotion");
		Map<String, Double> readValue = new ObjectMapper().readValue(emotions.toString(), Map.class);
		double maxValueInMap = (double) (Collections.max(readValue.values()));
		for (Entry<String, Double> entry : readValue.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				key = entry.getKey();
			}
		}
		switch (key) {
		case "Fear":
			System.out.println("Are you feeling "+key+",take dark chocolate");
			break;
		case "Bored":
			System.out.println("Are you feeling "+key+",take Nnsweetened chocolate");
			break;
		case "Excited":
			System.out.println("Are you feeling "+key+",take Semi-sweet chocolate");
			break;
		case "Happy":
			System.out.println("Are you feeling "+key+",take Milk Chocolate");
			break;
		case "Angry":
			System.out.println("Are you feeling "+key+",take White Chocolate");
			break;
		case "Sad":
			System.out.println("Are you feeling "+key+",take Ruby Chocolate");
			break;
		default:
			System.out.println("no match");
		}
	}

}
