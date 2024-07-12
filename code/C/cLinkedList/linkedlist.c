#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"

struct node {
    int data;
    struct node * next;
};


struct list {
    Node * head;
};

Node * createNode(int data);

Node * createNode(int data){
    Node * newNode = malloc(sizeof(Node));
    if(!newNode) {
        return NULL;
    }
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
    }

List * createList(){
    List * list = malloc(sizeof(List));
    if(!list) {
        return NULL;
    }
    list->head = NULL;
    return list;

}

    void displayList(List * list) {
        Node * current = list->head;
        if(list->head == NULL)
            return;

        for(; current != NULL; current = current->next) {
            printf("%d\n", current->data);
        }

    }

    void listSize(List * list){
        int i = 0;
        Node * current = list->head;
        if(list->head == NULL){
            printf("List is empty\n");
            return;
        }
        for(; current != NULL; current = current->next) {
            i++;
        }
        printf("List has %d elements.\n", i);
        return;

    }
        



    void add(int data, List * list){
        Node * current = NULL;
        if(list->head == NULL) {
            list->head = createNode(data);
        }
        else {
            current = list->head;
            while(current->next!=NULL) {
                current = current->next;
            }
            current->next = createNode(data);
        }

    }

    void delete(int data, List * list) {
        Node * current = list->head;
        while(current != NULL){
            if(current->data == data){
                if(current == list->head)
                    list->head = current->next;
                free(current);
                return;
            }
            current = current->next;
        }
    }


    void destroy(List * list){
        Node * current = list->head;
        Node * next = current;
        while(current != NULL){
            next = current->next;
            free(current);
            current = next;
        }
        free(list);
    }




