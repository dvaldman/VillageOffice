package sk.village.office.util;

public class ReportMailBuilder {
	
	private String PLACEHOLDER_POS = "{position}";
	private String PLACEHOLDER_TXT = "{body}";
	private String default_template = "Spr‡va poslan‡ z adresy: {position} \n\nText hl‡senia:\n\n{body}";
	
	
	private String mail;
	
	public ReportMailBuilder(){
		resetMail();
	}
	
	public void resetMail(){
		mail = default_template;
	}
	
	
	public ReportMailBuilder setMailPosition(String pos){
		mail = mail.replace(PLACEHOLDER_POS, pos);
		return this;
	}
	
	
	public ReportMailBuilder setMailText(String text){
		mail = mail.replace(PLACEHOLDER_TXT, text);
		return this;
	}

	public String getMail(){
		return mail;
	}
	
}
