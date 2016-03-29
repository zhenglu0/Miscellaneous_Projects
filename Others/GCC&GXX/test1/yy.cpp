///////////////////////////////////////////
// this is a cpp source, compile with g++

#include <iostream>

using namespace std;

struct Y
{
  int y_;
  virtual void f() {};    // this makes the problem!
};

extern "C" int cfunc( double x, struct Y y, double z);

int main()
{
  Y y;
  y.y_ = 1;
  cout << cfunc(3.14, y, 4.14) << endl;   // bad!

  return 0;
}
