public class Filter {
	private String field;
	private String relation;
	private String target;
	
	public Filter(String f, String r, String t) {
		field = f;
		relation = r;
		target = t;
	}
	public String getField() {
		return field;
	}
	public String getRelation() {
		return relation;
	}
	public String getTarget() {
		return target;
	}
	public String toString() {
		return field + " " + relation + " " + target;
	}

}
