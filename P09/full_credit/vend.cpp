#include "vending_machine.h"
#include <iostream>

int main() 
{
  Vending_machine machine;

  machine.add("Peanut butter crackers", 169);
  machine.add("Oreos", 189);

  std::cout << machine.menu() << std::endl;

  machine.buy(1);

  return 0;
}