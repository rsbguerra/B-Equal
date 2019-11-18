package pt.ubi.di.pmd.b_equal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.ArrayList;

public class GameActivity extends Activity {

    private dbHandler db;
    private ArrayList<Answer> answers;
    private ArrayList<Scenario> scenarios;
    private int currentScenario = 0;
    private int currentSelectionId = -1;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent fromMainActivity = getIntent();

        db = new dbHandler(this);

        scenarios = db.ReadScenarios();
        Scenario s = new Scenario(db);

        Log.i("pt.ubi.di.pmd.b_equal", "array: " + scenarios.toString());

        updateLayout(scenarios.get(currentScenario));
    }

    public void updateLayout(Scenario s){
        TextView TV_title = (TextView) findViewById(R.id.title);
        TV_title.setText("Cenário " + s.getId());

        TextView TV_scenario = (TextView) findViewById(R.id.scenario_text);
        TV_scenario.setText(s.getText());

        TextView TV_question = (TextView) findViewById(R.id.question_text);
        TV_question.setText(s.getQuestion());

        displayOptions();
    }

    public void displayOptions(){

        radioGroup = (RadioGroup) findViewById(R.id.rg_game);
        radioGroup.removeAllViews();


        answers = db.ReadAnswers(currentScenario);

        for(int i = 0; i < answers.size(); i++){
            RadioGroup line = (RadioGroup) getLayoutInflater().inflate(R.layout.activity_game_line, null);
            Answer op = answers.get(i);

            RadioButton check = (RadioButton) line.findViewById(R.id.checkbox);
            check.setId((int) (op.getId()));

            check.setChecked(false);

            check.setText((CharSequence) op.getText());
            radioGroup.addView(line);
        }
    }

    public void onClickOp(View view){
        //boolean checked = ((RadioButton) view).isChecked();
        RadioGroup g = findViewById(view.getId());

        int i = g.getCheckedRadioButtonId();
        Log.i("pt.ubi.di.pmd.b_equal", "clicked: " + i +
                "\n group id: " + view.getId());

    }

    public void onClickProximo(View view){
        Log.i("pt.ubi.di.pmd.b_equal", "not yet");
    }

    public void goBack(View view){
        db.closeDB();
        this.finish();
    }

    public void finish(){
        super.finish();
    }
}
