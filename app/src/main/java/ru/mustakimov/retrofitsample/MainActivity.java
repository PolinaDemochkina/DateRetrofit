package ru.mustakimov.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        Button getInfoBtn = (Button) findViewById(R.id.button);

        getInfoBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                RadioButton getDateBtn = (RadioButton) findViewById(R.id.radioBtn1);
                RadioButton getIpBtn = (RadioButton) findViewById(R.id.radioBtn2);
                String type = "";

                if (getIpBtn.isChecked())
                {
                    type = "ip";
                }
                else if (getDateBtn.isChecked()){
                    type = "date";
                }
                else {
                    Toast.makeText(MainActivity.this, "Please choose the type of request", Toast.LENGTH_SHORT).show();
                }

                if (type != "") {
                    final String finalType = type;
                    App.getApi().getInfo(type).enqueue(new Callback<InfoModel>() {
                        @Override
                        public void onResponse(Call<InfoModel> call, Response<InfoModel> response) {
                            InfoModel info = response.body();
                            if (finalType == "ip") {
                                textView.setText("IP: " + info.getIp());
                            }
                            else {
                                textView.setText("Date: " + info.getDate() + "\nTime: " + info.getTime());
                            }
                        }

                        @Override
                        public void onFailure(Call<InfoModel> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    public void onclick(View v) {

    }

}
