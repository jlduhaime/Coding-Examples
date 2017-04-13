Disclaimer: Some of the files and projects here were written weeks to up to a year and a half ago,
	as such I believe I have improved greatly in code documentation and following general 
	coding guidelines.

Description of Files:

============
Fibonacci.py
============
Language: Python
Description: 
	- Custom library for fibonacci number objects
	- Fibonacci Object itself contains a list of the first fibonacci numbers
	  up to the integer given in the contructor
	- Customized print function override for ease of use by programmer
	- Customized next itterator override for ease of use by programmer
	- Customized Itterator override for ease of use by programmer

===========
Dynamic.cpp
===========
Language: C++
Description:
	- Dynamic Programming exmaple of the Longest Common Subsequence Algorithm
	- Takes in two command line arguments (the two strings to compare)
	- Performs error checking
	- Implements the algorithm and prints the result to the console
How to run:
	[developed and tested in a unix environment]
	compile: g++ -o Dynamic Dynamic.cpp
	run: ./Dynamic <stringFile1> <stringFile2>

======
main.c
======
Language: C
Description:
	- Custom shell ran in a unix environment
	- Supports directory creation, deletion, and traversal
	- Supports command execution
	- Supports multi-threadding for background command execution
How to run:
	[developed and tested in a unix environment]
	compile: gcc main.c
	run: ./a.out
	exit the shell: exit

=================
ListActivity.java
=================
Language: Java
Description:
	- One java file from a mobile application developed as a group
	- Handles the population of a list by pulling from the google places API
	- Displays restaurants and other information based on a given radius
	- Implements many core android functions such as:
		- ClickListeners
		- ArrayAdapters
		- Intents
		- Bundles