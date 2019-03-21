package edu.gatech.econet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParamNewTask extends AppCompatActivity {
    Button moreButton;
    Button lessButton;
    TextView setFrequency;
    TextView textFrequency;
    Button goHT;
    private int frequency = 3;

    String receivedTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_new_task);
        moreButton = findViewById(R.id.buttonMore);
        lessButton = findViewById(R.id.buttonLess);
        setFrequency = findViewById(R.id.setFrequency);
        textFrequency = findViewById(R.id.textFrequency);
        goHT = findViewById(R.id.goHabitTracker);
        final Bundle bundleIn = getIntent().getExtras();

        if (bundleIn!=null){
            receivedTask = bundleIn.getString("firstData");
        }
        TextView selectedTaskText = (TextView) findViewById(R.id.taskSelected);
        String sentence = "Set the parameters for the task " + receivedTask;
        selectedTaskText.setText(sentence);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more();
            }
        });
        lessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                less();
            }
        });
        goHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenNewActivity(receivedTask,bundleIn);
            }
        });
    }
    private void more(){
        if (frequency<6){
            frequency++;
            setFrequency.setText((String.valueOf(frequency)));
        }
    }
    private void less(){
        if(frequency>1){
            frequency--;
            setFrequency.setText((String.valueOf(frequency)));
        }
    }
    private void OpenNewActivity(String taskName, Bundle bundleInn){
        Intent intent = new Intent(this, habitTracker.class);
        Bundle bundleOut = new Bundle();
        for (int i=0; i< bundleInn.size();i++){
            bundleOut.putString("Task_List"+Integer.toString(i), bundleInn.getString("Task_List"+Integer.toString(i)));
        }
        bundleOut.putString("Task_List"+Integer.toString(bundleInn.size()+1),taskName);
        intent.putExtras(bundleOut);
        startActivity(intent);
    }

}
