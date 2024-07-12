#include <stdio.h>
#include <stdlib.h>

int count_words(char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        printf("Error: Could not open file %s\n", filename);
        return -1;
    }

    int word_count = 0;
    char c;
    while ((c = fgetc(file)) != EOF) {
        if (c == ' ' || c == '\n' || c == '\t') {
            word_count++;
        }
    }

    fclose(file);
    return word_count - 1; // subtract 1 to account for the extra count
}

int main() {

    int num_words = count_words("example.txt");
    if(num_words != -1) {
        printf("Number of words: %d\n", num_words);
    }
    return 0;

}
