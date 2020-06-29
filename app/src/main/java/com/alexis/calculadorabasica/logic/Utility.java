package com.alexis.calculadorabasica.logic;

import android.widget.TextView;
import java.util.LinkedList;
import java.util.List;
import com.alexis.calculadorabasica.constants.Keys;
import com.alexis.calculadorabasica.constants.Operadores;

public class Utility {

    private Keys myKeys = new Keys();
    private Operadores misOperadores = new Operadores();

    public Utility() {}

    public boolean instruccionCorrecta(String cantidadCompleta) {

        return false;
    }

    public List<String>tokensText(String cantidadCompleta) {
        List<String>listaTemporal = new LinkedList<String>();
        String tokensTemporal = "";
        boolean firstCharacter = true;

        for (int x = 0; x < cantidadCompleta.length(); x++) {
            if (cantidadCompleta.charAt(x) == misOperadores.OPERADOR_SUMA_CHAR ||
                cantidadCompleta.charAt(x) == misOperadores.OPERADOR_RESTA_CHAR ||
                cantidadCompleta.charAt(x) == misOperadores.OPERADOR_PRODUCTO_CHAR ||
                cantidadCompleta.charAt(x) == misOperadores.OPERADOR_DIVICION_CHAR ||
                x == cantidadCompleta.length()-1) {
                if (firstCharacter) {
                    tokensTemporal = Character.toString(cantidadCompleta.charAt(x));
                    listaTemporal.add(tokensTemporal);
                } else {
                    if (x == cantidadCompleta.length()-1) {
                        tokensTemporal += cantidadCompleta.charAt(x);
                        listaTemporal.add(tokensTemporal);
                    } else {
                        listaTemporal.add(tokensTemporal);
                        tokensTemporal = Character.toString(cantidadCompleta.charAt(x));
                        listaTemporal.add(tokensTemporal);
                        tokensTemporal = "";
                    }
                }
            } else {
                tokensTemporal += cantidadCompleta.charAt(x);
                firstCharacter = false;
            }
        }
        return listaTemporal;
    }
}
