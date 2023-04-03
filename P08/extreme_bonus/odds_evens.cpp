#include <iostream>
#include <vector>

int main() 
{
  std::vector<int> v;
  int element;

  while (std::cin >> element) 
  {
    v.push_back(element);
  }

  std::cout << "Number of elements: " << v.size() << std::endl;

  std::cout << "Elements: ";
  for (auto i = v.begin(); i != v.end(); ++i) 
  {
    std::cout << *i << " ";
  }
  std::cout << std::endl;

  std::cout << "Even indices: ";
  for (int i = 0; i < v.size(); i += 2) 
  {
    std::cout << v[i] << " ";
  }
  std::cout << std::endl;

  std::cout << "Odd  indices: ";
  for (int i = 1; i < v.size(); i += 2) 
  {
    std::cout << v[i] << " ";
  }
  std::cout << std::endl;

  return 0;
}
