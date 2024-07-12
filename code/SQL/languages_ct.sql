CREATE TABLE Languages (
    Native_Country VARCHAR(100),
    L1_Speakers INTEGER,
    L2_Speakers INTEGER,
    Language_Family VARCHAR(100),
    Early_Forms VARCHAR(100),
    Standard_Forms VARCHAR(100),
    Writing_System VARCHAR(50),
    Signed_Forms VARCHAR(50),
    ISO_639_1_Code VARCHAR(2),   -- ISO 639-1 code allows 2 letter representation
    Ranking INTEGER,
    PRIMARY KEY (ISO_639_1_Code)
);
