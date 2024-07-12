#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"


int main(void) {

    List * listA = createList();
    List * listB = createList();

    add(5, listA);
    add(11, listA);
    add(22, listA);
    add(21, listA);
    add(-11, listA);
    add(67, listA);
    add(-15, listA);
    add(49, listA);
    add(18, listB);
    add(-15, listB);
    displayList(listA);
    displayList(listB);
    listSize(listB);
    delete(18, listB);
    delete(57, listB);
    displayList(listB);

    destroy(listA);
    destroy(listB);


    return EXIT_SUCCESS;

}
