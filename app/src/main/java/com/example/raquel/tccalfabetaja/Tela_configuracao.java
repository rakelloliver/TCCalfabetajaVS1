package com.example.raquel.tccalfabetaja;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.com.exemple.raquel.entidade.DadosCrianca;

import java.util.List;

public class Tela_configuracao extends AppCompatActivity {

    Button buttonC;

    //ListView ListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_configuracao);





                buttonC = (Button) findViewById(R.id.buttonC);
              //  textIdade = (TextView)findViewById(R.id.textIdade);


                BancoDados bd = new BancoDados(this);

                List<DadosCrianca> dd = bd.Dados();

                   // buttonC.setText(dd.get(1).getNomeCrianca());
                  //  textIdade.setText(dd.get(1).getNomeCrianca());


        for(int i = 0; i < dd.size(); i++){

            RelativeLayout buttonL = (RelativeLayout)findViewById(R.id.buttonL);

            Button button = new Button(this);
            button.setText(dd.get(i).getNomeCrianca());

            buttonL.addView(button);





        }



    }

    public void onClickCrianca (View view){
        String[] items={"Editar","Excluir"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Tela_configuracao.this);
        builder.setTitle("escolha a opção:")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
                    builder.show();



           /* AlertDialog.Builder builder = new AlertDialog.Builder(Tela_configuracao.this);
            builder.setCancelable(true);
            builder.setPositiveButton("OK", null);

           // builder.setView(ListView);

            AlertDialog dialog = builder.create();

            dialog.show();*/

        }

}
