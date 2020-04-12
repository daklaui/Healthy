package com.example.healthy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.healthy.Classes.Account;
import com.example.healthy.Classes.Diet;
import com.example.healthy.Classes.Nourriture;
import com.example.healthy.Classes.Portion;
import com.example.healthy.Classes.Profile;
import com.example.healthy.Classes.Regime;
import com.example.healthy.Classes.TypeNourriture;

public class DatabaseHandler extends SQLiteOpenHelper {

    //DB
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "healthyDB";
    //TABLE ACCOUNT
    private static final String TABLE_ACCOUNT = "account";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    //TABLE PROFILE
    private static final String TABLE_PROFILE = "profile";
    private static final String PROFILE_ID = "id";
    private static final String PROFILE_NOM = "nom";
    private static final String PROFILE_PRENOM = "prenom";
    private static final String PROFILE_ANNIVERSAIRE = "anniversaire";
    private static final String PROFILE_AGE = "age";
    private static final String PROFILE_SEXE = "sexe";
    private static final String PROFILE_POIDS = "poids";
    private static final String PROFILE_TAILLE = "taille";
    private static final String PROFILE_IMC = "imc";
    //TABLE REGIME_CHOIX
    private static final String TABLE_REGIME = "REGIME";
    private static final String REGIME_ID = "id";
    private static final String DEGRE_ACTIVITE = "degre_activite";
    private static final String NEW_POIDS = "nouveau_poids";
    private static final String OBJECTIF = "objectif";
    private static final String TYPE_REGIME = "type_regime";

    //TABLE DIET
    private static final String TABLE_DIET = "DIET" ;
    private static final String DIET_ID = "id";
    private static final String DIET_TYPE = "Diet_Type";
    private static final String DIET_CALORIES  = "Nb_Calories";

    //TABLE PORTION
    private static final String TABLE_PORTION = "PORTION" ;
    private static final String PORTION_ID = "id";
    private static final String PORTION_TYPE = "Portion_Type";
    private static final String PORTION_QUANTITE  = "Portion_quantite";

    //TABLE TYPENOURRITURE
    private static final String TABLE_TYPENOURRITURE = "TYPENOURRITURE" ;
    private static final String TYPENOURRITURE_ID = "id";
    private static final String TYPENOURRITUE_TYPE = "TypeNourriture_Type";

    //table Nourriture
    private static final String TABLE_NOURRITURE = "NOURRITURE" ;
    private static final String NOURRITURE_ID = "id";
    private static final String NOURRITURE_Nom = "Nourriture_Nom";
    private static final String NOURRITURE_TYPE = "Nourriture_Type";
    private static final String NOURRITURE_CALORIES = "Nourriture_Calories";
    private static final String NOURRITURE_GRAISSE = "Nourriture_Graisse";
    private static final String NOURRITURE_CHOLESTEROL = "Nourriture_Cholesterol";
    private static final String NOURRITURE_SODIUM = "Nourriture_Sodium";
    private static final String NOURRITURE_SUCRES = "Nourriture_Sucres";
    private static final String NOURRITURE_PROTEIN = "Nourriture_Protein";
    private static final String NOURRITURE_VITAMINA = "Nourriture_VITAMINA";
    private static final String NOURRITURE_VITAMINC = "Nourriture_VITAMINC";
    private static final String NOURRITURE_FER = "Nourriture_Fer";
    private static final String NOURRITURE_CALCIUM = "Nourriture_Calcium";
    private static final String NOURRITURE_TYPE_PORTION = "Nourriture_Type_Portion";
    private static final String NOURRITURE_Qunatite_Portion = "Nourriture_QUantite_Portion";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_ACCOUNT + "("
                + KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD+ " TEXT" + ")";
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
                + PROFILE_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + PROFILE_NOM + " TEXT,"
                + PROFILE_PRENOM+ " TEXT,"+ PROFILE_ANNIVERSAIRE+ " TEXT,"+ PROFILE_AGE +" INTEGER,"
                + PROFILE_SEXE+ " TEXT,"+ PROFILE_POIDS + " DOUBLE,"+PROFILE_TAILLE+" DOUBLE,"+
                PROFILE_IMC+" INTEGER" + ")";

        String CREATE_REGIME_TABLE = "CREATE TABLE " + TABLE_REGIME + "("
                + REGIME_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + DEGRE_ACTIVITE + " INTEGER,"
                + NEW_POIDS+ " DOUBLE,"+ OBJECTIF+ " INTEGER,"+ TYPE_REGIME+ " TEXT" + ")";

        String CREATE_DIET_TABLE = "CREATE TABLE " + TABLE_DIET + "("
                + DIET_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + DIET_TYPE + " TEXT,"
                + DIET_CALORIES+ " INTEGER)";

        String CREATE_PORTION_TABLE = "CREATE TABLE " + TABLE_PORTION + "("
                + PORTION_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + PORTION_TYPE + " TEXT,"
                + PORTION_QUANTITE+ " TEXT)";

        String CREATE_TYPE_NOURRITURE_TABLE = "CREATE TABLE " + TABLE_TYPENOURRITURE + "("
                + TYPENOURRITURE_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + TYPENOURRITUE_TYPE + " TEXT)";

        String CREATE_NOURRITURE_TABLE = "CREATE TABLE " + TABLE_NOURRITURE + "("
                + NOURRITURE_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + NOURRITURE_Nom + " TEXT,"
                + NOURRITURE_TYPE+ " TEXT,"+ NOURRITURE_CALORIES+ " INTEGER,"+ NOURRITURE_GRAISSE +" DOUBLE,"
                + NOURRITURE_CHOLESTEROL+ " DOUBLE,"+ NOURRITURE_SODIUM + " DOUBLE,"+NOURRITURE_SUCRES+" DOUBLE,"+NOURRITURE_PROTEIN+" DOUBLE,"+NOURRITURE_VITAMINA+" DOUBLE,"+
                NOURRITURE_VITAMINC+" DOUBLE,"+NOURRITURE_FER+" DOUBLE,"+NOURRITURE_CALCIUM+" DOUBLE,"+NOURRITURE_TYPE_PORTION+" TEXT,"+
               NOURRITURE_Qunatite_Portion+" TEXT" + ")";


        db.execSQL(CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_REGIME_TABLE);
        db.execSQL(CREATE_DIET_TABLE);
        db.execSQL(CREATE_PORTION_TABLE);
        db.execSQL(CREATE_TYPE_NOURRITURE_TABLE);
        db.execSQL(CREATE_NOURRITURE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PORTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPENOURRITURE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOURRITURE);
        onCreate(db);
    }

    //Account methods
    public void addAccount(Account compte) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, compte.get_email());
        values.put(KEY_PASSWORD, compte.get_password());
        db.insert(TABLE_ACCOUNT, null, values);
        db.close();
    }

    public Account getAccount(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ACCOUNT, new String[] { KEY_ID,
                        KEY_EMAIL,KEY_PASSWORD}, KEY_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Account compte = new Account(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return compte;
    }
    public Account getAccount_parid(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ACCOUNT, new String[] { KEY_ID,
                        KEY_EMAIL,KEY_PASSWORD}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Account compte = new Account(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return compte;
    }

    public Boolean checkUserAccount(String email , String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+KEY_EMAIL+"= '"+email+"' and "+KEY_PASSWORD+"= '"+pass+"' ;",null);
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }


    public  Boolean checkEmailExists(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+KEY_EMAIL+" = '"+email+"';",null);
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }

    //PROFILE METHODS

    public void addProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_NOM, profile.get_nom());
        values.put(PROFILE_PRENOM, profile.get_prénom());
        values.put(PROFILE_ANNIVERSAIRE, profile.get_anniversaire());
        values.put(PROFILE_AGE, profile.get_age());
        values.put(PROFILE_SEXE, profile.get_sexe());
        values.put(PROFILE_POIDS, profile.get_poids());
        values.put(PROFILE_TAILLE, profile.get_taille());
        values.put(PROFILE_IMC, profile.get_imc());
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    public Profile getProfile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROFILE, new String[] { PROFILE_ID,
                        PROFILE_NOM,PROFILE_PRENOM,PROFILE_ANNIVERSAIRE,PROFILE_AGE,PROFILE_SEXE,PROFILE_POIDS,PROFILE_TAILLE,PROFILE_IMC}, PROFILE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Profile profil = new Profile(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),
                cursor.getString(5),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),Double.parseDouble(cursor.getString(8)));
        return profil;
    }



    public Regime getRegime(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REGIME, new String[] { REGIME_ID ,
                        DEGRE_ACTIVITE ,NEW_POIDS ,OBJECTIF ,TYPE_REGIME }, REGIME_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Regime regime = new Regime(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(3)), cursor.getString(4), Integer.parseInt(cursor.getString(1)),Double.parseDouble(cursor.getString(2)));
        return regime;
    }

    //Account methods
    public void addregime(Regime regime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DEGRE_ACTIVITE, regime.getDegre_act());
        values.put(NEW_POIDS, regime.getNew_poids());
        values.put(OBJECTIF, regime.getObjectif());
        values.put(TYPE_REGIME, regime.getTyp_regime());
        db.insert(TABLE_REGIME, null, values);
        db.close();
    }

    //add diet
    public void adddiet(Diet diet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DIET_TYPE, diet.getType());
        values.put(DIET_CALORIES,diet.getCalories());
        db.insert(TABLE_DIET, null, values);
        db.close();
    }

    //add portion
    public void addPortion(Portion portion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PORTION_TYPE, portion.getTypePortion());
        values.put(PORTION_QUANTITE,portion.getQuantitéParPotion());
        db.insert(TABLE_PORTION, null, values);
        db.close();
    }

    //add typenourriture
    public void addTypeNourriture(TypeNourriture tn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TYPENOURRITUE_TYPE, tn.getNomtype());
        db.insert(TABLE_TYPENOURRITURE, null, values);
        db.close();
    }


    //add noourriture
    public void addNourriture(Nourriture n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOURRITURE_Nom, n.getNom());
        values.put(NOURRITURE_TYPE, n.getType());
        values.put(NOURRITURE_CALORIES, n.getCalories());
        values.put(NOURRITURE_GRAISSE, n.getGraisse());
        values.put(NOURRITURE_CHOLESTEROL, n.getCholesterol());
        values.put(NOURRITURE_SODIUM, n.getSodium());
        values.put(NOURRITURE_SUCRES, n.getSucres());
        values.put(NOURRITURE_PROTEIN, n.getProtein());
        values.put(NOURRITURE_VITAMINA, n.getVitaminA());
        values.put(NOURRITURE_VITAMINC, n.getVitaminC());
        values.put(NOURRITURE_FER, n.getFer());
        values.put(NOURRITURE_CALCIUM, n.getCalcium());
        values.put(NOURRITURE_TYPE_PORTION, n.getTypePortion());
        values.put(NOURRITURE_Qunatite_Portion, n.getQuantitéParPortion());
        db.insert(TABLE_NOURRITURE, null, values);
        db.close();
    }













}
