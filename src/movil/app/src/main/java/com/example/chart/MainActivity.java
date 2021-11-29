package com.example.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn,btn_server;
    TextView contador, time;
    Cliente_pruebas cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn         = findViewById(R.id.btn_juego);
        btn_server  = findViewById(R.id.btn_server);
        contador    = findViewById(R.id.contador);
        time        = findViewById(R.id.time);

        listeners();



    }

    private void listeners() {
        btn.setOnClickListener(this);
        btn_server.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == btn.getId())
        {
            if (cliente != null)
            {
                cliente.pulsar();
                Toast.makeText(this, "pulsado", Toast.LENGTH_SHORT).show();

            }

        }else
        {
            cliente = new Cliente_pruebas();
            Toast.makeText(this, "creando servidor", Toast.LENGTH_SHORT).show();
        }

    }

}