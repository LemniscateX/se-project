#include <stdio.h>
#include <time.h>
#include <stdbool.h>

int main() {
    while (true) {
        sleep(1);
        time_t rawtime;
        struct tm *timeinfo;
        time(&rawtime);
        timeinfo = localtime(&rawtime);
        printf("Those output come from child, %s", asctime(timeinfo));
    }
    return 0;
}