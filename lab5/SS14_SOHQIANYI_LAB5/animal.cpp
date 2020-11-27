#include "childAnimal.h"
#include "animal.h"
#include <iostream>

using namespace std;

Animal::Animal() : _name("unknown") {
    cout << "constructing Animal Object " << _name << endl;
}

Animal::Animal(string n, COLOR c) : _name(n), _color(c) {
    cout << "constructing " << COLOR(_color) << " " << _name << endl;
}

Animal::~Animal() {
    cout << "destructing Animal object " << _name << endl;
}
void Animal::speak() const {
    cout << "Animal speaks" << endl;
}
void Animal::move()  {
}


Mammal::Mammal() : _name("unknown") {
    cout << "constructing Mammal object " << _name << endl;
}

Mammal::Mammal(string n, COLOR c) : Animal(n, c) {
}

Mammal::~Mammal() {
    cout << "destructing Mammal object " << _name << endl;
}


void Mammal::eat() const {
    cout << "Mammal eats" << endl;
}

void Mammal::move()  {
    cout << "Mammal moves" << endl;
}

Dog::Dog() :_name("unknown"), _owner("unknown") {
    cout << "constructing Dog object " << _name << " with owner " << _owner << endl;
}

Dog::Dog(string n, COLOR c, string owner) : Mammal(n, c) {
    _owner = owner;
    cout << "Owner is " << _owner << endl;
}

Dog:: ~Dog() {
    cout << "Destructing dog object " << _name << endl;
}

void Dog::speak() const {
    cout << "Dog woofs" << endl;
}

void Dog::move() {
    cout << "Dog moves" << endl;
}


Cat::Cat() :_name("unknown"), _owner("unknown") {
    cout << "constructing Cat object " << _name << " with owner " << _owner << endl;
}

Cat::Cat(string n, COLOR c, string owner) : Mammal(n, c) {
    _owner = owner;
    cout << "Owner is " << _owner << endl;
}

Cat::~Cat() {
    cout << "destructing Cat object" << _name << endl;
}

void Cat::speak() const {
    cout << "Cat meows" << endl;
}

void Cat::move() {
    cout << "Cat moves" << endl;
}

Lion::Lion() :_name("unknown") {
    cout << "constructing Lion Object " << _name << endl;
}

Lion::Lion(string n, COLOR c) : Mammal(n, c) {
    cout << "Lion has no owner" << endl;
}

Lion::~Lion() {
    cout << "destructing Lion object " << _name << endl;
}

void Lion::speak() const {
    cout << "Lion roars" << endl;
}

void Lion::move() {
    cout << "Lion moves" << endl;
}

/*string getColour(COLOR c) {
    string colour;
    switch (c) {
    case 0:
        colour = "Green";
        break;
    case 1:
        colour = "Blue";
        break;
    case 2:
        colour = "White";
        break;
    case 3:
        colour = "Black";
        break;
    case 4:
        colour = "Brown";
        break;
    default:
        colour = "unknown";
        break;
    }
    return colour; */

