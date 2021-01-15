package com.example.myapplication.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.HistoryEntry;
import com.example.myapplication.viewmodel.HistoryFacade;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //final int MENU_RESET_ID = 1;
    //final int MENU_QUIT_ID = 2;

    EditText num1;
    EditText num2;
    Button plus;
    Button min;
    Button multiply;
    Button division;
    TextView out_result;
    private ArrayList<HistoryItem> history;
    public static final String HISTORY_KEY = "history";
    String oper;

    //@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим элементы
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);

        plus = (Button) findViewById(R.id.plus);
        min = (Button) findViewById(R.id.min);
        multiply = (Button) findViewById(R.id.multiply);
        division = (Button) findViewById(R.id.division);

        out_result = (TextView) findViewById(R.id.out_result);

        plus.setOnClickListener(this);
        min.setOnClickListener(this);
        multiply.setOnClickListener(this);
        division.setOnClickListener(this);

        history = new ArrayList<>();

    }
    //кнопки для 3 мат операций
    // @Override
    public void onClick(View v) {
        float number1 = 0;
        float number2 = 0;
        float result = 0;

        // поля на пустоту
        if (TextUtils.isEmpty(num1.getText().toString())
                || TextUtils.isEmpty(num2.getText().toString()))
            return;

        number1 = Float.parseFloat(num1.getText().toString());
        number2 = Float.parseFloat(num2.getText().toString());
        // опр нажатую кнопку
        switch (v.getId()) {
            case R.id.min:
                oper = "-";
                result = number1 - number2;
                break;
            case R.id.plus:
                oper = "+";
                result = number1 + number2;
                break;
            case R.id.multiply:
                oper = "*";
                result = number1 * number2;
                break;
            case R.id.division:
                oper = "/";
                result = number1 / number2;
                break;
            default:
                break;
        }
        Toast toast1 = Toast.makeText(this, "Результат " + result, Toast.LENGTH_SHORT);
        toast1.show();
        out_result.setText(number1 + " " + oper + number2 + " " + "= " + result);
        String operand1String = String.format("%s",number1);
        String operand2String = String.format("%s",number2);
        String resultString = String.format("%s",result);
        addToHistory(new HistoryEntry(operand1String, operand2String, oper, resultString));
//        history.add(new HistoryItem(operand1String, operand2String, oper, resultString));
//        DatabaseManager databaseManager = new DatabaseManager(this);
//        databaseManager.open();
//        databaseManager.insert(new HistoryItem(operand1String, operand2String, oper, resultString));
//        databaseManager.close();
    }

    public void addToHistory(HistoryEntry newItem){
        HistoryFacade.addItem(getBaseContext(), newItem);
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            Intent intent;
        switch (item.getItemId()){
            case R.id.open_history_item:
                intent = new Intent(this, HistoryListActivity.class);
                intent.putParcelableArrayListExtra(HISTORY_KEY, new ArrayList<>(HistoryFacade.getAllAsList(getBaseContext())));
                startActivity(intent);
                break;
            case R.id.service_item:
                intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.browser_call_item:
                intent = new Intent(this, BrowserCallActivity.class);
                startActivity(intent);
                break;
            case R.id.file_item:
                intent = new Intent(this, FileActivity.class);
                startActivity(intent);
                break;
            case R.id.db_item:
                intent = new Intent(this, DBActivity.class);
                startActivity(intent);
                break;
            case R.id.shared_pref_item:
                intent = new Intent(this, SharedPreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.graphic_item:
                intent = new Intent(this, GraphicActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

