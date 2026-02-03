package com.example.materialdesignapp_tarea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Stack;

public class Fragmento3 extends Fragment {

    private TextView texto;
    private String input = "";
    private boolean puedeEscribirDecimal = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragmento3, container, false);

        texto = view.findViewById(R.id.text_resultado);
        texto.setText("0");

        // Números 0–9
        Button[] botonesNum = {
                view.findViewById(R.id.btn0),
                view.findViewById(R.id.btn1),
                view.findViewById(R.id.btn2),
                view.findViewById(R.id.btn3),
                view.findViewById(R.id.btn4),
                view.findViewById(R.id.btn5),
                view.findViewById(R.id.btn6),
                view.findViewById(R.id.btn7),
                view.findViewById(R.id.btn8),
                view.findViewById(R.id.btn9)
        };

        for (int i = 0; i < botonesNum.length; i++) {
            int finalI = i;
            botonesNum[i].setOnClickListener(v -> {
                String text = texto.getText().toString();

                if (text.equals("0")) {
                    if (finalI == 0) return;
                    input = String.valueOf(finalI);
                    texto.setText(input);
                    return;
                }

                char ultimo = text.charAt(text.length() - 1);
                if (esOperador(ultimo)) {
                    input = text + finalI;
                    texto.setText(input);
                    return;
                }

                String[] separacion = text.split("[+\\-*/]");
                int n = separacion.length;

                if (separacion[n - 1].startsWith("0") && finalI == 0) return;

                if (separacion[n - 1].startsWith("0") && finalI != 0 && !separacion[n - 1].startsWith("0.")) {
                    String textSecundario = text.substring(0, text.length() - 1);
                    input = textSecundario + finalI;
                    texto.setText(input);
                    return;
                }

                input = text + finalI;
                texto.setText(input);
            });
        }

        // Operadores
        view.findViewById(R.id.btnSum).setOnClickListener(v -> setOperador("+"));
        view.findViewById(R.id.btnRes).setOnClickListener(v -> setOperador("-"));
        view.findViewById(R.id.btnMul).setOnClickListener(v -> setOperador("*"));
        view.findViewById(R.id.btnDiv).setOnClickListener(v -> setOperador("/"));

        // Decimal
        view.findViewById(R.id.btnDec).setOnClickListener(v -> {
            String text = texto.getText().toString();

            if (text.isEmpty() || esOperador(text.charAt(text.length() - 1))) {
                input = text + "0.";
                texto.setText(input);
                puedeEscribirDecimal = false;
                return;
            }

            String ultimoNumero = obtenerUltimoNumero(text);
            if (!ultimoNumero.contains(".")) {
                input = text + ".";
                texto.setText(input);
                puedeEscribirDecimal = false;
            }
        });

        // Borrar último
        view.findViewById(R.id.btnC).setOnClickListener(v -> {
            String original = texto.getText().toString();
            if (!original.isEmpty() && !original.equals("0")) {
                input = original.substring(0, original.length() - 1);
                if (input.isEmpty()) input = "0";
                texto.setText(input);
            }
        });

        // Borrar todo
        view.findViewById(R.id.btnAC).setOnClickListener(v -> {
            input = "";
            texto.setText("0");
            puedeEscribirDecimal = true;
        });

        // Igual
        view.findViewById(R.id.btnIg).setOnClickListener(v -> {
            String expresion = texto.getText().toString();

            if (expresion.isEmpty() || esOperador(expresion.charAt(expresion.length() - 1))) return;

            try {
                double resultado = evaluarExpresion(expresion);
                if (resultado == (long) resultado) {
                    texto.setText(String.valueOf((long) resultado));
                } else {
                    texto.setText(String.valueOf(resultado));
                }
                input = texto.getText().toString();
                puedeEscribirDecimal = true;
            } catch (Exception e) {
                texto.setText("Error");
                input = "";
            }
        });

        return view;
    }

    // ------------------------------
    // MÉTODOS AUXILIARES
    // ------------------------------

    public void setOperador(String operador) {
        String text = texto.getText().toString();
        if (text.isEmpty()) return;

        char ultimoCaracter = text.charAt(text.length() - 1);
        if (esOperador(ultimoCaracter)) {
            input = text.substring(0, text.length() - 1) + operador;
        } else {
            input = text + operador;
        }
        texto.setText(input);
        puedeEscribirDecimal = true;
    }

    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private String obtenerUltimoNumero(String texto) {
        int i = texto.length() - 1;
        while (i >= 0 && !esOperador(texto.charAt(i))) i--;
        return texto.substring(i + 1);
    }

    private double evaluarExpresion(String expr) {
        expr = expr.replace(",", ".");
        Stack<Double> valores = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ') continue;

            if ((c >= '0' && c <= '9') || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && ((expr.charAt(i) >= '0' && expr.charAt(i) <= '9') || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                i--;
                valores.push(Double.parseDouble(sb.toString()));
            } else if (esOperador(c)) {
                while (!ops.isEmpty() && prioridad(ops.peek()) >= prioridad(c)) {
                    double val2 = valores.pop();
                    double val1 = valores.pop();
                    char op = ops.pop();
                    valores.push(aplicarOperacion(val1, val2, op));
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            double val2 = valores.pop();
            double val1 = valores.pop();
            char op = ops.pop();
            valores.push(aplicarOperacion(val1, val2, op));
        }

        return valores.pop();
    }

    private int prioridad(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private double aplicarOperacion(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return b == 0 ? 0 : a / b;
        }
        return 0;
    }
}
