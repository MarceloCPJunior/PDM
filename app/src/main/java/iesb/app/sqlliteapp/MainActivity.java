package iesb.app.sqlliteapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iesb.app.sqlliteapp.dao.ClienteDAO;
import iesb.app.sqlliteapp.model.ClienteVO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void btnOnClickCadastrarCliente(View view) {
        ClienteDAO db = new ClienteDAO(this);
        Log.d("Insert: ", "Inserindo cliente...");

        EditText nomeEditText = (EditText) findViewById(R.id.nome_cadastro);
        EditText emailEditText = (EditText) findViewById(R.id.email_cadastro);

        ClienteVO vo = new ClienteVO();
        vo.setNome(nomeEditText.getText().toString());
        vo.setEmail(emailEditText.getText().toString());
        db.addCliente(vo);
    }

    public void btnOnClickEditar(View view) {
        ClienteDAO db = new ClienteDAO(this);

        EditText nomeEditText = (EditText) findViewById(R.id.nome_editar);
        EditText emailEditText = (EditText) findViewById(R.id.email_editar);
        Log.d("Edit: ", "Editando cliente " + emailEditText.getText().toString());

        ClienteVO vo = new ClienteVO();
        vo.setNome(nomeEditText.getText().toString());
        vo.setEmail(emailEditText.getText().toString());

        int editados = db.updateCliente(vo);

        if(editados>0) {
            Toast.makeText(getApplicationContext(), "Editado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nenhum cliente encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnOnClickDeletar(View view) {
        ClienteDAO db = new ClienteDAO(this);

        EditText emailEditText = (EditText) findViewById(R.id.email_deletar);
        Log.d("Edit: ", "Deletando cliente " + emailEditText.getText().toString());

        ClienteVO vo = new ClienteVO();
        vo.setEmail(emailEditText.getText().toString());
        int deletados = db.deleteCliente(vo);
        if(deletados>0) {
            Toast.makeText(getApplicationContext(), "Deletado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nenhum cliente encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnOnClickListar(View view) {
        setContentView(R.layout.listar_cliente);

        RecyclerView listaCliente = findViewById(R.id.lista);
        ClienteDAO db = new ClienteDAO(this);
        List<ClienteVO> clientes = db.getAllClientes();
    }

    public void btnOnClickParaCadastro(View view) {
        setContentView(R.layout.cadastro_cliente);
    }

    public void btnOnClickParaMenu(View view) {
        setContentView(R.layout.activity_main);
    }

    public void btnOnClickParaDeletar(View view) {
        setContentView(R.layout.deletar_cliente);
    }

    public void btnOnClickParaEditar(View view) {
        setContentView(R.layout.deletar_cliente);
    }

}