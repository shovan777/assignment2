package fusemachines.backend.assignments.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReadJson {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String jsonFile = "1-0-994.txt";
		// String jsonFile = "test2.txt";
		String writeFile = "company_intro.txt";
		Scanner sReader = null;
		BufferedWriter sWriter = null;
		try {
			// file read
			sReader = new Scanner(new BufferedReader(new FileReader(jsonFile)));
			sWriter = new BufferedWriter(new FileWriter(writeFile));

			// gson magic
			List<Company> list = new ArrayList<Company>();
			Gson gson = new GsonBuilder().create();

			final Pattern[] patterns = {

					// remove all www. patterns
					Pattern.compile("www\\."),

					// remove all www_.patterns
					Pattern.compile("www.\\."),

					// remove all content after /
					Pattern.compile("/.*"),

					// Pattern.compile("^(\\w+\\.).*$"),

					// Pattern.compile("([a-z]?)*("),
					// Pattern.compile("www[0-9]"),
					// Pattern.compile("[a-z][0-9]."),
					// Others
			};
			
			int count = 0;

			final String[] replacements = { "",
					// Others
			};

			while (sReader.hasNextLine()) {
				sReader.useDelimiter(",");
				sReader.next();
				sReader.useDelimiter("");
				sReader.next();
				sReader.useDelimiter("\\p{javaWhitespace}+\n" + "");
				String jsonString = sReader.nextLine();
				Company c = gson.fromJson(jsonString, Company.class);
				// System.out.println(c);
				list.add(c);
				String companyIntro = c.getCompany() + ":" + c.getDescription();
				sWriter.write(companyIntro);
				sWriter.newLine();

				// Print all companies with keypeople > 0
				if (args[0].equals("vip")) {
					if (c.getKeyPeople().length > 0) {
						count++;
						System.out.println(String.format("%3d. %s", count, c.getCompany()));
					}
				}

				// Print all companies along with their fax
				if (args[0].equals("fax")) {
					String faxNums = c.getFax();
					faxNums = faxNums.replaceAll("-", "");
					if (!(faxNums.isEmpty())) {
						count ++;
						System.out.println(String.format("%3d. %-60s : %s", count, c.getCompany(), faxNums));
					}
				}

				// Print domains of the companies and remove all unrelated content from url
				if (args[0].equals("domain")) {
					String weburl = c.getWeburl();
					if (weburl != null) {
						count ++;
						// Match each pattern to the given weburl and replace them with empty string
						for (int i = 0; i < patterns.length; i++) {
							weburl = patterns[i].matcher(weburl).replaceAll(replacements[0]);
						}

						String[] webArray = weburl.split("\\.");
						int len = webArray.length;
						if (len > 2) {
							weburl = webArray[len - 2] + "." + webArray[len - 1];
						}
						
						System.out.println(String.format("%3d. %-80s : %s", count, c.getCompany(), weburl));

					}
				}

			}

		} finally {
			if (sReader != null) {
				sReader.close();
			}
			sWriter.close();
		}

	}
}
