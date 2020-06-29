package com.alexis.calculadorabasica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alexis.calculadorabasica.logic.Utility;

public class MainActivity extends AppCompatActivity {

    private TextView myTextView = null;
    private String cantidadCompleta = "";
    private Utility myUtility = new Utility();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setMyTextView(View view) {
        myTextView = (TextView) findViewById(R.id.first_text_view);
        Button myButton = (Button) view;
        String numero_text = myButton.getText().toString();
        cantidadCompleta = myTextView.getText().toString();

        if (cantidadCompleta.equals("0")) {
            myTextView.setText(numero_text);
            cantidadCompleta = numero_text;
        } else {
            myTextView.setText(cantidadCompleta + numero_text);
            cantidadCompleta += numero_text;
        }
    }

    public void eliminarUno(View view) {
        if (cantidadCompleta.equals("0") || cantidadCompleta.equals("")) {
        } else {
            if (cantidadCompleta.length() == 1) {
                cantidadCompleta = "0";
            } else {
                cantidadCompleta = cantidadCompleta.substring(0,cantidadCompleta.length()-1);
            }
            myTextView.setText(cantidadCompleta);
        }
    }

    public void eliminarTodo(View view) {
        cantidadCompleta = "";
        myTextView.setText(cantidadCompleta);
    }

    public void calcular(View view) {

    }

}
