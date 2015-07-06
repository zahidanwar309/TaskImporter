/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowdlab.taskimporter.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.crowdlab.taskimporter.activities.R;
import com.crowdlab.taskimporter.utils.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anwar
 */
public abstract class TaskImporterFragment extends Fragment {

    protected ImageView topbarMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //To change body of generated methods, choose Tools | Templates.
    }

    protected void setTopbarMenu() {
        topbarMenu = (ImageView) getActivity().findViewById(R.id.image_menu);
        
        if(topbarMenu != null)
            topbarMenu.setVisibility(View.VISIBLE);
    }

    protected abstract void setContentView();

    /*
     * Re-use the code from internet
     * 
     * sub class/classes will pass resource as @param
     * and the method will hand back parse JSONObject
     * which can be used to save or view data on the ListView
     */
    protected JSONArray readJsonFromLocalResources(InputStream resources) {

        InputStream is = resources;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        if (is != null) {

            int ctr;
            try {
                ctr = is.read();
                while (ctr != -1) {
                    byteArrayOutputStream.write(ctr);
                    ctr = is.read();
                }

                if (byteArrayOutputStream.size() > 0) {
                    Logger.d("JSON= " + byteArrayOutputStream.toString());
                    return new JSONArray(byteArrayOutputStream.toString());
                }

            } catch (IOException e) {
                Logger.e("Could not read the json file, IOException" + e);
            } catch (JSONException ex) {
                Logger.e("Could not parse into json object, JSONException" + ex);
            } finally {

                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.e("Could not close InputStream, IOException" + ex);
                }
            }
        }

        Logger.d("Could not return json object"); //Let's hope not to hit this line
        return null;
    }
}
