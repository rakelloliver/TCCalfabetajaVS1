    package com.example.raquel.tccalfabetaja;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.com.exemple.raquel.entidade.DadosCrianca;

import java.io.IOException;

    public class Telainicial extends AppCompatActivity implements View.OnClickListener {
        EditText EditTextNome;
        EditText EditTextIdade;
        Button buttonProximo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telainicial);



        EditTextNome = (EditText)findViewById(R.id.EditTextNome);
        EditTextIdade= (EditText)findViewById(R.id.EditTextIdade);
        buttonProximo = (Button)findViewById(R.id.buttonProximo);
        buttonProximo.setOnClickListener(this);





        BancoDados bd = new BancoDados(this);
        try {
            bd.create();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private boolean validaCampos(){

            boolean res =  false;
            String nomeCrianca = EditTextNome.getText().toString();
           // int idade = Integer.parseInt(EditTextIdade.getText().toString());
            //String idades = EditTextIdade.getText().toString();

            if(res = isCampoVazio(nomeCrianca)){
                EditTextNome.requestFocus();
            }
            // else if(res = isCampoVazio(idade)) {
             //    EditTextIdade.requestFocus();
            //}
           // else if(res = isTamanho(idade)){
             //   EditTextIdade.requestFocus();
            //}

            if(res){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Ha campos invalidos ou em branco!");
                dlg.setNeutralButton("Ok", null);
                dlg.show();
            }
            return res;

        }
        public boolean verificaIdade() {
            boolean id = true;
            if (EditTextIdade.getText().toString().length() > 2) {
                EditTextIdade.setError("Error");
                Toast.makeText(getApplicationContext(), "Idade não pode ter mais que 2 caracteres", Toast.LENGTH_SHORT).show();
                id = false;
            }
            return id;
        }
        private boolean isCampoVazio(String valor){
            boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty() );

            return result;
        }

        /*private boolean isTamanho(int idade1){
            boolean result = false;
            if(idade1 > 9){
                result = false;
            }else {
                result = true;
            }
            return result;
        }*/

        public void onClick(View v) {
            DadosCrianca dc = new DadosCrianca();
            BancoDados bd = new BancoDados(getBaseContext());

            dc.setNomeCrianca(EditTextNome.getText().toString());
            dc.setIdade(Integer.parseInt(EditTextIdade.getText().toString()));
            if (bd.insereData(dc) && validaCampos() == false && verificaIdade()){
                Intent intent1 = new Intent(Telainicial.this, Tela_senha.class);
                startActivity(intent1);
            }
        }

    }

