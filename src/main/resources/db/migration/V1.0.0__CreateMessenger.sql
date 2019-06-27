CREATE TABLE "messenger"."messages" (
  "id"          UUID PRIMARY KEY,
  "sender"      TEXT NOT NULL,
  "recipient"   TEXT NOT NULL,
  "message"     TEXT NOT NULL,
  "created_at"  TIMESTAMP DEFAULT current_timestamp,
  "updated_at"  TIMESTAMP DEFAULT current_timestamp
);

CREATE INDEX idx ON messenger.messages(id);
CREATE INDEX conversation ON messenger.messages(sender, recipient);
