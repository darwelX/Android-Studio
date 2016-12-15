package darwel.quintero.hola.mundo.ejemplo.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView text;
    ImageView imagen;
    Button botonA, botonB, botonC;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText2);
        imagen = (ImageView) findViewById(R.id.imageView2);
        botonA = (Button) findViewById(R.id.button2);
        botonB = (Button) findViewById(R.id.button3);
        botonC = (Button) findViewById(R.id.button4);
        botonA.setOnClickListener(this);
        botonB.setOnClickListener(this);
        botonC.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button2:
                text.setText(R.string.saludo);
                break;
            case R.id.button3:
                imagen.setImageResource(R.drawable.ve);
                break;
            case R.id.button4:
                Intent intent = new Intent(this,HelloWordlTexto.class);
                String dato =  editText.getText().toString();
                intent.putExtra("DATO",dato);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
