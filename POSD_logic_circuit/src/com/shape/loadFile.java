package com.shape;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class loadFile {
	public static void LoadDatabase(String FilePath) throws IOException
	{
		FileReader fr      = new FileReader(FilePath);
		/*BufferedReader bfr = new BufferedReader(fr);*/

        BufferedReader bfr = new BufferedReader(
           new InputStreamReader(
                      new FileInputStream(FilePath), "UTF8"));
		String tran = "";
		while((tran=bfr.readLine())!=null)
		{
			System.out.println(tran);
		}
		fr.close();
		bfr.close();
	}
}
