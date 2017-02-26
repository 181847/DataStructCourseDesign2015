package collegeComponent.tool;

import basicInterface.IPositionHolder;

public class InfoInClub implements IPositionHolder {
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
