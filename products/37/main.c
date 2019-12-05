#include <stdio.h>
#include <unistd.h>
#include <stdbool.h>
#include <time.h>

int main() {
    int pid = fork();
    if (pid < 0) {
        printf("process create failed");
    } else if (pid > 0) {
        // This is parent process
        while (true) {
            sleep(1);
            time_t rawtime;
            struct tm *timeinfo;
            time(&rawtime);
            timeinfo = localtime(&rawtime);
            printf("Those output come from parent, %s", asctime(timeinfo));
        }
    } else {
        // This is child process
        execlp("./child", "./child", NULL);
    }
}