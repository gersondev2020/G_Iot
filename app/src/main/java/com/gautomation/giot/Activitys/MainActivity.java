package com.gautomation.giot.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gautomation.giot.Class.Constants;
import com.gautomation.giot.Class.PahoMqttClient;
import com.gautomation.giot.Class.UnidadesAut;
import com.gautomation.giot.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.gautomation.giot.Class.Constants.numerodeitem;


public class MainActivity extends AppCompatActivity {
    private TextView item;
    TextView[] TextNome = new TextView[30];
    TextView[] variavel = new TextView[30];
    LinearLayout[] Items = new LinearLayout[30];
    TextView Status;
    private MqttAndroidClient client;
    private String TAG = "MainActivity";
    private PahoMqttClient pahoMqttClient;
    private String clientid = "";
    private UnidadesAut unidadesAut;
    private Timer myTimer;
    private  String[] ListaTagReal;
    private  String[] ListaTagExibir ;
    String servidor,
            porta,
            usuario,
            senha,
            cliente,
            cabecalho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextNome[0] = findViewById( R.id.txtNome1 );
        TextNome[1] = findViewById( R.id.txtNome2 );
        TextNome[2] = findViewById( R.id.txtNome3 );
        TextNome[3] = findViewById( R.id.txtNome4 );
        TextNome[4] = findViewById( R.id.txtNome5 );
        TextNome[5] = findViewById( R.id.txtNome6 );
        TextNome[6] = findViewById( R.id.txtNome7 );
        TextNome[7] = findViewById( R.id.txtNome8 );
        TextNome[8] = findViewById( R.id.txtNome9 );
        TextNome[9] = findViewById( R.id.txtNome10 );
        TextNome[10] = findViewById( R.id.txtNome11 );
        TextNome[11] = findViewById( R.id.txtNome12 );
        TextNome[12] = findViewById( R.id.txtNome13 );
        TextNome[13] = findViewById( R.id.txtNome14 );
        TextNome[14] = findViewById( R.id.txtNome15 );
        TextNome[15] = findViewById( R.id.txtNome16 );
        TextNome[16] = findViewById( R.id.txtNome17 );
        TextNome[17] = findViewById( R.id.txtNome18 );
        TextNome[18] = findViewById( R.id.txtNome19 );
        TextNome[19] = findViewById( R.id.txtNome20 );
        TextNome[20] = findViewById( R.id.txtNome21 );
        TextNome[21] = findViewById( R.id.txtNome22 );
        TextNome[22] = findViewById( R.id.txtNome23 );
        TextNome[23] = findViewById( R.id.txtNome24 );
        TextNome[24] = findViewById( R.id.txtNome25 );
        TextNome[25] = findViewById( R.id.txtNome26 );
        TextNome[26] = findViewById( R.id.txtNome27 );
        TextNome[27] = findViewById( R.id.txtNome28 );
        TextNome[28] = findViewById( R.id.txtNome29 );
        TextNome[29] = findViewById( R.id.txtNome30 );

        variavel[0] = findViewById(R.id.txtValor1);
        variavel[1] = findViewById(R.id.txtValor2);
        variavel[2] = findViewById(R.id.txtValor3);
        variavel[3] = findViewById(R.id.txtValor4);
        variavel[4] = findViewById(R.id.txtValor5);
        variavel[5] = findViewById(R.id.txtValor6);
        variavel[6] = findViewById(R.id.txtValor7);
        variavel[7] = findViewById(R.id.txtValor8);
        variavel[8] = findViewById(R.id.txtValor9);
        variavel[9] = findViewById(R.id.txtValor10);
        variavel[10] = findViewById(R.id.txtValor11);
        variavel[11] = findViewById(R.id.txtValor12);
        variavel[12] = findViewById(R.id.txtValor13);
        variavel[13] = findViewById(R.id.txtValor14);
        variavel[14] = findViewById(R.id.txtValor15);
        variavel[15] = findViewById(R.id.txtValor16);
        variavel[16] = findViewById(R.id.txtValor17);
        variavel[17] = findViewById(R.id.txtValor18);
        variavel[18] = findViewById(R.id.txtValor19);
        variavel[19] = findViewById(R.id.txtValor20);
        variavel[20] = findViewById(R.id.txtValor21);
        variavel[21] = findViewById(R.id.txtValor22);
        variavel[22] = findViewById(R.id.txtValor23);
        variavel[23] = findViewById(R.id.txtValor24);
        variavel[24] = findViewById(R.id.txtValor25);
        variavel[25] = findViewById(R.id.txtValor26);
        variavel[26] = findViewById(R.id.txtValor27);
        variavel[27] = findViewById(R.id.txtValor28);
        variavel[28] = findViewById(R.id.txtValor29);
        variavel[29] = findViewById(R.id.txtValor30);

        Items[0] = findViewById(R.id.Item01);
        Items[1] = findViewById(R.id.Item02);
        Items[2] = findViewById(R.id.Item03);
        Items[3] = findViewById(R.id.Item04);
        Items[4] = findViewById(R.id.Item05);
        Items[5] = findViewById(R.id.Item06);
        Items[6] = findViewById(R.id.Item07);
        Items[7] = findViewById(R.id.Item08);
        Items[8] = findViewById(R.id.Item09);
        Items[9] = findViewById(R.id.Item10);
        Items[10] = findViewById(R.id.Item11);
        Items[11] = findViewById(R.id.Item12);
        Items[12] = findViewById(R.id.Item13);
        Items[13] = findViewById(R.id.Item14);
        Items[14] = findViewById(R.id.Item15);
        Items[15] = findViewById(R.id.Item16);
        Items[16] = findViewById(R.id.Item17);
        Items[17] = findViewById(R.id.Item18);
        Items[18] = findViewById(R.id.Item19);
        Items[19] = findViewById(R.id.Item20);
        Items[20] = findViewById(R.id.Item21);
        Items[21] = findViewById(R.id.Item22);
        Items[22] = findViewById(R.id.Item23);
        Items[23] = findViewById(R.id.Item24);
        Items[24] = findViewById(R.id.Item25);
        Items[25] = findViewById(R.id.Item26);
        Items[26] = findViewById(R.id.Item27);
        Items[27] = findViewById(R.id.Item28);
        Items[28] = findViewById(R.id.Item29);
        Items[29] = findViewById(R.id.Item30);

        ListaTagReal = getResources().getStringArray(R.array.ListaTagReal);
        ListaTagExibir = getResources().getStringArray(R.array.ListaTagExibir);

        SharedPreferences Sharad = getSharedPreferences("confiServidor", Context.MODE_PRIVATE);
        servidor = Sharad.getString("Servidor", "broker.hivemq.com");
        porta = Sharad.getString("Porta", "1883");
        usuario = Sharad.getString("Usuario", "eruyieu");
        senha = Sharad.getString("Senha", "********");
        cliente = Sharad.getString("Cliente", "Cliente_G_IOT");
        cabecalho = Sharad.getString("Cabecalho", "estivas/");

        //================ Configurações MQTT ============================================
        clientid = cliente; // id do criente.

        Random aleatorio = new Random();
        int numeroID_1 = aleatorio.nextInt(100) + 1;
        int numeroID_2 = aleatorio.nextInt(100) + 1;
        int numeroID_3 = aleatorio.nextInt(100) + 1;

        pahoMqttClient = new PahoMqttClient();
        client = pahoMqttClient.getMqttClient(getApplicationContext(),// Connect to MQTT Broker
                "tcp://"+servidor+":"+porta,
                clientid + numeroID_1*numeroID_2/numeroID_3,
                usuario,
                senha
        );
        unidadesAut = new UnidadesAut();
        //Create listener for MQTT messages.
        mqttCallback();
        //Create Timer to report MQTT connection status every 1 second
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ScheduleTasks();
            }

        }, 0, 2000);

        for(int i = 0; i < numerodeitem; i++) {
            Items[i].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                String view = String.valueOf(v);
                view = view.substring(view.length()-3,view.length()-1);
                    Intent passateladeconfig = new Intent(MainActivity.this, ConfigItem.class);
                    passateladeconfig.putExtra("item", view);
                    startActivity(passateladeconfig);
                    return false;
                }
            });
        }

    }

    // =================== Menus =================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_conf, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.confservidor) {
            Intent trocatela=new Intent(MainActivity.this, ConfigServidor.class);
            startActivity(trocatela);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // ===========================================================

    private void ScheduleTasks()
    {
        this.runOnUiThread(RunScheduledTasks);
    }
    private Runnable RunScheduledTasks = new Runnable() {
        public void run() {
            TextView status  = findViewById(R.id.statusconexao);
            String msg_new="";
            if(pahoMqttClient.mqttAndroidClient.isConnected() ) {
                msg_new = "Conectado\r\n";
                status.setTextColor(0xFF00FF00); //Green if connected
                status.setTextSize( TypedValue.COMPLEX_UNIT_SP, 14);
                try {
                    for(int i = 0; i < numerodeitem; i++){
                        pahoMqttClient.subscribe(client, cabecalho+ListaTagReal[i], 0);
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
            else {
                msg_new = "Desconectado\r\n";
                status.setTextColor(0xFFFF0000); //Red if not connected
                status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }
            status.setText(msg_new);
        }
    };
    // Called when a subscribed message is received
    protected void mqttCallback() {
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                //msg("Connection lost...");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String msg = message.toString();
                //TextView tvMessage = (TextView) findViewById(R.id.subscribedMsg);
                String[] listadetag = new String[numerodeitem];
                for(int i = 0; i < numerodeitem; i++) {
                    listadetag[i] = cabecalho + ListaTagReal[i];
                }
                for(int i = 0; i < numerodeitem; i++){
                    if(topic.equals(listadetag[i])) {
                        if(msg.length() > 5) {
                            variavel[i].setText(msg.substring(0,5));
                        }else{
                            variavel[i].setText(msg);
                        }
                    }
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

    }
}