#ifndef VENDING_MACHINE_H
#define VENDING_MACHINE_H

#include <vector>
#include <string>
#include "item.h"

class Vending_machine 
{
    public:
        void add(std::string name, int price);
        void buy(int index);
        std::string menu();

    private:
        std::vector<Item> items;
};

#endif