package com.example.jgchan.ej02_calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean primerDigito=true;
    private double acumulador =0.0f,memoria=0;
    private boolean tienePunto=false;
    private String operador="=";
    EditText Display;
    String s="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display=(EditText)findViewById(R.id.edtDisplay);
        moverPuntero();
    }

    public void manejadorBotones(View v){
        Button boton = (Button)v;

        String tecla = boton.getText().toString();

        if(".0123456789".indexOf(tecla)>=0){

            if(primerDigito){

                if(("0".indexOf(tecla)>=0)){
                    Display.setText("0");

                }else if(tecla.equalsIgnoreCase(".")){

                    if(!tienePunto){
                        Display.setText("0.");
                        tienePunto=true;
                        primerDigito=false;
                    }

                }else{
                    Display.setText(tecla);
                    primerDigito=false;
                }

            }else{

                if(tecla.equalsIgnoreCase(".")){
                    if(!tienePunto) {
                        s = Display.getText().toString();
                        Display.setText(s + tecla);
                        tienePunto=true;
                    }
                }else{
                    s = Display.getText().toString();
                    Display.setText(s + tecla);
                }

            }

        }else{

            if(!primerDigito){
                calcular(Display.getText().toString());
                primerDigito=true;
            }

            tienePunto=false;
            operador= tecla;
        }

        moverPuntero();
    }

    private void calcular(String s) {
        double valor = Double.parseDouble(s);
        char op = operador.charAt(0);

        switch (op){
            case '+':
                acumulador+=valor;
                break;
            case '-':
                acumulador-=valor;
                break;
            case '*':
                acumulador*=valor;
                break;
            case '/':
                acumulador/=valor;
                break;
            case '=':
                acumulador=valor;
                break;
        }

        Display.setText(""+acumulador);
    }


    public  void borrar(View v){
        Display.setText("0");
        acumulador=0;
        primerDigito=true;
        tienePunto=false;
        operador="=";
        moverPuntero();
    }

    public  void borrarUltimo(View v){
        String s= Display.getText().toString();

        if(s.length()-1>0 && !s.equalsIgnoreCase("0")){

            if(!(s.substring(0,s.length()-1).indexOf(".")>0)){
                tienePunto=false;
            }
            Display.setText(s.substring(0,s.length()-1));
        }
        else{
            Display.setText("0");
            primerDigito=true;
        }
        moverPuntero();
    }

    public  void guardarEnMemoria(View v){
        Toast.makeText(this, "Guardado en memoria", Toast.LENGTH_SHORT).show();
        String s= Display.getText().toString();
        memoria=Double.parseDouble(s);
    }

    public  void desplegarMemoria(View v){
        Display.setText(""+memoria);
        calcular(Display.getText().toString());
        primerDigito=true;
    }


    public void moverPuntero(){
        Display.setSelection(Display.getText().length());
    }



}