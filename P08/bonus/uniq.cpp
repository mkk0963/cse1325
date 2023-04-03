#include <iostream>

int main(int argc, char* argv[]) 
{
    std::string previous;

    for (int index = 1; index < argc; index++) 
    {
        std::string current(argv[index]);

        if (current != previous) 
        {
            std::cout << current << " ";
            previous = current;
        }
    }
    std::cout << std::endl;

    return 0;
}
