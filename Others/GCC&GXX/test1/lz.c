// this is a c source, compile with gcc

struct Y
{
  int y_;
};

int cfunc( double x, struct Y y, double z)
{
  return  y.y_;
}
