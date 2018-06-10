package example.khraisat.com.roomexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import example.khraisat.com.roomexample.model.ToDoModel;

/**
 * Created by Ayat khraisat  on 6/10/2018.
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: RoomExample
 * <p>
 * Blessed Tree IT
 */
public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private ArrayList<ToDoModel> toDoList;
    private Context context;

    public interface DeleteButtonClickListener {
        void onDeleteClick(ToDoModel toDoModel);
    }

    private DeleteButtonClickListener deleteButtonClickListener;

    public ToDoListAdapter(Context context, DeleteButtonClickListener deleteButton) {
        this.context = context;
        toDoList = new ArrayList<>();
        this.deleteButtonClickListener = deleteButton;
    }

    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_to_do, parent, false);

        return new ToDoListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ToDoModel toDo = toDoList.get(position);

        holder.toDoTextView.setText(toDo.getToDo());
        holder.toDoKeyTextView.setText(toDo.getToDoId() + "");


    }

    public void setToDoList(ArrayList<ToDoModel> toDoList) {
        this.toDoList = toDoList;
    }

    public void changeItems(ArrayList<ToDoModel> toDoModel) {
        this.toDoList.addAll(toDoModel);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (toDoList == null)
            return 0;
        return toDoList.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView toDoTextView;
        private TextView toDoKeyTextView;
        private Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            toDoTextView = view.findViewById(R.id.tv_to_do);
            toDoKeyTextView = view.findViewById(R.id.tv_to_do_key);
            deleteButton = view.findViewById(R.id.btn_delete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                deleteButtonClickListener.onDeleteClick(toDoList.get(getAdapterPosition()));
                    toDoList.remove(toDoList.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });
        }
    }

}
