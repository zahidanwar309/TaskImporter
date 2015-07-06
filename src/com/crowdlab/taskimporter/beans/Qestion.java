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
public class Qestion {
    private final Cursor c;
    private final int questionId, taskId;
    private final String title, summary;

    public Qestion(Cursor c) throws Exception {
        this.c = c;
        this.questionId = c.getInt(c.getColumnIndex("question_id"));
        this.title = c.getString(c.getColumnIndex("title"));
        this.summary = c.getString(c.getColumnIndex("summary"));
        this.taskId = c.getInt(c.getColumnIndex("task_id"));
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
    
    
    
    
}
