# DataBridge

This project is a Java Swing desktop application that allows users to:
•View database tables
•Preview table data
•Select specific columns or all columns
•Export database records into an Excel sheet

The application connects to a database using JDBC and provides a simple GUI workflow for exporting data.

# Features
•Database table listing
•Data preview (limited rows for quick view)
•Custom column selection
•Support for exporting all columns
•Excel sheet generation
•Multi-window GUI workflow

# Project Structure
1. TableSelectionPage
Displays available database tables
Allows user to select a table for export

2. PreviewSheet
Shows preview of selected table data
Accepts column selection input
Accepts Excel sheet name
Initiates export process

3. CreateSheet
Executes SQL query
Generates Excel file from database data

4. Connection Layer
GetConnection handles database connectivity

5. POJO
Stores user input and configuration details

# Technologies Used
Java
Swing (GUI)
JDBC
MySQL 
Excel file handling library(Apache POI)

# Usage
Connect to database.
Select a table from the list.
Preview table data.
Enter: all to export all columns
OR
column names separated by commas (e.g., name,age,address)
Enter sheet name.
Click Proceed to generate Excel file.

#Notes
Column names must match database column names.
Table name input must be valid.
Basic error handling included.

# Future Improvements
Input validation for table and column names
Prepared statements for improved security
File location selection
Better layout management (responsive UI)
