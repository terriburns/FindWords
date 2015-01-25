# FindWords

A game that produces all possible words given a set of letters (user input) and a dictionary (any .txt file with a list of words, separated by line). Displays all of the words lengths two up to the number of letters from the set. Includes nonsensical words. Front end made with Processing. See the program in action <a href="https://www.youtube.com/watch?v=8Ydv63oltX8&feature=youtu.be">here</a>.

This program has two “interfaces”:

1. DictionaryFrontEnd.java utlizes the Processing interface

2. FindWords.java represents the original back end, and uses the command line to read the dictionary file

Feel free to use your own dictionary.txt file to return various possible words. The current dictionary file I used contains 80,000 words sorted alphabetically. The data is stored + searched in an AVL tree (which also alleviates the need for the words to be listed alphabetically.) Note that this is a continual work in progress, with notable necessary bug fixes that will be implemented eventually. (See documentation & TODOs for more information.)
