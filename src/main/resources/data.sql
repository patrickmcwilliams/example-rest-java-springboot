-- Init data for the Count demo

DROP TABLE IF EXISTS counts;

CREATE TABLE Counts (
	id IDENTITY NOT NULL PRIMARY KEY,
	Value int
)

-- Add more SQL down here for init scripts