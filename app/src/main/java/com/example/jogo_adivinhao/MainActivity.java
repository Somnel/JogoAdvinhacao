package com.example.jogo_adivinhao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Components
    private EditText enviar_textfield;
    private TextView result_textview;
    private TextView tentativa_textview;
    private ImageView result_seta_cima;
    private ImageView result_seta_baixo;
    private TextView dica_textview;

    // Variaveis
    private Random rndFunc;
    private int rndNumero;
    private int rndNumero_MAX;
    private int tentativa_value = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void testeRndNumero(View v) {
        final String valString = enviar_textfield.getText().toString();

        if(!valString.isEmpty()) {
            final int val = Integer.parseInt(valString);  // Converte
            Log.d("MainExemplo", "Valor: " + val);
            if(val == rndNumero) {
                reiniciar();
            } else {
                setResult_seta(val < rndNumero);


                tentativa_value++;
                tentativa_textview.setText(String.valueOf(tentativa_value));
            }
        }
    }


    private void setResult_seta(boolean seta) { // 1 : Cima | 0 : Baixo | NULL : Default
        if(seta) {
            result_seta_cima.setImageResource(R.drawable.seta_cima_ativo);
            result_seta_baixo.setImageResource(R.drawable.seta_baixo);
        } else {
            result_seta_cima.setImageResource(R.drawable.seta_cima);
            result_seta_baixo.setImageResource(R.drawable.seta_baixo_ativo);
        }
    }

    public void showDica(View v) {
        dica_textview.setVisibility(View.VISIBLE);
    }

    private void init() {
        enviar_textfield = findViewById(R.id.enviar_textfield);
        dica_textview = findViewById(R.id.dica_textview);

        result_seta_cima = findViewById(R.id.result_seta_cima);
        result_seta_baixo = findViewById(R.id.result_seta_baixo);


        result_textview = findViewById(R.id.result_textview);


        tentativa_textview = findViewById(R.id.tentativa_value);
        tentativa_textview.setText(String.valueOf(tentativa_value));

        rndFunc = new Random();
        rndNumero_MAX = 100;

        final int rndNumero_length = String.valueOf(rndNumero_MAX).length();
        tentativa_textview.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(rndNumero_length)
        });

        reiniciar();
    }

    private void reiniciar() {
        result_seta_baixo.setImageResource(R.drawable.seta_cima);
        result_seta_baixo.setImageResource(R.drawable.seta_baixo);

        dica_textview.setVisibility(View.INVISIBLE);

        tentativa_textview.setText(getString(R.string.tentativasDefaultValue));


        rndNumero = rndFunc.nextInt(rndNumero_MAX) + 1;

        setDica();
        Log.d("MainExemplo", "Número : " + rndNumero);
    }

    private void setDica() {
        String dica = rndNumero % 2 == 0 ? "par" : "impar";
        dica_textview.setText(String.format("O número é %s", dica));
    }
}