/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowdlab.taskimporter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.crowdlab.taskimporter.activities.R;
import com.crowdlab.taskimporter.activities.TaskListActivity;
import com.crowdlab.taskimporter.adapter.TaskListAdapter;
import com.crowdlab.taskimporter.db.TaskListDatabaseHelper;
import com.crowdlab.taskimporter.utils.Logger;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anwar
 */
public class TaskListFragment extends TaskImporterFragment {

    private ListView taskLists;
    private TaskListDatabaseHelper dbHelper;

    /*
     * 
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.task_list_fragment, container, false);

        dbHelper = new TaskListDatabaseHelper(((TaskListActivity) getActivity()).getDb());

        taskLists = (ListView) root.findViewById(R.id.list);

        setContentView();

        return root;
    }

    @Override
    protected void setContentView() {
        setTopbarMenu();

        try {
            JSONArray resourcesJsonArrayText = readJsonFromLocalResources(getResources().openRawResource(R.raw.tasks));

            if (resourcesJsonArrayText.length() > 0) {

                Logger.d("JSON Array data = " + resourcesJsonArrayText);

                JSONArray lastSavedJsonArrayText = dbHelper.getSavedJsonData();

                if (lastSavedJsonArrayText == null) {
                    Logger.d("No previously saved JSON text found");

                    dbHelper.saveJsonData(resourcesJsonArrayText);
                }

                JsonParser parser = new JsonParser();
                JsonElement r = parser.parse(resourcesJsonArrayText.toString());
                JsonElement l = parser.parse(lastSavedJsonArrayText.toString());

                if (!r.equals(l)) {
                    Logger.d("The json data is different from the last saved data");
                    dbHelper.clearJsonData();

                    if (dbHelper.saveJsonData(resourcesJsonArrayText)) {
                        Logger.d("New json data has been saved for future use");
                    }

                }
                TaskListAdapter adapter = new TaskListAdapter(resourcesJsonArrayText, getActivity());

                taskLists.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {

            Logger.e("There is a problem with json parsing" + e);
        }

    }

}
