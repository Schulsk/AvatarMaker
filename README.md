I'm trying to make an avatar maker. You input a string of any size, and get a "unique" avatar.
In trying to create few collisions I have been interested in making my own, simple hash-function
without looking up too much on how they are done in todays standards.
My custom hash-function should be able to make an output that is of any length,
because I plan to use this output to seed a procedural generation with few inputs.

Currently I have one model that maps letters to pixels in a 5x6 image, and then
mirrors the pattern to make a sort of pixelated rorchach inkblot. The background
color is also determined by the input string in a convoluted and overly
complicated way, I'm sure, but that is part of the fun of doing this project.
I have also made a way to copy your image into a border of any length. Pretty kewl.
