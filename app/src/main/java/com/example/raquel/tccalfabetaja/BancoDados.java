package com.example.raquel.tccalfabetaja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.com.exemple.raquel.entidade.DadosCrianca;
import com.com.exemple.raquel.entidade.DadosSenha;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.OPEN_READWRITE;
import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by raquel on 17/09/17.
 */

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CRIANCA= "bd_crianca";

    private static final String TABELA_CRIANCA = "tb_crianca";
    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_IDADE = "idade";
    private static final String COLUNA_MODULO = "IDModulo";
    private static final String COLUNA_SENHA = "senha";
    private static final String COLUNA_CODSENHA = "CodSenha";
    private static final String TABELA_SENHA = "tb_senha";



    private static String path;
    private Context context;
    private SQLiteDatabase sDB;




    public BancoDados(Context context) {
        super(context, BANCO_CRIANCA, null, VERSAO_BANCO);

        if(android.os.Build.VERSION.SDK_INT >= 17){
            path = context.getApplicationInfo().dataDir + "/databases/";
        }else{
            path = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.context = context;

    }



    public void create()throws IOException {
        boolean bancoExist = VerificaBD();

        if(bancoExist){

        }else{
            // criando um banco em branco
            this.getReadableDatabase();

            try{
                copiaBD();
            }catch (IOException  e){
                throw new Error("Erro ao copiar banco");
            }
        }
    }

    private boolean VerificaBD(){
        SQLiteDatabase verificaBD = null;
        try{
            String caminho = path + BANCO_CRIANCA;
            verificaBD = SQLiteDatabase.openDatabase(caminho,null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        if(verificaBD != null){
            verificaBD.close();
        }
        return verificaBD != null ? true : false;
    }



    //Copiar banco para novo arquivo
    private void copiaBD()throws IOException{
        //cria um canal de entrada
        InputStream myInput = context.getAssets().open("bd_crianca");
        //estancia o endereço de canal de saida
        String saidaDados = path + "bd_crianca";

        OutputStream myOut = new FileOutputStream(saidaDados);

        // define tamanho de dados de transferencia
        byte[] buffer = new byte[1024];
        // cria variavel int para receber tamanho do arquivo bd na pasta assets em bytes
        int comprimento;
        while ((comprimento = myInput.read(buffer)) > 0){
            myOut.write(buffer, 0, comprimento);
        }
        myOut.flush();
        myOut.close();
        myInput.close();
    }


    public boolean abrir(){
        try{
            String meu_caminho = path + BANCO_CRIANCA;
            sDB = openDatabase(meu_caminho, null, OPEN_READWRITE);
            return true;
        }catch (SQLiteException sqle){
            sDB = null;
            return false;
        }
    }



    public synchronized void close(){
        if(sDB != null)
            sDB.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<DadosCrianca> Dados(){
        ArrayList<DadosCrianca> dd = new ArrayList<>();

        try{
            String query = "SELECT * FROM  tb_crianca";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(path + BANCO_CRIANCA, null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);

            while(cursor.moveToNext()){
                DadosCrianca dadosCrianca = new DadosCrianca();

                dadosCrianca.setNomeCrianca(cursor.getString(1));


                dd.add(dadosCrianca);
            }

            }catch (Exception e){
                System.out.println(e);
        }
        return dd;
    }

    public boolean insereData(DadosCrianca dadosCrianca){
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUNA_NOME, dadosCrianca.getNomeCrianca());
            contentValues.put(COLUNA_IDADE, dadosCrianca.getIdade());
            contentValues.put(COLUNA_MODULO, 1);

            db.insertOrThrow(TABELA_CRIANCA, null,contentValues);

            db.close();

            return true;


        }catch(Exception e){
            return false;
        }
    }
    public boolean insereSenha(DadosSenha dadosSenha){
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUNA_SENHA, dadosSenha.getSenha());

            db.insertOrThrow(TABELA_SENHA, null,contentValues);

            db.close();

            return true;
        }catch (Exception e){
            return false;
        }

    }
    public String consultaSenha(){

        String query = "select * from tb_senha";
        SQLiteDatabase bd = SQLiteDatabase.openDatabase(path + BANCO_CRIANCA, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = bd.rawQuery(query, null);

        DadosSenha ds = new DadosSenha();
        while (cursor.moveToNext()){
            ds.setSenha(cursor.getString(1));
        }

        return ds.getSenha();

    }

}

