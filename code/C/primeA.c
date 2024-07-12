 #include <stdio.h>
int isPrime(int n) {
    if (n <= 1) return 0;
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            return 0;
        }
    }
    return 1;
}
void printPrimes(int n) {
    if (n < 2) {
        return;
    }
    for (int i = 2; i < n; i++) {
        if (isPrime(i)) {
            printf("%d ", i);
        }
    }
    printf("\n");
}
int main() {
    int n;
    printf("Enter an integer: ");
    scanf("%d", &n);
    printf("All prime numbers smaller than %d are: ", n);
    printPrimes(n);
    return 0;
}
