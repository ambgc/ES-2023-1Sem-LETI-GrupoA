package leti.grupoa.projeto_es;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ScheduleRepresent {

	private Schedule targetSchedule;
	
	ScheduleRepresent(Schedule s ){
		
		targetSchedule = s;
		
	}
	
	public Schedule getSchedule() {
		return targetSchedule;
	}
	
	
	public void htmlGen() throws IOException{
	
		File htmlF = new File("Schedule.html");
		if(htmlF.createNewFile()) {
			System.out.println("Ficheiro " + htmlF.getName()+ " criado\n");
		}else {
			System.out.println("Ocorreu um erro.\n "
					+ "Provavelmente é necessário apagar o ficheiro com o mesmo nome criado previamente.");
		}
		StringBuilder htmlBuild = new StringBuilder();
		ArrayList<String> list = getSchedule().getScheduleText();
		htmlBuild.append("<html>");
		htmlBuild.append("<head><title>Schedule File</title></head>");
		htmlBuild.append("<body>");
		htmlBuild.append("<h1>Schedule</h1>");
		htmlBuild.append("<ul>");
		for (String item : list) {
        	htmlBuild.append("<li>" + item + "</li>");
        }
        htmlBuild.append("</ul>");
        htmlBuild.append("</body>");
        htmlBuild.append("</html>");
        BufferedWriter writer = new BufferedWriter(new FileWriter(htmlF.getName(), true));
        writer.write(htmlBuild.toString());
	}
	
	
}
