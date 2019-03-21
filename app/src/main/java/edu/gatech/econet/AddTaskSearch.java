package edu.gatech.econet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ListResourceBundle;

import static android.view.View.VISIBLE;

public class AddTaskSearch extends AppCompatActivity {
    SearchView searchView;
    ListView listTasks;
    EditText hint;
    Button button;
    // Hard coded listview to get to use the bundle and retrieve information to next activity
    String proposedTasks[] = new String [] {"Use a steel straw","Eat vegetarian","Avoid useless wastes"};

    public void show(View view){
        listTasks.setVisibility(View.VISIBLE);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_search);
        hint = (EditText) findViewById(R.id.hintSearch);
        listTasks = (ListView) findViewById(R.id.listFound);
        searchView = (SearchView) findViewById(R.id.tasksearch);
        button = (Button) findViewById(R.id.nextAct);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,proposedTasks);
        listTasks.setAdapter(adapter);
        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long is) {
                //Toast.makeText(AddTaskSearch.this,proposedTasks[position],Toast.LENGTH_SHORT).show();
                OpenNewActivityWithParam(proposedTasks[position]);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenNewActivity();
            }
        });
    }
    private void OpenNewActivity(){
        Intent intent = new Intent(this, ParamNewTask.class);
        startActivity(intent);
    }
    private void OpenNewActivityWithParam(String data1){
        Intent intent = new Intent(this, ParamNewTask.class);
        Bundle bundle = new Bundle();
        bundle.putString("firstData",data1);
        intent.putExtras(bundle);
        //intent.putExtra("firstData",data1);
        //intent.putExtra("secondData",data2);
        startActivity(intent);
    }

}
