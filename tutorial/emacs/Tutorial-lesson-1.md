// This is a new file called temp.cpp  
// C-x C-f : Opens a file, asks for the file name. if it cannot find the file, create the file.  
// C-x C-s : Saves the file without a prompt  
// C-x s   : Save all files with a prompt  
//  
// Everytime you save a file, Emacs creates another file with the neam "filename~".  
// This tilde(~) file is the previous version of the file. It will be in the same dir.  
// Also, Emacs auto-saves everything you type to a file with the name "#filename#".  
// If you quit Emacs without saving, you can see this auto-save file. Let's do that.  
// C-x C-c : Quits Emacs  
//  
// M-x recover-file : recovers the auto-saved file. ( M = Escape key)  
//  
// Copy/Cut/Paste Commands:  
// ------------------------  
// C-k     : Kill/deletes the whole line, puts it into the clipboard.  
// C-y     : Pastes whatever is in the clipboard at the cursor.  
//  
// C-space : starts marking/highlighting a region.  
// M-w     : Copies this region into the clipboard.  
// C-w     : Cuts this region into the clipboard (deletes the region and copies it to clipbrd)  
//  
// Saving a region involves hitting C-space to start selecting, and then hitting M-w or C-w to  
// copy or cut it into the clipboard, and then hitting C-y to paste it.  
// If you dont like the region you are selecting, hit C-g.  
// C-g     : Always quits your command.  
//  
// Cursor Commands:  
// ----------------  
// C-a     : Beginning of the line  
// C-e     : End of line  
// M->     : End of buffer  
// M-<     : Beginning of buffer.  
//  
// C-/     : Undo  
// C-g C-/ : Redo  
// Buffer Management Commands:  
// ---------------------------  
// C-x b   : Switches buffers, asks you which buffer to switch to  
// C-x C-b : Switches buffers, but show you list of buffers in a new window  
//   
// Hit C-x o (other window) to go to other window and hit <enter> on the buffer you want to 
// switch to.
// C-x 0   : Will close that window
// C-x 1   : Will leave olny one window
// C-x 2   : Will make a horizontal cut and show a secondary window
// C-x 3   : Will make a vertical cut and show a secundary window
//
