CREATE TABLE Performance(
	Id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Title VARCHAR2 (30),
	ShowType VARCHAR2 (30),
	Lang VARCHAR2 (30),
	Duration VARCHAR2 (40),
	Description VARCHAR (500),
	Price NUMBER
)
/

CREATE OR REPLACE PROCEDURE insertPerformance(
	id OUT Performance.ID%TYPE,
	title IN Performance.Title%TYPE,
	showType IN Performance.Showtype%TYPE,
	lang IN Performance.Lang%Type,
	duration IN Performance.Duration%Type,
	description IN Performance.Description%TYPE,
	price IN Performance.Price%TYPE
	)
	
IS 

BEGIN 
	INSERT INTO Performance (Title, ShowType, Lang, Duration, Description, Price)
	VALUES (title, showType, lang, duration, description, price) RETURNING ID INTO id;
END;
/

DECLARE 	
	PerformanceID int;
BEGIN
	insertPerformance(PerformanceID,'Macbeth', 'Theatre', 'English', '(1h 53m With 15 minute interval)',
		'Fleance - Banquo''s son, who survives Macbeth''s attempt to murder him. 
		At the end of the play, Fleance''s whereabouts are unknown. Presumably, 
		he may come to rule Scotland, fulfilling the witches''prophecy that Banquo''s 
		sons will sit on the Scottish throne.', 15);
	insertPerformance(PerformanceID, 'Hamlet', 'Theatre', 'English', '3h 35m (with Two 15 minute intervals)', 
		'After the death of his father, Hamlet''s mother Gertrude married Claudius, 
		Hamlet''s uncle and the new King of Denmark. One night, a ghost (said to be the 
		ghost of King Hamlet) appears to Hamlet''s best friend Horatio. ... Hamlet is in 
		love with Ophelia, the daughter of Claudius''most trusted counselor, Polonius.', 15);
	insertPerformance(PerformanceID, 'Le Miserables', 'Musical', 'French/English', '2h 40m(with 15 minute interval)', 
		'Based on the novel by Victor Hugo, ''Les Miserables''travels with prisoner-on-parole, 
		24601, Jean Valjean, as he runs from the ruthless Inspector Javert on a journey beyond the 
		barricades, at the center of the June Rebellion.', 15);
	insertPerformance(PerformanceID, 'Verdi''s Aida', 'Opera', 'Italian', '3h 5m(with Two 15 minute intervals)',
		'An enslaved Nubian princess, Aida, finds her heart entangled with Radames, an Egyptian 
		soldier who is betrothed to the Pharaoh''s daughter, Amneris. As their forbidden love 
		blossoms, Aida is forced to weigh her heart against the responsibility that she faces as the leader of her people', 15);
	insertPerformance(PerformanceID, 'Foo Fighters', 'Concert', '', '3h+','Foo Fighters is an American rock band, 
		formed in Seattle, Washington in 1994. It was founded by Nirvana drummer Dave Grohl 
		as a one-man project following the dissolution of Nirvana after the death of Kurt Cobain.', 15);
	insertPerformance(PerformanceID, 'Arctic Monkeys', 'Concert', '', '3h+', 'Arctic Monkeys are an English rock band 
		formed in 2002 in High Green, a suburb of Sheffield. The band consists of Alex Turner (lead vocals, 
		rhythm/lead guitar), Matt Helders (drums, vocals), Jamie Cook (lead/rhythm guitar) and Nick O''Malley (bass, backing vocals)', 15);	
END;
/

CREATE TABLE Showing (
	Id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	PerformanceID INT , 
	ShowDate Date,
	ShowTime VARCHAR (10),
	CONSTRAINT PerformanceId_FK FOREIGN KEY (PerformanceID) REFERENCES Performance(ID)
)
/

CREATE OR REPLACE PROCEDURE insertShowing(
	id OUT Showing.ID%TYPE,
	performanceID IN Showing.performanceID%TYPE,
	showDate IN Showing.ShowDate%TYPE,
	showTime IN Showing.ShowTime%TYPE
)
IS

BEGIN 
	INSERT INTO Showing (PerformanceID, ShowDate, ShowTime)
	VALUES (performanceId, showDate, showTime) RETURNING ID INTO id;
END;
/

DECLARE 
	ShowingID int;
BEGIN 
	insertShowing(ShowingID, 1,'24-MAY-18','9AM');
	insertShowing(ShowingID, 1,'24-MAY-18','5PM');
	insertShowing(ShowingID, 2,'19-OCT-18','9AM');
	insertShowing(ShowingID, 2,'19-OCT-18','5PM');
	insertShowing(ShowingID, 3,'05-JUL-18','9AM');
	insertShowing(ShowingID, 3,'05-JUL-18','5PM');
	insertShowing(ShowingID, 4,'11-JAN-18','9AM');
	insertShowing(ShowingID, 4,'11-JAN-18','5PM');
	insertShowing(ShowingID, 5,'24-JUL-18','5PM');
	insertShowing(ShowingID, 6,'10-NOV-17','5PM');
END ;
/

CREATE TABLE Customer (
	Email VARCHAR(50) PRIMARY KEY,
	FirstName VARCHAR(30),
	LastName VARCHAR(30),
	AddressLine1 VARCHAR(40),
	AddressLine2 VARCHAR(40),
	Postcode VARCHAR(40),
	Country VARCHAR(30),
	Password VARCHAR(30),
	IsAdmin INT,
	CONSTRAINT ck_IsAdmin CHECK (IsAdmin IN (0,1)) 
)
/

CREATE OR REPLACE PROCEDURE insertCustomer (
	email IN  Customer.Email%TYPE,
    firstName IN  Customer.FirstName%TYPE,
    lastName IN  Customer.LastName%TYPE,
	addressLine1 IN Customer.AddressLine1%TYPE,
	addressLine2 IN Customer.AddressLine2%TYPE,
	postcode IN Customer.Postcode%TYPE,
	country IN Customer.Country%TYPE,
	password IN Customer.Password%TYPE
)
IS 
BEGIN
	INSERT INTO Customer (Email, FirstName, LastName,  AddressLine1, AddressLine2, Postcode, Country, Password, IsAdmin)
	VALUES (email, firstName, lastName, addressLine1, addressLine2, postcode, country, password, 0);
END;
/

DECLARE

BEGIN
	insertCustomer('jameswashington@hotmail.co.uk','James','Washington','42 Fairey Street','Birmingham','B45 8GW','United Kingdom','meowwoof');
	insertCustomer('richardalexander@Gmail.com','Richard','Alexander','1 Pomeroy Way','Birmingham','B37 7WB','United Kingdom','wackyactor48');
	insertCustomer('jessicareed@hotmail.co.uk','Jessica ','Reed','431 Tyburn Rd','Birmingham','B24 8HJ ','United Kingdom','bestsilk82');
	insertCustomer('jessicathomas@hotmail.co.uk','Jessica ','Thomas','134 Greenfield Rd','Birmingham','B17 0EG','United Kingdom','dizzyfood39');
	insertCustomer('williamprice@hotmail.co.uk','William ','Price','41 Landor St','Birmingham','B8 1AE','United Kingdom','pinkcrown83');
	insertCustomer('susanwhite@hotmail.co.uk','Susan ','White','116 Kings Rd','Birmingham','B11 2AT','United Kingdom','shortgrape48');
	insertCustomer('deborahalexander@gmail.com','Deborah ','Alexander','60 Frederick St','Birmingham','B1 3HS','United Kingdom','hotsky66');
	insertCustomer('karentaylor@gmail.com','Karen ','Taylor','15a Albion St','Birmingham','B1 3ED','United Kingdom','bigjelly62');
	insertCustomer('deborahgarcia@hotmail.co.uk','Deborah ','Garcia','40 Goodman St','Birmingham','B1 2SS','United Kingdom','jumpypart16');
	insertCustomer('jameskelly@gmail.com','James ','Kelly','One Victoria Square','Birmingham','B1 1BD','United Kingdom','slimyspace47');
	insertCustomer('elizabethperez@gmail.com','Elizabeth ','Perez','41 The Mailbox','Birmingham','B1 1RE','United Kingdom','poorring71');
	insertCustomer('donnaperry@gmail.com','Donna ','Perry','69 Albion St','Birmingham','B1 3EA','United Kingdom','jazzycap33');
	insertCustomer('deborahmiller@hotmail.co.uk','Deborah ','Miller','84 Cambridge St','Birmingham','B1 2NP','United Kingdom','hotmatch99');
	insertCustomer('roberthenderson@gmail.com','Robert ','Henderson','18 Albion St','Birmingham','B1 3ED','United Kingdom','    ');
	insertCustomer('barbaragonzales@hotmail.co.uk','Barbara ','Gonzales','The Waters Edge','Birmingham','B1 2HL','United Kingdom','tallwax96');
	insertCustomer('maryhayes@hotmail.co.uk','Mary ','Hayes','Granville St','Birmingham','B1 1SB','United Kingdom','sadcanary89');
	insertCustomer('patriciachaandeer@hotmail.co.uk','Patricia ','Chaandeer','264 Broad Street','Birmingham','B1 2DS','United Kingdom','lumpyhoney76');
	insertCustomer('margaretdavis@gmail.com','Margaret ','Davis','42 Fairey Street','Birmingham','B45 8GW','United Kingdom','graywinter62');
	insertCustomer('karenrivera@gmail.com','Karen ','Rivera','1 Pomeroy Way','Birmingham','B37 7WB','United Kingdom','bumpyrhino78');
	UPDATE Customer SET IsAdmin = 1 WHERE Email = 'jameswashington@hotmail.co.uk';
END;
/

CREATE TABLE Ticket (
	Id INT GENERATED ALWAYS AS IDENTITY,
	Email VARCHAR2(50),
	ShowingId INT,
	Type VARCHAR2(20),
	Price NUMBER,
	SeatRow VARCHAR2(1),
	SeatNumber INT,
	IsInBasket INT,
	CONSTRAINT fk_Email FOREIGN KEY (Email) REFERENCES Customer(Email) ON DELETE CASCADE,
	CONSTRAINT fk_ShowingId FOREIGN KEY (ShowingId) REFERENCES Showing(Id) ON DELETE CASCADE,
	CONSTRAINT ck_IsInBasket CHECK (IsInBasket IN (0,1)) 
)
/

CREATE OR REPLACE PROCEDURE insertBasketTicket(
	id OUT Ticket.Id%TYPE,
	email IN Ticket.Email%TYPE,
	showingId IN Ticket.ShowingId%TYPE,
	type IN Ticket.Type%TYPE,
	price IN Ticket.Price%TYPE,
	seatRow IN Ticket.SeatRow%TYPE,
	seatNumber IN Ticket.SeatNumber%TYPE
)
IS 

BEGIN
    INSERT INTO Ticket (Email, ShowingId, Type, Price, SeatRow, SeatNumber, IsInBasket)
    VALUES (email, showingId, type, price, seatRow, seatNumber, 1) RETURNING Id INTO id;
END;
/

CREATE OR REPLACE PROCEDURE payForBasketTickets(
	email IN Ticket.Email%TYPE
)
IS 

BEGIN
    UPDATE Ticket SET IsInBasket = 0 WHERE Email = email AND IsInBasket = 1;
END;
/