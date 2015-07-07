The app should be able to read json data from local resources and perform
other tasks such as parse and save it to local sqlite database for future use.

The app should avoid to add duplicate data on the table.

Model::

tblTask


task_id INTEGER PRIMARY KEY,
title TEXT,
hidden INTEGER


tblQuestion


question_id INTEGER PRIMARY KEY,
title TEXT,
summary TEXT,
task_id INTEGER


tblOption

option_id INTEGER PRIMARY KEY,
type TEXT,
label TEXT,
question_id INTEGER

tblLastJson
_id INTEGER PRIMARY KEY,
type last_json_txt


CrowdlabDatabaseHelper.java

An abstract class, which extends Android SQLiteOpenHelper and has all the protected method, which used by other sub classes.

- CrowdlabDatabaseHelper(Context context) constractor
- void onCreate(SQLiteDatabase arg0) - overridden method to create the schema, tables and indexes
- void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) - overridden method to drop tables,indexes and re-create tables and indexes
- boolean createTable(SQLiteDatabase db, String table, String... columndefs) - to create table
- boolean createIndex(SQLiteDatabase db, String indexname, String on, boolean unique) - to create indexes
- void initialiseIndexes(SQLiteDatabase db) - overridden method to initialize indexes
- void initialiseTables(SQLiteDatabase db) - overridden method to initialize tables


DatabaseHelper.java

A singleton Database instance.
- synchronized DatabaseHelper getHelper(Context context) - create singleton database instance
- void onCreate(SQLiteDatabase db) - Initialize all tables and indexes
- void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) - Drop tables and indexes and re-create them
- void dirty(SQLiteDatabase db) - Drop tables and indexes
- void initialiseTables(SQLiteDatabase db) - Initialise all tables
- void initialiseIndexes(SQLiteDatabase db) - Initialise all indexes
- boolean insert(String tableName, ContentValues values) - To insert data on the table (return true if success)
- boolean delete(String tableName, String whereClause) - To delete row from the table
- Cursor rawQuery(String stm) - To run select statement, return Android Cursor ResultSet
- 

TaskListDatabaseHelper.java

A helper object to add/modify/delete task list.

- TaskListDatabaseHelper(DatabaseHelper db) - Constructor, accept DatabaseHelper object as @param
- boolean saveJsonData(JSONArray text) throws Exception - Save json array data as text on the table
- boolean clearJsonData() - clean previously saved json array data
- JSONArray getSavedJsonData() throws Exception - return saved json array data
- oid distributeJsonData(JSONArray arr) throws Exception - Save json data to tblTask, tblQuestion and tblOption tables.
- boolean insertTask(int taskId, String title, int hidden) - insert task details
- boolean insertQuestion(int questionId, String title, String summary, int taskId) - insert question details
- boolean insertOption(int optionId, String type, String label, int questionId) - insert option details

Controllers::

SplashActivity.java

An activity, which shows splash screen on app load.

- void onCreate(Bundle savedInstanceState) - create the splash screen.


TaskImporterActivity.java

An abstract class with protected methods.

- void onCreate(Bundle savedInstanceState)
- replaceFragment(Fragment contentFragment) - To replace fragment with another fragment
- getDb() - Database instance


TaskListActivity.java

Controller class, which control TaskListFragment.java fragment and other views.

- void onCreate(Bundle savedInstanceState)
- void onStart() - Replace middle content with TaskListFragment.java fragment
- 

QuestionActivity.java

Controller class, which control all question related views (not implemented yet).

View::

TaskImporterFragment.java

An abstract Fragment object.

- void onCreate(Bundle savedInstanceState)
- void setTopbarMenu() - Set top bar menu (left)
- void setContentView() - Set view on the middle
- JSONArray readJsonFromLocalResources(InputStream resources) - Read json data from resource and parse it and return it as JSONArray object


TaskListFragment.java

- View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) - inflate layout view
- void setContentView() - Set Fragments' content view, read, parse, save json on sqlite database
- 




