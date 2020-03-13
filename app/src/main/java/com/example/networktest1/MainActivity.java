package com.example.networktest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    EditText txt2;
    TextView output1;

    String out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codeViewConnection();

    }

    public void codeViewConnection(){

        txt2 = findViewById(R.id.editText);
        output1 = findViewById(R.id.first);

    }

    public String tcpImpl(){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket("se2-isys.aau.at", 53212);
                    PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                    pw.println(txt2.getText().toString());
                    //pw.flush();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    out = in.readLine();

                    pw.close();
                    s.close();
                    //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                    /*String userInput;
                    while ((userInput = stdIn.readLine()) != null){
                        out.println(userInput);
                        System.out.println(in.readLine());
                    }*/

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return out;

    }


}
