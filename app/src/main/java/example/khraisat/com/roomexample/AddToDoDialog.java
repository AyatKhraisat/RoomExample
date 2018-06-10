package example.khraisat.com.roomexample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ayat khraisat  on 6/10/2018.
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: RoomExample
 * <p>
 * Blessed Tree IT
 */
public class AddToDoDialog extends DialogFragment {


    private EditText toDoEditText;
    private AddToDoInterface addToDoInterface;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View root = inflater.inflate(R.layout.dialog_add_todo, null);
        toDoEditText = root.findViewById(R.id.et_todo);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setPositiveButton(getContext()
                .getString(R.string.save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addToDoInterface.onSaveClick(toDoEditText.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton(getContext()
                .getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setView(root);
        return dialogBuilder.create();
    }


    public void setAddToDoInterface(AddToDoInterface addToDoInterface) {
        this.addToDoInterface = addToDoInterface;
    }
}
