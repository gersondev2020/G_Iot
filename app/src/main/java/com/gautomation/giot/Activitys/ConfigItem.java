package com.gautomation.giot.Activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.gautomation.giot.Class.UnidadesAut;
import com.gautomation.giot.R;

public class ConfigItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText edittexto, edtiunidade, editqtddig, editvalormin, editvalormax;
    TextView item;
    Spinner spintag;
    private  String[] ListaTagReal;
    private String[] ListaTagExibir;
    int items = 0;

    //================ Ler no sharad o tag do item ==============================================

    UnidadesAut unidadesAut = new UnidadesAut();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_item);

        item = findViewById(R.id.textItem);
        edittexto = findViewById(R.id.editTexto);
        edtiunidade = findViewById(R.id.editUnidade);
        spintag = findViewById(R.id.SpinnerTag);
        editqtddig =  findViewById(R.id.editQtddig);
        editvalormin = findViewById(R.id.editValormax);
        editvalormax =  findViewById(R.id.editValormin);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ListaTagReal, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spintag.setAdapter(adapter);
        spintag.setOnItemSelectedListener(this);

        ListaTagReal = getResources().getStringArray(R.array.ListaTagReal);
        ListaTagExibir = getResources().getStringArray(R.array.ListaTagExibir);

        items = itemClicado();
        SharedPreferences SharadItem = getSharedPreferences("ConfigItems", Context.MODE_PRIVATE);
        int tagdoItem = SharadItem.getInt("Items"+items,items);
        spintag.setSelection(tagdoItem);
        //===========================================================================================
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
            //============ Salva O tag do item =========================================================
           SharedPreferences SharadItem = getSharedPreferences("ConfigItems", Context.MODE_PRIVATE);
           SharedPreferences.Editor editorItem = SharadItem.edit();
           editorItem.putInt("Items"+items,spintag.getSelectedItemPosition());
           editorItem.apply();
            //============ Salva dados do tag =========================================================
           SharedPreferences SharadTags = getSharedPreferences("ConfigTags", Context.MODE_PRIVATE);
           SharedPreferences.Editor editortag = SharadTags.edit();
           editortag.putString("Tags"+spintag.getSelectedItemPosition(),
                       edittexto.getText().toString()     +","+
                       spintag.getSelectedItemPosition()  +","+
                       edtiunidade.getText().toString()   +","+
                       editqtddig.getText().toString()    +","+
                       editvalormin.getText().toString()  +","+
                       editvalormax.getText().toString()
           );
           //===========================================================================================
            editortag.apply();
            Toast.makeText(getApplicationContext(), "Configuração Salvas:)", Toast.LENGTH_SHORT).show();
           // Toast.makeText(getApplicationContext(), "testes"+edtiunidade.getText().toString(), Toast.LENGTH_SHORT).show();

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    public int itemClicado() {
        Intent intent= getIntent();
        String itemclicado = (String) intent.getSerializableExtra("item");
        item.setText("Item Selecionado: "+itemclicado);
        return Integer.parseInt(itemclicado)-1;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int item = parent.getSelectedItemPosition();

        //================ Ler no sharad o dados do tag==============================================
        SharedPreferences SharadTags = getSharedPreferences("ConfigTags", Context.MODE_PRIVATE);
        String[] dadosSalvos = SharadTags.getString("Tags" + item,
                ListaTagExibir[item] + "," +
                        ListaTagReal[item] + "," +
                        unidadesAut.Valor_padrao_unidade(ListaTagReal[item]) + "," +
                        "5" + "," +
                        "9999" + "," +
                        "-9999"
        ).split(",");
        //===========================================================================================
        edittexto.setText(dadosSalvos[0]);
        edtiunidade.setText(dadosSalvos[2]);
        editqtddig.setText(dadosSalvos[3]);
        editvalormin.setText(dadosSalvos[4]);
        editvalormax.setText(dadosSalvos[5]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}