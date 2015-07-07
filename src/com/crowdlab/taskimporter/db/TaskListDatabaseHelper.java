/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowdlab.taskimporter.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.crowdlab.taskimporter.utils.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anwar
 */
public class TaskListDatabaseHelper {

    private final DatabaseHelper db;

    public TaskListDatabaseHelper(DatabaseHelper db) {
        this.db = db;
    }

    public boolean saveJsonData(JSONArray text) throws Exception {

        ContentValues values = new ContentValues();
        values.put("last_json_txt", text.toString());

        if (db.insert("tblLastJson", values)) {
            Logger.d("We have successfully inserted task json data.");

            distributeJsonData(text);

            return true;
        } else {
            Logger.d("There is problem to insert task json data.");

            Logger.d("VALUES= " + values);
        }

        return false;
    }

    public boolean clearJsonData() {
        if (db.delete("tblLastJson", "_id > 0")) {
            Logger.d("tblLastJson tables data cleared successfully");
            return true;
        } else {
            Logger.d("There is problem to clean json data.");
        }

        return false;
    }

    public JSONArray getSavedJsonData() throws Exception {

        Cursor c = db.rawQuery("SELECT last_json_txt FROM tblLastJson;");

        String text = null;

        if (c.moveToNext()) {
            text = c.getString(0);

            Logger.d("JSON= " + text);
        }

        c.close();

        if (text != null) {
            return new JSONArray(text);
        }

        return null;

    }

    private void distributeJsonData(JSONArray arr) throws Exception {

        if (arr.length() > 0) {

            for (int i = 0; i < arr.length(); i++) {
                Logger.d("Tasks= " + arr.getString(i));

                JSONObject task = arr.getJSONObject(i);

                if (task.length() > 0) {

                    insertTask(task.getInt("id"), task.getString("title"), (task.getBoolean("hidden")) ? 1 : 0);

                    JSONArray questions = task.getJSONArray("questions");

                    for (int j = 0; j < questions.length(); j++) {
                        JSONObject question = questions.getJSONObject(j);
                        insertQuestion(question.getInt("id"), question.getString("title"), question.getString("summary"), task.getInt("id"));

                        JSONArray options = question.getJSONArray("options");

                        for (int k = 0; k < options.length(); k++) {

                            JSONObject option = options.getJSONObject(k);

                            insertOption(option.getInt("id"), option.getString("type"), option.getString("label"), question.getInt("id"));
                        }
                    }

                }

            }
        }

    }

    /*
     * Insert Task data on the table
     * protected keyword used for junit test
     */
    public boolean insertTask(int taskId, String title, int hidden) {
        ContentValues values = new ContentValues();
        values.put("task_id", taskId);
        values.put("title", title);
        values.put("hidden", hidden);

        return db.insert("tblTask", values);
    }
    /*
     * Insert Question data on the table
     * protected keyword used for junit test
     */

    public boolean insertQuestion(int questionId, String title, String summary, int taskId) {

        ContentValues values = new ContentValues();

        values.put("question_id", questionId);
        values.put("title", title);
        values.put("summary", summary);
        values.put("task_id", taskId);

        return db.insert("tblQuestion", values);
    }

    /*
     * Insert option data on the table
     * protected keyword used for junit test
     */
    public boolean insertOption(int optionId, String type, String label, int questionId) {

        ContentValues values = new ContentValues();

        values.put("option_id", optionId);
        values.put("type", type);
        values.put("label", label);
        values.put("question_id", optionId);

        return db.insert("tblOption", values);
    }
}
