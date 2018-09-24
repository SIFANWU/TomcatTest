package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//Extracts a jarfile to a destination directory
public class Extractor {

	private static String jarpath = "source/path/MyJar.jar";
	private static String destdir = "target/path/";

	// Main method extracts a jarfile to a set of
	// directories under the target/path/ directory
	public static void main(String[] args) throws IOException {
		JarFile jarfile = new JarFile(jarpath);
		for (Enumeration<JarEntry> iter = jarfile.entries(); iter.hasMoreElements();) {
			JarEntry entry = iter.nextElement();
			System.out.println("Processing: " + entry.getName());
			File outfile = new File(destdir, entry.getName());
			if (!outfile.exists())
				outfile.getParentFile().mkdirs(); // create dir structure

			if (!entry.isDirectory()) {
				InputStream instream = jarfile.getInputStream(entry);
				FileOutputStream outstream = new FileOutputStream(outfile);
				while (instream.available() > 0) {
					outstream.write(instream.read());
				}
				outstream.close();
				instream.close();
			} 
		} 
		jarfile.close();
	} 
} 
