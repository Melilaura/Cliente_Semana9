package com.example.cliente_semana9.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cliente_semana9.R;
import com.example.cliente_semana9.event.OnMessageListener;
import com.example.cliente_semana9.model.Orden;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMessageListener {

    private Button aButton, bButton,cButton,dButton;
    private Gson gson;
    private Orden nuevaOrden;
    private UDPConnection udp;

    private LocalTime time;
    private DateTimeFormatter timeFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aButton = findViewById(R.id.aButton);
        bButton = findViewById(R.id.bButton);
        cButton = findViewById(R.id.cButton);
        dButton = findViewById(R.id.dButton);

        udp = new UDPConnection(this);
        udp.setObserver(this);
        udp.start();

        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        dButton.setOnClickListener(this);



        nuevaOrden = new Orden();

        gson = new Gson();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.aButton:


                Gson gsonA = new Gson();
                nuevaOrden.setOrden("bbtea");
                Fecha();
                String jsonA = gsonA.toJson(nuevaOrden);
                udp.enviarMensaje(jsonA);
                Toast.makeText(this, "Aja boton A "+ jsonA, Toast.LENGTH_SHORT).show();
                break;

            case R.id.bButton:

                Gson gsonB = new Gson();
                nuevaOrden.setOrden("pastel1");
                Fecha();
                String jsonB = gsonB.toJson(nuevaOrden);
                udp.enviarMensaje(jsonB);
                Toast.makeText(this, "Aja boton B "+ jsonB, Toast.LENGTH_SHORT).show();

                break;

            case R.id.cButton:




                Gson gsonC = new Gson();
                nuevaOrden.setOrden("pastelA");
                Fecha();
                String jsonC = gsonC.toJson(nuevaOrden);
                udp.enviarMensaje(jsonC);

                Toast.makeText(this, "Aja boton C " + jsonC, Toast.LENGTH_SHORT).show();

                break;
            case R.id.dButton:

                Gson gsonD = new Gson();
                nuevaOrden.setOrden("pastel2");
                Fecha();
                String jsonD = gsonD.toJson(nuevaOrden);
                udp.enviarMensaje(jsonD);

                Toast.makeText(this, "Aja boton D "+ jsonD, Toast.LENGTH_SHORT).show();
                break;
        }

    }


    private void Fecha(){

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd \nHH:mm:ss");
        String formattedDate = df.format(c.getTime());
        nuevaOrden.setTiempo(formattedDate);
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
    }

    public void mensajeRecibido(String mensaje)
    {
        runOnUiThread(
                ()->
                {
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }
        );
    }

}