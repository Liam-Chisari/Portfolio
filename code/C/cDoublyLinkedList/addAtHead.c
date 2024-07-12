
    void addAtHead(int data List * list) {
        Node * current = NULL;
        if(list->head == NULL)
            list->head = createNode(data);
        else {
            current = list->tail;
            while(current->prev != NULL) {
                current->next = createNode(current->data);
