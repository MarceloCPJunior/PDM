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

import java.util.List;

import iesb.app.sqlliteapp.dao.ClienteDAO;
import iesb.app.sqlliteapp.model.ClienteVO;

public class MainActivity extends AppCompatActivity {

    private EditText pesquisa;
    private TextView nomePesquisa;
    private TextView emailPesquisa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        /*
        pesquisa = findViewById(R.id.pesquisa);
        nomePesquisa = findViewById(R.id.nomePesquisado);
        emailPesquisa = findViewById(R.id.emailPesquisado);*/
        pesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 4) {
                    loadCadastroLayout(s.toString());
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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

    public void loadCadastroLayout(String nome) {
        ClienteDAO db = new ClienteDAO(this);

        ClienteVO cliente = db.getClienteNome(nome);

        if(cliente != null) {
            nomePesquisa.setText(cliente.getNome());
            emailPesquisa.setText(cliente.getEmail());
        }
    }
    /*
    <EditText
    android:id="@+id/pesquisa"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:ems="10"
    android:inputType="text"
    android:hint="Pesquisar"
    tools:layout_editor_absoluteX="43dp"
    tools:layout_editor_absoluteY="59dp" />

    <TextView
    android:id="@+id/nomePesquisado"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:ems="10"
    android:inputType="text"
    android:hint="Nome Pesquisado"
    tools:layout_editor_absoluteX="43dp"
    tools:layout_editor_absoluteY="59dp" />

    <TextView
    android:id="@+id/emailPesquisado"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:ems="10"
    android:inputType="text"
    android:hint="Email Pesquisado"
    tools:layout_editor_absoluteX="43dp"
    tools:layout_editor_absoluteY="59dp" />*/

}