#ifndef VENDING_MACHINE_H
#define VENDING_MACHINE_H

#include <vector>
#include <string>
#include "item.h"

class Vending_machine 
{
    public:
        Vending_machine();
        Vending_machine(std::istream& is);
        void add(std::string name, int price);
        void buy(int index);
        friend std::ostream& operator<<(std::ostream& os, const Vending_machine& machine);

    private:
        std::vector<Item> items;
};

#endif