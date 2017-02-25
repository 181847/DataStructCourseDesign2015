package unfinishedClass;

import basicInterface.IPositionInClub;

public class InfoInClub implements IPositionInClub {
	public String position;
	
	public InfoInClub(String position){
		this.position = position;
	}

	@Override
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String getPosition() {
		return position;
	}

}
