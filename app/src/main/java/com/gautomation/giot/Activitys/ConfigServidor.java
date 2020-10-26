package com.gautomation.giot.Activitys;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gautomation.giot.R;

public class ConfigServidor extends AppCompatActivity {

    EditText Servidor, Porta, Usuario, Senha, Cliente, Cabecalho;
    String servidor, porta, usuario, senha, cliente, cabecalho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_servidor);

        Servidor = findViewById( R.id.editURL);
        Porta =  findViewById( R.id.editPorta );
        Usuario = findViewById( R.id.editCliente);
        Senha = findViewById( R.id.editUsuario);
        Cliente = findViewById( R.id.editSenha);
        Cabecalho =  findViewById( R.id.editCabecalho);

        SharedPreferences Sharad = getSharedPreferences("confiServidor", Context.MODE_PRIVATE);
        servidor = Sharad.getString("Servidor", "broker.hivemq.com");
        porta = Sharad.getString("Porta", "1883");
        usuario = Sharad.getString("Usuario", "eruyieu");
        senha = Sharad.getString("Senha", "********");
        cliente = Sharad.getString("Cliente", "Cliente_G_IOT");
        cabecalho = Sharad.getString("Cabecalho", "estivas/");

        Servidor.setText(servidor);
        Porta.setText(porta);
        Usuario.setText(usuario);
        Senha.setText(senha);
        Cliente.setText(cliente);
        Cabecalho.setText(cabecalho);
    }
    // =================== Menus =================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.salvar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sairesalvar) {
            SharedPreferences Sharad = getSharedPreferences("confiServidor", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = Sharad.edit();
            editor.putString("Servidor", Servidor.getText().toString());
            editor.putString("Porta",Porta.getText().toString());
            editor.putString("Usuario", Usuario.getText().toString());
            editor.putString("Senha", Senha.getText().toString());
            editor.putString("Cliente", Cliente.getText().toString());
            editor.putString("Cabecalho", Cabecalho.getText().toString());
            editor.commit();
            Toast.makeText(getApplicationContext(), "Configuração Salvas:)", Toast.LENGTH_SHORT).show();

            Intent trocatela=new Intent(ConfigServidor.this, MainActivity.class);
            startActivity(trocatela);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // ===========================================================
}