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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReadJson {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// System.out.println(args[0]);
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
			// int count = 0;
			while (sReader.hasNextLine()) {
				sReader.useDelimiter(",");
				sReader.next();
				sReader.useDelimiter("");
				sReader.next();
				sReader.useDelimiter("\\p{javaWhitespace}+\n" + "");
				String jsonString = sReader.nextLine();
				// System.out.println(s.delimiter());
				// System.out.println(jsonString);
				Company c = gson.fromJson(jsonString, Company.class);
				// System.out.println(c);
				list.add(c);
				String companyIntro = c.getCompany() + ":" + c.getDescription();
				sWriter.write(companyIntro);
				sWriter.newLine();
				if (args[0].equals("vip")) {
					if (c.getKeyPeople().length > 0) {
						System.out.println(c.getCompany());
					}
				}

				if (args[0].equals("fax")) {
					String faxNums = c.getFax();
					faxNums = faxNums.replaceAll("-", "");
					// System.out.println(faxNums);
					if (!(faxNums.isEmpty())) {
						System.out.println(c.getCompany() + ":" + faxNums);
						// count++;
					}
				}

				if (args[0].equals("domain")) {
					String weburl = c.getWeburl();
					// System.out.println(weburl);
					if (weburl != null) {
						weburl = weburl.replaceAll("www.", "");
						System.out.println(c.getCompany() + ": " + weburl);

					}
				}

			}
			// for (Company company : list) {
			// System.out.println(company);
			// }
			// System.out.println(count);
		} finally {
			if (sReader != null) {
				sReader.close();
			}
			sWriter.close();
		}

	}
}
