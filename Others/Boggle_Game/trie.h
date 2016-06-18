#ifndef _H_TRIE
#define _H_TRIE

#include <vector>
#include <string>

class Node {
public:
  Node() { mContent = ' '; mMarker = false; }
  char content() { return mContent; }
  void setContent(char c) { mContent = c; }
  bool wordMarker() { return mMarker; }
  void setWordMarker() { mMarker = true; }
  Node* findChild(char c);
  void appendChild(Node* child) { mChildren.push_back(child); }
  std::vector<Node*> children() { return mChildren; }

private:
  char mContent;
  bool mMarker;
  std::vector<Node*> mChildren;
};

class Trie {
public:
  Trie();
  ~Trie();
  void addWord(const std::string& s);
  bool searchWord(const std::string& s, bool search_prefix = false);
  bool searchPrefix(const std::string& s);
private:
  void delete_trie(Node *child);
  Node* root;
};

#endif /* _H_TRIE*/
