-- liquibase formatted sql
-- changeset sergey:1
create index student_name_index ON student (name);

-- changeset sergey:2
create index faculty_name_color_index ON faculty (name, color);