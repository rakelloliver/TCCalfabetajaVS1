package com.example.raquel.tccalfabetaja;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.com.exemple.raquel.entidade.DadosSenha;

import static com.example.raquel.tccalfabetaja.R.id.editTextSenha;

public class Tela_sistema extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageButton_Configuracao;
    Tela_senha ts = new Tela_senha();
    DadosSenha ds = new DadosSenha();
    String senha = "asd";
    String senha_digitada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sistema);

        imageButton_Configuracao = (ImageButton) findViewById(R.id.imageButton_Configuracao);
        imageButton_Configuracao.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Title");
        alert.setMessage("Message");


        final EditText input = new EditText(this);
        alert.setView(input);

        AlertDialog.Builder builder = alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                BancoDados bd = new BancoDados(getBaseContext());

                String sen = bd.consultaSenha();
                senha_digitada = input.getText().toString();
                if(senha_digitada.equals(sen)){
                    Intent intent2 = new Intent(Tela_sistema.this, Tela_configuracao.class);
                    startActivity(intent2);
                }else{
                    Toast.makeText(getApplicationContext(), "Acesso negado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();


    }
}
