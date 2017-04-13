#include <vector>
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

void lcs(string, string, vector<vector<int> >&, vector<vector<int> >&);
void print(vector<vector<int> >&, string, int, int);

// DEBUG
void printv(vector<vector<int> > b, int a, int c) 
{
	cout << "PRINTING" << endl;

	for(int i = 0; i < a; i++)
	{
		for (int j = 0; j < c; j++)
			cout << b[i][j] << " ";
		cout << endl;
	}		
}

int main(int args, char** argv) {
   

   if (args != 3)
   {
     cout << "Invalid arguments. Usage: <EXECUTABLE> <STRING 1 FILENAME> <STRING 2 FILENAME>" << endl;
     return 1;
   }

   ifstream file1;
   ifstream file2;

   file1.open(argv[1]);
   file2.open(argv[2]);

   if (!file1.is_open() || !file2.is_open())
   {
        cout << "1 or more file does not exist" << endl;
         return 1;
   }

   string str1, str2;

   getline(file1, str1);
   getline(file2, str2);

   file1.close();
   file2.close();

   vector<vector<int> > b(str1.length()+1,vector<int>(str2.length()+1, 0));
   vector<vector<int> > c(str1.length()+1,vector<int>(str2.length()+1, 0));


   lcs(str1, str2, b, c);

   print(b, str1, str1.length(), str2.length());
   cout << endl;
}

void lcs(string X, string Y, vector<vector<int> > &b, vector<vector<int> > &c) {
   int m = X.length();
   int n = Y.length();

   for( int i = 1; i <= m; i++) {
	for (int j = 1; j <= n; j++) {
	    if (X[i-1] == Y[j-1]) {
		c[i][j] = c[i-1][j-1] + 1;
		b[i][j] = -1;
	    }
	    else if (c[i-1][j] >= c[i][j-1]) {
		c[i][j] = c[i-1][j];
		b[i][j] = 0;
	    }
	    else {
		c[i][j] = c[i][j-1];
		b[i][j] = 1;
	    }
	}
   }
}

void print(vector< vector<int> > &b, string X, int i, int j) {
    if (i == 0 || j == 0)
	return;
    if (b[i][j] == -1) {
	print(b, X, i-1, j-1);
	cout << X[i-1];
    }
    else if (b[i][j] == 0)
	print(b, X, i-1, j);
    else
	print(b, X, i, j-1);
}
