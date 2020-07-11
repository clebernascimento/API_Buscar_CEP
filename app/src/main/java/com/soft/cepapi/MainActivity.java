package com.soft.cepapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.soft.cepapi.model.CEP;
import com.soft.cepapi.service.HttpService;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mCep;
    private TextView mRetonaCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_buscarCep).setOnClickListener(this);
        mCep = findViewById(R.id.edit_cep);
        mRetonaCep = findViewById(R.id.text_cepEndereco);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_buscarCep:
                // buscar cep
                if (mCep.getText().toString().length() > 0 && !mCep.getText().equals("") && mCep.getText().toString().length() == 8) {
                    HttpService service = new HttpService(mCep.getText().toString());
                    try {
                        CEP retornoCEP = service.execute().get();
                        mRetonaCep.setText(retornoCEP.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}