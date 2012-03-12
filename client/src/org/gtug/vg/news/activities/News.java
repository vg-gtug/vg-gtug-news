package org.gtug.vg.news.activities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.gtug.vg.news.R;
import org.gtug.vg.news.model.Noticia;
import org.gtug.vg.news.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class News extends ListActivity {

	private ArrayList<String> titulos = new ArrayList<String>();
	private ArrayList<Noticia> noticias = new ArrayList<Noticia>();
	private static final String TAG = "NewsActivity";

	/**
	 * Función que controla el inicio de la aplicación
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//setContentView(R.layout.main);
		super.onCreate(savedInstanceState);
		JSONObject jObject = getJSONfromURL("http://vg-gtug-news.appspot.com/json/noticias.json");
		try {
			JSONArray jArray = jObject.getJSONArray("noticias");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jO = jArray.getJSONObject(i).getJSONObject("noticia");
				String titulo = jO.getString("titulo");
				titulos.add(titulo);
				String cuerpo = jO.getString("cuerpo");
				String autor = jO.getString("autor");
				String fecha = jO.getString("fecha");
				String url = jO.getString("url");
				Noticia noticia = new Noticia(titulo, cuerpo, autor, fecha, url);
				noticias.add(noticia);
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		setListAdapter(new ArrayAdapter<String>(this, R.layout.listnews, R.id.newElement, titulos));
		ListView lv = this.getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent();
				Bundle b = new Bundle();
				Noticia noticia = noticias.get(position);
				b.putSerializable(Constants.NOTICIA, noticia);
				i.putExtras(b);
				i.setClass(view.getContext(), ShowNewActivity.class);
				startActivity(i);
			}
		});
	}

	/**
	 * Devuelve un JSONObject recuperado de la url pasada por parametro
	 * 
	 * @param url
	 * @return
	 */
	public static JSONObject getJSONfromURL(String url) {
		//initialize
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;
		//http post
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//try parse the string to a JSON object
		try {
			jArray = new JSONObject(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jArray;
	}
}