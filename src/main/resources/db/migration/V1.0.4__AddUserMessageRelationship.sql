ALTER TABLE demo.messages
  ADD CONSTRAINT messages_users_recipient_fk
FOREIGN KEY (recipient) REFERENCES demo.users (name);
ALTER TABLE demo.messages
  ADD CONSTRAINT messages_users_sender_fk
FOREIGN KEY (sender) REFERENCES demo.users (name);