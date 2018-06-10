package example.khraisat.com.roomexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import example.khraisat.com.roomexample.model.ToDoModel;
import example.khraisat.com.roomexample.room.DataBaseManager;

public class ToDoListActivity extends AppCompatActivity implements AddToDoInterface, ToDoListAdapter.DeleteButtonClickListener {

    public static final String ADD_TO_DO_DIALOG = "add-to-do-dialog";
    private ToDoListAdapter toDoListAdapter;
    private RecyclerView toDoRecyclerView;
    private DataBaseManager dataBaseManager;
    private TextView emptyListTextView;
    private FloatingActionButton addFloatingActionButton;
    private ArrayList<ToDoModel> toDoModelArrayList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseManager = new DataBaseManager(this);
        setContentView(R.layout.activity_to_do_list);
        toDoRecyclerView = findViewById(R.id.rv_todo);
        emptyListTextView = findViewById(R.id.tv_empty_list);
        addFloatingActionButton = findViewById(R.id.fab_add_todo);
        toDoListAdapter = new ToDoListAdapter(this, this);
        toDoModelArrayList = new ArrayList<>();

        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AddToDoDialog addToDoDialog = new AddToDoDialog();

                addToDoDialog.setAddToDoInterface(ToDoListActivity.this);
                addToDoDialog.show(getSupportFragmentManager(), ADD_TO_DO_DIALOG);
            }
        });
        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toDoRecyclerView.setAdapter(toDoListAdapter);
        ArrayList<ToDoModel> toDoModelArrayList = dataBaseManager.getData();

        if (toDoModelArrayList != null && toDoModelArrayList.size() > 0) {
            this.toDoModelArrayList.addAll(toDoModelArrayList);
            toDoListAdapter.setToDoList(toDoModelArrayList);
        }


    }

    @Override
    public void onSaveClick(String toDo) {
        ToDoModel toDoModel = new ToDoModel(toDo);
        dataBaseManager.saveItemToDataBase(toDoModel);
        //toDoModelArrayList.add(toDoModel);
        toDoListAdapter.changeItems(dataBaseManager.getData());
    }


    @Override
    public void onDeleteClick(ToDoModel toDoModel) {
        dataBaseManager.remove(toDoModel);
    }
}
