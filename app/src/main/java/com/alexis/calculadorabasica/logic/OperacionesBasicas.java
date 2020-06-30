package com.alexis.calculadorabasica.logic;

import java.util.List;
import com.alexis.calculadorabasica.constants.Operadores;

public class OperacionesBasicas {

    private Operadores misOperadores = new Operadores();

    public OperacionesBasicas() {}

    public double operacionSencilla(List<String>tokens) {
        double resultadoFinal = 0;
        double valorAnterior = 0;
        boolean primerCalculo = true;
        int index = 0;

        for (String token : tokens) {
            if (!token.equals(misOperadores.OPERADOR_SUMA) &&
                    !token.equals(misOperadores.OPERADOR_RESTA)) {
                valorAnterior = Double.parseDouble(token);
            }

            if (token.equals(misOperadores.OPERADOR_RESTA)) {
                if (primerCalculo) {
                    resultadoFinal = valorAnterior - Double.parseDouble(tokens.get(index+1));
                    primerCalculo = false;
                } else {
                    resultadoFinal -= Double.parseDouble(tokens.get(index+1));
                }

            }

            if (token.equals(misOperadores.OPERADOR_SUMA)) {
                if (primerCalculo) {
                    resultadoFinal = valorAnterior +  Double.parseDouble(tokens.get(index+1));
                    primerCalculo = false;
                } else {
                    resultadoFinal += Double.parseDouble(tokens.get(index+1));
                }

            }
            index++;
        }

        return resultadoFinal;
    }

    public double operacionJerarquica(List<String>tokens) {
        double valorActual = 0;
        double resultadoFinal = 0;
        double acomulativo = 0, acomulativoFuncion = 0;
        boolean esPositivo = true;
        int index = 0;

        for (String token : tokens) {
            if (!token.equals(misOperadores.OPERADOR_SUMA) &&
                    !token.equals(misOperadores.OPERADOR_RESTA) &&
                    !token.equals(misOperadores.OPERADOR_PRODUCTO) &&
                    !token.equals(misOperadores.OPERADOR_DIVICION)) {
                valorActual = Double.parseDouble(token);
            }

            if (token.equals(misOperadores.OPERADOR_SUMA)) {
                acomulativo = Double.parseDouble(tokens.get(index-1)) + acomulativoFuncion;
            }
            index++;
        }
        return resultadoFinal;
    }

    public double suma(double valAnterior, double valSiguiente) {
        return valAnterior + valSiguiente;
    }

    public double resta(double valAnterior, double valSiguiente) {
        return valAnterior - valSiguiente;
    }

    public double producto(double valAnterior, double valSiguiente) {
        return valAnterior * valSiguiente;
    }

    public double division(double valAnterior, double valSiguiente) {
        return valAnterior / valSiguiente;
    }
}
