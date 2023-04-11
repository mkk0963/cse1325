#ifndef ITEM_H
#define ITEM_H

#include <string>
#include <iostream>

class Item 
{
    private:
        std::string _name;
        int _price;

    public:
        Item();
        Item(std::string name, int price);
        friend std::ostream& operator<<(std::ostream& os, const Item& item);
        friend std::istream& operator>>(std::istream& is, Item& item);
};

#endif