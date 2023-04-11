#include <iostream>
#include <exception>
#include "item.h"


Item::Item(std::string name, int price) 
    : _name{name}, _price{price} 
{
    if (price < 0) 
    {
        throw std::runtime_error("A price cannot be negative.");
    }
}

std::string Item::to_string() 
{
    return _name + " ($" + std::to_string(_price / 100) + "." + std::to_string(_price % 100) + ")";
}
