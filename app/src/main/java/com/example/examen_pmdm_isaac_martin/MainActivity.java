package com.example.examen_pmdm_isaac_martin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declaración de EditText
    EditText edtNombre;

    // Declaración RadioGroup
    RadioGroup rbtgGenero;
    RadioGroup rbtgRespuestas;

    // Declaración de SwitchCompat
    SwitchCompat swMayorEdad;

    // Declaración de Spinner
    Spinner spnPreguntas;

    // Declaración de ArrayAdapter
    ArrayAdapter<CharSequence> adapterSpnMenorEdad;
    ArrayAdapter<CharSequence> adapterSpnMayorEdad;

    // Declaración de CheckBox
    CheckBox chkMostrarLogs;

    // Array de String para almacenar las respuestas
    String[] arrayRespuestas;

    // Declaración de RadioButton para las respuestas
    RadioButton rbtRespuestaUno;
    RadioButton rbtRespuestaDos;
    RadioButton rbtRespuestaTres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Acomplamiento de RadioButton de las respuestas a XML
        rbtRespuestaUno = findViewById(R.id.rbtRespuestaUno);
        rbtRespuestaDos = findViewById(R.id.rbtRespuestaDos);
        rbtRespuestaTres = findViewById(R.id.rbtRespuestaTres);

        arrayRespuestas = new String[]{
                getText(R.string.rbt_menor_edad_respuesta_1).toString(),
                getText(R.string.rbt_menor_edad_respuesta_2).toString(),
                getText(R.string.rbt_menor_edad_respuesta_3).toString(),
                getText(R.string.rbt_menor_edad_respuesta_4).toString(),
                getText(R.string.rbt_menor_edad_respuesta_5).toString(),
                getText(R.string.rbt_menor_edad_respuesta_6).toString(),
                getText(R.string.rbt_menor_edad_respuesta_1).toString(),
                getText(R.string.rbt_menor_edad_respuesta_2).toString(),
                getText(R.string.rbt_menor_edad_respuesta_3).toString(),
                getText(R.string.rbt_menor_edad_respuesta_4).toString(),
                getText(R.string.rbt_menor_edad_respuesta_5).toString(),
                getText(R.string.rbt_menor_edad_respuesta_6).toString(),
        };

        // Acoplamiento de EditText a XML
        edtNombre = findViewById(R.id.edtNombre);

        // Activar el foco en el EditText
        edtNombre.requestFocus();

        // Acoplamiento de RadioGroup a XML
        rbtgGenero = findViewById(R.id.rbtgGenero);
        rbtgRespuestas = findViewById(R.id.rbtgRespuestas);

        // Acoplamiento de SwitchCompat a XML
        swMayorEdad = findViewById(R.id.swMayorEdad);

        // Listener para el SwitchCompat
        swMayorEdad.setOnCheckedChangeListener((buttonView, isChecked) -> cambiarAdaptador(isChecked));

        // Acoplamiento de CheckBox a XML
        chkMostrarLogs = findViewById(R.id.chkMostrarLogs);

        // Acoplamiento de Spinner a XML
        spnPreguntas = findViewById(R.id.spnPreguntas);

        // ArrayAdapter para los Spinner
        adapterSpnMenorEdad = ArrayAdapter.createFromResource(this, R.array.spn_menor_edad, android.R.layout.simple_spinner_item);
        adapterSpnMayorEdad = ArrayAdapter.createFromResource(this, R.array.spn_mayor_edad, android.R.layout.simple_spinner_item);

        // Estilo para el desplegable de los Spinner
        adapterSpnMenorEdad.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapterSpnMayorEdad.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Vinculación del Adapter al Spinner
        cambiarAdaptador(swMayorEdad.isChecked());

        // Listener para el Spinner
        spnPreguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarRespuestas(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Listener para el Button "Enviar Repuesta"
        findViewById(R.id.btnEnviarRespuesta).setOnClickListener(v -> enviarRespuesta());
    }

    // Método para almacenar el contenido del Log en caso de que no haya fallos
    protected void mostrarLogsEnCasoCorrecto(){
        Log.i("Martín", edtNombre.getText().toString()
                + "\n" + rbtgGenero.getCheckedRadioButtonId()
                + "\n" + spnPreguntas.getSelectedItem().toString()
                + "\n" + rbtgRespuestas.getCheckedRadioButtonId());
    }

    // Método para el Button "Enviar Repuesta"
    protected void enviarRespuesta(){
        if(edtNombre.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Es obligatorio el nombre", Toast.LENGTH_SHORT).show();
        }else if(rbtgRespuestas.getCheckedRadioButtonId() == -1){
            Toast.makeText(MainActivity.this, "Ninguna respuesta ha sido marcada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Respuesta enviada", Toast.LENGTH_LONG).show();
            mostrarLogsEnCasoCorrecto();
        }
    }

    // Método para cambiar el adaptador según el SwitchCompat "Es mayor de edad" está check o no
    protected void cambiarAdaptador(boolean isChecked){
        if(swMayorEdad.isChecked()){
            spnPreguntas.setAdapter(adapterSpnMayorEdad);
        }else{
            spnPreguntas.setAdapter(adapterSpnMenorEdad);
        }
    }

    // Método para cambiar las respuestas en el RadioGroup. Recibe como parámetro la posición del item seleccionado en el Spinner
    protected void cambiarRespuestas(int pos){
        if(!swMayorEdad.isChecked()){
            switch (pos){
                case 0: // Respuestas de los RadioButton a la pregunta 1 en "Mayor Edad"
                    rbtRespuestaUno.setText(arrayRespuestas[0]);
                    rbtRespuestaDos.setText(arrayRespuestas[1]);
                    rbtRespuestaTres.setText(arrayRespuestas[2]);
                    break;
                case 1: // Respuestas de los RadioButton a la pregunta 2 en "Mayor Edad"
                    rbtRespuestaUno.setText(arrayRespuestas[3]);
                    rbtRespuestaDos.setText(arrayRespuestas[4]);
                    rbtRespuestaTres.setText(arrayRespuestas[5]);
                    break;
            }
        }else{
            switch (pos){
                case 0: // Respuestas de los RadioButton a la pregunta 1 en "Menor Edad"
                    rbtRespuestaUno.setText(arrayRespuestas[6]);
                    rbtRespuestaDos.setText(arrayRespuestas[7]);
                    rbtRespuestaTres.setText(arrayRespuestas[8]);
                    break;
                case 1: // Respuestas de los RadioButton a la pregunta 2 en "Menor Edad"
                    rbtRespuestaUno.setText(arrayRespuestas[9]);
                    rbtRespuestaDos.setText(arrayRespuestas[10]);
                    rbtRespuestaTres.setText(arrayRespuestas[11]);
                    break;
            }
        }
    }
}