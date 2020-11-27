#ifndef LAB5_CHILDANIMAL_H
#define LAB5_CHILDANIMAL_H

#include <iostream>
#include <string>
#include "animal.h"
class Dog : public Mammal {
private:
    string _owner;
    string _name;
public:
    Dog();
    Dog(string n, COLOR c, string owner);
    ~Dog();
    void speak() const;
    void move();
};

class Cat : public Mammal {
private:
    string _owner;
    string _name;
public:
    Cat();
    Cat(string n, COLOR c, string owner);
    ~Cat();
    void speak() const;
    void move();
};

class Lion : public Mammal {
private:
    string _name;
public:
    Lion();
    Lion(string n, COLOR c);
    ~Lion();
    void speak() const;
    void move();
};

#endif