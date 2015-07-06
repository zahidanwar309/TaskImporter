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
 * 
 * Option bean
 * 
 * 
 * 
 * 
 */


public class Option {
    
    private final int optionId, questionId;
    private final String type, label;
    private final Cursor c;

    public Option(Cursor c) throws Exception {
        this.c = c;
        this.optionId = c.getInt(c.getColumnIndex("option_id"));
        this.type = c.getString(c.getColumnIndex("type"));
        this.label = c.getString(c.getColumnIndex("label"));
        this.questionId = c.getInt(c.getColumnIndex("question_id"));
    }

    public int getOptionId() {
        return optionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }  
    
}
