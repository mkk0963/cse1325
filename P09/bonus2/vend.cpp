#include "vending_machine.h"
#include <iostream>
#include <fstream>

int main() 
{
  std::ifstream infile("products.txt");
  Vending_machine machine(infile);

  while(true)
  {
    int index;
    
    std::cout << "\n";
    std::cout << machine << "\n\n";
    std::cout << "Products? ";
    std::cin >> index;

    if(index < 0)
    {
      break;
    }
    machine.buy(index);
  }

  return 0; 
}