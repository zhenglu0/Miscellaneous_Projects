#define HEIGHT 5
#define WIDTH 3

int main() {
  double **p2DArray;

  // Allocate memory
  p2DArray = new double*[HEIGHT];
  for (int i = 0; i < HEIGHT; ++i)
    p2DArray[i] = new double[WIDTH];

  // Assign values
  p2DArray[0][0] = 3.6;
  p2DArray[1][2] = 4.0;

  // De-Allocate memory to prevent memory leak
  for (int i = 0; i < HEIGHT; ++i)
    delete [] p2DArray[i];
  delete [] p2DArray;

  return 0;
}