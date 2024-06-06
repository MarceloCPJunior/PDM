package iesb.app.sqlliteapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

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

        EditText nomeEditText = (EditText) findViewById(R.id.nome);
        EditText emailEditText = (EditText) findViewById(R.id.email);

        ClienteVO vo = new ClienteVO();
        vo.setNome(nomeEditText.getText().toString());
        vo.setEmail(emailEditText.getText().toString());
        db.addCliente(vo);
    }

    public void btnOnClickCadastro(View view) {
        setContentView(R.layout.cadastro_cliente);
    }

    public void btnOnClickListar(View view) {
        ClienteDAO db = new ClienteDAO(this);
        RecyclerView listaCliente = findViewById(R.id.lista);

        setContentView(R.layout.listar_cliente);

    }

    public void btnOnClickMenu(View view) {
        setContentView(R.layout.activity_main);
    }

    public void btnOnClickDeletar(View view) {
        setContentView(R.layout.deletar_cliente);
    }

    public void btnOnClickEditar(View view) {
        setContentView(R.layout.deletar_cliente);
    }

}