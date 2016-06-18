// Astar.h
// http://en.wikipedia.org/wiki/A*
// 
// Modified by 罗铮 on 03/26/14.
// http://code.activestate.com/recipes/577457-a-star-shortest-path-algorithm/
// http://keithmaggio.wordpress.com/2011/11/23/
// c-link-a-star-shortest-path-algorithm-c-recipe/

#include <iostream>
#include <queue>
#include <string>
#include <cmath>

using namespace std;

const   int n = 60; // horizontal size of the map
const   int m = 60; // vertical size size of the map
static  int map[n][m];
static  int closed_nodes_map[n][m]; // map of closed (tried-out) nodes
static  int priority_nodes_map[n][m]; // map of open (not-yet-tried) nodes
static  int dir_map[n][m]; // map of directions
const   int dir = 8; // number of possible directions to go at any position
// if dir == 4
//static int dx[dir]={1, 0, -1, 0};
//static int dy[dir]={0, 1, 0, -1};
// if dir == 8
static int dx[dir] = {1, 1, 0, -1, -1, -1, 0, 1};
static int dy[dir] = {0, 1, 1, 1, 0, -1, -1, -1};

class node {
    // current position
    int xPos;
    int yPos;
    // total distance already travelled to reach the node
    int level;
    // priority=level+remaining distance estimate
    int priority;  // smaller: higher priority

public:
    node(int xp, int yp, int d, int p) 
        {xPos=xp; yPos=yp; level=d; priority=p;}

    int getxPos()     const {return xPos;}
    int getyPos()     const {return yPos;}
    int getLevel()    const {return level;}
    int getPriority() const {return priority;}

    void updatePriority(const int & xDest, const int & yDest)
    {
         priority = level + estimate(xDest, yDest) * 10; //A*
    }

    // Give better priority to going strait instead of diagonally
    void nextLevel(const int & i) // i: direction
    {
         level += (dir == 8 ? (i%2 == 0?10:14) : 10);
    }
    
    // This is the hueristic
    // Estimation function for the remaining distance to the goal.
    const int & estimate(const int & xDest, const int & yDest) const
    {
        static int xd, yd, d;
        xd = xDest-xPos;
        yd = yDest-yPos;         

        // Euclidian Distance
        d = static_cast<int>(sqrt(xd*xd + yd*yd));

        // Manhattan distance
        //d = abs(xd)+abs(yd);
        
        // Chebyshev distance
        //d = max(abs(xd), abs(yd));

        return(d);
    }
};

// Determine priority (in the priority queue)
bool operator < (const node & a, const node & b)
{
    return a.getPriority() > b.getPriority();
}

// A-star algorithm.
// The route returned is a string of direction digits.
string pathFind( const int & xStart, const int & yStart, 
                 const int & xFinish, const int & yFinish )
{
    static priority_queue<node> pq[2]; // list of open (not-yet-tried) nodes
    static int pqi; // pq index
    static node* n0; // parent node
    static node* m0; // child node
    static int i, j, x, y, xdx, ydy;
    static char c;
    pqi = 0;

    // reset the node maps
    for(y = 0; y < m; y++)
    {
        for(x = 0; x < n; x++)
        {
            closed_nodes_map[x][y] = 0;
            priority_nodes_map[x][y] = 0;
        }
    }

    // Create the start node and push into list of open nodes
    n0 = new node(xStart, yStart, 0, 0);
    n0->updatePriority(xFinish, yFinish);
    pq[pqi].push(*n0);
    priority_nodes_map[x][y] = n0->getPriority(); // mark it on the open nodes map

    // A* search
    while(!pq[pqi].empty())
    {
        // get the current node w/ the highest priority
        // from the list of open nodes
        n0 = new node(pq[pqi].top().getxPos(), pq[pqi].top().getyPos(), 
                      pq[pqi].top().getLevel(), pq[pqi].top().getPriority());

        x = n0->getxPos(); 
        y = n0->getyPos();
        // remove the node from the open list
        pq[pqi].pop(); 
        // mark it on the nodes not open map
        priority_nodes_map[x][y]   = 0;
        // mark it on the closed nodes map
        closed_nodes_map[x][y] = 1;

        // quit searching when the goal state is reachedß
        if (x == xFinish && 
            y == yFinish) 
        {
            // generate the path from finish to start
            // by following the directions
            string path = "";
            while (x != xStart || 
                   y != yStart)
            {
                j = dir_map[x][y];
                c = '0' + (j+dir/2) % dir;
                path = c + path;
                x += dx[j];
                y += dy[j];
            }

            // garbage collection
            delete n0;
            
            // empty the leftover nodes
            while(!pq[pqi].empty()) 
                pq[pqi].pop();           
            
            return path;
        }

        // generate moves (child nodes) in all possible directions
        for(i = 0; i < dir; i++)
        {
            // this will calculate number of possible moves
            xdx = x + dx[i]; 
            ydy = y + dy[i];

            if( xdx >= 0 && xdx <= n-1 && 
                ydy >= 0 && ydy <= m-1 &&
                map[xdx][ydy] == 0 &&  //not obstacle
                closed_nodes_map[xdx][ydy] == 0 )
            {
                // generate a child node
                m0 = new node( xdx, ydy, n0->getLevel(), 
                               n0->getPriority());
                m0->nextLevel(i);
                m0->updatePriority(xFinish, yFinish);

                // if it is not in the priority_nodes_map then add into that
                if (priority_nodes_map[xdx][ydy] == 0)
                {
                    priority_nodes_map[xdx][ydy] = m0->getPriority();
                    pq[pqi].push(*m0);
                    // mark its parent node direction
                    dir_map[xdx][ydy] = (i+dir/2) % dir;
                }
                else if (priority_nodes_map[xdx][ydy] > m0->getPriority())
                {
                    // update the priority info
                    priority_nodes_map[xdx][ydy] = m0->getPriority();
                    // update the parent direction info
                    dir_map[xdx][ydy] = (i+dir/2)%dir;

                    // replace the node
                    // by emptying one pq to the other one
                    // except the node to be replaced will be ignored
                    // and the new node will be pushed in instead
                    while( pq[pqi].top().getxPos() != xdx ||
                           pq[pqi].top().getyPos() != ydy)
                    {                
                        pq[1-pqi].push(pq[pqi].top());
                        pq[pqi].pop();       
                    }
                    pq[pqi].pop(); // remove the wanted node
                    
                    // empty the larger size pq to the smaller one
                    if(pq[pqi].size() > pq[1-pqi].size()) 
                        pqi = 1-pqi;

                    while(!pq[pqi].empty())
                    {                
                        pq[1-pqi].push(pq[pqi].top());
                        pq[pqi].pop();       
                    }

                    pqi = 1-pqi;
                    pq[pqi].push(*m0); // add the better node instead
                } 
                delete m0; // garbage collection
            }
        }
        delete n0; // garbage collection
    }
    return ""; // no route found
}
