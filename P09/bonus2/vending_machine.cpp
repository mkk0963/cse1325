#include "vending_machine.h"
#include <iostream>

Vending_machine::Vending_machine()
{

}

Vending_machine::Vending_machine(std::istream& is)
{
    Item item;
    
    while (is >> item) 
    {
        items.push_back(item);
    }
}

void Vending_machine::add(std::string name, int price) 
{
    Item item(name, price);
    items.push_back(item);
}

void Vending_machine::buy(int index) 
{
    std::cout << "#### Buying " << items[index] << std::endl;
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