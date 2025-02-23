--create database ASSslot185

CREATE TABLE Users ( 
UserID INT PRIMARY KEY IDENTITY(1,1), 
FullName VARCHAR(100) NOT NULL, 
Email VARCHAR(100) NOT NULL UNIQUE, 
Password VARCHAR(255) NOT NULL, 
Role VARCHAR(20) NOT NULL CHECK (Role IN ('Owner', 'Inspector', 'Station', 'Police', 'Admin')),
Phone VARCHAR(15) NOT NULL, 
Address TEXT NOT NULL 
)

CREATE TABLE Vehicles ( 
VehicleID INT PRIMARY KEY IDENTITY(1,1), 
OwnerID INT NOT NULL, 
PlateNumber VARCHAR(15) NOT NULL UNIQUE, 
Brand VARCHAR(50) NOT NULL, 
Model VARCHAR(50) NOT NULL, 
ManufactureYear INT NOT NULL, 
EngineNumber VARCHAR(100) NOT NULL, 
FOREIGN KEY (OwnerID) REFERENCES Users(UserID)
)
CREATE TABLE InspectionStations ( 
StationID INT PRIMARY KEY IDENTITY(1,1), 
Name VARCHAR(100) NOT NULL, 
Address TEXT NOT NULL, 
Phone VARCHAR(15) NOT NULL, 
Email VARCHAR(100) NOT NULL UNIQUE, 
UserID INT NOT NULL, 
FOREIGN KEY (UserID) REFERENCES Users(UserID)
)

CREATE TABLE InspectionRecords ( --ket qua kiem dinh
RecordID INT PRIMARY KEY IDENTITY(1,1), 
VehicleID INT NOT NULL, 
StationID INT NOT NULL, 
InspectorID INT NOT NULL, 
RegistrationDate DATETIME,
InspectionDate DATETIME DEFAULT CURRENT_TIMESTAMP,  
Result VARCHAR(10) NOT NULL CHECK (Result IN ('Pass', 'Fail', 'Pending', 'Testing')),
CO2Emission DECIMAL(5,2) NOT NULL, 
HCEmission DECIMAL(5,2) NOT NULL, 
Comments TEXT, 
FOREIGN KEY (VehicleID) REFERENCES Vehicles(VehicleID), 
FOREIGN KEY (StationID) REFERENCES InspectionStations(StationID),
FOREIGN KEY (InspectorID) REFERENCES Users(UserID) 
)

CREATE TABLE Notifications ( 
NotificationID INT PRIMARY KEY IDENTITY(1,1), 
UserID INT NOT NULL, 
Message TEXT NOT NULL, 
SentDate DATETIME DEFAULT CURRENT_TIMESTAMP, 
IsRead BIT DEFAULT 0, 
FOREIGN KEY (UserID) REFERENCES Users(UserID) 
)

CREATE TABLE Logs ( --lich su hoat dong
LogID INT PRIMARY KEY IDENTITY(1,1), 
UserID INT NOT NULL, 
Action VARCHAR(100) NOT NULL, 
Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, 
FOREIGN KEY (UserID) REFERENCES Users(UserID) 
)

INSERT INTO Users (FullName, Email, Password, Role, Phone, Address) VALUES
('John Smith', 'johnsmith@gmail.com', 'password1', 'Owner', '1234567890', '123 Main Street, New York, USA'),
('Emily Davis', 'emilydavis@gmail.com', 'password2', 'Inspector', '9876543210', '456 Oak Street, Los Angeles, USA'),
('Michael Johnson', 'michaeljohnson@gmail.com', 'password3', 'Station', '4567891230', '789 Pine Street, Chicago, USA'),
('Sophia Brown', 'sophiabrown@gmail.com', 'password4', 'Police', '7890123456', '101 Maple Street, Houston, USA'),
('William Garcia', 'williamgarcia@gmail.com', 'password5', 'Owner', '6543217890', '202 Cedar Street, Phoenix, USA'),
('Olivia Martinez', 'oliviamartinez@gmail.com', 'password6', 'Inspector', '3216549870', '303 Elm Street, Philadelphia, USA'),
('James Wilson', 'jameswilson@gmail.com', 'password7', 'Station', '9871234560', '404 Birch Street, San Antonio, USA'),
('Isabella Taylor', 'isabellataylor@gmail.com', 'password8', 'Police', '1237894560', '505 Spruce Street, San Diego, USA'),
('Ethan Moore', 'ethanmoore@gmail.com', 'password9', 'Owner', '4569871230', '606 Poplar Street, Dallas, USA'),
('Admin', 'admin@gmail.com', 'password10', 'Admin', '7894561230', '707 Aspen Street, San Jose, USA');

INSERT INTO Vehicles (OwnerID, PlateNumber, Brand, Model, ManufactureYear, EngineNumber) VALUES
(1, 'ABC123', 'Toyota', 'Corolla', 2015, 'ENG123456'),
(5, 'XYZ789', 'Honda', 'Civic', 2018, 'ENG789456'),
(9, 'LMN456', 'Ford', 'Focus', 2020, 'ENG456123');

INSERT INTO InspectionStations (Name, Address, Phone, Email, UserID) VALUES
('New York Inspection Center', '123 Inspection St, New York, USA', '1112223333', 'nycenter@gmail.com', 3),
('Los Angeles Vehicle Check', '456 Auto Ave, Los Angeles, USA', '2223334444', 'lacenter@gmail.com', 7);

INSERT INTO InspectionRecords (VehicleID, StationID, InspectorID, RegistrationDate, Result, CO2Emission, HCEmission, Comments) VALUES
(1, 1, 2, '2024-02-01 10:00:00', 'Pass', 2.5, 0.3, 'No issues detected.'),
(2, 2, 6, '2024-02-02 14:30:00', 'Fail', 3.8, 0.7, 'Excessive emissions detected.'),
(3, 1, 2, '2024-02-03 09:15:00', 'Pass', 2.1, 0.2, 'Vehicle in good condition.');

INSERT INTO Notifications (UserID, Message) VALUES
(1, 'Your vehicle has passed the inspection.'),
(5, 'Your vehicle failed the inspection due to high emissions.'),
(9, 'Your vehicle inspection is scheduled for tomorrow.');

INSERT INTO Logs (UserID, Action) VALUES
(1, 'Logged in'),
(2, 'Performed vehicle inspection'),
(3, 'Approved station registration'),
(5, 'Checked vehicle inspection result'),
(9, 'Scheduled vehicle inspection');


Select * from InspectionRecords
order by RecordID desc
