package pt.ubi.di.pmd.b_equal;

import android.content.ContentValues;

public class Answer {

    private long Id;
    private String Text;
    private long Scenario_Id;
    private int Correct;

    private dbHandler db;

    public Answer (dbHandler db){
        this.db = db;
    }

    public Answer (String Text,long Scenario_Id, int Correct, dbHandler db){
        this.db = db;
        this.Text = Text;
        this.Scenario_Id = Scenario_Id;
        this.Correct = Correct;
    }

    public Answer (long Id, String Text, long Scene_Id, int Correct, dbHandler db){
        this.Id = Id;
        this.Text = Text;
        this.Scenario_Id = Scenario_Id;
        this.Correct = Correct;
        this.db = db;
    }

    public void Insert(){
        ContentValues values = new ContentValues();

        values.put(dbHandler.ANSWER_TEXT, this.Text);
        values.put(dbHandler.ANSWER_QUESTION, this.Scenario_Id);
        values.put(dbHandler.CORRECT, this.Scenario_Id);
        long newId = db.Insert(dbHandler.ANSWER_TABLE_NAME, values);
        this.Id = newId;
    }

    public void Delete(){
        db.Delete(dbHandler.ANSWER_TABLE_NAME, dbHandler.ANSWER_ID, this.Id);
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public long getScenario_Id() {
        return Scenario_Id;
    }

    public void setScenario_Id(long Scenario_Id) {
        this.Scenario_Id = Scenario_Id;
    }

    public int isCorrect() {
        return Correct;
    }

    public void setCorrect(int correct) {
        Correct = correct;
    }
}
