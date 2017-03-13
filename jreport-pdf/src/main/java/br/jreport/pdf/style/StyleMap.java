package br.jreport.pdf.style;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StyleMap {

	private static Map<String, Map<String, String>> mapStyle;

	public StyleMap() {
		createMapStyle();
	}

	public static Map<String, String> getClassStyle(String classe) {
		new StyleMap();
		if (mapStyle.containsKey(classe)) {
			return mapStyle.get(classe);
		} else {
			for (String key : mapStyle.keySet()) {
				if (key.equals(classe.split("-")[0])) {
					return mapStyle.get(key);
				}
			}
			return new HashMap<>();
		}
	}

	private void createMapStyle() {
		if (mapStyle == null) {
			Gson gson = new Gson();
			Type typeOfT = new TypeToken<Map<String, Map<String, String>>>() {
			}.getType();
			mapStyle = gson.fromJson(getFile("pdfstyle.json"), typeOfT);
		}
	}

	private String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
