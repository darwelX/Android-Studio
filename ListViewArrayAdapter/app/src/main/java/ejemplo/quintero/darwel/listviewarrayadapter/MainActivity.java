package ejemplo.quintero.darwel.listviewarrayadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TweetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tweet tweets[]= new Tweet[]{
                new Tweet(R.drawable.ic_launcher,"Primer tweet"),
                new Tweet(R.drawable.ic_launcher,"Segundo tweet"),
                new Tweet(R.drawable.ic_launcher,"Tercer tweet"),
                new Tweet(R.drawable.ic_launcher,"Cuarto tweet"),
                new Tweet(R.drawable.ic_launcher,"Quinto tweet"),
                new Tweet(R.drawable.ic_launcher,"Sexto tweet"),
                new Tweet(R.drawable.ic_launcher,"Septimio tweet"),
                new Tweet(R.drawable.ic_launcher,"Octavo tweet"),
                new Tweet(R.drawable.ic_launcher,"Noveno tweet"),
                new Tweet(R.drawable.ic_launcher,"Decimo tweet"),
                new Tweet(R.drawable.ic_launcher,"Decimo primero tweet"),
                new Tweet(R.drawable.ic_launcher,"Decimo Segundo tweet"),
                new Tweet(R.drawable.ic_launcher,"Decimo tercero tweet"),
                new Tweet(R.drawable.ic_launcher,"Decimo cuarto tweet"),
                new Tweet(R.drawable.ic_launcher,"Decimo quinto tweet"),
                new Tweet(R.drawable.ic_launcher,"Decimo sexto tweet"),
        };

        adapter = new TweetAdapter(this,R.layout.listiview_item_row,tweets);
        listView = (ListView) findViewById(R.id.listview);
        View header = (View) getLayoutInflater().inflate(R.layout.list_header_row,null);

        listView.addHeaderView(header);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text = (TextView) view.findViewById(R.id.textView);
                Toast.makeText(getApplicationContext(), text.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
