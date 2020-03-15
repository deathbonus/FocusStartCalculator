package com.deathbonus.focusstartcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deathbonus.focusstartcalculator.calculator.Calculator;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator;
    private EditText editText;
    private TextView textView;
    private boolean isInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        editText = findViewById(R.id.edit_text_view);
        textView = findViewById(R.id.result_text_view);
        calculator = new Calculator();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().matches(".*[^-+*/().0-9].*")) {
                    Toast.makeText(getApplicationContext(), R.string.invalid_input_alert, Toast.LENGTH_SHORT).show();
                    isInputValid = false;
                } else {
                    isInputValid = true;
                }
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (isInputValid && keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    try {
                        textView.setText(calculator.calculate(editText.getText().toString()));
                        return true;
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                        return false;
                    }
                } else {
                    return false;
                }
            }
        });
    }
}