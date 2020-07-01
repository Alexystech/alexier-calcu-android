package com.alexis.calculadorabasica.logic;

import java.util.LinkedList;
import java.util.List;
import com.alexis.calculadorabasica.constants.Keys;
import com.alexis.calculadorabasica.constants.Operadores;

public class Utility {

    private Keys myKeys = new Keys();
    private Operadores misOperadores = new Operadores();
    private OperacionesBasicas misOperacionesBasicas = new OperacionesBasicas();

    public Utility() {}

    /**
     * este metodo sirve para parsear y calcular
     * el resultado final del texto ingresado por el cliente
     * */

    /**
     * queda pendiente checar gerarquia de operaciones
     * para implementar al proceso (primero multiplicaciones
     * y divisiones por ultimo sumas y restas)
     * */
    public double calcularOperacion(String cantidadCompleta) {
        double resultadoOperacion = 0;

        List<String>tokens = tokensText(cantidadCompleta);

        if (tokens.size() == 1) {
            if (tokens.get(0).equals("")) {
                resultadoOperacion = 0;
            } else {
                resultadoOperacion = Double.parseDouble(tokens.get(0));
            }
        } else {

            if (operacionSencilla(tokens)) {
                resultadoOperacion = misOperacionesBasicas.operacionSencilla(tokens);
            } else {
                resultadoOperacion = misOperacionesBasicas.operacionJerarquica(tokens);
            }
        }
        return resultadoOperacion;
    }

    public boolean operacionSencilla(List<String>tokens) {
        boolean primeraEvaluacionSencillas = false;
        boolean primeraEvaluecionOperadores = false;

        for (String token : tokens) {

            if (primeraEvaluacionSencillas && primeraEvaluecionOperadores) {
                break;
            }

            if (token.equals(misOperadores.OPERADOR_RESTA) ||
                token.equals(misOperadores.OPERADOR_SUMA)) {
                if (!primeraEvaluacionSencillas) {
                    primeraEvaluacionSencillas = true;
                }
            }

            if (token.equals(misOperadores.OPERADOR_DIVICION) ||
                token.equals(misOperadores.OPERADOR_PRODUCTO)) {
                if (!primeraEvaluecionOperadores) {
                    primeraEvaluecionOperadores = true;
                }
            }

        }

        if (primeraEvaluacionSencillas && primeraEvaluecionOperadores) {
            return false;
        } else {
            return true;
        }
    }

    public boolean instruccionCorrecta(String cantidadCompleta) {
        List<String>listaTokens = tokensText(cantidadCompleta);
        List<Integer>listaLlaves = keys(listaTokens);
         if (veredictoFinal(listaLlaves)) {
             return false;
         } else {
             return true;
         }
    }

    public boolean veredictoFinal(List<Integer>keys) {
        boolean hayError = false;

        try {
            if (keys.get(0).equals(myKeys.KEY_RANDOM)) {
                hayError = evaluarLlaves(keys);
            } else {
                hayError = true;
            }
        } catch (IndexOutOfBoundsException e) {
            hayError = false;
        }
        return hayError;
    }

    public boolean evaluarLlaves(List<Integer>llaves) {
        boolean hayError = false;
        if (llaves.get(llaves.size()-1).equals(myKeys.KEY_RANDOM)) {
            for (int key : llaves) {
                if (key == myKeys.KEY_ERROR) {
                    hayError = true;
                    break;
                } else {
                    hayError = false;
                }
            }
        } else {
            hayError = true;
        }
        return hayError;
    }

    public List<Integer>keys(List<String>tokens) {
        List<Integer>listKeys = new LinkedList<>();
        for (String token : tokens) {
            if (token.equals(misOperadores.OPERADOR_SUMA)) {
                listKeys.add(myKeys.KEY_SUMA);
            } else if (token.equals(misOperadores.OPERADOR_RESTA)) {
                listKeys.add(myKeys.KEY_RESTA);
            } else if (token.equals(misOperadores.OPERADOR_PRODUCTO)) {
                listKeys.add(myKeys.KEY_PRODUCTO);
            } else if (token.equals(misOperadores.OPERADOR_DIVICION)) {
                listKeys.add(myKeys.KEY_DIVICION);
            } else if (token.equals("")) {
                listKeys.add(myKeys.KEY_ERROR);
            } else {
                listKeys.add(myKeys.KEY_RANDOM);
            }
        }
        return listKeys;
    }

    public List<String>tokensText(String cantidadCompleta) {
        List<String>listaTemporal = new LinkedList<String>();
        String tokensTemporal = "";

        for (int x = 0; x < cantidadCompleta.length(); x++) {
            if (cantidadCompleta.charAt(x) == misOperadores.OPERADOR_SUMA_CHAR ||
                cantidadCompleta.charAt(x) == misOperadores.OPERADOR_RESTA_CHAR ||
                cantidadCompleta.charAt(x) == misOperadores.OPERADOR_PRODUCTO_CHAR ||
                cantidadCompleta.charAt(x) == misOperadores.OPERADOR_DIVICION_CHAR) {
                if (x == cantidadCompleta.length() - 1) {
                    listaTemporal.add(tokensTemporal);
                    tokensTemporal = Character.toString(cantidadCompleta.charAt(x));
                    listaTemporal.add(tokensTemporal);
                } else {
                    listaTemporal.add(tokensTemporal);
                    tokensTemporal = Character.toString(cantidadCompleta.charAt(x));
                    listaTemporal.add(tokensTemporal);
                    tokensTemporal = "";
                }
            } else {
                if (x == cantidadCompleta.length()-1) {
                    tokensTemporal += cantidadCompleta.charAt(x);
                    listaTemporal.add(tokensTemporal);
                } else {
                    tokensTemporal += cantidadCompleta.charAt(x);
                }
            }
        }
        return listaTemporal;
    }
}
