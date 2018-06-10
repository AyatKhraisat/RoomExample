package example.khraisat.com.roomexample.room;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import example.khraisat.com.roomexample.model.ToDoModel;

/**
 * Created by Ayat khraisat  on 6/10/2018.
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: RoomExample
 * <p>
 * Blessed Tree IT
 */
public class DataBaseManager {


    private static ToDoDao dao;

    public DataBaseManager(Context context) {
        dao = DatabaseHolder.getDatabaseInstance(context).toDoDao();
    }

    public void saveDataToDataBase(ArrayList<ToDoModel> arrayList) {
        SaveDataToDatabase saveDataToDatabase = new SaveDataToDatabase( arrayList);
        saveDataToDatabase.execute();
    }

    public void saveItemToDataBase(ToDoModel entry) {
        SaveItemToDatabase saveItemToDatabase = new SaveItemToDatabase( entry);
        saveItemToDatabase.execute();
    }


    public void remove(ToDoModel toDoModel) {
        RemoveItemFromDataBase removeItemFromDataBase = new RemoveItemFromDataBase(toDoModel);
        removeItemFromDataBase.execute();
    }


    public int listSize() {
        return dao.getNumberOfRows();
    }


    public ArrayList<ToDoModel> getData() {
        retrievedDataFromDatabase retrievedDataFromDatabase =
                new retrievedDataFromDatabase();
        try {
            ArrayList<ToDoModel> es = retrievedDataFromDatabase.execute().get();
            return es;
        } catch (InterruptedException | ExecutionException e) {
            return new ArrayList<>();
        }
    }


    public void removeAll() {
        dao.clearAll();
    }

    private static class SaveDataToDatabase extends AsyncTask<Void, Void, Void> {


        private ArrayList<ToDoModel> toDoArrayList;


        SaveDataToDatabase( ArrayList<ToDoModel> entry) {

            this.toDoArrayList = entry;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           dao.saveAll(toDoArrayList);
            return null;

        }
    }

    private static class SaveItemToDatabase extends AsyncTask<Void, Void, Void> {

        private ToDoModel toDoModel;


        SaveItemToDatabase(ToDoModel toDoModel) {
            this.toDoModel = toDoModel;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.save(toDoModel);
            return null;

        }
    }

    private static class RemoveItemFromDataBase extends AsyncTask<Void, Void, Void> {

        private ToDoModel toDoModel;


        RemoveItemFromDataBase(ToDoModel toDoModel) {
            this.toDoModel = toDoModel;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.delete(toDoModel);
            return null;

        }
    }

    private static class retrievedDataFromDatabase extends AsyncTask<Void, Void, ArrayList<ToDoModel>> {



        @Override
        protected ArrayList<ToDoModel> doInBackground(Void... voids) {

            return (ArrayList<ToDoModel>) dao.getAll();
        }

        @Override
        protected void onPostExecute(ArrayList<ToDoModel> es) {
            super.onPostExecute(es);
        }
    }

}
