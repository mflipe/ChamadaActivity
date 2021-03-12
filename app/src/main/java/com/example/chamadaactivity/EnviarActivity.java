package com.example.chamadaactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EnviarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);

        String titulo = getIntent().getStringExtra("titulo");
        String conteudo = getIntent().getStringExtra("conteudo");

        ((TextView) findViewById(R.id.idTitulo)).setText(titulo);
        ((TextView) findViewById(R.id.idConteudo)).setText(conteudo);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void chamarCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            ImageView foto = (ImageView) findViewById(R.id.foto);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(imageBitmap);
        }
    }

    public void compartilhar(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(intent.EXTRA_SUBJECT, ((TextView) findViewById(R.id.idTitulo)).getText());
        intent.putExtra(intent.EXTRA_TEXT, ((TextView) findViewById(R.id.idConteudo)).getText());
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Compartilhando com ..."));
    }
}