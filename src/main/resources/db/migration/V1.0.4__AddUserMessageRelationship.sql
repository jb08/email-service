ALTER TABLE messenger.messages
  ADD CONSTRAINT messages_users_recipient_fk
FOREIGN KEY (recipient) REFERENCES messenger.users (name);
ALTER TABLE messenger.messages
  ADD CONSTRAINT messages_users_sender_fk
FOREIGN KEY (sender) REFERENCES messenger.users (name);