package com.hamsoace.AppForAll;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hamsoace.AppForAll.db.DatabaseHandler;
import com.hamsoace.AppForAll.models.Marks;

import java.util.ArrayList;
import java.util.List;

public class EnterMarks extends Activity {
    EditText firstName;
    EditText lastName;
    RadioButton CR;
    Button CO;
    EditText TM;
    EditText deleteName;
    DatabaseHandler dbHandler;

    String popUpContents[];
    PopupWindow popupWindow;


    //On create to show the grade entry form
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_marks);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        CR = (RadioButton) findViewById(R.id.credit);
        TM= (EditText) findViewById(R.id.total_marks);
        deleteName=(EditText) findViewById(R.id.deleteName);
        dbHandler = new DatabaseHandler(this, null, null, 2);

        //courses list
        List<String> courses = new ArrayList<String>();
        courses.add("PROG 8480");
        courses.add("PROG 8470");
        courses.add("PROG 8460");
        courses.add("PROG 8450");

        popUpContents = new String[courses.size()];
        courses.toArray(popUpContents);

        popupWindow = popupWindow();

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.course:
                        popupWindow.showAsDropDown(v, -5, 0);
                        break;
                }
            }
        };
        CO= (Button) findViewById(R.id.course);
        CO.setOnClickListener(handler);

    }

    private PopupWindow popupWindow() {
        //pop up window to show the list view  for the courses selection
        PopupWindow popupWindowContent = new PopupWindow(this);
        ListView listView = new ListView(this);
        listView.setAdapter(coursesAdapter(popUpContents));
        listView.setOnItemClickListener(new DropDownItemClickListener());

        popupWindowContent.setFocusable(true);
        popupWindowContent.setWidth(250);
        popupWindowContent.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindowContent.setContentView(listView);

        return popupWindowContent;
    }

    private ArrayAdapter<String> coursesAdapter(String coursesArray[]){

        //array adapter for handling arrays

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesArray){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                String item = getItem(position);
                String[] itemArr = item.split(" ");
                String text = itemArr[0];

                TextView listItem = new TextView(EnterMarks.this);

                listItem.setText(text);
                listItem.setTextSize(22);
                listItem.setPadding(10,10,10,10);

                return listItem;
            }
        };

        return adapter;
    }

    public void reset() {
        // using this function to reset the string as empty
        firstName.setText("");
        lastName.setText("");
        CO.setText("");
        CR.setText("");
        TM.setText("");
        deleteName.setText("");
    }
    public void onAddEntryClick(View view){

        //get values from form and submit to db

        Marks marks = new Marks( firstName.getText().toString(), lastName.getText().toString(),
                CO.getText().toString(), CR.getText().toString()
                , TM.getText().toString());
        dbHandler.addMarks(marks);
        reset();
    }
    public void onDeleteClick(View view){
        dbHandler.deleteMarks(deleteName.getText().toString());
        reset();
    }
}
