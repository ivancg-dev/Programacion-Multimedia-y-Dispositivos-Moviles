package com.example.gestionbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    EditText editTextCuenta;
    CheckBox checkBoxPropina;
    SeekBar seekBarPropina;
    TextView textViewPorcentaje, textViewResultado;
    RadioGroup radioGroupPago;
    RatingBar ratingBarServicio;
    Button buttonCalcular;
    AutoCompleteTextView autoCompleteCamarero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCuenta = findViewById(R.id.editTextCuenta);
        checkBoxPropina = findViewById(R.id.checkBoxPropina);
        seekBarPropina = findViewById(R.id.seekBarPropina);
        textViewPorcentaje = findViewById(R.id.textViewPorcentaje);
        textViewResultado = findViewById(R.id.textViewResultado);
        radioGroupPago = findViewById(R.id.radioGroupPago);
        ratingBarServicio = findViewById(R.id.ratingBarServicio);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        autoCompleteCamarero = findViewById(R.id.autoCompleteCamarero);

        // Lista de camareros para el AutoComplete
        String[] camareros = {"Juan", "María", "Pedro", "Lucía", "Carlos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, camareros);
        autoCompleteCamarero.setAdapter(adapter);
        autoCompleteCamarero.setThreshold(3);

        // Listener del SeekBar
        seekBarPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewPorcentaje.setText("Propina: " + progress + "%");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Botón calcular
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotal();
            }
        });
    }

    private void calcularTotal() {
        String cuentaStr = editTextCuenta.getText().toString().trim();

        if (cuentaStr.isEmpty()) {
            textViewResultado.setTextColor(Color.RED);
            textViewResultado.setText("Falta meter el valor de la cuenta");
            return;
        }

        double cuenta;
        try {
            cuenta = Double.parseDouble(cuentaStr);
        } catch (NumberFormatException e) {
            textViewResultado.setTextColor(Color.RED);
            textViewResultado.setText("Formato inválido. Introduce solo números.");
            return;
        }

        if (cuenta <= 0) {
            textViewResultado.setTextColor(Color.RED);
            textViewResultado.setText("El valor de la cuenta debe ser mayor que 0.");
            return;
        }

        double total = cuenta;
        if (checkBoxPropina.isChecked()) {
            int porcentaje = seekBarPropina.getProgress();
            total += cuenta * porcentaje / 100.0;
        }

        int selectedId = radioGroupPago.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        String metodoPago = radioButton.getText().toString();

        float rating = ratingBarServicio.getRating();
        String camarero = autoCompleteCamarero.getText().toString();

        textViewResultado.setTextColor(Color.BLACK);
        textViewResultado.setText("Total: " + total + " €\n" +
                "Método de pago: " + metodoPago + "\n" +
                "Calificación servicio: " + rating + " estrellas\n" +
                "Camarero: " + (camarero.isEmpty() ? "No especificado" : camarero));
    }
}
