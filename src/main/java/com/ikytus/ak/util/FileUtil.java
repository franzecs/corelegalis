package com.ikytus.ak.util;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import javax.servlet.http.Part;

import org.springframework.stereotype.Service;

@Service
public class FileUtil {
	
	public void getCSV(Part arquivo) throws IOException {
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		scanner.useDelimiter(";");

		while(scanner.hasNext()) {
			String linha = scanner.nextLine();
			if(linha !=null && !linha.trim().isEmpty()) {
				linha = linha.replace("\"", "");
				String[] dados = linha.split("\\;");
				System.out.println("nome: " + dados[0] + " e o email: " + dados[1]);
			}
		}
		scanner.close();
	}
	
	public NumberFormat getNumberFormat() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	}
	
	public SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	}

}
