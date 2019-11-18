package pt.ubi.di.pmd.b_equal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;

/*
 * TODO
 *   Adicionar 3 questoes pre definidas;
 * */

public class dbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Questionario.db";

    SQLiteDatabase SQLiteDB;
    Context context;

    // Tabela para guardar as questoes
    public static final String SCENARIO_TABLE_NAME = "Scenario";
    public static final String SCENARIO_ID = "Id";
    public static final String SCENARIO_TEXT = "Scenario_Text";
    public static final String SCENARIO_QUESTION = "Scenario_Question";

    // Tabela para guardar as respostas
    protected static final String ANSWER_TABLE_NAME = "Answer";
    protected static final String ANSWER_ID = "Id";
    protected static final String ANSWER_TEXT = "Answer_Text";
    protected static final String ANSWER_QUESTION = "Question_ID";
    protected static final String CORRECT =  "Correct";

    // Tabela para guardar o historico
    protected static final String HISTORY_TABLE_NAME = "History";
    protected static final String HISTORY_ID = "Id";
    protected static final String HISTORY_QUESTION = "Question_ID";
    protected static final String HISTORY_ANSWER =  "Answer_ID";
    protected static final String Date = "Date";


    // Query para criar tabelas
    private static final String CREATE_SCENARIO_TABLE =
            "CREATE TABLE " + SCENARIO_TABLE_NAME + "(" +
                    SCENARIO_ID       + " INTEGER PRIMARY KEY, " +
                    SCENARIO_TEXT     + " VARCHAR(255), " +
                    SCENARIO_QUESTION + " VARCHAR(255)" +
                    ");";

    private static final String CREATE_ANSWER_TABLE =
            "CREATE TABLE " + ANSWER_TABLE_NAME + "(" +
                    ANSWER_ID       + " INTEGER PRIMARY KEY," +
                    ANSWER_TEXT     + " VARCHAR(255)," +
                    ANSWER_QUESTION + " INTEGER, " +
                    CORRECT         + " INTEGER," +
                    "FOREIGN KEY("   + ANSWER_QUESTION + ") " +
                    "REFERENCES " + SCENARIO_TABLE_NAME +
                    "(" + SCENARIO_ID + ")" +
                    ");";

    private static final String CREATE_HISTORY_TABLE =
            "CREATE TABLE " + HISTORY_TABLE_NAME + "(" +
                    HISTORY_ID       + " INTEGER PRIMARY KEY," +
                    HISTORY_QUESTION + " INTEGER," +
                    HISTORY_ANSWER   + " INTEGER," +
                    "FOREIGN KEY("   + HISTORY_QUESTION + ") " +
                    "REFERENCES " + SCENARIO_TABLE_NAME +
                    "(" + SCENARIO_ID + ")," +
                    "FOREIGN KEY("   + HISTORY_ANSWER + ") " +
                    "REFERENCES " + ANSWER_TABLE_NAME +
                    "(" + ANSWER_ID + ")" +
                    ");";

    //  Query para inserir perguntas pre-definidas
    private static final String CREATE_DEFAULT_QUESTION_1 =
            "INSERT INTO " + SCENARIO_TABLE_NAME + "(" +
                    SCENARIO_ID + ", " +
                    SCENARIO_TEXT + ", " +
                    SCENARIO_QUESTION + ")" +
                    " VALUES(" +
                    "'1', " +
                    "'O/A meu/minha namorado/a diz-me diariamente que gosta muito de mim e pediu-me as chaves de acesso ao meu telemóvel.', " +
                    "'Se fosse contigo, achas que devias dar-lhe essa informação?');";

    private static final String CREATE_DEFAULT_ANSWER_1 =
            "INSERT INTO " + ANSWER_TABLE_NAME + "(" +
                    ANSWER_TEXT + ", " +
                    ANSWER_QUESTION + ", " +
                    CORRECT + ")" +
                    " VALUES(" +
                    "'Sim, ele diz que gosta de mim e, por isso, os ciúmes justificam que ele queira controlar o que faço com o meu telemóvel.', " +
                    "'1', " +
                    "'0')";

    private static final String CREATE_DEFAULT_ANSWER_2 =
            "INSERT INTO " + ANSWER_TABLE_NAME + "(" +
                    ANSWER_TEXT + ", " +
                    ANSWER_QUESTION + ", " +
                    CORRECT + ")" +
                    " VALUES(" +
                    "'Sim, porque a nossa relação é baseada na confiança e sei que ele não vai fazer-me mal.', " +
                    "'1', " +
                    "'0')";

    private static final String CREATE_DEFAULT_ANSWER_3 =
            "INSERT INTO " + ANSWER_TABLE_NAME + "(" +
                    ANSWER_TEXT + ", " +
                    ANSWER_QUESTION + ", " +
                    CORRECT + ")" +
                    " VALUES(" +
                    "'Não dou as chaves de acesso ao meu telemóvel porque são pessoais.', " +
                    "'1', " +
                    "'1')";

    private static final String CREATE_DEFAULT_ANSWER_4 =
            "INSERT INTO " + ANSWER_TABLE_NAME + "(" +
                    ANSWER_TEXT + ", " +
                    ANSWER_QUESTION + ", " +
                    CORRECT + ")" +
                    " VALUES(" +
                    "'Só dou essa informação se ele também me der as chaves de acesso ao seu telemóvel.', " +
                    "'1', " +
                    "'0')";



    public dbHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        SQLiteDB = this.getWritableDatabase();
    }


    /* Insere valores pré-definidos na base de dados */
    public void seedDB() {

        SQLiteDB.execSQL(CREATE_DEFAULT_QUESTION_1);

        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_1);
        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_2);
        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_3);
        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_4);
    }


    @Override
    public void onCreate(SQLiteDatabase SQLiteDB){
        // Criar tabelas

            SQLiteDB.execSQL(CREATE_SCENARIO_TABLE);
            SQLiteDB.execSQL(CREATE_ANSWER_TABLE);
            SQLiteDB.execSQL(CREATE_HISTORY_TABLE);

            Log.i("bequal", "tabelas criadas com sucesso:\n" +
                    CREATE_SCENARIO_TABLE + "\n" +
                    CREATE_ANSWER_TABLE  + "\n" +
                    CREATE_HISTORY_TABLE + "\n");
        SQLiteDB.execSQL(CREATE_DEFAULT_QUESTION_1);

        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_1);
        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_2);
        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_3);
        SQLiteDB.execSQL(CREATE_DEFAULT_ANSWER_4);
    }


    /**
     * Insere informacao na base de dados
     * @param table String que contem o nome da tabela a inserir os valores
     * @param values ContentValues que contem a informacao a inserir
     * @return retorna a chave primaria do valor inserido
     */
    public long Insert(String table, ContentValues values){
        Log.i("content values size:", values.size() + "");
        return SQLiteDB.insert(table, null, values);
    }


    /**
     * Remove coluna da base de dados
     * @param table String que contem o nome da tabela a inserir os valores
     * @param column ContentValues que contem a informacao a inserir
     * @param id long que contem a chave primaria da coluna a remover
     */
    public void Delete(String table, String column, long id){
        SQLiteDB.delete(table, column + "=" + id, null);
    }



    @Override
    public void onUpgrade (SQLiteDatabase SQLiteDB, int oldVersion, int newVersion) {
        SQLiteDB.execSQL("DROP TABLE " + SCENARIO_TABLE_NAME + ";");
        SQLiteDB.execSQL("DROP TABLE " + ANSWER_TABLE_NAME + ";");
        SQLiteDB.execSQL("DROP TABLE " + HISTORY_TABLE_NAME + ";");

        SQLiteDB.execSQL(CREATE_SCENARIO_TABLE);
        SQLiteDB.execSQL(CREATE_ANSWER_TABLE);
        SQLiteDB.execSQL(CREATE_HISTORY_TABLE);
    }


    /**
     * Abrir a base de dados
     */
    public void openDB(){
        SQLiteDB = this.getReadableDatabase();
    }
    /**
     * Fechar a base de dados
     */
    public void closeDB(){
        SQLiteDB.close();
    }

    public ArrayList<History> ReadHistory(){
        Cursor cursor = SQLiteDB.query(HISTORY_TABLE_NAME, new String[]{"*"},
                null, null, null, null, null, null);

        ArrayList<History> history_enteries = new ArrayList<>();
        boolean carryOn = cursor.moveToFirst();

        while(carryOn){
            Date date = new Date(cursor.getLong(3) * 1000);
            History h = new History(cursor.getLong(0), cursor.getString(1), cursor.getString(2), date, this);
            history_enteries.add(h);

            carryOn = cursor.moveToNext();
        }

        cursor.close();
        return history_enteries;
    }

    public ArrayList<Scenario> ReadScenarios(){
        ArrayList<Scenario> scenarios = new ArrayList<>();

        try{
            Cursor cursor = SQLiteDB.query(SCENARIO_TABLE_NAME, new String[]{"*"},
                    null, null, null, null, null, null);
            boolean carryOn = cursor.moveToFirst();

            while(carryOn){

                Scenario s = new Scenario(cursor.getLong(0), cursor.getString(1), cursor.getString(2), this);
                scenarios.add(s);

                carryOn = cursor.moveToNext();
            }

            cursor.close();

        } catch (Exception e){
            Log.i("pt.ubi.di.pmd.b_equal", e.getMessage());
        }
        return scenarios;


    }


    // TODO
    // fazer metodo ReadAnswers que receba o ID da questao associada

    public ArrayList<Answer> ReadAnswers(int scenario){
        Cursor cursor = SQLiteDB.query(ANSWER_TABLE_NAME, new String[]{"*"},
                null, null, null, null, null, null);

        ArrayList<Answer> answers = new ArrayList<>();
        boolean carryOn = cursor.moveToFirst();

        while(carryOn){

            Answer a = new Answer(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getInt(3), this);

            if(a.getScenario_Id() == scenario){
                answers.add(a);
            }

            carryOn = cursor.moveToNext();
        }

        cursor.close();
        return answers;
    }

}