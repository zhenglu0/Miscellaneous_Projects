#include "trie.h"

using namespace std;

Node* Node::findChild(char c)
{
  for ( unsigned int i = 0; i < mChildren.size(); i++ ) {
    Node* tmp = mChildren[i];
    if ( tmp->content() == c )
      return tmp;
  }
    return NULL;
}

Trie::Trie()
{
  root = new Node();
}

Trie::~Trie()
{
  delete_trie(root);
}

void Trie::delete_trie(Node *node)
{
  for ( unsigned int i = 0; i < node->children().size(); i++ ) {
    Node* child = node->children()[i];
    if (child)
      delete_trie(child);
  }
  
  delete node;
}

void Trie::addWord(const string &s)
{
  Node* current = root;

  if ( s.length() == 0 ) {
    current->setWordMarker(); // an empty word
    return;
  }

  for ( unsigned int i = 0; i < s.length(); i++ ) {        
    Node* child = current->findChild(s[i]);
    if ( child != NULL ) {
      current = child;
    }
    else {
      Node* tmp = new Node();
      tmp->setContent(s[i]);
      current->appendChild(tmp);
      current = tmp;
    }
    if ( i == s.length() - 1 )
      current->setWordMarker();
  }
}


bool Trie::searchWord(const string &s, bool search_prefix)
{
  Node* current = root;

  for ( unsigned int i = 0; i < s.length(); i++ ) {
    Node* tmp = current->findChild(s[i]);
    if ( tmp == NULL )
      return false;
    current = tmp;
  }

  if ( current->wordMarker() || search_prefix)
    return true;
  else
    return false;
}

bool Trie::searchPrefix(const string &s)
{
  return searchWord(s, true);
}
