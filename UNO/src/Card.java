
public class Card {
	
	private String type;
	private String color;
	
	public Card()
	{
		type = null;
		color = null;
	}
	
	public Card(String type, String color)
	{
		this.type = type;
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public String getColor() {
		return color;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString()
	{
		if(color.contentEquals(""))
			return type;
		else
			return color + " " + type;
	}
}
