package com.example.practicatema6;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Código para solicitar el permiso de notificaciones
    private static final int REQ_POST_NOTIFICATIONS = 100;
    // Lista de eventos
    private ArrayList<Evento> eventos;

    // Adaptador para mostrar los nombres de los eventos en el ListView
    private ArrayAdapter<String> adapter;

    // Lista visual en la interfaz para mostrar los eventos
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventos = new ArrayList<>();
        // Inicializa la lista de eventos
        listView = findViewById(R.id.listViewEventos);
        // Conecta la lista de la interfaz con la variable
        Button btnAgregar = findViewById(R.id.btnAgregarEvento);
        // Botón para agregar un nuevo evento

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(adapter);
        // Configura el ListView para mostrar los nombres de los eventos

        // Acción al presionar el botón "Agregar"
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoNombre();
                // Muestra un diálogo para ingresar el nombre del evento
            }
        });

        // Acción al seleccionar un elemento de la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarToastPersonalizado(eventos.get(position));
                // Muestra un toast personalizado con la info del evento seleccionado
            }
        });
    }

    // Muestra un diálogo para ingresar el nombre del evento
    private void mostrarDialogoNombre() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nombre del evento");

        final EditText input = new EditText(this);
        builder.setView(input);
        // Agrega un campo de texto al diálogo

        builder.setPositiveButton("Siguiente", (dialog, which) -> {
            String nombre = input.getText().toString();
            mostrarDatePicker(nombre);
            // Pasa el nombre ingresado y continúa al selector de fecha
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
        // Muestra la opción de cancelar
    }

    // Muestra un selector de fecha
    private void mostrarDatePicker(final String nombre) {
        final Calendar calendar = Calendar.getInstance();
        // Obtiene la fecha y hora actuales
        DatePickerDialog datePicker = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                    mostrarTimePicker(nombre, fecha);
                    // Pasa al selector de hora
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
        // Muestra el diálogo de selección de fecha
    }

    // Muestra un selector de hora
    private void mostrarTimePicker(final String nombre, final String fecha) {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePicker = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    String hora = String.format("%02d:%02d", hourOfDay, minute);
                    Evento evento = new Evento(nombre, fecha, hora);
                    agregarEvento(evento);
                    // Crea el evento completo y lo agrega a la lista
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true); // true = formato 24 horas
        timePicker.show();
        // Muestra la selección de hora
    }

    // Agrega un evento a la lista y se actualiza la lista
    private void agregarEvento(Evento evento) {
        eventos.add(evento);
        // Agrega el evento a la lista interna

        // Crea una lista solo con los nombres de los eventos para el ListView
        ArrayList<String> nombres = new ArrayList<>();
        for (Evento e : eventos) {
            nombres.add(e.getNombre());
        }

        adapter.clear();
        adapter.addAll(nombres);
        adapter.notifyDataSetChanged();
        // Actualiza el ListView con los nuevos datos

        if (puedeNotificacion()) {
            mostrarNotificacion(evento);
        } else {
            requestNotificacion();
        }
        // Muestra una notificación con la información del evento
    }

    // Solicita el permiso de notificaciones al usuario
    private void requestNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    REQ_POST_NOTIFICATIONS
            );
        }
    }

    // Comprueba si tenemos permiso para mostrar notificaciones
    private boolean puedeNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    // Muestra una notificación del evento agregado
    private void mostrarNotificacion(Evento evento) {
        String channelId = "evento_channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Se crea el canal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Eventos", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        // Se crea la acción que va a ocurrir cuando se toque la notificación
        Intent intent = new Intent(this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        // Configura la notificación con título, icono, texto y acción al tocarla
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_view_agenda_24)
                .setContentTitle(evento.getNombre())
                .setContentText(evento.getFecha() + " " + evento.getHora())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        // La notificación desaparece al tocarla

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
        // Muestra la notificación
    }

    // Muestra un toast personalizado con la información del evento
    private void mostrarToastPersonalizado(Evento evento) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, null);
        // Carga el layout personalizado para el toast

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(evento.getNombre() + "\n" + evento.getFecha() + " " + evento.getHora());

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        // Muestra el toast personalizado en pantalla
    }
}
