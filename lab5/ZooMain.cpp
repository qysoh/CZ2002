#include <iostream>
#include "animal.h"
#include "childAnimal.h"

int main() {
    int ch, i;

    cout << "Select the animal to send to Zoo: " << endl;
    cout << "(1) Dog" << endl;
    cout << "(2) Cat" << endl;
    cout << "(3) Lion" << endl;
    cout << "(4) Move all animals" << endl;
    cout << "(5) Quit" << endl;
    cin >> ch;
    i = 0;
    Mammal* m[10];
    while (ch != 5) {
        switch (ch) {
        case 1:
            m[i] = new Dog("Lassie", White, "Andy");
            i++;
            break;
        case 2:
            m[i] = new Cat("Kitty", Black, "Julie");
            i++;
            break;
        case 3:
            m[i] = new Lion("Rawr", Brown);
            i++;
            break;
        case 4:
            for (int j = 0; j < i; j++) {
                m[j]->move();
                m[j]->speak();
                m[j]->eat();
            }
            break;
        default:
            cout << "Select a choice: " << endl;
            break;

        }
        cout << "Select the animal to send to Zoo: " << endl;
        cout << "(1) Dog" << endl;
        cout << "(2) Cat" << endl;
        cout << "(3) Lion" << endl;
        cout << "(4) Move all animals" << endl;
        cout << "(5) Quit" << endl;
        cin >> ch;
    }
    cout << "Program completed" << endl;

    for (int j = 0;j < i; j++) {
        delete m[j];
    }

    return 0;
}
