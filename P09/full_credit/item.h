#ifndef ITEM_H
#define ITEM_H

#include <string>

class Item 
{
    private:
        std::string _name;
        int _price;

    public:
        Item(std::string name, int price);
        std::string to_string();
};

#endif