/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowdlab.taskimporter.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.crowdlab.taskimporter.db.DatabaseHelper;
import com.crowdlab.taskimporter.utils.FragmentStack;
import com.crowdlab.taskimporter.utils.Logger;

/**
 *
 * @author anwar
 * 
 * 
 */
public abstract class TaskImporterActivity extends FragmentActivity {

    /*
     * Fragment manager to add/remove/replace fragments
     */
    protected FragmentManager fragmentmanager;

    /*
     * Save all the fragment for future use
     */
    protected final FragmentStack stack = new FragmentStack();

    /*
    * Set layout content
    */
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //To change body of generated methods, choose Tools | Templates.
        
        this.fragmentmanager = getFragmentManager();
        
        /*
        * Set default layout
        */
        setContentView(R.layout.task_importer_activity);
    }

    /*
     * Method to replace any fragment
     * @param contentFragment, the fragment, which you would like to replace with
     * 
     *
     */
    public int replaceFragment(Fragment contentFragment) {

        int result = 0;
        try {

            FragmentTransaction transaction = fragmentmanager.beginTransaction();

            transaction.replace(R.id.fragment, contentFragment);
            transaction.addToBackStack(null);
            result = transaction.commitAllowingStateLoss();

            getSupportFragmentManager().executePendingTransactions();
            
            Logger.d("Pushing " + contentFragment + " for future use");
            stack.push(contentFragment);

        } catch (Exception e) {
            
            Logger.e("Could not replace fragment"+ e);

        }

        return result;
    }
    
    
    /*
    * Re-usable method to use Database object
    */
    
    public DatabaseHelper getDb() {
        return DatabaseHelper.getHelper(this);
    }

}
