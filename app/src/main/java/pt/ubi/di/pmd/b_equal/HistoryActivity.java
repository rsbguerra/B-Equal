package pt.ubi.di.pmd.b_equal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class HistoryActivity extends Activity {

    /**
     * TO DO:
     * Testar se tabela de Historico esta vazia
     *      se esta vazia, carrgar layout empty history
     *      caso contrario, carregar layout do hostorico normal
     */

    private dbHandler db;
    private ArrayList<History> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_history);
        Intent fromMainActivity = getIntent();

        // se a tabela de historico nao esta vazia, apresentar linearLayout
    }

    public void goBack(View v){
        this.finish();

    }

    public void finish(){
        super.finish();
    }
}
