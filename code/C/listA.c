#include <stdio.h>
#include <stdlib.h>


// Define the structure for a doubly linked list node
typedef struct Node {
    int data;
    struct Node* next;
    struct Node* prev;
} Node;

// Function to create a new node
Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    if(!newNode) {
        printf("Memory error\n");
        return NULL;
    }
    newNode->data = data;
    newNode->next = newNode->prev = NULL;
    return newNode;
}

// Function to insert a new node at the end of the list
void insertNode(Node** head, Node* newNode) {
    if(*head == NULL) {
        *head = newNode;
    } else {
        Node* lastNode = *head;
        while(lastNode->next) {
            lastNode = lastNode->next;
        }
        lastNode->next = newNode;
        newNode->prev = lastNode;
    }
}

// Function to search for an element in the list
int searchNode(Node* head, int key) {
    int position = 0;
    while(head) {
        if(head->data == key) {
            return position;
        }
        head = head->next;
        position++;
    }
    return -1;
}

// Function to print the list
void printList(Node* head) {
    while(head) {
        printf("%d ", head->data);
        head = head->next;
    }
    printf("\n");
}


// Function to save the list to a CSV file
void saveListToCSV(Node* head, const char* filename) {
    FILE* file = fopen(filename, "w");
    if(!file) {
        printf("Error opening file\n");
        return;
    }

    Node* current = head;
    while(current) {
        fprintf(file, "%d", current->data);
        if(current->next) {
            fprintf(file, ",");
        }
        current = current->next;
    }

    fclose(file);
}

// Main function
int main() {
    Node* head = NULL;
    int input;

    // Get input from user until -1 is given
    printf("Enter integers (-1 to stop): ");
    while(scanf("%d", &input) == 1 && input != -1) {
        Node* newNode = createNode(input);
        insertNode(&head, newNode);
    }

    // Print the list
    printf("Doubly linked list: ");
    printList(head);

    int choice;
    while(1) {
        printf("Enter 1 to search for an element, 2 to add more numbers, 3 to save to CSV, 4 to exit: ");
        scanf("%d", &choice);

        switch(choice) {
            case 1: {
                // Get the element to search for from the user
                int search;
                printf("Enter the integer to search for: ");
                scanf("%d", &search);

                // Search for the element in the list
                int position = searchNode(head, search);
                if(position != -1) {
                    printf("Element %d found at position %d\n", search, position);
                } else {
                    printf("Element %d not found in the list\n", search);
                }
                break;
            }
            case 2: {
                // Get more input from user until -1 is given
                printf("Enter more integers (-1 to stop): ");
                while(scanf("%d", &input) == 1 && input != -1) {
                    Node* newNode = createNode(input);
                    insertNode(&head, newNode);
                }

                // Print the updated list
                printf("Updated doubly linked list: ");
                printList(head);
                break;
            }
            case 3: {
                // Save the list into a CSV file
                saveListToCSV(head, "list.csv");
                break;
            }
            case 4:
                return 0;
            default:
                printf("Invalid choice. Please enter 1, 2, 3 or 4.\n");
        }
    }

    return 0;
}
