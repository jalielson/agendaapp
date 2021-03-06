package br.senac.rn.agendaescolar.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senac.rn.agendaescolar.models.Aluno;

public class AlunoListaActivity extends AppCompatActivity {

    private ListView lvAlunos;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_lista);
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        lvAlunos = (ListView) findViewById(R.id.lista_alunos);
        btCadastrar = (Button) findViewById(R.id.cadastrar);

        List<Aluno> alunos = new ArrayList<Aluno>();

        alunos.add(new Aluno(
                "Lucio Flávio",
                "Av Alexandrino de Alencar, 1592",
                "996360721",
                "http://www.lemavorum.com.br",
                10.0)
        );

        alunos.add(new Aluno(
                "Carlos Bandeira",
                "R da Diatomita, 357",
                "996073082",
                "http://www.facebook.com/doalceycarlos",
                10.0)
        );

        alunos.add(new Aluno(
                "Jalielson Andrade",
                "R Baraúna, 408",
                "988698986",
                "http://www.facebook.com/jalielson.andrade",
                10.0)
        );

        alunos.add(new Aluno(
                "Janna Barbosa",
                "R Sebastião Barreto, 4449",
                "988881402",
                "http://www.facebook.com/jannabarbosa",
                10.0)
        );

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(
                this,
                android.R.layout.simple_list_item_1,
                alunos);

        lvAlunos.setAdapter(adapter);
        registerForContextMenu(lvAlunos);
    }

    private void definirEventos() {
//        btCadastrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context contexto = AlunoListaActivity.this;
//                String mensagem = "Clicou no botão";
//                int duracao = Toast.LENGTH_LONG;
//                Toast.makeText(contexto, mensagem, duracao).show();
//                tvResultado.setText(etNome.getText().toString());
//            }
//        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_opcoes, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo alunoEscolhido;
        alunoEscolhido = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(alunoEscolhido.position);
        switch (item.getItemId()) {
            case R.id.item_sms:
                Intent intentSms = new Intent(Intent.ACTION_VIEW);
                intentSms.setData(Uri.parse("sms:" + aluno.getFone()));
                item.setIntent(intentSms);
                break;
            case R.id.item_site:
                Intent intentSite = new Intent(Intent.ACTION_VIEW);
                intentSite.setData(Uri.parse(aluno.getSite()));
                item.setIntent(intentSite);
                break;
            case R.id.item_mapa:
                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));
                item.setIntent(intentMapa);
                break;
            case R.id.item_ligar:
                Intent intentLigar = new Intent(Intent.ACTION_VIEW);
                intentLigar.setData(Uri.parse("tel:" + aluno.getFone()));
                item.setIntent(intentLigar);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
