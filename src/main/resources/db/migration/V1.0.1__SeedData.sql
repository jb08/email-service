CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO messenger.messenger.messages (
    id, sender, recipient, message
    )
VALUES (uuid_generate_v1(),
        'Jason',
        'Shelley',
        'Do you want to go to container store across the street to see closet setups?'
        ),
       (uuid_generate_v1(),
        'Shelley',
        'Jason',
        'Yes sounds good! When will you be there?'
       ),
       (uuid_generate_v1(),
        'Jason',
        'Shelley',
        'On my way! <10 mins'),
       (uuid_generate_v1(),
        'Shelley',
        'Jason',
        '👌'
       ),
       (uuid_generate_v1(),
        'Trump',
        'Putin',
        'People love me. And you know what, I have been very successful. Everybody loves me.'
        ),
       (uuid_generate_v1(),
        'Putin',
        'Trump',
        'We shall fight against them, throw them in prisons and destroy them.'
        ),
       (uuid_generate_v1(),
        'Trump',
        'Putin',
        'I have always won, and I am going to continue to win. And that is the way it is.'
        ),
       (uuid_generate_v1(),
        'Putin',
        'Trump',
        'Nobody should pin their hopes on a miracle.'
        )



