#include <iostream>
#include <exception>
#include "item.h"

Item::Item()
{
    
}

Item::Item(std::string name, int price) 
    : _name{name}, _price{price} 
{
    if (price < 0) 
    {
        throw std::runtime_error("A price cannot be negative.");
    }
}

std::ostream& operator<<(std::ostream& os, const Item& item) 
{
    os << item._name << " ($" << item._price / 100 << "." << item._price % 100 << ")";

    return os;
}

std::istream& operator>>(std::istream& is, Item& item) 
{
    std::getline(is >> std::ws, item._name);
    std::string price_str;
    std::getline(is >> std::ws, price_str);
    item._price = std::stoi(price_str);

    return is;
}
