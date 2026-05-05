DELETE FROM task_tag;
DELETE FROM task;
DELETE FROM category;
DELETE FROM priority;
DELETE FROM status;
DELETE FROM tag;

INSERT INTO category (id, name) VALUES (1, 'Sport');
INSERT INTO priority (id, name) VALUES (1, 'High');
INSERT INTO status (id, name) VALUES (1, 'Todo');
INSERT INTO tag (id, label) VALUES (1, 'Java');