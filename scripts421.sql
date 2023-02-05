
ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age >= 16),
    ALTER COLUMN name SET NOT NULL,
    ADD CONSTRAINT nickname_unique UNIQUE (name),
    ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);