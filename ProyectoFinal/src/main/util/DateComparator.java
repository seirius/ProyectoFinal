package main.util;

import java.util.Comparator;

import main.modelo.PostComments;

public class DateComparator implements Comparator<PostComments> {

	@Override
	public int compare(PostComments o1, PostComments o2) {
		int res = o1.getFechaCreacion().compareTo(o2.getFechaCreacion());
		
		return res * (-1);
	}

}
