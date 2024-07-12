#ifndef LIST_HEAD
#define LIST_HEAD

typedef struct node Node;

typedef struct list List;

List * createList();
void add(int data, List * list);
void delete(int data, List * list);
void displayList(List * list);
void listSize(List * list);
void destroy(List * list);

#endif
