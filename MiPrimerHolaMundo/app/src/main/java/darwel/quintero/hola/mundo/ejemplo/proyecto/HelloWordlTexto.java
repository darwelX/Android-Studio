package darwel.quintero.hola.mundo.ejemplo.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class HelloWordlTexto extends Activity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_wordl_texto);
        text = (TextView) findViewById(R.id.saludoText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String dato = (String) bundle.get("DATO");
        text.setText(dato);
    }

}
