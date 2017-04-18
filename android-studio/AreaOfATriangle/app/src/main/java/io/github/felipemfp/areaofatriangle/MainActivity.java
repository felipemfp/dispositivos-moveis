package io.github.felipemfp.areaofatriangle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText baseEdit;
    private EditText heightEdit;
    private EditText areaEdit;
    private EditText perimeterEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseEdit = (EditText) findViewById(R.id.baseEdit);
        heightEdit = (EditText) findViewById(R.id.heightEdit);
        areaEdit = (EditText) findViewById(R.id.areaEdit);
        perimeterEdit = (EditText) findViewById(R.id.perimeterEdit);
    }

    private void clear() {
        baseEdit.setText("");
        heightEdit.setText("");
    }

    public void handleSubmit(View view) {
        double base = Double.parseDouble(baseEdit.getText().toString());
        double height = Double.parseDouble(heightEdit.getText().toString());
        double area = base * height / 2;
        double perimeter = base + height + (Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2)));
        areaEdit.setText(String.format("%f", area));
        perimeterEdit.setText(String.format("%f", perimeter));
        clear();
    }
}
