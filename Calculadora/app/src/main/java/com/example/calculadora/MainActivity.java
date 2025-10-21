package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private String input = "";
    private boolean puedeEscribirDecimal = true; // Controla una sola coma por número

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_layout);

        texto = findViewById(R.id.text_resultado);
        texto.setText("0");

        // Números 0–9
        Button[] botonesNum = {
                findViewById(R.id.btn0),
                findViewById(R.id.btn1),
                findViewById(R.id.btn2),
                findViewById(R.id.btn3),
                findViewById(R.id.btn4),
                findViewById(R.id.btn5),
                findViewById(R.id.btn6),
                findViewById(R.id.btn7),
                findViewById(R.id.btn8),
                findViewById(R.id.btn9)
        };

        for (int i = 0; i < botonesNum.length; i++) {
            int finalI = i;
            botonesNum[i].setOnClickListener(v -> {
                String text = texto.getText().toString();

                // Si actualmente el texto es "0"
                if (text.equals("0")) {
                    if (finalI == 0) {
                        return;
                    } else {
                        input = String.valueOf(finalI);
                        texto.setText(input);
                        return;
                    }
                }

                // Si el último carácter es un operador
                char ultimo = text.charAt(text.length() - 1);
                if (esOperador(ultimo)) {
                    input = text + finalI;
                    texto.setText(input);
                    return;
                }
                String[] separacion = text.split("[+\\-*/]");
                int n = separacion.length;
                // Caso general: concatenar número
                if (separacion[n-1].startsWith("0") && finalI == 0){
                    return; // Evita 00, 000...
                }else if(separacion[n-1].startsWith("0") && finalI != 0){
                    String textSecundario = text.substring(0, text.length() - 1);
                    System.out.println(textSecundario);
                    input = textSecundario + finalI;
                    texto.setText(input);
                    return;
                }
                input = text + finalI;
                texto.setText(input);
            });
        }

        // Botones de operaciones
        Button botonSum = findViewById(R.id.btnSum);
        botonSum.setOnClickListener(v -> setOperador("+"));

        Button botonRes = findViewById(R.id.btnRes);
        botonRes.setOnClickListener(v -> setOperador("-"));

        Button botonMul = findViewById(R.id.btnMul);
        botonMul.setOnClickListener(v -> setOperador("*"));

        Button botonDiv = findViewById(R.id.btnDiv);
        botonDiv.setOnClickListener(v -> setOperador("/"));

        // Control de coma decimal
        Button botonDec = findViewById(R.id.btnDec);
        botonDec.setOnClickListener(v -> {
            String text = texto.getText().toString();

            // Si el último carácter es operador, empezamos nuevo número con "0."
            if (text.isEmpty() || esOperador(text.charAt(text.length() - 1))) {
                input = text + "0.";
                texto.setText(input);
                puedeEscribirDecimal = false;
                return;
            }

            // Verifica si el número actual ya tiene un punto
            String ultimoNumero = obtenerUltimoNumero(text);
            if (!ultimoNumero.contains(".")) {
                input = text + ".";
                texto.setText(input);
                puedeEscribirDecimal = false;
            }
        });

        // Botón borrar último (C)
        Button botonC = findViewById(R.id.btnC);
        botonC.setOnClickListener(v -> {
            String original = texto.getText().toString();
            if (!original.isEmpty() && !original.equals("0")) {
                input = original.substring(0, original.length() - 1);
                if (input.isEmpty()) input = "0";
                texto.setText(input);
            }
        });

        // Botón borrar todo (AC)
        Button botonAC = findViewById(R.id.btnAC);
        botonAC.setOnClickListener(v -> {
            input = "";
            texto.setText("0");
            puedeEscribirDecimal = true;
        });

        // Botón igual "="
        Button botonIgual = findViewById(R.id.btnIg );
        botonIgual.setOnClickListener(v -> {
            String expresion = texto.getText().toString();

            // Evitar evaluar expresiones vacías o terminadas en operador
            if (expresion.isEmpty() || esOperador(expresion.charAt(expresion.length() - 1))) {
                return;
            }

            try {
                double resultado = evaluarExpresion(expresion);
                // Eliminar ceros innecesarios (ej: 5.0 → 5)
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
    }

    // Inserta operador matemático (+, -, *, /)
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

    // Comprueba si un carácter es un operador
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Obtiene el último número de la expresión actual
    private String obtenerUltimoNumero(String texto) {
        int i = texto.length() - 1;
        while (i >= 0 && !esOperador(texto.charAt(i))) {
            i--;
        }
        return texto.substring(i + 1);
    }

    /**
     * Evalúa una expresión matemática con +, -, *, /
     * usando dos pilas (operadores y operandos)
     */
    private double evaluarExpresion(String expr) {
        expr = expr.replace(",", ".");
        Stack<Double> valores = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // Saltar espacios
            if (c == ' ') continue;

            // Si es número o punto decimal
            if ((c >= '0' && c <= '9') || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && ((expr.charAt(i) >= '0' && expr.charAt(i) <= '9') || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                i--;
                valores.push(Double.parseDouble(sb.toString()));
            }
            // Si es operador
            else if (esOperador(c)) {
                while (!ops.isEmpty() && prioridad(ops.peek()) >= prioridad(c)) {
                    double val2 = valores.pop();
                    double val1 = valores.pop();
                    char op = ops.pop();
                    valores.push(aplicarOperacion(val1, val2, op));
                }
                ops.push(c);
            }
        }

        // Aplicar los operadores restantes
        while (!ops.isEmpty()) {
            double val2 = valores.pop();
            double val1 = valores.pop();
            char op = ops.pop();
            valores.push(aplicarOperacion(val1, val2, op));
        }

        return valores.pop();
    }

    // Devuelve prioridad del operador
    private int prioridad(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    // Aplica la operación entre dos valores
    private double aplicarOperacion(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return b == 0 ? 0 : a / b; // evita dividir por 0
        }
        return 0;
    }
}
