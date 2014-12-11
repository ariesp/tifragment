package ch.genidea.geniweb.base.utility;

public class Paging {
	public static long getTotalPage(long rowcount, long limit) {
		if (limit < 1)
			return 0;
		long page = rowcount / limit;
		return rowcount % limit > 0 ? page + 1 : page;
	}
}