package com.shape;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class loadFile {
	static String load_content = "";
	public static String LoadDatabase(String FilePath) throws IOException
	{
		FileReader fr      = new FileReader(FilePath);
		/*BufferedReader bfr = new BufferedReader(fr);*/

        BufferedReader bfr = new BufferedReader(
           new InputStreamReader(
                      new FileInputStream(FilePath), "UTF8"));
		String line_text = "";
		while((line_text=bfr.readLine())!=null)
		{
			System.out.println(line_text);
			load_content += line_text + "\n";
		}
		fr.close();
		bfr.close();
		return load_content;
	}
}
