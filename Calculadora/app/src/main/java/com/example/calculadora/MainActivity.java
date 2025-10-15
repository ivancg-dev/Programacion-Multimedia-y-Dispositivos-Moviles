package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

        // Números
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
                        // Si se pulsa otro "0", no hacemos nada (evita 00, 000...)
                        return;
                    } else {
                        // Si se pulsa otro número distinto de 0, reemplazamos el "0"
                        input = String.valueOf(finalI);
                        texto.setText(input);
                        return;
                    }
                }

                // Si el último carácter es un operador, empieza un nuevo número
                char ultimo = text.charAt(text.length() - 1);
                if (esOperador(ultimo)) {
                    // Si el usuario presiona 0 justo después de un operador, sí se permite "0"
                    if (finalI == 0) {
                        input = text + "0";
                    } else {
                        input = text + finalI;
                    }
                    texto.setText(input);
                    return;
                }

                // Caso general: agregar el número al final
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

        // Botón borrar último
        Button botonC = findViewById(R.id.btnC);
        botonC.setOnClickListener(v -> {
            String original = texto.getText().toString();
            if (!original.isEmpty() && !original.equals("0")) {
                input = original.substring(0, original.length() - 1);
                if (input.isEmpty()) input = "0";
                texto.setText(input);
            }
        });

        Button botonAC = findViewById(R.id.btnAC);
        botonAC.setOnClickListener(v -> {
            input = "";
            texto.setText("0");
            puedeEscribirDecimal = true;
        });
    }

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
        while (i >= 0 && !esOperador(texto.charAt(i))) {
            i--;
        }
        return texto.substring(i + 1);
    }
}
