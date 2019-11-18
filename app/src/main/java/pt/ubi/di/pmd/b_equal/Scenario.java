package pt.ubi.di.pmd.b_equal;

import android.content.ContentValues;
import android.util.Log;

public class Scenario {

    private long Id;
    private String Text;
    private String Question;

    private dbHandler db;

    public Scenario (dbHandler db){
        this.db = db;
    }

    public Scenario (String Text, String Question, dbHandler db){
        this.Text = Text;
        this.Question = Question;
        this.db = db;

    }

    public Scenario (long Id, String Text, String Question, dbHandler db){
        this.db = db;
        this.Id = Id;
        this.Text = Text;
        this.Question = Question;
        this.db = db;

    }

    public void Insert(){
        ContentValues values = new ContentValues();



        values.put(dbHandler.SCENARIO_TEXT, this.Text);
        values.put(dbHandler.SCENARIO_QUESTION, this.Question);

        try{
            Log.i("pt.ubi.di.pmd.b_equal", "\nvalues:\n" + values.toString() +
                    "\n\ndb scenario text: " + dbHandler.SCENARIO_TEXT + ", scenario text: " + this.getText() +
                    "\ndb scenario question: " + dbHandler.SCENARIO_QUESTION + ", scenario question: " + this.getQuestion() + "\n"
            );

            long newId = db.Insert(dbHandler.SCENARIO_TABLE_NAME, values);
            this.Id = newId;

        } catch(Exception e){
            Log.e("pt.ubi.di.pmd.b_equal", "Scenario.Insert() error: " + e.getMessage());

        }

    }

    public void Delete(){
        db.Delete(dbHandler.SCENARIO_TABLE_NAME, dbHandler.SCENARIO_ID, this.Id);
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

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public dbHandler getDb() {
        return db;
    }

    public void setDb(dbHandler db) {
        this.db = db;
    }

    @Override
    public String toString() {
        return "Scenario Id " + Id + " {" +
                "\nText: " + Text +
                "\nQuestion: " + Question +
                '}';
    }
}
