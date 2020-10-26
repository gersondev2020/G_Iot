package com.gautomation.giot.Activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gautomation.giot.R;

public class ConfigItem extends AppCompatActivity {
    EditText edittexto, edtiunidade, editqtddig, editvalormin, editvalormax;
    TextView item;
    String itemclicado;
    Spinner spintag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_item);

        item = findViewById(R.id.textItem);
        edtiunidade = findViewById(R.id.editUnidade);
        editqtddig =  findViewById(R.id.editQtddig);
        editvalormin = findViewById(R.id.editCliente);
        editvalormax =  findViewById(R.id.editCabecalho);
        spintag = findViewById(R.id.SpinnerTag);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ListaTagReal, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spintag.setAdapter(adapter);
        //spintag.setOnItemSelectedListener(this);

        setEditqtddig();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.salvar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sairesalvar) {
//            SharedPreferences Sharad = getSharedPreferences("confiServidor", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = Sharad.edit();
//            editor.putString("Servidor", Servidor.getText().toString());
//            editor.putString("Porta",Porta.getText().toString());
//            editor.putString("Usuario", Usuario.getText().toString());
//            editor.putString("Senha", Senha.getText().toString());
//            editor.putString("Cliente", Cliente.getText().toString());
//            editor.putString("Cabecalho", Cabecalho.getText().toString());
//            editor.commit();
            Toast.makeText(getApplicationContext(), "Configuração Salvas:)", Toast.LENGTH_SHORT).show();

//            Intent trocatela=new Intent(ConfigItem.this, MainActivity.class);
//            startActivity(trocatela);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    public void setEditqtddig() {
        Intent intent= getIntent();
        itemclicado = (String) intent.getSerializableExtra("item");
        item.setText("Item Selecionado: "+itemclicado);
    }

}