--
-- SQL script to initialize a smart race instance database
--

-- Table representing a race
CREATE TABLE race (
  name TEXT NOT NULL PRIMARY KEY,
  location TEXT NOT NULL,
  description TEXT,
  state INTEGER NOT NULL DEFAULT 0,
  creation_date INTEGER NOT NULL DEFAULT 0,
  last_opening_date INTEGER NOT NULL DEFAULT 0,
  last_update_date INTEGER NOT NULL DEFAULT 0,
  version TEXT NOT NULL
);

-- Table representing a racer team
CREATE TABLE racer_team (
  racer_team_uuid TEXT NOT NULL PRIMARY KEY,
  name TEXT NOT NULL UNIQUE
);

-- Table representing a racer
CREATE TABLE racer (
  racer_uuid TEXT NOT NULL PRIMARY KEY,
  racer_team_uuid TEXT NOT NULL,
  last_name TEXT NOT NULL,
  first_name TEXT NOT NULL,
  sex INTEGER NOT NULL DEFAULT 0,
  birthday_date INTEGER NOT NULL DEFAULT 0,
  comments TEXT,
  UNIQUE (last_name, first_name),
  FOREIGN KEY (racer_team_uuid) REFERENCES racer_team(racer_team_uuid) ON DELETE CASCADE
);

-- Table representing a race category
CREATE TABLE race_category (
  race_category_uuid TEXT NOT NULL PRIMARY KEY,
  name TEXT NOT NULL UNIQUE,
  description TEXT
);

-- Table representing a race track
CREATE TABLE race_track (
  race_track_uuid TEXT NOT NULL PRIMARY KEY,
  name TEXT NOT NULL UNIQUE,
  distance REAL NOT NULL DEFAULT 0.0,
  elevation REAL,
  description TEXT,
  team_size_limit INTEGER NOT NULL DEFAULT 1,
  state INTEGER NOT NULL DEFAULT 0,
  start_time INTEGER,
  end_time INTEGER
);

-- Table representing a race record
CREATE TABLE race_record (
  race_record_uuid TEXT NOT NULL PRIMARY KEY,
  number INTEGER NOT NULL UNIQUE,
  racer_team_uuid TEXT NOT NULL,
  racer_track_uuid TEXT NOT NULL,
  state INTEGER NOT NULL DEFAULT 0,
  end_date INTEGER,
  FOREIGN KEY (racer_team_uuid) REFERENCES racer_team (racer_team_uuid) ON DELETE CASCADE,
  FOREIGN KEY (racer_track_uuid) REFERENCES  racer_track (racer_track_uuid) ON DELETE CASCADE
);

-- Table for mapping between race categories and race records
CREATE TABLE race_category_assignment (
  race_category_uuid TEXT NOT NULL,
  race_record_uuid TEXT NOT NULL,
  PRIMARY KEY (race_category_uuid, race_record_uuid),
  FOREIGN KEY (race_category_uuid) REFERENCES race_category (race_category_uuid) ON DELETE CASCADE,
  FOREIGN KEY (race_record_uuid) REFERENCES race_record (race_record_uuid) ON DELETE CASCADE
);


