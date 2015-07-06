/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crowdlab.taskimporter.activities;

import android.os.Bundle;
import com.crowdlab.taskimporter.fragments.TaskListFragment;

/**
 *
 * @author anwar
 * 
 * Act as controller for all the fragment
 * 
 * This controller should know, which fragment to draw
 * 
 * In this case, it will draw TaskListFragment in the middle content
 * 
 * Will replace the LinearLayout view, id=fragment on the task_importer_activity.xml
 * 
 * You can easily extend in future to add/remove/replace any fragment/fragments on the middle content
 * 
 * just below the topbar
 */
public class TaskListActivity extends TaskImporterActivity {

    private TaskListFragment taskListFragment = new TaskListFragment();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //To change body of generated methods, choose Tools | Templates.
    
        
    }

    @Override
    protected void onStart() {
        super.onStart(); //To change body of generated methods, choose Tools | Templates.
        replaceFragment(taskListFragment);
    }
    
    
    
    
}
