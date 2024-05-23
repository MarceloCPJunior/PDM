package iesb.app.sqlliteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import iesb.app.sqlliteapp.dao.DatabaseHandler;
import iesb.app.sqlliteapp.model.ClienteVO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnOnClickCadastrarCliente(View view) {
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserindo cliente...");
        db.addCliente(new ClienteVO("Marcelo"));
        db.addCliente(new ClienteVO("Bruno"));
        db.addCliente(new ClienteVO("Junior"));

        EditText nomeEditText = (EditText) findViewById(R.id.nome);
        EditText emailEditText = (EditText) findViewById(R.id.email);

        ClienteVO vo = new ClienteVO();
        vo.setNome(nomeEditText.getText().toString());
        vo.setEmail(emailEditText.getText().toString());
        db.addCliente(vo);
    }

    public void loadCadastroLayout(View view) {
        EditText nomeEditText = (EditText) findViewById(R.id.nome);
        EditText emailEditText = (EditText) findViewById(R.id.email);
    }

}