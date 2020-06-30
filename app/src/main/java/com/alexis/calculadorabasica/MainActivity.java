package com.alexis.calculadorabasica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    /**
     * este metodo sirve para agregar un valor numerico/simbolo
     * al TextView.
     * */
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

    /**
     * este metodo me sirve para eliminar un valor/simbolo
     * del TextView
     * */
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

    /**
     * este metodo me sirve para eliminar todos
     * los valores/simbolos del TextView
     * */
    public void eliminarTodo(View view) {
        cantidadCompleta = "";
        myTextView.setText(cantidadCompleta);
    }

    /**
     * en este metodo verifico si el texto ingresado
     * es considerado como valido, si es correcta esta
     * ultima validacion entonces paso a calcular todo
     * lo escrito en el TextView
     * */
    public void calcular(View view) {
        if (myUtility.instruccionCorrecta(cantidadCompleta)) {
            Toast.makeText(getApplicationContext(),"CALCULANDO",Toast.LENGTH_LONG).show();
            String resultado = Double.toString(myUtility.calcularOperacion(cantidadCompleta));
            cantidadCompleta = resultado;
            myTextView.setText(resultado);
        } else {
            Toast.makeText(getApplicationContext(),"SYNTAX ERROR",Toast.LENGTH_LONG).show();
        }
    }

}
