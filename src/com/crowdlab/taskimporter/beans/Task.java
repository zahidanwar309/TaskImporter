/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crowdlab.taskimporter.beans;

import android.database.Cursor;

/**
 *
 * @author anwar
 */
public class Task {
    
    private final int taskId;
    private final String title;
    private final boolean hidden;
    
    private final Cursor c;

    public Task(Cursor c) throws Exception {
        this.c = c;
        this.taskId = c.getInt(c.getColumnIndex("task_id"));
        this.title = c.getString(c.getColumnIndex("title"));
        this.hidden = c.getInt(c.getColumnIndex("hidden")) > 0;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isHidden() {
        return hidden;
    }
    
    
}
