# EnigmaFSM

A finite state machine models the ciphering technique used by Enigma machines. This can successfully cipher and decipher text. 

Enigma machines were used during the second world war by Nazi Germany to send encrypted
messages between each other. The code was thought to be unbreakable until Alan Turing cracked
the code using his infamous BOMBE machine.

## Usage
Compile the java files using 
```
javac *.java
```
Once compiled run Enigma with the enigma settings and letter pairings file
```
java Enigma enigmasettings.txt letterpairings.txt <word to encode/decode>
```

The cipher is symmetric i.e. to decipher text just enter the ciphered text back into the program. 

## Enigma Settings and Letter Pairings
The Enigma settings file represents the initial rotor settings of the enigma machine. It is in the format
```
i j k
```
Where i, j, k are integers from 0-26. Any number below or greater will throw an error. 

The letter pairings file represents the "plugs" in the enigma machine where two letters can be paired together. This is in the format
```
A B
C D
```
