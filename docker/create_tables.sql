DROP TABLE IF EXISTS posts;

CREATE TABLE posts(
    post_id SERIAL PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    posted_date TIMESTAMPTZ NOT NULL,
    posted_text TEXT NOT NULL,
    CONSTRAINT posts_title_uk UNIQUE (title)
);

INSERT INTO posts (title, posted_date, posted_text) VALUES
  ('Easy speaking in English',
  '2017-05-16 13:35',
	'Hello students,
	We have a website for you. Its name is www.speakinlevels.com. You can use filters to search for people at your level and speak via Skype with them. You can also search for people by country or gender (men or women).
	You can also use a form to write to people, and you will be informed when people are ready to talk with you. Are you afraid to talk with somebody who you don’t know? Don’t worry! There are tips and topics which can help you start talking.
	Have fun and speak with your new friends! We hope that you will improve your English!'),
	('Swimming for Human Rights',
	'2017-05-16 21:45',
	'Twelve swimmers swim about 10 kilometres. They swim from California to Mexico. They want to support human rights. They want to show their solidarity with the world. One hundred schoolchildren meet them in Mexico. They cheer for them.
  The event organiser says that the swim is not a protest. She says that the swim is for human rights. It is to show that every life is important.
  Difficult words: support (help), human rights (the belief that everybody is free), solidarity (if you show solidarity, you show that you see someone’s problems and that you are sorry).');
