# file-system

File System project with a cmd look-a-like front, concurrent controller & a back-end File System.
The file system data-structure contains a multiple children tree, which each node is a map object, and those are the directories,
which they contains inner directories & files and so on, with an inner lexicographic searching tree.
Contains Parameter Runnable class, which is an extension of the consumer logic, with three parameters implementing Runnable in order to simplify the controller code.
Contains a JavaFX basic cmd look-a-like front-end.

Prior-Knowledge needed in order to test the application:

  Command List: 
  
                  1.addFile(parentDirName,fileName,fileSize).
                  
                  2.addDir(parentDirName,dirName).
                  
                  3.delete(name).
                  
                  4.showFileSystem()
                  
  Examples:
  
                  adding a file - addFile(Root,file1,450).
                  
                  adding a directory - addDir(Root,dir1).
                  
        	        deleting a file - delete(file1), or a dir delete(dir1).
            
                  showing the file system - showFileSystem().


  P.S - Very Important~!
   
                  1.the Root directory is the first and the main directory.
                             
                  2.all the commands and files are case sensitive and must be typed correctly.
                             
                  3.I hope you liked the program!.
