package iesb.app.sqlliteapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import iesb.app.sqlliteapp.model.ClienteVO;

public class ClienteDAO extends SQLiteOpenHelper {
    private static ClienteDAO clienteDAO;
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "LOJA_DB";

    private static final String TB_CLIENTES = " tb_clientes ";

    private static final String KEY_ID = " id ";

    private static final String NOME = " nome ";

    private static final String EMAIL = " email ";

    public ClienteDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        clienteDAO = this;
    }

    public static ClienteDAO getDatabaseHandler() {
        return clienteDAO;
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
    public void addCliente(ClienteVO clienteVO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_ID, getCountClientes() + 1);
        contentValues.put(NOME, clienteVO.getNome());
        contentValues.put(EMAIL, clienteVO.getEmail());

        db.insert(TB_CLIENTES, null, contentValues);
        db.close();
    }

    public int updateCliente(ClienteVO clienteVO) {
        int qtdRegistrosAtualizados = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NOME, clienteVO.getNome());

        qtdRegistrosAtualizados = db.update(TB_CLIENTES, contentValues, EMAIL + " = ? ", new String[]{String.valueOf(clienteVO.getEmail())});

        db.close();
        return qtdRegistrosAtualizados;
    }

    public int deleteCliente(ClienteVO clienteVO) {
        int qtdRegistrosAtualizados = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL, clienteVO.getNome());

        qtdRegistrosAtualizados = db.delete(TB_CLIENTES, EMAIL + " = ? ", new String[]{String.valueOf(clienteVO.getEmail())});

        db.close();
        return qtdRegistrosAtualizados;
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

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ClienteVO clienteVO = new ClienteVO();
                    clienteVO.setId(cursor.getInt(0));
                    clienteVO.setNome(cursor.getString(1));
                    clienteVO.setEmail(cursor.getString(2));

                    ltClientes.add(clienteVO);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        db.close();

        return ltClientes;
    }

    public ClienteVO getCliente(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ClienteVO clienteVO = new ClienteVO();

        Cursor cursor = db.query(TB_CLIENTES,
                new String[]{KEY_ID, NOME, EMAIL},
                KEY_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            // move o ponteiro da classe para o primeiro item da lista
            cursor.moveToFirst();

            clienteVO.setId(cursor.getInt(0));
            clienteVO.setNome(cursor.getString(1));
            clienteVO.setEmail(cursor.getString(2));

            cursor.close();
        }

        db.close();

        return clienteVO;
    }

    public ClienteVO getClienteNome(String nome) {
        SQLiteDatabase db = this.getReadableDatabase();
        ClienteVO clienteVO = new ClienteVO();

        Cursor cursor = db.query(TB_CLIENTES,
                new String[]{KEY_ID, NOME, EMAIL},
                NOME + " = ? ",
                new String[]{nome}, null, null, null);

        if (cursor != null && cursor.getCount() != 0) {
            try {
                // move o ponteiro da classe para o primeiro item da lista
                cursor.moveToFirst();

                if(!cursor.isNull(0)) {
                    clienteVO.setId(cursor.getInt(0));
                    clienteVO.setNome(cursor.getString(1));
                    clienteVO.setEmail(cursor.getString(2));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            cursor.close();
        }

        db.close();

        return clienteVO;
    }
}
