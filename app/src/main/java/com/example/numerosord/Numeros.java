package com.example.numerosord;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Numeros extends AppCompatActivity implements View.OnClickListener {

    TextView tvArreglo;
    ImageButton ibAUp, ibADown;
    int i = 0;
    String cadena = "";

    ArrayList<Integer> numeros = new ArrayList<Integer>();
    ArrayList<Integer> ordenados = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);

        ibAUp = findViewById(R.id.ibAUp);
        ibADown = findViewById(R.id.ibADown);
        tvArreglo = findViewById(R.id.tvArreglo);

        ibAUp.setOnClickListener(this);
        ibADown.setOnClickListener(this);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        numeros = bundle.getIntegerArrayList("arreglo");
        ordenar(numeros,0,numeros.size()-1);
        for(i=0;i<numeros.size();i++){
            cadena = cadena+numeros.get(i)+"\n"+"\n";
        }
        tvArreglo.setText(cadena);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibAUp:
                cadena = "";
                ordenar(numeros,0,numeros.size()-1);
                ordenados = reverse(numeros);
                for(i=0;i<ordenados.size();i++){
                    cadena = cadena+ordenados.get(i)+"\n"+"\n";
                }
                tvArreglo.setText(cadena);
                break;

            case R.id.ibADown:
                cadena = "";
                ordenar(numeros,0,numeros.size()-1);
                for(i=0;i<numeros.size();i++){
                    cadena = cadena+numeros.get(i)+"\n"+"\n";
                }
                tvArreglo.setText(cadena);
                break;
            default:
                break;
        }
    }

    //Metodo de ordenamiento QuickSort
    protected boolean ordenar(ArrayList<Integer> vector, int first, int last){
        int i=first, j=last;
        int pivote=vector.get((first + last) / 2);
        int auxiliar;
        do
        {
            while(vector.get(i)<pivote) i++;
            while(vector.get(j)>pivote) j--;
            if (i<=j)
            {
                auxiliar=vector.get(j);
                vector.add(j,vector.get(i));
                vector.remove(j+1);
                vector.add(i,auxiliar);
                vector.remove(i+1);
                i++;
                j--;
            }
        }
        while (i<=j);
        if(first<j)
        {
            ordenar(vector,first, j);
        }
        if(last>i)
        {
            ordenar(vector,i, last);
        }
        return true;
    }

    //Metodo para invertir el orden de un arreglo
    protected ArrayList<Integer> reverse(ArrayList<Integer> source) {
        ArrayList<Integer> reverse;

        if (source != null) {

            reverse = new ArrayList<>();

            for (int i = source.size()-1; i>=0; i--) {

                reverse.add(source.get(i));
            }
        } else {
            reverse = null;
        }
        return reverse;
    }
}
