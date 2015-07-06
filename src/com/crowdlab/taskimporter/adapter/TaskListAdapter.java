/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowdlab.taskimporter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.crowdlab.taskimporter.activities.R;
import com.crowdlab.taskimporter.utils.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anwar
 */
public class TaskListAdapter extends BaseAdapter {

    private final JSONArray json;
    private final Context context;
    private final LayoutInflater inflater;
    private int id;

    public TaskListAdapter(JSONArray json, Context context) {
        this.json = json;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return json.length();
    }

    public Object getItem(int arg0) {
        try {
            return json.get(arg0);
        } catch (Exception e) {
            Logger.e(e.toString() + "");
            return null;
        }
    }

    public long getItemId(int arg0) {
        try {
            JSONObject obj = (JSONObject) json.get(arg0);

            id = obj.getInt("id");

            return id;
        } catch (Exception e) {
            Logger.e(e.toString() + "");
            return -1;
        }
    }

    public View getView(final int index, View convertview, ViewGroup parent) {

        try {
            convertview = inflater.inflate(R.layout.task_list_rows, parent, false);
            TextView taskName = (TextView) convertview.findViewById(R.id.txt_task);
            
            ImageView leftIcon = (ImageView) convertview.findViewById(R.id.img_task);

            if (getCount() > 0) {
                
                
                taskName.setText(((JSONObject) getItem(index)).getString("title"));
            }
        } catch (Exception e) {
            Logger.e(e.toString() + "");
        }
        return convertview;
    }



}
