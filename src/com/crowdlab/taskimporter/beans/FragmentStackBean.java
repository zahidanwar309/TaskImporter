/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crowdlab.taskimporter.beans;

import android.app.Fragment;

/**
 *
 * @author anwar
 * 
 * Java bean to save fragment with id
 * 
 * Could be extendable
 * 
 * Working with multiple fragments, some time hard to track which fragment is currently on the view
 * 
 * Return fragment with fragment id is useful for the stage where you have back/cancel buttons
 */
public class FragmentStackBean {
    
    private final Fragment fragment;
    private final int id;

    public FragmentStackBean(Fragment fragment, int id) {
        this.fragment = fragment;
        this.id = id;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public int getId() {
        return id;
    }
}