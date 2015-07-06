/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crowdlab.taskimporter.utils;

import android.app.Fragment;
import com.crowdlab.taskimporter.beans.FragmentStackBean;
import java.util.ArrayList;

/**
 *
 * @author anwar
 */
public class FragmentStack {

    private int id = 0;
    private FragmentStackBean stack;
    private ArrayList<FragmentStackBean> al = new ArrayList();

    public void push(Fragment f) {


        id++;
        stack = new FragmentStackBean(f, id);
        al.add(stack);
    }

//    public Fragment pop() {
//
//        Fragment s = null;
//        if (al != null) {
//            for (int i = 0; i < al.size(); i++) {
//                if (al.get(i).getId() == id - 1) {
//                    id--;
//                    s = al.get(i).getFragment();
//                    al.remove(i);
//                    return s;
//                }
//
//            }
//        }
//        return s;
//    }
 
        
    public Fragment pop() {
        if(al != null) {
            int size = al.size();
            if (size > 0) {           
                id--;
                al.remove(size - 1);
                size = al.size();
                return (size <= 0) ? null : al.get(size - 1).getFragment();
            }
        }
        return null;
    }

    public void clear() {
        if (al != null && al.size() > 0) {
            for (int i = 0; i < al.size(); i++) {
                al.remove(i);
                id = 0;
            }
        }
    }
}
