package basicTool;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import basicInterface.IPinyinHolder;

/**
 * 本类继承于RegistObject类型，
 * 实现了IPinyinHolder接口，
 * 获取拼音时将拼音对应到父类RegistObject的名字，
 * 内部用两个字符串来分别存储全拼音和短拼音，
 * 并且重载父类的setName()方法，
 * 设置名字的时候也要根据新的名字来设置全拼音和短拼音。
 */
public class RegistObjectWithPinyin extends RegistObject implements IPinyinHolder {
	protected String pinyin;
	protected String shortPinyin;
	
	public RegistObjectWithPinyin(String index, String name) {
		super(index, name);
		setPinyin(null);
		setShortPinyin(null);
	}
	
	/**
	 * 获取名字的拼音
	 * @return
	 *  	名字的拼音。
	 */
	@Override
	public String getPinyin() {
		return pinyin;
	}
	
	/**
	 * 设置名字，
	 * 这个方法在NameWithPinyin重载，
	 * 就是为了保证拼音与名字任何时刻都要保持一致。
	 * @param name
	 * 		要设置的名字。
	 */
	@Override
	public void setName(String name){
		super.setName(name);
		setPinyin(null);
		setShortPinyin(null);
	}

	/**
	 * 本方法不会利用参数，
	 * 而是直接从父类的NameHolder中获取名字然后将汉字转换为拼音。
	 * @param pinyinName
	 * 		本方法不会用参数做任何设置。
	 */
	@Override
	public void setPinyin(String pinyinName) {
		try {
			this.pinyin = PinyinHelper.convertToPinyinString(getName(), "", PinyinFormat.WITHOUT_TONE);
			return;
		} catch (PinyinException e) {
			MyLogger.logError("设置拼音名字失败。请检查名字：" + getName());
			MyLogger.logException(e);
		}
		this.pinyin = "";
	}

	/**
	 * 返回短拼音字符串，
	 * 就是将汉字换成对应拼音的首字母。
	 * @return
	 * 		名字的短拼音字符串。
	 */
	@Override
	public String getShortPinyin() {
		return shortPinyin;
	}

	/**
	 * 设置名字的短拼音字符串。
	 * @param shortPinyin
	 * 		这个参数不会用到，可以随便设置为null，
	 * 		这个方法或调用getName()方法获取名字，
	 * 		然后转换成短拼音形式。
	 */
	@Override
	public void setShortPinyin(String shortPinyin) {
		try {
			this.shortPinyin = PinyinHelper.getShortPinyin(getName());
			return;
		} catch (PinyinException e) {
			MyLogger.logError("设置拼音名字失败。请检查名字：" + getName());
			MyLogger.logException(e);
		}
		this.shortPinyin = "";
	}

}
