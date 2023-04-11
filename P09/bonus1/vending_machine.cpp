#include "vending_machine.h"
#include <iostream>

void Vending_machine::add(std::string name, int price) 
{
    Item item(name, price);
    items.push_back(item);
}

void Vending_machine::buy(int index) 
{
    std::cout << "#### Buying " << items[index] << std::endl;
    items.erase(items.begin() + index);
}

std::ostream& operator<<(std::ostream& os, const Vending_machine& machine) 
{
    os << "======================" << std::endl;
    os << "Welcome to UTA Vending" << std::endl;
    os << "======================" << std::endl;
    
    for (int i = 0; i < machine.items.size(); ++i) 
    {
        os << i << ") " << machine.items[i] << std::endl;
    }

    return os;
}