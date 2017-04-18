package io.github.felipemfp.areaofany;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    private EditText ratioEdit;
    private EditText baseEdit;
    private EditText heightEdit;
    private EditText areaEdit;

    private Spinner shapesSpinner;

    private TableRow ratioRow;
    private TableRow baseRow;
    private TableRow heightRow;
    private TableRow areaRow;
    private TableRow submitRow;

    private String currentShape = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratioEdit = (EditText) findViewById(R.id.ratioEdit);
        baseEdit = (EditText) findViewById(R.id.baseEdit);
        heightEdit = (EditText) findViewById(R.id.heightEdit);
        areaEdit = (EditText) findViewById(R.id.areaEdit);


        ratioRow = (TableRow) findViewById(R.id.ratioRow);
        baseRow = (TableRow) findViewById(R.id.baseRow);
        heightRow = (TableRow) findViewById(R.id.heightRow);
        areaRow = (TableRow) findViewById(R.id.areaRow);
        submitRow = (TableRow) findViewById(R.id.submitRow);

        shapesSpinner = (Spinner) findViewById(R.id.shapesSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shapes_array, android.R.layout.simple_spinner_dropdown_item);
        shapesSpinner.setAdapter(adapter);
        shapesSpinner.setOnItemSelectedListener(handleSpinnerItemClick);
    }

    private AdapterView.OnItemSelectedListener handleSpinnerItemClick = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            currentShape = parent.getItemAtPosition(position).toString();
            updateInterface();
        }

        public void onNothingSelected(AdapterView<?> parent) {
            currentShape = null;
            updateInterface();
        }

    };

    private void updateInterface() {
        switch (currentShape) {
            case "Rectangle":
            case "Triangle":
            case "Square":
                ratioRow.setVisibility(View.GONE);
                baseRow.setVisibility(View.VISIBLE);
                heightRow.setVisibility(View.VISIBLE);
                submitRow.setVisibility(View.VISIBLE);
                areaRow.setVisibility(View.VISIBLE);
                break;
            case "Circle":
                ratioRow.setVisibility(View.VISIBLE);
                baseRow.setVisibility(View.GONE);
                heightRow.setVisibility(View.GONE);
                submitRow.setVisibility(View.VISIBLE);
                areaRow.setVisibility(View.VISIBLE);
                break;
            default:
                ratioRow.setVisibility(View.GONE);
                baseRow.setVisibility(View.GONE);
                heightRow.setVisibility(View.GONE);
                submitRow.setVisibility(View.GONE);
                areaRow.setVisibility(View.GONE);
                break;
        }
        clear(true);
    }

    private void clear(boolean all) {
        if (all) areaEdit.setText("");
        baseEdit.setText("");
        heightEdit.setText("");
        ratioEdit.setText("");
    }

    private void clear() {
        clear(false);
    }

    public void handleSubmit(View view) {
        double area = 0;
        double base;
        double height;
        double ratio;
        switch (currentShape) {
            case "Rectangle":
            case "Square":
                base = Double.parseDouble(baseEdit.getText().toString());
                height = Double.parseDouble(heightEdit.getText().toString());
                area = base * height;
                break;
            case "Triangle":
                base = Double.parseDouble(baseEdit.getText().toString());
                height = Double.parseDouble(heightEdit.getText().toString());
                area = base * height / 2;
                break;
            case "Circle":
                ratio = Double.parseDouble(ratioEdit.getText().toString());
                area = Math.PI * ratio * ratio;
                break;
            default:
                return;
        }
        areaEdit.setText(String.format("%.3f", area));
        clear();
    }
}
