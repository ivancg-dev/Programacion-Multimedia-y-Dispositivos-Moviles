package com.example.fragmentoespeciales;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jspecify.annotations.NonNull;

public class MyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Confirmacion")
                .setMessage("¿Estás seguro de que deseas continuar?")
                .setPositiveButton("Si", (dialog, id) -> {
                    Toast.makeText(getContext(), "Continuando...", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        return builder.create();
    }
}
