// Astar.cpp
// http://en.wikipedia.org/wiki/A*
// 
// Modified by 罗铮 on 03/26/14.
// http://code.activestate.com/recipes/577457-a-star-shortest-path-algorithm/
// http://keithmaggio.wordpress.com/2011/11/23/
// c-link-a-star-shortest-path-algorithm-c-recipe/

#include "Astar.h"
#include <iostream>
#include <string>

using namespace std;

int main()
{
    srand(time(NULL));

    // create empty map
    for(int y = 0; y<m; y++)
        for(int x = 0; x<n; x++) 
            map[x][y] = 0;

    // fillout the map matrix with a '+' pattern
    for(int x = n/8; x < n*7/8; x++)
        map[x][m/2] = 1;
    
    for(int y = m/8; y < m*7/8; y++)
        map[n/2][y] = 1;
    
    
    // randomly select start and finish locations
    int xA, yA, xB, yB;
    switch(rand()%8)
    {
        case 0: xA=0;yA=0;xB=n-1;yB=m-1; break;
        case 1: xA=0;yA=m-1;xB=n-1;yB=0; break;
        case 2: xA=n/2-1;yA=m/2-1;xB=n/2+1;yB=m/2+1; break;
        case 3: xA=n/2-1;yA=m/2+1;xB=n/2+1;yB=m/2-1; break;
        case 4: xA=n/2-1;yA=0;xB=n/2+1;yB=m-1; break;
        case 5: xA=n/2+1;yA=m-1;xB=n/2-1;yB=0; break;
        case 6: xA=0;yA=m/2-1;xB=n-1;yB=m/2+1; break;
        case 7: xA=n-1;yA=m/2+1;xB=0;yB=m/2-1; break;
    }

    cout << "Map Size (X,Y): " << n << "," << m << endl;
    cout << "Start: " << xA << "," << yA << endl;
    cout << "Finish: " << xB << "," << yB << endl;
    
    // get the route
    clock_t start = clock();
    string route = pathFind(xA, yA, xB, yB);
    
    if(route == "") 
        cout << "An empty route generated!" << endl;
    
    clock_t end = clock();
    double time_elapsed = double(end - start);
    cout << "Time to calculate the route (ms): " << time_elapsed << endl;
    cout << "Route:" << endl;
    cout << route << endl << endl;

    // follow the route on the map and display it 
    if(route.length()>0)
    {
        int j; char c;
        int x = xA;
        int y = yA;
        map[x][y] = 2;

        for(int i = 0; i<(int)route.length(); i++)
        {
            c = route.at(i);
            j = atoi(&c); 
            x = x+dx[j];
            y = y+dy[j];
            map[x][y] = 3;
        }
        map[x][y] = 4;
    
        // display the map with the route
        for(int y = 0; y < m; y++)
        {
            for(int x = 0; x < n; x++)
                if     (map[x][y] == 0)
                    cout << "."; //not obstacle
                else if(map[x][y] == 1)
                    cout << "O"; //obstacle
                else if(map[x][y] == 2)
                    cout << "S"; //start
                else if(map[x][y] == 3)
                    cout << "R"; //route
                else if(map[x][y] == 4)
                    cout << "F"; //finish
            cout << endl;
        }
    }
 
    return 0;
}
