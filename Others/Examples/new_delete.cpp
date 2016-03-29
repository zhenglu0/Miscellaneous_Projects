#include <iostream>
#include <vector>

using namespace std;

struct node
{
    struct nodeT *left;
    struct nodeT *right;
    int value;
};

int main(int argc, char * argv[])
{
    cout << "-------------------main begin--------------------------" << endl;
    node* ptr = new node();
    ptr->value = 8;
    cout << "ptr = " << ptr << endl;
    vector<node*> v;
    v.push_back(ptr);
    cout << v[0] << endl;
    delete ptr;
    //delete ptr;
    cout << v[0]->value << endl;
    cout << ptr->value << endl;
    cout << "-------------------main end--------------------------" << endl;
    return 0;
}

