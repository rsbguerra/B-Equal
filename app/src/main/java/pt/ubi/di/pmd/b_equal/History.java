package pt.ubi.di.pmd.b_equal;

import android.content.ContentValues;

import java.sql.Date;
import java.util.ArrayList;

public class History {

    private long Id;
    private String Question;
    private String Answer;
    private Date Date;

    private dbHandler db;


    public History (dbHandler db){
        this.db = db;
    }

    public History (String Question, String Answer, Date Date, dbHandler db){
        this.Answer = Answer;
        this.Question = Question;
        this.Date = Date;
    }

    public History (long Id, String Question, String Answer, Date Date, dbHandler db){
        this.Id = Id;
        this.Answer = Answer;
        this.Question = Question;
        this.Date = Date;
    }

    public void Insert(){
        ContentValues values = new ContentValues();

        values.put(dbHandler.HISTORY_QUESTION, this.Question);
        values.put(dbHandler.HISTORY_ANSWER, this.Answer);
        long newId = db.Insert(dbHandler.HISTORY_TABLE_NAME, values);
        this.Id = newId;
    }

    public void Delete(){
        db.Delete(dbHandler.HISTORY_TABLE_NAME, dbHandler.HISTORY_ID, this.Id);
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion_Id(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer_Id(String answer) {
        Answer = answer;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public dbHandler getDb() {
        return db;
    }

    public void setDb(dbHandler db) {
        this.db = db;
    }
}
