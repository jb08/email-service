CREATE TABLE "messenger"."users" (
  "name"        TEXT PRIMARY KEY,
  "email"       TEXT NOT NULL,
  "created_at"  TIMESTAMP DEFAULT current_timestamp,
  "updated_at"  TIMESTAMP DEFAULT current_timestamp
);

CREATE INDEX idxUsers ON messenger.users(name);


