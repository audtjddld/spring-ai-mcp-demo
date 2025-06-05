DROP TABLE IF EXISTS TODO;

create table TODO
(
    ID               BIGINT auto_increment
        primary key,
    LAST_UPDATE_TIME TIMESTAMP,
    REG_DATE_TIME    TIMESTAMP,
    DATE             CHARACTER VARYING(255),
    DESCRIPTION      CHARACTER VARYING(255),
    TITLE            CHARACTER VARYING(255)
);

INSERT INTO todo (title, description, date, reg_date_time, last_update_time)
VALUES ('Grocery Shopping', 'Buy milk, eggs, bread, and vegetables for the week.', '2025-04-25', NOW(), NOW()),
       ('Team Meeting Preparation', 'Prepare slides and agenda for the weekly team sync.', '2025-04-26', NOW(), NOW()),
       ('Complete Spring WebFlux Tutorial', 'Finish the reactive programming section and build a sample app.',
        '2025-04-27', NOW(), NOW());
