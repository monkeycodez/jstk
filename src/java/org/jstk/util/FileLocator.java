package org.jstk.util;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import static java.nio.file.StandardOpenOption.*;

/**
 * Static methods for locating files.
 * 	It locates 2 types of files:
 * 		Read files: it will search through multiple directories
 * 			until it finds it. If it is not found returns null.
 * 			It will search in the order dirs are passed to it.
 * 		Write files: it will create the file if it is not created
 * 			in the specified folder.
 */
public final class FileLocator{
	
	private FileLocator(){}
	
	private static String write_dir = ".";
	private static List<String> read_dirs = new LinkedList<String>();

	//Sets the write dir, and creates it if nesicary
	public static boolean create_and_set_write_dir(String dir){
		Path p = Paths.get(dir);
		if(Files.isDirectory(p)){
			write_dir = dir;
			return true;
		}else{
			//Create the dir if possible
			try{
				Files.createDirectories(p);
			}catch(Exception ex){
				return false;
			}
			write_dir = dir;
			return true;
		}
	}

	//Adds all dirs specified in order. If one fails, none are added
	//and returns false, else return true
	public static boolean add_read_dirs(String ... dirs){
		List<String> adds = new LinkedList<String>();
		for(String dir : dirs){
			Path p = Paths.get(dir);
			if(Files.isDirectory(p)){
				adds.add(dir);
			}else{
				return false;
			}
		}
		read_dirs.addAll(adds);
		return true;
	}

	/**
	 * Adds all dirs. If one fails/does not exist it will continue and
	 * 	add the others
	 */
	public static boolean add_all_read_dirs(String ... dirs){
		for(String dir : dirs){
			Path p = Paths.get(dir);
			if(Files.isDirectory(p)){
				read_dirs.add(dir);
			}
		}
		return true;
	}



	public static InputStream get_read_file(String file){
		for(String dir: read_dirs){
			Path p = Paths.get(dir, file);
			if(Files.exists(p)){
				try{
					return Files.newInputStream(p, READ);
				}catch(IOException ex){
					//Shouldn't happen
					ex.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * Will return a output stream of the file, creates it if needed.
	 * 	Returns null on error
	 */
	public static OutputStream get_write_file(String file){
		try{
			return Files.newOutputStream(
				Paths.get(write_dir, file),
				CREATE);
		}catch(IOException ex){
			//Semi-normal, but file should be created
			return null;
		}
	}
	


}



