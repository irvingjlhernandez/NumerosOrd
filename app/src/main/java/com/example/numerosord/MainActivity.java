package com.example.numerosord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnNext, btnOrdenar, btnEliminar, btnBorrar;
    EditText etNumeros;
    TextView tvValores;
    String saux = null, error = null, advempty = null, advertencia = null, advlon = null,
            valcorrecto = null, valores = null, val = null, noElemento = null, elimino = null, borro = null;
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    int i=0, ancho, alto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();

        display.getSize(size);

        ancho = size.x;
        alto = size.y;

        btnNext = findViewById(R.id.btnNext);
        btnOrdenar = findViewById(R.id.btnOrdenar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnEliminar = findViewById(R.id.btnEliminar);
        etNumeros = findViewById(R.id.etNumeros);
        tvValores = findViewById(R.id.tvValores);

        btnNext.setOnClickListener(this);
        btnOrdenar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);

        advertencia = getResources().getString(R.string.advertencia);
        advempty = getResources().getString(R.string.advempty);
        advlon = getResources().getString(R.string.advlon);
        valcorrecto = getResources().getString(R.string.valcorrecto);
        valores = getResources().getString(R.string.valores);
        val = getResources().getString(R.string.val);
        noElemento = getResources().getString(R.string.noelemento);
        borro = getResources().getString(R.string.borro);
        elimino = getResources().getString(R.string.elimino);
        error = getResources().getString(R.string.error);

        tvValores.setText(valores+" 0 "+val);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNext:
                saux = etNumeros.getText().toString();
                if(noEstaVacio(saux)){
                    if(verificarLongitud(saux)){
                            int num = Integer.parseInt(saux);
                            numeros.add(num);
                            Toast.makeText(MainActivity.this, valcorrecto,Toast.LENGTH_SHORT).show();
                            tvValores.setText(valores+" "+(i+1)+" "+val);
                            etNumeros.setText("");
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(etNumeros.getWindowToken(), 0);
                            i++;
                    }else{
                        Toast.makeText(MainActivity.this, advlon,Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, advempty,Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnOrdenar:
                try{
                    if(i==0){
                        Toast.makeText(MainActivity.this, noElemento+"",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("arreglo", numeros);

                    //Toast.makeText(MainActivity.this, turfc+" "+appat+apmat+nombre+asaux+msaux+dsaux+".",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, Numeros.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, error+".",Toast.LENGTH_SHORT).show();
                    break;
                }
                break;
            case R.id.btnBorrar:
                if(i<=0){
                    Toast.makeText(MainActivity.this, noElemento+"",Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    Toast.makeText(MainActivity.this, borro+" "+numeros.get(i)+".",Toast.LENGTH_SHORT).show();
                    numeros.remove(i);
                    tvValores.setText(valores+" "+i+" "+val);
                }
                break;
            case R.id.btnEliminar:
                if(i==0){
                    Toast.makeText(MainActivity.this, noElemento+"",Toast.LENGTH_SHORT).show();
                }else{
                    i = 0;
                    numeros.clear();
                    Toast.makeText(MainActivity.this, elimino+"",Toast.LENGTH_SHORT).show();
                    tvValores.setText(valores+" "+i+" "+val);
                }
                break;
        }
    }

    protected boolean noEstaVacio(String s){
        if (s.equals("")) return false;
        return true;
    }

    protected boolean verificarLongitud(String s){
        if (Integer.parseInt(s)<10000) return true;
        return false;
    }

}
