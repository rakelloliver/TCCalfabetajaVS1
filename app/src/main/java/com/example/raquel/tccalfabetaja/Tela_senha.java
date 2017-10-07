package com.example.raquel.tccalfabetaja;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.com.exemple.raquel.entidade.DadosSenha;

public class Tela_senha extends AppCompatActivity implements View.OnClickListener {
    EditText editTextSenha;
    Button buttonsenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_senha);

        editTextSenha = (EditText)findViewById(R.id.editTextSenha);
        buttonsenha = (Button)findViewById(R.id.buttonSenha);
        buttonsenha.setOnClickListener(this);

        //BancoDados bd = new BancoDados(this);
    }

    @Override
    public void onClick(View view) {
        DadosSenha ds = new DadosSenha();
        BancoDados bd = new BancoDados(getBaseContext());

        ds.setSenha(editTextSenha.getText().toString());
        if (bd.insereSenha(ds)){
            Intent intent2 = new Intent(Tela_senha.this, Tela_sistema.class);
            startActivity(intent2);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("fail");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    }
}