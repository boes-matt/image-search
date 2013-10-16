package com.boes.imagesearch;

public class GoogleAPI {
	
	// Request
	
	public static final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images";
	
	public static final String KEY_VERSION = "v";
	public static final String VERSION = "1.0";
	
	public static final String KEY_QUERY = "q";
	public static final String KEY_RESULT_SIZE = "rsz";
	public static final String KEY_START = "start";
	
	public static final int MAX_RESULT_SIZE = 8;
	public static final int MAX_TOTAL_RESULTS = 64;
	
	public static final String KEY_SIZE = "imgsz";
	public static final String[] SIZES = {"", "small", "medium", "large", "xlarge"};
	
	public static final String KEY_COLOR = "imgcolor";
	public static final String[] COLORS = {"", "black", "blue", "brown", "gray", "green", "orange", 
		                                   "pink", "purple", "red", "teal", "white", "yellow"};
	
	public static final String KEY_TYPE = "imgtype";
	public static final String[] TYPES = {"", "face", "photo", "clipart", "lineart"};
	
	public static final String KEY_SITE = "as_sitesearch";
	public static final String EMPTY = "";
	
	
	// Response
	
	public static final String KEY_STATUS = "responseStatus";
	public static final int RESULT_OK = 200;
	
	public static final String KEY_DATA = "responseData";           
	public static final String KEY_RESULTS = "results";
	
	public static final String KEY_URL = "url";
	public static final String KEY_TB_URL = "tbUrl";

}
