package org.gtug.vg.news.activities;

import org.gtug.vg.news.R;
import org.gtug.vg.news.model.Noticia;
import org.gtug.vg.news.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowNewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shownew);
		Bundle b = this.getIntent().getExtras();
		Noticia noticia = (Noticia) b.getSerializable(Constants.NOTICIA);
		TextView tVTitulo = (TextView) findViewById(R.id.titulo);
		tVTitulo.setText(noticia.getTitulo());
		TextView tVFecha = (TextView) findViewById(R.id.fecha);
		tVFecha.setText(noticia.getFecha());
		TextView tVAutor = (TextView) findViewById(R.id.autor);
		tVAutor.setText(noticia.getAutor());
		TextView tVCuerpo = (TextView) findViewById(R.id.cuerpo);
		tVCuerpo.setText(noticia.getCuerpo());
		TextView tVURL = (TextView) findViewById(R.id.url);
		tVURL.setText(noticia.getUrl());
		Button next = (Button) findViewById(R.id.volverNew);
		next.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}