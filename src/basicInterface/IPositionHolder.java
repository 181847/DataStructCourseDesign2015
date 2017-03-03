package basicInterface;

/**
 * 定义了获取和设置职位的接口方法，
 * 这个所谓的职位是指社员在社团中的职位，
 * 比如说“会长”、“副会长”、“秘书”、“会员”.......
 */
public interface IPositionHolder {
	/**
	 * 设置职位。
	 * @param position
	 * 		职位信息，
	 * 		比如说“会长”、“副会长”、“秘书”、“会员”.......
	 */
	public void setPosition(String position);
	
	/**
	 * 获取职位信息。
	 * @return
	 * 		职位信息，
	 * 		比如说“会长”、“副会长”、“秘书”、“会员”.......
	 */
	public String getPosition();
}
