package com.ikytus.ak.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static String decodeParam(String s) {
			try {
				return URLDecoder.decode(s, "UTF-8");
			} 
			catch (UnsupportedEncodingException e) {
				return "";
			}
		}	
		
		public static List<Long> decodeLongList(String categorias) {
			return Arrays.asList(categorias.split(",")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
		}
}
