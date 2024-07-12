#include <CUnit/Basic.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

void test_socket_client(void) {
    int sockfd;
    struct sockaddr_in server_addr;

    // Create a socket
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    CU_ASSERT(sockfd >= 0);

    // Connect to a test server
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(8080);
    inet_pton(AF_INET, "127.0.0.1", &server_addr.sin_addr);
    CU_ASSERT(connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == 0);

    // Send and receive data
    char buffer[1024];
    CU_ASSERT(send(sockfd, "Hello", 5, 0) == 5);
    CU_ASSERT(recv(sockfd, buffer, 1024, 0) == 5);
    CU_ASSERT(strcmp(buffer, "World") == 0);

    // Close the socket
    CU_ASSERT(close(sockfd) == 0);
}

int main() {
    CU_initialize_registry();
    CU_add_test(test_socket_client);
    CU_run_tests();
    CU_cleanup_registry();
    return 0;
}
