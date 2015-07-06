The app should be able to read json data from local resources and perform
other tasks such as parse and save it to local sqlite database for future use.

The app should avoid to add duplicate data on the table.

Model::

tblsTask


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



