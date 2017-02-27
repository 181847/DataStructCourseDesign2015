package testSpace;

import unfinishedClass.SearchOperatorForMyMembers;

public class TestSearchOperatorForMyMembers extends Test {
	public static void main(String[] args){
		prepare();
		Test.showCollege();
		SearchOperatorForMyMembers sofmm = new SearchOperatorForMyMembers(college, "2012003");
		sofmm.setSearchInfo("");
		sofmm.selectSearchLog(-1);
		sofmm.operate();
		sofmm.getResult().getAllResult(allTrueFilter).traverseInfo(myMemberTraverser, allTrueFilter);
	}

}
