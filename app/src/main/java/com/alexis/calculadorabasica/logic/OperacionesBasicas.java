package com.alexis.calculadorabasica.logic;

import java.util.LinkedList;
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
                    !token.equals(misOperadores.OPERADOR_RESTA) &&
                    !token.equals(misOperadores.OPERADOR_DIVICION) &&
                    !token.equals(misOperadores.OPERADOR_PRODUCTO)) {
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

            if (token.equals(misOperadores.OPERADOR_PRODUCTO)) {
                if (primerCalculo) {
                    resultadoFinal = valorAnterior * Double.parseDouble(tokens.get(index+1));
                    primerCalculo = false;
                } else {
                    resultadoFinal *= Double.parseDouble(tokens.get(index+1));
                }
            }

            if (token.equals(misOperadores.OPERADOR_DIVICION)) {
                if (primerCalculo) {
                    resultadoFinal = valorAnterior / Double.parseDouble(tokens.get(index+1));
                    primerCalculo = false;
                } else {
                    resultadoFinal /= Double.parseDouble(tokens.get(index+1));
                }
            }
            index++;
        }

        return resultadoFinal;
    }

    public double operacionJerarquica(List<String>tokens) {
        List<String>listaCalculoFinal = new LinkedList<String>();
        boolean operacionAnterior = false;
        int index = 0, indexFinalList = -1;
        double valorAcomuldoProceso = 0;

        for (String token : tokens) {
            if (!token.equals(misOperadores.OPERADOR_PRODUCTO) &&
                !token.equals(misOperadores.OPERADOR_DIVICION)) {

                if (!token.equals(misOperadores.OPERADOR_SUMA) &&
                    !token.equals(misOperadores.OPERADOR_RESTA)) {
                    listaCalculoFinal.add(token);
                    indexFinalList++;

                    if (operacionAnterior) {
                        listaCalculoFinal.remove(indexFinalList);
                        indexFinalList--;
                    }
                }

                if (token.equals(misOperadores.OPERADOR_SUMA) ||
                    token.equals(misOperadores.OPERADOR_RESTA)) {
                    listaCalculoFinal.add(token);
                    indexFinalList++;

                    if (operacionAnterior) {
                        operacionAnterior = false;
                    }
                }

            }

            if (token.equals(misOperadores.OPERADOR_DIVICION) ||
                token.equals(misOperadores.OPERADOR_PRODUCTO)) {

                if (token.equals(misOperadores.OPERADOR_PRODUCTO)) {
                    if (operacionAnterior) {
                        listaCalculoFinal.remove(indexFinalList);
                        indexFinalList--;

                        valorAcomuldoProceso *= Double.parseDouble(tokens.get(index+1));

                        listaCalculoFinal.add(Double.toString(valorAcomuldoProceso));
                        indexFinalList++;
                    } else {
                        valorAcomuldoProceso = 0;

                        listaCalculoFinal.remove(indexFinalList);
                        indexFinalList--;

                        valorAcomuldoProceso = Double.parseDouble(tokens.get(index-1)) * Double
                                .parseDouble(tokens.get(index+1));
                        operacionAnterior = true;

                        listaCalculoFinal.add(Double.toString(valorAcomuldoProceso));
                        indexFinalList++;
                    }
                }

                if (token.equals(misOperadores.OPERADOR_DIVICION)) {
                    if (operacionAnterior) {
                        listaCalculoFinal.remove(indexFinalList);
                        indexFinalList--;

                        valorAcomuldoProceso /= Double.parseDouble(tokens.get(index+1));

                        listaCalculoFinal.add(Double.toString(valorAcomuldoProceso));
                        indexFinalList++;
                    } else {
                        valorAcomuldoProceso = 0;

                        listaCalculoFinal.remove(indexFinalList);
                        indexFinalList--;

                        valorAcomuldoProceso = Double.parseDouble(tokens.get(index-1)) / Double
                                .parseDouble(tokens.get(index+1));
                        operacionAnterior = true;

                        listaCalculoFinal.add(Double.toString(valorAcomuldoProceso));
                        indexFinalList++;
                    }
                }
            }
            index++;
        }

        double resultadoFinal = operacionSencilla(listaCalculoFinal);

        return resultadoFinal;
    }
}
