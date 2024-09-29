# Clojure - City Information System

This repository contains a Clojure-based information system designed to load, manage, and display data about cities in Canada. The project focuses on utilizing functional programming techniques in Clojure to manipulate city data and display information based on user queries. Developed as part of COMP 348.

**Language:** Clojure  
**Type:** Individual Assignment

## Main Takeaway

- Reading and parsing data from files in Clojure.
- Utilizing functional programming techniques like recursion, `map`, `reduce`, and `filter`.
- Manipulating and displaying data based on user input through a command-line interface.
- Gaining experience in string processing and working with Clojure’s `slurp` and `clojure.string` library.

## Assignment

### City Information System

#### Objective

To create a simple information system in Clojure that reads city data from a file and allows users to perform operations on this data.

#### Description

The system loads data from a file (`cities.txt`) containing information about various cities in Canada. The data file contains the city name, province, size, population, and area. The system provides a menu allowing users to list cities, display specific city information, and show provincial statistics. The menu is implemented using recursion to provide a seamless user experience.

#### Key Features

- **File Handling**  
  Reads data from a file (`cities.txt`) where each city record is stored as a line of text.
- **Data Parsing**  
  The data is parsed and stored in appropriate data structures, such as lists or vectors, using `slurp` to read the file content.
- **Command-line Interface**  
  A menu-driven system allows the user to:
  - List cities alphabetically or by province.
  - Display detailed information for a specific city.
  - Show provincial information, such as the total number of cities and population.
- **Sorting and Filtering**  
  Users can sort cities by name, size, or population, and filter cities by province.

- **Functional Programming Techniques**  
  Implements functional programming concepts such as `map`, `reduce`, and `filter` to process and display data.

- **Recursive Menu**  
  The program uses recursion to continuously display the menu until the user chooses to exit.

- **Error Handling**  
  Handles basic errors, such as invalid input, and ensures the data file is properly loaded.

### IDE

- **VSCode with Clojure extension**
