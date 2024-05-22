package iesb.app.sqlliteapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import iesb.app.sqlliteapp.model.ClienteVO;
import iesb.app.sqlliteapp.model.Entidade;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "LOJA_DB";

    private static final String TB_CLIENTES = "tb_clientes";

    private static final String KEY_ID = "id";

    private static final String NOME = "nome";

    private static final String EMAIL = "email";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_CLIENTES = "CREATE TABLE " + TB_CLIENTES + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, " + NOME + " TEXT, "
                + EMAIL + " TEXT )";

        db.execSQL(CREATE_TB_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_CLIENTES);
        onCreate(db);
    }

    // metodos de negocio
    public void addCliente(ClienteVO clienteVO) throws IllegalAccessException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NOME, clienteVO.getNome());
        contentValues.put(EMAIL, clienteVO.getEmail());

        db.insert(TB_CLIENTES, null, contentValues);
        db.close();
    }

    public int getCountClientes() {
        int count = 0;
        String countQuery = "SELECT COUNT(*) FROM " + TB_CLIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        return count;
    }

    public List<ClienteVO> getAllClientes() {
        List<ClienteVO> ltClientes = new ArrayList<ClienteVO>();
        String selectQuery = "SELECT * FROM " + TB_CLIENTES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                ClienteVO clienteVO = new ClienteVO();
                clienteVO.setId(cursor.getInt(0));
                clienteVO.setNome(cursor.getString(1));
                clienteVO.setEmail(cursor.getString(2));

                ltClientes.add(clienteVO);
            } while(cursor.moveToNext());
        }

        return ltClientes;
    }
}
