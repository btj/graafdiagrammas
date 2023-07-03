package graafdiagrammas;

/**
 * @invar | 1 <= getStraal()
 */
public class CirkelvormigeKnoop extends Knoop {
	
	/**
	 * @invar | 1 <= straal
	 */
	int straal;
	
	public int getStraal() { return straal; }
	
	/**
	 * @throws IllegalArgumentException | straal <= 0
	 * @post | getStraal() == straal
	 * @post | getUitgaandeBogen().isEmpty()
	 * @post | getInkomendeBogen().isEmpty()
	 */
	public CirkelvormigeKnoop(int straal) {
		if (straal <= 0)
			throw new IllegalArgumentException("`straal` is not greater than zero");
		this.straal = straal;
	}
	
	/**
	 * @throws IllegalArgumentException | info == null
	 * @throws IllegalArgumentException | info.length < 2
	 * @inspects | this
	 * @mutates | info
	 * @post | info[0] == 2*getStraal()*314/100
	 * @post | info[1] == getStraal()*getStraal()*314/100
	 */
	@Override
	public void berekenInfo(int[] info) {
		if (info == null)
			throw new IllegalArgumentException("`info` is null");
		if (info.length < 2)
			throw new IllegalArgumentException("`info.length` is less than 2");
		info[0] = 2*straal*314/100;
		info[1] = straal*straal*314/100;
	}
	
	@Override
	public boolean isIsomorfMet(Knoop andere) {
		return andere instanceof CirkelvormigeKnoop k && straal == k.straal;
	}

}
