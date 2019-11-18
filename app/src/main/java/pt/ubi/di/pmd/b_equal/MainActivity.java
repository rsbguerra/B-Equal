package pt.ubi.di.pmd.b_equal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * TO DO
 * onClick para o botao "comecar", mostra view das perguntas
 * onClick para o botao "historico", mostra view do historico
 *
 * se sessao iniciada:
 *      -> logout.
 * caso contrario:
 *      -> entrar como utilizador;
 *      -> criar conta: nome + password;
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick_History(View v){
        Intent goToHistory = new Intent(this, HistoryActivity.class);
        startActivity(goToHistory);
    }

    public void onClick_Start(View v){
        Intent StartGame = new Intent(this, GameActivity.class);
        startActivity(StartGame);
    }
}
