package ejemplo.radio.button.radio.group;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.radioButton2){
                    Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.radioButton3){
                    Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.radioButton4){
                    Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
                }
            }
        });
        */
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.radioButton2:
                if(checked){
                    Toast.makeText(getApplicationContext(),"presiono la opcion 1",Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.radioButton3:
                if(checked){
                    Toast.makeText(getApplicationContext(),"presiono la opcion 2",Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.radioButton4:
                if(checked){
                    Toast.makeText(getApplicationContext(),"presiono la opcion 3",Toast.LENGTH_SHORT).show();
                    break;
                }
            default:
                break;
        }
    }
}
